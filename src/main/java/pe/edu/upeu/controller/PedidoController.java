package pe.edu.upeu.controller;

import pe.edu.upeu.dto.Mensaje;
import pe.edu.upeu.model.Pedido;
import pe.edu.upeu.service.PedidoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    PedidoService PedidoService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista2")
    public ResponseEntity<List<Pedido>> list2(){
        System.out.println("hola mundo");
        List<Pedido> list = PedidoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Pedido>> list(){
        System.out.println("hola mundo");
        List<Pedido> list = PedidoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Pedido> getById(@PathVariable("id") int id){
        if(!PedidoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Pedido pedido = PedidoService.getOne(id).get();
        return new ResponseEntity(pedido, HttpStatus.OK);
    }

    /*@GetMapping("/detailname/{nombre}")
    public ResponseEntity<Producto> getByNombre(@PathVariable("nombre") String nombre){
        if(!productoService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Producto producto = productoService.getByNombre(nombre).get();
        return new ResponseEntity(producto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Producto productoDto){
        if(StringUtils.isBlank(productoDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(Double.isNaN(productoDto.getPrecio()) || productoDto.getPrecio()<0)
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(productoService.existsByNombre(productoDto.getNombre()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        //Producto producto = new Producto(productoDto.getNombre(), productoDto.getPrecio());
        //productoService.save(producto);
        productoService.save(productoDto);
        return new ResponseEntity(new Mensaje("producto creado"), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Producto productoDto){
        if(!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(productoService.existsByNombre(productoDto.getNombre()) && productoService.getByNombre(productoDto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(productoDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(Double.isNaN(productoDto.getPrecio()) || productoDto.getPrecio()<0 )
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);

        Producto producto = productoService.getOne(id).get();
        producto.setNombre(productoDto.getNombre());
        producto.setPrecio(productoDto.getPrecio());
        productoService.save(producto);
        return new ResponseEntity(new Mensaje("producto actualizado"), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        productoService.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }*/


}
