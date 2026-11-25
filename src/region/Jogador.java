package region;

public class Jogador {

    public static final String NOME_PADRAO = "Jogador 1";
    private static final String TITULO_INICIAL = "Treinador Novato";

    private String nome;
    private String titulo;

    public Jogador(String nome) {
        if (nome == null || nome.isBlank()) {
            this.nome = NOME_PADRAO;
        } 
        
        else {
            this.nome = nome.trim();
        }
        
        this.titulo = TITULO_INICIAL;
    }

    public String getNome() {
        return nome;
    }

    public String getTitulo() {
        return titulo;
    }
}