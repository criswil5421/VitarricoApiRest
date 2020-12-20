package pe.edu.upeu.service;

import pe.edu.upeu.model.SalidaProduccion;
import pe.edu.upeu.dao.SalidaProduccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SalidaProduccionService {

    @Autowired
    SalidaProduccionRepository SalidaProduccionRepository;

    public List<SalidaProduccion> list(){
        return SalidaProduccionRepository.findAll();
    }

    public Optional<SalidaProduccion> getOne(int salproId){
        return SalidaProduccionRepository.findById(salproId);
    }

    public Optional<SalidaProduccion> getByNombre(String salidaproduccion){
        return SalidaProduccionRepository.findBySalproFecha(salidaproduccion);
    }

    public void  save(SalidaProduccion salidaproduccion){
        SalidaProduccionRepository.save(salidaproduccion);
    }

    public void delete(int salproId){
        SalidaProduccionRepository.deleteById(salproId);
    }

    public boolean existsById(int salproId){
        return SalidaProduccionRepository.existsById(salproId);
    }

    public boolean existsByNombre(String salidaproduccion){
        return SalidaProduccionRepository.existsBySalproFecha(salidaproduccion);
    }

}