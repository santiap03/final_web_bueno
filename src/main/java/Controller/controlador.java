package Controller;

import Model.Cliente;
import Model.Photo;
import Service.DataService;
import Service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class controlador {
    @Autowired
    DataService servicio;
    @Autowired
    PhotoService photoService;
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
    @GetMapping(value="/clientes/mayores/{age}",produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Cliente> recuperarClientesMayores(@PathVariable("age") int edad) {
        return servicio.ClientesMayores(edad);
    }
    @PutMapping(value="clientes/actualizar/",consumes=MediaType.APPLICATION_JSON_VALUE)
    public void actualizarContacto(@RequestBody Cliente contacto) {
        servicio.actualizarCliente(contacto);
    }


    //Images MongoDB

    @PostMapping("/photos/add")
    public String addPhoto(@RequestParam("title") int title,
                           @RequestParam("image") MultipartFile image, Model model)
            throws IOException {
        String id = photoService.addPhoto(title, image);
        return "redirect:/photos/" + id;
    }
    @GetMapping("/photos/{Mongoid}")//imagen by MongoId
    public String getPhoto(@PathVariable String Mongoid) {
        Photo photo = photoService.getPhoto(Mongoid);
        return Base64.getEncoder().encodeToString(photo.getImage().getData());
    }
    @GetMapping("/photo/{id}")
    public String getPhotoid(@PathVariable int id) {
        Photo photo = photoService.getPhotoByTitle(id);
        System.out.println("hola");
        return  Base64.getEncoder().encodeToString(photo.getImage().getData());

    }


}
