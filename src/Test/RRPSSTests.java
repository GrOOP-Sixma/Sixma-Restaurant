package Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.*;

import org.junit.jupiter.api.*;

import Customer.*;
import Factory.*;
import Food.*;
import Order.*;
import Reservation.ReservationController;
import Reservation.ReservationFactory;
import Restaurant.RestaurantBack.Gender;
import Staff.*;
import Table.*;

public class RRPSSTests {
    private final String RESTAURANT_NAME = "Test Restaurant";

    private final String CUSTOMER_NAME = "Test Customer";
    private final int CUSTOMER_CONTACT = 88888888;

    private final String STAFF_NAME = "Test Staff";
    private final Gender STAFF_GENDER = Gender.FEMALE; // fight the patriachy our team sausage party but the restaurant doesnt have to be
    private final String STAFf_ROLE = "Test Role";

    private final String MENU_NAME = "Test Menu Item";
    private final String MENU_DESCRIPTION = "Test Description";
    private final double MENU_PRICE = 10.00;
    private final FoodType MENU_TYPE = FoodType.MAIN_COURSE;

    private RRPSS rrpss;
    private Restaurant restaurant;
    private CustomerFactory customerFactory;
    private StaffFactory staffFactory;
    private TableFactory tableFactory;
    private MenuFactory menuFactory;
    private SetMenuFactory setMenuFactory;
    private ReservationFactory reservationFactory;
    private OrderFactory orderFactory;




    // before each setup
    @BeforeEach
    public void setup() {
        new File(System.getProperty("user.dir") + "/tmp").mkdir();

        // first create the RRPSS
        rrpss = new RRPSS(RESTAURANT_NAME);
        restaurant = rrpss.getRestaurant();

        // get the factories
        customerFactory = restaurant.getCustomerFactory();
        staffFactory = restaurant.getStaffFactory();
        tableFactory = restaurant.getTableFactory();
        menuFactory = restaurant.getMenuFactory();
        setMenuFactory = restaurant.getSetMenuFactory();
        reservationFactory = restaurant.getReservationFactory();
        orderFactory = restaurant.getOrderFactory();
    }

    // after each tear down
    @AfterEach
    public void tearDown() {
        rrpss = null;
        restaurant = null;
        customerFactory = null;
        staffFactory = null;
        tableFactory = null;
        menuFactory = null;
        setMenuFactory = null;
        reservationFactory = null;
        orderFactory = null;
    }

    // test if the RRPSS is created
    @Test
    public void testRRPSS() {
        assertNotNull(rrpss);
    }

    // test if the RRPSS is created with the correct name
    @Test
    public void testRRPSSName() {
        assertEquals(RESTAURANT_NAME, restaurant.getName());
    }


    // !!!!! test if the factories are created !!!!!
    @Test
    public void testFactories() {
        assertNotNull(customerFactory);
        assertNotNull(staffFactory);
        assertNotNull(tableFactory);
        assertNotNull(menuFactory);
        assertNotNull(setMenuFactory);
        assertNotNull(reservationFactory);
        assertNotNull(orderFactory);
    }

    // ! customer factory tests !
    // test if the customer factory is created
    @Test
    public void testCustomerFactory() {
        assertNotNull(customerFactory);
    }

    // test that there are no customers in the factory
    @Test
    public void testCustomerFactoryEmpty() {
        CustomerController customerController = customerFactory.getCustomerController();
        assertEquals(0, customerController.getCustomerList().size());
    }

    // create a customer and test if it is created
    @Test
    public void testCustomerFactoryCreate() {
        CustomerController customerController = customerFactory.getCustomerController();
        customerController.addCustomer(CUSTOMER_NAME, CUSTOMER_CONTACT, false);
        assertEquals(1, customerController.getCustomerList().size());
    }
    
    // remove the customer using id 1
    @Test
    public void testCustomerFactoryRemove() {
        CustomerController customerController = customerFactory.getCustomerController();
        customerController.addCustomer(CUSTOMER_NAME, CUSTOMER_CONTACT, false);
        customerController.removeCustomer(CUSTOMER_NAME,1);
        assertEquals(0, customerController.getCustomerList().size());
    }
    
    // check that get customer by id is correct
    @Test
    public void testCustomerFactoryGetCustomer() {
        CustomerController customerController = customerFactory.getCustomerController();
        customerController.addCustomer(CUSTOMER_NAME, CUSTOMER_CONTACT, false);
        assertEquals(CUSTOMER_NAME, customerController.getCustomer(1).getName());
    }

