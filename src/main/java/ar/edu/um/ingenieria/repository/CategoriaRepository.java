package ar.edu.um.ingenieria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ar.edu.um.ingenieria.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
	@Query("SELECT c FROM Categoria c WHERE c.nombre=:nombreC")
	Categoria findCategoriaByName(@Param("nombreC") String nombreC);
}
