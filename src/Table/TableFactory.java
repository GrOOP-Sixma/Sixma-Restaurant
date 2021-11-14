package Table;

import java.util.Random;
import java.util.Scanner;
/**
 * Represents a boundary class to get the user input
 * in order for the TableController class to perform
 * the various method executions on the Table class
 * @author chris
 * @version 1.0
 * @since 2021-11-14
 */
public class TableFactory {
	/**
	 * The tableController of this TableFactory
	 */
    private TableController tableController;
    String name;

    // constructors
    /**
     * Creates a new TableFactory with the given name
     * @param name This TableFactory's name
     */
    public TableFactory(String name) {
        tableController = new TableController(name);
        this.name = name;
    }

    // getters
    /**
     * Gets this TableFactory's tableController
     * @return this TableFactory's tableController
     */
    public TableController getTableController() {return tableController;}

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
     * Runs the TableFactory to take in the user input
     */
    public void run() {
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        while (choice != 0) {
            System.out.println("\nTable Manager:");
            System.out.println("1. Set up a table");
            System.out.println("2. Remove a table");
            System.out.println("3. Check table availability");
            System.out.println("4. Create generic tables");
            System.out.println("5. View all tables");
            System.out.println("0. Exit");
            loop: while (choice != 0) {
                choice = getIntInput();
                switch (choice) {
                    case 0:
                        continue;
                    case 1:
                        addTable();
                        break loop;
                    case 2:
                        removeTable();
                        break loop;
                    case 3:
                        checkAvailability();
                        break loop;
                    case 4:
                        createGenericTables();
                        break loop;
                    case 5:
                        viewTable();
                        break loop;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }
    }

    /**
     * Adds a new Table into this TableFactory'ss
     * tableController's tableList
     */
    public void addTable() {
        System.out.println("Enter capacity of table to be set up");
        int numSeats = getIntInput();
        while (numSeats <= 0 || numSeats > 10) {
            System.out.println("Invalid number of seats");
            numSeats = getIntInput();
        }

        Table newTable = new Table(numSeats);
        tableController.addTable(newTable);
    }

    /**
     * Removes an existing table from this TableFactory's
     * tableController's tableList if the customer is in the tableList
     */
    public void removeTable() {
        System.out.println("Enter id of table to be removed");
        int id = getIntInput();
        while (id <= 0) {
            System.out.println("Invalid Id");
            id = getIntInput();
        }

        if (tableController.removeTable(id) == 0) {
            System.out.println("There is no table with id " + id);
        }
    }

    /**
     * Creates tables if there are no tables
     */
    public void createGenericTables() {
        // if there are no tables, create tables
        if (tableController.getTableMap().size() == 0) {
            for (int i = 2; i < 10; i ++) {
                if (i % 2 != 0) {
                    i++;
                }
                for (int j = 1; j < 10; j++) {
                    // get random 1 or 0 
                    Random rand = new Random(System.currentTimeMillis());
                    int test = rand.nextInt(5); // 1 in 5 chance of creating a table
                    if (test == 0) {
                        Table newTable = new Table(i);
                        tableController.addTable(newTable);
                    }
                }
            }
        } else {
            System.out.println("There are already tables in the system");
        }
    }

    /**
     * Checks availability of tables in this 
     * TableFactory's tableList
     */
    public void checkAvailability() {tableController.checkAvailability();}

    /**
     * View all the Tables on the tableController's tableList
     */
    public void viewTable() {tableController.viewTable();}
    
    /**
     * Saves the tables in tableController's tableList into a file
     */
    public void writeInstances() {tableController.writeInstances();}
}
