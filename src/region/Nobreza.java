package region;

public class Nobreza {

    private static final int PESO_GINASIO = 10;
    private static final int PESO_TREINADOR = 2;
    private static final int PESO_HABITANTE = 1;
    private static final int HECTARES_POR_PONTO = 100;

    private static final int[] PRESTIGIO_MINIMO = {0, 400, 520, 640, 760, 880, 1000};

    public int calcularPrestigio(Cidade cidade) {
        return cidade.getGinasios() * PESO_GINASIO
                + cidade.getTreinadores() * PESO_TREINADOR
                + cidade.getHabitantes() * PESO_HABITANTE
                + cidade.getTerritorios() / HECTARES_POR_PONTO;
    }

    public int prestigioParaProximoTitulo(Jogador jogador) {
        if (jogador.ehMonarca()) {
            return -1;
        }

        return PRESTIGIO_MINIMO[jogador.getNivelTitulo() + 1];
    }

    public boolean verificarPromocao(Cidade cidade) {
        Jogador dono = cidade.getDono();
        int necessario = prestigioParaProximoTitulo(dono);
        if (necessario == -1) {
            return false;
        }

        if (calcularPrestigio(cidade) >= necessario) {
            return dono.promover();
        }
        
        return false;
    }
}