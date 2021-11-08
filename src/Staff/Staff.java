package Staff;

import Restaurant.Person;
import Restaurant.RestaurantBack.Gender;

import java.io.Serializable;

public class Staff extends Person implements Serializable {
    private Gender gender;
	private int staffId;
    private static int nextStaffId = 1;
    private String role;

    // constructor
    public Staff(String name, Gender gender, String role) {
        super(name);
        this.gender = gender;
        staffId = getNextStaffId();
        this.role = role;
    }

    // getters
    public Gender getGender() {return gender;}
    public int getStaffId() {return staffId;}
    public String getRole() {return role;}

    // setters
    public void setGender(Gender gender) {this.gender = gender;}
    public void setStaffId(int staffId) {this.staffId = staffId;}
    public void setRole(String role) {this.role = role;}

    // methods
    private static int getNextStaffId() {
        return nextStaffId++;
    }

    public byte[] toByteArray() {
        String name = this.getName();

        byte[] byteArray = new byte[1024];
        byteArray[0] = (byte) name.length();
        byteArray[1] = (byte) gender.ordinal();
        byteArray[2] = (byte) staffId;
        byteArray[3] = (byte) role.length();
        for (int i = 0; i<name.length(); i++) {
            byteArray[i + 4] = (byte) name.charAt(i);
        }
        for (int i=0; i<role.length(); i++) {
            byteArray[i + 4] = (byte) role.charAt(i);
        }
        return byteArray;
    }

    public void toFile(String fileName) {
        byte[] byteArray = toByteArray();
        System.out.println("bytes: " + byteArray.length);
        java.io.FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new java.io.FileOutputStream(fileName);
            fileOutputStream.write(byteArray);
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e);
        } finally {
            try {
                fileOutputStream.close();
            } catch (Exception e) {
                System.out.println("Error closing file: " + e);
            }
        }
    }
}