package pe.edu.upeu.controller;

import pe.edu.upeu.dto.Mensaje;
import pe.edu.upeu.model.Tienda;
import pe.edu.upeu.service.TiendaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tienda")
@CrossOrigin(origins = "*")
public class TiendaController {

    @Autowired
    TiendaService TiendaService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista2")
    public ResponseEntity<List<Tienda>> list2(){
        System.out.println("hola mundo");
        List<Tienda> list = TiendaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Tienda>> list(){
        System.out.println("hola mundo");
        List<Tienda> list = TiendaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Tienda> getById(@PathVariable("id") int id){
        if(!TiendaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Tienda tienda = TiendaService.getOne(id).get();
        return new ResponseEntity(tienda, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<List<Tienda>> getByNombreLista(@PathVariable("nombre") String nombre){
        //Proveedor proveedor = ProveedorService.getByNombre(nombre).get();
        List<Tienda> list = TiendaService.getByNombreLista(nombre);
        return new ResponseEntity(list, HttpStatus.OK);
        //return new ResponseEntity(proveedor, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Tienda almacenDto){
        if(StringUtils.isBlank(almacenDto.getTiendaNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(almacenDto.getTiendaDireccion()))
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(almacenDto.getTiendaTelefono()))
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(TiendaService.existsByNombre(almacenDto.getTiendaNombre()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        //Producto producto = new Producto(productoDto.getNombre(), productoDto.getPrecio());
        //productoService.save(producto);
        TiendaService.save(almacenDto);
        return new ResponseEntity(new Mensaje("producto creado"), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Tienda almacenDto){
        if(!TiendaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(TiendaService.existsByNombre(almacenDto.getTiendaNombre()) && TiendaService.getByNombre(almacenDto.getTiendaNombre()).get().getTiendaId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(almacenDto.getTiendaDireccion()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(almacenDto.getTiendaTelefono()))
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);

        Tienda tienda = TiendaService.getOne(id).get();
        tienda.setTiendaNombre(almacenDto.getTiendaNombre());
        tienda.setTiendaDireccion(almacenDto.getTiendaDireccion());
        tienda.setTiendaTelefono(almacenDto.getTiendaTelefono());
        TiendaService.save(tienda);
        return new ResponseEntity(new Mensaje("Tienda actualizado"), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!TiendaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        TiendaService.delete(id);
        return new ResponseEntity(new Mensaje("Tienda eliminado"), HttpStatus.OK);
    }


}
