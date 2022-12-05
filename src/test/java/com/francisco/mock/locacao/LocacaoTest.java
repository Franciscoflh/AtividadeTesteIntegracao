package com.francisco.mock.locacao;

import com.francisco.mock.model.Locacao;
import com.francisco.mock.repository.LocacaoRepository;
import com.francisco.mock.service.LocacaoService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocacaoTest {

    @Mock
    private LocacaoRepository locacaoRepository;

    @InjectMocks
    private LocacaoService locacaoServico;

    public Locacao getLocacaoTest(){
        Locacao locacao = new Locacao();
        locacao.setDataInicio(LocalDateTime.now());
        locacao.setDataFim(LocalDateTime.now().plusDays(30));
        locacao.setValorAluguel(1200.0);
        return locacao;
    }

    public Locacao getLocacaoAtrasadaTest(){
        Locacao locacao = new Locacao();
        locacao.setDataInicio(LocalDateTime.now().minusDays(65));
        locacao.setDataFim(LocalDateTime.now().minusDays(30));
        locacao.setValorAluguel(1400.0);
        return locacao;
    }

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void verificaCalculoValorAluguelSemMulta(){
        Locacao locacao = getLocacaoTest();
        when(locacaoRepository.getById(11)).thenReturn(locacao);

        double valor = locacaoServico.calculaValorDeveSerPago(11);
        assertEquals(1200.0, valor);
    }

    @Test
    public void verificaCalculoValorAluguelComMulta(){
        Locacao locacao = getLocacaoAtrasadaTest();
        when(locacaoRepository.getById(11)).thenReturn(locacao);

        double valor = locacaoServico.calculaValorDeveSerPago(11);
        assertTrue(valor>1300.0);
    }

}
