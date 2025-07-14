package org.example.resources;

import org.example.entities.Venda;
import org.example.services.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/vendas")
public class VendaResource {

    @Autowired
    private VendaService vendaService;

    @GetMapping
    public ResponseEntity<List<Venda>> getAll() {
        List<Venda> vendas = vendaService.getAll();
        return ResponseEntity.ok(vendas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> findById(@PathVariable Long id) {
        Venda venda = vendaService.findById(id);
        return ResponseEntity.ok(venda);
    }

    @PostMapping
    public ResponseEntity<Venda> insert(@RequestBody Venda venda) {
        Venda createdVenda = vendaService.insert(venda);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVenda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Venda venda) {
        if (vendaService.update(id, venda)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        vendaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
