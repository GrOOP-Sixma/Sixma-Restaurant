package Reservation;

import java.util.ArrayList;
import java.util.Calendar;

import Customer.Customer;
import Table.Table;

public class ReservationController {
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
}
