package pe.edu.upeu.controller;

import pe.edu.upeu.dto.Mensaje;
import pe.edu.upeu.model.DetalleSalida;
import pe.edu.upeu.service.DetalleSalidaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.service.SalidaService;
import pe.edu.upeu.service.Producto1Service;

import java.util.List;

@RestController
@RequestMapping("/detallesalida")
@CrossOrigin(origins = "*")
public class DetalleSalidaController {

    @Autowired
    DetalleSalidaService DetalleSalidaService;

    @Autowired
    Producto1Service Producto1Service;


    @Autowired
    SalidaService SalidaService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista2")
    public ResponseEntity<List<DetalleSalida>> list2(){
        System.out.println("hola mundo");
        List<DetalleSalida> list = DetalleSalidaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<DetalleSalida>> list(){
        System.out.println("hola mundo");
        List<DetalleSalida> list = DetalleSalidaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<DetalleSalida> getById(@PathVariable("id") int id){
        if(!DetalleSalidaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        DetalleSalida detallesalida = DetalleSalidaService.getOne(id).get();
        return new ResponseEntity(detallesalida, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DetalleSalida detalleSalidaDto){
        if(Double.isNaN(detalleSalidaDto.getProductoId().getProductoId()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(Double.isNaN(detalleSalidaDto.getSalidaId().getSalidaId()))
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);

        DetalleSalidaService.save(detalleSalidaDto);
        return new ResponseEntity(new Mensaje("detalleEntrada creado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody DetalleSalida detalleSalidaDto){
        if(!DetalleSalidaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(Double.isNaN(detalleSalidaDto.getProductoId().getProductoId()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(Double.isNaN(detalleSalidaDto.getSalidaId().getSalidaId()))
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);

        DetalleSalida detalleSalida = DetalleSalidaService.getOne(id).get();
        detalleSalida.setProductoId(Producto1Service.getOne(detalleSalidaDto.getProductoId().getProductoId()).get());
        detalleSalida.setSalidaId(SalidaService.getOne(detalleSalidaDto.getSalidaId().getSalidaId()).get());
        DetalleSalidaService.save(detalleSalida);
        return new ResponseEntity(new Mensaje("DetalleSalida actualizado"), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!DetalleSalidaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        DetalleSalidaService.delete(id);
        return new ResponseEntity(new Mensaje("DetalleSalida eliminado"), HttpStatus.OK);
    }



}
