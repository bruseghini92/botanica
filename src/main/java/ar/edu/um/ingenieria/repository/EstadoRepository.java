package ar.edu.um.ingenieria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.um.ingenieria.domain.Estado;

@Repository
public interface EstadoRepository  extends JpaRepository<Estado, Integer> {

}