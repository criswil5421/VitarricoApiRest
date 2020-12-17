package pe.edu.upeu.controller;

import pe.edu.upeu.dto.Mensaje;
import pe.edu.upeu.model.DetalleEntrada;
import pe.edu.upeu.service.DetalleEntradaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalleentrada")
@CrossOrigin(origins = "*")
public class DetalleEntradaController {

    @Autowired
    DetalleEntradaService DetalleEntradaService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista2")
    public ResponseEntity<List<DetalleEntrada>> list2(){
        System.out.println("hola mundo");
        List<DetalleEntrada> list = DetalleEntradaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<DetalleEntrada>> list(){
        System.out.println("hola mundo");
        List<DetalleEntrada> list = DetalleEntradaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<DetalleEntrada> getById(@PathVariable("id") int id){
        if(!DetalleEntradaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        DetalleEntrada detalleentrada = DetalleEntradaService.getOne(id).get();
        return new ResponseEntity(detalleentrada, HttpStatus.OK);
    }


    /*@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DetalleEntrada detalleEntradaDto){
        if(Integer.isInt(detalleEntradaDto.getMateriaId()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(Integer.isInt(detalleEntradaDto.getPedidoId()))
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        //Producto producto = new Producto(productoDto.getNombre(), productoDto.getPrecio());
        //productoService.save(producto);
        DetalleEntradaService.save(detalleEntradaDto);
        return new ResponseEntity(new Mensaje("detalleEntrada creado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody DetalleEntrada detalleEntradaDto){
        if(!DetalleEntradaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        /*if(DetalleEntradaService.existsByNombre(detalleEntradaDto.getNombre()) && DetalleEntradaService.getByNombre(detalleEntradaDto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(Integer.isInt(detalleEntradaDto.getMateriaId()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(Integer.isInt(detalleEntradaDto.getPedidoId()))
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);

        DetalleEntrada detalleEntrada = DetalleEntradaService.getOne(id).get();
        DetalleEntrada.setMateriaId(detalleEntradaDto.getMateriaId());
        DetalleEntrada.setPedidoId(detalleEntradaDto.getPedidoId());
        DetalleEntradaService.save(detalleEntrada);
        return new ResponseEntity(new Mensaje("detalleEntrada actualizado"), HttpStatus.OK);
    }*/

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!DetalleEntradaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        DetalleEntradaService.delete(id);
        return new ResponseEntity(new Mensaje("detalleEntrada eliminado"), HttpStatus.OK);
    }


}
