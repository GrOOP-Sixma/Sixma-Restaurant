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
        // create all the factories
        this.reservationFactory = new ReservationFactory();
        this.customerFactory = new CustomerFactory();
        this.orderFactory = new OrderFactory();
        this.staffFactory = new StaffFactory();
        this.tableFactory = new TableFactory();
        this.menuFactory = new MenuFactory();
        this.setMenuFactory = new SetMenuFactory(menuFactory.getMenu());

        orderFactory.setStaffController(staffFactory.getStaffController());
        orderFactory.setTableController(tableFactory.getTableController());
        orderFactory.setMenu(menuFactory.getMenu());

        reservationFactory.setCustomerFactory(customerFactory);
        reservationFactory.setTableController(tableFactory.getTableController());
    }

    // run the factories
    public void reservationRun() {
        reservationFactory.run();
    }

    public void customerRun() {
        customerFactory.run();
    }

    public void orderRun() {
        orderFactory.run();
    }

    public void staffRun() {
        staffFactory.run();
    }

    public void tableRun() {
        tableFactory.run();
    }

    public void menuRun() {
        menuFactory.run();
    }

    public void setMenuRun() {
        setMenuFactory.run();
    }

    // convert to byte array
    public byte[] toByteArray() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
    
}
