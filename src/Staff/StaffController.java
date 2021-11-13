package Staff;

import java.util.ArrayList;
import java.util.Scanner;

import Customer.Customer;

import java.io.*;

import Restaurant.RestaurantBack.Gender;

public class StaffController {
    private final ArrayList<Staff> staffList;
    private String name;

    // constructor
    public StaffController(String name) {
        staffList = new ArrayList<>();
        this.name = name;
        readInstances();
    }

    // methods
    public void addStaff(String name, Gender gender, String role) {
        Staff staff = new Staff(name, gender, role);
        staffList.add(staff);
    }

    public void addStaff(String name, Gender gender, int staffId, String role) {
        Staff staff = new Staff(name, gender, staffId, role);
        staffList.add(staff);
    }

    public void addStaff(Staff staff) {
        staffList.add(staff);
    }

    public int removeStaff(String name, int staffId) {
        for (int i=0; i<staffList.size(); i++) {
            if (staffList.get(i).getName().equals(name) && staffList.get(i).getStaffId() == staffId) {
                staffList.remove(i);
                return 1;
            }
        }
        return 0;
    }

    public Staff getStaff(int staffId) {
        for (Staff staff : staffList) {
            if (staff.getStaffId() == staffId) {
                return staff;
            }
        }
        return null;
    }

    public Staff getStaff(String name, int staffId) {
        for (Staff staff : staffList) {
            if (staff.getName().equals(name) && staff.getStaffId() == staffId) {
                return staff;
            }
        }
        return null;
    }

    public void viewStaff() {
        System.out.println("\nStaffs:");
        for (Staff staff : staffList) {
            staff.printStaff();
        }
    }

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

    public ArrayList<Staff> getStaffList() {
        return staffList;
    }
}
