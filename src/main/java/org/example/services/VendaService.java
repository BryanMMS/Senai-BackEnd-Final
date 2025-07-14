package org.example.services;

import org.example.entities.ItemVenda;
import org.example.entities.Produto;
import org.example.entities.Venda;
import org.example.repositories.ProdutoRepository;
import org.example.repositories.VendaRepository;
import org.example.services.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private VendaRepository repository;

    // Buscar todas as vendas
    public List<Venda> getAll() {
        return repository.findAll();
    }

    // Buscar venda por id
    public Venda findById(Long id) {
        Optional<Venda> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }


    public Venda insert(Venda venda) {
        for (ItemVenda item : venda.getItens()) {
            item.setVenda(venda);

            Long proId = item.getProduto().getProId();
            Produto produto = produtoRepository.findById(proId)
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: " + proId));

            // Atualiza o estoque do produto
            int estoqueAtual = produto.getProQuantidade();
            int novaQuantidade = estoqueAtual - item.getIvdQuantidade();

            if (novaQuantidade < 0) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getProId());
            }

            produto.setProQuantidade(novaQuantidade);
            produtoRepository.save(produto);

            item.setProduto(produto);

            double precoUnitario = produto.getProPrecoVenda();
            item.setIvdPrecoUnitario(precoUnitario);

            double subtotal = precoUnitario * item.getIvdQuantidade();
            item.setIvdSubtotal(subtotal);
        }

        double total = venda.getItens().stream()
                .mapToDouble(ItemVenda::getIvdSubtotal)
                .sum();
        venda.setVndTotal(total);

        return repository.save(venda);
    }




    public boolean update(Long id, Venda venda) {
        Optional<Venda> optionalVenda = repository.findById(id);
        if (optionalVenda.isPresent()) {
            Venda vendaSistema = optionalVenda.get();

            // 1. Devolver ao estoque as quantidades dos itens antigos
            for (ItemVenda itemAntigo : vendaSistema.getItens()) {
                Produto produtoAntigo = produtoRepository.findById(itemAntigo.getProduto().getProId())
                        .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: " + itemAntigo.getProduto().getProId()));

                int novoEstoque = produtoAntigo.getProQuantidade() + itemAntigo.getIvdQuantidade();
                produtoAntigo.setProQuantidade(novoEstoque);
                produtoRepository.save(produtoAntigo);
            }

            // Atualizar os campos básicos da venda
            vendaSistema.setCliente(venda.getCliente());
            vendaSistema.setFuncionario(venda.getFuncionario());
            vendaSistema.setFormaPagamento(venda.getFormaPagamento());
            vendaSistema.setVndDataVenda(venda.getVndDataVenda());
            vendaSistema.setVndConcluida(venda.getVndConcluida());
            vendaSistema.setVndObservacao(venda.getVndObservacao());

            List<ItemVenda> itensAtualizados = venda.getItens();

            // 2. Atualizar os itens da venda e descontar estoque dos produtos novos
            for (ItemVenda item : itensAtualizados) {
                item.setVenda(vendaSistema);

                Long proId = item.getProduto().getProId();
                Produto produto = produtoRepository.findById(proId)
                        .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: " + proId));

                int novoEstoque = produto.getProQuantidade() - item.getIvdQuantidade();
                if (novoEstoque < 0) {
                    throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getProId());
                }
                produto.setProQuantidade(novoEstoque);
                produtoRepository.save(produto);

                item.setProduto(produto);

                double precoUnitario = produto.getProPrecoVenda();
                item.setIvdPrecoUnitario(precoUnitario);

                double subtotal = precoUnitario * item.getIvdQuantidade();
                item.setIvdSubtotal(subtotal);
            }

            vendaSistema.setItens(itensAtualizados);

            // Recalcula o total da venda somando os subtotais
            double total = itensAtualizados.stream()
                    .mapToDouble(ItemVenda::getIvdSubtotal)
                    .sum();
            vendaSistema.setVndTotal(total);

            repository.save(vendaSistema);
            return true;
        }
        return false;
    }



    // Deletar venda por id
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
