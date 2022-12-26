import java.util.ArrayList;
import java.util.Scanner;

public class BingoApp {
    private static Scanner ler = new Scanner(System.in);
    private static Jogo jogo = new Jogo();
    private static boolean gerouCartelas = false;

    public static void main(String[] args){
        menuInicial();
    }

    //exibe o menu inicial
    static void menuInicial(){
        System.out.println("\n----------------------------------------------------");
        System.out.println("                        BINGO                       ");
        System.out.println("----------------------------------------------------");
        System.out.println("\n1 - Gerar Cartelas ");
        System.out.println("2 - Iniciar Jogo   ");
        System.out.println("3 - Sair");
        System.out.print("\nEscolha uma das opções acima: ");

        switch (ler.next()) {
            case "1" -> menuGeradorCartelas();
            case "2" -> submenuJogo();
            case "3" -> System.exit(0);
            default -> {
                System.out.println("\nEntrada Invalida");
                menuInicial();
            }
        }
    }

    //gera a quantidade de cartelas desejada pelo jogador
    static void menuGeradorCartelas(){
        System.out.print("\nInsira quantas cartelas deseja gerar : ");
        String qte = ler.next();

        for (int i = 0; i < qte.length(); i++) {
            if(!Character.isDigit(qte.charAt(i))){
                System.out.println("\nEntrada Invalida");
                menuGeradorCartelas();
                return;
            }
        }

        gerouCartelas = true;
        jogo.preencheCartelas(Integer.parseInt(qte));
        menuInicial();
    }

    //exibe o submenu para o usuario definir o tipo de jogo e inicia o loop do game
    static void submenuJogo(){
        if (!gerouCartelas){
            System.out.println("\nNenhuma cartela foi gerada");
            menuInicial();
            return;
        }

        while (true){
            System.out.println("\na. Linha   - que dá vitória a cartela que concluir a linha. ");
            System.out.println("b. Coluna  - que dá vitória a cartela que concluir a coluna.");
            System.out.println("c. Janelão - que dá a vitória a cartela que completar as linhas e colunas dos extremos.");
            System.out.println("d. Cheia   - que dá a vitória a cartela que completar todos os números.");
            System.out.print("\nEscolha Uma dos tipos de jogo listados acima: ");
            String tipo = ler.next().toLowerCase();

            if(tipo.charAt(0) >= 'a' && tipo.charAt(0)< 'e' && tipo.length() == 1){
                jogo.setTipoDeJogo(tipo.charAt(0));
                break;
            }else{
                System.out.println("\nEntrada Invalida");
            }
        }
        jogo.loopGame();
        novoJogo();
    }

    //reinicia as variaveis do jogo depois que uma partida acabar
    private static void novoJogo(){
        jogo.setCartela(new ArrayList<>());
        Globo.setNumerosJaSorteados(new int[75]);
        Globo.setI(0);
        gerouCartelas = false;
        menuInicial();
    }
}