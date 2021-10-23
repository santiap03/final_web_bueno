package Controller;

import Domain.LoginDto;
import Model.Cliente;
import Service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Controller
public class controlador {
    @Autowired
    DataService servicio;
    @Autowired

    @GetMapping(value="/clientes",produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cliente>> recuperarClientes() {
        return ResponseEntity.ok(servicio.recuperarClientes());
    }
    @ResponseBody
    @PostMapping(value="clientes/login",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.TEXT_PLAIN_VALUE)
    public String recuperarClientes(@RequestBody LoginDto loginDto) {
        return (servicio.login(loginDto));
    }
    @PostMapping(value="/clientes",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.TEXT_PLAIN_VALUE)
    public String guardarCliente(@RequestBody Cliente cliente) {
        return String.valueOf(servicio.agregarCliente(cliente));
    }

    @PutMapping(value="clientes/actualizar/",consumes=MediaType.APPLICATION_JSON_VALUE)
    public void actualizarContacto(@RequestBody Cliente contacto) {
        servicio.actualizarCliente(contacto);
    }

    @GetMapping(value="/test",produces= MediaType.APPLICATION_JSON_VALUE)
    public String test() {
        return "index_main";
    }


}
