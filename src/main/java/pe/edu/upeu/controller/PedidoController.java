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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Pedido pedidoDto){
        if(StringUtils.isBlank(pedidoDto.getPedidoFecha()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        PedidoService.save(pedidoDto);
        return new ResponseEntity(new Mensaje("pedido creado"), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Pedido pedidoDto){
        if(!PedidoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(PedidoService.existsByNombre(pedidoDto.getPedidoFecha()) && PedidoService.getByNombre(pedidoDto.getPedidoFecha()).get().getPedidoId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(pedidoDto.getPedidoFecha()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);


        Pedido pedido = PedidoService.getOne(id).get();
        pedido.setPedidoFecha(pedidoDto.getPedidoFecha());
        PedidoService.save(pedido);
        return new ResponseEntity(new Mensaje("Pedido actualizado"), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!PedidoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        PedidoService.delete(id);
        return new ResponseEntity(new Mensaje("Pedido eliminado"), HttpStatus.OK);
    }


}
