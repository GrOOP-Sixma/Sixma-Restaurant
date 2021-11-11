package Table;

public class Table {
    private int tableId;
    private static int nextTableId = 1;
    private int numSeats;
    private TableStatus status;

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
