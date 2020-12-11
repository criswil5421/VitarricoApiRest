package pe.edu.upeu.service;

import pe.edu.upeu.model.DetalleEntrada;
import pe.edu.upeu.dao.DetalleEntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetalleEntradaService {

    @Autowired
    DetalleEntradaRepository DetalleEntradaRepository;

    public List<DetalleEntrada> list(){
        return DetalleEntradaRepository.findAll();
    }

    public Optional<DetalleEntrada> getOne(int detalleId){
        return DetalleEntradaRepository.findById(detalleId);
    }


    public void  save(DetalleEntrada detalleentrada){
        DetalleEntradaRepository.save(detalleentrada);
    }

    public void delete(int detalleId){
        DetalleEntradaRepository.deleteById(detalleId);
    }

    public boolean existsById(int detalleId){
        return DetalleEntradaRepository.existsById(detalleId);
    }

}