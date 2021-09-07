package Service;

import Data.DatosJPA;
import Model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl implements DataService{
    @Autowired
    DatosJPA data;
    @Override
    public List<Cliente> recuperarClientes() {
        return data.findAll();
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        if(data.findById(cliente.getIdCliente()).isEmpty()){

            data.save(cliente);

            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void actualizarCliente(Cliente cliente) {
        data.save(cliente);
    }

    @Override
    public boolean eliminarCliente(int idCliente) {
        if(data.findById(idCliente).isEmpty()){
            return false;
        }
        else {
            data.deleteById(idCliente);
            return true;
        }
    }

    @Override
    public Cliente buscarCliente(int idCliente) {
        return data.findById(idCliente).orElse(null);
    }

    @Override
    public List<Cliente> ClientesMayores(int edad) {
        return data.GetByEdad(edad);

    }
}
