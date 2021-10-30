package Factory;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.jar.Attributes.Name;

import Food.*;
import Order.*;
import RestuarantFront.*;
import RestaurantBack.*;
public class Restaurant implements Serializable {

    // attributes
    private String name;
    private Menu menu; // the menu of the restaurant
    private ArrayList<SetMenu> availablSetMenus; // the lists of available set menus
    private HashMap<Integer, Staff> employees; // the list of employees
    private SalesReport totalSales; // the total sales report
    private ArrayList<OrderInvoice> orders; // list of all orders
    private HashMap<Integer, Table> tables; // list of all tables
    private HashMap<String, Reservation> reservations; // list of all reservations

    // constructor
    public Restaurant(String name) {
        this.name = name;
        this.menu = new Menu();
        this.availablSetMenus = new ArrayList<>();
        this.employees = new HashMap<>();
        this.orders = new ArrayList<>();
        this.tables = new HashMap<>();
        this.totalSales = new SalesReport(orders, menu);
        this.reservations = new HashMap<>();
    }

    // getters
    public String getName() {
        return name;
    }

    public Menu getMenu() {
        return menu;
    }

    public ArrayList<SetMenu> getAvailablSetMenus() {
        return availablSetMenus;
    }

    public HashMap<Integer, Staff> getEmployees() {
        return employees;
    }

    public SalesReport getTotalSales() {
        return totalSales;
    }

    public ArrayList<OrderInvoice> getOrders() {
        return orders;
    }

    public HashMap<Integer, Table> getTables() {
        return tables;
    }

    // populate the restaurant

    // hire employees
    private Staff createEmployee(String name, int id, String role) {
        // create new staff
        Staff staff = new Staff(name, id, role);
        return staff;
    }

    public void addEmployee(String name, int id, String role) {
        Staff newEmployee = createEmployee(name, id, role);
        employees.put(id, newEmployee);
    }

    // get employee
    public Staff getEmployee(int id) {
        return employees.get(id);
    }

    // fire employees
    public void fireEmployee(int id) {
        employees.remove(id);
    }

    // populate the menu
    private MenuItem createMenuItem(FoodType type, String name, String description, double price) {
        // create new menu item
        MenuItem menuItem = new MenuItem(type, name, description, price);
        return menuItem;
    }

    public void addMenuItem(FoodType type, String name, String description, double price) {
        MenuItem newMenuItem = createMenuItem(type, name, description, price);
        menu.addItem(newMenuItem);
    }   

    // tables
    private Table createTable(int id, int capacity) { 
        // create new table
        Table table = new Table(id, capacity);
        return table;
    }

    public void addTables(int n) { // create n tables
        for (int i = 0; i < n; i++) {
            int id = i + 1;
            int capacity = 2 * ThreadLocalRandom.current().nextInt(1, 5);
            Table newTable = createTable(id, capacity);
            tables.put(id, newTable);
        }
    }

    public Table getTable(int id) {
        return tables.get(id);
    }

    // reservation
    public Boolean addReservation(int numOfPax, int customerContactNo, String customerName, Calendar reservationTime, Calendar reservationDate, Table tableNo) {
        Boolean success = false;
        // create new reservation
        // check if table status is vacant
        if (tables.get(tableNo.getTableID()).getStatus() == TableStatus.VACANT) {
            Reservation reservation = new Reservation(numOfPax, customerContactNo, customerContactNo, customerName, reservationTime, reservationDate, tableNo, null);
            tables.get(tableNo.getTableID()).reserve();
            reservations.put(reservation.getCustomerName(), reservation);
            success = true;
        }
        return success;
    }

    public Boolean cancelReservation(String customerName) {
        Boolean success = false;
        // cancel reservation
        if (reservations.containsKey(customerName)) {
            Reservation reservation = reservations.get(customerName);
            reservation.cancelReservation();
            success = true;
        }

        return success;
    }

    public void changeReservationDate(String customerName, Calendar reservationDate) {
        // change reservation date
        if (reservations.containsKey(customerName)) {
            Reservation reservation = reservations.get(customerName);
            reservation.changeReservationDate(reservationDate);
        }
    }

