package main.java.personal.cafe.entity;

import main.java.personal.cafe.constant.CoffeeName;
import main.java.personal.cafe.constant.CoffeeSize;

public class Coffee {

    CoffeeName coffeeName;
    CoffeeSize coffeeSize;
    Boolean isIced;

    public Coffee(CoffeeName coffeeName, CoffeeSize coffeeSize, Boolean isIced) {

        this.coffeeName = coffeeName;
        this.coffeeSize = coffeeSize;
        this.isIced = isIced;

    }
}
