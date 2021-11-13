package Table;

import java.util.Random;
import java.util.Scanner;

public class TableFactory {
    private TableController tableController;
    String name;

    // constructors
    public TableFactory(String name) {
        tableController = new TableController(name);
        this.name = name;
    }

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
        System.out.println("""
        $$$$$$$$\\           $$\\       $$\\                 $$\\      $$\\                                                             
            \\__$$  __|          $$ |      $$ |                $$$\\    $$$ |                                                            
            $$ |    $$$$$$\\  $$$$$$$\\  $$ | $$$$$$\\        $$$$\\  $$$$ | $$$$$$\\  $$$$$$$\\   $$$$$$\\   $$$$$$\\   $$$$$$\\   $$$$$$\\  
            $$ |    \\____$$\\ $$  __$$\\ $$ |$$  __$$\\       $$\\$$\\$$ $$ | \\____$$\\ $$  __$$\\  \\____$$\\ $$  __$$\\ $$  __$$\\ $$  __$$\\ 
            $$ |    $$$$$$$ |$$ |  $$ |$$ |$$$$$$$$ |      $$ \\$$$  $$ | $$$$$$$ |$$ |  $$ | $$$$$$$ |$$ /  $$ |$$$$$$$$ |$$ |  \\__|
            $$ |   $$  __$$ |$$ |  $$ |$$ |$$   ____|      $$ |\\$  /$$ |$$  __$$ |$$ |  $$ |$$  __$$ |$$ |  $$ |$$   ____|$$ |      
            $$ |   \\$$$$$$$ |$$$$$$$  |$$ |\\$$$$$$$\\       $$ | \\_/ $$ |\\$$$$$$$ |$$ |  $$ |\\$$$$$$$ |\\$$$$$$$ |\\$$$$$$$\\ $$ |      
            \\__|    \\_______|\\_______/ \\__| \\_______|      \\__|     \\__| \\_______|\\__|  \\__| \\_______| \\____$$ | \\_______|\\__|      
                                                                                                        $$\\   $$ |                    
                                                                                                        \\$$$$$$  |                    
                                                                                                        \\______/                     
        """);
        while (choice != 0) {
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

    public void checkAvailability() {tableController.checkAvailability();}

    public void viewTable() {tableController.viewTable();}

    public void writeInstances() {tableController.writeInstances();}
}
