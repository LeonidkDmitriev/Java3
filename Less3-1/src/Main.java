import Fruits.Apple;
import Fruits.Box;
import Fruits.Orange;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
/*********задания 1 и 2************/
      MyArrAndList a = new MyArrAndList(); //создадим объект a
      Integer myArr[] = new Integer[5];    // пусть наш массив будет целочисленный
      ArrayList<Integer> myList = new ArrayList<>(); // создадим список
      System.out.println(" Массив до преобразования ");
        for (int i = 0; i < 5; i++) {  //заполним массив числами от 0 до 4
            myArr[i] = i;
            System.out.print(" " + myArr[i]);
        }


      myArr =  (Integer[]) a.ArrChange(myArr,0,1); //поменяем в массиве элементы
      myList =  a.ArrToList(myList,myArr); //преобразуем массив в список
      System.out.println("\n Наш список ");
        for (int i = 0; i < 5; i++) {  //выведим список
            System.out.print(" " + myList.get(i));

        }

/*****************большое задание*********************************/
        Box<Apple> appleBox = new Box<>();
        for(int i = 0; i <5; i++){               //положим 5 яблок
            appleBox.addFruit(new Apple());
        }
        System.out.print("\n коробка с яблоками весит "+appleBox.getTotalWeight());//их вес

      //  appleBox.addFruit(new Orange()); // высвечивается ошибка

        Box<Orange> orangeBox = new Box<>();
        for(int i = 0; i <3; i++){               //положим 3 лепесина
            orangeBox.addFruit(new Orange());
        }
        System.out.print("\n коробка с грушами весит "+orangeBox.getTotalWeight());//их вес

        //проверено - если положить 10 апельсинов и 15 яблок то будет истина
        System.out.print("\n Сравним вес коробок " +  orangeBox.compare(appleBox));

        Box<Apple> appleBox2 = new Box<>();
        appleBox2 = appleBox.ToAnotherBox(appleBox);

        //убедимся, что яблоки пересыпались в новую коробку
        System.out.print("\n вес новой коробки " + appleBox2.getTotalWeight());


    }
}
