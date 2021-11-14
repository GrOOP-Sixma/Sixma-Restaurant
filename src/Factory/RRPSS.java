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

    public static final String BACKGROUND_BLUE = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

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
                System.out.println(ANSI_RED + "Invalid input." + ANSI_RESET);
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
            System.out.println(BACKGROUND_BLUE + "It looks like this is the first time this program is being run" + ANSI_RESET);
            System.out.println(BACKGROUND_BLUE + "Please enter the name of your name: " + ANSI_RESET);
            String ownerName = sc.nextLine();
            System.out.println(BACKGROUND_BLUE + "Enter 1 if you are male, or 2 if you are female" + ANSI_RESET);
            Gender gender;
            if (getIntInput() == 1) {
                gender = Gender.MALE;
            } else {
                gender = Gender.FEMALE;
            }

            // create owner
            this.restaurant.getStaffFactory().getStaffController().addStaff(ownerName, gender, "Owner");
            System.out.println(BACKGROUND_BLUE + "Welcome to the RRPSS!" + ANSI_RESET);
            System.out.println(BACKGROUND_BLUE + "------------------------------------------------------------" + ANSI_RESET);
        }

        int choice = -1;
        while (choice != 0) {
            System.out.println(BACKGROUND_BLUE + """
            $$$$$$$\\  $$$$$$$\\  $$$$$$$\\   $$$$$$\\   $$$$$$\\  
            $$  __$$\\ $$  __$$\\ $$  __$$\\ $$  __$$\\ $$  __$$\\ 
            $$ |  $$ |$$ |  $$ |$$ |  $$ |$$ /  \\__|$$ /  \\__|
            $$$$$$$  |$$$$$$$  |$$$$$$$  |\\$$$$$$\\  \\$$$$$$\\  
            $$  __$$< $$  __$$< $$  ____/  \\____$$\\  \\____$$\\ 
            $$ |  $$ |$$ |  $$ |$$ |      $$\\   $$ |$$\\   $$ |
            $$ |  $$ |$$ |  $$ |$$ |      \\$$$$$$  |\\$$$$$$  |
            \\__|  \\__|\\__|  \\__|\\__|       \\______/  \\______/ 
                                                  
                                                  
                                                  
            """ + ANSI_RESET);
            System.out.println(BACKGROUND_BLUE + "\nWhat would you like to do?" + ANSI_RESET);
            System.out.println(BACKGROUND_BLUE + "1. Menu Options" + ANSI_RESET);
            System.out.println(BACKGROUND_BLUE + "2. Set Menu Options" + ANSI_RESET);
            System.out.println(BACKGROUND_BLUE + "3. Staff Options" + ANSI_RESET);
            System.out.println(BACKGROUND_BLUE + "4. Table Options" + ANSI_RESET);
            System.out.println(BACKGROUND_BLUE + "5. Reservation Options" + ANSI_RESET);
            System.out.println(BACKGROUND_BLUE + "6. Point of Sale Options" + ANSI_RESET);
            System.out.println(BACKGROUND_BLUE + "0. Exit"  + ANSI_RESET);
            loop: while (choice != 0) {
                choice = getIntInput();
                switch (choice) {
                    case 1:
                        System.out.println(BACKGROUND_BLUE + "------------------------------------------------------------" + ANSI_RESET);
                        AsciiPrinter.print();
                        restaurant.menuRun();
                        break loop;
                    case 2:
                        System.out.println(BACKGROUND_BLUE + "------------------------------------------------------------" + ANSI_RESET);
                        AsciiPrinter.print();
                        restaurant.setMenuRun();
                        break loop;
                    case 3:
                        System.out.println(BACKGROUND_BLUE + "------------------------------------------------------------" + ANSI_RESET);
                        AsciiPrinter.print();
                        restaurant.staffRun();
                        break loop;
                    case 4:
                        System.out.println(BACKGROUND_BLUE + "------------------------------------------------------------" + ANSI_RESET);
                        AsciiPrinter.print();
                        restaurant.tableRun();
                        break loop;
                    case 5:
                        System.out.println(BACKGROUND_BLUE + "------------------------------------------------------------" + ANSI_RESET);
                        AsciiPrinter.print();
                        restaurant.reservationRun();
                        break loop;
                    case 6:
                        System.out.println(BACKGROUND_BLUE + "------------------------------------------------------------" + ANSI_RESET);
                        AsciiPrinter.print();
                        restaurant.orderRun();
                        break loop;
                    case 0:
                        continue;
                    default:
                        System.out.println(ANSI_RED + "Invalid choice" + ANSI_RESET);
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