    // check that get customer by name and contact no is correct
    @Test
    public void testCustomerFactoryGetCustomerByNameAndContact() {
        CustomerController customerController = customerFactory.getCustomerController();
        customerController.addCustomer(CUSTOMER_NAME, CUSTOMER_CONTACT, false);
        assertEquals(CUSTOMER_NAME, customerController.getCustomer(CUSTOMER_NAME, CUSTOMER_CONTACT).getName());
    }

    // create a customer, write the customer to file and read it back in
    // @Test
    // public void testCustomerFactoryWriteToFile() {
    //     CustomerController customerController = customerFactory.getCustomerController();
    //     customerController.addCustomer(CUSTOMER_NAME, CUSTOMER_CONTACT, false);
    //     customerController.writeInstances();
    //     // reset the customer controller
    //     customerController = new CustomerController(RESTAURANT_NAME);
    //     customerController.readInstances();
    //     assertEquals(1, customerController.getCustomerList().size());
    // }

    // ! staff factory tests !
    // test if the staff factory is created
    @Test
    public void testStaffFactory() {
        assertNotNull(staffFactory);
    }

    // test that there are no staff in the factory
    @Test
    public void testStaffFactoryEmpty() {
        StaffController staffController = staffFactory.getStaffController();
        assertEquals(0, staffController.getStaffList().size());
    }

    // create a staff and test if it is created
    @Test
    public void testStaffFactoryCreate() {
        StaffController staffController = staffFactory.getStaffController();
        staffController.addStaff(STAFF_NAME, STAFF_GENDER, STAFf_ROLE);
        assertEquals(1, staffController.getStaffList().size());
    }

    // remove staff by id and name and check that size is 0
    @Test
    public void testStaffFactoryRemove() {
        StaffController staffController = staffFactory.getStaffController();
        staffController.removeStaff(STAFF_NAME, 1);
        assertEquals(0, staffController.getStaffList().size());
    }

    // check that get staff by id is correct
    @Test
    public void testStaffFactoryGetStaff() {
        StaffController staffController = staffFactory.getStaffController();
        staffController.addStaff(STAFF_NAME, STAFF_GENDER, STAFf_ROLE);
        assertEquals(STAFF_NAME, staffController.getStaff(1).getName());
    }

    // ! table factory tests !
    // test if the table factory is created
    @Test
    public void testTableFactory() {
        assertNotNull(tableFactory);
    }

    // test that there are no tables in the factory
    @Test
    public void testTableFactoryEmpty() {
        TableController tableController = tableFactory.getTableController();
        Map<Integer, ArrayList<Table>> tableMap = tableController.getTableMap();
        // loop through the map, check that each list is empty
        for (ArrayList<Table> list : tableMap.values()) {
            assertEquals(0, list.size());
        }
    }

    // create a 2 seater table and test if it is created
    @Test
    public void testTableFactoryCreate() {
        TableController tableController = tableFactory.getTableController();
        tableController.addTable(2);
        assertEquals(1, tableController.getTableMap().get(2).size());
    }

    // create a 2 seater table and check that no other tables are created
    @Test
    public void testTableFactoryCreateNoOtherTables() {
        TableController tableController = tableFactory.getTableController();
        tableController.addTable(2);
        assertNull(tableController.getTableMap().get(1));
        assertNotNull(tableController.getTableMap().get(2));
        assertNull(tableController.getTableMap().get(3));
    }

    // create a vacant 2 seater table and check getVacantTable works
    @Test
    public void testTableFactoryGetVacantTable() {
        TableController tableController = tableFactory.getTableController();
        tableController.addTable(2);
        assertNotNull(tableController.getVacantTable(2));
        assertEquals(2, tableController.getVacantTable(2).getNumSeats());
    }

    // create a 4 seater table, make it not vacant, and check that getVacantTable returns null
    // then make it vacant and check that getVacantTable returns the table
    @Test
    public void testTableFactoryGetVacantTableNotVacant() {
        TableController tableController = tableFactory.getTableController();
        tableController.addTable(4);
        tableController.getTableMap().get(4).get(0).setStatus(TableStatus.RESERVED);
        assertNull(tableController.getVacantTable(4));
        tableController.getTableMap().get(4).get(0).setStatus(TableStatus.VACANT);
        assertNotNull(tableController.getVacantTable(4));
    }

