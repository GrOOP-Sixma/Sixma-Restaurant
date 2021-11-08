package Table;

import java.util.Scanner;

public class TableFactory {
    private TableController tableController;

    public TableFactory() {tableController = new TableController();}

    public TableController getTableController() {return tableController;}

    public void run() {
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        while (choice != 0) {
            System.out.println("1. Set up a table");
            System.out.println("2. Remove a table");
            System.out.println("3. View all tables");
            System.out.println("0. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addTable();
                    break;
                case 2:
                    removeTable();
                    break;
                case 3:
                    viewTable();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }

        sc.close();
    }

    public void addTable() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter capacity of table to be set up");
        int numSeats = sc.nextInt();

        Table newTable = new Table(numSeats);
        tableController.addTable(newTable);
        sc.close();
    }

    public void removeTable() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter capacity of table to be removed");
        int numSeats = sc.nextInt();
        tableController.removeTable(numSeats);
        sc.close();
    }

    public void viewTable() {tableController.viewTable();}
}
