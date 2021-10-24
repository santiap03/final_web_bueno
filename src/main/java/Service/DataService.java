package Service;

import Domain.LoginDto;
import Model.Cliente;

import java.util.List;

public interface DataService {
    List<Cliente> recuperarClientes();
    boolean agregarCliente(Cliente cliente);
    void actualizarCliente(Cliente cliente);
    String eliminarCliente(LoginDto loginDto);
    Cliente buscarCliente(int idCliente);
    String login(LoginDto loginDto);

}
