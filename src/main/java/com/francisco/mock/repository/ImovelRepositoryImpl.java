package com.francisco.mock.repository;

import com.francisco.mock.model.Imovel;

import javax.persistence.EntityManager;
import java.util.List;

public class ImovelRepositoryImpl implements ImovelRepository{

    private EntityManager manager;

    public ImovelRepositoryImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Imovel getById(Integer idImovel) {
        return null;
    }

    @Override
    public List<Imovel> getImoveisDisponiveisByCaracteristicas(Imovel imovelBase) {
        return null;
    }

    @Override
    public List<Imovel> getImoveisDisponiveisAbaixoDoValor(Double valorSugerido) {
        return null;
    }

    @Override
    public Imovel salvar(Imovel imovel) {
        return null;
    }

    @Override
    public boolean atualizar(Imovel imovel) {
        return false;
    }

    @Override
    public boolean deletar(Imovel imovel) {
        return false;
    }
}
