package Order;

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

public class OrderFactory {
    private StaffController staffController;
    private TableController tableController;
    private ReservationController reservationController;
    private Menu menu;
    private SetMenu setMenu;
    private OrderController orderController;

    public OrderFactory(StaffController staffController, TableController tableController, ReservationController reservationController, Menu menu, SetMenu setMenu) {
        this.staffController = staffController;
        this.tableController = tableController;
        this.reservationController = reservationController;
        this.menu = menu;
        this.setMenu = setMenu;
        orderController = new OrderController(menu, setMenu);
    }

    // getters
    public StaffController getStaffController() {return staffController;}
    public TableController getTableController() {return tableController;}
    public Menu getMenu() {return menu;}
    public SetMenu getSetMenu() {return setMenu;}
    public OrderController getOrderController() {return orderController;}

    // methods
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
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }
    }

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

    public void viewOrders() {
        orderController.viewOrders();
    }

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

    public void viewOrderInvoices() {
        orderController.viewOrderInvoices();
    }

    public void writeInstances() {
        orderController.writeInstances();
    }
}
