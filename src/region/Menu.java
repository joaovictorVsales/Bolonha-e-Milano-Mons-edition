package region;

import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private boolean emExecucao;

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
        System.out.println(" Um jogo de estrategia economica inspirada no universo de pokemon");
        System.out.println("==========================================");
        System.out.println("1 - Novo jogo");
        System.out.println("2 - Sobre o jogo");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opcao: ");
    }

    private int lerOpcao() {
        if (!scanner.hasNextLine()) {
            return 0; 
        }
        
        String linha = scanner.nextLine().trim();
        try {
            return Integer.parseInt(linha);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                System.out.println("Em breve...");
                break;
            case 2:
                exibirSobre();
                break;
            case 0:
                System.out.println("Ate a proxima, treinador!");
                emExecucao = false;
                scanner.close();
                break;
            default:
                System.out.println("Opcao invalida. Digite 1, 2 ou 0.");
        }
    }

    private void exibirSobre() {
        System.out.println();
        System.out.println("Voce governa uma regiao do mundo Pokemon e precisa");
        System.out.println("equilibrar alimentos, territorios, taxas municipais,");
        System.out.println("convivencia e defesa para fazer a regiao prosperar.");
        System.out.println("Gerencie Pokedolares, moradores, treinadores e");
        System.out.println("construcoes como Centros Pokemon, ginasios e laboratorios.");
    }
}