package Factory;

import java.io.*;
import java.util.*;

import Customer.*;
import Food.*;
import Order.*;
import Reservation.*;
import Staff.*;
import Table.*;
public class Restaurant implements Serializable {
    // attributes
    private String name;

    // controllers
    private ReservationController reservationController;
    private CustomerController customerController;
    private OrderController orderController;
    private StaffController staffController;
    private TableController tableController;
    private Menu menu;

    // factories
    private ReservationFactory reservationFactory;
    private CustomerFactory customerFactory;
    private OrderFactory orderFactory;
    private StaffFactory staffFactory;
    private TableFactory tableFactory;
    private MenuFactory menuFactory;
    private SetMenuFactory setMenuFactory;

    // constructor
    public Restaurant(String name) {
        this.reservationController = new ReservationController();
        this.customerController = new CustomerController();
        this.orderController = new OrderController();
        this.staffController = new StaffController();
        this.tableController = new TableController();
        this.menu = new Menu();
        this.name = name;

        // create all the factories
        this.reservationFactory = new ReservationFactory(this);
        this.customerFactory = new CustomerFactory(this);
        this.orderFactory = new OrderFactory(this);
        this.staffFactory = new StaffFactory(this);
        this.tableFactory = new TableFactory(this);
        this.menuFactory = new MenuFactory(this);
        this.setMenuFactory = new SetMenuFactory(this);
        
    }

    
    // dealing with controllers
    // * reservation
    public ReservationController getReservationController() {
        return this.reservationController;
    }

    public void addReservation(Reservation reservation) {
        this.reservationController.addReservation(reservation);
    }

    public Reservation getReservation(int id) {
        return this.reservationController.getReservation(id);
    }

    // * customer
    public CustomerController getCustomerController() {
        return this.customerController;
    }

    public void addCustomer(Customer customer) {
        this.customerController.addCustomer(customer);
    }

    public Customer getCustomer(String name2) {
        return this.customerController.getCustomer(name2);
    }

    // * order
    public OrderController getOrderController() {
        return this.orderController;
    }

    public void addOrder(Order order) {
        this.orderController.addOrder(order);
    }

    public Order getOrder(int id) {
        return this.orderController.getOrder(id);
    }

    public void addOrderInvoice(OrderInvoice orderInvoice) {
        this.orderController.addOrderInvoice(orderInvoice, menu);
    }

    // * staff
    public StaffController getStaffController() {
        return this.staffController;
    }

    public void addStaff(Staff staff) {
        this.staffController.addStaff(staff);
    }

    public Staff getStaff(int id) {
        return this.staffController.getStaff(id);
    }

    public void removeStaff(String name, int id) {
        this.staffController.removeStaff(name, id);
    }

    public void viewStaff() {
        this.staffController.viewStaff();
    }
    
    // * table

    public TableController getTableController() {
        return this.tableController;
    }


    public Table getTable(int id) {
        return this.tableController.getTable(id);
    }

    //* menu

    public Menu getMenu() {
        return this.menu;
    }

    public void addFood(MenuItem food) {
        this.menu.addFood(food);
    }

    public MenuItem getFood(int id) {
        return this.menu.getMenuItem(id);
    }


    public void viewReservations() {
        this.reservationController.viewReservations();
    }






 
    
    
    
    // // save restaurant to file
    // public void saveRestaurant() {
        //     // save restaurant to file
        //     try {
            //         FileOutputStream fileOut = new FileOutputStream(this.name + ".ser");
            //         ObjectOutputStream out = new ObjectOutputStream(fileOut);
            //         out.writeObject(this);
            //         out.close();
            //         fileOut.close();
            //         System.out.println("Serialized data is saved in" + this.name+ ".ser");
            //     } catch (IOException i) {
                //         i.printStackTrace();
                //     }
                // }
                
                // // load restaurant from file
                // public static Restaurant loadRestaurant(String name) {
                    //     Restaurant restaurant = null;
                    //     try {
                        //         FileInputStream fileIn = new FileInputStream(name + ".ser");
                        //         ObjectInputStream in = new ObjectInputStream(fileIn);
                        //         restaurant = (Restaurant) in.readObject();
                        //         in.close();
                        //         fileIn.close();
                        //     } catch (IOException i) {
                            //         i.printStackTrace();
                            //         return null;
                            //     } catch (ClassNotFoundException c) {
                                //         System.out.println("Restaurant class not found");
                                //         c.printStackTrace();
    //         return null;
    //     }
    //     return restaurant;
    // }
    // getters
}