    // ! menu factory tests !
    // test if the menu factory is created
    @Test
    public void testMenuFactory() {
        assertNotNull(menuFactory);
    }

    // test that there are no menus in the factory
    @Test
    public void testMenuFactoryEmpty() {
        Menu menuController = menuFactory.getMenu();
        assertEquals(0, menuController.getMenu().size());
    }

    // create a menu item and test if it is created
    @Test
    public void testMenuFactoryCreate() {
        Menu menuController = menuFactory.getMenu();
        menuController.addMenuItem(MENU_NAME, MENU_PRICE, MENU_TYPE, MENU_DESCRIPTION);
        assertEquals(1, menuController.getMenu().size());
    }

    // remove menu item by id
    @Test
    public void testMenuFactoryRemove() {
        Menu menuController = menuFactory.getMenu();
        menuController.addMenuItem(MENU_NAME, MENU_PRICE, MENU_TYPE, MENU_DESCRIPTION);
        menuController.removeMenuItem(1);
        assertEquals(0, menuController.getMenu().size());
    }

    // check that get menu item by id is correct
    @Test
    public void testMenuFactoryGetMenuItem() {
        Menu menuController = menuFactory.getMenu();
        menuController.addMenuItem(MENU_NAME, MENU_PRICE, MENU_TYPE, MENU_DESCRIPTION);
        assertEquals(MENU_NAME, menuController.getMenuItem(1).getName());
        assertEquals(MENU_DESCRIPTION, menuController.getMenuItem(1).getDescription());
        assertEquals(MENU_PRICE, menuController.getMenuItem(1).getPrice());
    }

    // ! set menu factory tests !
    // test if the set menu factory is created
    @Test
    public void testSetMenuFactory() {
        assertNotNull(setMenuFactory);
    }

    // test that there are no set menus in the factory
    @Test
    public void testSetMenuFactoryEmpty() {
        SetMenu setMenuController = setMenuFactory.getSetMenu();
        assertEquals(0, setMenuController.getSetMenu().size());
    }

    // check that the set menu has menu in it
    @Test
    public void testSetMenuFactoryGetMenu() {
        SetMenu setMenuController = setMenuFactory.getSetMenu();
        assertEquals(menuFactory.getMenu(), setMenuController.getMenu());
    }

    // add an object to the main menu first
    // then add a set menu item and check that the set menu has the menu item
    @Test
    public void testSetMenuFactoryAddSetMenuItem() {
        SetMenu setMenuController = setMenuFactory.getSetMenu();
        Menu menuController = menuFactory.getMenu();
        menuController.addMenuItem(MENU_NAME, MENU_PRICE, MENU_TYPE, MENU_DESCRIPTION);
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(menuController.getMenuItem(1));
        setMenuController.addSetItem("Test Set", 5.0, 1, menuItems);
        assertEquals(1, setMenuController.getSetMenu().size());
    }

    // remove a set menu item and check that the set menu has no items
    @Test
    public void testSetMenuFactoryRemoveSetMenuItem() {
        SetMenu setMenuController = setMenuFactory.getSetMenu();
        Menu menuController = menuFactory.getMenu();
        menuController.addMenuItem(MENU_NAME, MENU_PRICE, MENU_TYPE, MENU_DESCRIPTION);
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(menuController.getMenuItem(1));
        setMenuController.addSetItem("Test Set", 5.0, 1, menuItems);
        setMenuController.removeSetItem(1);
        assertEquals(0, setMenuController.getSetMenu().size());
    }

    // check that get set menu item by id is correct
    @Test
    public void testSetMenuFactoryGetSetMenuItem() {
        SetMenu setMenuController = setMenuFactory.getSetMenu();
        Menu menuController = menuFactory.getMenu();
        menuController.addMenuItem(MENU_NAME, MENU_PRICE, MENU_TYPE, MENU_DESCRIPTION);
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(menuController.getMenuItem(1));
        setMenuController.addSetItem("Test Set", 5.0, 1, menuItems);
        assertEquals(1, setMenuController.getSetMenu().size());
        // for every menu item in set menu item, check that it is correct
        for (SetItem setMenuItem : setMenuController.getSetMenu()) {
            assertEquals(1, setMenuItem.getSetItems().size());
            assertEquals(MENU_NAME, setMenuItem.getSetItems().get(0).getName());
            assertEquals(MENU_DESCRIPTION, setMenuItem.getSetItems().get(0).getDescription());
            assertEquals(MENU_PRICE, setMenuItem.getSetItems().get(0).getPrice());
        }
        // check set price is correct
        assertEquals(5.0, setMenuController.getSetMenu().get(0).getPrice());
        // check set name is correct
        assertEquals("Test Set", setMenuController.getSetMenu().get(0).getName());
    }


