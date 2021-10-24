package Service;

import Data.DatosJPA;
import Domain.LoginDto;
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
        if(data.findById(cliente.getUsuarioId()).isEmpty()){

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
    public String eliminarCliente(LoginDto loginDto) {
        var consulta = data.findByUsuarioId(loginDto.getUsuarioId());
        if(consulta==null){
            return "false";
        }
        else {
            data.deleteById(loginDto.getUsuarioId());
            return "true";
        }
    }

    @Override
    public Cliente buscarCliente(int idCliente) {
        return data.findById(idCliente).orElse(null);
    }

    @Override
    public String login(LoginDto loginDto) {
        var user = data.findByUsuarioIdAndPassword(loginDto.getUsuarioId(), loginDto.getPassword());
        if (user==null){
            return "false";
        }
        else {
            return user.getRol();
        }
    }


}
