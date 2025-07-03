package org.example.resources;

import org.example.entities.CargoFunc;
import org.example.entities.Marca;
import org.example.services.CargoFuncService;
import org.example.services.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/cargosfunc")
public class CargoFuncResource {

    @Autowired
    private CargoFuncService cargoFuncService;


    @GetMapping
    public ResponseEntity<List<CargoFunc>> getAll(){
        List<CargoFunc> funcoes = cargoFuncService.getAll();
        return ResponseEntity.ok(funcoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargoFunc> findById(@PathVariable Long id){
        CargoFunc obj = cargoFuncService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<CargoFunc> insert(@RequestBody CargoFunc cargoFunc) {
        CargoFunc createdCargo = cargoFuncService.insert(cargoFunc);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCargo);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CargoFunc cargoFunc){
        if (cargoFuncService.update(id, cargoFunc)){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        cargoFuncService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
