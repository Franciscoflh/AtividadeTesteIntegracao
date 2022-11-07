package com.francisco.mock.service;

import com.francisco.mock.model.Aluguel;
import com.francisco.mock.model.Locacao;
import com.francisco.mock.repository.AluguelRepository;
import com.francisco.mock.repository.AluguelRepositoryImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Objects;

public class AluguelService {
    private final AluguelRepository aluguelRepository;

    public AluguelService(AluguelRepository aluguelRepository) {
        this.aluguelRepository = aluguelRepository;
    }

    public AluguelService() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("database");
        this.aluguelRepository = new AluguelRepositoryImpl(emf.createEntityManager());
    }

    public Aluguel buscarPorId(Integer idAluguel) {
        return aluguelRepository.getById(idAluguel);
    }

    public List<Aluguel> buscarAlugueisPorModelo(Aluguel aluguelBase) {
        return aluguelRepository.getAlugueisByCriterios(aluguelBase);
    }

    public Aluguel salvarNovoAluguel(Aluguel aluguel) {
        if (Objects.nonNull(aluguel.getAluguelId())) {
            throw new IllegalArgumentException("Há algum erro no aluguel");
        }

        verificarValorPagoAluguelValido(aluguel);
        return aluguelRepository.salvarAluguel(aluguel);
    }

    public boolean atualizarAluguel(Aluguel aluguel) {
        verificarValorPagoAluguelValido(aluguel);

        return aluguelRepository.atualizarAluguel(aluguel);
    }

    public boolean deletarAluguel(Aluguel aluguel) {
        return aluguelRepository.deletarAluguel(aluguel);
    }

    public Double calculaValorDeveSerPago() {
        return 0.0;
    }

    public void verificarValorPagoAluguelValido(Aluguel aluguel) {
        Locacao locacao = aluguelRepository.buscarLocacaoDoAluguel(aluguel);
        if (Objects.nonNull(locacao))
            if (locacao.getValorAluguel() > aluguel.getValorPago()) {
                throw new IllegalArgumentException("Valor pago inválido");
            }
    }
}
