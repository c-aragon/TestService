package hello.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import hello.bean.Greeting;
import hello.domain.Cliente;
import hello.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private ClienteRepository clienteRepository;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

    @GetMapping(path="/add") // Map ONLY GET Requests
    public Cliente addNewCliente (@RequestParam(value="name") String name,
                                  @RequestParam(value="email") String email) {

        Cliente cliente = new Cliente();
        cliente.setEmail(email);
        cliente.setName(name);

        clienteRepository.save(cliente);

        return cliente;
    }

    @GetMapping(path="/list")
    public Iterable<Cliente> listaClientes () {
        Iterable<Cliente> clientes = clienteRepository.findAll();
        return clientes;
    }

    @PostMapping(path="/findAllByName")
    public List<Cliente> findByName (@RequestParam(value="name") String name) {
        List<Cliente> clientes = clienteRepository.findByName(name);
        return clientes;
    }
}
