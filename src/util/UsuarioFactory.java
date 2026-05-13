package util;

import model.Filme;
import model.PerfilCinefilo;
import model.Usuario;
import model.enums.ClassificacaoEtaria;
import model.enums.Genero;
import model.enums.Idioma;

import java.util.Arrays;

public class UsuarioFactory {

    private UsuarioFactory() {}

    public static final Filme BLADE_RUNNER_2049 = new Filme(
            "F08", "Blade Runner 2049", 2017, 164,
            Arrays.asList(Genero.FICCAO_CIENTIFICA, Genero.DRAMA),
            ClassificacaoEtaria.DEZESSEIS, Idioma.INGLES, 80);

    public static final Filme EX_MACHINA = new Filme(
            "F09", "Ex Machina", 2014, 108,
            Arrays.asList(Genero.FICCAO_CIENTIFICA, Genero.DRAMA),
            ClassificacaoEtaria.DEZESSEIS, Idioma.INGLES, 77);

    public static Usuario maria() {
        PerfilCinefilo perfil = new PerfilCinefilo();
        perfil.inserirPesoDoGenero(Genero.FICCAO_CIENTIFICA, 0.9);
        perfil.inserirPesoDoGenero(Genero.DRAMA, 0.6);
        perfil.inserirPesoDoGenero(Genero.COMEDIA, 0.5);
        perfil.inserirPesoDoGenero(Genero.TERROR, 0.0);
        perfil.inserirPesoDoGenero(Genero.ROMANCE, 0.4);
        perfil.inserirFaixaDeDuracao(90, 150);
        perfil.setClassificacaoMaxima(ClassificacaoEtaria.DEZESSEIS);
        perfil.adicinarIdioma(Idioma.PORTUGUES);
        perfil.adicinarIdioma(Idioma.INGLES);
        perfil.marcarFilmeComoAssistido(EX_MACHINA);
        perfil.atribuirNotaAoFilme(BLADE_RUNNER_2049, 5);
        perfil.atribuirNotaAoFilme(EX_MACHINA, 2);

        return new Usuario(1, "Maria", 28, perfil, true);
    }

    public static Usuario joao() {
        PerfilCinefilo perfil = new PerfilCinefilo();
        perfil.inserirPesoDoGenero(Genero.ACAO, 0.9);
        perfil.inserirPesoDoGenero(Genero.COMEDIA, 0.8);
        perfil.inserirPesoDoGenero(Genero.DRAMA, 0.3);
        perfil.inserirPesoDoGenero(Genero.TERROR, 0.1);
        perfil.inserirFaixaDeDuracao(80, 130);
        perfil.setClassificacaoMaxima(ClassificacaoEtaria.DEZESSEIS);
        perfil.adicinarIdioma(Idioma.INGLES);
        perfil.adicinarIdioma(Idioma.PORTUGUES);

        return new Usuario(2, "João", 22, perfil, false);
    }
}