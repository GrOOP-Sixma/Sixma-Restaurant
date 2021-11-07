package Restaurant.RestaurantFront;

import Restaurant.RestaurantBack.Staff;

import java.util.ArrayList;

public class CustomerController {
    private final ArrayList<Customer> customerList;

    // constructor
    public CustomerController() {
        customerList = new ArrayList<>();
    }

    // methods
    public void addCustomer(String name, int contactNo, boolean isMember) {
        Customer customer = new Customer(name, contactNo, isMember);
        customerList.add(customer);
    }

    public void removeCustomer(String name, int customerId) {
        for (int i=0; i<customerList.size(); i++) {
            if (customerList.get(i).getName().equals(name) && customerList.get(i).getCustomerId() == customerId) {
                customerList.remove(i);
                break;
            }
        }
    }

    public Customer getCustomer(int customerId) {
        for (Customer customer : customerList)
            if (customer.getCustomerId() == customerId)
                return customer;

        return null;
    }
}
