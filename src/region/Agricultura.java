package region;

public class Agricultura {

    private static final int HECTARES_POR_HABITANTE = 5;
    private static final int RENDIMENTO_POR_HECTARE = 2;
    private static final int HABITANTES_POR_ALIMENTO = 5;
    private static final int CRESCIMENTO_PERCENTUAL = 5;
    private static final int PERCENTUAL_MINIMO = 20;
    private static final int PERCENTUAL_MAXIMO = 80;

    public int processarColheita(Cidade cidade) {
        if (cidade.isColheitaFeita()) {
            return -1;
        }

        int hectares = Math.min(cidade.getTerritorios(),
                cidade.getHabitantes() * HECTARES_POR_HABITANTE);
        int sementes = hectares / 2;

        if (sementes > cidade.getAlimentos()) {
            sementes = cidade.getAlimentos();
            hectares = sementes * 2;
        }

        cidade.removerAlimentos(sementes);
        int producao = hectares * RENDIMENTO_POR_HECTARE;
        cidade.adicionarAlimentos(producao);

        cidade.marcarColheitaFeita();
        return hectares;
    }

    public boolean distribuirAlimentos(Cidade cidade, int percentual) {
        if (percentual < PERCENTUAL_MINIMO || percentual > PERCENTUAL_MAXIMO) {
            return false;
        }

        int alimentoDistribuido = (cidade.getAlimentos() * percentual) / 100;
        cidade.removerAlimentos(alimentoDistribuido);

        int necessidade = cidade.getHabitantes() / HABITANTES_POR_ALIMENTO;
        if (alimentoDistribuido >= necessidade) {
            int crescimento = cidade.getHabitantes() * CRESCIMENTO_PERCENTUAL / 100;
            cidade.adicionarHabitantes(crescimento);
        } else {
            int deficit = necessidade - alimentoDistribuido;
            cidade.removerHabitantes(Math.min(deficit, cidade.getHabitantes()));
        }
        return true;
    }
}