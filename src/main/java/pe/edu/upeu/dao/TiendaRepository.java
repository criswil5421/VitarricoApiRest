package pe.edu.upeu.dao;

import pe.edu.upeu.model.Tienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import java.util.List;

@Repository
public interface TiendaRepository extends JpaRepository<Tienda, Integer> {
    Optional<Tienda> findByTiendaNombre(String nombre);
    boolean existsByTiendaNombre(String nombre);

    @Query("select p from Tienda p  where p.tiendaNombre=?1")
    List<Tienda> findByTiendaNombrelista(String nombre);
}