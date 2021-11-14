package Staff;

import java.util.ArrayList;
import java.util.Scanner;

import Customer.Customer;
import Human.Gender;

import java.io.*;
/**
 * Represents a control class to execute the methods on the Staff class
 * @author chris
 * @version 1.0
 * @since 2021-11-14
 *
 */
public class StaffController {
	/**
	 * The list of current staff that work at the restaurant
	 */
    private final ArrayList<Staff> staffList;
    /**
     * The name of this Staff
     */
    private String name;

    // constructor
    /**
     * Creates a new StaffController with the given name
     * @param name this StaffController's name
     */
    public StaffController(String name) {
        staffList = new ArrayList<>();
        this.name = name;
        readInstances();
    }

    // methods
    /**
     * Adds a Staff into this StaffController's staffList
     * @param name this Staff's name
     * @param gender this Staff's gender
     * @param role this Staff's role
     */
    public void addStaff(String name, Gender gender, String role) {
        Staff staff = new Staff(name, gender, role);
        staffList.add(staff);
    }
    
    /**
     * Adds a Staff into this StaffController's staffList
     * @param name this Staff's name
     * @param gender this Staff's gender
     * @param staffId this Staff's staffId
     * @param role this Staff's role
     */
    public void addStaff(String name, Gender gender, int staffId, String role) {
        Staff staff = new Staff(name, gender, staffId, role);
        staffList.add(staff);
    }

    /**
     * Adds a staff into this StaffController's stafflist
     * @param staff this new Staff object
     */
    public void addStaff(Staff staff) {
        staffList.add(staff);
    }

    /**
     * Removes a Staff from this StaffController's staffList
     * @param name this Staff's name
     * @param staffId this Staff's staffId
     * @return whether or nor this Staff as been removed from the staffList
     */
    public int removeStaff(String name, int staffId) {
        for (int i=0; i<staffList.size(); i++) {
            if (staffList.get(i).getName().equals(name) && staffList.get(i).getStaffId() == staffId) {
                staffList.remove(i);
                return 1;
            }
        }
        return 0;
    }

    /**
     * Gets the staff from this StaffController's staffList
     * @param staffId this Staff's staffId
     * @return whether or not this Staff is in the staffList
     */
    public Staff getStaff(int staffId) {
        for (Staff staff : staffList) {
            if (staff.getStaffId() == staffId) {
                return staff;
            }
        }
        return null;
    }

    /**
     * Gets the staff from this StaffController's staffList
     * @param name this Staff's name
     * @param staffId this Staff's Staff Id
     * @return whether or not this Staff is in the staffList
     */
    public Staff getStaff(String name, int staffId) {
        for (Staff staff : staffList) {
            if (staff.getName().equals(name) && staff.getStaffId() == staffId) {
                return staff;
            }
        }
        return null;
    }
    
    /**
     * View all the Staff in the StaffController's staffList
     */
    public void viewStaff() {
        System.out.println("\nStaffs:");
        for (Staff staff : staffList) {
            staff.printStaff();
        }
    }

    /**
     * Saves the Staff in the StaffControllers's staffList into a file
     * @throws IOException If an input or output exception has occurred
     */
    public void writeInstances() {
        String name;
        Gender gender;
        int staffId;
        String role;
        try {
            File myObj = new File(System.getProperty("user.dir") + "/tmp/" + this.name + "Staff.txt");
            myObj.createNewFile(); // if file already exists will do nothing
            FileWriter myWriter = new FileWriter(System.getProperty("user.dir") + "/tmp/" + this.name + "Staff.txt");
            for (Staff staff : staffList) {
                name = staff.getName();
                gender = staff.getGender();
                staffId = staff.getStaffId();
                role = staff.getRole();
                if (gender == Gender.MALE) {
                    myWriter.write(name + ";0;" + staffId + ";" + role + "\n");
                }
                else {
                    myWriter.write(name + ";1;" + staffId + ";" + role + "\n");
                }
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Read the Staffs in the StaffController's staffList 
     * @throws IOException If an input or output exception occurred
     * @throws FileNotFoundException If a file is not found
     */
    public void readInstances() {
        String name;
        int gender;
        int staffId;
        String role;
        int maxStaffId = 0;
        try {
            File myObj = new File(System.getProperty("user.dir") + "/tmp/" + this.name + "Staff.txt");
            myObj.createNewFile(); // if file already exists will do nothing
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] attributes = data.split(";");
                name = attributes[0];
                gender = Integer.parseInt(attributes[1]);
                staffId = Integer.parseInt(attributes[2]);
                role = attributes[3];
                if (gender == 0) {
                    addStaff(name, Gender.MALE, staffId, role);
                }
                else if (gender == 1) {
                    addStaff(name, Gender.FEMALE, staffId, role);
                }
                if (staffId > maxStaffId) {
                    maxStaffId = staffId;
                }
            }
            Staff.setNextStaffId(maxStaffId + 1);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Get all of the Staff in this StaffController staffList
     * @return this StaffController's staffList
     */
    public ArrayList<Staff> getStaffList() {
        return staffList;
    }
}
