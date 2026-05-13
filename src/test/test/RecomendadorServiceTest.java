package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import model.Usuario;
import service.CalculadoraScore;
import service.CatalogoFilmesAPI;
import service.FiltroFilmes;
import service.HistoricoUsuarioRepository;
import service.NotificadorPush;
import service.RecomendadorService;
import util.GeradorAleatorio;

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
	private Usuario usuario;
	
	@BeforeEach
	void setUp() {
		filtroFilmes = new FiltroFilmes();
		recomendador = new RecomendadorService(catalogoAPI, historicoRepository, notificadorPush,
				geradorAleatorio, calculadoraScore, filtroFilmes);
	}
	
}
