package Order;

import java.util.Calendar;
import java.text.DecimalFormat;

import Food.MenuItem;
import Food.SetItem;
/**
 * Represents an orderInvoice generated by an order from the Order class
 * @author tengwei
 * @version 1.0
 * @since 2021-11-14
 */
public class OrderInvoice extends Order{
	/**
	 * The date of this OrderInvoice
	 */
    private Calendar date;
    /**
     * The subTotal of this OrderInvoice
     */
    private double subTotal;
    /**
     * The serviceChargeAmount of this OrderInvoice
     */
    private double serviceChargeAmount;
    /**
     * The GSTAmount of this OrderInvoice
     */
    private double GSTAmount;
    /**
     * The total of this OrderInvoice
     */
    private double total;

    /**
     * {@value #GST_RATE}
     */
    private final static double GST_RATE = 0.07;
    /**
     * {@value #SERVICE_CHARGE_RATE}
     */
    private final static double SERVICE_CHARGE_RATE = 0.1;

    // constructors
    /**
     * Creates a new OrderInvoice with this OrderInvoice's order
     * @param order this OrderInvoice's order
     */
    public OrderInvoice(Order order) {
        super(order.getOrderId(), order.getStaffName(), order.getTableId(), order.isMember(), order.getOrderedMenuItems(), order.getOrderedSetItems());
        date = Calendar.getInstance();
        calculateSubtotal();
        calculateServiceChargeAmount();
        calculateGSTAmount();
        calculateTotal();
    }

    /**
     * Creates a new OrderInvoice with this OrderInvoice's order, date, subTotal, serviceChargeAmount, GSTAmount and total
     * @param order this OrderInvoice's order
     * @param date this OrderInvoice's date
     * @param subTotal this OrderInvoice's subTotal
     * @param serviceChargeAmount this OrderInvoice's serviceChargeAmount
     * @param GSTAmount this OrderInvoice's GSTAmount
     * @param total this OrderInvoice's total
     */
    public OrderInvoice(Order order, Calendar date, double subTotal, double serviceChargeAmount, double GSTAmount, double total) {
        super(order.getOrderId(), order.getStaffName(), order.getTableId(), order.isMember, order.getOrderedMenuItems(), order.getOrderedSetItems());
        this.date = date;
        this.subTotal = subTotal;
        this.serviceChargeAmount = serviceChargeAmount;
        this.GSTAmount = GSTAmount;
        this.total = total;
    }

    // getters
    /**
     * Gets the date of this OrderInvoice
     * @return this OrderInvoice's date
     */
    public Calendar getDate() {return date;}
    /**
     * Gets the subTotal of this OrderInvoice
     * @return this OrderInvoice's subTotal
     */
    public double getSubTotal() {return subTotal;}
    /**
     * Gets the serviceChargeAmount of this OrderInvoice
     * @return this OrderInvoice's serviceChargeAmount
     */
    public double getServiceChargeAmount() {return serviceChargeAmount;}
    /**
     * Gets the GSTAmount of this OrderInvoice
     * @return this OrderInvoice's GSTAmount
     */
    public double getGSTAmount() {return GSTAmount;}
    /**
     * Gets the total of this OrderInvoice
     * @return this OrderInvoice's total
     */
    public double getTotal() {return total;}

    // methods
    /**
     * Calculate the subTotal of this OrderInvoice
     */
    private void calculateSubtotal() {
        subTotal = 0;
        for (MenuItem menuItem : orderedMenuItems.keySet()) {
            subTotal += menuItem.getPrice() * orderedMenuItems.get(menuItem);
        }
        for (SetItem setItem : orderedSetItems.keySet()) {
            subTotal += setItem.getPrice() * orderedSetItems.get(setItem);
        }

        if (isMember) {
            subTotal *= 0.8;
        }
    }

    /**
     * Calculate the serviceChargeAmount of this OrderInvoice
     */
    private void calculateServiceChargeAmount() {
        serviceChargeAmount = subTotal * SERVICE_CHARGE_RATE;
    }

    /**
     * Calculate the GSTAmount of this OrderInvoice
     */
    private void calculateGSTAmount() {
        GSTAmount = (subTotal + serviceChargeAmount) * GST_RATE;
    }

    /**
     * Calculate the total of this OrderInvoice
     */
    private void calculateTotal() {
        total = subTotal + serviceChargeAmount + GSTAmount;
        total = Math.round(total * 1000.0) / 1000.0;
    }

    /**
     * Print this OrderInvoice
     */
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
            System.out.println("Discount: $" + df.format((subTotal) * 0.2));
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
