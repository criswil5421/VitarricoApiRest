package pe.edu.upeu.dao;

import pe.edu.upeu.model.MateriaPrima;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import java.util.List;

@Repository
public interface MateriaPrimaRepository extends JpaRepository<MateriaPrima, Integer> {
    Optional<MateriaPrima> findByMateriaNombre(String nombre);
    boolean existsByMateriaNombre(String nombre);


    @Query("select p from MateriaPrima p  where p.materiaNombre=?1")
    List<MateriaPrima> findByMateriaNombrelista(String nombre);
}