package org.example.resources;


import org.apache.coyote.Response;
import org.example.entities.Categoria;
import org.example.entities.FormaPagamento;
import org.example.entities.Marca;
import org.example.repositories.CategoriaRepository;
import org.example.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;


    @GetMapping
    public ResponseEntity<List<Categoria>> getAll(){
        List<Categoria> funcoes = categoriaService.getAll();
        return ResponseEntity.ok(funcoes);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Long id){
        Categoria obj = categoriaService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Categoria categoria){
        if (categoriaService.update(id, categoria)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Categoria> insert(@RequestBody Categoria categoria) {
        Categoria createdCategoria = categoriaService.insert(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoria);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
