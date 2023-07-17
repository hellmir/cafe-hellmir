package main.java.personal.cafe.entity;

import main.java.personal.cafe.constant.CoffeeName;
import main.java.personal.cafe.constant.CoffeeSize;

public class Menu {

    private CoffeeName coffeeName;
    private Integer price;
    private CoffeeSize coffeeSize;
    private Boolean isIced;

    private Menu(CoffeeName coffeeName, CoffeeSize coffeeSize, Boolean isIced) {

        this.coffeeName = coffeeName;
        price = coffeeName.getPrice();

        this.coffeeSize = coffeeSize;
        price += coffeeSize.getPrice();

        this.isIced = isIced;

    }

    public static Menu createMenu(CoffeeName coffeeName, CoffeeSize coffeeSize, Boolean isIced) {
        return new Menu(coffeeName, coffeeSize, isIced);
    }

    public CoffeeName getCoffeeName() {
        return coffeeName;
    }

    public Integer getPrice() {
        return price;
    }

    public CoffeeSize getCoffeeSize() {
        return coffeeSize;
    }

    public Boolean isIced() {
        return isIced;
    }

}
