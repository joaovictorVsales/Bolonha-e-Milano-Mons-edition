package region;

public class Cidade {

    public static final String[] CIDADES_DISPONIVEIS = {
        "Pallet Town", "Viridian", "Pewter", "Cerulean", "Vermilion",
        "Lavender", "Celadon", "Fuchsia", "Saffron", "Cinnabar"
    };
    
    public static final String CIDADE_PADRAO = CIDADES_DISPONIVEIS[0];

    private static final int ANO_INICIAL = 1986;
    private static final int POKEDOLARES_INICIAIS = 1000;
    private static final int TERRITORIOS_INICIAIS = 10000;
    private static final int ALIMENTOS_INICIAIS = 500;

    private String nome;
    private int anoAtual;
    private Jogador dono;
    private int pokedolares;
    private int territorios;
    private int alimentos;

    public Cidade(String nome, Jogador dono) {
        if (!nomeValido(nome)) {
            throw new IllegalArgumentException("Cidade invalida: " + nome);
        }

        this.nome = nome;
        this.dono = dono;
        this.anoAtual = ANO_INICIAL;
        this.pokedolares = POKEDOLARES_INICIAIS;
        this.territorios = TERRITORIOS_INICIAIS;
        this.alimentos = ALIMENTOS_INICIAIS;
    }

    public static boolean nomeValido(String nome) {
        for (String cidade : CIDADES_DISPONIVEIS) {
            if (cidade.equals(nome)) {
                return true;
            }
        }
        return false;
    }

    public String getNome() { return nome; }

    public int getAnoAtual() { return anoAtual; }

    public Jogador getDono() { return dono; }

    public void avancarAno() { anoAtual++; }

    public int getPokedolares() { return pokedolares; }

    public void adicionarPokedolares(int quantidade) {
        if (quantidade > 0) pokedolares += quantidade;
    }

    public boolean removerPokedolares(int quantidade) {
        if (quantidade <= 0 || quantidade > pokedolares) return false;
        pokedolares -= quantidade;
        return true;
    }

    public int getTerritorios() { return territorios; }

    public void adicionarTerritorios(int quantidade) {
        if (quantidade > 0) territorios += quantidade;
    }

    public boolean removerTerritorios(int quantidade) {
        if (quantidade <= 0 || quantidade > territorios) return false;
        territorios -= quantidade;
        return true;
    }

    public int getAlimentos() { return alimentos; }

    public void adicionarAlimentos(int quantidade) {
        if (quantidade > 0) alimentos += quantidade;
    }

    public boolean removerAlimentos(int quantidade) {
        if (quantidade <= 0 || quantidade > alimentos) return false;
        alimentos -= quantidade;
        return true;
    }

    public void exibirStatus() {
        System.out.println();
        System.out.println("------------- STATUS DA CIDADE -------------");
        System.out.println("Cidade:      " + nome);
        System.out.println("Governante:  " + dono.getTitulo() + " " + dono.getNome());
        System.out.println("Ano atual:   " + anoAtual);
        System.out.println("Pokedolares: " + pokedolares);
        System.out.println("Territorios: " + territorios + " hectares");
        System.out.println("Alimentos:   " + alimentos);
    }
}