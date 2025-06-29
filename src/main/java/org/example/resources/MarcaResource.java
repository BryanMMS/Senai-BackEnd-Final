package org.example.resources;


import org.example.entities.Categoria;
import org.example.entities.Marca;
import org.example.services.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/marcas")
public class MarcaResource {

    @Autowired
    private MarcaService marcaService;


    @GetMapping
    public ResponseEntity<List<Marca>> getAll(){
        List<Marca> funcoes = marcaService.getAll();
        return ResponseEntity.ok(funcoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> findById(@PathVariable Long id){
        Marca obj = marcaService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Marca> insert(@RequestBody Marca marca) {
        Marca createdMarca = marcaService.insert(marca);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMarca);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Marca marca){
        if (marcaService.update(id, marca)){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        marcaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
