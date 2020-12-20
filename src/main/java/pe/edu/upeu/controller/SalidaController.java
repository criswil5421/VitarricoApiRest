package pe.edu.upeu.controller;

import pe.edu.upeu.dto.Mensaje;
import pe.edu.upeu.model.Salida;
import pe.edu.upeu.service.SalidaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.service.TiendaService;


import java.util.List;

@RestController
@RequestMapping("/salida")
@CrossOrigin(origins = "*")
public class SalidaController {

    @Autowired
    SalidaService SalidaService;

    @Autowired
    TiendaService TiendaService;



    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista2")
    public ResponseEntity<List<Salida>> list2(){
        System.out.println("hola mundo");
        List<Salida> list = SalidaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Salida>> list(){
        System.out.println("hola mundo");
        List<Salida> list = SalidaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Salida> getById(@PathVariable("id") int id){
        if(!SalidaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Salida salida = SalidaService.getOne(id).get();
        return new ResponseEntity(salida, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Salida> getByNombre(@PathVariable("nombre") String nombre){
        if(!SalidaService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Salida salida = SalidaService.getByNombre(nombre).get();
        return new ResponseEntity(salida, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Salida salidaDto){
        if(StringUtils.isBlank(salidaDto.getSalidaFecha()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(Double.isNaN(salidaDto.getTiendaId().getTiendaId()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(SalidaService.existsByNombre(salidaDto.getSalidaFecha()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        SalidaService.save(salidaDto);
        return new ResponseEntity(new Mensaje("Salida creado"), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Salida salidaDto){
        if(!SalidaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(SalidaService.existsByNombre(salidaDto.getSalidaFecha()) && SalidaService.getByNombre(salidaDto.getSalidaFecha()).get().getSalidaId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(salidaDto.getSalidaFecha()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(Double.isNaN(salidaDto.getTiendaId().getTiendaId()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);


        Salida salida = SalidaService.getOne(id).get();
        salida.setSalidaFecha(salidaDto.getSalidaFecha());
        salida.setTiendaId(TiendaService.getOne(salidaDto.getTiendaId().getTiendaId()).get());
        SalidaService.save(salida);
        return new ResponseEntity(new Mensaje("Salida actualizado"), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!SalidaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        SalidaService.delete(id);
        return new ResponseEntity(new Mensaje("Salida eliminado"), HttpStatus.OK);
    }


}
