package Factory;

import Customer.*;
import Food.*;
import Order.*;
import Reservation.*;
import Staff.*;
import Table.*;

public class Restaurant {
    private ReservationFactory reservationFactory;
    private CustomerFactory customerFactory;
    private OrderFactory orderFactory;
    private StaffFactory staffFactory;
    private TableFactory tableFactory;
    private MenuFactory menuFactory;
    private SetMenuFactory setMenuFactory;
    private String name;

    // constructor
    public Restaurant(String name) {
        System.out.println("Welcome to Restuarant " + name);
        this.name = name;
        this.customerFactory = new CustomerFactory(name);
        this.staffFactory = new StaffFactory(name);
        this.tableFactory = new TableFactory(name);
        this.reservationFactory = new ReservationFactory(name, customerFactory.getCustomerController(), tableFactory.getTableController());
        this.menuFactory = new MenuFactory(name);
        this.setMenuFactory = new SetMenuFactory(name, menuFactory.getMenu());
        this.orderFactory = new OrderFactory(name, staffFactory.getStaffController(), tableFactory.getTableController(), reservationFactory.getReservationController(), menuFactory.getMenu(), setMenuFactory.getSetMenu());
    }

    public String getName() {
        return name;
    }

    public void customerRun() {
        customerFactory.run();
    }

    public void staffRun() {
        staffFactory.run();
    }

    public void tableRun() {
        tableFactory.run();
    }

    public void reservationRun() {
        reservationFactory.run();
    }

    public void menuRun() {
        menuFactory.run();
    }

    public void setMenuRun() {
        setMenuFactory.run();
    }

    public void orderRun() {
        orderFactory.run();
    }

    // getters
    public CustomerFactory getCustomerFactory() {
        return customerFactory;
    }

    public StaffFactory getStaffFactory() {
        return staffFactory;
    }

    public TableFactory getTableFactory() {
        return tableFactory;
    }

    public ReservationFactory getReservationFactory() {
        return reservationFactory;
    }

    public MenuFactory getMenuFactory() {
        return menuFactory;
    }

    public SetMenuFactory getSetMenuFactory() {
        return setMenuFactory;
    }

    public OrderFactory getOrderFactory() {
        return orderFactory;
    }

    
    public void writeInstances() {
        customerFactory.writeInstances();
        staffFactory.writeInstances();
        tableFactory.writeInstances();
        reservationFactory.writeInstances();
        menuFactory.writeInstances();
        setMenuFactory.writeInstances();
        orderFactory.writeInstances();
    }
}
