package main.java.personal.cafe.validation;

import main.java.personal.cafe.constant.CoffeeName;
import main.java.personal.cafe.constant.CoffeeSize;

import java.util.ArrayList;
import java.util.List;

public class CoffeeInputFormatValidator {

    private List<String> coffeeOptionList;

    public List<String> getCoffeeOptionList() {
        return coffeeOptionList;
    }

    public boolean isCoffeeNameValid(String coffeeName) {

        coffeeOptionList = new ArrayList<>();

        for (CoffeeName name : CoffeeName.values()) {

            if (name.name().equals(coffeeName)) {
                return true;
            }

            coffeeOptionList.add(name.name());

        }

        return false;

    }

    public boolean isCoffeeSizeValid(String coffeeSize) {

        coffeeOptionList = new ArrayList<>();

        for (CoffeeSize size : CoffeeSize.values()) {

            if (size.name().equals(coffeeSize)) {
                return true;
            }

            coffeeOptionList.add(size.name());

        }

        return false;

    }

    public boolean isIcedValid(String iceOption) {

        coffeeOptionList = new ArrayList<>();

        if (iceOption.equals("ICE") || iceOption.equals("HOT")) {
            return true;
        }

        coffeeOptionList.add("ICE");
        coffeeOptionList.add("HOT");

        return false;

    }

}
