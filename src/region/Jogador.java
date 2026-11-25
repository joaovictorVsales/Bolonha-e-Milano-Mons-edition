package region;

public class Jogador {

    public static final String NOME_PADRAO = "Jogador 1";

    private static final String[] TITULOS = {
        "Treinador Novato", "Treinador", "Treinador Experiente",
        "Lider de Ginasio", "Membro de Elite", "Campeao", "Campeao Mundial"
    };

    private final String nome;
    private int nivelTitulo;

    public Jogador(String nome) {
        if (nome == null || nome.isBlank()) {
            this.nome = NOME_PADRAO;
        } 
        
        else {
            this.nome = nome.trim();
        }
        this.nivelTitulo = 0;
    }

    public String getNome() {
        return nome;
    }

    public String getTitulo() {
        return TITULOS[nivelTitulo];
    }

    public int getNivelTitulo() {
        return nivelTitulo;
    }

    public boolean promover() {
        if (ehMonarca()) {
            return false;
        }

        nivelTitulo++;
        return true;
    }

    public boolean ehMonarca() {
        return nivelTitulo == TITULOS.length - 1;
    }
}