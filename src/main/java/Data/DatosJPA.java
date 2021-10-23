package Data;

import Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DatosJPA extends JpaRepository<Cliente, Integer> {
    Cliente findByUsuarioIdAndPassword(int usuarioId, String password);
}
