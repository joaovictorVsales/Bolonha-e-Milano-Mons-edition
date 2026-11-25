package region;

public class Ginasios {

    private static final int PRECO_TREINADOR = 5;
    private static final int HABITANTES_POR_TREINADOR = 1;
    private static final int PRECO_GINASIO = 20;

    public int getPrecoTreinador() {
        return PRECO_TREINADOR;
    }

    public int getPrecoGinasio() {
        return PRECO_GINASIO;
    }

    public boolean recrutarTreinadores(Cidade cidade, int quantidade) {
        if (quantidade <= 0) {
            return false;
        }
        long custo = (long) quantidade * PRECO_TREINADOR;
        long habitantesNecessarios = (long) quantidade * HABITANTES_POR_TREINADOR;

        if (custo > cidade.getPokedolares() || habitantesNecessarios > cidade.getHabitantes()) {
            return false;
        }

        cidade.removerPokedolares((int) custo);
        cidade.removerHabitantes((int) habitantesNecessarios);
        cidade.adicionarTreinadores(quantidade);
        return true;
    }

    public boolean construirGinasios(Cidade cidade, int quantidade) {
        if (quantidade <= 0) {
            return false;
        }
        
        long custo = (long) quantidade * PRECO_GINASIO;
        if (custo > cidade.getPokedolares()) {
            return false;
        }
        cidade.removerPokedolares((int) custo);
        cidade.adicionarGinasios(quantidade);
        return true;
    }
}