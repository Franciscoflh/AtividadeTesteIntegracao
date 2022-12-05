package com.francisco.mock.service;

import com.francisco.mock.model.Cliente;
import com.francisco.mock.model.EmailApi;
import com.francisco.mock.repository.ClienteRepository;
import com.francisco.mock.repository.ClienteRepositoryImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Objects;

public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteService(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("database");
        this.clienteRepository = new ClienteRepositoryImpl(emf.createEntityManager());
    }

    public Cliente salvarNovoCliente(Cliente cliente){
        if(Objects.nonNull(cliente.getIdClient())){
            throw new IllegalArgumentException("Houve algum problema em salvar o cliente");
        }

        return clienteRepository.salvar(cliente);
    }

    public Cliente buscarPorId(Integer idCliente){
        return clienteRepository.getById(idCliente);
    }

    public Cliente buscarPorCpf(String cpf){
        return clienteRepository.getClienteByCpf(cpf);
    }

    public boolean atualizarCliente(Cliente cliente){
        return clienteRepository.atualizar(cliente);
    }

    public boolean removerCliente(Cliente cliente){
        return clienteRepository.deletar(cliente);
    }

    public void notificarAtrasados(){
        List<Cliente> atrasados = clienteRepository.getClienteAluguelAtrasado();
        atrasados.forEach(atrasado -> {
            try{
                EmailApi.sendEmail(atrasado.getEmail(), "Seu aluguel esta atrasado.");
            }catch (Exception e){
                System.out.println("Error ao enviar email para " + atrasado.getEmail());
            }
        });

    }

}
