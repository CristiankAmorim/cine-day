package catalogo;

import model.Filme;
import model.enums.ClassificacaoEtaria;
import model.enums.Genero;
import model.enums.Idioma;
import service.CatalogoFilmesAPI;

import java.util.Arrays;
import java.util.List;

public class CatalogoMock implements CatalogoFilmesAPI {

    @Override
    public List<Filme> buscarTodos() {
        return Arrays.asList(
            new Filme("F01", "Duna: Parte Dois",         2024, 166, Arrays.asList(Genero.FICCAO_CIENTIFICA, Genero.DRAMA),   ClassificacaoEtaria.QUATORZE,  Idioma.INGLES,    92),
            new Filme("F02", "Ela (Her)",                2013, 126, Arrays.asList(Genero.FICCAO_CIENTIFICA, Genero.DRAMA, Genero.ROMANCE), ClassificacaoEtaria.DEZESSEIS, Idioma.INGLES, 78),
            new Filme("F03", "O Iluminado",              1980, 146, Arrays.asList(Genero.TERROR),                             ClassificacaoEtaria.DEZOITO,   Idioma.INGLES,    88),
            new Filme("F04", "Interestelar",             2014, 169, Arrays.asList(Genero.FICCAO_CIENTIFICA, Genero.DRAMA),   ClassificacaoEtaria.DOZE,      Idioma.INGLES,    95),
            new Filme("F05", "Tropa de Elite",           2007, 115, Arrays.asList(Genero.ACAO, Genero.DRAMA),                ClassificacaoEtaria.DEZOITO,   Idioma.PORTUGUES, 80),
            new Filme("F06", "Click",                    2006, 107, Arrays.asList(Genero.COMEDIA, Genero.DRAMA),             ClassificacaoEtaria.DOZE,      Idioma.INGLES,    65),
            new Filme("F07", "A Chegada",                2016, 116, Arrays.asList(Genero.FICCAO_CIENTIFICA, Genero.DRAMA),   ClassificacaoEtaria.DOZE,      Idioma.INGLES,    84),
            new Filme("F08", "Blade Runner 2049",        2017, 164, Arrays.asList(Genero.FICCAO_CIENTIFICA, Genero.DRAMA),   ClassificacaoEtaria.DEZESSEIS, Idioma.INGLES,    80),
            new Filme("F09", "Ex Machina",               2014, 108, Arrays.asList(Genero.FICCAO_CIENTIFICA, Genero.DRAMA),ClassificacaoEtaria.DEZESSEIS, Idioma.INGLES,    77),
            new Filme("F10", "Matrix",                   1999, 136, Arrays.asList(Genero.FICCAO_CIENTIFICA, Genero.ACAO),    ClassificacaoEtaria.QUATORZE,  Idioma.INGLES,    90),
            new Filme("F11", "Annihilation",             2018, 115, Arrays.asList(Genero.FICCAO_CIENTIFICA, Genero.TERROR),  ClassificacaoEtaria.DEZESSEIS, Idioma.INGLES,    72),
            new Filme("F12", "Parasita",                 2019, 132, Arrays.asList(Genero.DRAMA, Genero.DRAMA),            ClassificacaoEtaria.DEZESSEIS, Idioma.JAPONES,   93),
            new Filme("F13", "Joker",                    2019, 122, Arrays.asList(Genero.DRAMA, Genero.DRAMA),            ClassificacaoEtaria.DEZOITO,   Idioma.INGLES,    85),
            new Filme("F14", "Nomadland",                2020, 108, Arrays.asList(Genero.DRAMA),                             ClassificacaoEtaria.DOZE,      Idioma.INGLES,    74),
            new Filme("F15", "A Grande Aposta",          2015, 130, Arrays.asList(Genero.DRAMA, Genero.COMEDIA),             ClassificacaoEtaria.DEZESSEIS, Idioma.INGLES,    79),
            new Filme("F16", "Whiplash",                 2014, 107, Arrays.asList(Genero.DRAMA),                             ClassificacaoEtaria.QUATORZE,  Idioma.INGLES,    87),
            new Filme("F17", "Superbad",                 2007, 113, Arrays.asList(Genero.COMEDIA),                           ClassificacaoEtaria.DEZESSEIS, Idioma.INGLES,    76),
            new Filme("F18", "Corra!",                   2017, 104, Arrays.asList(Genero.TERROR, Genero.DRAMA),           ClassificacaoEtaria.DEZESSEIS, Idioma.INGLES,    83),
            new Filme("F19", "Midsommar",                2019, 148, Arrays.asList(Genero.TERROR),                            ClassificacaoEtaria.DEZOITO,   Idioma.INGLES,    71),
            new Filme("F20", "À Beira do Universo",      2017, 121, Arrays.asList(Genero.DRAMA, Genero.ROMANCE),             ClassificacaoEtaria.DOZE,      Idioma.INGLES,    68),
            new Filme("F21", "Amor Sublime Amor",        2021, 156, Arrays.asList(Genero.ROMANCE, Genero.DRAMA),             ClassificacaoEtaria.DOZE,      Idioma.INGLES,    75),
            new Filme("F22", "O Discurso do Rei",        2010, 118, Arrays.asList(Genero.DRAMA),                             ClassificacaoEtaria.DOZE,      Idioma.INGLES,    82),
            new Filme("F23", "Chef",                     2014, 115, Arrays.asList(Genero.COMEDIA, Genero.DRAMA),             ClassificacaoEtaria.DOZE,      Idioma.INGLES,    73),
            new Filme("F24", "Birdman",                  2014, 119, Arrays.asList(Genero.COMEDIA, Genero.DRAMA),             ClassificacaoEtaria.DEZESSEIS, Idioma.INGLES,    79),
            new Filme("F25", "A Origem",                 2010, 148, Arrays.asList(Genero.FICCAO_CIENTIFICA, Genero.ACAO),    ClassificacaoEtaria.DOZE,      Idioma.INGLES,    91),
            new Filme("F26", "Planeta dos Macacos",      2011, 105, Arrays.asList(Genero.FICCAO_CIENTIFICA, Genero.ACAO),    ClassificacaoEtaria.DOZE,      Idioma.INGLES,    77),
            new Filme("F27", "Alita: Anjo de Combate",  2019, 122, Arrays.asList(Genero.FICCAO_CIENTIFICA, Genero.ACAO),    ClassificacaoEtaria.QUATORZE,  Idioma.INGLES,    70),
            new Filme("F28", "O Hobbit",                 2012, 169, Arrays.asList(Genero.DRAMA, Genero.ACAO),             ClassificacaoEtaria.DOZE,      Idioma.INGLES,    78),
            new Filme("F29", "Guardiões da Galáxia",     2014, 121, Arrays.asList(Genero.ACAO, Genero.COMEDIA, Genero.ACAO), ClassificacaoEtaria.DOZE, Idioma.INGLES,   86),
            new Filme("F30", "Planeta Terra II",         2016, 110, Arrays.asList(Genero.DRAMA),                     ClassificacaoEtaria.LIVRE,     Idioma.INGLES,    89),
            new Filme("F31", "Divertida Mente",          2015,  95, Arrays.asList(Genero.DRAMA, Genero.COMEDIA),          ClassificacaoEtaria.LIVRE,     Idioma.INGLES,    88),
            new Filme("F32", "Coco",                     2017, 105, Arrays.asList(Genero.COMEDIA, Genero.DRAMA),            ClassificacaoEtaria.LIVRE,     Idioma.INGLES,    87)
        );
    }
}