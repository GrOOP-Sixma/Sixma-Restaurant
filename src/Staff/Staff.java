package Staff;

import Restaurant.Person;
import Restaurant.RestaurantBack.Gender;

public class Staff extends Person {
    private Gender gender;
    private int staffId;
    private static int nextStaffId = 1;
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
    public Staff(String name, Gender gender, String role) {
        super(name);
        this.gender = gender;
        staffId = getNextStaffId();
        this.role = role;
    }

    public Staff(String name, Gender gender, int staffId, String role) {
        super(name);
        this.gender = gender;
        this.staffId = staffId;
        this.role = role;
    }

    // getters
    public Gender getGender() {return gender;}
    public int getStaffId() {return staffId;}
    public String getRole() {return role;}

    // setters
    public void setGender(Gender gender) {this.gender = gender;}
    public static void setNextStaffId(int nextStaffId) {Staff.nextStaffId = nextStaffId;}
    public void setRole(String role) {this.role = role;}

    // methods
    private static int getNextStaffId() {
        return nextStaffId++;
    }

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
