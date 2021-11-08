package Reservation;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;

import Customer.Customer;
import Table.Table;

public class ReservationController implements Serializable{
    private final ArrayList<Reservation> reservationList;

    // constructor
    public ReservationController() {
        reservationList = new ArrayList<>();
    }

    // methods
    public void addReservation(Customer customer, Table table, int numOfPax, Calendar reservationDate) {
        Reservation reservation = new Reservation(customer, table, numOfPax, reservationDate);
        reservationList.add(reservation);
    }

    public void addReservation(Reservation reservation) {
        reservationList.add(reservation);
    }

    public void removeReservation(int reservationId) {
        for (int i=0; i<reservationList.size(); i++) {
            if (reservationList.get(i).getReservationID() == reservationId) {
                reservationList.remove(i);
                break;
            }
        }
    }

    public void removeReservation(Customer customer) {
        for (int i=0; i<reservationList.size(); i++) {
            if (reservationList.get(i).getCustomerName().equals(customer.getName()) && reservationList.get(i).getCustomerContactNo() == customer.getContactNo()) {
                reservationList.remove(i);
                break;
            }
        }
    }

    public Reservation getReservation(int reservationId) {
        for (Reservation reservation : reservationList)
            if (reservation.getReservationID() == reservationId)
                return reservation;

        return null;
    }

    public void viewReservations() {
        for (Reservation reservation : reservationList)
            System.out.println(reservation);
    }

    // convert to byte array
    public byte[] objectToByteArray() {
        byte[] byteArray = new byte[0];
        for (Reservation reservation : reservationList) {
            byte[] temp = reservation.toByteArray();
            byteArray = concatenateByteArrays(byteArray, temp);
        }
        return byteArray;
    }

    public byte[] concatenateByteArrays(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    // byte array to file
    public void toFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("../data/reservation.dat");
            fileOutputStream.write(this.objectToByteArray());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // file to byte array
    public byte[] fileToByteArray() {
        try {
            byte[] byteArray = Files.readAllBytes(Paths.get("../data/reservatiion.dat"));
            return byteArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // byte array to object
    public static ReservationController byteArrayToObject(byte[] byteArray) throws ClassNotFoundException, IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return (ReservationController) objectInputStream.readObject();
    }
}
