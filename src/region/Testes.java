package region;

public class Testes {
    private static int aprovados = 0;
    private static int reprovados = 0;

    public static void main(String[] args) {
        System.out.println("===== TESTES BOLONHA E MILANO - POKEMON EDITION =====");
        
        testarCidade();
        testarMercado();
        testarAgricultura();
        testarGoverno();
        testarGinasios();
        testarJogadorENobreza();
        testarPartida();

        System.out.println("Aprovados: " + aprovados + " | Reprovados: " + reprovados);
        if (reprovados > 0) {
            System.exit(1);
        }
    }

    private static void verificar(String descricao, boolean condicao) {
        if (condicao) {
            aprovados++;
            System.out.println("[OK] " + descricao);
        } else {
            reprovados++;
            System.out.println("[FALHA] " + descricao);
        }
    }

    private static Cidade novaCidade() {
        return new Cidade("Pallet Town", new Jogador("Teste"));
    }

    private static void testarCidade() {
        System.out.println("\n--- Cidade ---");
        Cidade c = novaCidade();
        verificar("cidade nasce com 1000 pokedolares", c.getPokedolares() == 1000);
        verificar("cidade nasce no ano 1986", c.getAnoAtual() == 1986);
        verificar("cidade nasce com 10000 territorios", c.getTerritorios() == 10000);
        verificar("cidade nasce com 500 alimentos", c.getAlimentos() == 500);
        verificar("cidade nasce com 200 habitantes", c.getHabitantes() == 200);
        verificar("remover Pokedolares acima do saldo devolve false", !c.removerPokedolares(1500));
        c.avancarAno();
        verificar("avancarAno soma 1 ao ano", c.getAnoAtual() == 1987);
    }

    private static void testarMercado() {
        System.out.println("\n--- Mercado ---");
        Cidade c = novaCidade();
        Mercado mercado = new Mercado();

        boolean comprou = mercado.comprarAlimentos(c, 100);
        verificar("comprar 100 alimentos da certo", comprou);
        verificar("e custa 200 pokedolares", c.getPokedolares() == 800);
        verificar("e soma 100 alimentos", c.getAlimentos() == 600);

        boolean qtdNegativa = mercado.comprarAlimentos(c, -10);
        verificar("comprar quantidade negativa de alimentos e recusado", !qtdNegativa);

        boolean semPokedolares = mercado.comprarAlimentos(c, 10000);
        verificar("comprar sem pokedolares suficientes e recusado", !semPokedolares);
    }

    private static void testarAgricultura() {
        System.out.println("\n--- Agricultura ---");
        Cidade c = novaCidade();
        Agricultura agri = new Agricultura();

        int hectares = agri.processarColheita(c);
        verificar("primeira colheita do ano cultiva hectares validos", hectares > 0);
        verificar("segunda colheita no mesmo ano devolve -1", agri.processarColheita(c) == -1);

        boolean distInvalida = agri.distribuirAlimentos(c, 10);
        verificar("distribuir 10% (abaixo do minimo 20%) e recusado", !distInvalida);
    }

    private static void testarGoverno() {
        System.out.println("\n--- Governo ---");
        Cidade c = novaCidade();
        Governo gov = new Governo();

        boolean taxaInvalida = gov.definirTaxaLiga(c, 60);
        verificar("taxa de 60% e recusada (maximo 50%)", !taxaInvalida);

        boolean justicaInvalida = gov.definirRegulamentacao(c, "Branda");
        verificar("regulamentacao invalida e recusada", !justicaInvalida);

        int arrecadado = gov.arrecadarTaxas(c);
        verificar("primeira arrecadacao do ano funciona", arrecadado >= 0);
        verificar("segunda arrecadacao no mesmo ano devolve -1", gov.arrecadarTaxas(c) == -1);
    }

    private static void testarGinasios() {
        System.out.println("\n--- Ginasios e Treinadores ---");
        Cidade c = novaCidade();
        Ginasios ginasios = new Ginasios();

        c.removerHabitantes(180);
        boolean recrutou = ginasios.recrutarTreinadores(c, 50);
        verificar("recrutar 50 treinadores com so 20 habitantes e recusado", !recrutou);
        verificar("e NAO debita pokedolares pela metade", c.getPokedolares() == 1000);

        boolean construiu = ginasios.construirGinasios(c, 2);
        verificar("construir 2 ginasios da certo", construiu);
        verificar("e custa 40 pokedolares", c.getPokedolares() == 960);
    }

    private static void testarJogadorENobreza() {
        System.out.println("\n--- Jogador e Nobreza ---");
        Jogador j = new Jogador("Ash");
        verificar("jogador comeca como Treinador Novato", j.getTitulo().equals("Treinador Novato"));

        Cidade c = novaCidade();
        Nobreza nob = new Nobreza();
        int prestigioInicial = nob.calcularPrestigio(c);
        verificar("prestigio inicial calculado corretamente", prestigioInicial > 0);
    }

   private static void testarPartida() {
        System.out.println("\n--- Partida ---");
        Partida partida = new Partida();
        
        Cidade c1 = new Cidade(Cidade.CIDADES_DISPONIVEIS[0], new Jogador("Ash"));
        Cidade c2 = new Cidade(Cidade.CIDADES_DISPONIVEIS[1], new Jogador("Gary"));

        partida.adicionarCidade(c1);
        partida.adicionarCidade(c2);

        verificar("primeiro turno e da primeira cidade adicionada", partida.getCidadeAtual().getNome().equals(c1.getNome()));
        
        boolean fimAno = partida.passarTurno();
        verificar("passar o turno da primeira cidade NAO fecha o ano", !fimAno);
        verificar("agora e a vez da segunda cidade", partida.getCidadeAtual().getNome().equals(c2.getNome()));

        boolean fimAno2 = partida.passarTurno();
        verificar("passar o turno da ultima cidade fecha o ano", fimAno2);
    }
}