package Customer;

import Restaurant.Person;

public class Customer extends Person{
    private int contactNo;
    private int customerId;
    private static int nextCustomerId = 1;
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
