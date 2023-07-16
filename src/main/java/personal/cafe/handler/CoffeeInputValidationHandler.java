package main.java.personal.cafe.handler;

import main.java.personal.cafe.validation.CoffeeInputFormatValidator;

public class CoffeeInputValidationHandler {

    CoffeeInputFormatValidator coffeeInputFormatValidator = new CoffeeInputFormatValidator();

    public void validateCoffeeInputFormat(String coffeeName, String coffeeSize, String iceOption) {

        if (!coffeeInputFormatValidator.isCoffeeNameValid(coffeeName)) {
            throw new IllegalArgumentException
                    ("존재하지 않는 커피 이름입니다. 현재 주문 가능한 커피 이름 목록: "
                            + coffeeInputFormatValidator.getCoffeeOptionList());
        }

        if (!coffeeInputFormatValidator.isCoffeeSizeValid(coffeeSize)) {
            throw new IllegalArgumentException
                    ("존재하지 않는 커피 사이즈입니다. 현재 주문 가능한 커피 사이즈 목록: "
                            + coffeeInputFormatValidator.getCoffeeOptionList());
        }

        if (!coffeeInputFormatValidator.isIcedValid(iceOption)) {
            throw new IllegalArgumentException
                    ("존재하지 않는 얼음 옵션입니다. 현재 주문 가능한 얼음 옵션 목록: "
                            + coffeeInputFormatValidator.getCoffeeOptionList());
        }

    }

}
