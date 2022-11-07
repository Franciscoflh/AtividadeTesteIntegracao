package com.francisco.mock.repository;

import com.francisco.mock.model.Locacao;

import javax.persistence.EntityManager;

public class LocacaoRepositoryImpl implements LocacaoRepository{

    private EntityManager manager;

    public LocacaoRepositoryImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Locacao getById(Integer idLocacao) {
        return null;
    }

    @Override
    public Locacao salvar(Locacao locacao) {
        return null;
    }

    @Override
    public boolean atualizar(Locacao locacao) {
        return false;
    }

    @Override
    public boolean deletar(Locacao locacao) {
        return false;
    }
}
