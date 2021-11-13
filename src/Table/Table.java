package Table;

public class Table {
    private int tableId;
    private static int nextTableId = 1;
    private int numSeats;
    private TableStatus status;

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
    public Table(int numSeats) {
        tableId = getNextTableId();
        this.numSeats = numSeats;
        status = TableStatus.VACANT;
    }

    public Table(int numSeats, int tableId, TableStatus status) {
        this.tableId = tableId;
        this.numSeats = numSeats;
        this.status = status;
    }

    // getters
    public int getTableId() {return tableId;}
    public int getNumSeats() {return numSeats;}
    public TableStatus getStatus() {return status;}

    // setters
    public static void setNextTableId(int nextTableId) {Table.nextTableId = nextTableId;}
    public void setNumSeats(int numSeats) {this.numSeats = numSeats;}
    public void setStatus(TableStatus status) {this.status = status;}

    // methods
    private static int getNextTableId() {
        return nextTableId++;
    }

    public void reserveTable() {
        status = TableStatus.RESERVED;
    }

    public void unreserveTable() {
        status = TableStatus.VACANT;
    }

    public void printTable() {
        System.out.println(ANSI_GREEN + "-------------------------------" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Table ID: " + tableId + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Capacity: " + numSeats + ANSI_RESET);
        if (status == TableStatus.VACANT) {
            System.out.println(ANSI_GREEN + "Status: Vacant" + ANSI_RESET);
        }
        else {
            System.out.println(ANSI_GREEN + "Status: Reserved" + ANSI_RESET);
        }
    }
}
