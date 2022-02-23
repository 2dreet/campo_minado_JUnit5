package br.com.campominado.modelo;

import br.com.campominado.excecao.ExplosaoException;
import br.com.campominado.utilitario.Utilitario;

import java.util.ArrayList;
import java.util.List;

public class Campo {

    private boolean minado;
    private boolean aberto;
    private boolean marcado;

    private final int linha;
    private final int coluna;

    private List<Campo> vizinhos = new ArrayList<>();

    public Campo(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public boolean adicionarVizinho(Campo vizinho) {
        final boolean deveAdicionar = Utilitario.validaCampoVizinho(this, vizinho);
        if(deveAdicionar) {
            vizinhos.add(vizinho);
        }

        return deveAdicionar;
    }

    public void alternarMarcacao() {
        if(!aberto) {
            this.marcado = !this.marcado;
        }
    }

    public boolean abrir() {
        if(!this.aberto && !this.marcado) {
            this.aberto = true;

            if(this.minado) {
                throw new ExplosaoException();
            }

            if(vizinhancaSegura()){
                vizinhos.stream().forEach(Campo::abrir);
            }

            return true;
        }

        return false;
    }

    // aqui o noneMatch quer dizer que nenhum pode ser verdadeiro
    public boolean vizinhancaSegura() {
        return vizinhos.stream().noneMatch(v -> v.minado);
    }

    public boolean objetivoAlcancado() {
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;
        return desvendado || protegido;
    }

    public Long minasNaVizinhanca() {
        return vizinhos.stream().filter(v -> v.isMinado()).count();
    }

    public void reiniciar() {
        this.marcado = false;
        this.minado = false;
        this.aberto = false;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    public void minar() {
        this.minado = true;
    }

    public int getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }

    public boolean isMinado() {
        return minado;
    }

    public boolean isAberto() {
        return aberto;
    }

    public boolean isFechado() {
        return !aberto;
    }

    public boolean isMarcado() {
        return marcado;
    }

    public List<Campo> getVizinhos() {
        return vizinhos;
    }

    public String toString() {
        if(marcado) {
            return "x";
        } else if (aberto && minado) {
            return "*";
        } else if (aberto && minasNaVizinhanca() > 0) {
            return Long.toString(minasNaVizinhanca());
        } else if (aberto) {
            return " ";
        } else {
            return "?";
        }
    }
}
