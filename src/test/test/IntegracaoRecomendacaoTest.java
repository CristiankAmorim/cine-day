package test;

import catalogo.CatalogoMock;
import model.Recomendacao;
import model.Usuario;
import org.junit.jupiter.api.*;
import service.*;
import util.GeradorAleatorioImpl;
import util.UsuarioFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Tag("integracao")
@DisplayName("Pipeline Completo — catálogo real + filtro real + calculadora real")
class IntegracaoRecomendacaoTest {

    private RecomendadorService service;
    private HistoricoUsuarioRepository historicoMock;
    private NotificadorPush notificadorMock;
    private Usuario maria;

    @BeforeEach
    void setUp() {
        historicoMock  = mock(HistoricoUsuarioRepository.class);
        notificadorMock = mock(NotificadorPush.class);

        service = new RecomendadorService(
                new CatalogoMock(),
                historicoMock,
                notificadorMock,
                new GeradorAleatorioImpl(),
                new CalculadoraScore(),
                new FiltroFilmes()
        );
        maria = UsuarioFactory.maria();
    }

    @Test
    @DisplayName("deve_RetornarTop5Filmes_ComPipelineCompleto")
    void deve_RetornarTop5Filmes_ComPipelineCompleto() {
        List<Recomendacao> resultado = service.recomendar(maria, 5);

        assertFalse(resultado.isEmpty());
        assertTrue(resultado.size() <= 5);
        assertTrue(resultado.get(0).getScore() >= resultado.get(resultado.size() - 1).getScore());
    }

    @Test
    @DisplayName("deve_ExcluirFilmesJaAssistidos_NoPipelineCompleto")
    void deve_ExcluirFilmesJaAssistidos_NoPipelineCompleto() {
        List<Recomendacao> resultado = service.recomendar(maria, 10);

        resultado.forEach(rec -> assertFalse(
                List.of("F08", "F09").contains(rec.getFilme().getId()),
                "Filme já assistido não deveria estar na lista: " + rec.getFilme().getTitulo()
        ));
    }

    @Test
    @DisplayName("deve_ExcluirFilmesDeTerrror_QuandoMariaTemPesoBloqueado")
    void deve_ExcluirFilmesDeTerrror_QuandoMariaTemPesoBloqueado() {
        List<Recomendacao> resultado = service.recomendar(maria, 20);

        resultado.forEach(rec ->
                assertFalse(rec.getFilme().getGeneros().stream()
                        .anyMatch(g -> g.name().equals("TERROR")),
                        "Terror não deveria aparecer para Maria: " + rec.getFilme().getTitulo())
        );
    }

    @Test
    @DisplayName("deve_ChamarHistorico_NoPipelineCompleto")
    void deve_ChamarHistorico_NoPipelineCompleto() {
        service.recomendar(maria, 5);
        verify(historicoMock, times(1)).registrarRecomendacao(eq(maria), anyList());
    }

    @Test
    @DisplayName("deve_ChamarNotificador_QuandoMariaTemPushHabilitado")
    void deve_ChamarNotificador_QuandoMariaTemPushHabilitado() {
        service.recomendar(maria, 5);
        verify(notificadorMock, times(1)).enviarNotificacao(eq(maria), anyList());
    }
}