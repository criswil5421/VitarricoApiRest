package pe.edu.upeu.service;

import pe.edu.upeu.model.Tienda;
import pe.edu.upeu.dao.TiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TiendaService {

    @Autowired
    TiendaRepository TiendaRepository;

    public List<Tienda> list(){
        return TiendaRepository.findAll();
    }

    public Optional<Tienda> getOne(int tiendaId){
        return TiendaRepository.findById(tiendaId);
    }


    public Optional<Tienda> getByNombre(String tiendaNombre){
        return TiendaRepository.findByTiendaNombre(tiendaNombre);
    }

    public void  save(Tienda tienda){
        TiendaRepository.save(tienda);
    }

    public void delete(int tiendaId){
        TiendaRepository.deleteById(tiendaId);
    }

    public boolean existsById(int tiendaId){
        return TiendaRepository.existsById(tiendaId);
    }

    public boolean existsByNombre(String tiendaNombre){
        return TiendaRepository.existsByTiendaNombre(tiendaNombre);
    }
}