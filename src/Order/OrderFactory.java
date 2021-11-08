package Order;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import Staff.Staff;
import Table.Table;

import Staff.StaffController;
import Table.TableController;
import Food.Menu;

public class OrderFactory {
    private OrderController orderController;
    private StaffController staffController;
    private TableController tableController;
    private Menu menu;

    public OrderFactory() {orderController = new OrderController();}

    public void setStaffController(StaffController staffController) {this.staffController = staffController;}
    public void setTableController(TableController tableController) {this.tableController = tableController;}
    public void setMenu(Menu menu) {this.menu = menu;}

    public void run() {
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        while (choice != 0) {
            System.out.println("Enter 1 to create a new order");
            System.out.println("Enter 2 to change an existing order");
            System.out.println("Enter 0 to exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    createOrder();
                    break;
                case 2:
                    modifyOrder();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
            sc.close();
        }
    }

    // make new order
    public void createOrder() {
        System.out.println("Entering a new order...");

        // order id
        Random rand = new Random(orderController.getOrders().size());
        int orderId = rand.nextInt(100);
        while (orderController.getOrders().containsKey(orderId)) {
            orderId = rand.nextInt(100);
        }

        Scanner scanner = new Scanner(System.in);

        // staff id
        System.out.println("Enter your staff id");
        int staffId = scanner.nextInt();
        Staff staff = staffController.getStaff(staffId);

        // table id
        System.out.println("Enter your table id");
        int tableId = scanner.nextInt();
        Table table = tableController.getTable(tableId);

        // ordered food
        int choice = -1;
        HashMap<Integer, Integer> foodList = new HashMap<>();
        while (choice != 0) {
            System.out.println("Enter 1 to add a new food");
            System.out.println("Enter 2 to remove a food");
            System.out.println("Enter 0 to finish adding food");
            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("Enter food id");
                    int foodId = scanner.nextInt();
                    System.out.println("Enter quantity");
                    int quantity = scanner.nextInt();
                    foodList.put(foodId, quantity);
                    break;
                case 2:
                    System.out.println("Enter food id");
                    foodId = scanner.nextInt();
                    foodList.remove(foodId);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
            scanner.close();
        }

        Order order = new Order(staff, orderId, foodList, table);
        orderController.addOrder(order);
        System.out.println("Order created");
    }

    // modify order
    public void modifyOrder() {
        System.out.println("Enter id of order to be modified");
        Scanner scanner = new Scanner(System.in);
        int orderId = scanner.nextInt();
        Order order = orderController.getOrder(orderId);
        if (order == null) {
            System.out.println("Order not found");
            scanner.close();
            return;
        }
        HashMap<Integer, Integer> foodList = order.getOrderedItems();

        int choice = -1;
        while (choice != 0) {
            System.out.println("Enter 1 to add a new food");
            System.out.println("Enter 2 to remove a food");
            System.out.println("Enter 0 to finish adding food");
            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("Enter food id");
                    int foodId = scanner.nextInt();
                    System.out.println("Enter quantity");
                    int quantity = scanner.nextInt();
                    foodList.put(foodId, quantity);
                    break;
                case 2:
                    System.out.println("Enter food id");
                    foodId = scanner.nextInt();
                    foodList.remove(foodId);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
            order.setOrderedItems(foodList);
            scanner.close();
        }

        System.out.println("Order modified");
    }

    // calculate total price
    public void calculateTotalPrice() {
        System.out.println("Enter id of order to be calculated");
        Scanner scanner = new Scanner(System.in);
        int orderId = scanner.nextInt();
        Order order = orderController.getOrder(orderId);
        if (order == null) {
            System.out.println("Order not found");
            scanner.close();
            return;
        }
        double price = orderController.calculateTotalPrice(orderId, menu);
        System.out.println("Total price: " + price);
        scanner.close();
    }

    // create order invoice
    public void createInvoice() {
        System.out.println("Enter id of order to be created");
        Scanner scanner = new Scanner(System.in);
        int orderId = scanner.nextInt();
        Order order = orderController.getOrder(orderId);
        if (order == null) {
            System.out.println("Order not found");
            scanner.close();
            return;
        }
        OrderInvoice orderInvoice = new OrderInvoice(order);
        orderController.addOrderInvoice(orderInvoice, menu);
        scanner.close();
    }
}
