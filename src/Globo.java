import java.util.*;

public class Globo {

    private static int[] numerosJaSorteados = new int[75];
    private static int i = 0;
    private static Random random = new Random();

    //sorteia um numero verificando se ele é repetido, caso não seja, o armazena no array de numeros ja sorteados
    public static int sorteiaNumero(){
        int num;
        while(true){
            num = random.nextInt(75)+1;
            boolean repetido = false;

            for (int numerosJaSorteado : numerosJaSorteados) {
                if (numerosJaSorteado != 0 && numerosJaSorteado == num) {
                    repetido = true;
                    break;
                }
            }

            if (!repetido || (i+1 == 75)){
                break;
            }
        }

        if (i+1 < 75){
            numerosJaSorteados[i] = num;
            i++;
            return num;
        }else{
            return 0;
        }
    }

    public static void setNumerosJaSorteados(int[] numerosJaSorteados) {
        Globo.numerosJaSorteados = numerosJaSorteados;
    }

    public static void setI(int i) {
        Globo.i = i;
    }
}
