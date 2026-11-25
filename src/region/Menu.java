package region;

import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private boolean emExecucao;
    private Jogador jogador;
    private Cidade cidade;
    private Mercado mercado;
    private Agricultura agricultura;
    private Governo governo;
    private Ginasios ginasios;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.emExecucao = true;
        this.mercado = new Mercado();
        this.agricultura = new Agricultura();
        this.governo = new Governo();
        this.ginasios = new Ginasios();
    }

    public void executar() {
        while (emExecucao) {
            exibirOpcoes();
            int opcao = lerOpcao();
            processarOpcao(opcao);
        }
    }

    private void exibirOpcoes() {
        System.out.println();
        System.out.println("==========================================");
        System.out.println(" BOLONHA E MILANO - POKEMON EDITION");
        System.out.println(" Um jogo de estrategia economica Pokemon");
        System.out.println("==========================================");
        System.out.println("1 - Novo jogo");
        System.out.println("2 - Ver status da cidade");
        System.out.println("3 - Mercado");
        System.out.println("4 - Agricultura");
        System.out.println("5 - Governo");
        System.out.println("6 - Ginasios");
        System.out.println("7 - Sobre o jogo");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opcao: ");
    }

    private String lerLinha() {
        return scanner.hasNextLine() ? scanner.nextLine().trim() : null;
    }

    private int lerOpcao() {
        String linha = lerLinha();
        if (linha == null) {
            return 0;
        }
        try {
            return Integer.parseInt(linha);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private int lerQuantidade() {
        String linha = lerLinha();
        if (linha == null) {
            return -1;
        }
        try {
            return Integer.parseInt(linha);
        } catch (NumberFormatException e) {
            return -1;
        }
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
                exibirSobre();
                break;
            case 0:
                System.out.println("Ate a proxima, treinador!");
                emExecucao = false;
                scanner.close();
                break;
            default:
                System.out.println("Opcao invalida. Escolha uma das opcoes do menu.");
        }
    }

    private void iniciarNovoJogo() {
        System.out.print("Digite o nome do jogador (Enter para \"" + Jogador.NOME_PADRAO + "\"): ");
        String nomeJogador = lerLinha();
        String nomeCidade = escolherCidade();

        jogador = new Jogador(nomeJogador);
        cidade = new Cidade(nomeCidade, jogador);
        cidade.exibirStatus();
    }

    private String escolherCidade() {
        String[] cidades = Cidade.CIDADES_DISPONIVEIS;
        while (true) {
            System.out.println();
            System.out.println("Escolha a cidade que voce vai governar:");
            for (int i = 0; i < cidades.length; i++) {
                System.out.println((i + 1) + " - " + cidades[i]);
            }
            System.out.print("Numero da cidade (Enter para " + Cidade.CIDADE_PADRAO + "): ");

            String linha = lerLinha();
            if (linha == null || linha.isEmpty()) {
                return Cidade.CIDADE_PADRAO;
            }
            try {
                int numero = Integer.parseInt(linha);
                if (numero >= 1 && numero <= cidades.length) {
                    return cidades[numero - 1];
                }
            } catch (NumberFormatException e) {}
            System.out.println("Opcao invalida. Digite um numero de 1 a " + cidades.length + ".");
        }
    }

    private void exibirStatus() {
        if (cidade == null) {
            System.out.println("Nenhum jogo em andamento. Escolha 1 - Novo jogo.");
        } else {
            cidade.exibirStatus();
        }
    }

    private void abrirMercado() {
        if (cidade == null) {
            System.out.println("Nenhum jogo em andamento. Escolha 1 - Novo jogo.");
            return;
        }

        boolean noMercado = true;
        while (noMercado) {
            System.out.println();
            System.out.println("------------------ MERCADO ------------------");
            System.out.println("Pokedolares: " + cidade.getPokedolares()
                    + " | Alimentos: " + cidade.getAlimentos()
                    + " | Territorios: " + cidade.getTerritorios());
            System.out.println("Preco por unidade -> alimento: " + mercado.getPrecoAlimento()
                    + " | territorio: " + mercado.getPrecoTerritorio());
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
        } else if (mercado.comprarAlimentos(cidade, quantidade)) {
            System.out.println("Compra realizada: " + quantidade + " alimentos por "
                    + (quantidade * mercado.getPrecoAlimento()) + " Pokedolares.");
        } else {
            System.out.println("Compra recusada: Pokedolares insuficientes.");
        }
    }

    private void venderAlimentos() {
        System.out.print("Quantidade de alimentos para vender: ");
        int quantidade = lerQuantidade();
        if (quantidade <= 0) {
            System.out.println("Quantidade invalida. Digite um numero maior que zero.");
        } else if (mercado.venderAlimentos(cidade, quantidade)) {
            System.out.println("Venda realizada: " + quantidade + " alimentos por "
                    + (quantidade * mercado.getPrecoAlimento()) + " Pokedolares.");
        } else {
            System.out.println("Venda recusada: alimentos insuficientes.");
        }
    }

    private void comprarTerritorios() {
        System.out.print("Quantidade de territorios (hectares) para comprar: ");
        int quantidade = lerQuantidade();
        if (quantidade <= 0) {
            System.out.println("Quantidade invalida. Digite um numero maior que zero.");
        } else if (mercado.comprarTerritorios(cidade, quantidade)) {
            System.out.println("Compra realizada: " + quantidade + " hectares por "
                    + (quantidade * mercado.getPrecoTerritorio()) + " Pokedolares.");
        } else {
            System.out.println("Compra recusada: Pokedolares insuficientes.");
        }
    }

    private void venderTerritorios() {
        System.out.print("Quantidade de territorios (hectares) para vender: ");
        int quantidade = lerQuantidade();
        if (quantidade <= 0) {
            System.out.println("Quantidade invalida. Digite um numero maior que zero.");
        } else if (mercado.venderTerritorios(cidade, quantidade)) {
            System.out.println("Venda realizada: " + quantidade + " hectares por "
                    + (quantidade * mercado.getPrecoTerritorio()) + " Pokedolares.");
        } else {
            System.out.println("Venda recusada: territorios insuficientes.");
        }
    }

    private void abrirAgricultura() {
        if (cidade == null) {
            System.out.println("Nenhum jogo em andamento. Escolha 1 - Novo jogo.");
            return;
        }

        boolean naAgricultura = true;
        while (naAgricultura) {
            System.out.println();
            System.out.println("---------------- AGRICULTURA ----------------");
            System.out.println("Territorios: " + cidade.getTerritorios()
                    + " | Habitantes: " + cidade.getHabitantes()
                    + " | Alimentos: " + cidade.getAlimentos());
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

        if (hectares == 0) {
            System.out.println("Nada foi plantado: faltam habitantes, territorios ou alimentos para sementes.");
        } else {
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
        } else if (habitantesDepois < habitantesAntes) {
            System.out.println("Faltou comida! Habitantes: " + habitantesAntes
                    + " -> " + habitantesDepois);
        } else {
            System.out.println("A populacao se manteve em " + habitantesDepois + " habitantes.");
        }
    }

    private void abrirGoverno() {
        if (cidade == null) {
            System.out.println("Nenhum jogo em andamento. Escolha 1 - Novo jogo.");
            return;
        }

        boolean noGoverno = true;
        while (noGoverno) {
            System.out.println();
            System.out.println("------------------ GOVERNO ------------------");
            System.out.println("Pokedolares: " + cidade.getPokedolares()
                    + " | Taxa da Liga: " + cidade.getTaxaLiga() + "%"
                    + " | Regulamentacao: " + cidade.getRegulamentacao());
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

        System.out.println("A Liga Pokemon cobrou as taxas dos habitantes e repassou "
                + arrecadado + " Pokedolares a tesouraria de " + cidade.getNome() + ".");
        System.out.println("Pokedolares: " + pokedolaresAntes + " -> " + cidade.getPokedolares());
    }

    private void definirTaxaLiga() {
        System.out.print("Nova taxa da Liga em % (0 a 50): ");
        int taxa = lerQuantidade();

        if (governo.definirTaxaLiga(cidade, taxa)) {
            System.out.println("Taxa da Liga definida em " + taxa + "%.");
        } else {
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
        } else {
            System.out.println("Nivel invalido. Escolha 1, 2 ou 3.");
        }
    }

    private void abrirGinasios() {
        if (cidade == null) {
            System.out.println("Nenhum jogo em andamento. Escolha 1 - Novo jogo.");
            return;
        }

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
        } else if (ginasios.recrutarTreinadores(cidade, quantidade)) {
            System.out.println("Recrutamento realizado: " + quantidade + " treinadores por "
                    + ((long) quantidade * ginasios.getPrecoTreinador()) + " Pokedolares e "
                    + quantidade + " habitantes.");
        } else {
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
        } else if (ginasios.construirGinasios(cidade, quantidade)) {
            System.out.println("Construcao realizada: " + quantidade + " ginasios por "
                    + ((long) quantidade * ginasios.getPrecoGinasio()) + " Pokedolares.");
        } else {
            System.out.println("Construcao recusada: Pokedolares insuficientes.");
        }
    }

    private void exibirSobre() {
        System.out.println();
        System.out.println("Voce governa uma cidade da regiao de Kanto e precisa");
        System.out.println("equilibrar alimentos, territorios, taxas da Liga Pokemon,");
        System.out.println("convivencia e defesa para fazer a regiao prosperar.");
        System.out.println("Gerencie Pokedolares, habitantes, treinadores e");
        System.out.println("ginasios para fazer sua cidade crescer.");
    }
}