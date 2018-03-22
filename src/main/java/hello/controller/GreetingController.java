package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
