package TaskArray;

import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        // исходный массив для отсечения после последней 4 и для проверки наличия 4 и 1
        //int[] arrLong = {1,1,1,4,1,1,4,1,1,1,1};  //корректный вариант
//        int[] arrLong = {0,2,2,3,2,2,5,3,0,0};  //некорректный вариант - отсутствует 4 и 1
//        int[] arr = clippingArray(arrLong);
//        for (int i =0; i<arr.length; i++) {   // выведем результат отсечения
//            System.out.print(arr [i] + " ");
//        }
//
//        System.out.println("\n" + isOneWihtFour(arrLong)); // результат метода о наличии 1 или 4

}

    // отсечение элементов массива после последней 4
    public int[] clippingArray(int[] arrLong){
        int[] arrShort;
       if ( IntStream.of(arrLong).anyMatch(x -> x == 4)) { // проверим наличие 4 в массиве
            int i=arrLong.length-1;   //будем просматривать массив с конца пока не наткнемся на 4
            while (arrLong[i]!=4) {
              i--;
            }
            arrShort = Arrays.copyOfRange(arrLong,i+1, arrLong.length);
           return arrShort;
        }
        else {
           logger.info("Массив не содержит ни одной 4");  // помимо ошибки еще и в лог заишем
           throw new RuntimeException("Массив не содержит ни одной 4");
           }

    }

    // проверка на наличие 1 или 4
    public  boolean isOneWihtFour(int[] arrLong){
        for (int i = 0; i < arrLong.length-1; i++) {
            //  если есть хоть одна 1 из 4 или одна 4 из 1 они будут граничить друг с другом
            if (((arrLong[i]==4)&&(arrLong[i+1]==1))||((arrLong[i]==1)&&(arrLong[i+1]==4)))
                return true;
        }
    return false;
    }
}
