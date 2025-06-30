package org.example.services;

import org.example.dto.FornecedorDTO;
import org.example.dto.FuncionarioDTO;
import org.example.entities.Contato;
import org.example.entities.Endereco;
import org.example.entities.Fornecedor;
import org.example.entities.Funcionario;
import org.example.repositories.ContatoRepository;
import org.example.repositories.EnderecoRepository;
import org.example.repositories.FornecedorRepository;
import org.example.repositories.FuncionarioRepository;
import org.example.services.exeptions.ResourceNotFoundException;
import org.example.services.exeptions.ValueBigForAtributeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    public List<Funcionario> getAll(){
        return repository.findAll();
    }

    public Funcionario findById(Long id){
        Optional<Funcionario> obj = repository.findById(id);
        return obj.orElseThrow(() -> new
                ResourceNotFoundException(id));
    }

    public Funcionario insert(Funcionario obj){
        try{
            obj.setFunId(null);
            obj = repository.save(obj);
            enderecoRepository.saveAll(obj.getEnderecos());
            contatoRepository.saveAll(obj.getContatos());
            return obj;
        }catch (DataIntegrityViolationException e){
            throw  new ValueBigForAtributeException(e.getMessage());
        }
    }

    public  Funcionario update(Long id, FuncionarioDTO objDto){
        try {
            Funcionario funcionario = findById(id);
            //Atualiza os dados do Fornecedor
            funcionario.setFunNome(objDto.getFunNome());
            funcionario.setFunCpf(objDto.getFunCpf());
            funcionario.setFunDataAdmissao(objDto.getFunDataAdmissao());

            //Atualiza o endereço do Forne
            Endereco endereco = funcionario.getEnderecos().get(0);
            //Assumindo que há apenas um endereço por Fornecedor
            endereco.setEndRua(objDto.getEndRua());
            endereco.setEndNumero(objDto.getEndNumero());
            endereco.setEndCidade(objDto.getEndCidade());
            endereco.setEndCep(objDto.getEndCep());
            endereco.setEndEstado(objDto.getEndEstado());

            //Atualiza o contato
            Contato contato = funcionario.getContatos().get(0);
            //Assumindo que há apenas um contato por Fornecedor
            contato.setConCelular(objDto.getConCelular());
            contato.setConTelefoneComercial(objDto.getConTelefoneComercial());
            contato.setConEmail(objDto.getConEmail());

            //Salva as alterações
            repository.save(funcionario);
            return funcionario;
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

    public Funcionario fromDTO(FuncionarioDTO objDto) {
        Funcionario funcionario = new Funcionario(null, objDto.getFunNome(), objDto.getFunCpf(), objDto.getFunDataAdmissao());

        Endereco endereco = new Endereco(null, funcionario, objDto.getEndRua(), objDto.getEndNumero(),
                objDto.getEndCidade(), objDto.getEndCep(), objDto.getEndEstado());

        Contato contato = new Contato(null, funcionario, objDto.getConCelular(), objDto.getConTelefoneComercial(),
                objDto.getConEmail());

        funcionario.getEnderecos().add(endereco);
        funcionario.getContatos().add(contato);

        return funcionario;
    }

    public FuncionarioDTO toNewDTO(Funcionario obj) {
        FuncionarioDTO dto = new FuncionarioDTO();

// Mapeie os atributos comuns entre Fornecedor e FornecedorNewDTO
        dto.setFunId(obj.getFunId());
        dto.setFunCpf(obj.getFunCpf());
        dto.setFunNome(obj.getFunNome());
        dto.setFunDataAdmissao(obj.getFunDataAdmissao());

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
