package pe.edu.upeu.dao;

import pe.edu.upeu.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    Optional<Pedido> findByPedidoFecha(String nombre);
    boolean existsByPedidoFecha(String nombre);
}