package Customer;

import Restaurant.Person;
/*
 * Represents a customer intending to go/at the restaurant
 */
public class Customer extends Person{
	/*
	 * The contact number of this Customer
	 */
    private int contactNo;
    /*
     * The unique customerId for each Customer
     */
    private int customerId;
    /*
     * The initialisation of the customerId
     */
    private static int nextCustomerId = 1;
    /*
     * Check whether this Customer is a member to be qualified for
     * membership discount
     */
    private boolean isMember;

    // constructors
    /*
     * Creates a new Customer with the customer's name, contact number and membership
     * @param name This Customer's name
     * @param contactNo This Customer's contact number
     * @param isMember This Customer's membership
     * 
     */
    public Customer(String name, int contactNo, boolean isMember) {
        super(name);
        this.contactNo = contactNo;
        customerId = getNextCustomerId();
        this.isMember = isMember;
    }
    
    /*
     * Creates a new Customer with the customer's name, contact number, customerId and membership
     * @param name This Customer's name
     * @param contactNo This Customer's contact number
     * @param customerId This Customer's unique customerId
     * @param isMember This Customer's membership
     */
    public Customer(String name, int contactNo, int customerId, boolean isMember) {
        super(name);
        this.contactNo = contactNo;
        this.customerId = customerId;
        this.isMember = isMember;
    }

    // getters
    /*
     * Gets the contact number of this Customer
     * @return this Customer's contact number
     */
    public int getContactNo() {return contactNo;}
    
    /*
     * Gets the customer Id of this Customer
     * @return this Customer's customerId
     */
    public int getCustomerId() {return customerId;}
    /*
     * Gets the membership for this Customer
     * @return this Customer's membership
     */
    public boolean isMember() {return isMember;}

    // setters
    /*
     * Sets contact number for this Customer
     * @param contactNo This Customer's contact number
     */
    public void setContactNo(int contactNo) {this.contactNo = contactNo;}
    /*
     * Sets the customerId of this Customer
     * @param nextCustomerId This Customer's customerId
     */
    public static void setNextCustomerId(int nextCustomerId) {Customer.nextCustomerId = nextCustomerId;}
    /*
     * Sets the membership for this Customer
     * @param isMember This Customer's membership
     */
    public void setMember(boolean isMember) {this.isMember = isMember;}

    // methods
    
    /*
     * Gets the next customerId
     * @return nextCustomerId incremented by 1
     */
    private static int getNextCustomerId() {
        return nextCustomerId++;
    }
    
    /*
     * Prints the details of this Customer
     */
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
