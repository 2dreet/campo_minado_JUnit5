package br.com.campominado;

import br.com.campominado.excecao.ExplosaoException;
import br.com.campominado.modelo.Tabuleiro;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TabuleiroTeste {

    Tabuleiro tabuleiro;

    @Test
    void testeAbrirExplosao() {
        tabuleiro = new Tabuleiro(1,1, 1);
        tabuleiro.reiniciar();
        assertThrows(ExplosaoException.class, () -> {
            tabuleiro.abrir(0 ,0);
        });
    }

    @Test
    void testeAlterarMarcacao() {
        tabuleiro = new Tabuleiro(1,1, 1);
        tabuleiro.reiniciar();
        tabuleiro.alternarMarcacao(0,0);
        assertTrue(tabuleiro.objetivoAlcancado());
    }

    @Test
    void testeToString() {
        tabuleiro = new Tabuleiro(1,1, 1);
        tabuleiro.reiniciar();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("    0\n");
        stringBuilder.append("  0 ? \n");

        assertEquals(stringBuilder.toString(), tabuleiro.toString());
    }

}
