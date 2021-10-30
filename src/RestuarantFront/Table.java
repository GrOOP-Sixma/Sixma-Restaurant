package RestuarantFront;
public class Table {
    private int tableID; // unique identifier for the table
    private int numSeats; // number of seats in the table
    private TableStatus status = TableStatus.VACANT; // status of the table default is vacant

    // constructor

    public Table(int tableID, int numSeats) {
        this.tableID = tableID;
        this.numSeats = numSeats;
    }

    // getters

    public int getTableID() {
        return tableID;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public TableStatus getStatus() {
        return status;
    }

    // methods

    public void reserve() {
        status = TableStatus.RESERVED;
    }

    public void cancel() {
        status = TableStatus.VACANT;
    }

}