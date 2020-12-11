package pe.edu.upeu.service;

import pe.edu.upeu.model.Pedido;
import pe.edu.upeu.dao.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PedidoService {

    @Autowired
    PedidoRepository PedidoRepository;

    public List<Pedido> list(){
        return PedidoRepository.findAll();
    }

    public Optional<Pedido> getOne(int pedidoId){
        return PedidoRepository.findById(pedidoId);
    }


    public void  save(Pedido pedido){
        PedidoRepository.save(pedido);
    }

    public void delete(int pedidoId){
        PedidoRepository.deleteById(pedidoId);
    }

    public boolean existsById(int pedidoId){
        return PedidoRepository.existsById(pedidoId);
    }

}