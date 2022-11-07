package com.francisco.mock.repository;

import com.francisco.mock.model.Cliente;

public interface ClienteRepository {
    public Cliente getById(Integer idCliente);

    public Cliente getClienteByCpf(String cpf);

    public Cliente salvar(Cliente cliente);

    public boolean atualizar(Cliente cliente);

    public boolean deletar(Cliente cliente);
}
