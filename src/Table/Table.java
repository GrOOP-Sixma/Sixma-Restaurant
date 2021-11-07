package Table;

public class Table {
    private final int tableID;
    private static int nextTableID = 1;
    private int numSeats;
    private TableStatus status = TableStatus.VACANT;

    // constructor
    public Table(int numSeats) {
        tableID = getNextTableID();
        this.numSeats = numSeats;
    }

    // getters
    public int getTableID() {return tableID;}
    public int getNumSeats() {return numSeats;}
    public TableStatus getStatus() {return status;}

    // setters
    public void setNumSeats(int numSeats) {this.numSeats = numSeats;}
    public void setStatus(TableStatus status) {this.status = status;}

    // methods
    private static int getNextTableID() {
        return nextTableID++;
    }
}