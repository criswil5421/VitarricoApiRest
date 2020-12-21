package pe.edu.upeu.dao;

import pe.edu.upeu.model.Almacen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;                                        
import java.util.List;


@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, Integer> {
    Optional<Almacen> findByAlmacenNombre(String nombre);
    boolean existsByAlmacenNombre(String nombre);

    @Query("select p from Almacen p  where p.almacenNombre=?1")
    List<Almacen> findByAlmacenNombrelista(String nombre);
}