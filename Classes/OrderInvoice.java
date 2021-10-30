import java.time.*;
import java.util.*;

public class OrderInvoice extends Order {
    private LocalDate date; // date of order
    private double subtotal; // subtotal of order
    private double GSTAmount; // GST amount
    private double ServiceCharge; // service charge amount
    private double total; // total amount of order
    
    public final static double GST = 0.07; // GST rate
    public final static double SERVICE_CHARGE = 0.10; // service charge rate
	
    public OrderInvoice(Staff staffOrder, int orderID, Reservation reservationName, ArrayList<Food> orderedItems, Table tableID, Boolean isReservatation) {
        super(staffOrder, orderID, reservationName, orderedItems, tableID, isReservatation);
        this.date = LocalDate.now();
    }

    public LocalDate getDate() {
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
}