package org.example.services;

import org.example.entities.Categoria;
import org.example.entities.Marca;
import org.example.repositories.CategoriaRepository;
import org.example.repositories.MarcaRepository;
import org.example.services.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {


    @Autowired
    private CategoriaRepository repository;

    public List<Categoria> getAll(){
        return repository.findAll();
    }

    public Categoria findById(Long id){
        Optional<Categoria> obj = repository.findById(id);
        return obj.orElseThrow(() ->new ResourceNotFoundException(id));
    }

    public Categoria insert(Categoria categoria){ return repository.save(categoria);}

    public boolean update(Long id, Categoria categoria) {
        Optional<Categoria> optionalCategoria = repository.findById(id);
        if (optionalCategoria.isPresent()) {
            Categoria categoriaSistema = optionalCategoria.get();
            categoriaSistema.setCtgNome(categoria.getCtgNome());
            categoriaSistema.setCtgDescricao(categoria.getCtgDescricao());
            categoriaSistema.setCtgDataAtualizado(categoria.getCtgDataAtualizado());
            categoriaSistema.setCtgAtivo(categoria.getCtgAtivo());
            repository.save(categoriaSistema);
            return true;
        }
        return false;
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }
}


