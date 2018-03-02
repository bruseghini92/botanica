package ar.edu.um.ingenieria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import ar.edu.um.ingenieria.domain.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{
	@Query("SELECT r FROM Rol r WHERE r.rol != 'ROLE_ADMIN'")
	List<Rol> findWithOutAdmin();
}
