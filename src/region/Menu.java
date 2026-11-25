package region;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner;
    private boolean emExecucao;
    private Partida partida;
    private Cidade cidade; 
    private final Mercado mercado;
    private final Agricultura agricultura;
    private final Governo governo;
    private final Ginasios ginasios;
    private final Nobreza nobreza;
    private final Eventos eventos;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.emExecucao = true;
        this.mercado = new Mercado();
        this.agricultura = new Agricultura();
        this.governo = new Governo();
        this.ginasios = new Ginasios();
        this.nobreza = new Nobreza();
        this.eventos = new Eventos();
    }

    public void executar() {
        while (emExecucao) {
            exibirOpcoes();
            int opcao = lerOpcao();
            processarOpcao(opcao);
        }
        scanner.close();
    }

    private void exibirOpcoes() {
        System.out.println();
        System.out.println("==========================================");
        System.out.println(" BOLONHA E MILANO - POKEMON EDITION");
        System.out.println(" Um jogo de estrategia economica Pokemon");
        System.out.println("==========================================");
        if (partida != null) {
            System.out.println("Ano " + partida.getAnoAtual() + " | Vez de: "
                    + cidade.getDono().getNome() + " (" + cidade.getNome() + ") - "
                    + cidade.getDono().getTitulo());
            System.out.println("------------------------------------------");
        }
        System.out.println("1 - Novo jogo");
        System.out.println("2 - Ver status da cidade");
        System.out.println("3 - Mercado");
        System.out.println("4 - Agricultura");
        System.out.println("5 - Governo");
        System.out.println("6 - Ginasios");
        System.out.println("7 - Encerrar turno");
        System.out.println("8 - Ver classificacao");
        System.out.println("9 - Sobre o jogo");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opcao: ");
    }

    private String lerLinha() {
        return scanner.hasNextLine() ? scanner.nextLine().trim() : null;
    }

    private int lerInteiro() {
        String linha = lerLinha();
        if (linha == null || linha.isEmpty()) {
            return -1;
        }
        try {
            return Integer.parseInt(linha);
        } 
        catch (NumberFormatException e) {
            return -1;
        }
    }

    private int lerOpcao() {
        return lerInteiro();
    }

    private int lerQuantidade() {
        return lerInteiro();
    }

    private boolean exigirJogoEmAndamento() {
        if (partida == null || cidade == null) {
            System.out.println("Nenhum jogo em andamento. Escolha 1 - Novo jogo.");
            return false;
        }
        return true;
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                iniciarNovoJogo();
                break;

            case 2:
                exibirStatus();
                break;

            case 3:
                abrirMercado();
                break;

            case 4:
                abrirAgricultura();
                break;

            case 5:
                abrirGoverno();
                break;

            case 6:
                abrirGinasios();
                break;

            case 7:
                encerrarTurno();
                break;

            case 8:
                exibirClassificacao();
                break;

            case 9:
                exibirSobre();
                break;

            case 0:
                System.out.println("Ate a proxima, treinador!");
                emExecucao = false;
                break;

            default:
                System.out.println("Opcao invalida. Escolha uma das opcoes do menu.");
        }
    }

    private void iniciarNovoJogo() {
        System.out.print("Quantos jogadores (1 a " + Partida.MAXIMO_JOGADORES + ")? ");
        int quantidade = lerQuantidade();
        if (quantidade < 1 || quantidade > Partida.MAXIMO_JOGADORES) {
            quantidade = 2;
            System.out.println("Numero invalido. A partida vai ter 2 jogadores.");
        }

        Partida nova = new Partida();
        for (int i = 1; i <= quantidade; i++) {
            System.out.println();
            System.out.println("--- Jogador " + i + " ---");
            System.out.print("Nome do jogador (Enter para \"Jogador " + i + "\"): ");
            String nome = lerLinha();
            if (nome == null || nome.isEmpty()) {
                nome = "Jogador " + i;
            }

            String nomeCidade = escolherCidade(nova);
            nova.adicionarCidade(new Cidade(nomeCidade, new Jogador(nome)));
        }

        partida = nova;
        cidade = partida.getCidadeAtual();

        System.out.println();
        System.out.println("Partida criada! A Liga Pokemon comeca em " + partida.getAnoAtual() + " e termina em " + partida.getAnoFinal() + ".");
        System.out.println("Quem chegar primeiro a Campeao Mundial vence.");
        System.out.println("Comeca " + cidade.getDono().getNome() + " (" + cidade.getNome() + ").");
        cidade.exibirStatus();
    }

    private boolean cidadeJaEscolhida(Partida p, String nome) {
        for (Cidade c : p.getCidades()) {
            if (c.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    private String primeiraCidadeLivre(Partida p) {
        for (String nome : Cidade.CIDADES_DISPONIVEIS) {
            if (!cidadeJaEscolhida(p, nome)) {
                return nome;
            }
        }
        return Cidade.CIDADE_PADRAO;
    }

    private String escolherCidade(Partida p) {
        String[] cidades = Cidade.CIDADES_DISPONIVEIS;
        String padrao = primeiraCidadeLivre(p);
        while (true) {
            System.out.println();
            System.out.println("Escolha a cidade que voce vai governar:");
            for (int i = 0; i < cidades.length; i++) {
                String marca = cidadeJaEscolhida(p, cidades[i]) ? " (ja escolhida)" : "";
                System.out.println((i + 1) + " - " + cidades[i] + marca);
            }

            System.out.print("Numero da cidade (Enter para " + padrao + "): ");

            String linha = lerLinha();
            if (linha == null || linha.isEmpty()) {
                return padrao;
            }
            
            try {
                int numero = Integer.parseInt(linha);
                if (numero >= 1 && numero <= cidades.length) {
                    if (cidadeJaEscolhida(p, cidades[numero - 1])) {
                        System.out.println("Essa cidade ja foi escolhida por outro jogador.");
                        continue;
                    }
                    return cidades[numero - 1];
                }
            } 
            
            catch (NumberFormatException e) {}
            System.out.println("Opcao invalida. Digite um numero de 1 a " + cidades.length + ".");
        }
    }

    private void exibirStatus() {
        if (!exigirJogoEmAndamento()) return;
        cidade.exibirStatus();

        int prestigio = nobreza.calcularPrestigio(cidade);
        int necessario = nobreza.prestigioParaProximoTitulo(cidade.getDono());
        if (necessario == -1) {
            System.out.println("Prestigio:       " + prestigio + " (titulo maximo)");
        } 
        
        else {
            System.out.println("Prestigio:       " + prestigio + " (proximo titulo exige " + necessario + ")");
        }
    }

    private void encerrarTurno() {
        if (!exigirJogoEmAndamento()) return;

        String mensagemEvento = eventos.tentarEventoAleatorio(cidade);
        if (mensagemEvento != null) {
            System.out.println();
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println(mensagemEvento);
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }

        boolean fimDoAno = partida.passarTurno();
        if (fimDoAno) {
            List<Cidade> promovidas = partida.encerrarAno(nobreza);

            System.out.println();
            System.out.println("========== O ANO " + (partida.getAnoAtual() - 1) + " TERMINOU ==========");
            if (promovidas.isEmpty()) {
                System.out.println("Ninguem foi promovido neste ano.");
            } 
            
            else {
                for (Cidade promovida : promovidas) {
                    Jogador dono = promovida.getDono();
                    System.out.println(">>> " + dono.getNome() + " (" + promovida.getNome()
                            + ") foi promovido a " + dono.getTitulo() + "!");
                }
            }
            exibirClassificacao();

            if (partida.terminou()) {
                encerrarPartida();
                return;
            }

            System.out.println();
            System.out.println("Comeca o ano " + partida.getAnoAtual() + ".");
        }

        cidade = partida.getCidadeAtual();
        System.out.println("Agora e a vez de " + cidade.getDono().getNome() + " (" + cidade.getNome() + ").");
    }

    private void exibirClassificacao() {
        if (!exigirJogoEmAndamento()) return;

        List<Cidade> ordenadas = new ArrayList<>(partida.getCidades());
        ordenadas.sort((a, b) -> {
            int nivel = Integer.compare(b.getDono().getNivelTitulo(), a.getDono().getNivelTitulo());
            if (nivel != 0) {
                return nivel;
            }
            
            return Integer.compare(nobreza.calcularPrestigio(b), nobreza.calcularPrestigio(a));
        });

        System.out.println();
        System.out.println("------------- CLASSIFICACAO DA LIGA -------------");
        int posicao = 1;
        for (Cidade c : ordenadas) {
            Jogador dono = c.getDono();
            int prestigio = nobreza.calcularPrestigio(c);
            int necessario = nobreza.prestigioParaProximoTitulo(dono);

            String proximo;
            if (necessario == -1) {
                proximo = "titulo maximo alcancado";
            } 
            
            else if (prestigio >= necessario) {
                proximo = "prestigio ja suficiente para o proximo titulo";
            } 
            
            else {
                proximo = "faltam " + (necessario - prestigio) + " pontos para o proximo titulo";
            }

            System.out.println(posicao + ". " + dono.getNome() + " (" + c.getNome() + ") - "
                    + dono.getTitulo() + " | Prestigio: " + prestigio + " | " + proximo);
            posicao++;
        }
    }

    private void encerrarPartida() {
        Cidade vencedora = partida.determinarVencedor(nobreza);
        Jogador dono = vencedora.getDono();

        System.out.println();
        System.out.println("================== FIM DE JOGO ==================");
        if (dono.ehMonarca()) {
            System.out.println(dono.getNome() + ", de " + vencedora.getNome() + ", chegou ao topo da Liga como "
                    + dono.getTitulo() + " e venceu!");
        } 
        
        else {
            System.out.println("O prazo (" + partida.getAnoFinal() + ") acabou. Melhor colocado: "
                    + dono.getNome() + " (" + vencedora.getNome() + "), " + dono.getTitulo() + ".");
        }

        partida = null;
        cidade = null;
    }

    private void abrirMercado() {
        if (!exigirJogoEmAndamento()) return;

        boolean noMercado = true;
        while (noMercado) {
            System.out.println();
            System.out.println("------------------ MERCADO ------------------");
            System.out.println("Pokedolares: " + cidade.getPokedolares() + " | Alimentos: " + cidade.getAlimentos() + " | Territorios: " + cidade.getTerritorios());
            System.out.println("Preco por unidade -> alimento: " + mercado.getPrecoAlimento() + " | territorio: " + mercado.getPrecoTerritorio());
            System.out.println("1 - Comprar alimentos");
            System.out.println("2 - Vender alimentos");
            System.out.println("3 - Comprar territorios");
            System.out.println("4 - Vender territorios");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opcao: ");

            int opcao = lerOpcao();
            switch (opcao) {
                case 1:
                    comprarAlimentos();
                    break;

                case 2:
                    venderAlimentos();
                    break;

                case 3:
                    comprarTerritorios();
                    break;

                case 4:
                    venderTerritorios();
                    break;

                case 0:
                    noMercado = false;
                    break;

                default:
                    System.out.println("Opcao invalida. Escolha uma das opcoes do mercado.");
            }
        }
    }

    private void comprarAlimentos() {
        System.out.print("Quantidade de alimentos para comprar: ");
        int quantidade = lerQuantidade();
        if (quantidade <= 0) {
            System.out.println("Quantidade invalida. Digite um numero maior que zero.");
        } 
        
        else if (mercado.comprarAlimentos(cidade, quantidade)) {
            System.out.println("Compra realizada: " + quantidade + " alimentos por "
                    + (quantidade * mercado.getPrecoAlimento()) + " Pokedolares.");
        } 
        
        else {
            System.out.println("Compra recusada: Pokedolares insuficientes.");
        }
    }

    private void venderAlimentos() {
        System.out.print("Quantidade de alimentos para vender: ");
        int quantidade = lerQuantidade();
        if (quantidade <= 0) {
            System.out.println("Quantidade invalida. Digite um numero maior que zero.");
        } 
        
        else if (mercado.venderAlimentos(cidade, quantidade)) {
            System.out.println("Venda realizada: " + quantidade + " alimentos por "
                    + (quantidade * mercado.getPrecoAlimento()) + " Pokedolares.");
        } 
        
        else {
            System.out.println("Venda recusada: alimentos insuficientes.");
        }
    }

    private void comprarTerritorios() {
        System.out.print("Quantidade de territorios (hectares) para comprar: ");
        int quantidade = lerQuantidade();
        if (quantidade <= 0) {
            System.out.println("Quantidade invalida. Digite um numero maior que zero.");
        } 
        
        else if (mercado.comprarTerritorios(cidade, quantidade)) {
            System.out.println("Compra realizada: " + quantidade + " hectares por "
                    + (quantidade * mercado.getPrecoTerritorio()) + " Pokedolares.");
        } 
        
        else {
            System.out.println("Compra recusada: Pokedolares insuficientes.");
        }
    }

    private void venderTerritorios() {
        System.out.print("Quantidade de territorios (hectares) para vender: ");
        int quantidade = lerQuantidade();
        if (quantidade <= 0) {
            System.out.println("Quantidade invalida. Digite um numero maior que zero.");
        } 
        
        else if (mercado.venderTerritorios(cidade, quantidade)) {
            System.out.println("Venda realizada: " + quantidade + " hectares por "
                    + (quantidade * mercado.getPrecoTerritorio()) + " Pokedolares.");
        } 
        
        else {
            System.out.println("Venda recusada: territorios insuficientes.");
        }
    }

    private void abrirAgricultura() {
        if (!exigirJogoEmAndamento()) return;

        boolean naAgricultura = true;
        while (naAgricultura) {
            System.out.println();
            System.out.println("---------------- AGRICULTURA ----------------");
            System.out.println("Territorios: " + cidade.getTerritorios() + " | Habitantes: " + cidade.getHabitantes() + " | Alimentos: " + cidade.getAlimentos());
            System.out.println("Colheita do ano: " + (cidade.isColheitaFeita() ? "feita" : "pendente"));
            System.out.println("1 - Colher (processar safra)");
            System.out.println("2 - Distribuir alimentos");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opcao: ");

            int opcao = lerOpcao();
            switch (opcao) {
                case 1:
                    colher();
                    break;

                case 2:
                    distribuirAlimentos();
                    break;

                case 0:
                    naAgricultura = false;
                    break;

                default:
                    System.out.println("Opcao invalida. Escolha uma das opcoes da agricultura.");
            }
        }
    }

    private void colher() {
        int alimentosAntes = cidade.getAlimentos();
        int hectares = agricultura.processarColheita(cidade);

        if (hectares == -1) {
            System.out.println("Voce ja colheu neste ano. Encerre o turno (opcao 7) para avancar.");
        } 
        
        else if (hectares == 0) {
            System.out.println("Nada foi plantado: faltam habitantes, territorios ou alimentos para sementes.");
            System.out.println("A colheita deste ano foi encerrada.");
        } 
        
        else {
            System.out.println("Colheita realizada: " + hectares + " hectares cultivados.");
            System.out.println("Alimentos: " + alimentosAntes + " -> " + cidade.getAlimentos());
        }
    }

    private void distribuirAlimentos() {
        System.out.print("Percentual do estoque a distribuir (20 a 80): ");
        int percentual = lerQuantidade();

        int alimentosAntes = cidade.getAlimentos();
        int habitantesAntes = cidade.getHabitantes();

        if (!agricultura.distribuirAlimentos(cidade, percentual)) {
            System.out.println("Percentual invalido. Digite um numero de 20 a 80.");
            return;
        }

        int distribuido = alimentosAntes - cidade.getAlimentos();
        int habitantesDepois = cidade.getHabitantes();
        System.out.println("Foram distribuidos " + distribuido + " alimentos.");

        if (habitantesDepois > habitantesAntes) {
            System.out.println("Comida suficiente! Habitantes: " + habitantesAntes
                    + " -> " + habitantesDepois);
        } 
        
        else if (habitantesDepois < habitantesAntes) {
            System.out.println("Faltou comida! Habitantes: " + habitantesAntes
                    + " -> " + habitantesDepois);
        } 
        
        else {
            System.out.println("A populacao se manteve em " + habitantesDepois + " habitantes.");
        }
    }

    private void abrirGoverno() {
        if (!exigirJogoEmAndamento()) return;

        boolean noGoverno = true;
        while (noGoverno) {
            System.out.println();
            System.out.println("------------------ GOVERNO ------------------");
            System.out.println("Pokedolares: " + cidade.getPokedolares() + " | Taxa da Liga: " + cidade.getTaxaLiga() + "%" + " | Regulamentacao: " + cidade.getRegulamentacao());
            System.out.println("Taxas do ano: " + (cidade.isTaxasArrecadadas() ? "arrecadadas" : "pendentes"));
            System.out.println("1 - Arrecadar taxas (Liga Pokemon)");
            System.out.println("2 - Definir taxa da Liga");
            System.out.println("3 - Definir regulamentacao de convivencia");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opcao: ");

            int opcao = lerOpcao();
            switch (opcao) {
                case 1:
                    arrecadarTaxas();
                    break;

                case 2:
                    definirTaxaLiga();
                    break;

                case 3:
                    definirRegulamentacao();
                    break;

                case 0:
                    noGoverno = false;
                    break;

                default:
                    System.out.println("Opcao invalida. Escolha uma das opcoes do governo.");
            }
        }
    }

    private void arrecadarTaxas() {
        int pokedolaresAntes = cidade.getPokedolares();
        int arrecadado = governo.arrecadarTaxas(cidade);

        if (arrecadado == -1) {
            System.out.println("As taxas deste ano ja foram arrecadadas. Encerre o turno (opcao 7) para avancar.");
            return;
        }

        System.out.println("A Liga Pokemon cobrou as taxas dos habitantes e repassou " + arrecadado + " Pokedolares a tesouraria de " + cidade.getNome() + ".");
        System.out.println("Pokedolares: " + pokedolaresAntes + " -> " + cidade.getPokedolares());
    }

    private void definirTaxaLiga() {
        System.out.print("Nova taxa da Liga em % (0 a 50): ");
        int taxa = lerQuantidade();

        if (governo.definirTaxaLiga(cidade, taxa)) {
            System.out.println("Taxa da Liga definida em " + taxa + "%.");
        } 
        
        else {
            System.out.println("Taxa invalida. Digite um numero de 0 a 50.");
        }
    }

    private void definirRegulamentacao() {
        System.out.println();
        System.out.println("Regulamentacao de convivencia:");
        System.out.println("1 - Justa (arrecada 20% a menos)");
        System.out.println("2 - Moderada (arrecadacao padrao)");
        System.out.println("3 - Severa (arrecada 30% a mais)");
        System.out.print("Escolha o nivel: ");

        int opcao = lerOpcao();
        String nivel = null;
        switch (opcao) {
            case 1:
                nivel = Governo.NIVEL_JUSTA;
                break;

            case 2:
                nivel = Governo.NIVEL_MODERADA;
                break;

            case 3:
                nivel = Governo.NIVEL_SEVERA;
                break;

            default:
                break;
        }

        if (nivel != null && governo.definirRegulamentacao(cidade, nivel)) {
            System.out.println("Regulamentacao definida: " + nivel + ".");
        } 
        
        else {
            System.out.println("Nivel invalido. Escolha 1, 2 ou 3.");
        }
    }

    private void abrirGinasios() {
        if (!exigirJogoEmAndamento()) return;

        boolean noGinasio = true;
        while (noGinasio) {
            System.out.println();
            System.out.println("------------------ GINASIOS -----------------");
            System.out.println("Pokedolares: " + cidade.getPokedolares()
                    + " | Habitantes: " + cidade.getHabitantes()
                    + " | Treinadores: " + cidade.getTreinadores()
                    + " | Ginasios: " + cidade.getGinasios());
            System.out.println("Treinador: " + ginasios.getPrecoTreinador()
                    + " Pokedolares + 1 habitante | Ginasio: "
                    + ginasios.getPrecoGinasio() + " Pokedolares");
            System.out.println("1 - Recrutar treinadores");
            System.out.println("2 - Construir ginasios");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opcao: ");

            int opcao = lerOpcao();
            switch (opcao) {
                case 1:
                    recrutarTreinadores();
                    break;

                case 2:
                    construirGinasios();
                    break;

                case 0:
                    noGinasio = false;
                    break;

                default:
                    System.out.println("Opcao invalida. Escolha uma das opcoes dos ginasios.");
            }
        }
    }

    private void recrutarTreinadores() {
        System.out.print("Quantidade de treinadores para recrutar: ");
        int quantidade = lerQuantidade();
        if (quantidade <= 0) {
            System.out.println("Quantidade invalida. Digite um numero maior que zero.");
        } 
        
        else if (ginasios.recrutarTreinadores(cidade, quantidade)) {
            System.out.println("Recrutamento realizado: " + quantidade + " treinadores por "
                    + ((long) quantidade * ginasios.getPrecoTreinador()) + " Pokedolares e "
                    + quantidade + " habitantes.");
        } 
        
        else {
            System.out.println("Recrutamento recusado: voce precisa de "
                    + ((long) quantidade * ginasios.getPrecoTreinador()) + " Pokedolares e "
                    + quantidade + " habitantes disponiveis.");
        }
    }

    private void construirGinasios() {
        System.out.print("Quantidade de ginasios para construir: ");
        int quantidade = lerQuantidade();
        if (quantidade <= 0) {
            System.out.println("Quantidade invalida. Digite um numero maior que zero.");
        } 
        
        else if (ginasios.construirGinasios(cidade, quantidade)) {
            System.out.println("Construcao realizada: " + quantidade + " ginasios por "
                    + ((long) quantidade * ginasios.getPrecoGinasio()) + " Pokedolares.");
        } 
        
        else {
            System.out.println("Construcao recusada: Pokedolares insuficientes.");
        }
    }

    private void exibirSobre() {
        System.out.println();
        System.out.println("De 1 a 4 treinadores governam cidades de Kanto. A cada ano,");
        System.out.println("cada um joga o seu turno: compra e vende, colhe, arrecada as");
        System.out.println("taxas da Liga Pokemon e investe em treinadores e ginasios.");
        System.out.println("Seu prestigio decide sua posicao na Liga, de Treinador Novato");
        System.out.println("ate Campeao Mundial. Quem chegar primeiro ao topo vence;");
        System.out.println("se ninguem chegar ate o ano final, vence o melhor colocado na associacao da Liga.");
    }
}