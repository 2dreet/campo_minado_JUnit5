package br.com.campominado.visao;

import br.com.campominado.excecao.ExplosaoException;
import br.com.campominado.excecao.SairException;
import br.com.campominado.modelo.Tabuleiro;

import java.util.Scanner;

public class TabuleiroConsole {
    private Tabuleiro tabuleiro;
    Scanner sc = new Scanner(System.in);

    public TabuleiroConsole(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;

        executarJogo();


    }

    private void executarJogo() {
        try {

                boolean continuar = true;
                while(continuar) {
                    System.out.println("Outra partida? (S/n) ");
                    String resposta = sc.nextLine();

                    if(resposta.equalsIgnoreCase("n")) {
                        continuar = false;
                    } else {
                       cicloDoJogo();
                    }
                }

        } catch (SairException e) {
            System.out.println("Obrigado por jogar!!!");
        } finally {
            sc.close();
        }
    }

    private void cicloDoJogo() {
        try {
            tabuleiro.reiniciar();
            while (!tabuleiro.objetivoAlcancado()) {
                System.out.println(tabuleiro.toString());

                String operacao = capturarValor("(A) Abrir | (M) Marcar | (S) Sair: ");

                int linha = Integer.parseInt(capturarValor("Digite a linha: "));

                int coluna = Integer.parseInt(capturarValor("Digite a coluna: "));

                if (operacao.equalsIgnoreCase("a")) {
                    tabuleiro.abrir(linha, coluna);
                } else if (operacao.equalsIgnoreCase("m")) {
                    tabuleiro.alternarMarcacao(linha, coluna);
                }
            }

            System.out.println("Voce ganhou!");
            System.out.print(tabuleiro.toString());
        } catch (ExplosaoException e) {
            System.out.println("Voce perdeu!");
            System.out.print(tabuleiro.toString());
        }
    }

    private String capturarValor(String texto) {
        System.out.println(texto);
        String valor =  sc.nextLine();
        if(valor.equalsIgnoreCase("s") || valor.equalsIgnoreCase("sair")) {
            throw new SairException();
        }

        return valor;
    }


}
