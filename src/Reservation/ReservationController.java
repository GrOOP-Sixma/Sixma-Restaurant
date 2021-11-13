package Reservation;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Calendar;
import java.io.*;

import Customer.*;
import Table.*;

public class ReservationController {
    private final CustomerController customerController;
    private final TableController tableController;
    private final ArrayList<Reservation> reservationList;
    private String name;

    // constructors
    public ReservationController(String name, CustomerController customerController, TableController tableController) {
        this.name = name;
        this.customerController = customerController;
        this.tableController = tableController;
        reservationList = new ArrayList<>();
        readInstances();
    }

    public ArrayList<Reservation> getReservationList() {
        return reservationList;
    }

    // methods
    public void addReservation(Calendar reservationDate, Customer customer, int numPax, Table table) {
        Reservation reservation = new Reservation(reservationDate, customer, numPax, table);
        reservationList.add(reservation);
    }

    public void addReservation(int reservationId, Calendar reservationDate, Customer customer, int numPax, Table table) {
        Reservation reservation = new Reservation(reservationId, reservationDate, customer, numPax, table);
        reservationList.add(reservation);
    }

    public void addReservation(Reservation reservation) {
        reservationList.add(reservation);
    }

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

    public void finishReservation(Table table) {
        for (Reservation reservation : reservationList) {
            if (reservation.getTable() == table) {
                reservationList.remove(reservation);
                return;
            }
        }
    }

    public Reservation getReservation(int reservationId) {
        for (Reservation reservation : reservationList) {
            if (reservation.getReservationId() == reservationId) {
                return reservation;
            }
        }
        return null;
    }

    public Reservation getReservation(Table table) {
        for (Reservation reservation : reservationList) {
            if (reservation.getTable() == table) {
                return reservation;
            }
        }
        return null;
    }

    public void viewReservations() {
        System.out.println("\nReservations:");
        for (Reservation reservation : reservationList) {
            reservation.printReservation();
        }
    }

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

    public void writeInstances() {
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

    public void readInstances() {
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
