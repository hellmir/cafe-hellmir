package main.java.personal.cafe;

import main.java.personal.cafe.entity.Barista;
import main.java.personal.cafe.entity.Customer;
import main.java.personal.cafe.entity.Wallet;

import java.util.Scanner;

public class CafeApplication {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Customer customer = new Customer(sc.next(), new Wallet(0));

        customer.orderCoffee(sc.nextInt(), new Barista(sc.next()), sc.next(), sc.next(), sc.next());

    }

}
