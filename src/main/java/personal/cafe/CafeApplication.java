package main.java.personal.cafe;

import main.java.personal.cafe.entity.Barista;
import main.java.personal.cafe.entity.Customer;
import main.java.personal.cafe.entity.Wallet;

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

            customer.orderCoffee(moneyToCharge, new Barista(baristaName), coffeeName, coffeeSize, iceOption);

        }

    }

}
