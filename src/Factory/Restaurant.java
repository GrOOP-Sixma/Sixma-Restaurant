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

    // constructor
    public Restaurant() {
        this.customerFactory = new CustomerFactory();
        this.staffFactory = new StaffFactory();
        this.tableFactory = new TableFactory();
        this.reservationFactory = new ReservationFactory(customerFactory.getCustomerController(), tableFactory.getTableController());
        this.menuFactory = new MenuFactory();
        this.setMenuFactory = new SetMenuFactory(menuFactory.getMenu());
        this.orderFactory = new OrderFactory(staffFactory.getStaffController(), tableFactory.getTableController(), reservationFactory.getReservationController(), menuFactory.getMenu(), setMenuFactory.getSetMenu());
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
