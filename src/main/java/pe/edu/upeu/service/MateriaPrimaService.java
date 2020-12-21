package pe.edu.upeu.service;

import pe.edu.upeu.model.MateriaPrima;
import pe.edu.upeu.dao.MateriaPrimaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MateriaPrimaService {

    @Autowired
    MateriaPrimaRepository MateriaPrimaRepository;

    public List<MateriaPrima> list(){
        return MateriaPrimaRepository.findAll();
    }

    public Optional<MateriaPrima> getOne(int materiaId){
        return MateriaPrimaRepository.findById(materiaId);
    }

    public Optional<MateriaPrima> getByNombre(String materiaPrima){
        return MateriaPrimaRepository.findByMateriaNombre(materiaPrima);
    }

    public List<MateriaPrima> getByNombreLista(String materiaNombre){
        return MateriaPrimaRepository.findByMateriaNombrelista(materiaNombre);
    }

    public void  save(MateriaPrima materiaprima){
        MateriaPrimaRepository.save(materiaprima);
    }

    public void delete(int materiaId){
        MateriaPrimaRepository.deleteById(materiaId);
    }

    public boolean existsById(int materiaId){
        return MateriaPrimaRepository.existsById(materiaId);
    }
    public boolean existsByNombre(String materiaPrima){
        return MateriaPrimaRepository.existsByMateriaNombre(materiaPrima);
    }

}