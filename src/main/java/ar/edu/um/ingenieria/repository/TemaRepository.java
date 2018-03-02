package ar.edu.um.ingenieria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.edu.um.ingenieria.domain.Tema;

@Repository
public interface TemaRepository  extends JpaRepository<Tema, Integer> {
	@Query("SELECT t FROM Tema t WHERE t.categoria=:idCategoria")
	List<Tema> findTemasByCategoria(@Param("idCategoria") Integer idCategoria);
}