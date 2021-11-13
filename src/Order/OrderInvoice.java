package Order;

import java.lang.reflect.Member;
import java.util.Calendar;
import java.text.DecimalFormat;

import Food.MenuItem;
import Food.SetItem;

public class OrderInvoice extends Order{
    private Calendar date;
    private double subTotal;
    private double serviceChargeAmount;
    private double GSTAmount;
    private double total;

    private final static double GST_RATE = 0.07;
    private final static double SERVICE_CHARGE_RATE = 0.1;

    // constructors
    public OrderInvoice(Order order) {
        super(order.getOrderId(), order.getStaffName(), order.getTableId(), order.isMember(), order.getOrderedMenuItems(), order.getOrderedSetItems());
        date = Calendar.getInstance();
        calculateSubtotal();
        calculateServiceChargeAmount();
        calculateGSTAmount();
        calculateTotal();
    }

    public OrderInvoice(Order order, Calendar date, double subTotal, double serviceChargeAmount, double GSTAmount, double total) {
        super(order.getOrderId(), order.getStaffName(), order.getTableId(), order.isMember, order.getOrderedMenuItems(), order.getOrderedSetItems());
        this.date = date;
        this.subTotal = subTotal;
        this.serviceChargeAmount = serviceChargeAmount;
        this.GSTAmount = GSTAmount;
        this.total = total;
    }

    // getters
    public Calendar getDate() {return date;}
    public double getSubTotal() {return subTotal;}
    public double getServiceChargeAmount() {return serviceChargeAmount;}
    public double getGSTAmount() {return GSTAmount;}
    public double getTotal() {return total;}

    // methods
    private void calculateSubtotal() {
        subTotal = 0;
        for (MenuItem menuItem : orderedMenuItems.keySet()) {
            subTotal += menuItem.getPrice() * orderedMenuItems.get(menuItem);
        }
        for (SetItem setItem : orderedSetItems.keySet()) {
            subTotal += setItem.getPrice() * orderedSetItems.get(setItem);
        }
    }

    private void calculateServiceChargeAmount() {
        serviceChargeAmount = subTotal * SERVICE_CHARGE_RATE;
    }

    private void calculateGSTAmount() {
        if (isMember) {
            GSTAmount = (subTotal + serviceChargeAmount) * 0.8 * GST_RATE;
        }
        else {
            GSTAmount = (subTotal + serviceChargeAmount) * GST_RATE;
        }
    }

    private void calculateTotal() {
        if (isMember) {
            total = (subTotal + serviceChargeAmount) * 0.8 + GSTAmount;
        }
        else {
            total = subTotal + serviceChargeAmount + GSTAmount;
        }
    }

    public void printOrderInvoice() {
        System.out.println("-------------------------------");
        System.out.println("Order ID: " + orderId);
        System.out.println("Staff: " + staffName);
        System.out.println("Table ID: " + tableId);
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("-------------------");
        System.out.println("MenuItems:");
        for (MenuItem menuItem : orderedMenuItems.keySet()) {
            System.out.println("||" + orderedMenuItems.get(menuItem) + " x " + menuItem.getName());
            System.out.println("||$" + df.format(menuItem.getPrice() * orderedMenuItems.get(menuItem)));
        }
        System.out.println("-------------------");
        System.out.println("SetItems:");
        for (SetItem setItem : orderedSetItems.keySet()) {
            System.out.println("||" + orderedSetItems.get(setItem) + " x " + setItem.getName());
            System.out.println("||$" + df.format(setItem.getPrice() * orderedSetItems.get(setItem)));
        }
        System.out.println("Sub Total: $" + df.format(subTotal));
        System.out.println("Service Charge: $" + df.format(serviceChargeAmount));
        if (isMember) {
            System.out.println("Discount: $" + df.format((subTotal * serviceChargeAmount) * 0.2));
        }
        System.out.println("GST: $" + df.format(GSTAmount));
        System.out.println("Total: $" + df.format(total));
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DAY_OF_MONTH);
        int hour = date.get(Calendar.HOUR_OF_DAY);
        int minute = date.get(Calendar.MINUTE);
        int second = date.get(Calendar.SECOND);
        System.out.printf("Paid on %02d/%02d/%4d %02d:%02d:%02d\n", day, month + 1, year, hour, minute, second);
    }
}
