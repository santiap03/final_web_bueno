package Controller;

import Model.Cliente;
import Service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class controlador {
    @Autowired
    DataService servicio;
    @GetMapping(value="/clientes",produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Cliente> recuperarClientes() {
        return servicio.recuperarClientes();
    }
    @GetMapping(value="clientes/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
    public Cliente recuperarClientes(@PathVariable("id") int id) {
        return servicio.buscarCliente(id);
    }
    @PostMapping(value="/clientes",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.TEXT_PLAIN_VALUE)
    public String guardarCliente(@RequestBody Cliente cliente) {
        return String.valueOf(servicio.agregarCliente(cliente));
    }
    @GetMapping(value="/clientes/mayores/{id}",produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Cliente> recuperarClientesMayores(@PathVariable("id") int edad) {
        return servicio.ClientesMayores(edad);
    }
    @PutMapping(value="contactos",consumes=MediaType.APPLICATION_JSON_VALUE)
    public void actualizarContacto(@RequestBody Cliente contacto) {
        servicio.actualizarCliente(contacto);
    }
}
