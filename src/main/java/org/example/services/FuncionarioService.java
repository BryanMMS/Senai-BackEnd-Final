package org.example.services;

import org.example.dto.FuncionarioDTO;
import org.example.entities.Contato;
import org.example.entities.Endereco;
import org.example.entities.Funcionario;
import org.example.entities.CargoFunc;
import org.example.repositories.ContatoRepository;
import org.example.repositories.EnderecoRepository;
import org.example.repositories.FuncionarioRepository;
import org.example.repositories.CargoFuncRepository;
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

    @Autowired
    private CargoFuncRepository cargoFuncRepository;

    public List<Funcionario> getAll() {
        return repository.findAll();
    }

    public Funcionario findById(Long id) {
        Optional<Funcionario> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Funcionario insert(Funcionario obj) {
        try {
            obj.setFunId(null);
            obj = repository.save(obj);
            enderecoRepository.saveAll(obj.getEnderecos());
            contatoRepository.saveAll(obj.getContatos());
            return obj;
        } catch (DataIntegrityViolationException e) {
            throw new ValueBigForAtributeException(e.getMessage());
        }
    }

    public Funcionario update(Long id, FuncionarioDTO objDto) {
        try {
            Funcionario funcionario = findById(id);

            funcionario.setFunNome(objDto.getFunNome());
            funcionario.setFunCpf(objDto.getFunCpf());
            funcionario.setFunDataAdmissao(objDto.getFunDataAdmissao());

            CargoFunc cargoFunc = cargoFuncRepository.findById(objDto.getCarId())
                    .orElseThrow(() -> new ResourceNotFoundException("Cargo não encontrado com ID: " + objDto.getCarId()));
            funcionario.setCargoFunc(cargoFunc);

            Endereco endereco = funcionario.getEnderecos().get(0);
            endereco.setEndRua(objDto.getEndRua());
            endereco.setEndNumero(objDto.getEndNumero());
            endereco.setEndCidade(objDto.getEndCidade());
            endereco.setEndCep(objDto.getEndCep());
            endereco.setEndEstado(objDto.getEndEstado());

            Contato contato = funcionario.getContatos().get(0);
            contato.setConCelular(objDto.getConCelular());
            contato.setConTelefoneComercial(objDto.getConTelefoneComercial());
            contato.setConEmail(objDto.getConEmail());

            return repository.save(funcionario);
        } catch (DataIntegrityViolationException e) {
            throw new ValueBigForAtributeException(e.getMessage());
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
        CargoFunc cargoFunc = cargoFuncRepository.findById(objDto.getCarId())
                .orElseThrow(() -> new ResourceNotFoundException("Cargo não encontrado com ID: " + objDto.getCarId()));

        Funcionario funcionario = new Funcionario(null, cargoFunc, objDto.getFunNome(), objDto.getFunCpf(), objDto.getFunDataAdmissao());

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

        dto.setFunId(obj.getFunId());
        dto.setFunCpf(obj.getFunCpf());
        dto.setFunNome(obj.getFunNome());
        dto.setFunDataAdmissao(obj.getFunDataAdmissao());
        dto.setCarId(obj.getCargoFunc().getCarId());

        Endereco endereco = obj.getEnderecos().get(0);
        dto.setEndRua(endereco.getEndRua());
        dto.setEndNumero(endereco.getEndNumero());
        dto.setEndCidade(endereco.getEndCidade());
        dto.setEndCep(endereco.getEndCep());
        dto.setEndEstado(endereco.getEndEstado());

        Contato contato = obj.getContatos().get(0);
        dto.setConCelular(contato.getConCelular());
        dto.setConTelefoneComercial(contato.getConTelefoneComercial());
        dto.setConEmail(contato.getConEmail());

        return dto;
    }
}
