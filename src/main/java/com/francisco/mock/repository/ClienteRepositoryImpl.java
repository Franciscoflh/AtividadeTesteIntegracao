package com.francisco.mock.repository;

import com.francisco.mock.model.Cliente;

import javax.persistence.EntityManager;

public class ClienteRepositoryImpl implements ClienteRepository{

    private EntityManager manager;

    public ClienteRepositoryImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Cliente getById(Integer idCliente) {
        return null;
    }

    @Override
    public Cliente getClienteByCpf(String cpf) {
        return null;
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        return null;
    }

    @Override
    public boolean atualizar(Cliente cliente) {
        return false;
    }

    @Override
    public boolean deletar(Cliente cliente) {
        return false;
    }
}
