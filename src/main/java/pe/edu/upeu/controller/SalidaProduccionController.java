package pe.edu.upeu.controller;

import pe.edu.upeu.dto.Mensaje;
import pe.edu.upeu.model.SalidaProduccion;
import pe.edu.upeu.service.SalidaProduccionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.service.Producto1Service;

import java.util.List;

@RestController
@RequestMapping("/salidaproduccion")
@CrossOrigin(origins = "*")
public class SalidaProduccionController {

    @Autowired
    SalidaProduccionService SalidaProduccionService;

    @Autowired
    Producto1Service Producto1Service;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista2")
    public ResponseEntity<List<SalidaProduccion>> list2(){
        System.out.println("hola mundo");
        List<SalidaProduccion> list = SalidaProduccionService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<SalidaProduccion>> list(){
        System.out.println("hola mundo");
        List<SalidaProduccion> list = SalidaProduccionService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<SalidaProduccion> getById(@PathVariable("id") int id){
        if(!SalidaProduccionService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        SalidaProduccion salidaproduccion = SalidaProduccionService.getOne(id).get();
        return new ResponseEntity(salidaproduccion, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody SalidaProduccion salidaProduccionDto){
        if(StringUtils.isBlank(salidaProduccionDto.getSalproFecha()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(Double.isNaN(salidaProduccionDto.getProductoId().getProductoId()))
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(SalidaProduccionService.existsByNombre(salidaProduccionDto.getSalproFecha()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);

        SalidaProduccionService.save(salidaProduccionDto);
        return new ResponseEntity(new Mensaje("SalidaProduccionService creado"), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody SalidaProduccion salidaProduccionDto){
        if(!SalidaProduccionService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(SalidaProduccionService.existsByNombre(salidaProduccionDto.getSalproFecha()) && SalidaProduccionService.getByNombre(salidaProduccionDto.getSalproFecha()).get().getSalproId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(salidaProduccionDto.getSalproFecha()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(Double.isNaN(salidaProduccionDto.getProductoId().getProductoId()))
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);

        SalidaProduccion salidaProduccion = SalidaProduccionService.getOne(id).get();
        salidaProduccion.setSalproFecha(salidaProduccionDto.getSalproFecha());
        salidaProduccion.setProductoId(Producto1Service.getOne(salidaProduccionDto.getProductoId().getProductoId()).get());
        SalidaProduccionService.save(salidaProduccion);
        return new ResponseEntity(new Mensaje("SalidaProduccion actualizado"), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!SalidaProduccionService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        SalidaProduccionService.delete(id);
        return new ResponseEntity(new Mensaje("salidaProduccionDto eliminado"), HttpStatus.OK);
    }


}
