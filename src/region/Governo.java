package region;

public class Governo {

    public static final String NIVEL_JUSTA = "Justa";
    public static final String NIVEL_MODERADA = "Moderada";
    public static final String NIVEL_SEVERA = "Severa";

    private static final int TAXA_MAXIMA = 50;
    private static final double MULTIPLICADOR_JUSTA = 0.8;
    private static final double MULTIPLICADOR_MODERADA = 1.0;
    private static final double MULTIPLICADOR_SEVERA = 1.3;

    public int arrecadarTaxas(Cidade cidade) {
        if (cidade.isTaxasArrecadadas()) {
            return -1;
        }

        int base = cidade.getHabitantes() * cidade.getTaxaLiga() / 100;
        double multiplicador = multiplicadorPara(cidade.getRegulamentacao());
        int arrecadado = (int) (base * multiplicador);
        cidade.adicionarPokedolares(arrecadado);

        cidade.marcarTaxasArrecadadas();
        return arrecadado;
    }

    public boolean definirTaxaLiga(Cidade cidade, int taxa) {
        if (taxa < 0 || taxa > TAXA_MAXIMA) {
            return false;
        }
        cidade.setTaxaLiga(taxa);
        return true;
    }

    public boolean definirRegulamentacao(Cidade cidade, String nivel) {
        if (!NIVEL_JUSTA.equals(nivel)
                && !NIVEL_MODERADA.equals(nivel)
                && !NIVEL_SEVERA.equals(nivel)) {
            return false;
        }
        cidade.setRegulamentacao(nivel);
        return true;
    }

    private double multiplicadorPara(String nivel) {
        if (NIVEL_JUSTA.equals(nivel)) {
            return MULTIPLICADOR_JUSTA;
        }
        if (NIVEL_SEVERA.equals(nivel)) {
            return MULTIPLICADOR_SEVERA;
        }
        return MULTIPLICADOR_MODERADA;
    }
}