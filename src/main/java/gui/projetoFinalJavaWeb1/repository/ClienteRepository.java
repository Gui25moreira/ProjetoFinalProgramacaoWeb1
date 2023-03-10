package gui.projetoFinalJavaWeb1.repository;

import gui.projetoFinalJavaWeb1.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpfContaining(String cpf);
}
