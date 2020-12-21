package pe.edu.upeu.dao;

import pe.edu.upeu.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
    Optional<Proveedor> findByProveedorNombre(String nombre);
    boolean existsByProveedorNombre(String nombre);
    
    @Query("select p from Proveedor p  where p.proveedorNombre=?1")
    List<Proveedor> findByProveedorNombrelista(String nombre);
}