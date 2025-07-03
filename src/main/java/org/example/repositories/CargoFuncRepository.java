package org.example.repositories;

import org.example.entities.CargoFunc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoFuncRepository extends JpaRepository<CargoFunc, Long> {
}
