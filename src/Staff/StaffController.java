package Staff;

import java.util.ArrayList;

import Restaurant.RestaurantBack.Gender;

public class StaffController {
    private final ArrayList<Staff> staffList;

    // constructor
    public StaffController() {
        staffList = new ArrayList<>();
    }

    // methods
    public void addStaff(String name, Gender gender, String role) {
        Staff staff = new Staff(name, gender, role);
        staffList.add(staff);
    }

    public void addStaff(Staff staff) {
        staffList.add(staff);
    }

    public void removeStaff(String name, int staffId) {
        for (int i=0; i<staffList.size(); i++) {
            if (staffList.get(i).getName().equals(name) && staffList.get(i).getStaffId() == staffId) {
                staffList.remove(i);
                break;
            }
        }
    }

    public Staff getStaff(int staffId) {
        for (Staff staff : staffList)
            if (staff.getStaffId() == staffId)
                return staff;

        return null;
    }

    public void viewStaff() {
        for (Staff staff : staffList) {
            System.out.println(staff);
        }
    }
}