    // ! reservation factory tests !
    // test if the reservation factory is created
    @Test
    public void testReservationFactory() {
        assertNotNull(reservationFactory);
    }

    // test that there are no reservations in the factory
    @Test
    public void testReservationFactoryEmpty() {
        ReservationController reservationController = reservationFactory.getReservationController();
        assertEquals(0, reservationController.getReservationList().size());
    }

    // create a reservation and test if it is created
    @Test
    public void testReservationFactoryCreate() {
        ReservationController reservationController = reservationFactory.getReservationController();
        Customer customer = new Customer(CUSTOMER_NAME, CUSTOMER_CONTACT, false);
        Calendar date = Calendar.getInstance();
        // create a 2 seater table // default is vacant
        tableFactory.getTableController().addTable(2);
        Table table = tableFactory.getTableController().getTable(1);
        reservationController.addReservation(date, customer, 2, table);
        assertEquals(1, reservationController.getReservationList().size());
    }

    // remove a reservation and test if it is removed
    @Test
    public void testReservationFactoryRemove() {
        ReservationController reservationController = reservationFactory.getReservationController();
        Customer customer = new Customer(CUSTOMER_NAME, CUSTOMER_CONTACT, false);
        Calendar date = Calendar.getInstance();
        // create a 2 seater table // default is vacant
        tableFactory.getTableController().addTable(2);
        Table table = tableFactory.getTableController().getTable(1);
        reservationController.addReservation(date, customer, 2, table);
        reservationController.removeReservation(1);
        assertEquals(0, reservationController.getReservationList().size());
    }

    // check that get reservation by id is correct
    @Test
    public void testReservationFactoryGetReservation() {
        ReservationController reservationController = reservationFactory.getReservationController();
        Customer customer = new Customer(CUSTOMER_NAME, CUSTOMER_CONTACT, false);
        Calendar date = Calendar.getInstance();
        // create a 2 seater table // default is vacant
        tableFactory.getTableController().addTable(2);
        Table table = tableFactory.getTableController().getTable(1);
        reservationController.addReservation(date, customer, 2, table);
        assertEquals(1, reservationController.getReservationList().size());
        assertEquals(CUSTOMER_NAME, reservationController.getReservation(1).getCustomer().getName());
        assertEquals(CUSTOMER_CONTACT, reservationController.getReservation(1).getCustomer().getContactNo());
        assertEquals(1, reservationController.getReservation(1).getTable().getTableId());
        assertEquals(2, reservationController.getReservation(1).getTable().getNumSeats());
        assertEquals(date, reservationController.getReservation(1).getReservationDate());
    }

    // create a reservation for a table and check that the table is occupied
    @Test
    public void testReservationFactoryTableOccupied() {
        ReservationController reservationController = reservationFactory.getReservationController();
        Customer customer = new Customer(CUSTOMER_NAME, CUSTOMER_CONTACT, false);
        Calendar date = Calendar.getInstance();
        // create a 2 seater table // default is vacant
        tableFactory.getTableController().addTable(2);
        Table table = tableFactory.getTableController().getTable(1);
        reservationController.addReservation(date, customer, 2, table);
        assertEquals(1, reservationController.getReservationList().size());
        assertEquals(1, table.getTableId());
        assertEquals(2, table.getNumSeats());
        assertEquals(TableStatus.RESERVED, table.getStatus());
    }

    // ! order factory tests !
    // test if the order factory is created
    @Test
    public void testOrderFactory() {
        assertNotNull(orderFactory);
    }

    // test that there are no orders in the factory
    @Test
    public void testOrderFactoryEmpty() {
        OrderController orderController = orderFactory.getOrderController();
        assertEquals(0, orderController.getOrders().size());
    }

    // create an order and test if it is created // not member
    @Test
    public void testOrderFactoryCreate() {
        OrderController orderController = orderFactory.getOrderController();
        // create a 2 seater table // default is vacant
        tableFactory.getTableController().addTable(2);
        // add a menu item to the menu
        menuFactory.getMenu().addMenuItem(MENU_NAME, MENU_PRICE, MENU_TYPE, MENU_DESCRIPTION);

        // order 3 of the menu item
        HashMap<MenuItem, Integer> orderItems = new HashMap<>();
        HashMap<SetItem, Integer> setOrderItems = new HashMap<>();
        orderItems.put(menuFactory.getMenu().getMenuItem(1), 3);

        // create the order
        orderController.addOrder(STAFF_NAME, 1, false, orderItems, setOrderItems);
        assertEquals(1, orderController.getOrders().size());
    }

