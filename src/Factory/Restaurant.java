package Factory;

import Customer.*;
import Food.*;
import Order.*;
import Reservation.*;
import Staff.*;
import Table.*;
/**
 * Represents a Restaurant boundary class to run the individual factories
 * according to user's input
 * @author tengwei
 * @version 1.0
 * @since 2021-11-14
 */
public class Restaurant {
	/**
	 * The reservationFactory of this Restaurant
	 */
    private ReservationFactory reservationFactory;
	/**
	 * The customerFactory of this Restaurant
	 */
    private CustomerFactory customerFactory;
	/**
	 * The orderFactory of this Restaurant
	 */
    private OrderFactory orderFactory;
	/**
	 * The staffFactory of this Restaurant
	 */
    private StaffFactory staffFactory;
	/**
	 * The tableFactory of this Restaurant
	 */
    private TableFactory tableFactory;
	/**
	 * The menuFactory of this Restaurant
	 */
    private MenuFactory menuFactory;
	/**
	 * The setMenuFactory of this Restaurant
	 */
    private SetMenuFactory setMenuFactory;
	/**
	 * The name of this Restaurant
	 */
    private String name;

    // constructor
    /**
     * Creates a new Restaurant with the given name
     * @param name This Restaurant's name
     */
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
    
    /**
     * Gets this Restaurant's name
     * @return name of this Restaurant
     */
    public String getName() {
        return name;
    }
    /**
     * Runs the customerFactory
     */
    public void customerRun() {
        customerFactory.run();
    }
    
    /**
     * Runs the staffFactory
     */
    public void staffRun() {
        staffFactory.run();
    }
    /**
     * Runs the tableFactory
     */
    public void tableRun() {
        tableFactory.run();
    }
    /**
     * Runs the reservationFactory
     */
    public void reservationRun() {
        reservationFactory.run();
    }
    /**
     * Runs the menuFactory
     */
    public void menuRun() {
        menuFactory.run();
    }
    /**
     * Runs the setMenuFactory
     */
    public void setMenuRun() {
        setMenuFactory.run();
    }
    /**
     * Runs the orderFactory
     */
    public void orderRun() {
        orderFactory.run();
    }

    // getters
    /**
     * Gets this Restaurant's customerFactory
     * @return this Restaurant's customerFactory
     */
    public CustomerFactory getCustomerFactory() {
        return customerFactory;
    }
    /**
     * Gets this Restaurant's staffFactory
     * @return this Restaurant's staffFactory
     */
    public StaffFactory getStaffFactory() {
        return staffFactory;
    }
    /**
     * Gets this Restaurant's tableFactory
     * @return this Restaurant's tableFactory
     */
    public TableFactory getTableFactory() {
        return tableFactory;
    }
    /**
     * Gets this Restaurant's reservationFactory
     * @return this Restaurant's reservationFactory
     */
    public ReservationFactory getReservationFactory() {
        return reservationFactory;
    }
    /**
     * Gets this Restaurant's menuFactory
     * @return this Restaurant's menuFactory
     */
    public MenuFactory getMenuFactory() {
        return menuFactory;
    }
    /**
     * Gets this Restaurant's setMenuFactory
     * @return this Restaurant's setMenuFactory
     */
    public SetMenuFactory getSetMenuFactory() {
        return setMenuFactory;
    }
    /**
     * Gets this Restaurant's orderFactory
     * @return this Restaurant's orderFactory
     */
    public OrderFactory getOrderFactory() {
        return orderFactory;
    }

    /**
     * Save the factories into a file
     */
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
