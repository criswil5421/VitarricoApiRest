package pe.edu.upeu.service;

import java.util.List;
import java.util.Optional;
import pe.edu.upeu.model.ServPeriodo;

/**
 *
 * @author davidmp
 */
public interface ServPeriodoService {
    ServPeriodo save(ServPeriodo user);
    List<ServPeriodo> findAll();
    void delete(int idPeriodo);   
    
    public Optional<ServPeriodo> getOne(int idPeriodo);
    public Optional<ServPeriodo> getByPeriodo(String periodo);
    public boolean existsById(int idPeriodo);
    public boolean existsByPeriodo(String periodo);
}
