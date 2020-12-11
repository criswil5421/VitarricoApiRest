package pe.edu.upeu.dao;

        import pe.edu.upeu.model.Almacen;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

        import java.util.Optional;

@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, Integer> {
    Optional<Almacen> findByAlmacenNombre(String nombre);
    boolean existsByAlmacenNombre(String nombre);
}