package region;

import java.util.Random;

public class Eventos {

    private final Random random;

    public Eventos() {
        this.random = new Random();
    }

    public String tentarEventoAleatorio(Cidade cidade) {
        if (random.nextInt(10) >= 4) {
            return null;
        }

        int tipoEvento = random.nextInt(6);
        return executarEvento(cidade, tipoEvento);
    }

    public String executarEvento(Cidade cidade, int tipoEvento) {
        switch (tipoEvento) {
            case 0: 
                int ginasiosDestruidos = Math.min(5, cidade.getGinasios());
                if (ginasiosDestruidos > 0) {
                    cidade.removerGinasios(ginasiosDestruidos);
                    return "[EVENTO POKEMON] Um Tyranitar selvagem apareceu e, com sua ira, destruiu "
                            + ginasiosDestruidos + " ginasio(s)!";
                } 
                
                else {
                    int prejuizo = Math.min(150, cidade.getPokedolares());
                    cidade.removerPokedolares(prejuizo);
                    return "[EVENTO POKEMON] Um Tyranitar selvagem atacou a cidade! Como nao havia ginasios, "
                            + "o conserto das ruas custou " + prejuizo + " Pokedolares.";
                }

            case 1: 
                cidade.adicionarAlimentos(100);
                return "[EVENTO] Um grupo de Bulbasaur passou pela cidade e ajudou na colheita! (+100 alimentos)";

            case 2: 
                cidade.adicionarPokedolares(300);
                return "[EVENTO] Um Meowth usou Pay Day na praca central deixando moedas por toda parte! (+300 Pokedolares)";

            case 3:
                int comidaRoubada = Math.min(120, cidade.getAlimentos());
                cidade.removerAlimentos(comidaRoubada);
                return "[EVENTO] Um Snorlax dormiu no meio do celeiro e devorou "+ comidaRoubada + " unidades de alimentos!";

            case 4:
                cidade.adicionarHabitantes(15);
                cidade.adicionarTreinadores(3);
                return "[EVENTO] Um festival com Pikachu atraiu visitantes de toda Kanto! (+15 habitantes e +3 treinadores)";

            case 5: 
                int roubo = Math.min(250, cidade.getPokedolares());
                cidade.removerPokedolares(roubo);
                return "[EVENTO] A Equipe Rocket invadiu o cofre municipal durante a noite! (-" + roubo + " Pokedolares)";

            default:
                return null;
        }
    }
}