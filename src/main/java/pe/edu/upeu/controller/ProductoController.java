package pe.edu.upeu.controller;

import pe.edu.upeu.dto.Mensaje;
import pe.edu.upeu.model.Producto;
import pe.edu.upeu.service.ProductoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    ProductoService productoService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista2")
    public ResponseEntity<List<Producto>> list2(){
        System.out.println("hola mundo");
        List<Producto> list = productoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Producto>> list(){
        System.out.println("hola mundo");
        List<Producto> list = productoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Producto> getById(@PathVariable("id") int id){
        if(!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Producto producto = productoService.getOne(id).get();
        return new ResponseEntity(producto, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Producto> getByNombre(@PathVariable("nombre") String nombre){
        if(!productoService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Producto producto = productoService.getByNombre(nombre).get();
        return new ResponseEntity(producto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Producto productoDto){
        if(StringUtils.isBlank(productoDto.getProductoNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(Double.isNaN(productoDto.getProductoPrecio()))
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(productoDto.getProductoIngreso()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(productoDto.getProductoCantidad()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(productoDto.getProductoDescripcion()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(Double.isNaN(productoDto.getAlmacenId()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(productoService.existsByNombre(productoDto.getProductoNombre()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);

            productoService.save(productoDto);
        return new ResponseEntity(new Mensaje("Producto1 creado"), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Producto productoDto){
        if(!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(productoService.existsByNombre(productoDto.getProductoNombre()) && productoService.getByNombre(productoDto.getProductoNombre()).get().getProductoId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(productoDto.getProductoNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(Double.isNaN(productoDto.getProductoPrecio()))
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(productoDto.getProductoIngreso()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(productoDto.getProductoCantidad()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(productoDto.getProductoDescripcion()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(Double.isNaN(productoDto.getAlmacenId()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);

        Producto producto = productoService.getOne(id).get();
        producto.setProductoNombre(productoDto.getProductoNombre());
        producto.setProductoPrecio(productoDto.getProductoPrecio());
        producto.setProductoIngreso(productoDto.getProductoIngreso());
        producto.setProductoCantidad(productoDto.getProductoCantidad());
        producto.setProductoDescripcion(productoDto.getProductoDescripcion());
        producto.setAlmacenId(productoDto.getAlmacenId());
        productoService.save(producto);
        return new ResponseEntity(new Mensaje("Producto1 actualizado"), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        productoService.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }


}
