package Order;

import java.text.DecimalFormat;
import java.util.*;
import java.io.*;

import Food.Menu;
import Food.MenuItem;
import Food.SetItem;
import Food.SetMenu;

public class OrderController {
    private final Menu menu;
    private final SetMenu setMenu;
    private final ArrayList<Order> orders;
    private final ArrayList<OrderInvoice> orderInvoices;
    private String name;

    // constructors
    public OrderController(String name, Menu menu, SetMenu setMenu) {
        this.name = name;
        this.menu = menu;
        this.setMenu = setMenu;
        orders = new ArrayList<>();
        orderInvoices = new ArrayList<>();
        readInstances();
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public ArrayList<OrderInvoice> getOrderInvoices() {
        return orderInvoices;
    }

    // methods
    public void addOrder(String staffName, int tableId, HashMap<MenuItem, Integer> orderedMenuItems, HashMap<SetItem, Integer> orderedSetItems) {
        Order order = new Order(staffName, tableId, orderedMenuItems, orderedSetItems);
        orders.add(order);
    }

    public void addOrder(int orderId, String staffName, int tableId, HashMap<MenuItem, Integer> orderedMenuItems, HashMap<SetItem, Integer> orderedSetItems) {
        Order order = new Order(orderId, staffName, tableId, orderedMenuItems, orderedSetItems);
        orders.add(order);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public int removeOrder(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                orders.remove(order);
                return 1;
            }
        }
        return 0;
    }

    public Order getOrder(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null;
    }

    public void viewOrders() {
        System.out.println("\nOrders:");
        for (Order order : orders) {
            order.printOrder();
        }
    }

    public void addOrderInvoice(Order order) {
        removeOrder(order.getOrderId());
        OrderInvoice orderInvoice = new OrderInvoice(order);
        orderInvoices.add(orderInvoice);
    }

    public void addOrderInvoice(Order order, Calendar date, double subTotal, double serviceChargeAmount, double GSTAmount, double total) {
        OrderInvoice orderInvoice = new OrderInvoice(order, date, subTotal, serviceChargeAmount, GSTAmount, total);
        orderInvoices.add(orderInvoice);
    }

    public void addOrderInvoice(OrderInvoice orderInvoice) {
        orderInvoices.add(orderInvoice);
    }

    public OrderInvoice getOrderInvoice(int orderId) {
        for (OrderInvoice orderInvoice : orderInvoices) {
            if (orderInvoice.getOrderId() == orderId) {
                return orderInvoice;
            }
        }
        return null;
    }

    public void viewOrderInvoices() {
        System.out.println("Invoices:");
        for (OrderInvoice orderInvoice : orderInvoices) {
            orderInvoice.printOrderInvoice();
        }
    }

    public void viewDaySalesReport(int day, int month, int year) {
        HashMap<MenuItem, Integer> menuItemQuantity = new HashMap<>();
        HashMap<SetItem, Integer> setItemQuantity = new HashMap<>();
        double totalRevenue = 0;
        for (OrderInvoice orderInvoice : orderInvoices) {
            Calendar date = orderInvoice.getDate();
            int invoiceDay = date.get(Calendar.DAY_OF_MONTH);
            int invoiceMonth = date.get(Calendar.MONTH);
            int invoiceYear = date.get(Calendar.YEAR);
            if (invoiceDay == day && invoiceMonth == month && invoiceYear == year) {
                HashMap<MenuItem, Integer> orderedMenuItems = orderInvoice.getOrderedMenuItems();
                for (MenuItem menuItem : orderedMenuItems.keySet()) {
                    if (!menuItemQuantity.containsKey(menuItem)) {
                        menuItemQuantity.put(menuItem, orderedMenuItems.get(menuItem));
                    }
                    else {
                        menuItemQuantity.put(menuItem, menuItemQuantity.get(menuItem) + orderedMenuItems.get(menuItem));
                    }
                }

                HashMap<SetItem, Integer> orderedSetItems = orderInvoice.getOrderedSetItems();
                for (SetItem setItem : orderedSetItems.keySet()) {
                    if (!setItemQuantity.containsKey(setItem)) {
                        setItemQuantity.put(setItem, orderedSetItems.get(setItem));
                    }
                    else {
                        setItemQuantity.put(setItem, setItemQuantity.get(setItem) + orderedSetItems.get(setItem));
                    }
                }

                totalRevenue += orderInvoice.getSubTotal() + orderInvoice.getServiceChargeAmount();
            }
        }

        System.out.println("Sales Report for " + day + "/" + month + "/" + year + ":");
        System.out.println("-------------------");
        System.out.println("||Individual menu items sold:");
        for (MenuItem menuItem : menuItemQuantity.keySet()) {
            System.out.println("||" + menuItemQuantity.get(menuItem) + " x " + menuItem.getName());
        }
        System.out.println("-------------------");
        System.out.println("||Individual set items sold:");
        for (SetItem setItem : setItemQuantity.keySet()) {
            System.out.println("||" + setItemQuantity.get(setItem) + " x " + setItem.getName());
        }
        System.out.println("-------------------");
        System.out.println("||Revenue earned:");
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("||$" + df.format(totalRevenue));
    }

