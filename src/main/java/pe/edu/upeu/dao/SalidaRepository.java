package pe.edu.upeu.dao;

import pe.edu.upeu.model.Salida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;                                        
import java.util.List;

import java.util.Optional;

@Repository
public interface SalidaRepository extends JpaRepository<Salida, Integer> {
    Optional<Salida> findBySalidaFecha(String nombre);
    boolean existsBySalidaFecha(String nombre);

}