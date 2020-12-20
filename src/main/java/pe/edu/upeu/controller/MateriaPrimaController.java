package pe.edu.upeu.controller;

import pe.edu.upeu.dto.Mensaje;
import pe.edu.upeu.model.MateriaPrima;
import pe.edu.upeu.service.MateriaPrimaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.service.AlmacenService;

import java.util.List;

@RestController
@RequestMapping("/materiaprima")
@CrossOrigin(origins = "*")
public class MateriaPrimaController {

    @Autowired
    MateriaPrimaService MateriaPrimaService;

    @Autowired
    AlmacenService AlmacenService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista2")
    public ResponseEntity<List<MateriaPrima>> list2(){
        System.out.println("hola mundo");
        List<MateriaPrima> list = MateriaPrimaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<MateriaPrima>> list(){
        System.out.println("hola mundo");
        List<MateriaPrima> list = MateriaPrimaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<MateriaPrima> getById(@PathVariable("id") int id){
        if(!MateriaPrimaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        MateriaPrima materiaprima = MateriaPrimaService.getOne(id).get();
        return new ResponseEntity(materiaprima, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<MateriaPrima> getByNombre(@PathVariable("nombre") String nombre){
        if(!MateriaPrimaService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        MateriaPrima materiaPrima = MateriaPrimaService.getByNombre(nombre).get();
        return new ResponseEntity(materiaPrima, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody MateriaPrima materiaPrimaDto){
        if(StringUtils.isBlank(materiaPrimaDto.getMateriaNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(materiaPrimaDto.getMateriaCantidad()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(materiaPrimaDto.getMateriaIngreso()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(Double.isNaN(materiaPrimaDto.getAlmacenId().getAlmacenId()))
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(MateriaPrimaService.existsByNombre(materiaPrimaDto.getMateriaNombre()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);

        MateriaPrimaService.save(materiaPrimaDto);
        return new ResponseEntity(new Mensaje("materiaPrima creado"), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody MateriaPrima materiaPrimaDto){
        if(!MateriaPrimaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(MateriaPrimaService.existsByNombre(materiaPrimaDto.getMateriaNombre()) && MateriaPrimaService.getByNombre(materiaPrimaDto.getMateriaNombre()).get().getMateriaId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(materiaPrimaDto.getMateriaNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(materiaPrimaDto.getMateriaCantidad()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(materiaPrimaDto.getMateriaIngreso()))
                return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(Double.isNaN(materiaPrimaDto.getAlmacenId().getAlmacenId()))
                return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);

    MateriaPrima materiaPrima = MateriaPrimaService.getOne(id).get();
        materiaPrima.setMateriaNombre(materiaPrimaDto.getMateriaNombre());
        materiaPrima.setMateriaCantidad(materiaPrimaDto.getMateriaCantidad());
        materiaPrima.setMateriaIngreso(materiaPrimaDto.getMateriaIngreso());
        materiaPrima.setAlmacenId(AlmacenService.getOne(materiaPrimaDto.getAlmacenId().getAlmacenId()).get());
        MateriaPrimaService.save(materiaPrima);
        return new ResponseEntity(new Mensaje("MateriaPrima actualizado"), HttpStatus.OK);
}

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!MateriaPrimaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        MateriaPrimaService.delete(id);
        return new ResponseEntity(new Mensaje("MateriaPrima eliminado"), HttpStatus.OK);
    }


}
