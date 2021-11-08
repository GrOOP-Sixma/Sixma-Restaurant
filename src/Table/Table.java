package Table;

import java.io.Serializable;

public class Table implements Serializable {
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

    public byte[] toByteArray() {
        byte[] byteArray = new byte[1024];
        byteArray[0] = (byte) tableID;
        byteArray[1] = (byte) numSeats;
        byteArray[2] = (byte) status.ordinal();
        return byteArray;
    }

    public void toFile(String fileName) {
        byte[] byteArray = toByteArray();
        System.out.println("bytes: " + byteArray.length);
        java.io.FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new java.io.FileOutputStream(fileName);
            fileOutputStream.write(byteArray);
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e);
        } finally {
            try {
                fileOutputStream.close();
            } catch (Exception e) {
                System.out.println("Error closing file: " + e);
            }
        }
    }
}