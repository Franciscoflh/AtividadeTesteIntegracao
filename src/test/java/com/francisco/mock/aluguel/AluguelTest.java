package com.francisco.mock.aluguel;

import com.francisco.mock.model.Aluguel;
import com.francisco.mock.model.Locacao;
import com.francisco.mock.repository.AluguelRepositoryImpl;
import com.francisco.mock.service.AluguelService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(SpringRunner.class)
public class AluguelTest {
    private EntityManager manager;
    private static EntityManagerFactory emf;
    private AluguelRepositoryImpl aluguelRepository;
    private AluguelService aluguelServico;

    public Aluguel getAluguelParaTest(){
        Aluguel aluguel = new Aluguel();
        aluguel.setLocacaoId(20);
        aluguel.setValorPago(1200.0);
        aluguel.setDataVencimento(LocalDateTime.now());
        aluguel.setDataPagamento(LocalDateTime.now().minusDays(2));
        return aluguel;
    }

    public Locacao getLocacaoDoAluguelParaTest(){
        Locacao locacao = new Locacao();
        locacao.setValorAluguel(2000.0);
        return locacao;
    }

    public Aluguel getNovoAluguelParaTest(){
        Aluguel aluguel = new Aluguel();
        aluguel.setLocacaoId(1);
        aluguel.setValorPago(1200.0);
        aluguel.setDataVencimento(LocalDateTime.now());
        aluguel.setDataPagamento(LocalDateTime.now().minusDays(2));
        return aluguel;
    }

    public Aluguel getAluguelNoBancoParaTest(){
        Aluguel aluguel = new Aluguel();
        aluguel.setAluguelId(11);
        aluguel.setLocacaoId(1);
        aluguel.setValorPago(1200.0);
        aluguel.setDataVencimento(LocalDateTime.now());
        aluguel.setDataPagamento(LocalDateTime.now().minusDays(2));
        return aluguel;
    }

    public Aluguel getAluguelPagamentoInvalidoParaTest(){
        Aluguel aluguel = new Aluguel();
        aluguel.setAluguelId(1);
        aluguel.setLocacaoId(11);
        aluguel.setValorPago(800.0);
        aluguel.setDataVencimento(LocalDateTime.now());
        aluguel.setDataPagamento(LocalDateTime.now().minusDays(2));
        return aluguel;
    }


    @Before
    public void begin(){
        emf = Persistence.createEntityManagerFactory("databaseTest");
        manager = emf.createEntityManager();
        manager.getTransaction().begin();

        aluguelRepository = new AluguelRepositoryImpl(manager);
        aluguelServico = new AluguelService(aluguelRepository);
    }

    @After
    public void end() {
        manager.getTransaction().rollback();
        emf.close();
    }

    @Test
    public void componentesTestNaoEstaoNull(){
        assertThat(aluguelRepository).isNotNull();
        assertThat(aluguelServico).isNotNull();
    }

    @Test
    public void aluguelCreatTest(){

        Aluguel salvarAluguel = aluguelServico.salvarNovoAluguel(getNovoAluguelParaTest());
        assertNotNull(salvarAluguel.getAluguelId());
        Aluguel salvarAluguelRepository = aluguelRepository.salvarAluguel(getNovoAluguelParaTest());
        assertNotNull(salvarAluguelRepository.getAluguelId());


        Aluguel aluguelJaSalvo = getAluguelNoBancoParaTest();
        Throwable thrown = catchThrowable(() -> aluguelServico.salvarNovoAluguel(aluguelJaSalvo));

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Há algum erro no aluguel");

    }

    @Test
    public void aluguelReadTest(){
        assertTrue(Objects.isNull(aluguelRepository.getById(123)));
        assertFalse(Objects.isNull(aluguelRepository.getById(11)));

        Aluguel aluguelResultadoServico = aluguelServico.buscarPorId(11);
        assertTrue(Objects.nonNull(aluguelResultadoServico));
    }

    @Test
    public void aluguelUpdateTest(){

        Aluguel aluguel = aluguelServico.salvarNovoAluguel(getAluguelParaTest());
        assertNotNull(aluguel.getAluguelId());

        aluguel.setValorPago(1300.0);
        boolean resultadoServico = aluguelServico.atualizarAluguel(aluguel);
        assertTrue(resultadoServico);

        aluguel.setValorPago(500.0);
        Throwable thrown = catchThrowable(() -> aluguelServico.atualizarAluguel(aluguel));

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Valor pago inválido");
    }

    @Test
    public void aluguelDeleteTest(){
        Aluguel aluguel = getAluguelNoBancoParaTest();
        boolean resultadoServico = aluguelServico.deletarAluguel(aluguel);

        assertTrue(resultadoServico);
    }

    @Test
    public void verificaPagamentoValidoATV03(){
        Aluguel aluguel = getAluguelParaTest();

        boolean resultadoServico = aluguelServico.atualizarAluguel(aluguel);
        assertTrue(resultadoServico);
    }

    @Test
    public void verificaPagamentoInvalidoATV03(){
        Aluguel aluguel = getAluguelPagamentoInvalidoParaTest();
        Throwable thrown = catchThrowable(() -> aluguelServico.atualizarAluguel(aluguel));

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Valor pago inválido");
    }

    public void verificaPagamentoInvalido(){
        Aluguel aluguel = getAluguelParaTest();
        Locacao locacaoDoAluguel = getLocacaoDoAluguelParaTest();
        when(aluguelRepository.buscarLocacaoDoAluguel(aluguel)).thenReturn(locacaoDoAluguel);

        Throwable thrown = catchThrowable(() -> aluguelServico.atualizarAluguel(aluguel));

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("O valor não é o suficiente para pagar o aluguel.");
    }

}
