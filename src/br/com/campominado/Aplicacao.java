package br.com.campominado;

import br.com.campominado.modelo.Tabuleiro;
import br.com.campominado.visao.TabuleiroConsole;

public class Aplicacao {
    public static void main(String[] args) {
        new TabuleiroConsole(new Tabuleiro(6,6, 6 ));
    }
}
