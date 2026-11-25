package region;

public class Cidade {

    public static final String[] CIDADES_DISPONIVEIS = {
        "Pallet Town", "Viridian", "Pewter", "Cerulean", "Vermilion",
        "Lavender", "Celadon", "Fuchsia", "Saffron", "Cinnabar"
    };

    public static final String CIDADE_PADRAO = CIDADES_DISPONIVEIS[0];

    private static final int ANO_INICIAL = 1986;

    private String nome;
    private int anoAtual;
    private Jogador dono;

    public Cidade(String nome, Jogador dono) {

        if (!nomeValido(nome)) {
            throw new IllegalArgumentException("Cidade invalida: " + nome);
        }

        this.nome = nome;
        this.dono = dono;
        this.anoAtual = ANO_INICIAL;
    }

    public static boolean nomeValido(String nome) {
        for (String cidade : CIDADES_DISPONIVEIS) {

            if (cidade.equals(nome)) {
                return true;
            }
        }
        return false;
    }

    public void avancarAno() {
        anoAtual++;
    }

    public void exibirStatus() {
        System.out.println();
        System.out.println("------------- STATUS DA CIDADE -------------");
        System.out.println("Cidade:      " + nome);
        System.out.println("Governante:  " + dono.getTitulo() + " " + dono.getNome());
        System.out.println("Ano atual:   " + anoAtual);
    }
}