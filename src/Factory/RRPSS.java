package Factory;

import java.util.Scanner;

import Restaurant.RestaurantBack.Gender;
/**
 * Represents the UI of the Restaurant Reservation and Point of Sale System
 * @author tengwei
 * @version 1.0
 * @since 2021-11-14
 */
public class RRPSS implements Runnable{
	/**
	 * The restaurant of this RRPSS
	 */
    private Restaurant restaurant;

    // constructor
    /**
     * Creates a new RRPSS with the given name
     * @param name This RRPSS's name
     */
    public RRPSS(String name) {this.restaurant = new Restaurant(name);}

    // methods
    /**
     * Gets the input of the user
     * @return the user's input
     */
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
    /**
     * Checks if the restaurant is a new restaurant
     * @return boolean of this restaurant existence
     */
    // check if restaurant is a new restaurant
    public boolean isNewRestaurant() {
        // check if number of staff is 0
        if (this.restaurant.getStaffFactory().getStaffController().getStaffList().size() == 0) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Runs the RRPSS to take in user input
     */
    public void run() {
        Scanner sc = new Scanner(System.in);

        if (isNewRestaurant()) {
            System.out.println("It looks like this is the first time this program is being run");
            System.out.println("Please enter the name of your name: ");
            String ownerName = sc.nextLine();
            System.out.println("Enter 1 if you are male, or 2 if you are female");
            Gender gender;
            if (getIntInput() == 1) {
                gender = Gender.MALE;
            } else {
                gender = Gender.FEMALE;
            }

            // create owner
            this.restaurant.getStaffFactory().getStaffController().addStaff(ownerName, gender, "Owner");
            System.out.println("Welcome to the RRPSS!");
            System.out.println("------------------------------------------------------------");
        }

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
    
    /**
     * Gets this RRPSS's restaurant
     * @return this RRPSS's restaurant
     */
    public Restaurant getRestaurant() {return this.restaurant;}
}
