package org.example.services;

import org.example.entities.ItemVenda;
import org.example.entities.Produto;
import org.example.entities.Venda;
import org.example.entities.FormaPagamento; // Importe FormaPagamento
import org.example.repositories.ClienteRepository;
import org.example.repositories.FuncionarioRepository;
import org.example.repositories.FormaPagamentoRepository;
import org.example.repositories.ProdutoRepository;
import org.example.repositories.VendaRepository;
import org.example.services.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal; // Importe BigDecimal
import java.math.RoundingMode; // Importe RoundingMode
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private VendaRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    // Buscar todas as vendas
    public List<Venda> getAll() {
        return repository.findAll();
    }

    // Buscar venda por id
    public Venda findById(Long id) {
        Optional<Venda> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public Venda insert(Venda venda) {
        // Carregar a Forma de Pagamento completa do banco para obter a taxa correta
        FormaPagamento formaPagamentoDb = formaPagamentoRepository.findById(venda.getFormaPagamento().getFpgId())
                .orElseThrow(() -> new ResourceNotFoundException("Forma de Pagamento não encontrada"));
        venda.setFormaPagamento(formaPagamentoDb); // Garante que a forma de pagamento esteja gerenciada e completa

        double totalSemJuros = 0.0;
        for (ItemVenda item : venda.getItens()) {
            item.setVenda(venda);

            Long proId = item.getProduto().getProId();
            Produto produto = produtoRepository.findById(proId)
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: " + proId));

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
            totalSemJuros += subtotal; // Acumula o subtotal
        }

        // Calcula o total final da venda, aplicando a taxa da forma de pagamento do banco
        if (formaPagamentoDb.getFpgPermiteParcelamento() && formaPagamentoDb.getFpgTaxaAdicional() != null) {
            BigDecimal taxaBd = formaPagamentoDb.getFpgTaxaAdicional(); // É BigDecimal

            BigDecimal totalSemJurosBd = BigDecimal.valueOf(totalSemJuros);

            // Cálculo: totalSemJuros + (totalSemJuros * taxa / 100)
            BigDecimal cem = BigDecimal.valueOf(100);
            BigDecimal taxaPercentual = taxaBd.divide(cem, 4, RoundingMode.HALF_UP); // Divide por 100
            BigDecimal valorTaxa = totalSemJurosBd.multiply(taxaPercentual);
            BigDecimal totalComTaxaBd = totalSemJurosBd.add(valorTaxa);

            venda.setVndTotal(totalComTaxaBd.doubleValue()); // Converte o resultado final para double
        } else {
            venda.setVndTotal(totalSemJuros);
        }

        return repository.save(venda);
    }

    @Transactional
    public boolean update(Long id, Venda venda) {
        Optional<Venda> optionalVenda = repository.findById(id);
        if (!optionalVenda.isPresent()) {
            return false;
        }
        Venda vendaSistema = optionalVenda.get();

        // 1. Devolver ao estoque as quantidades dos itens antigos
        for (ItemVenda itemAntigo : vendaSistema.getItens()) {
            Produto produtoAntigo = produtoRepository.findById(itemAntigo.getProduto().getProId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: " + itemAntigo.getProduto().getProId()));

            int novoEstoque = produtoAntigo.getProQuantidade() + itemAntigo.getIvdQuantidade();
            produtoAntigo.setProQuantidade(novoEstoque);
            produtoRepository.save(produtoAntigo);
        }

        // Carregar entidades relacionadas do banco para garantir que são gerenciadas
        var cliente = clienteRepository.findById(venda.getCliente().getCliId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        var funcionario = funcionarioRepository.findById(venda.getFuncionario().getFunId())
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario não encontrado"));

        // Importante: Carregar a FORMA DE PAGAMENTO do banco para ter a taxa correta
        var formaPagamento = formaPagamentoRepository.findById(venda.getFormaPagamento().getFpgId())
                .orElseThrow(() -> new ResourceNotFoundException("FormaPagamento não encontrado"));

        // Atualizar os campos básicos da venda
        vendaSistema.setCliente(cliente);
        vendaSistema.setFuncionario(funcionario);
        vendaSistema.setFormaPagamento(formaPagamento); // Associa a forma de pagamento carregada do BD
        vendaSistema.setVndDataVenda(venda.getVndDataVenda());
        vendaSistema.setVndConcluida(venda.getVndConcluida());
        vendaSistema.setVndObservacao(venda.getVndObservacao());

        // Limpar itens antigos para evitar problemas com orphanRemoval
        vendaSistema.getItens().clear();

        // Variável para acumular o total sem juros
        double totalSemJuros = 0.0;

        // 2. Atualizar os itens da venda e descontar estoque dos produtos novos
        for (ItemVenda item : venda.getItens()) {
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
            totalSemJuros += subtotal; // Acumula o subtotal para o cálculo do total da venda

            vendaSistema.getItens().add(item);
        }

        // AQUI ESTÁ A MUDANÇA PRINCIPAL: RECALCULAR O vndTotal com BigDecimal e converter para double
        if (formaPagamento.getFpgPermiteParcelamento() && formaPagamento.getFpgTaxaAdicional() != null) {
            BigDecimal taxaBd = formaPagamento.getFpgTaxaAdicional(); // É BigDecimal

            BigDecimal totalSemJurosBd = BigDecimal.valueOf(totalSemJuros);

            // Cálculo: totalSemJuros + (totalSemJuros * taxa / 100)
            BigDecimal cem = BigDecimal.valueOf(100);
            BigDecimal taxaPercentual = taxaBd.divide(cem, 4, RoundingMode.HALF_UP); // Divide por 100
            BigDecimal valorTaxa = totalSemJurosBd.multiply(taxaPercentual);
            BigDecimal totalComTaxaBd = totalSemJurosBd.add(valorTaxa);

            vendaSistema.setVndTotal(totalComTaxaBd.doubleValue()); // Converte o resultado final para double
        } else {
            vendaSistema.setVndTotal(totalSemJuros);
        }

        repository.save(vendaSistema);
        return true;
    }

    // Deletar venda por id
    @Transactional
    public void delete(Long id) {
        Venda venda = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada com ID: " + id));

        // 1. Devolver o estoque dos produtos
        for (ItemVenda item : venda.getItens()) {
            Produto produto = produtoRepository.findById(item.getProduto().getProId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: " + item.getProduto().getProId()));

            int novoEstoque = produto.getProQuantidade() + item.getIvdQuantidade();
            produto.setProQuantidade(novoEstoque);
            produtoRepository.save(produto);
        }

        // 2. Excluir a venda
        repository.deleteById(id);
    }
}