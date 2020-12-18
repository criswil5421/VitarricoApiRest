package pe.edu.upeu.controller;

import pe.edu.upeu.dto.Mensaje;
import pe.edu.upeu.model.DetalleProduccion;
import pe.edu.upeu.service.DetalleProduccionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalleproduccion")
@CrossOrigin(origins = "*")
public class DetalleProduccionController {

    @Autowired
    DetalleProduccionService DetalleProduccionService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista2")
    public ResponseEntity<List<DetalleProduccion>> list2(){
        System.out.println("hola mundo");
        List<DetalleProduccion> list = DetalleProduccionService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<DetalleProduccion>> list(){
        System.out.println("hola mundo");
        List<DetalleProduccion> list = DetalleProduccionService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<DetalleProduccion> getById(@PathVariable("id") int id){
        if(!DetalleProduccionService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        DetalleProduccion detalleproduccion = DetalleProduccionService.getOne(id).get();
        return new ResponseEntity(detalleproduccion, HttpStatus.OK);
    }


    /*@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DetalleProduccion detalleProduccionDto){
        if(StringUtils.isBlank(detalleProduccionDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(Double.isNaN(detalleProduccionDto.getPrecio()) || detalleProduccionDto.getPrecio()<0)
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(DetalleProduccionService.existsByNombre(detalleProduccionDto.getNombre()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);

        DetalleProduccionService.save(detalleProduccionDto);
        return new ResponseEntity(new Mensaje("DetalleProduccion creado"), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody DetalleProduccion detalleProduccionDto){
        if(!DetalleProduccionService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(DetalleProduccionService.existsByNombre(detalleProduccionDto.getNombre()) && DetalleProduccionService.getByNombre(detalleProduccionDto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(detalleProduccionDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(Double.isNaN(detalleProduccionDto.getPrecio()) || detalleProduccionDto.getPrecio()<0 )
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);

        DetalleProduccion detalleProduccion = DetalleProduccionService.getOne(id).get();
        detalleProduccion.setNombre(detalleProduccionDto.getNombre());
        detalleProduccion.setPrecio(detalleProduccionDto.getPrecio());
        DetalleProduccionService.save(detalleProduccion);
        return new ResponseEntity(new Mensaje("DetalleProduccion actualizado"), HttpStatus.OK);
    }*/

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!DetalleProduccionService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        DetalleProduccionService.delete(id);
        return new ResponseEntity(new Mensaje("DetalleProduccion eliminado"), HttpStatus.OK);
    }


}
