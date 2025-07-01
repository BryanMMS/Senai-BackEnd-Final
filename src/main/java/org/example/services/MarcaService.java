package org.example.services;


import org.example.entities.FormaPagamento;
import org.example.entities.Marca;
import org.example.repositories.MarcaRepository;
import org.example.services.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository repository;

    public List<Marca> getAll(){
        return repository.findAll();
    }

    public Marca findById(Long id){
        Optional<Marca> obj = repository.findById(id);
        return obj.orElseThrow(() ->new ResourceNotFoundException(id));
    }

    public Marca insert(Marca marca){ return repository.save(marca);}

    public boolean update(Long id, Marca marca) {
        Optional<Marca> optionalMarca = repository.findById(id);
        if (optionalMarca.isPresent()) {
            Marca marcaSistema = optionalMarca.get();
            marcaSistema.setMarNome(marca.getMarNome());
            marcaSistema.setMarDescricao(marca.getMarDescricao());
            marcaSistema.setMarDataAtualizado(marca.getMarDataAtualizado());
            marcaSistema.setMarAtivo(marca.getMarAtivo());
            repository.save(marcaSistema);
            return true;
        }
        return false;
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