    // remove an order and test if it is removed // not member
    @Test
    public void testOrderFactoryRemove() {
        OrderController orderController = orderFactory.getOrderController();
        // create a 2 seater table // default is vacant
        tableFactory.getTableController().addTable(2);
        // add a menu item to the menu
        menuFactory.getMenu().addMenuItem(MENU_NAME, MENU_PRICE, MENU_TYPE, MENU_DESCRIPTION);

        // order 3 of the menu item
        HashMap<MenuItem, Integer> orderItems = new HashMap<>();
        HashMap<SetItem, Integer> setOrderItems = new HashMap<>();
        orderItems.put(menuFactory.getMenu().getMenuItem(1), 3);

        // create the order
        orderController.addOrder(STAFF_NAME, 1, false, orderItems, setOrderItems);
        assertEquals(1, orderController.getOrders().size());
        orderController.removeOrder(1);
        assertEquals(0, orderController.getOrders().size());
    }

    // check that get order by id is correct
    @Test
    public void testOrderFactoryGetOrder() {
        OrderController orderController = orderFactory.getOrderController();
        // create a 2 seater table // default is vacant
        tableFactory.getTableController().addTable(2);
        // add a menu item to the menu
        menuFactory.getMenu().addMenuItem(MENU_NAME, MENU_PRICE, MENU_TYPE, MENU_DESCRIPTION);

        // order 3 of the menu item
        HashMap<MenuItem, Integer> orderItems = new HashMap<>();
        HashMap<SetItem, Integer> setOrderItems = new HashMap<>();
        orderItems.put(menuFactory.getMenu().getMenuItem(1), 3);

        // create the order
        orderController.addOrder(STAFF_NAME, 1, false, orderItems, setOrderItems);
        assertEquals(1, orderController.getOrders().size());
        assertEquals(STAFF_NAME, orderController.getOrder(1).getStaffName());
        assertEquals(1, orderController.getOrder(1).getTableId());
        assertEquals(3, orderController.getOrder(1).getOrderedMenuItems().get(menuFactory.getMenu().getMenuItem(1)));
    }

    // create order invoice from the order
    @Test
    public void testOrderFactoryInvoice() {
        OrderController orderController = orderFactory.getOrderController();
        // create a 2 seater table // default is vacant
        tableFactory.getTableController().addTable(2);
        // add a menu item to the menu
        menuFactory.getMenu().addMenuItem(MENU_NAME, MENU_PRICE, MENU_TYPE, MENU_DESCRIPTION);

        // order 3 of the menu item
        HashMap<MenuItem, Integer> orderItems = new HashMap<>();
        HashMap<SetItem, Integer> setOrderItems = new HashMap<>();
        orderItems.put(menuFactory.getMenu().getMenuItem(1), 3);

        // create the order
        orderController.addOrder(STAFF_NAME, 1, false, orderItems, setOrderItems);
        
        // create the invoice
        orderController.addOrderInvoice(orderController.getOrder(1));

        // check that the invoice is created
        assertEquals(1, orderController.getOrderInvoices().size());
    }

    // calculate the prices of the order invoice
    @Test
    public void testOrderFactoryInvoiceTotal() {
        OrderController orderController = orderFactory.getOrderController();
        // create a 2 seater table // default is vacant
        tableFactory.getTableController().addTable(2);
        // add a menu item to the menu
        menuFactory.getMenu().addMenuItem(MENU_NAME, MENU_PRICE, MENU_TYPE, MENU_DESCRIPTION);

        // order 3 of the menu item
        HashMap<MenuItem, Integer> orderItems = new HashMap<>();
        HashMap<SetItem, Integer> setOrderItems = new HashMap<>();
        orderItems.put(menuFactory.getMenu().getMenuItem(1), 3);

        // create the order
        orderController.addOrder(STAFF_NAME, 1, false, orderItems, setOrderItems);
        
        // create the invoice
        orderController.addOrderInvoice(orderController.getOrder(1));

        // check that the invoice is created
        assertEquals(1, orderController.getOrderInvoices().size());
        assertEquals(3 * MENU_PRICE, orderController.getOrderInvoices().get(0).getSubTotal());
        assertEquals((3 * MENU_PRICE) * 0.1, orderController.getOrderInvoices().get(0).getServiceChargeAmount());
        assertEquals((((3 * MENU_PRICE) * 0.1) + (3 * MENU_PRICE)) * 0.07, orderController.getOrderInvoices().get(0).getGSTAmount());
        assertEquals(((((3 * MENU_PRICE) * 0.1) + (3 * MENU_PRICE)) * 0.07) + ((3 * MENU_PRICE) * 0.1) + (3 * MENU_PRICE), orderController.getOrderInvoices().get(0).getTotal());
        assertEquals(1, orderController.getOrderInvoices().get(0).getOrderId());
        assertEquals(STAFF_NAME, orderController.getOrderInvoices().get(0).getStaffName());
        assertEquals(1, orderController.getOrderInvoices().get(0).getTableId());
    }

