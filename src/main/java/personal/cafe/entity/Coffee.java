package personal.cafe.entity;

import personal.cafe.constant.CoffeeName;
import personal.cafe.constant.CoffeeSize;

public class Coffee {

    private CoffeeName coffeeName;
    private CoffeeSize coffeeSize;
    private Boolean isIced;

    private Coffee(CoffeeName coffeeName, CoffeeSize coffeeSize, Boolean isIced) {

        this.coffeeName = coffeeName;
        this.coffeeSize = coffeeSize;
        this.isIced = isIced;

    }

    public static Coffee createCoffee(CoffeeName coffeeName, CoffeeSize coffeeSize, Boolean isIced) {
        return new Coffee(coffeeName, coffeeSize, isIced);
    }

    public CoffeeName getCoffeeName() {
        return coffeeName;
    }

    public CoffeeSize getCoffeeSize() {
        return coffeeSize;
    }

    public Boolean isIced() {
        return isIced;
    }

}
