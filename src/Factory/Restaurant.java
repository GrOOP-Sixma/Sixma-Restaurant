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
        this.setMenuFactory = new SetMenuFactory();

        orderFactory.setStaffController(staffFactory.getStaffController());
        orderFactory.setTableController(tableFactory.getTableController());
        orderFactory.setMenu(menuFactory.getMenu());

        reservationFactory.setCustomerController(customerFactory.getCustomerController());
        reservationFactory.setTableController(tableFactory.getTableController());
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