    // check that price calculation works for set menu too
    @Test
    public void testOrderFactoryInvoiceSetTotal() {
        OrderController orderController = orderFactory.getOrderController();
        // create a 2 seater table // default is vacant
        tableFactory.getTableController().addTable(2);
        // add a menu item to the menu
        menuFactory.getMenu().addMenuItem(MENU_NAME, MENU_PRICE, MENU_TYPE, MENU_DESCRIPTION);

        // create a set item
        SetMenu setMenuController = setMenuFactory.getSetMenu();
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(menuFactory.getMenu().getMenuItem(1));
        setMenuController.addSetItem("Test Set", 5.0, 1, menuItems);

        // order 3 of the set item
        HashMap<MenuItem, Integer> orderItems = new HashMap<>();
        HashMap<SetItem, Integer> setOrderItems = new HashMap<>();
        setOrderItems.put(setMenuController.getSetItem(1), 3);

        // create the order
        orderController.addOrder(STAFF_NAME, 1, false, orderItems, setOrderItems);
        
        // create the invoice
        orderController.addOrderInvoice(orderController.getOrder(1));

        // check that the invoice is created
        assertEquals(1, orderController.getOrderInvoices().size());
        assertEquals(3 * 5.0, orderController.getOrderInvoices().get(0).getSubTotal());
        assertEquals((3 * 5.0) * 0.1, orderController.getOrderInvoices().get(0).getServiceChargeAmount());
        assertEquals((((3 * 5.0) * 0.1) + (3 * 5.0)) * 0.07, orderController.getOrderInvoices().get(0).getGSTAmount());
        assertEquals(((((3 * 5.0) * 0.1) + (3 * 5.0)) * 0.07) + ((3 * 5.0) * 0.1) + (3 * 5.0), orderController.getOrderInvoices().get(0).getTotal());
        assertEquals(1, orderController.getOrderInvoices().get(0).getOrderId());
        assertEquals(STAFF_NAME, orderController.getOrderInvoices().get(0).getStaffName());
        assertEquals(1, orderController.getOrderInvoices().get(0).getTableId());
    }

    // price checking for members. members have a 20% discount before tax
    @Test
    public void testOrderFactoryInvoiceMemberTotal() {
        OrderController orderController = orderFactory.getOrderController();
        // create a 2 seater table // default is vacant
        tableFactory.getTableController().addTable(2);
        // add a menu item to the menu
        menuFactory.getMenu().addMenuItem(MENU_NAME, MENU_PRICE, MENU_TYPE, MENU_DESCRIPTION);

        // order 3 of the menu item
        HashMap<MenuItem, Integer> orderItems = new HashMap<>();
        HashMap<SetItem, Integer> setOrderItems = new HashMap<>();
        orderItems.put(menuFactory.getMenu().getMenuItem(1), 3);

        // create the order with member
        orderController.addOrder(STAFF_NAME, 1, true, orderItems, setOrderItems);
        
        // create the invoice
        orderController.addOrderInvoice(orderController.getOrder(1));

        // check that the invoice is created
        assertEquals(1, orderController.getOrderInvoices().size());
        assertEquals(3 * MENU_PRICE * 0.8, orderController.getOrderInvoices().get(0).getSubTotal());
        assertEquals((3 * MENU_PRICE * 0.8) * 0.1, orderController.getOrderInvoices().get(0).getServiceChargeAmount());
        assertEquals((((3 * MENU_PRICE * 0.8) * 0.1) + (3 * MENU_PRICE * 0.8)) * 0.07, orderController.getOrderInvoices().get(0).getGSTAmount());
        assertEquals(((((3 * MENU_PRICE * 0.8) * 0.1) + (3 * MENU_PRICE * 0.8)) * 0.07) + ((3 * MENU_PRICE * 0.8) * 0.1) + (3 * MENU_PRICE * 0.8), orderController.getOrderInvoices().get(0).getTotal());
    }

