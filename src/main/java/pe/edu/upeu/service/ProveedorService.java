package pe.edu.upeu.service;

import pe.edu.upeu.model.Proveedor;
import pe.edu.upeu.dao.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProveedorService {

    @Autowired
    ProveedorRepository ProveedorRepository;

    public List<Proveedor> list(){
        return ProveedorRepository.findAll();
    }

    public Optional<Proveedor> getOne(int proveedorId){
        return ProveedorRepository.findById(proveedorId);
    }

    public Optional<Proveedor> getByNombre(String proveedorNombre){
        return ProveedorRepository.findByProveedorNombre(proveedorNombre);
    }

    public void  save(Proveedor proveedor){
        ProveedorRepository.save(proveedor);
    }

    public void delete(int proveedorId){
        ProveedorRepository.deleteById(proveedorId);
    }

    public boolean existsById(int proveedorId){
        return ProveedorRepository.existsById(proveedorId);
    }

    public boolean existsByProveedorNombre(String almacenNombre){
        return ProveedorRepository.existsByProveedorNombre(almacenNombre);
    }
}