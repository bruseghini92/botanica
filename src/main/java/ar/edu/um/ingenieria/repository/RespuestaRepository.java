package ar.edu.um.ingenieria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.edu.um.ingenieria.domain.Respuesta;



@Repository
public interface RespuestaRepository  extends JpaRepository<Respuesta, Integer> {
	@Query("SELECT r FROM Respuesta r WHERE r.tema=:idTema")
	List<Respuesta> findRespuestaByTema(@Param("idTema") Integer idTema);

}