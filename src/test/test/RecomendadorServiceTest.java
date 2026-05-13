package test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import model.Filme;
import model.Recomendacao;
import model.Usuario;
import model.enums.ClassificacaoEtaria;
import model.enums.Genero;
import model.enums.Idioma;
import service.CalculadoraScore;
import service.CatalogoFilmesAPI;
import service.FiltroFilmes;
import service.HistoricoUsuarioRepository;
import service.NotificadorPush;
import service.RecomendadorService;
import util.GeradorAleatorio;
import util.UsuarioFactory;

@Tag("unitario")
@ExtendWith(MockitoExtension.class)
@DisplayName("Orquestração e integração com mocks")
public class RecomendadorServiceTest {

	@Mock private CatalogoFilmesAPI catalogoAPI;
	@Mock private HistoricoUsuarioRepository historicoRepository;
	@Mock private NotificadorPush notificadorPush;
	@Mock private GeradorAleatorio geradorAleatorio;
	
	@Spy private CalculadoraScore calculadoraScore = new CalculadoraScore();
	
	private FiltroFilmes filtroFilmes;
	private RecomendadorService recomendador;
	private Usuario maria;
	
	private Filme filmeAltoScore;
    private Filme filmeMedioScore;
    private Filme filmeBaixoScore;
	
	@BeforeEach
	void setUp() {
		filtroFilmes = new FiltroFilmes();
		recomendador = new RecomendadorService(catalogoAPI, historicoRepository, notificadorPush,
				geradorAleatorio, calculadoraScore, filtroFilmes);
		maria = UsuarioFactory.maria();
		filmeAltoScore = new Filme("F01", "Alto Score", 2024, 120, Arrays.asList(Genero.FICCAO_CIENTIFICA), ClassificacaoEtaria.DOZE, Idioma.INGLES, 90);
        filmeMedioScore = new Filme("F02", "Medio Score", 2024, 110, Arrays.asList(Genero.FICCAO_CIENTIFICA), ClassificacaoEtaria.DOZE, Idioma.INGLES, 70);
        filmeBaixoScore = new Filme("F01", "Alto Score", 2024, 110, Arrays.asList(Genero.COMEDIA), ClassificacaoEtaria.DOZE, Idioma.INGLES, 50);
		
	}
	
	   @Test
	    @DisplayName("deve_RetornarTopN_Quando_Catalogo_Tem_Filmes_Suficientes")
	    void deve_Retornar_TopN_Quando_Catalogo_Tem_Filmes_Suficientes() {
	        when(catalogoAPI.buscarTodos())
	                .thenReturn(Arrays.asList(filmeAltoScore, filmeMedioScore, filmeBaixoScore));
	 
	        List<Recomendacao> resultado = recomendador.recomendar(maria, 2);
	 
	        assertEquals(2, resultado.size());
	    }
	   
	   @Test
	    @DisplayName("deve_Ordenar_Por_Score_Desc_Quando_Recomendacao_Tem_Multiplos_Filmes")
	    void deve_Ordenar_Por_Score_Desc_Quando_Recomendacao_TemMultiplos_Filmes() {
	        when(catalogoAPI.buscarTodos())
	                .thenReturn(Arrays.asList(filmeBaixoScore, filmeAltoScore, filmeMedioScore));
	 
	        List<Recomendacao> resultado = recomendador.recomendar(maria, 3);
	 
	        assertEquals(3, resultado.size());
	        assertTrue(resultado.get(0).getScore() >= resultado.get(1).getScore());
	        assertTrue(resultado.get(1).getScore() >= resultado.get(2).getScore());
	    }
	   
	   @Test
	    @DisplayName("deve_Desempatar_Por_Popularidade_Quando_Scores_Iguais")
	    void deve_Desempatar_Por_Popularidade_Quando_Scores_Iguais() {
	        Filme maisPopular  = new Filme ("F01", "Mais popular", 2024, 120, Arrays.asList(Genero.FICCAO_CIENTIFICA), ClassificacaoEtaria.DOZE, Idioma.INGLES, 95);
	        Filme menosPopular = new Filme("F02", "Menos popular", 2024, 120, Arrays.asList(Genero.FICCAO_CIENTIFICA), ClassificacaoEtaria.DOZE, Idioma.INGLES, 60);
	 
	        when(catalogoAPI.buscarTodos()).thenReturn(Arrays.asList(menosPopular, maisPopular));
	 
	        List<Recomendacao> resultado = recomendador.recomendar(maria, 2);
	 
	        assertEquals(2, resultado.size());
	        assertTrue(resultado.get(0).getFilme().getPopularidade() >= resultado.get(1).getFilme().getPopularidade());
	    }
	   
