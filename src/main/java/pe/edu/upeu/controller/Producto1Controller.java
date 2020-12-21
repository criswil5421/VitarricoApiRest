package pe.edu.upeu.controller;

import pe.edu.upeu.dto.Mensaje;
import pe.edu.upeu.model.Producto1;
import pe.edu.upeu.service.Producto1Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.service.AlmacenService;

import java.util.List;

@RestController
@RequestMapping("/pro1")
@CrossOrigin(origins = "*")
public class Producto1Controller {

    @Autowired
    Producto1Service Producto1Service;

    @Autowired
    AlmacenService AlmacenService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista2")
    public ResponseEntity<List<Producto1>> list2(){
        System.out.println("hola mundo");
        List<Producto1> list = Producto1Service.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Producto1>> list(){
        System.out.println("hola mundo");
        List<Producto1> list = Producto1Service.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Producto1> getById(@PathVariable("id") int id){
        if(!Producto1Service.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Producto1 producto1 = Producto1Service.getOne(id).get();
        return new ResponseEntity(producto1, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<List<Producto1>> getByNombreLista(@PathVariable("nombre") String nombre){
        //Proveedor proveedor = ProveedorService.getByNombre(nombre).get();
        List<Producto1> list = Producto1Service.getByNombreLista(nombre);
        return new ResponseEntity(list, HttpStatus.OK);
        //return new ResponseEntity(proveedor, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Producto1 producto1Dto){
        if(StringUtils.isBlank(producto1Dto.getProductoNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(Double.isNaN(producto1Dto.getProductoPrecio()))
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(producto1Dto.getProductoIngreso()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(producto1Dto.getProductoCantidad()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(producto1Dto.getProductoDescripcion()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(Double.isNaN(producto1Dto.getAlmacenId().getAlmacenId()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(Producto1Service.existsByNombre(producto1Dto.getProductoNombre()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);

        Producto1Service.save(producto1Dto);
        return new ResponseEntity(new Mensaje("Producto1 creado"), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Producto1 producto1Dto){
        if(!Producto1Service.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(Producto1Service.existsByNombre(producto1Dto.getProductoNombre()) && Producto1Service.getByNombre(producto1Dto.getProductoNombre()).get().getProductoId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(producto1Dto.getProductoNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(Double.isNaN(producto1Dto.getProductoPrecio()))
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(producto1Dto.getProductoIngreso()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(producto1Dto.getProductoCantidad()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(producto1Dto.getProductoDescripcion()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(Double.isNaN(producto1Dto.getAlmacenId().getAlmacenId()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);

        Producto1 producto1 = Producto1Service.getOne(id).get();
        producto1.setProductoNombre(producto1Dto.getProductoNombre());
        producto1.setProductoPrecio(producto1Dto.getProductoPrecio());
        producto1.setProductoIngreso(producto1Dto.getProductoIngreso());
        producto1.setProductoCantidad(producto1Dto.getProductoCantidad());
        producto1.setProductoDescripcion(producto1Dto.getProductoDescripcion());
        producto1.setAlmacenId(AlmacenService.getOne(producto1Dto.getAlmacenId().getAlmacenId()).get());
        Producto1Service.save(producto1);
        return new ResponseEntity(new Mensaje("Producto1 actualizado"), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!Producto1Service.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Producto1Service.delete(id);
        return new ResponseEntity(new Mensaje("Producto1 eliminado"), HttpStatus.OK);
    }


}