    public void viewMonthSalesReport(int month, int year) {
        HashMap<MenuItem, Integer> menuItemQuantity = new HashMap<>();
        HashMap<SetItem, Integer> setItemQuantity = new HashMap<>();
        double totalRevenue = 0;
        for (OrderInvoice orderInvoice : orderInvoices) {
            Calendar date = orderInvoice.getDate();
            int invoiceMonth = date.get(Calendar.MONTH);
            int invoiceYear = date.get(Calendar.YEAR);
            if (invoiceMonth == month && invoiceYear == year) {
                HashMap<MenuItem, Integer> orderedMenuItems = orderInvoice.getOrderedMenuItems();
                for (MenuItem menuItem : orderedMenuItems.keySet()) {
                    if (!menuItemQuantity.containsKey(menuItem)) {
                        menuItemQuantity.put(menuItem, orderedMenuItems.get(menuItem));
                    }
                    else {
                        menuItemQuantity.put(menuItem, menuItemQuantity.get(menuItem) + orderedMenuItems.get(menuItem));
                    }
                }

                HashMap<SetItem, Integer> orderedSetItems = orderInvoice.getOrderedSetItems();
                for (SetItem setItem : orderedSetItems.keySet()) {
                    if (!setItemQuantity.containsKey(setItem)) {
                        setItemQuantity.put(setItem, orderedSetItems.get(setItem));
                    }
                    else {
                        setItemQuantity.put(setItem, setItemQuantity.get(setItem) + orderedSetItems.get(setItem));
                    }
                }

                totalRevenue += orderInvoice.getSubTotal() + orderInvoice.getServiceChargeAmount();
            }
        }

        System.out.println("Sales Report for " + month + "/" + year + ":");
        System.out.println("-------------------");
        System.out.println("||Individual menu items sold:");
        for (MenuItem menuItem : menuItemQuantity.keySet()) {
            System.out.println("||" + menuItemQuantity.get(menuItem) + " x " + menuItem.getName());
        }
        System.out.println("-------------------");
        System.out.println("||Individual set items sold:");
        for (SetItem setItem : setItemQuantity.keySet()) {
            System.out.println("||" + setItemQuantity.get(setItem) + " x " + setItem.getName());
        }
        System.out.println("-------------------");
        System.out.println("||Revenue earned:");
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("||$" + df.format(totalRevenue));
    }

