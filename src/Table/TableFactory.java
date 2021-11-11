package Table;

import java.util.Scanner;

public class TableFactory {
    private TableController tableController;

    // constructors
    public TableFactory() {tableController = new TableController();}

    // getters
    public TableController getTableController() {return tableController;}

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
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        while (choice != 0) {
            System.out.println("\nTable Manager:");
            System.out.println("1. Set up a table");
            System.out.println("2. Remove a table");
            System.out.println("3. Check table availability");
            System.out.println("4. View all tables");
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
                        viewTable();
                        break loop;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }
    }

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

    public void checkAvailability() {tableController.checkAvailability();}

    public void viewTable() {tableController.viewTable();}

    public void writeInstances() {tableController.writeInstances();}
}
