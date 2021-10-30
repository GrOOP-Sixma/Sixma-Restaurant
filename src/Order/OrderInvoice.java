package Order;
import java.util.*;

import Food.MenuItem;
import RestaurantBack.Reservation;
import RestaurantBack.Staff;
import RestuarantFront.Table;

public class OrderInvoice extends Order {
    private Calendar date; // date of order
    private double subtotal; // subtotal of order
    private double GSTAmount; // GST amount
    private double ServiceCharge; // service charge amount
    private double total; // total amount of order
    
    public final static double GST = 0.07; // GST rate
    public final static double SERVICE_CHARGE = 0.10; // service charge rate

    public OrderInvoice(Staff staffOrder, int orderID, Reservation reservationName, ArrayList<MenuItem> orderedItems, Table tableID, Boolean isReservatation, Calendar date) {
        super(staffOrder, orderID, reservationName, orderedItems, tableID, isReservatation);
        this.date = date;
        calculateSubtotal();
        calculateGST();
        calculateServiceCharge();
        calculateTotal();
    }


    public Calendar getDate() {
        return date;
    }

    // calculations
    public void calculateSubtotal() {
        subtotal = super.getTotalPrice();
    }

    public void calculateGST() {
        GSTAmount = subtotal * GST;
    }

    public void calculateServiceCharge() {
        ServiceCharge = subtotal * SERVICE_CHARGE;
    }

    public void calculateTotal() {
        total = subtotal + GSTAmount + ServiceCharge;
    }

    // getters
    public double getSubtotal() {
        return subtotal;
    }

    public double getGSTAmount() {
        return GSTAmount;
    }

    public double getServiceCharge() {
        return ServiceCharge;
    }

    public double getTotal() {
        return total;
    }

    // print invoice
    public void printOrderInvoice() {
        System.out.println("Order Invoice");
        super.printOrder();
        System.out.println("Date: " + date.getTime());
        System.out.println("Subtotal: " + subtotal);
        System.out.println("GST: " + GSTAmount);
        System.out.println("Service Charge: " + ServiceCharge);
        System.out.println("Total: " + total);
    }

}