package Reservation;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Calendar;
import java.io.*;

import Customer.*;
import Table.*;
/**
 * Represents a control class to execute the methods on the Reservation class
 * @author tengwei
 * @version 1.0
 * @since 2021-11-14
 */

public class ReservationController {
    /**
     * The customerConroller of the ReservationController
     */
    private final CustomerController customerController;
    /**
     * The tableController of the ReservationController
     */
    private final TableController tableController;
    /**
     * The list of reservations
     */
    private final ArrayList<Reservation> reservationList;
    /**
     * The name of this ReservationController
     */
    private String name;

    // constructors
    /**
     * Creates a new ReservationController witht the given name
     * @param name This ReservationController's name
     * @param customerController This ReservationController's customerController
     * @param tableController This ReservationController's tableController
     */
    
    public ReservationController(String name, CustomerController customerController, TableController tableController) throws FileNotFoundException, IOException{
        this.name = name;
        this.customerController = customerController;
        this.tableController = tableController;
        reservationList = new ArrayList<>();
        readInstances();
    }

    /**
     * Gets the current and upcoming Reservations in the reservationList
     * @return this ReservationController's reservationList
     */
    public ArrayList<Reservation> getReservationList() {
        return reservationList;
    }

    // methods
    /**
     * Adds a reservation into this ReservationController's reservationList
     * @param reservationDate this Reservation's reservationDate
     * @param customer this Reservation's customer
     * @param numPax this Reservation's numPax
     * @param table this Reservation's table
     */
    public void addReservation(Calendar reservationDate, Customer customer, int numPax, Table table) {
        Reservation reservation = new Reservation(reservationDate, customer, numPax, table);
        reservationList.add(reservation);
    }

    /**
     * Adds a reservation into this ReservationController's reservationList
     * @param reservationId this ReservationController's
     * @param reservationDate this Reservation's reservationDate
     * @param customer this Reservation's customer
     * @param numPax this Reservation's numPax
     * @param table this Reservation's table
     */
    public void addReservation(int reservationId, Calendar reservationDate, Customer customer, int numPax, Table table) {
        Reservation reservation = new Reservation(reservationId, reservationDate, customer, numPax, table);
        reservationList.add(reservation);
    }

    /**
     * Adds a reservation into this ReservationController's reservationList
     * @param reservation this new Reservation Object
     */
    public void addReservation(Reservation reservation) {
        reservationList.add(reservation);
    }

    /**
     * Removes a Reservation from this ReservationController's reservationList
     * if the reservationList contains the reservation in the reservationId
     * @param reservationId this reservation's reservationId
     * @return whether this Reservation has been removed from the reservationList
     */
    public int removeReservation(int reservationId) {
        for (Reservation reservation : reservationList) {
            if (reservation.getReservationId() == reservationId) {
                reservation.getTable().unreserveTable();
                reservationList.remove(reservation);
                return 1;
            }
        }
        return 0;
    }

    /**
     * Removes a reservation from the reservationList
     * if the table is in the reservationList
     * @param table the reservation's table
     */
    public void finishReservation(Table table) {
        for (Reservation reservation : reservationList) {
            if (reservation.getTable() == table) {
                reservationList.remove(reservation);
                return;
            }
        }
    }

    /**
     * Gets the Reservation from this ReservationController's reservationList
     * if the reservationList contains the reservationId
     * @param reservationId this reservation's reservationId
     * @return whether this reservation is in reservationList
     */
    public Reservation getReservation(int reservationId) {
        for (Reservation reservation : reservationList) {
            if (reservation.getReservationId() == reservationId) {
                return reservation;
            }
        }
        return null;
    }

    /**
     * Gets the Reservation from this ReservationController's reservationList
     * if the reservationList contains the table
     * @param table this reservation's table
     * @return whether this reservation is in reservationList
     */
    public Reservation getReservation(Table table) {
        for (Reservation reservation : reservationList) {
            if (reservation.getTable() == table) {
                return reservation;
            }
        }
        return null;
    }

    /**
     * Prints the reservations in This ReservtionControllers's reservationList
     */
    public void viewReservations() {
        System.out.println("\nReservations:");
        for (Reservation reservation : reservationList) {
            reservation.printReservation();
        }
    }

    /**
     * Updates This ReservationController's reservationList 
     * if the reservation is expired then remove it from the reservationList
     */
    public void updateReservations() {
        ArrayList<Reservation> toRemove = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            Calendar reservationDate = reservation.getReservationDate();
            Calendar nowDate = Calendar.getInstance();
            if (reservationDate.before(nowDate)) {
                reservation.getTable().unreserveTable();
                toRemove.add(reservation);
            }
        }
        reservationList.removeAll(toRemove);
    }

    /**
     * Writes an Instance of Reservation into a txt file
     * @throws IOException If an input or output error has occurred
     */
    public void writeInstances() throws IOException{
        int reservationId;
        Calendar reservationDate;
        Customer customer;
        int numPax;
        Table table;
        try {
            File myObj = new File(System.getProperty("user.dir") + "/tmp/" + this.name  + "Reservation.txt");
            myObj.createNewFile(); // if file already exists will do nothing
            FileWriter myWriter = new FileWriter(System.getProperty("user.dir") + "/tmp/" + this.name  + "Reservation.txt");
            for (Reservation reservation : reservationList) {
                reservationId = reservation.getReservationId();
                reservationDate = reservation.getReservationDate();
                int year = reservationDate.get(Calendar.YEAR);
                int month = reservationDate.get(Calendar.MONTH);
                int day = reservationDate.get(Calendar.DAY_OF_MONTH);
                int hour = reservationDate.get(Calendar.HOUR_OF_DAY);
                int minute = reservationDate.get(Calendar.MINUTE);
                int second = reservationDate.get(Calendar.SECOND);
                customer = reservation.getCustomer();
                numPax = reservation.getNumPax();
                table = reservation.getTable();
                myWriter.write(reservationId + ";" + year + ";" + month + ";" + day + ";" + hour + ";" + minute + ";" + second + ";" + customer.getCustomerId() + ";" + numPax + ";" + table.getTableId() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Reads an Instance of reservations from a txt file
     * @throws FileNotFoundException If file cannot be found
     * @throws IOException If an input or ouput error has occurred
     */
    public void readInstances() throws FileNotFoundException, IOException{
        int reservationId;
        int year;
        int month;
        int day;
        int hour;
        int minute;
        int second;
        int customerId;
        int numPax;
        int tableId;
        int maxReservationId = 0;
        try {
            File myObj = new File(System.getProperty("user.dir") + "/tmp/" + this.name  + "Reservation.txt");
            myObj.createNewFile(); // if file already exists will do nothing
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] attributes = data.split(";");
                reservationId = Integer.parseInt(attributes[0]);
                year = Integer.parseInt(attributes[1]);
                month = Integer.parseInt(attributes[2]);
                day = Integer.parseInt(attributes[3]);
                hour = Integer.parseInt(attributes[4]);
                minute = Integer.parseInt(attributes[5]);
                second = Integer.parseInt(attributes[6]);
                Calendar reservationDate = new GregorianCalendar(year, month, day, hour, minute, second);
                customerId = Integer.parseInt(attributes[7]);
                Customer customer = customerController.getCustomer(customerId);
                numPax = Integer.parseInt(attributes[8]);
                tableId = Integer.parseInt(attributes[9]);
                Table table = tableController.getTable(tableId);
                addReservation(reservationId, reservationDate, customer, numPax, table);
                if (reservationId > maxReservationId) {
                    maxReservationId = reservationId;
                }
            }
            Reservation.setNextReservationId(maxReservationId + 1);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