	   @Test
	    @DisplayName("deve_Retornar_ListaVazia_Quando_Catalogo_Vazio")
	    void deve_Retornar_Lista_Vazia_Quando_Catalogo_Vazio() {
	        when(catalogoAPI.buscarTodos()).thenReturn(Collections.emptyList());
	 
	        List<Recomendacao> resultado = recomendador.recomendar(maria, 5);
	 
	        assertNotNull(resultado);
	        assertTrue(resultado.isEmpty());
	    }
	   
	   @Test
	    @DisplayName("deve_Nao_Derrubar_Quando_Catalogo_Lanca_Excecao")
	    void deve_Nao_Derrubar_Quando_Catalogo_Lanca_Excecao() {
	        when(catalogoAPI.buscarTodos()).thenThrow(new RuntimeException("API offline"));
	 
	        assertDoesNotThrow(() -> {
	            List<Recomendacao> resultado = recomendador.recomendar(maria, 5);
	            assertTrue(resultado.isEmpty());
	        });
	 
	        verify(notificadorPush, never()).enviarNotificacao(any(), any());
	    }
	   
	   @Test
	    @DisplayName("deve_Registrar_Recomendacoes_Corretas_Usando_Argument_Captor")
	    void deve_Registrar_Recomendacoes_Corretas_Usando_Argument_Captor() {
	        when(catalogoAPI.buscarTodos())
	                .thenReturn(Arrays.asList(filmeAltoScore, filmeMedioScore, filmeBaixoScore));
	 
	        recomendador.recomendar(maria, 3);
	 
	        ArgumentCaptor<List<Recomendacao>> captor = ArgumentCaptor.forClass(List.class);
	        verify(historicoRepository).registrarRecomendacao(eq(maria), captor.capture());
	 
	        List<Recomendacao> registradas = captor.getValue();
	        assertAll(
	                () -> assertEquals(3, registradas.size()),
	                () -> assertNotNull(registradas.get(0).getJustificativa()),
	                () -> assertTrue(registradas.get(0).getScore() >= registradas.get(2).getScore())
	        );
	    }
	   
	   @Test
	    @DisplayName("deve_Chamar_Notificador_Quando_Push_Esta_Habilitado")
	    void deve_Chamar_Notificador_Quando_Push_Esta_Habilitado() {
	        when(catalogoAPI.buscarTodos()).thenReturn(Arrays.asList(filmeAltoScore));
	 
	        recomendador.recomendar(maria, 1);
	 
	        verify(notificadorPush, times(1)).enviarNotificacao(eq(maria), anyList());
	    }
	
	   @Test
	    @DisplayName("deve_Nao_Chamar_Notificador_Quando_Push_Esta_Desabilitado")
	    void deve_Nao_Chamar_Notificador_Quando_Push_EstaDesabilitado() {
	        Usuario joao = UsuarioFactory.joao();
	        when(catalogoAPI.buscarTodos()).thenReturn(Arrays.asList(filmeAltoScore));
	 
	        recomendador.recomendar(joao, 1);
	 
	        verify(notificadorPush, never()).enviarNotificacao(any(), any());
	    }
	   
	   @Test
	    @DisplayName("deve_Retornar_Um_Filme_Quando_Modo_SurpreendaMe")
	    void deve_Retornar_UmFilme_Quando_Modo_SurpreendaMe() {
	        when(catalogoAPI.buscarTodos()).thenReturn(Arrays.asList(filmeAltoScore, filmeMedioScore));
	        when(geradorAleatorio.sortearNumeroInteiro(0, 1)).thenReturn(0);
	 
	        Optional<Recomendacao> resultado = recomendador.recomendarFilmeAleatorio(maria);
	 
	        assertTrue(resultado.isPresent());
	        assertNotNull(resultado.get().getFilme());
	    }
}
