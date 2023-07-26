package personal.cafe;

import personal.cafe.constant.CoffeeName;
import personal.cafe.constant.CoffeeSize;
import personal.cafe.entity.Barista;
import personal.cafe.entity.Customer;
import personal.cafe.entity.Menu;
import personal.cafe.entity.Wallet;
import personal.cafe.handler.CoffeeInputValidationHandler;

import java.util.Scanner;

public class CafeApplication {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {

            String customerName = sc.next();
            Customer customer = new Customer(customerName, Wallet.createWallet());

            Integer moneyToCharge = sc.nextInt(); // moneyToCharge > 0
            String baristaName = sc.next();
            String coffeeName = sc.next(); // coffeeName = "AMERICANO" | "CAFFE_LATTE"
            String coffeeSize = sc.next(); // coffeeSize = "TALL" | "GRANDE" | "VENTI"
            String iceOption = sc.next(); // iceOption = "ICE" | "HOT"

            new CoffeeInputValidationHandler().validateCoffeeInputFormat(coffeeName, coffeeSize, iceOption);

            Menu menu = Menu.createMenu
                    (CoffeeName.valueOf(coffeeName), CoffeeSize.valueOf(coffeeSize), iceOption.equals("ICE"));

            customer.orderCoffee(moneyToCharge, new Barista(baristaName), menu);

        }

    }

}
