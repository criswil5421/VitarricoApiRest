package pe.edu.upeu.service;

import pe.edu.upeu.model.Salida;
import pe.edu.upeu.dao.SalidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SalidaService {

    @Autowired
    SalidaRepository SalidaRepository;

    public List<Salida> list(){
        return SalidaRepository.findAll();
    }

    public Optional<Salida> getOne(int salidaId){
        return SalidaRepository.findById(salidaId);
    }

    public Optional<Salida> getByNombre(String salida){
        return SalidaRepository.findBySalidaFecha(salida);
    }

    public void  save(Salida salida){
        SalidaRepository.save(salida);
    }

    public void delete(int salidaId){
        SalidaRepository.deleteById(salidaId);
    }

    public boolean existsById(int salidaId){
        return SalidaRepository.existsById(salidaId);
    }

    public boolean existsByNombre(String salida){
        return SalidaRepository.existsBySalidaFecha(salida);
    }
}