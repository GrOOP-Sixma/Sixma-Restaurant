package Table;
/**
 * Represents a Table at the restaurant
 * @author chris
 * @version 1.0
 * @since 2021-11-14
 *
 */
public class Table {
	/**
	 * The unique table id of this Table
	 */
    private int tableId;
    /**
     * The next table id of this Table
     */
    private static int nextTableId = 1;
    /**
     * The number of seats in the table
     */
    private int numSeats;
    /**
     * The status of the table, whether it is vacant or reserved
     */
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
    /**
     * Creates a new Table with the table's number of seats
     * @param numSeats This Table's number of seats in the table
     */
    public Table(int numSeats) {
        tableId = getNextTableId();
        this.numSeats = numSeats;
        status = TableStatus.VACANT;
    }

    /**
     * Creates a new Table with the Table's number of seats, tableId, tableStatus
     * @param numSeats This Table's number of seats
     * @param tableId This Table's tableId
     * @param status This Table's status
     */
    public Table(int numSeats, int tableId, TableStatus status) {
        this.tableId = tableId;
        this.numSeats = numSeats;
        this.status = status;
    }

    // getters
    /**
     * Gets the tableId of the Table
     * @return this Table's tableId
     */
    public int getTableId() {return tableId;}
    /**
     * Gets the number of seats of the Table
     * @return this Table's number of seats
     */
    public int getNumSeats() {return numSeats;}
    /**
     * Gets the status of the Table
     * @return this Table's status
     */
    public TableStatus getStatus() {return status;}

    // setters
    /**
     * Sets the nextTableId of this Table
     * @param nextTableId this Table's nextTableId
     */
    public static void setNextTableId(int nextTableId) {Table.nextTableId = nextTableId;}
    /**
     * Sets the number of seats of this Table
     * @param numSeats this Table's nuumber of seats
     */
    public void setNumSeats(int numSeats) {this.numSeats = numSeats;}
    /**
     * Sets the status of this Table
     * @param status this Table's status
     */
    public void setStatus(TableStatus status) {this.status = status;}

    // methods
    /**
     * Gets the next tableId
     * @return
     */
    private static int getNextTableId() {
        return nextTableId++;
    }
    /**
     * Sets the status of a Table to RESERVED
     */
    public void reserveTable() {
        status = TableStatus.RESERVED;
    }
    /**
     * Sets the status of a Table to VACANT
     */
    public void unreserveTable() {
        status = TableStatus.VACANT;
    }
    /**
     * Prints the details of this Table
     */
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
