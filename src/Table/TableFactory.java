package Table;

import java.util.Random;
import java.util.Scanner;

import Factory.AsciiPrinter;

public class TableFactory {
    private TableController tableController;
    String name;

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
                System.out.println(ANSI_RED + "Invalid input." + ANSI_RESET);
                sc.next();
            }
        }
    }

    public void run() {
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        System.out.println(ANSI_GREEN + """
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
        """ + ANSI_RESET);
        while (choice != 0) {
            System.out.println(ANSI_GREEN + "" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "1. Set up a table" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "2. Remove a table" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "3. Check table availability" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "4. Create generic tables" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "5. View all tables" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "0. Exit" + ANSI_RESET);
            loop: while (choice != 0) {
                choice = getIntInput();
                switch (choice) {
                    case 0:
                        AsciiPrinter.print();
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
                        System.out.println(ANSI_RED + "Invalid choice" + ANSI_RESET);
                }
            }
        }
    }

    public void addTable() {
        System.out.println(ANSI_GREEN + "Enter capacity of table to be set up" + ANSI_RESET);
        int numSeats = getIntInput();
        while (numSeats <= 0 || numSeats > 10) {
            System.out.println(ANSI_RED + "Invalid number of seats" + ANSI_RESET);
            numSeats = getIntInput();
        }

        Table newTable = new Table(numSeats);
        tableController.addTable(newTable);
    }

    public void removeTable() {
        System.out.println(ANSI_GREEN + "Enter id of table to be removed" + ANSI_RESET);
        int id = getIntInput();
        while (id <= 0) {
            System.out.println(ANSI_RED + "Invalid Id" + ANSI_RESET);
            id = getIntInput();
        }

        if (tableController.removeTable(id) == 0) {
            System.out.println(ANSI_RED + "There is no table with id " + id);
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
                    if (test == 1) {
                        Table newTable = new Table(i);
                        tableController.addTable(newTable);
                    }
                }
            }
        } else {
            System.out.println(ANSI_RED + "There are already tables in the system" + ANSI_RESET);
        }
    }

    public void checkAvailability() {tableController.checkAvailability();}

    public void viewTable() {tableController.viewTable();}

    public void writeInstances() {tableController.writeInstances();}
}
