package pe.edu.upeu.dao;

import pe.edu.upeu.model.DetalleProduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetalleProduccionRepository extends JpaRepository<DetalleProduccion, Integer> {
    /*Optional<Almacen> findByAlmacenNombre(String nombre);
    boolean existsByAlmacenNombre(String nombre);*/
}