    // check that when 2 orders are made, one by a member and one by not a member, the member discount is applied
    @Test
    public void testOrderFactoryInvoiceMemberTotal2() {
        OrderController orderController = orderFactory.getOrderController();
        // create a 2 seater table // default is vacant
        tableFactory.getTableController().addTable(2);
        tableFactory.getTableController().addTable(2);
        // add a menu item to the menu
        menuFactory.getMenu().addMenuItem(MENU_NAME, MENU_PRICE, MENU_TYPE, MENU_DESCRIPTION);

        // order 3 of the menu item
        HashMap<MenuItem, Integer> orderItems = new HashMap<>();
        HashMap<SetItem, Integer> setOrderItems = new HashMap<>();
        orderItems.put(menuFactory.getMenu().getMenuItem(1), 3);

        // create the order with member
        orderController.addOrder(STAFF_NAME, 1, true, orderItems, setOrderItems);
        orderController.addOrder(STAFF_NAME, 2, false, orderItems, setOrderItems);
        // create the invoice
        orderController.addOrderInvoice(orderController.getOrder(1));
        orderController.addOrderInvoice(orderController.getOrder(2));

        // check that the invoice is created
        assertNotEquals(orderController.getOrderInvoice(1).getSubTotal(), orderController.getOrderInvoice(2).getSubTotal());
        assertNotEquals(orderController.getOrderInvoice(1).getServiceChargeAmount(), orderController.getOrderInvoice(2).getServiceChargeAmount());
        assertNotEquals(orderController.getOrderInvoice(1).getGSTAmount(), orderController.getOrderInvoice(2).getGSTAmount());
        assertNotEquals(orderController.getOrderInvoice(1).getTotal(), orderController.getOrderInvoice(2).getTotal());
    }

    // ! sales report tests
    // testing day sales
    @Test
    public void testOrderFactoryDaySales() {
        OrderController orderController = orderFactory.getOrderController();
        // create a 2 seater table // default is vacant
        tableFactory.getTableController().addTable(2);
        // add a menu item to the menu
        menuFactory.getMenu().addMenuItem(MENU_NAME, MENU_PRICE, MENU_TYPE, MENU_DESCRIPTION);

        // order 3 of the menu item
        HashMap<MenuItem, Integer> orderItems = new HashMap<>();
        HashMap<SetItem, Integer> setOrderItems = new HashMap<>();
        orderItems.put(menuFactory.getMenu().getMenuItem(1), 3);

        // create 2 of this order
        orderController.addOrder(STAFF_NAME, 1, false, orderItems, setOrderItems);
        orderController.addOrder(STAFF_NAME, 2, false, orderItems, setOrderItems);

        // create order invoices
        orderController.addOrderInvoice(orderController.getOrder(1));
        orderController.addOrderInvoice(orderController.getOrder(2));

        Calendar date = orderController.getOrderInvoice(1).getDate(); // lazy manually type
        int day = date.get(Calendar.DAY_OF_MONTH);
        int month = date.get(Calendar.MONTH);
        int year = date.get(Calendar.YEAR);

        // run the day sales report
        // total revenue should be 3 * MENU_PRICE * 2, and the service charge should be 0.1 * 3 * MENU_PRICE * 2
        assertEquals((double)(3 * MENU_PRICE * 2 + 3 * 2 * MENU_PRICE * 0.1), orderController.viewDaySalesReport(day, month, year));
    }

