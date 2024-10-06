package machine;

import java.util.Scanner;
import machine.States;

public class CoffeeMachine {
    public static int currentWater = 400;
    public static int currentMilk = 540;
    public static int currentBeans = 120;
    public static int currentCups = 9;
    public static int currentMoney = 550;

    public static int cupsOfCoffeeMade = 0;

    public static States machineState = States.CLEAN;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean exitLoop = false;

        while (!exitLoop) {
            System.out.println("Write action (buy, fill, take, clean, remaining, exit):");
            String action = sc.next();

            if (machineState.equals(States.DIRTY)) {
                if (!action.equals("clean")) {
                    System.out.println("I need cleaning!");
                }
                else {
                    cupsOfCoffeeMade = 0;
                    machineState = States.CLEAN;
                    System.out.println("I have been cleaned!");
                }
                continue;
            }

            switch (action) {
                case "buy":
                    buyCoffee(sc);
                    break;
                case "fill":
                    fill(sc);
                    break;
                case "take":
                    take();
                    break;
                case "remaining":
                    remaining();
                    break;
                case "exit":
                    exitLoop = true;
                    break;
            }
        }
        sc.close();
    }

    public static void buyCoffee(Scanner sc) {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String coffee_type = sc.next();
        int requiredWater = 0, requiredMilk = 0, requiredBeans = 0, requiredCup = 1, cost = 0;

        switch (coffee_type) {
            case "1":
                requiredWater = 250;
                requiredBeans = 16;
                cost = 4;
                break;
            case "2":
                requiredWater = 350;
                requiredMilk = 75;
                requiredBeans = 20;
                cost = 7;
                break;
            case "3":
                requiredWater = 200;
                requiredMilk = 100;
                requiredBeans = 12;
                cost = 6;
                break;
            case "back":
                return;
        }

        if (currentWater >= requiredWater && currentMilk >= requiredMilk
                && currentBeans >= requiredBeans && currentCups >= requiredCup) {

            System.out.println("I have enough resources, making you a coffee!");

            currentWater -= requiredWater;
            currentMilk -= requiredMilk;
            currentBeans -= requiredBeans;
            currentCups -= requiredCup;
            currentMoney += cost;

            cupsOfCoffeeMade++;
            if (cupsOfCoffeeMade >= 10) {
                machineState = States.DIRTY;
            }
        }
        else {
            if (currentWater < requiredWater) {
                System.out.println("Sorry, not enough water!");
            }
            else if (currentMilk < requiredMilk) {
                System.out.println("Sorry, not enough milk!");
            }
            else if (currentBeans < requiredBeans) {
                System.out.println("Sorry, not enough beans!");
            }
            else if (currentCups < requiredCup) {
                System.out.println("Sorry, not enough cups!");
            }
        }
    }

    public static void fill(Scanner sc) {
        System.out.println("Write how many ml of water you want to add:");
        int water = sc.nextInt();
        currentWater += water;

        System.out.println("Write how many ml of milk you want to add:");
        int milk = sc.nextInt();
        currentMilk += milk;

        System.out.println("Write how many grams of coffee beans you want to add:");
        int beans = sc.nextInt();
        currentBeans += beans;

        System.out.println("Write how many disposable cups you want to add:");
        int cups = sc.nextInt();
        currentCups += cups;
    }

    public static void take() {
        System.out.println("I gave you $" + currentMoney);
        currentMoney = 0;
    }

    public static void remaining() {
        System.out.println("The coffee machine has:");
        System.out.println(currentWater + " ml of water");
        System.out.println(currentMilk + " ml of milk");
        System.out.println(currentBeans + " g of coffee beans");
        System.out.println(currentCups + " disposable cups");
        System.out.println("$" + currentMoney + " of money");
    }
}

