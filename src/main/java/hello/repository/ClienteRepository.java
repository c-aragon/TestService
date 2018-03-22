package hello.repository;

import hello.domain.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    List<Cliente> findByName(String name);
}
