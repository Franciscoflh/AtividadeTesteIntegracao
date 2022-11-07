package com.francisco.mock.service;

import com.francisco.mock.model.Imovel;
import com.francisco.mock.repository.ImovelRepository;
import com.francisco.mock.repository.ImovelRepositoryImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Objects;

public class ImovelService {
    private ImovelRepository repository;

    public ImovelService(ImovelRepository repository) {
        this.repository = repository;
    }

    public ImovelService() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("database");
        this.repository = new ImovelRepositoryImpl(emf.createEntityManager());
    }


    public Imovel buscarPorId(Integer idImovel){
        return repository.getById(idImovel);
    }

    public List<Imovel> buscaDisponiveisPorModelo(Imovel imovelBase){
        return repository.getImoveisDisponiveisByCaracteristicas(imovelBase);
    }

    public List<Imovel> buscaDisponiveisAbaixoValor(Double valorDesejado){
        return repository.getImoveisDisponiveisAbaixoDoValor(valorDesejado);
    }

    public Imovel salvarNovoImovel(Imovel imovel){
        if(Objects.nonNull(imovel.getIdImovel())){
            throw new IllegalArgumentException("Há algum erro com o imovel");
        }
        return repository.salvar(imovel);
    }

    public boolean atualizarImovel(Imovel imovel){
        return repository.atualizar(imovel);
    }

    public boolean removerImovel(Imovel imovel){
        return repository.deletar(imovel);
    }
}
