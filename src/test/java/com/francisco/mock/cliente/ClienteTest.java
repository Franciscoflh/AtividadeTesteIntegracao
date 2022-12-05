package com.francisco.mock.cliente;

import com.francisco.mock.model.Cliente;
import com.francisco.mock.repository.ClienteRepositoryImpl;
import com.francisco.mock.service.ClienteService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteTest {
    @Mock
    private ClienteRepositoryImpl clienteRepository;

    @InjectMocks
    private ClienteService clienteServico;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    public List<Cliente> getClientesAluguelAtrasado(){
        Cliente cliente = new Cliente();
        cliente.setEmail("inventado@email.com");
        Cliente cliente2 = new Cliente();
        cliente2.setEmail("inventado2@email.com");

        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente);
        clientes.add(cliente2);
        return clientes;
    }

    public List<Cliente> getClientesAluguelAtrasadoErro(){
        Cliente cliente = new Cliente();
        cliente.setEmail("invalido.email.com");
        Cliente cliente2 = new Cliente();
        cliente2.setEmail("valido@email.com");

        List<Cliente> clientes = getClientesAluguelAtrasado();
        clientes.add(cliente);
        clientes.add(cliente2);
        return clientes;

    }

    @Test
    public void verificarEnvioEmail(){
        List<Cliente> clientesValido = getClientesAluguelAtrasado();

        when(clienteRepository.getClienteAluguelAtrasado()).thenReturn(clientesValido);
        clienteServico.notificarAtrasados();
        verify(IllegalArgumentException.class, times(0));
    }

    @Test
    public void verificarEnvioEmailInvalido(){
        List<Cliente> clientesInvalido = getClientesAluguelAtrasadoErro();

        when(clienteRepository.getClienteAluguelAtrasado()).thenReturn(clientesInvalido);
        clienteServico.notificarAtrasados();
        verify(IllegalArgumentException.class, times(1));
    }
}
