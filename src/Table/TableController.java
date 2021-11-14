package Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Customer.Customer;

import java.io.*;

/**
 * Represents a control class to execute the methods on the Table class
 * @author chris
 * @version 1.0
 * @since 2021-11-14
 *
 */
public class TableController {
	/**
	 * The list of current tables that are in the restaurant
	 */
    private final Map<Integer, ArrayList<Table>> tableMap;
    /**
     * name This TableController's name
     */
    private String name;

    // constructors
    /**
     * Creates a new TableController with the given name
     * @param name This TableController's name
     */
    public TableController(String name) {
        tableMap = new HashMap<>();
        this.name = name;
        readInstances();
    }

    // methods
    /**
     * Adds a table into this TableController's tableList
     * @param numSeats this Table's number of seats
     */
    public void addTable(int numSeats) {
        Table table = new Table(numSeats);

        ArrayList<Table> tableList;
        if (!tableMap.containsKey(numSeats)) {
            tableList = new ArrayList<>();
            tableMap.put(numSeats, tableList);
        }
        else {
            tableList = tableMap.get(numSeats);
        }
        tableList.add(table);
    }

    /**
     * Adds a table into this TableController's tableList
     * @param numSeats this Table's number of seats
     * @param tableId this Table's tableId
     * @param status this Table's status
     */
    public void addTable(int numSeats, int tableId, TableStatus status) {
        Table table = new Table(numSeats, tableId, status);

        ArrayList<Table> tableList;
        if (!tableMap.containsKey(numSeats)) {
            tableList = new ArrayList<>();
            tableMap.put(numSeats, tableList);
        }
        else {
            tableList = tableMap.get(numSeats);
        }
        tableList.add(table);
    }

    /**
     * Adds a table into this TableController's tableList
     * @param table this Table's Table
     */
    public void addTable(Table table) {
        ArrayList<Table> tableList;
        if (!tableMap.containsKey(table.getNumSeats())) {
            tableList = new ArrayList<>();
            tableMap.put(table.getNumSeats(), tableList);
        }
        else {
            tableList = tableMap.get(table.getNumSeats());
        }
        tableList.add(table);
    }

    /**
     * Removes a table from this TableController's tableList
     * @param tableId this Table's name
     * @return whether this Table has been removed from the tableList
     */
    public int removeTable(int tableId) {
        for (int i=1; i<=10; i++) {
            if (!tableMap.containsKey(i)) {
                continue;
            }

            ArrayList<Table> tableList = tableMap.get(i);
            for (Table table : tableList) {
                if (table.getTableId() == tableId) {
                    tableList.remove(table);
                    if (tableList.size() == 0) {
                        tableMap.remove(i);
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    /**
     * Gets the table from this TableController's tableList
     * @param tableId this Table's tableId
     * @return whether this Table is in the TableList
     */
    public Table getTable(int tableId) {
        for (ArrayList<Table> tableList : tableMap.values()) {
            for (Table table : tableList) {
                if (table.getTableId() == tableId) {
                    return table;
                }
            }
        }
        return null;
    }

    /**
     * Gets a vacant table from this TableController's tableList
     * @param numSeats this Table's number of seats
     * @return whether or not this Table is in the TableList
     */
    public Table getVacantTable(int numSeats) {
        for (int i=numSeats; i<=10; i++) {
            if (tableMap.containsKey(i)) {
                ArrayList<Table> tableList = tableMap.get(i);
                for (Table table : tableList) {
                    if (table.getStatus() == TableStatus.VACANT) {
                        return table;
                    }
                }
            }
        }
        return null;
    }

    /**
     * View the tables that are available
     */
    public void checkAvailability() {
        System.out.println("\nTable Availability:");
        System.out.println("Capacity: Vacancy");
        for (ArrayList<Table> tableList : tableMap.values()) {
            int numSeats = tableList.get(0).getNumSeats();
            int vacancy = 0;
            for (Table table : tableList) {
                if (table.getStatus() == TableStatus.VACANT) {
                    vacancy++;
                }
            }
            System.out.printf("%1$-" + 8 + "s", numSeats);
            System.out.println(": " + vacancy);
        }
    }

    /**
     * View all of the table in this TableController's tableMap's tableList
     */
    public void viewTable() {
        System.out.println("\nTables:");
        for (ArrayList<Table> tableList : tableMap.values()) {
            for (Table table : tableList) {
                table.printTable();
            }
        }
    }

    /**
     * Saves the Tables in the TableController's tableList into a file
     * @throws IOException If an input or output exception has occurred
     */
    public void writeInstances() {
        int tableId;
        int numSeats;
        TableStatus status;
        
        try {
            File myObj = new File(System.getProperty("user.dir") + "/tmp/" + this.name  + "Table.txt");
            myObj.createNewFile(); // if file already exists will do nothing
            FileWriter myWriter = new FileWriter(System.getProperty("user.dir") + "/tmp/" + this.name  + "Table.txt");
            for (ArrayList<Table> tableList : tableMap.values()) {
                for (Table table : tableList) {
                    tableId = table.getTableId();
                    numSeats = table.getNumSeats();
                    status = table.getStatus();
                    if (status == TableStatus.VACANT) {
                        myWriter.write(tableId + ";" + numSeats + ";0\n");
                    }
                    else {
                        myWriter.write(tableId + ";" + numSeats + ";1\n");
                    }
                }
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Reads the Tables in the TableController's tableList from a file
     * @throws IOException If an input or output exception has occurred
     * @throws FileNotFoundException If the file is not found
     */
    public void readInstances() {
        int tableId;
        int numSeats;
        int status;
        int maxTableId = 0;
        try {
            File myObj = new File(System.getProperty("user.dir") + "/tmp/" + this.name  + "Table.txt");
            myObj.createNewFile(); // if file already exists will do nothing
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] attributes = data.split(";");
                tableId = Integer.parseInt(attributes[0]);
                numSeats = Integer.parseInt(attributes[1]);
                status = Integer.parseInt(attributes[2]);
                if (status == 0) {
                    addTable(numSeats, tableId, TableStatus.VACANT);
                }
                else {
                    addTable(numSeats, tableId, TableStatus.RESERVED);
                }
                if (tableId > maxTableId) {
                    maxTableId = tableId;
                }
            }
            Table.setNextTableId(maxTableId + 1);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * The list of tableStatus of the Table's tableList
     * @return the tableMap
     */
    public Map<Integer, ArrayList<Table>> getTableMap() {
        return tableMap;
    }
}
