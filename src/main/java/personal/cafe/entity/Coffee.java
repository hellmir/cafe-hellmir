package main.java.personal.cafe.entity;

import main.java.personal.cafe.constant.CoffeeName;
import main.java.personal.cafe.constant.CoffeeSize;

public class Coffee {

    CoffeeName coffeeName;
    CoffeeSize coffeeSize;
    Boolean isIced;

    private Coffee(CoffeeName coffeeName, CoffeeSize coffeeSize, Boolean isIced) {

        this.coffeeName = coffeeName;
        this.coffeeSize = coffeeSize;
        this.isIced = isIced;

    }

    public static Coffee createCoffee(CoffeeName coffeeName, CoffeeSize coffeeSize, Boolean isIced) {
        return new Coffee(coffeeName, coffeeSize, isIced);
    }

}
