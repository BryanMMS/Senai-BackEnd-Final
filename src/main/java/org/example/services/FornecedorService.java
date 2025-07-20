package org.example.services;


import org.example.dto.ClienteDTO;
import org.example.dto.FornecedorDTO;
import org.example.entities.*;
import org.example.repositories.ClienteRepository;
import org.example.repositories.ContatoRepository;
import org.example.repositories.EnderecoRepository;
import org.example.repositories.FornecedorRepository;
import org.example.services.exeptions.ResourceNotFoundException;
import org.example.services.exeptions.ValueBigForAtributeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {
    @Autowired
    private FornecedorRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    public  List<Fornecedor> getAll(){
        return repository.findAll();
    }

    public Fornecedor findById(Long id){
        Optional<Fornecedor> obj = repository.findById(id);
        return obj.orElseThrow(() -> new
                ResourceNotFoundException(id));
    }

    public Fornecedor insert(Fornecedor obj){
        try{
            obj.setForId(null);
            obj = repository.save(obj);
            enderecoRepository.saveAll(obj.getEnderecos());
            contatoRepository.saveAll(obj.getContatos());
            return obj;
        }catch (DataIntegrityViolationException e){
            throw  new ValueBigForAtributeException(e.getMessage());
        }
    }

    public  Fornecedor update(Long id, FornecedorDTO objDto){
        try {
            Fornecedor fornecedor = findById(id);
            //Atualiza os dados do Fornecedor
            fornecedor.setForCnpj(objDto.getForCnpj());
            fornecedor.setForRazaoSocial(objDto.getForRazaoSocial());
            fornecedor.setForNomeFantasia(objDto.getForNomeFantasia());
            fornecedor.setForAtivo(objDto.getForAtivo());


            //Atualiza o endereço do Forne
            Endereco endereco = fornecedor.getEnderecos().get(0);
            //Assumindo que há apenas um endereço por Fornecedor
            endereco.setEndRua(objDto.getEndRua());
            endereco.setEndNumero(objDto.getEndNumero());
            endereco.setEndCidade(objDto.getEndCidade());
            endereco.setEndCep(objDto.getEndCep());
            endereco.setEndEstado(objDto.getEndEstado());

            //Atualiza o contato
            Contato contato = fornecedor.getContatos().get(0);
            //Assumindo que há apenas um contato por Fornecedor
            contato.setConCelular(objDto.getConCelular());
            contato.setConTelefoneComercial(objDto.getConTelefoneComercial());
            contato.setConEmail(objDto.getConEmail());

            //Salva as alterações
            repository.save(fornecedor);
            return fornecedor;
        }catch (DataIntegrityViolationException e){
            throw new ValueBigForAtributeException(e.getMessage()
            );
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public Fornecedor fromDTO(FornecedorDTO objDto) {
        Fornecedor fornecedor = new Fornecedor(null, objDto.getForNomeFantasia(), objDto.getForCnpj(), objDto.getForRazaoSocial(),objDto.getForAtivo());

        Endereco endereco = new Endereco(null, fornecedor, objDto.getEndRua(), objDto.getEndNumero(),
                objDto.getEndCidade(), objDto.getEndCep(), objDto.getEndEstado());

        Contato contato = new Contato(null, fornecedor, objDto.getConCelular(), objDto.getConTelefoneComercial(),
                objDto.getConEmail());

        fornecedor.getEnderecos().add(endereco);
        fornecedor.getContatos().add(contato);

        return fornecedor;
    }

    public FornecedorDTO toNewDTO(Fornecedor obj) {
        FornecedorDTO dto = new FornecedorDTO();

// Mapeie os atributos comuns entre Fornecedor e FornecedorNewDTO
        dto.setForId(obj.getForId());
        dto.setForCnpj(obj.getForCnpj());
        dto.setForNomeFantasia(obj.getForNomeFantasia());
        dto.setForRazaoSocial(obj.getForRazaoSocial());
        dto.setForAtivo(obj.getForAtivo());

// Atributos específicos de Endereco
        Endereco endereco = obj.getEnderecos().get(0);
        dto.setEndRua(endereco.getEndRua());
        dto.setEndNumero(endereco.getEndNumero());
        dto.setEndCidade(endereco.getEndCidade());
        dto.setEndCep(endereco.getEndCep());
        dto.setEndEstado(endereco.getEndEstado());

// Atributos específicos de Contato
        Contato contato = obj.getContatos().get(0);
        dto.setConCelular(contato.getConCelular());
        dto.setConTelefoneComercial(contato.getConTelefoneComercial());
        dto.setConEmail(contato.getConEmail());

        return dto;
    }
}


