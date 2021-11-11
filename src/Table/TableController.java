package Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.*;

public class TableController {
    private final Map<Integer, ArrayList<Table>> tableMap;

    // constructors
    public TableController() {
        tableMap = new HashMap<>();
        readInstances();
    }

    // methods
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

    public void viewTable() {
        System.out.println("\nTables:");
        for (ArrayList<Table> tableList : tableMap.values()) {
            for (Table table : tableList) {
                table.printTable();
            }
        }
    }

    public void writeInstances() {
        int tableId;
        int numSeats;
        TableStatus status;
        try {
            FileWriter myWriter = new FileWriter(System.getProperty("user.dir") + "/tmp/Table.txt");
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

    public void readInstances() {
        int tableId;
        int numSeats;
        int status;
        int maxTableId = 0;
        try {
            File myObj = new File(System.getProperty("user.dir") + "/tmp/Table.txt");
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
        }
    }
}
