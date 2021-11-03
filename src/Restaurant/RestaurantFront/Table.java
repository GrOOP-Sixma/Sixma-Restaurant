package Restaurant.RestaurantFront;

public class Table {
    private final int tableID;
    private static int nextTableID = 1;
    private int numSeats;
    private TableStatus status = TableStatus.VACANT;

    // constructor
    public Table(int numSeats) {
        this.tableID = getNextTableID();
        this.numSeats = numSeats;
        this.status = TableStatus.VACANT;
    }

    // getters
    public int getTableID() {return tableID;}
    public int getNumSeats() {return numSeats;}
    public TableStatus getStatus() {return status;}

    // setters
    public void setNumSeats(int numSeats) {this.numSeats = numSeats;}
    //public void setStatus(TableStatus status) {this.status = status;}

    // methods

    // reserve table
    public void reserveTable(){
        this.status = TableStatus.RESERVED;
    }

    // unreserve table
    public void unreserveTable(){
        this.status = TableStatus.VACANT;
    }

    public static int getNextTableID() {
        int id = nextTableID++;
        return id;
    }
}