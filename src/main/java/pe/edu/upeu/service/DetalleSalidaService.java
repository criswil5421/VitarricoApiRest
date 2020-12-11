package pe.edu.upeu.service;

import pe.edu.upeu.model.DetalleSalida;
import pe.edu.upeu.dao.DetalleSalidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetalleSalidaService {

    @Autowired
    DetalleSalidaRepository DetalleSalidaRepository;

    public List<DetalleSalida> list(){
        return DetalleSalidaRepository.findAll();
    }

    public Optional<DetalleSalida> getOne(int detalleId){
        return DetalleSalidaRepository.findById(detalleId);
    }


    public void  save(DetalleSalida detallesalida){
        DetalleSalidaRepository.save(detallesalida);
    }

    public void delete(int detalleId){
        DetalleSalidaRepository.deleteById(detalleId);
    }

    public boolean existsById(int detalleId){
        return DetalleSalidaRepository.existsById(detalleId);
    }

}