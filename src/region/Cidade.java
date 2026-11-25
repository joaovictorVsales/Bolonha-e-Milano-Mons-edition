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
    private static final int HABITANTES_INICIAIS = 200;
    private static final int TAXA_LIGA_INICIAL = 10;
    private static final String REGULAMENTACAO_INICIAL = "Moderada";

    private final String nome;
    private int anoAtual;
    private final Jogador dono;
    private int pokedolares;
    private int territorios;
    private int alimentos;
    private int habitantes;
    private int taxaLiga;
    private String regulamentacao;
    private int treinadores;
    private int ginasios;
    private boolean colheitaFeita;
    private boolean taxasArrecadadas;

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
        this.habitantes = HABITANTES_INICIAIS;
        this.taxaLiga = TAXA_LIGA_INICIAL;
        this.regulamentacao = REGULAMENTACAO_INICIAL;
        this.treinadores = 0;
        this.ginasios = 0;
        this.colheitaFeita = false;
        this.taxasArrecadadas = false;
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
    public void avancarAno() {
        anoAtual++;
        colheitaFeita = false;
        taxasArrecadadas = false;
    }

    public boolean isColheitaFeita() { return colheitaFeita; }
    public void marcarColheitaFeita() { colheitaFeita = true; }
    public boolean isTaxasArrecadadas() { return taxasArrecadadas; }
    public void marcarTaxasArrecadadas() { taxasArrecadadas = true; }


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

    public int getHabitantes() { return habitantes; }
    public void adicionarHabitantes(int quantidade) {
        if (quantidade > 0) habitantes += quantidade;
    }

    public boolean removerHabitantes(int quantidade) {
        if (quantidade <= 0 || quantidade > habitantes) return false;
        habitantes -= quantidade;
        return true;
    }

    public int getTreinadores() { return treinadores; }
    public void adicionarTreinadores(int quantidade) {
        if (quantidade > 0) treinadores += quantidade;
    }

    public boolean removerTreinadores(int quantidade) {
        if (quantidade <= 0 || quantidade > treinadores) return false;
        treinadores -= quantidade;
        return true;
    }

    public int getGinasios() { return ginasios; }
    public void adicionarGinasios(int quantidade) {
        if (quantidade > 0) ginasios += quantidade;
    }

    public boolean removerGinasios(int quantidade) {
        if (quantidade <= 0 || quantidade > ginasios) return false;
        ginasios -= quantidade;
        return true;
    }

    public int getTaxaLiga() { return taxaLiga; }
    public void setTaxaLiga(int taxaLiga) { this.taxaLiga = taxaLiga; }
    public String getRegulamentacao() { return regulamentacao; }
    public void setRegulamentacao(String regulamentacao) { this.regulamentacao = regulamentacao; }

    public void exibirStatus() {
        System.out.println();
        System.out.println("------------- STATUS DA CIDADE -------------");
        System.out.println("Cidade:         " + nome);
        System.out.println("Governante:     " + dono.getTitulo() + " " + dono.getNome());
        System.out.println("Ano atual:      " + anoAtual);
        System.out.println("Pokedolares:    " + pokedolares);
        System.out.println("Territorios:    " + territorios + " hectares");
        System.out.println("Alimentos:      " + alimentos);
        System.out.println("Habitantes:     " + habitantes);
        System.out.println("Taxa da Liga:   " + taxaLiga + "%");
        System.out.println("Regulamentacao: " + regulamentacao);
        System.out.println("Treinadores:    " + treinadores);
        System.out.println("Ginasios:       " + ginasios);
        System.out.println("Colheita do ano: " + (colheitaFeita ? "feita" : "pendente"));
        System.out.println("Taxas do ano:    " + (taxasArrecadadas ? "arrecadadas" : "pendentes"));
    }
}