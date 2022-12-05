package com.francisco.mock.repository;

import com.francisco.mock.model.Cliente;

import java.util.List;

public interface ClienteRepository {
    Cliente getById(Integer idCliente);

    Cliente getClienteByCpf(String cpf);

    List<Cliente> getClienteAluguelAtrasado();

    Cliente salvar(Cliente cliente);

    boolean atualizar(Cliente cliente);

    boolean deletar(Cliente cliente);
}
