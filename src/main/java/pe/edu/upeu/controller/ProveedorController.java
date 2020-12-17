package pe.edu.upeu.controller;

import pe.edu.upeu.dto.Mensaje;
import pe.edu.upeu.model.Proveedor;
import pe.edu.upeu.service.ProveedorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedor")
@CrossOrigin(origins = "*")
public class ProveedorController {

    @Autowired
    ProveedorService ProveedorService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista2")
    public ResponseEntity<List<Proveedor>> list2(){
        System.out.println("hola mundo");
        List<Proveedor> list = ProveedorService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Proveedor>> list(){
        System.out.println("hola mundo");
        List<Proveedor> list = ProveedorService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Proveedor> getById(@PathVariable("id") int id){
        if(!ProveedorService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Proveedor proveedor = ProveedorService.getOne(id).get();
        return new ResponseEntity(proveedor, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Proveedor> getByNombre(@PathVariable("nombre") String nombre){
        if(!ProveedorService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Proveedor proveedor = ProveedorService.getByNombre(nombre).get();
        return new ResponseEntity(proveedor, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Proveedor proveedorDto){
        if(StringUtils.isBlank(proveedorDto.getProveedorNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(proveedorDto.getProveedorDireccion()))
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(proveedorDto.getProveedorCorreo()))
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(proveedorDto.getProveedorTelefono()))
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(ProveedorService.existsByNombre(proveedorDto.getProveedorNombre()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        //Producto producto = new Producto(productoDto.getNombre(), productoDto.getPrecio());
        //productoService.save(producto);
        ProveedorService.save(proveedorDto);
        return new ResponseEntity(new Mensaje("Proveedor creado"), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Proveedor proveedorDto){
        if(!ProveedorService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(ProveedorService.existsByNombre(proveedorDto.getProveedorNombre()) && ProveedorService.getByNombre(proveedorDto.getProveedorNombre()).get().getProveedorId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(proveedorDto.getProveedorNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(proveedorDto.getProveedorDireccion()))
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(proveedorDto.getProveedorCorreo()))
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(proveedorDto.getProveedorTelefono()))
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);


        Proveedor proveedor = ProveedorService.getOne(id).get();
        proveedor.setProveedorNombre(proveedorDto.getProveedorNombre());
        proveedor.setProveedorDireccion(proveedorDto.getProveedorDireccion());
        proveedor.setProveedorCorreo(proveedorDto.getProveedorCorreo());
        proveedor.setProveedorTelefono(proveedorDto.getProveedorTelefono());
        ProveedorService.save(proveedor);
        return new ResponseEntity(new Mensaje("Proveedor actualizado"), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!ProveedorService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        ProveedorService.delete(id);
        return new ResponseEntity(new Mensaje("Proveedor eliminado"), HttpStatus.OK);
    }


}
