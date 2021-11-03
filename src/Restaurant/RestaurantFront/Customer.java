package Restaurant.RestaurantFront;

import Restaurant.Person;

public class Customer extends Person {
    private int contactNo;
    private boolean isMember;

    // constructor
    public Customer(String name, int contactNo, boolean isMember) {
        super(name);
        this.contactNo = contactNo;
        this.isMember = isMember;
    }

    // getters
    public int getContactNo() {return contactNo;}
    public boolean isMember() {return isMember;}

    // setters
    public void setContactNo(int contactNo) {this.contactNo = contactNo;}
    public void setMember(boolean isMember) {this.isMember = isMember;}
}
