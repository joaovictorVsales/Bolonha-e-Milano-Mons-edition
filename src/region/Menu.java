package region;

import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private boolean emExecucao;
    private Jogador jogador;
    private Cidade cidade;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.emExecucao = true;
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
        System.out.println("3 - Sobre o jogo");
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
        }
        catch (NumberFormatException e) {
            return -1;
        }
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {

            case 1:
                iniciarNovoJogo();
                break;

            case 2:
                if (cidade == null) {
                    System.out.println("Nenhum jogo em andamento. Escolha 1 - Novo jogo.");
                }
                else {
                    cidade.exibirStatus();
                }
                break;

            case 3:
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
            } 
            catch (NumberFormatException e) {}
            System.out.println("Opcao invalida. Digite um numero de 1 a " + cidades.length + ".");
        }
    }

    private void exibirSobre() {
        System.out.println();
        System.out.println("Voce governa uma cidade da regiao de Kanto e precisa");
        System.out.println("equilibrar alimentos, territorios, taxas municipais,");
        System.out.println("convivencia e defesa para fazer a regiao prosperar.");
        System.out.println("Gerencie Pokedolares, habitantes, patrulheiros e");
        System.out.println("construcoes como Centros Pokemon, ginasios e laboratorios.");
    }
}