package br.com.campominado.utilitario;

import br.com.campominado.modelo.Campo;

public class Utilitario {

    public static boolean validaCampoVizinho(Campo c, Campo v) {
        int diffColuna = Math.abs(c.getColuna() - v.getColuna());
        int diffLinha = Math.abs(c.getLinha() - v.getLinha());
        boolean diagonal = (c.getLinha() != v.getLinha()) && (c.getColuna() != v.getColuna());

        int soma = diffColuna + diffLinha;
        return ((soma == 1 && !diagonal) || (diagonal && soma == 2));
    };

}
