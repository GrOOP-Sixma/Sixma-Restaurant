package Staff;

import Restaurant.Person;
import Restaurant.RestaurantBack.Gender;

public class Staff extends Person {
    private Gender gender;
    private int staffId;
    private static int nextStaffId = 1;
    private String role;

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