    // orders
    public ArrayList<MenuItem> getOrderedItems() {
        Boolean done = false;
        ArrayList<MenuItem> orderedItems = new ArrayList<>();
        while (!done) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the item name or type 'done' to finish: ");
            String itemName = sc.nextLine();
            if (itemName.equals("done")) {
                done = true;
            } else {
                // loop through menu items and getting id
                Boolean hasItem = false;
                for (MenuItem item : menu.getAllItems()) {
                    if (item.getName().equals(itemName)) {
                        orderedItems.add(item);
                        hasItem = true;
                    }
                }
                if (!hasItem) {
                    System.out.println("Item not found");
                }
            }
            sc.close();
        }
        return orderedItems;
    }

    public void takeOrder(Staff staffOrder, Reservation reservationName, ArrayList<MenuItem> orderItems, Table tableID, Boolean isReservation) {
        // create new order
        int orderID = orders.size() + 1;
        Calendar currentTime = Calendar.getInstance();
        OrderInvoice newOrder = new OrderInvoice(staffOrder, orderID, reservationName, orderItems, tableID, isReservation, currentTime);
        orders.add(newOrder);
    }

    public void viewOrder(int orderID) {
        // view order
        for (OrderInvoice order : orders) {
            if (order.getOrderID() == orderID) {
                order.printOrder();
            }
        }
    }

    public void printOrderInvoice(int orderID) {
        // print order invoice
        for (OrderInvoice order : orders) {
            if (order.getOrderID() == orderID) {
                order.printOrderInvoice();
            }
        }
    }

    // create set menu
    public void createSetMenu(String name) {
        // create new set menu
        SetMenu newSetMenu = new SetMenu(name);
        availablSetMenus.add(newSetMenu);
    }

    public void addItemToSetMenu(String name, MenuItem item) {
        // add item to set menu
        for (SetMenu setMenu : availablSetMenus) {
            if (setMenu.getName().equals(name)) {
                setMenu.addItem(item);
            }
        }
    }

    public void removeItemFromSetMenu(String name, MenuItem item) {
        // remove item from set menu
        for (SetMenu setMenu : availablSetMenus) {
            if (setMenu.getName().equals(name)) {
                setMenu.removeItem(item);
            }
        }
    }

    public void removeSetMenu(String name) {
        // remove set menu
        for (SetMenu setMenu : availablSetMenus) {
            if (setMenu.getName().equals(name)) {
                availablSetMenus.remove(setMenu);
            }
        }
    }

    // print menus
    public void printMenu() {
        // print menu
        menu.print();
    }

    public void printSetMenus() {
        // print set menus
        for (SetMenu setMenu : availablSetMenus) {
            setMenu.print();
        }
    }

    // accountancy
    public void createNewSalesReport() {
        // create new sales report
        totalSales = new SalesReport(orders, menu);
        // todo accept set menus
    }

    public void printSalesReport(int month, int year) {
        // print sales report
        totalSales.printSalesRevenueReport(month, year);
    }

    // print restaurant details
    // todo
    // public void printRestaurantDetails() {
    //     // print restaurant details
    //     System.out.println("Restaurant Details");
    //     System.out.println("==================");
    //     System.out.println("Restaurant Name: " + restaurantName);
    //     System.out.println("Restaurant Address: " + restaurantAddress);
    //     System.out.println("Restaurant Phone Number: " + restaurantPhoneNumber);
    //     System.out.println("Restaurant Email: " + restaurantEmail);
    //     System.out.println("Restaurant Website: " + restaurantWebsite);
    //     System.out.println("Restaurant Opening Hours: " + restaurantOpeningHours);
    //     System.out.println("Restaurant Closing Hours: " + restaurantClosingHours);
    //     System.out.println("Restaurant Manager: " + restaurantManager);
    //     System.out.println("Restaurant Manager Contact Number: " + restaurantManagerContactNumber);
    //     System.out.println("Restaurant Manager Email: " + restaurantManagerEmail);
    //     System.out.println("Restaurant Manager Website: " + restaurantManagerWebsite);
    //     System.out.println("Restaurant Manager Opening Hours: " + restaurantManagerOpeningHours);
    //     System.out.println("Restaurant Manager Closing Hours: " + restaurantManagerClosingHours);
    // }

    // save restaurant to file
    public void saveRestaurant() {
        // save restaurant to file
        try {
            FileOutputStream fileOut = new FileOutputStream(this.name + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in" + this.name+ ".ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    // load restaurant from file
    public static Restaurant loadRestaurant(String name) {
        Restaurant restaurant = null;
        try {
            FileInputStream fileIn = new FileInputStream(name + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            restaurant = (Restaurant) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Restaurant class not found");
            c.printStackTrace();
            return null;
        }
        return restaurant;
    }
}
