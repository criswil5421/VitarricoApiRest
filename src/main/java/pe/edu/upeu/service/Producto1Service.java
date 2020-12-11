package pe.edu.upeu.service;

import pe.edu.upeu.model.Producto1;
import pe.edu.upeu.dao.Producto1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class Producto1Service {

    @Autowired
    Producto1Repository Producto1Repository;

    public List<Producto1> list(){
        return Producto1Repository.findAll();
    }

    public Optional<Producto1> getOne(int productoId){
        return Producto1Repository.findById(productoId);
    }


    public void  save(Producto1 producto1){
        Producto1Repository.save(producto1);
    }

    public void delete(int productoId){
        Producto1Repository.deleteById(productoId);
    }

    public boolean existsById(int productoId){
        return Producto1Repository.existsById(productoId);
    }

}