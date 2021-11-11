package Factory;

import java.util.Scanner;

public class RRPSS {
    private Restaurant restaurant;

    // constructor
    public RRPSS(String name) {this.restaurant = new Restaurant(name);}

    // methods
    public int getIntInput() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (sc.hasNextInt()) {
                return sc.nextInt();
            }
            else {
                System.out.println("Invalid input.");
                sc.next();
            }
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Menu Options");
            System.out.println("2. Set Menu Options");
            System.out.println("3. Staff Options");
            System.out.println("4. Table Options");
            System.out.println("5. Reservation Options");
            System.out.println("6. Point of Sale Options");
            System.out.println("0. Exit");
            loop: while (choice != 0) {
                choice = getIntInput();
                switch (choice) {
                    case 1:
                        System.out.println("------------------------------------------------------------");
                        restaurant.menuRun();
                        break loop;
                    case 2:
                        System.out.println("------------------------------------------------------------");
                        restaurant.setMenuRun();
                        break loop;
                    case 3:
                        System.out.println("------------------------------------------------------------");
                        restaurant.staffRun();
                        break loop;
                    case 4:
                        System.out.println("------------------------------------------------------------");
                        restaurant.tableRun();
                        break loop;
                    case 5:
                        System.out.println("------------------------------------------------------------");
                        restaurant.reservationRun();
                        break loop;
                    case 6:
                        System.out.println("------------------------------------------------------------");
                        restaurant.orderRun();
                        break loop;
                    case 0:
                        continue;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }


        restaurant.writeInstances();
        sc.close();
    }

    public Restaurant getRestaurant() {return this.restaurant;}
}