    public void writeInstances() {
        int orderId;
        String staffName;
        int tableId;
        int menuItemsSize;
        String orderedMenuItems;
        int setItemsSize;
        String orderedSetItems;
        try {
            File myObj = new File(System.getProperty("user.dir") + "/tmp/" + this.name  + "OrderInvoice.txt");
            myObj.createNewFile(); // if file already exists will do nothing
            FileWriter myWriter = new FileWriter(System.getProperty("user.dir") + "/tmp/" + this.name  + "Order.txt");
            for (Order order : orders) {
                orderId = order.getOrderId();
                staffName = order.getStaffName();
                tableId = order.getTableId();
                menuItemsSize = 0;
                orderedMenuItems = "";
                for (MenuItem menuItem : order.getOrderedMenuItems().keySet()) {
                    menuItemsSize++;
                    orderedMenuItems = orderedMenuItems + menuItem.getMenuItemId() + ";" + order.getOrderedMenuItems().get(menuItem) + ";";
                }
                orderedMenuItems = orderedMenuItems.substring(0, orderedMenuItems.length() - 1);
                setItemsSize = 0;
                orderedSetItems = "";
                for (SetItem setItem : order.getOrderedSetItems().keySet()) {
                    setItemsSize++;
                    orderedSetItems = orderedSetItems + setItem.getSetItemId() + ";" + order.getOrderedSetItems().get(setItem) + ";";
                }
                orderedSetItems = orderedSetItems.substring(0, orderedSetItems.length() - 1);
                myWriter.write(orderId + ";" + staffName + ";" + tableId + ";" + menuItemsSize + ";" + orderedMenuItems + ";" + setItemsSize + ";" + orderedSetItems + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Calendar date;
        double subTotal;
        double serviceChargeAmount;
        double GSTAmount;
        double total;
        try {
            File myObj = new File(System.getProperty("user.dir") + "/tmp/" + this.name  + "OrderInvoice.txt");
            myObj.createNewFile(); // if file already exists will do nothing
            FileWriter myWriter = new FileWriter(System.getProperty("user.dir") + "/tmp/" + this.name  + "OrderInvoice.txt");
            for (OrderInvoice orderInvoice : orderInvoices) {
                orderId = orderInvoice.getOrderId();
                staffName = orderInvoice.getStaffName();
                tableId = orderInvoice.getTableId();
                menuItemsSize = 0;
                orderedMenuItems = "";
                for (MenuItem menuItem : orderInvoice.getOrderedMenuItems().keySet()) {
                    menuItemsSize++;
                    orderedMenuItems = orderedMenuItems + menuItem.getMenuItemId() + ";" + orderInvoice.getOrderedMenuItems().get(menuItem) + ";";
                }
                orderedMenuItems = orderedMenuItems.substring(0, orderedMenuItems.length() - 1);
                setItemsSize = 0;
                orderedSetItems = "";
                for (SetItem setItem : orderInvoice.getOrderedSetItems().keySet()) {
                    setItemsSize++;
                    orderedSetItems = orderedSetItems + setItem.getSetItemId() + ";" + orderInvoice.getOrderedSetItems().get(setItem) + ";";
                }
                orderedSetItems = orderedSetItems.substring(0, orderedSetItems.length() - 1);
                date = orderInvoice.getDate();
                int year = date.get(Calendar.YEAR);
                int month = date.get(Calendar.MONTH);
                int day = date.get(Calendar.DAY_OF_MONTH);
                int hour = date.get(Calendar.HOUR_OF_DAY);
                int minute = date.get(Calendar.MINUTE);
                int second = date.get(Calendar.SECOND);
                subTotal = orderInvoice.getSubTotal();
                serviceChargeAmount = orderInvoice.getServiceChargeAmount();
                GSTAmount = orderInvoice.getGSTAmount();
                total = orderInvoice.getTotal();
                myWriter.write(orderId + ";" + staffName + ";" + tableId + ";" + menuItemsSize + ";" + orderedMenuItems + ";" + setItemsSize + ";" + orderedSetItems + ";" +
                        year + ";" + month + ";" + day + ";" + hour + ";" + minute + ";" + second + ";" + subTotal + ";" + serviceChargeAmount + ";" + GSTAmount + ";" + total + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void readInstances() {
        int orderId;
        String staffName;
        int tableId;
        int menuItemsSize;
        HashMap<MenuItem, Integer> orderedMenuItems;
        int setItemsSize;
        HashMap<SetItem, Integer> orderedSetItems;
        int maxOrderId = 0;
        try {
            File myObj = new File(System.getProperty("user.dir") + "/tmp/" + this.name  + "Order.txt");
            myObj.createNewFile(); // if file already exists will do nothing
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] attributes = data.split(";");
                orderId = Integer.parseInt(attributes[0]);
                staffName = attributes[1];
                tableId = Integer.parseInt(attributes[2]);
                menuItemsSize = Integer.parseInt(attributes[3]);
                orderedMenuItems = new HashMap<>();
                for (int i=0; i<menuItemsSize; i++) {
                    int menuItemId = Integer.parseInt(attributes[4 + 2 * i]);
                    MenuItem menuItem = menu.getMenuItem(menuItemId);
                    int quantity = Integer.parseInt(attributes[4 + 2 * i + 1]);
                    orderedMenuItems.put(menuItem, quantity);
                }
                setItemsSize = Integer.parseInt(attributes[4 + 2 * menuItemsSize]);
                orderedSetItems = new HashMap<>();
                for (int i=0; i<setItemsSize; i++) {
                    int setItemId = Integer.parseInt(attributes[4 + 2 * menuItemsSize + 1 + 2 * i]);
                    SetItem setItem = setMenu.getSetItem(setItemId);
                    int quantity = Integer.parseInt(attributes[4 + 2 * menuItemsSize + 1 + 2 * i + 1]);
                    orderedSetItems.put(setItem, quantity);
                }
                addOrder(orderId, staffName, tableId, orderedMenuItems, orderedSetItems);
                if (orderId > maxOrderId) {
                    maxOrderId = orderId;
                }
            }
            Order.setNextOrderId(maxOrderId + 1);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        int year;
        int month;
        int day;
        int hour;
        int minute;
        int second;
        double subTotal;
        double serviceChargeAmount;
        double GSTAmount;
        double total;
        try {
            File myObj = new File(System.getProperty("user.dir") + "/tmp/" + this.name  + "OrderInvoice.txt");
            myObj.createNewFile(); // if file already exists will do nothing
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] attributes = data.split(";");
                orderId = Integer.parseInt(attributes[0]);
                staffName = attributes[1];
                tableId = Integer.parseInt(attributes[2]);
                menuItemsSize = Integer.parseInt(attributes[3]);
                orderedMenuItems = new HashMap<>();
                for (int i=0; i<menuItemsSize; i++) {
                    int menuItemId = Integer.parseInt(attributes[4 + 2 * i]);
                    MenuItem menuItem = menu.getMenuItem(menuItemId);
                    int quantity = Integer.parseInt(attributes[4 + 2 * i + 1]);
                    orderedMenuItems.put(menuItem, quantity);
                }
                setItemsSize = Integer.parseInt(attributes[4 + 2 * menuItemsSize]);
                orderedSetItems = new HashMap<>();
                for (int i=0; i<setItemsSize; i++) {
                    int setItemId = Integer.parseInt(attributes[4 + 2 * menuItemsSize + 1 + 2 * i]);
                    SetItem setItem = setMenu.getSetItem(setItemId);
                    int quantity = Integer.parseInt(attributes[4 + 2 * menuItemsSize + 1 + 2 * i + 1]);
                    orderedSetItems.put(setItem, quantity);
                }
                Order order = new Order(orderId, staffName, tableId, orderedMenuItems, orderedSetItems);
                year = Integer.parseInt(attributes[4 + 2 * menuItemsSize + 2 * setItemsSize + 1]);
                month = Integer.parseInt(attributes[4 + 2 * menuItemsSize + 2 * setItemsSize + 2]);
                day = Integer.parseInt(attributes[4 + 2 * menuItemsSize + 2 * setItemsSize + 3]);
                hour = Integer.parseInt(attributes[4 + 2 * menuItemsSize + 2 * setItemsSize + 4]);
                minute = Integer.parseInt(attributes[4 + 2 * menuItemsSize + 2 * setItemsSize + 5]);
                second = Integer.parseInt(attributes[4 + 2 * menuItemsSize + 2 * setItemsSize + 6]);
                Calendar date = new GregorianCalendar(year, month, day, hour, minute, second);
                subTotal = Double.parseDouble(attributes[4 + 2 * menuItemsSize + 2 * setItemsSize + 7]);
                serviceChargeAmount = Double.parseDouble(attributes[4 + 2 * menuItemsSize + 2 * setItemsSize + 8]);
                GSTAmount = Double.parseDouble(attributes[4 + 2 * menuItemsSize + 2 * setItemsSize + 9]);
                total = Double.parseDouble(attributes[4 + 2 * menuItemsSize + 2 * setItemsSize + 10]);
                addOrderInvoice(order, date, subTotal, serviceChargeAmount, GSTAmount, total);
            }
            Order.setNextOrderId(maxOrderId + 1);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
