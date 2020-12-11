package pe.edu.upeu.dao;

import pe.edu.upeu.model.Producto1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Producto1Repository extends JpaRepository<Producto1, Integer> {
    /*Optional<Almacen> findByAlmacenNombre(String nombre);
    boolean existsByAlmacenNombre(String nombre);*/
}