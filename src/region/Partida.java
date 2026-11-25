package region;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Partida {

    public static final int MAXIMO_JOGADORES = 4;
    private static final int ANO_FINAL = 1996; 

    private final List<Cidade> cidades;
    private int indiceAtual;

    public Partida() {
        this.cidades = new ArrayList<>();
        this.indiceAtual = 0;
    }

    public boolean adicionarCidade(Cidade cidade) {
        if (cidade == null || cidades.size() >= MAXIMO_JOGADORES) {
            return false;
        }

        for (Cidade existente : cidades) {
            if (existente.getNome().equals(cidade.getNome())) {
                return false;
            }
        }

        cidades.add(cidade);
        return true;
    }

    public List<Cidade> getCidades() {
        return Collections.unmodifiableList(cidades);
    }

    public Cidade getCidadeAtual() {
        if (cidades.isEmpty()) {
            return null;
        }

        return cidades.get(indiceAtual);
    }

    public int getAnoAtual() {
        if (cidades.isEmpty()) {
            return 0;
        }

        return cidades.get(0).getAnoAtual();
    }

    public int getAnoFinal() {
        return ANO_FINAL;
    }

    public boolean passarTurno() {
        indiceAtual++;
        if (indiceAtual >= cidades.size()) {
            indiceAtual = 0;
            return true;
        }

        return false;
    }

    public List<Cidade> encerrarAno(Nobreza nobreza) {
        List<Cidade> promovidas = new ArrayList<>();
        for (Cidade cidade : cidades) {
            cidade.avancarAno();
            if (nobreza.verificarPromocao(cidade)) {
                promovidas.add(cidade);
            }
        }
        return promovidas;
    }

    public boolean terminou() {
        for (Cidade cidade : cidades) {
            if (cidade.getDono().ehMonarca()) {
                return true;
            }
        }
        return getAnoAtual() > ANO_FINAL;
    }

    public Cidade determinarVencedor(Nobreza nobreza) {
        if (cidades.isEmpty()) {
            return null;
        }
        
        Cidade melhor = cidades.get(0);
        for (int i = 1; i < cidades.size(); i++) {
            Cidade candidata = cidades.get(i);
            int nivelCandidata = candidata.getDono().getNivelTitulo();
            int nivelMelhor = melhor.getDono().getNivelTitulo();
            if (nivelCandidata > nivelMelhor
                    || (nivelCandidata == nivelMelhor
                        && nobreza.calcularPrestigio(candidata) > nobreza.calcularPrestigio(melhor))) {
                melhor = candidata;
            }
        }
        return melhor;
    }
}