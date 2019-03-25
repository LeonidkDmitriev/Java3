import java.util.ArrayList;
import java.util.Collections;

public class MyArrAndList<T> {


    public T[] ArrChange(T[] arr, int n1, int n2){
        T i;
        i=arr[n1];
        arr[n1]=arr[n2];
        arr[n2]=i;
        return arr;
    }
    public ArrayList<T> ArrToList(ArrayList<T> list, T[] arr){
       // Collections.addAll(list, arr);  //стандартное преобразование
        for (T st: arr) {  // преобразование циклом
            list.add(st);
        }
        return list;
    }
}
