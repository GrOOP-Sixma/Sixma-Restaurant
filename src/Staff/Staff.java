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
        System.out.println("-------------------------------");
        System.out.println("Staff ID: " + staffId);
        System.out.println("Name: " + this.getName());
        if (gender == Gender.MALE) {
            System.out.println("Gender: Male");
        }
        else {
            System.out.println("Gender: Female");
        }
        System.out.println("Role: " + role);
    }
}
