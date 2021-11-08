package Order;

import java.util.HashMap;

import Food.Menu;
import Food.SetItem;

public class OrderController {
    private HashMap<Integer, Order> orders;
    private HashMap<Integer, OrderInvoice> orderInvoices;

    // constructor
    public OrderController() {
        orders = new HashMap<>();
        orderInvoices = new HashMap<>();
    }

    // getter
    public HashMap<Integer, Order> getOrders() {
        return orders;
    }

    public HashMap<Integer, OrderInvoice> getOrderInvoices() {
        return orderInvoices;
    }

    // ! order logic
    // add order
    public void addOrder(Order order) {
        orders.put(order.getOrderID(), order);
    }

    // remove order
    public void removeOrder(int orderId) {
        orders.remove(orderId);
    }

    // get order
    public Order getOrder(int orderId) {
        return orders.get(orderId);
    }

    // calculate total price
    public double calculateTotalPrice(int orderId, Menu menu) {
        double totalPrice = 0;
        Order order = orders.get(orderId);
        HashMap<Integer, Integer> orderItems = order.getOrderedItems(); // id, quantity
        for (int id : orderItems.keySet()) {
            totalPrice += menu.getMenuItem(id).getPrice() * orderItems.get(id);
        }
        HashMap<SetItem, Integer> setItems = order.getOrderedSetItems();
        for (SetItem setItem : setItems.keySet()) {
            totalPrice += setItem.getPrice() * setItems.get(setItem);
        }
        return totalPrice;
    }

    // print order
    public void printOrder(int orderId, Menu menu) {
        Order order = orders.get(orderId);
        System.out.println("Order ID: " + order.getOrderID());
        System.out.println("Order Items: ");
        HashMap<Integer, Integer> orderItems = order.getOrderedItems();
        for (int id : orderItems.keySet()) {
            System.out.println("\t" + menu.getMenuItem(id).getName() + " x " + orderItems.get(id));
        }
        System.out.println("Order Set Items: ");
        HashMap<SetItem, Integer> setItems = order.getOrderedSetItems();
        for (SetItem setItem : setItems.keySet()) {
            System.out.println("\t" + setItem.getName() + " x " + setItems.get(setItem));
        }
    }

    // ! order invoice logic
    // add order invoice
    public void addOrderInvoice(OrderInvoice orderInvoice, Menu menu) {
        setGSTAmount(orderInvoice, menu);
        setSubtotal(orderInvoice, menu);
        setServiceCharge(orderInvoice, menu);
        setTotal(orderInvoice, menu);
        orderInvoices.put(orderInvoice.getOrderID(), orderInvoice);
    }

    // set subtotal
    public void setSubtotal(OrderInvoice orderInvoice, Menu menu) {
        orderInvoice.setSubtotal(calculateTotalPrice(orderInvoice.getOrderID(), menu));
    }

    // set tax
    public void setGSTAmount(OrderInvoice orderInvoice, Menu menu) {
        orderInvoice.setGSTAmount(orderInvoice.getSubtotal() * OrderInvoice.GST);
    }

    // set service charge
    public void setServiceCharge(OrderInvoice orderInvoice, Menu menu) {
        orderInvoice.setServiceCharge(orderInvoice.getSubtotal() * OrderInvoice.SERVICE_CHARGE);
    }

    // set total
    public void setTotal(OrderInvoice orderInvoice, Menu menu) {
        orderInvoice.setTotal(orderInvoice.getSubtotal() + orderInvoice.getGSTAmount() + orderInvoice.getServiceCharge());
    }

    // print order invoice
    public void printOrderInvoice(int orderId, Menu menu) {
        OrderInvoice orderInvoice = orderInvoices.get(orderId);
        System.out.println("Order ID: " + orderInvoice.getOrderID());
        printOrder(orderId, menu);
        System.out.println("Subtotal: " + orderInvoice.getSubtotal());
        System.out.println("GST Amount: " + orderInvoice.getGSTAmount());
        System.out.println("Service Charge: " + orderInvoice.getServiceCharge());
        System.out.println("Total: " + orderInvoice.getTotal());
    }

}
