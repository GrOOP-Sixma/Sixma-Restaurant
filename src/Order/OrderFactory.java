package Order;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.HashMap;
import java.util.Scanner;

import Staff.Staff;
import Table.Table;
import Table.TableStatus;

import Staff.StaffController;
import Table.TableController;
import Reservation.ReservationController;
import Food.Menu;
import Food.MenuItem;
import Food.SetMenu;
import Food.SetItem;
/**
 * Represents a boundary class to get the user input 
 * in order for the OrderController class to perform
 * the various method executions on the Order and OrderInvoice class
 * @author tengwei
 * @version 1.0
 * @since 2021-11-14
 */
public class OrderFactory {
	/**
	 * The staffController of this OrderFactory
	 */
    private StaffController staffController;
    /**
     * The tableController of this OrderFactory
     */
    private TableController tableController;
    /**
     * The reservationController of this OrderFactory
     */
    private ReservationController reservationController;
    /**
     * The menu of this OrderFactory
     */
    private Menu menu;
    /**
     * The setMenu of this OrderFactory
     */
    private SetMenu setMenu;
    /**
     * The orderController of this OrderFactory
     */
    private OrderController orderController;
    /**
     * The name of this OrderFactory
     */
    private String name;

    /**
     * Creates a new OrderFactory with this OrderFactory's name, staffController, tableController, reservationController, menu and setMenu
     * @param name this OrderFactory's name
     * @param staffController this OrderFactory's staffController
     * @param tableController this OrderFactory's tableController
     * @param reservationController this OrderFactory's reservationController
     * @param menu this OrderFactory's menu
     * @param setMenu this OrderFactory's setMenu
     */
    public OrderFactory(String name, StaffController staffController, TableController tableController, ReservationController reservationController, Menu menu, SetMenu setMenu) {
        this.name = name;
        this.staffController = staffController;
        this.tableController = tableController;
        this.reservationController = reservationController;
        this.menu = menu;
        this.setMenu = setMenu;
        orderController = new OrderController(name, menu, setMenu);
    }

    // getters
    /**
     * Gets the staffController of this OrderFactory
     * @return this OrderFactory's staffController
     */
    public StaffController getStaffController() {return staffController;}
    /**
     * Gets the tableController of this OrderFactory
     * @return this OrderFactory's staffController
     */
    public TableController getTableController() {return tableController;}
    /**
     * Gets the menu of this OrderFactory
     * @return this OrderFactory's menu
     */
    public Menu getMenu() {return menu;}
    /**
     * Gets the setMenu of this OrderFactory
     * @return this OrderFactory's setMenu
     */
    public SetMenu getSetMenu() {return setMenu;}
    /**
     * Gets the orderController of this OrderFactory
     * @return this OrderFactory's orderController
     */
    public OrderController getOrderController() {return orderController;}

