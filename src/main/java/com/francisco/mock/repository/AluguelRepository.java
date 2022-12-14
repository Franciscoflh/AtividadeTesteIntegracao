package com.francisco.mock.repository;

import com.francisco.mock.model.Aluguel;
import com.francisco.mock.model.Locacao;

import java.util.List;

public interface AluguelRepository {

    public Aluguel getById(Integer idAluguel);

    public Locacao buscarLocacaoDoAluguel(Aluguel aluguel);

    public List<Aluguel> getAlugueisByCriterios(Aluguel aluguelBase);

    public List<Aluguel> getAlugueisPagos();

    public List<Aluguel> getAlugueisPagosNaDataVencimento();

    public Aluguel salvarAluguel(Aluguel aluguel);

    public boolean atualizarAluguel(Aluguel aluguel);

    public boolean deletarAluguel(Aluguel aluguel);

}
