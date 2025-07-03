package org.example.services;

import org.example.entities.CargoFunc;
import org.example.entities.Marca;

import org.example.repositories.CargoFuncRepository;
import org.example.repositories.MarcaRepository;
import org.example.services.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargoFuncService {

    @Autowired
    private CargoFuncRepository repository;

    public List<CargoFunc> getAll(){
        return repository.findAll();
    }

    public CargoFunc findById(Long id){
        Optional<CargoFunc> obj = repository.findById(id);
        return obj.orElseThrow(() ->new ResourceNotFoundException(id));
    }

    public CargoFunc insert(CargoFunc cargoFunc){ return repository.save(cargoFunc);}

    public boolean update(Long id, CargoFunc cargoFunc) {
        Optional<CargoFunc> optionalCargo = repository.findById(id);
        if (optionalCargo.isPresent()) {
            CargoFunc cargoSistema = optionalCargo.get();
            cargoSistema.setCarNome(cargoFunc.getCarNome());
            cargoSistema.setCarDescricao(cargoFunc.getCarDescricao());
            cargoSistema.setCarDataAtualizado(cargoFunc.getCarDataAtualizado());
            cargoSistema.setCarAtivo(cargoFunc.getCarAtivo());
            repository.save(cargoSistema);
            return true;
        }
        return false;
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