    // testing 1 member 1 not member
    @Test
    public void testOrderFactoryDaySales2() {
        OrderController orderController = orderFactory.getOrderController();
        // create a 2 seater table // default is vacant
        tableFactory.getTableController().addTable(2);
        // add a menu item to the menu
        menuFactory.getMenu().addMenuItem(MENU_NAME, MENU_PRICE, MENU_TYPE, MENU_DESCRIPTION);

        // order 3 of the menu item
        HashMap<MenuItem, Integer> orderItems = new HashMap<>();
        HashMap<SetItem, Integer> setOrderItems = new HashMap<>();
        orderItems.put(menuFactory.getMenu().getMenuItem(1), 3);

        // create 2 of this order
        orderController.addOrder(STAFF_NAME, 1, true, orderItems, setOrderItems);
        orderController.addOrder(STAFF_NAME, 2, false, orderItems, setOrderItems);

        // create order invoices
        orderController.addOrderInvoice(orderController.getOrder(1));
        orderController.addOrderInvoice(orderController.getOrder(2));

        Calendar date = orderController.getOrderInvoice(1).getDate(); // lazy manually type
        int day = date.get(Calendar.DAY_OF_MONTH);
        int month = date.get(Calendar.MONTH);
        int year = date.get(Calendar.YEAR);

        // run the day sales report
        // total revenue should be 3 * MENU_PRICE * 1.8, and the service charge should be 0.1 * 3 * MENU_PRICE * 0.8 + 0.1 * 3 * MENU_PRICE
        assertEquals((double)((3 * MENU_PRICE * 1.8) + (3 * MENU_PRICE * 0.1 * 0.8) + (3 * MENU_PRICE * 0.1)), orderController.viewDaySalesReport(day, month, year));
    }

    // testing month sales report
    @Test
    public void testOrderFactoryMonthSales() {
        OrderController orderController = orderFactory.getOrderController();
        // create a 2 seater table // default is vacant
        tableFactory.getTableController().addTable(2);
        // add a menu item to the menu
        menuFactory.getMenu().addMenuItem(MENU_NAME, MENU_PRICE, MENU_TYPE, MENU_DESCRIPTION);

        // order 3 of the menu item
        HashMap<MenuItem, Integer> orderItems = new HashMap<>();
        HashMap<SetItem, Integer> setOrderItems = new HashMap<>();
        orderItems.put(menuFactory.getMenu().getMenuItem(1), 3);

        // create 2 of this order
        orderController.addOrder(STAFF_NAME, 1, false, orderItems, setOrderItems);
        orderController.addOrder(STAFF_NAME, 2, false, orderItems, setOrderItems);

        // create order invoices
        orderController.addOrderInvoice(orderController.getOrder(1));
        orderController.addOrderInvoice(orderController.getOrder(2));

        Calendar date = orderController.getOrderInvoice(1).getDate(); // lazy manually type
        int day = date.get(Calendar.DAY_OF_MONTH);
        int month = date.get(Calendar.MONTH);
        int year = date.get(Calendar.YEAR);

        // run the month sales report
        // total revenue should be 3 * MENU_PRICE * 2, and the service charge should be 0.1 * 3 * MENU_PRICE * 2
        assertEquals((double)(3 * MENU_PRICE * 2 + 3 * 2 * MENU_PRICE * 0.1), orderController.viewMonthSalesReport(month, year));
    }

    // testing 1 member 1 not member
    @Test
    public void testOrderFactoryMonthSales2() {
        OrderController orderController = orderFactory.getOrderController();
        // create a 2 seater table // default is vacant
        tableFactory.getTableController().addTable(2);
        // add a menu item to the menu
        menuFactory.getMenu().addMenuItem(MENU_NAME, MENU_PRICE, MENU_TYPE, MENU_DESCRIPTION);

        // order 3 of the menu item
        HashMap<MenuItem, Integer> orderItems = new HashMap<>();
        HashMap<SetItem, Integer> setOrderItems = new HashMap<>();
        orderItems.put(menuFactory.getMenu().getMenuItem(1), 3);

        // create 2 of this order
        orderController.addOrder(STAFF_NAME, 1, true, orderItems, setOrderItems);
        orderController.addOrder(STAFF_NAME, 2, false, orderItems, setOrderItems);

        // create order invoices
        orderController.addOrderInvoice(orderController.getOrder(1));
        orderController.addOrderInvoice(orderController.getOrder(2));

        Calendar date = orderController.getOrderInvoice(1).getDate(); // lazy manually type
        int day = date.get(Calendar.DAY_OF_MONTH);
        int month = date.get(Calendar.MONTH);
        int year = date.get(Calendar.YEAR);

        // run the month sales report
        // total revenue should be 3 * MENU_PRICE * 1.8, and the service charge should be 0.1 * 3 * MENU_PRICE * 0.8 + 0.1 * 3 * MENU_PRICE
        assertEquals((double)((3 * MENU_PRICE * 1.8) + (3 * MENU_PRICE * 0.1 * 0.8) + (3 * MENU_PRICE * 0.1)), orderController.viewMonthSalesReport(month, year));
    }
}
