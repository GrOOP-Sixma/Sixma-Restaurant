/**
 * Staff is a package that includes all of the classes under the 
 * umbrella of the classes that are needed for a staff
 */
package Staff;

import java.util.Scanner;

import Factory.AsciiPrinter;
import Human.Gender;

/**
 * Represents a boundary class to get the user input
 * in order for the StaffController class to perform the
 * various method executions on the Staff class
 * @author chris
 * @version 1.0
 * @since 2021-11-14
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

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

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
                System.out.println(ANSI_RED +  "Invalid input." + ANSI_RESET);
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
        System.out.println(ANSI_CYAN +  """
        $$$$$$\\    $$\\                $$$$$$\\   $$$$$$\\        $$\\      $$\\                                                             
        $$  __$$\\   $$ |              $$  __$$\\ $$  __$$\\       $$$\\    $$$ |                                                            
        $$ /  \\__|$$$$$$\\    $$$$$$\\  $$ /  \\__|$$ /  \\__|      $$$$\\  $$$$ | $$$$$$\\  $$$$$$$\\   $$$$$$\\   $$$$$$\\   $$$$$$\\   $$$$$$\\  
        \\$$$$$$\\  \\_$$  _|   \\____$$\\ $$$$\\     $$$$\\           $$\\$$\\$$ $$ | \\____$$\\ $$  __$$\\  \\____$$\\ $$  __$$\\ $$  __$$\\ $$  __$$\\ 
         \\____$$\\   $$ |     $$$$$$$ |$$  _|    $$  _|          $$ \\$$$  $$ | $$$$$$$ |$$ |  $$ | $$$$$$$ |$$ /  $$ |$$$$$$$$ |$$ |  \\__|
        $$\\   $$ |  $$ |$$\\ $$  __$$ |$$ |      $$ |            $$ |\\$  /$$ |$$  __$$ |$$ |  $$ |$$  __$$ |$$ |  $$ |$$   ____|$$ |      
        \\$$$$$$  |  \\$$$$  |\\$$$$$$$ |$$ |      $$ |            $$ | \\_/ $$ |\\$$$$$$$ |$$ |  $$ |\\$$$$$$$ |\\$$$$$$$ |\\$$$$$$$\\ $$ |      
         \\______/    \\____/  \\_______|\\__|      \\__|            \\__|     \\__| \\_______|\\__|  \\__| \\_______| \\____$$ | \\_______|\\__|      
                                                                                                           $$\\   $$ |                    
                                                                                                           \\$$$$$$  |                    
                                                                                                            \\______/                     
        """ + ANSI_RESET);
        while (choice != 0){
            System.out.println("");
            System.out.println(ANSI_CYAN +  "1. Hire Staff" + ANSI_RESET);
            System.out.println(ANSI_CYAN +  "2. Fire Staff" + ANSI_RESET);
            System.out.println(ANSI_CYAN +  "3. View Staff" + ANSI_RESET);
            System.out.println(ANSI_CYAN +  "0. Exit" + ANSI_RESET);
            loop: while (choice != 0) {
                choice = getIntInput();
                switch (choice) {
                    case 0:
                        AsciiPrinter.print();
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
                        System.out.println(ANSI_RED +  "Invalid choice" + ANSI_RESET);
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
        System.out.println(ANSI_CYAN +  "Enter name of new staff:" + ANSI_RESET);
        String name = sc.nextLine();

        System.out.println(ANSI_CYAN +  "Enter gender of new staff:" + ANSI_RESET);
        System.out.println(ANSI_CYAN +  "1. Male" + ANSI_RESET);
        System.out.println(ANSI_CYAN +  "2. Female" + ANSI_RESET);
        int gender = getIntInput();
        while (gender != 1 && gender != 2) {
            System.out.println(ANSI_RED +  "Invalid choice" + ANSI_RESET);
            gender = getIntInput();
        }

        System.out.println(ANSI_CYAN +  "Enter role of new staff:" + ANSI_RESET);
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
        System.out.println(ANSI_CYAN +  "Enter name of staff to fire:" + ANSI_RESET);
        String name = sc.nextLine();

        System.out.println(ANSI_CYAN +  "Enter id of staff:" + ANSI_RESET);
        int id = getIntInput();
        while (id <= 0) {
            System.out.println(ANSI_RED +  "Invalid id" + ANSI_RESET);
            id = getIntInput();
        }
        if (staffController.removeStaff(name, id) == 0) {
            System.out.println(ANSI_RED + name + " is not a registered staff" + ANSI_RESET);
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