    // methods
    /**
     * Gets the input of the user for double data type
     * @return the user's input
     */
    public double getDoubleInput() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (sc.hasNextDouble()) {
                return sc.nextDouble();
            }
            else {
                System.out.println("Invalid input.");
                sc.next();
            }
        }
    }

    /**
     * Gets the input of the user for int data type
     * @return the user's input
     */
    public int getIntInput() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (sc.hasNextInt()) {
                return sc.nextInt();
            }
            else {
                System.out.println("Invalid input.");
                sc.next();
            }
        }
    }

    /**
     * Runs the OrderFactory to take in user input
     */
    public void run() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\nOrders Manager:");
            System.out.println("1. Create order");
            System.out.println("2. Add Item to existing order");
            System.out.println("3. Remove Item from existing order");
            System.out.println("4. View orders");
            System.out.println("5. Create order invoice");
            System.out.println("6. View order invoices");
            System.out.println("7. View Sales Report");
            System.out.println("0. Exit");
            loop: while (choice != 0) {
                choice = getIntInput();
                switch (choice) {
                    case 0:
                        continue;
                    case 1:
                        addOrder();
                        break loop;
                    case 2:
                        addToOrder();
                        break loop;
                    case 3:
                        removeFromOrder();
                        break loop;
                    case 4:
                        viewOrders();
                        break loop;
                    case 5:
                        createInvoice();
                        break loop;
                    case 6:
                        viewOrderInvoices();
                        break loop;
                    case 7:
                        viewSalesReport();
                        break loop;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }
    }

    /**
     * Create and add an order to this OrderFactory's orderController's orders
     */
    public void addOrder() {
        System.out.println("Enter your staff id:");
        int staffId = getIntInput();
        while (staffId <= 0) {
            System.out.println("Invalid id");
            staffId = getIntInput();
        }
        Staff staff = staffController.getStaff(staffId);
        if (staff == null) {
            System.out.println("There is no staff with id " + staffId);
            return;
        }

        System.out.println("Enter table id:");
        int tableId = getIntInput();
        while(tableId <= 0) {
            System.out.println("Invalid id");
            tableId = getIntInput();
        }
        Table table = tableController.getTable(tableId);
        if (table == null) {
            System.out.println("There is no table with id " + tableId);
            return;
        }
        else if (table.getStatus() == TableStatus.VACANT) {
            System.out.println("Table with id " + tableId + " is not occupied");
            return;
        }

        HashMap<MenuItem, Integer> orderedMenuItems = new HashMap<>();
        HashMap<SetItem, Integer> orderedSetItems = new HashMap<>();
        Order order = new Order(staff.getName(), table.getTableId(), orderedMenuItems, orderedSetItems);
        int choice1 = -1;
        while (choice1 != 0) {
            System.out.println("1. Add item");
            System.out.println("2. Remove item");
            System.out.println("0. Back");
            loop1: while (choice1 != 0) {
                choice1 = getIntInput();
                switch (choice1) {
                    case 0:
                        continue;
                    case 1:
                        int choice2 = -1;
                        while (choice2 != 0) {
                            System.out.println("1. Add menu item");
                            System.out.println("2. Add set item");
                            System.out.println("0. Back");
                            loop2: while (choice2 != 0) {
                                choice2 = getIntInput();
                                switch (choice2) {
                                    case 0:
                                        continue;
                                    case 1:
                                        menu.viewMenu();
                                        System.out.println("Enter id of menu item:");
                                        int menuItemId = getIntInput();
                                        while(menuItemId <= 0) {
                                            System.out.println("Invalid id");
                                            menuItemId = getIntInput();
                                        }
                                        MenuItem menuItem = menu.getMenuItem(menuItemId);
                                        if (menuItem == null) {
                                            System.out.println("There is no menu item with id " + menuItemId);
                                            continue;
                                        }

                                        System.out.println("Enter quantity:");
                                        int quantity = getIntInput();
                                        while (quantity <= 0) {
                                            System.out.println("Invalid quantity");
                                            quantity = getIntInput();
                                        }

                                        order.addMenuItem(menuItem, quantity);
                                        break loop2;
                                    case 2:
                                        setMenu.viewSetMenu();
                                        System.out.println("Enter id of set menu item:");
                                        int setItemId = getIntInput();
                                        while(setItemId <= 0) {
                                            System.out.println("Invalid id");
                                            setItemId = getIntInput();
                                        }
                                        SetItem setItem = setMenu.getSetItem(setItemId);
                                        if (setItem == null) {
                                            System.out.println("There is no set menu item with id " + setItemId);
                                            continue;
                                        }

                                        System.out.println("Enter quantity:");
                                        quantity = getIntInput();
                                        while (quantity <= 0) {
                                            System.out.println("Invalid quantity");
                                            quantity = getIntInput();
                                        }

                                        order.addSetItem(setItem, quantity);
                                        break loop2;
                                    default:
                                        System.out.println("Invalid choice");
                                }
                            }
                        }
                        break loop1;
                    case 2:
                        choice2 = -1;
                        while (choice2 != 0) {
                            System.out.println("1. Remove menu item");
                            System.out.println("2. Remove set item");
                            System.out.println("0. Back");
                            loop2:
                            while (choice2 != 0) {
                                choice2 = getIntInput();
                                switch (choice2) {
                                    case 0:
                                        continue;
                                    case 1:
                                        order.printOrder();
                                        System.out.println("Enter id of menu item:");
                                        int menuItemId = getIntInput();
                                        while(menuItemId <= 0) {
                                            System.out.println("Invalid id");
                                            menuItemId = getIntInput();
                                        }
                                        MenuItem menuItem = menu.getMenuItem(menuItemId);
                                        if (menuItem == null) {
                                            System.out.println("There is no menu item with id " + menuItemId);
                                            continue;
                                        }

                                        System.out.println("Enter quantity:");
                                        int quantity = getIntInput();
                                        while (quantity <= 0) {
                                            System.out.println("Invalid quantity");
                                            quantity = getIntInput();
                                        }

                                        if (order.removeMenuItem(menuItem, quantity) == 0) {
                                            System.out.println("The order does not consist of " + quantity + " " + menuItem.getName());
                                        }
                                        break loop2;
                                    case 2:
                                        order.printOrder();
                                        System.out.println("Enter id of set menu item:");
                                        int setItemId = getIntInput();
                                        while(setItemId <= 0) {
                                            System.out.println("Invalid id");
                                            setItemId = getIntInput();
                                        }
                                        SetItem setItem = setMenu.getSetItem(setItemId);
                                        if (setItem == null) {
                                            System.out.println("There is no menu item with id " + setItemId);
                                            continue;
                                        }

                                        System.out.println("Enter quantity:");
                                        quantity = getIntInput();
                                        while (quantity <= 0) {
                                            System.out.println("Invalid quantity");
                                            quantity = getIntInput();
                                        }

                                        if (order.removeSetItem(setItem, quantity) == 0) {
                                            System.out.println("The order does not consist of " + quantity + " " + setItem.getName());
                                        }
                                        break loop2;
                                    default:
                                        System.out.println("Invalid choice");
                                }
                            }
                        }
                        break loop1;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }
        orderController.addOrder(order);
        reservationController.finishReservation(table);
    }

    /**
     * Add menuItem or setItem to an existing order in this OrderFactory's orderController's orders
     */
    public void addToOrder() {
        System.out.println("Enter id of order to add items to:");
        int orderId = getIntInput();
        while(orderId <= 0) {
            System.out.println("Invalid id");
            orderId = getIntInput();
        }
        Order order = orderController.getOrder(orderId);
        if (order == null) {
            System.out.println("There is no order with id " + orderId);
            return;
        }

        int choice = -1;
        while (choice != 0) {
            System.out.println("1. Add menu item");
            System.out.println("2. Add set item");
            System.out.println("0. Back");
            loop: while (choice != 0) {
                choice = getIntInput();
                switch (choice) {
                    case 0:
                        continue;
                    case 1:
                        menu.viewMenu();
                        System.out.println("Enter id of menu item:");
                        int menuItemId = getIntInput();
                        while(menuItemId <= 0) {
                            System.out.println("Invalid id");
                            menuItemId = getIntInput();
                        }
                        MenuItem menuItem = menu.getMenuItem(menuItemId);
                        if (menuItem == null) {
                            System.out.println("There is no menu item with id " + menuItemId);
                            continue;
                        }

                        System.out.println("Enter quantity:");
                        int quantity = getIntInput();
                        while (quantity <= 0) {
                            System.out.println("Invalid quantity");
                            quantity = getIntInput();
                        }

                        order.addMenuItem(menuItem, quantity);
                        break loop;
                    case 2:
                        setMenu.viewSetMenu();
                        System.out.println("Enter id of set menu item:");
                        int setItemId = getIntInput();
                        while(setItemId <= 0) {
                            System.out.println("Invalid id");
                            setItemId = getIntInput();
                        }
                        SetItem setItem = setMenu.getSetItem(setItemId);
                        if (setItem == null) {
                            System.out.println("There is no menu item with id " + setItemId);
                            continue;
                        }

                        System.out.println("Enter quantity:");
                        quantity = getIntInput();
                        while (quantity <= 0) {
                            System.out.println("Invalid quantity");
                            quantity = getIntInput();
                        }

                        order.addSetItem(setItem, quantity);
                        break loop;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }
    }

    /**
     * Remove menuItem or setItem from an order in this OrderFactory's orderController's orders
     * if the order contains the quantity of menuItem or setItem
     */
    public void removeFromOrder() {
        System.out.println("Enter id of order to remove items from:");
        int orderId = getIntInput();
        while(orderId <= 0) {
            System.out.println("Invalid id");
            orderId = getIntInput();
        }
        Order order = orderController.getOrder(orderId);
        if (order == null) {
            System.out.println("There is no order with id " + orderId);
            return;
        }

        int choice = -1;
        while (choice != 0) {
            System.out.println("1. Remove menu item");
            System.out.println("2. Remove set item");
            System.out.println("0. Back");
            loop: while (choice != 0) {
                choice = getIntInput();
                switch (choice) {
                    case 0:
                        continue;
                    case 1:
                        order.printOrder();
                        System.out.println("Enter id of menu item:");
                        int menuItemId = getIntInput();
                        while(menuItemId <= 0) {
                            System.out.println("Invalid id");
                            menuItemId = getIntInput();
                        }
                        MenuItem menuItem = menu.getMenuItem(menuItemId);
                        if (menuItem == null) {
                            System.out.println("There is no menu item with id " + menuItemId);
                            continue;
                        }

                        System.out.println("Enter quantity:");
                        int quantity = getIntInput();
                        while (quantity <= 0) {
                            System.out.println("Invalid quantity");
                            quantity = getIntInput();
                        }

                        if (order.removeMenuItem(menuItem, quantity) == 0) {
                            System.out.println("The order does not consist of " + quantity + " " + menuItem.getName());
                        }
                        break loop;
                    case 2:
                        order.printOrder();
                        System.out.println("Enter id of set menu item:");
                        int setItemId = getIntInput();
                        while(setItemId <= 0) {
                            System.out.println("Invalid id");
                            setItemId = getIntInput();
                        }
                        SetItem setItem = setMenu.getSetItem(setItemId);
                        if (setItem == null) {
                            System.out.println("There is no menu item with id " + setItemId);
                            continue;
                        }

                        System.out.println("Enter quantity:");
                        quantity = getIntInput();
                        while (quantity <= 0) {
                            System.out.println("Invalid quantity");
                            quantity = getIntInput();
                        }

                        if (order.removeSetItem(setItem, quantity) == 0) {
                            System.out.println("The order does not consist of " + quantity + " " + setItem.getName());
                        }
                        break loop;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }
    }

    /**
     * View all of the order of this OrderFactory's orderController's orders
     */
    public void viewOrders() {
        orderController.viewOrders();
    }

    /**
     * Create and add an orderInvoice into this OrderFactory's orderController's orderInvoices
     * if the orderInvoices contains the orderInvoice
     */
    public void createInvoice() {
        System.out.println("Enter id of order to create invoice:");
        int orderId = getIntInput();
        while(orderId <= 0) {
            System.out.println("Invalid id");
            orderId = getIntInput();
        }
        Order order = orderController.getOrder(orderId);
        if (order == null) {
            System.out.println("There is no order with id " + orderId);
            return;
        }

        orderController.addOrderInvoice(order);
        Table table = tableController.getTable(order.getTableId());
        table.unreserveTable();
    }

    /**
     * View all of the orderInvoice in this OrderFactory's orderController's orderInvoices
     */
    public void viewOrderInvoices() {
        orderController.viewOrderInvoices();
    }

    /**
     * View the sales report of this OrderFactory 
     * on a specific day or month
     */
    public void viewSalesReport() {
        Scanner sc = new Scanner(System.in);
        System.out.println("View sales report by:");
        System.out.println("1. Day");
        System.out.println("2. Month");
        int choice = -1;
        while (choice != 0) {
            choice = getIntInput();
            switch (choice) {
                case 1:
                    System.out.println("Enter day (DD/MM/YYYY):");
                    String dateString = "";
                    boolean valid = false;
                    while (!valid) {
                        try {
                            dateString = sc.nextLine();
                            LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT));
                            valid = true;
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date");
                        }
                    }
                    String[] dateAttributes = dateString.split("/");;
                    int day = Integer.parseInt(dateAttributes[0]);
                    int month = Integer.parseInt(dateAttributes[1]) - 1;
                    int year = Integer.parseInt(dateAttributes[2]);

                    orderController.viewDaySalesReport(day, month, year);
                    break;
                case 2:
                    System.out.println("Enter month (MM/YYYY):");
                    dateString = "";
                    valid = false;
                    while (!valid) {
                        try {
                            dateString = sc.nextLine();
                            LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MM/uuuu").withResolverStyle(ResolverStyle.STRICT));
                            valid = true;
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date");
                        }
                    }
                    dateAttributes = dateString.split("/");;
                    month = Integer.parseInt(dateAttributes[1]) - 1;
                    year = Integer.parseInt(dateAttributes[2]);

                    orderController.viewMonthSalesReport(month, year);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    /**
     * Save all of the order and orderInvoices in this OrderFactory's orderController's orders and orderInvoices
     */
    public void writeInstances() {
        orderController.writeInstances();
    }
}
