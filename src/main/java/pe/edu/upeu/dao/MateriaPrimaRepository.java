package pe.edu.upeu.dao;

import pe.edu.upeu.model.MateriaPrima;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MateriaPrimaRepository extends JpaRepository<MateriaPrima, Integer> {
    Optional<MateriaPrima> findByMateriaNombre(String nombre);
    boolean existsByMateriaNombre(String nombre);
}