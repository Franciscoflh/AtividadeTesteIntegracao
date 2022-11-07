package com.francisco.mock.repository;

import com.francisco.mock.model.Aluguel;
import com.francisco.mock.model.Locacao;

import javax.persistence.EntityManager;
import java.util.List;

public class AluguelRepositoryImpl implements AluguelRepository{

    private EntityManager manager;

    public AluguelRepositoryImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Aluguel getById(Integer idAluguel) {
        return null;
    }

    @Override
    public Locacao buscarLocacaoDoAluguel(Aluguel aluguel) {
        return null;
    }

    @Override
    public List<Aluguel> getAlugueisByCriterios(Aluguel aluguelBase) {
        return null;
    }

    @Override
    public List<Aluguel> getAlugueisPagos() {
        return null;
    }

    @Override
    public List<Aluguel> getAlugueisPagosNaDataVencimento() {
        return null;
    }

    @Override
    public Aluguel salvarAluguel(Aluguel aluguel) {
        return null;
    }

    @Override
    public boolean atualizarAluguel(Aluguel aluguel) {
        return false;
    }

    @Override
    public boolean deletarAluguel(Aluguel aluguel) {
        return false;
    }
}
