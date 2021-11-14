package Staff;

import Restaurant.Person;
import Restaurant.RestaurantBack.Gender;
/**
 * Represents a Staff that works at a restaurant
 * @author chris
 * @version 1.0
 * @since 2021-11-14
 */
public class Staff extends Person {
    /**
     * The Gender of this Staff
     */
    private Gender gender;
    /**
     * The Id of this Staff
     */
    private int staffId;
    /**
     * The Id of the next Staff
     */
    private static int nextStaffId = 1;
    /**
     * The role of this Staff
     */
    private String role;

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
     * Creates a new Staff with the staff's name, gender and role
     * @param name this Staff's name
     * @param gender this Staff's gender
     * @param role this Staff's role
     */
    public Staff(String name, Gender gender, String role) {
        super(name);
        this.gender = gender;
        staffId = getNextStaffId();
        this.role = role;
    }

    /**
     * Creates a new Staff with the staff's name, gender, staffId and role
     * @param name this Staff's name
     * @param gender this Staff's gender
     * @param staffId this Staff's staffId
     * @param role this Staff's role
     */
    public Staff(String name, Gender gender, int staffId, String role) {
        super(name);
        this.gender = gender;
        this.staffId = staffId;
        this.role = role;
    }

    // getters
    /**
     * Gets the Gender of this Staff
     * @return this Staff's gender
     */
    public Gender getGender() {return gender;}

    /**
     * Gets the staffId of this Staff
     * @return this Staff's staffId 
     */
    public int getStaffId() {return staffId;}

    /**
     * Gets the role of this Staff
     * @return this Staff's role
     */
    public String getRole() {return role;}

    // setters
    /**
     * Sets the gender for this Staff
     * @param gender this Staff's gender
     */
    public void setGender(Gender gender) {this.gender = gender;}
    
    /**
     * Sets the nextStaffId for this Staff
     * @param nextStaffId
     */
    public static void setNextStaffId(int nextStaffId) {Staff.nextStaffId = nextStaffId;}
    
    /**
     * Sets the role for this Staff
     * @param role
     */
    public void setRole(String role) {this.role = role;}

    // methods
    /**
     * Gets the next staffId
     * @return nextStaffId incremented by 1
     */
    private static int getNextStaffId() {
        return nextStaffId++;
    }

    /**
     * Prints the details of this Staff
     */
    public void printStaff() {
        System.out.println( ANSI_CYAN + "-------------------------------" + ANSI_RESET);
        System.out.println( ANSI_CYAN + "Staff ID: " + staffId + ANSI_RESET);
        System.out.println( ANSI_CYAN + "Name: " + this.getName() + ANSI_RESET);
        if (gender == Gender.MALE) {
            System.out.println( ANSI_CYAN + "Gender: Male" + ANSI_RESET);
        }
        else {
            System.out.println( ANSI_CYAN + "Gender: Female" + ANSI_RESET);
        }
        System.out.println( ANSI_CYAN + "Role: " + role + ANSI_RESET);
    }
}
