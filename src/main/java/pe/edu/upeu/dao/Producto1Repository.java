package pe.edu.upeu.dao;

import pe.edu.upeu.model.Producto1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import java.util.List;

@Repository
public interface Producto1Repository extends JpaRepository<Producto1, Integer> {
    Optional<Producto1> findByProductoNombre(String nombre);
    boolean existsByProductoNombre(String nombre);

    @Query("select p from Producto1 p  where p.productoNombre=?1")
    List<Producto1> findByProductoNombrelista(String nombre);
}