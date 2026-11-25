package region;

public class Mercado {

    private static final int PRECO_ALIMENTO = 2;
    private static final int PRECO_TERRITORIO = 1;

    public int getPrecoAlimento() {
        return PRECO_ALIMENTO;
    }

    public int getPrecoTerritorio() {
        return PRECO_TERRITORIO;
    }

    public boolean comprarAlimentos(Cidade cidade, int quantidade) {
        if (quantidade <= 0) {
            return false;
        }
        int custo = quantidade * PRECO_ALIMENTO;
        if (!cidade.removerPokedolares(custo)) {
            return false;
        }
        cidade.adicionarAlimentos(quantidade);
        return true;
    }

    public boolean venderAlimentos(Cidade cidade, int quantidade) {
        if (quantidade <= 0) {
            return false;
        }
        if (!cidade.removerAlimentos(quantidade)) {
            return false;
        }
        int receita = quantidade * PRECO_ALIMENTO;
        cidade.adicionarPokedolares(receita);
        return true;
    }

    public boolean comprarTerritorios(Cidade cidade, int quantidade) {
        if (quantidade <= 0) {
            return false;
        }
        int custo = quantidade * PRECO_TERRITORIO;
        if (!cidade.removerPokedolares(custo)) {
            return false;
        }
        cidade.adicionarTerritorios(quantidade);
        return true;
    }

    public boolean venderTerritorios(Cidade cidade, int quantidade) {
        if (quantidade <= 0) {
            return false;
        }
        if (!cidade.removerTerritorios(quantidade)) {
            return false;
        }
        int receita = quantidade * PRECO_TERRITORIO;
        cidade.adicionarPokedolares(receita);
        return true;
    }
}