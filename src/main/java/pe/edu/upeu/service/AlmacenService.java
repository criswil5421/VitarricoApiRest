package pe.edu.upeu.service;

import pe.edu.upeu.model.Almacen;
import pe.edu.upeu.dao.AlmacenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AlmacenService {

    @Autowired
    AlmacenRepository AlmacenRepository;

    public List<Almacen> list(){
        return AlmacenRepository.findAll();
    }

    public Optional<Almacen> getOne(int almacenId){
        return AlmacenRepository.findById(almacenId);
    }

    public Optional<Almacen> getByNombre(String almacenNombre){
        return AlmacenRepository.findByAlmacenNombre(almacenNombre);
    }

    public void  save(Almacen almacen){
        AlmacenRepository.save(almacen);
    }

    public void delete(int almacenId){
        AlmacenRepository.deleteById(almacenId);
    }

    public boolean existsById(int almacenId){
        return AlmacenRepository.existsById(almacenId);
    }

    public boolean existsByNombre(String almacenNombre){
        return AlmacenRepository.existsByAlmacenNombre(almacenNombre);
    }
}