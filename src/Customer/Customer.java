package Customer;

import Restaurant.Person;
/**
 * Represents a customer intending to go/at the restaurant
 * @author tengwei
 * @version 1.0
 * @since 2021-11-14
 */
public class Customer extends Person{
	/**
	 * The contact number of this Customer
	 */
    private int contactNo;
    /**
     * The unique customerId for each Customer
     */
    private int customerId;
    /**
     * The initialisation of the customerId
     */
    private static int nextCustomerId = 1;
    /**
     * Check whether this Customer is a member to be qualified for
     * membership discount
     */
    private boolean isMember;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // constructors
    /**
     * Creates a new Customer with this Customer's name, contact number and membership
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
    
    /**
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
    /**
     * Gets the contact number of this Customer
     * @return this Customer's contact number
     */
    public int getContactNo() {return contactNo;}
    
    /**
     * Gets the customer Id of this Customer
     * @return this Customer's customerId
     */
    public int getCustomerId() {return customerId;}
    /**
     * Gets the membership for this Customer
     * @return this Customer's membership
     */
    public boolean isMember() {return isMember;}

    // setters
    /**
     * Sets contact number for this Customer
     * @param contactNo This Customer's contact number
     */
    public void setContactNo(int contactNo) {this.contactNo = contactNo;}
    /**
     * Sets the customerId of this Customer
     * @param nextCustomerId This Customer's customerId
     */
    public static void setNextCustomerId(int nextCustomerId) {Customer.nextCustomerId = nextCustomerId;}
    /**
     * Sets the membership for this Customer
     * @param isMember This Customer's membership
     */
    public void setMember(boolean isMember) {this.isMember = isMember;}

    // methods
    
    /**
     * Gets the next customerId
     * @return nextCustomerId incremented by 1
     */
    private static int getNextCustomerId() {
        return nextCustomerId++;
    }
    
    /**
     * Prints the details of this Customer
     */
    public void printCustomer() {
        System.out.println( ANSI_GREEN + "-------------------------------" + ANSI_RESET);
        System.out.println( ANSI_GREEN + "Customer ID: " + customerId + ANSI_RESET);
        System.out.println( ANSI_GREEN + "Name: " + this.getName() + ANSI_RESET);
        System.out.println( ANSI_GREEN + "Contact Number: " + contactNo + ANSI_RESET);
        if (isMember()) {
            System.out.println( ANSI_GREEN + "Member: Yes" + ANSI_RESET);
        }
        else {
            System.out.println( ANSI_GREEN + "Member: No" + ANSI_RESET);
        }
    }
}
