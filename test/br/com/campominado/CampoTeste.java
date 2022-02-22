package br.com.campominado;

import br.com.campominado.excecao.ExplosaoException;
import br.com.campominado.modelo.Campo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CampoTeste {

    private Campo campo;

    // para cada teste vai rodar essa funcao
    @BeforeEach
    void iniciarCampo() {
        campo = new Campo(3 ,3);
    }

    @Test
    void testeVizinhoVerdadeiro() {
        Campo vizinho = new Campo(3, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoVerdadeiro2() {
        Campo vizinho = new Campo(3, 4);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoVerdadeiro3() {
        Campo vizinho = new Campo(2, 3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoVerdadeiro4() {
        Campo vizinho = new Campo(4, 3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoVerdadeiro5() {
        Campo vizinho = new Campo(2, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoVerdadeiro6() {
        Campo vizinho = new Campo(4, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoVerdadeiro7() {
        Campo vizinho = new Campo(2, 4);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoVerdadeiro8() {
        Campo vizinho = new Campo(4, 4);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoFalso() {
        Campo vizinho = new Campo(2, 5);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(!resultado);
    }

    @Test
    void testeValorPadraoNaoMarcado() {
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAlterarMarcacao() {
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado());
    }

    @Test
    void testeAlterarMarcacao2() {
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAbrirCampoNaoMinadoNaoMarcado() {
        assertTrue(campo.abrir());
    }

    @Test
    void testeAbrirCampoNaoMinadoMarcado() {
        campo.alternarMarcacao();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirCampoMinadoMarcado() {
        campo.alternarMarcacao();
        campo.minar();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirCampoMinadoNaoMarcado() {
        campo.minar();
        assertThrows(ExplosaoException.class, () -> {
            campo.abrir();
        });
    }

    @Test
    void testeAbrirComVizinhos1() {
        Campo campo22 = new Campo(2, 2);
        Campo campo11 = new Campo(1, 1);

        campo22.adicionarVizinho(campo11);
        campo.adicionarVizinho(campo22);

        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isAberto());
    }

    @Test
    void testeAbrirComVizinhos2() {
        Campo campo22 = new Campo(2, 2);
        Campo campo11 = new Campo(1, 1);
        Campo campo12 = new Campo(1, 2);

        campo11.minar();

        campo22.adicionarVizinho(campo11);
        campo22.adicionarVizinho(campo12);
        campo.adicionarVizinho(campo22);

        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isFechado());
    }

    @Test
    void testeObjetivoAlcancado() {
        Campo campo22 = new Campo(2, 2);
        Campo campo11 = new Campo(1, 1);
        Campo campo12 = new Campo(1, 2);

        campo11.minar();

        campo22.adicionarVizinho(campo11);
        campo22.adicionarVizinho(campo12);
        campo.adicionarVizinho(campo22);

        campo.abrir();

        assertTrue(campo.objetivoAlcancado());
    }

    @Test
    void testeMinasNaVizinhanca() {
        Campo campo22 = new Campo(2, 2);
        campo22.minar();

        campo.adicionarVizinho(campo22);

        campo.abrir();

        assertEquals(1, campo.minasNaVizinhanca());
    }

    @Test
    void testeToString() {
        Campo campo22 = new Campo(2, 2);
        campo22.minar();

        campo.adicionarVizinho(campo22);

        campo.abrir();

        assertEquals("1", campo.toString());
    }

    @Test
    void testeToStringMarcado() {
        campo.alternarMarcacao();
        assertEquals("x", campo.toString());
    }

    @Test
    void testeToStringExplodidoAberto() {
        campo.minar();
        assertThrows(ExplosaoException.class, () -> {
            campo.abrir();
        });

        assertEquals("*", campo.toString());
    }

    @Test
    void testeToStringAberto() {
        campo.abrir();

        assertEquals(" ", campo.toString());
    }

    @Test
    void testeToStringPraEscolher() {
        assertEquals("?", campo.toString());
    }


    @Test
    void testeReiniciar() {
        Campo campo22 = new Campo(2, 2);
        campo22.minar();

        campo.adicionarVizinho(campo22);

        campo.abrir();

        campo.reiniciar();

        assertEquals(false, campo.isAberto());
        assertEquals(false, campo.isMarcado());
        assertEquals(false, campo.isMinado());
    }

    @Test
    void testeVizinhoQuatidade() {
        campo.adicionarVizinho(new Campo(2, 2));
        campo.adicionarVizinho(new Campo(3, 2));
        campo.adicionarVizinho(new Campo(4, 2));
        campo.adicionarVizinho(new Campo(4, 3));
        campo.adicionarVizinho(new Campo(2, 3));

        assertEquals(5, campo.getVizinhos().size());
    }

}
