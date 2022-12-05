package com.francisco.mock.repository;

import com.francisco.mock.model.Cliente;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteRepositoryImpl implements ClienteRepository{

    private final EntityManager manager;

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
    public List<Cliente> getClienteAluguelAtrasado() {

        return manager.createQuery("from Cliente i where exists (select 0 from Locacao l, Aluguel a where l.idCliente = i.idCliente and l.idLocacao = a.idLocacao and a.dataVencimento < CURRENT_TIMESTAMP()"+
                        "and a.dataPagamento is null)", Cliente.class)
                .getResultList();
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
