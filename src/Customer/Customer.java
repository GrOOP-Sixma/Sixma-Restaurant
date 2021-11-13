Custpackage Customer;

import Restaurant.Person;

public class Customer extends Person{
    private int contactNo;
    private int customerId;
    private static int nextCustomerId = 1;
    private boolean isMember;

    // constructors
    public Customer(String name, int contactNo, boolean isMember) {
        super(name);
        this.contactNo = contactNo;
        customerId = getNextCustomerId();
        this.isMember = isMember;
    }

    public Customer(String name, int contactNo, int customerId, boolean isMember) {
        super(name);
        this.contactNo = contactNo;
        this.customerId = customerId;
        this.isMember = isMember;
    }

    // getters
    public int getContactNo() {return contactNo;}
    public int getCustomerId() {return customerId;}
    public boolean isMember() {return isMember;}

    // setters
    public void setContactNo(int contactNo) {this.contactNo = contactNo;}
    public static void setNextCustomerId(int nextCustomerId) {Customer.nextCustomerId = nextCustomerId;}
    public void setMember(boolean isMember) {this.isMember = isMember;}

    // methods
    private static int getNextCustomerId() {
        return nextCustomerId++;
    }

    public void printCustomer() {
        System.out.println("-------------------------------");
        System.out.println("Customer ID: " + customerId);
        System.out.println("Name: " + this.getName());
        System.out.println("Contact Number: " + contactNo);
        if (isMember()) {
            System.out.println("Member: Yes");
        }
        else {
            System.out.println("Member: No");
        }
    }
}
