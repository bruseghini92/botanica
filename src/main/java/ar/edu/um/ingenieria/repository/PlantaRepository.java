package ar.edu.um.ingenieria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.um.ingenieria.domain.Planta;

@Repository
public interface PlantaRepository  extends JpaRepository<Planta, Integer> {

}