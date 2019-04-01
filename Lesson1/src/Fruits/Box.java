package Fruits;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> fruitList;

    private double totalWeight;

    public Box() {
        this.fruitList = new ArrayList<>();
    }

    public void addFruit(T fruit) {  //добавляем фрукты
        fruitList.add(fruit);

        totalWeight = totalWeight + fruit.getWeight();
    }

    public double getTotalWeight() {  //узнаем вес
        return totalWeight;
    }

    public boolean compare(Box box) { //сравниваем вес коробок
        return (this.getTotalWeight() == box.getTotalWeight ());
    }

    public Box ToAnotherBox(Box boxNew) { //пересыпаем фрукты
        boxNew.fruitList.addAll(this.fruitList);
        this.fruitList.clear();
        return boxNew;
    }
}