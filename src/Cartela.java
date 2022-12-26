import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Cartela {

    private int[][] numerosTabela = new int[5][5];
    private int idTabela;

    public Cartela(int id) {
        salvaTabela(id);
    }

    // salva a tabela gerada no package "cartelasgeradas"
    private void salvaTabela(int id){
        try{
            Path newFile = Paths.get("src/cartelasgeradas/cartela-" + (id+1) +".txt");
            FileWriter myWhiter = new FileWriter(newFile.toFile());
            idTabela = id+1;

            myWhiter.write(geraTabela());
            myWhiter.close();
        }catch (IOException e){
            System.out.println("Erro ao gerar tabelas");
        }
    }

    // gera uma tabela e a retorna;
    private String geraTabela(){
        StringBuilder tabela = new StringBuilder("B  I  N  G  O\n");
        int minimoColuna = 1;
        int[][] numerosSorteados = new int[5][5];

        for (int i = 0; i < 5; i++) {
            int[] numerosColuna = new int[5];
            for (int j = 0; j < 5; j++) {
                numerosColuna[j] = (sorteiaNumero(minimoColuna));
            }

            if(!repetido(numerosColuna)){
                numerosSorteados[i] = numerosColuna;
                minimoColuna += 15;
            }else{
                i--;
            }
        }
        preencheNumerosTabela(numerosSorteados);
        preencheTebelaTxt(numerosTabela, tabela);
        return tabela.toString();
    }

    // preenche o array dos numeros da tabela com os numeros sorteados
    private void preencheNumerosTabela(int[][] numeros){
        for (int i = 0; i < numeros.length; i++) {
            for (int j = 0; j < numeros.length; j++) {
                numerosTabela[j][i] = numeros[i][j];
            }
        }
        numerosTabela[2][2] = 0;
    }

    // preenche o '.txt' da tabela gerada com os numeros sorteados
    private void preencheTebelaTxt(int[][] numeros, StringBuilder tabela){
        for (int i = 0; i < numeros.length; i++) {
            for (int j = 0; j < numeros.length; j++) {
                tabela.append((String.valueOf(numeros[i][j]).length() == 1) ? "0": "").append((i == 2 && j == 2) ? "X " : numeros[i][j]).append(" ");
            }
            tabela.append("\n");
        }
    }

    // sorteia os numeros para preencher a tabela
    private int sorteiaNumero(int minimoColuna){
        Random sorteia = new Random();
        return sorteia.nextInt(15) + minimoColuna;
    }

    // verifica se o numero sorteado ja esta na tabela
    private boolean repetido(int[] coluna){
        for (int i = 0; i < coluna.length; i++) {
            for (int j = i+1; j < coluna.length; j++) {
                if(coluna[i] == coluna[j]){
                    return true;
                }
            }
        }
        return false;
    }

    // veririfa se o numero sorteado no globo esta presente na cartela e adiciona '0' na posição
    public boolean acertou(int numeroSorteado){
        for (int i = 0; i < numerosTabela.length; i++) {
            for (int j = 0; j < numerosTabela.length; j++) {
                if(numerosTabela[i][j] == numeroSorteado){
                    numerosTabela[i][j] = 0;
                    return true;
                }
            }
        }
        return false;
    }

    // verifica se tem alguma linha ganhadora
    public boolean linhaConcluida(){
        for (int i = 0; i < numerosTabela.length; i++) {
            int vidaLinha = 5;
            for (int j = 0; j < numerosTabela.length; j++) {
                if(numerosTabela[i][j] == 0 ){
                    vidaLinha--;
                }
                if (vidaLinha == 0){
                    return true;
                }
            }
        }

        return false;
    }

    // verifica se tem alguma coluna ganhadora
    public boolean colunaConcluida(){
        for (int i = 0; i < numerosTabela.length; i++) {
            int vidaColuna = 5;
            for (int j = 0; j < numerosTabela.length; j++) {
                if(numerosTabela[j][i] == 0){
                    vidaColuna--;
                }
                if (vidaColuna == 0){
                    return true;
                }
            }
        }

        return false;
    }

    // verifica se o janelão foi concluido
    public boolean janelaoConcluido(){
        int vidaJanelao = 16;
        for (int i = 0; i < numerosTabela.length; i++) {
            if (numerosTabela[0][i] == 0 && numerosTabela[4][i] == 0){
                vidaJanelao -= 2;
            }
            if (i>0 && i<numerosTabela.length-1){
                if (numerosTabela[i][0] == 0 && numerosTabela[i][4] == 0){
                    vidaJanelao -= 2;
                }
            }
        }

        return vidaJanelao == 0;
    }

    // verifica se toda a tabela ja foi concluida
    public boolean cheiaConcluida(){
        for (int i = 0; i < numerosTabela.length; i++) {
            for (int j = 0; j < numerosTabela.length; j++) {
                if(numerosTabela[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getIdTabela() {
        return idTabela;
    }
}
