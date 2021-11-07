package Order;
import java.util.*;

public class OrderInvoice extends Order {
    private Calendar date; // date of order
    private double subtotal; // subtotal of order
    private double GSTAmount; // GST amount
    private double ServiceCharge; // service charge amount
    private double total; // total amount of order
    
    public final static double GST = 0.07; // GST rate
    public final static double SERVICE_CHARGE = 0.10; // service charge rate


    public OrderInvoice(Order order) {
        super(order.getStaffOrder(), order.getOrderID(), order.getOrderedItems(), order.getTableID());
        this.date = Calendar.getInstance();
    }


    public Calendar getDate() {
        return date;
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

    // setters

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void setGSTAmount(double GSTAmount) {
        this.GSTAmount = GSTAmount;
    }

    public void setServiceCharge(double ServiceCharge) {
        this.ServiceCharge = ServiceCharge;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    
}