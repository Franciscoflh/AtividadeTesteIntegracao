package com.francisco.mock.imovel;

import com.francisco.mock.model.Imovel;
import com.francisco.mock.repository.ImovelRepositoryImpl;
import com.francisco.mock.service.ImovelService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImovelTest {
    private ImovelService imovelService;
    private EntityManager manager;
    private static EntityManagerFactory emf;

    public Imovel getImovelNovoTest(){
        Imovel imovel = new Imovel();
        imovel.setTipoImovel("Casa");
        imovel.setBairro("Vila do Pato");
        imovel.setCep(21312123);

        return imovel;
    }

    public Imovel getImovelExistenteTest(){
        Imovel imovel = new Imovel();
        imovel.setIdImovel(11);
        imovel.setTipoImovel("Casa");
        imovel.setBairro("Vila do Pato");
        imovel.setCep(21312123);
        imovel.setValorAluguelSugerido(1100.0);
        return imovel;
    }

    public Imovel getImovelExistenteSemLocacaoTest(){
        Imovel imovel = new Imovel();
        imovel.setIdImovel(12);
        imovel.setTipoImovel("Casa");
        imovel.setBairro("Vila do Pato");
        imovel.setCep(21312123);
        imovel.setValorAluguelSugerido(12000.0);
        return imovel;
    }

    @Before
    public void inicio(){
        emf = Persistence.createEntityManagerFactory("databaseTest");
        manager = emf.createEntityManager();
        manager.getTransaction().begin();

        ImovelRepositoryImpl imovelRepository = new ImovelRepositoryImpl(manager);
        imovelService = new ImovelService(imovelRepository);
    }

    @After
    public void fim() {
        manager.getTransaction().rollback();
        emf.close();
    }

    @Test
    public void imovelCreatTest(){

        Imovel imovel = imovelService.salvarNovoImovel(getImovelNovoTest());
        assertNotNull(imovel.getIdImovel());

        Imovel imovelJaExistente = getImovelExistenteTest();
        Throwable thrown = catchThrowable(() -> imovelService.salvarNovoImovel(imovelJaExistente));

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("O imovel informado possui o identificador, considere utilizar o metodo de atualizar ou remova o identificador para salvar como um novo.");
    }

    @Test
    public void imovelReadTest(){
        Imovel imovelNovo = getImovelNovoTest();
        Imovel imovelJaExistente = getImovelExistenteTest();

        assertTrue(Objects.isNull(imovelService.buscarPorId(imovelNovo.getIdImovel())));
        assertTrue(Objects.nonNull(imovelService.buscarPorId(imovelJaExistente.getIdImovel())));

        Imovel imovelDisponivel = getImovelExistenteSemLocacaoTest();
        List<Imovel> imoveis = imovelService.buscaDisponiveisAbaixoValor(imovelDisponivel.getValorAluguelSugerido());
        assertFalse(imoveis.isEmpty());
    }

    @Test
    public void imovelUpdateTest(){
        Imovel imovelNovo = imovelService.salvarNovoImovel(getImovelNovoTest());
        assertNotNull(imovelNovo.getIdImovel());

        imovelNovo.setTipoImovel("Puxadinho");
        boolean retornoService = imovelService.atualizarImovel(imovelNovo);
        assertTrue(retornoService);

        Imovel imovelAtualizado = imovelService.buscarPorId(imovelNovo.getIdImovel());
        assertEquals(imovelAtualizado.getTipoImovel(), "Puxadinho");

    }

    @Test
    public void imovelDeleteTest(){
        Imovel imovel = getImovelExistenteTest();
        boolean retornoService = imovelService.removerImovel(imovel);

        assertTrue(retornoService);
        assertNull(imovelService.buscarPorId(imovel.getIdImovel()));
    }

    @Test
    public void buscaEmBairroPorTipoEstandoDisponivelTest(){
        Imovel imovelModelo = new Imovel();
        imovelModelo.setTipoImovel("Apartamento");
        imovelModelo.setBairro("bairro 2");
        List<Imovel> retornoService = imovelService.buscaDisponiveisPorModelo(imovelModelo);

        assertFalse(retornoService.isEmpty());
    }

    @Test
    public void buscaLimiteDeValorTest(){
        Double valorLimite = 10000.0;
        List<Imovel> retornoService = imovelService.buscaDisponiveisAbaixoValor(valorLimite);

        assertFalse(retornoService.isEmpty());
    }
}
