package Customer;

import Restaurant.Person;

import java.io.Serializable;

public class Customer extends Person implements Serializable {
    private int contactNo;
    private int customerId;
    private static int nextCustomerId = 1;
    private boolean isMember;

    // constructor
    public Customer(String name, int contactNo, boolean isMember) {
        super(name);
        this.contactNo = contactNo;
        customerId = getNextCustomerId();
        this.isMember = isMember;
    }

    // getters
    public int getContactNo() {return contactNo;}
    public int getCustomerId() {return customerId;}
    public boolean isMember() {return isMember;}

    // setters
    public void setContactNo(int contactNo) {this.contactNo = contactNo;}
    public void setMember(boolean isMember) {this.isMember = isMember;}

    // methods
    private static int getNextCustomerId() {
        return nextCustomerId++;
    }

    public byte[] toByteArray() {
        String name = this.getName();

        byte[] byteArray = new byte[1024];
        byteArray[0] = (byte) name.length();
        byteArray[1] = (byte) contactNo;
        byteArray[2] = (byte) customerId;
        byteArray[3] = (byte) (isMember ? 1 : 0);
        for (int i = 0; i<name.length(); i++) {
            byteArray[i + 4] = (byte) name.charAt(i);
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
