import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jogo {
    private List<Cartela> cartela = new ArrayList<>();
    private char tipoDeJogo;
    private int idTabelaGanhadora = 0;

    //prenche as cartelas
    public void preencheCartelas(int quantidade){
        for (int i = 0; i < quantidade; i++) {
            cartela.add(new Cartela(i));
        }
    }

    //loop do jogo, é finalizado o looping quando alguma cartela vence
    public void loopGame() {
        do {
            System.out.print("\nPressione enter para sortear um numero   ");

            Scanner scanner = new Scanner(System.in);
            String key = scanner.nextLine();

            if(key!=null && key.isEmpty()) {
                int numeroSorteado = Globo.sorteiaNumero();
                boolean acertou = false;

                System.out.println((numeroSorteado != 0)? "\nNumero Sorteado : " + numeroSorteado + "\n" : "");

                for (Cartela cartelaAtual : cartela) {
                    if(cartelaAtual.acertou(numeroSorteado)){
                        System.out.println("Acertou na tabela " + cartelaAtual.getIdTabela());
                        acertou = true;
                    }
                }

                if(!acertou){
                    System.out.println("Errou em todas as tabelas ");
                }
            }else{
                System.out.println("\nEntrada invalida");
            }

        }while(!ganhouJogo());

        System.out.println("\nParabens a cartela " + idTabelaGanhadora + " foi a ganhadora");
    }

    //veririfa se a cartela vencedora de acorto com o tipo de jogo escolhido
    private boolean ganhouJogo(){
        for (Cartela cartelaAtual : cartela) {
            if (tipoDeJogo == 'a' && cartelaAtual.linhaConcluida() || tipoDeJogo == 'b' && cartelaAtual.colunaConcluida() || tipoDeJogo == 'c' && cartelaAtual.janelaoConcluido() || tipoDeJogo == 'd' && cartelaAtual.cheiaConcluida()) {
                idTabelaGanhadora = cartelaAtual.getIdTabela();
                return true;
            }
        }
        return false;
    }

    public void setTipoDeJogo(char tipoDeJogo) {
        this.tipoDeJogo = tipoDeJogo;
    }

    public void setCartela(List<Cartela> cartela) {
        this.cartela = cartela;
    }
}
