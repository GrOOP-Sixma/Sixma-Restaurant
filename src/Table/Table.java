package Table;
/**
 * Represents a Table at the restaurant
 * @author chris
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
        System.out.println("-------------------------------");
        System.out.println("Table ID: " + tableId);
        System.out.println("Capacity: " + numSeats);
        if (status == TableStatus.VACANT) {
            System.out.println("Status: Vacant");
        }
        else {
            System.out.println("Status: Reserved");
        }
    }
}
