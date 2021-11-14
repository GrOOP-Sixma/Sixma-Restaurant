package Staff;

import java.util.Scanner;

import Restaurant.RestaurantBack.Gender;

/**
 * Represents a boundary class to get the user input
 * in order for the StaffController class to perform the
 * various method executions on the Staff class
 * @author chris
 *
 */
public class StaffFactory {
	/**
	 * The StaffController of this StaffFactory
	 */
    private StaffController staffController;
    /**
     * The name of this StaffFactory
     */
    private String name;

    // constructors
    /**
     * Creates a new StaffFactory with the given name
     * @param name This StaffFactory's name
     */
    public StaffFactory(String name) {
        staffController = new StaffController(name);
        this.name = name;
    }

    // getters
    /**
     * Gets this StaffFactory's staffController
     * @return this StaffFactory's staffController
     */
    public StaffController getStaffController() {return staffController;}

    // methods
    /**
     * Gets the input of the user
     * @return return the user's input
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
     * Runs the StaffFactory to take in user input
     */

    public void run(){
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        while (choice != 0){
            System.out.println("\nStaff Manager:");
            System.out.println("1. Hire Staff");
            System.out.println("2. Fire Staff");
            System.out.println("3. View Staff");
            System.out.println("0. Exit");
            loop: while (choice != 0) {
                choice = getIntInput();
                switch (choice) {
                    case 0:
                        continue;
                    case 1:
                        hireStaff();
                        break loop;
                    case 2:
                        fireStaff();
                        break loop;
                    case 3:
                        viewStaff();
                        break loop;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }
    }
    /**
     * Adds a new staff into this StaffFactory's 
     * saffController's stafflist
     */

    public void hireStaff(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of new staff:");
        String name = sc.nextLine();

        System.out.println("Enter gender of new staff:");
        System.out.println("1. Male");
        System.out.println("2. Female");
        int gender = getIntInput();
        while (gender != 1 && gender != 2) {
            System.out.println("Invalid choice");
            gender = getIntInput();
        }

        System.out.println("Enter role of new staff:");
        String role = sc.nextLine();

        Staff newStaff;
        if (gender == 1) {
            newStaff = new Staff(name, Gender.MALE, role);
        }
        else {
            newStaff = new Staff(name, Gender.FEMALE, role);
        }
        staffController.addStaff(newStaff);
    }
    
    /**
     * Removes an existing Staff from this StaffFactory;s
     * staffController's stafflist if the staff is in the staffList
     */
    public void fireStaff(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of staff to fire:");
        String name = sc.nextLine();

        System.out.println("Enter id of staff:");
        int id = getIntInput();
        while (id <= 0) {
            System.out.println("Invalid id");
            id = getIntInput();
        }
        if (staffController.removeStaff(name, id) == 0) {
            System.out.println(name + " is not a registered staff");
        }
    }
    
    /**
     * View all the Staff on the staffController's staffList
     */

    public void viewStaff(){
        staffController.viewStaff();
    }

    /**
     * Saves the Staffs in the staffController's staffList into a file
     */
    public void writeInstances() {
        staffController.writeInstances();
    }
}
