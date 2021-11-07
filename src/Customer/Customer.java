package Customer;

import Restaurant.Person;

public class Customer extends Person {
    private int contactNo;
    private int customerId;
    private static int nextCustomerId = 1;
    private boolean isMember;

    // constructor
    public Customer(String name, int contactNo, boolean isMember) {
        super(name);
        this.contactNo = contactNo;
        customerId = getNextCustomerId();
        this.isMember = isMember;
    }

    // getters
    public int getContactNo() {return contactNo;}
    public int getCustomerId() {return customerId;}
    public boolean isMember() {return isMember;}

    // setters
    public void setContactNo(int contactNo) {this.contactNo = contactNo;}
    public void setMember(boolean isMember) {this.isMember = isMember;}

    // methods
    private static int getNextCustomerId() {
        return nextCustomerId++;
    }
}
