package pe.edu.upeu.controller;

import pe.edu.upeu.dto.Mensaje;
import pe.edu.upeu.model.Almacen;
import pe.edu.upeu.service.AlmacenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/almacen")
@CrossOrigin(origins = "*")
public class AlmacenController {

    @Autowired
    AlmacenService AlmacenService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista2")
    public ResponseEntity<List<Almacen>> list2(){
        System.out.println("hola mundo");
        List<Almacen> list = AlmacenService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Almacen>> list(){
        System.out.println("hola mundo");
        List<Almacen> list = AlmacenService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Almacen> getById(@PathVariable("id") int id){
        if(!AlmacenService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Almacen almacen = AlmacenService.getOne(id).get();
        return new ResponseEntity(almacen, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<List<Almacen>> getByNombreLista(@PathVariable("nombre") String nombre){
        //Proveedor proveedor = ProveedorService.getByNombre(nombre).get();
        List<Almacen> list = AlmacenService.getByNombreLista(nombre);
        return new ResponseEntity(list, HttpStatus.OK);
        //return new ResponseEntity(proveedor, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Almacen almacenDto){
        if(StringUtils.isBlank(almacenDto.getAlmacenNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(almacenDto.getAlmacenDireccion()))
            return new ResponseEntity(new Mensaje("la direccion ya esta registrada"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(almacenDto.getAlmacenTelefono()))
            return new ResponseEntity(new Mensaje("el telefono ya esta registrado"), HttpStatus.BAD_REQUEST);
        if(AlmacenService.existsByNombre(almacenDto.getAlmacenNombre()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        AlmacenService.save(almacenDto);
        return new ResponseEntity(new Mensaje("almacen creado"), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Almacen almacenDto){
        if(!AlmacenService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(AlmacenService.existsByNombre(almacenDto.getAlmacenNombre()) && AlmacenService.getByNombre(almacenDto.getAlmacenNombre()).get().getAlmacenId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(almacenDto.getAlmacenNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(almacenDto.getAlmacenDireccion()))
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(almacenDto.getAlmacenTelefono()))
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);

        Almacen almacen = AlmacenService.getOne(id).get();
        almacen.setAlmacenNombre(almacenDto.getAlmacenNombre());
        almacen.setAlmacenDireccion(almacenDto.getAlmacenDireccion());
        almacen.setAlmacenTelefono(almacenDto.getAlmacenTelefono());
        //System.out.println(almacen.toString);
        AlmacenService.save(almacen);
        return new ResponseEntity(new Mensaje("Almacen actualizado"), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!AlmacenService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        AlmacenService.delete(id);
        return new ResponseEntity(new Mensaje("Almacen eliminado"), HttpStatus.OK);
    }


}
