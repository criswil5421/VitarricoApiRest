package pe.edu.upeu.dao;

import pe.edu.upeu.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Optional<Producto> findByProductoNombre(String nombre);
    boolean existsByProductoNombre(String nombre);

    @Query("select p from Producto p  where p.productoNombre=?1")
    List<Producto> findByNombrelista(String nombre);
}
