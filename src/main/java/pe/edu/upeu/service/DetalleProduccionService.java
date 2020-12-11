package pe.edu.upeu.service;

import pe.edu.upeu.model.DetalleProduccion;
import pe.edu.upeu.dao.DetalleProduccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetalleProduccionService {

    @Autowired
    DetalleProduccionRepository DetalleProduccionRepository;

    public List<DetalleProduccion> list(){
        return DetalleProduccionRepository.findAll();
    }

    public Optional<DetalleProduccion> getOne(int detalleId){
        return DetalleProduccionRepository.findById(detalleId);
    }


    public void  save(DetalleProduccion detalleproduccion){
        DetalleProduccionRepository.save(detalleproduccion);
    }

    public void delete(int detalleId){
        DetalleProduccionRepository.deleteById(detalleId);
    }

    public boolean existsById(int detalleId){
        return DetalleProduccionRepository.existsById(detalleId);
    }

}