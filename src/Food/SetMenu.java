package Food;

import java.io.Serializable;
import java.util.ArrayList;

public class SetMenu implements Serializable {
    private String setName;
    private ArrayList<MenuItem> menuItems;
    private double setPrice;

    // constructor
    public SetMenu(String setName, ArrayList<MenuItem> menuItems, double setPrice) {
        this.setName = setName;
        this.menuItems = menuItems;
        this.setPrice = setPrice;
    }

    // getters
    public String getSetName() {return setName;}
    public ArrayList<MenuItem> getMenuItems() {return menuItems;}
    public double getSetPrice() {return setPrice;}

    // setters
    public void setSetName(String setName) {this.setName = setName;}
    public void setMenuItems(ArrayList<MenuItem> menuItems) {this.menuItems = menuItems;}
    public void setSetPrice(double setPrice) {this.setPrice = setPrice;}

    // methods
    public void addItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public void removeItem(MenuItem menuItem) {
        menuItems.remove(menuItem);
    }

    // toString
    public void print() {
        System.out.println("---------------------");
        System.out.println("Set Name: " + setName);
        for (int i=0; i<menuItems.size(); i++) {
            MenuItem menuItem = menuItems.get(i);
            System.out.println("Item " + i + 1 + ": " + menuItem.getName());
            System.out.println("Food Type: " + menuItem.getFoodType());
            System.out.println("Description:" + menuItem.getDescription());
        }
        System.out.println("Set Price: " + setPrice);
    }

    // convert to byte array
    public byte[] toByteArray() {
        byte[] bytes = new byte[1024];
        int index = 0;
        byte[] setNameBytes = setName.getBytes();
        byte[] setPriceBytes = Double.toString(setPrice).getBytes();
        for (int i=0; i<setNameBytes.length; i++) {
            bytes[index] = setNameBytes[i];
            index++;
        }
        bytes[index] = '\0';
        index++;
        for (int i=0; i<setPriceBytes.length; i++) {
            bytes[index] = setPriceBytes[i];
            index++;
        }
        bytes[index] = '\0';
        index++;
        for (int i=0; i<menuItems.size(); i++) {
            MenuItem menuItem = menuItems.get(i);
            byte[] menuItemBytes = menuItem.toByteArray();
            for (int j=0; j<menuItemBytes.length; j++) {
                bytes[index] = menuItemBytes[j];
                index++;
            }
        }
        return bytes;
    }

    // convert to file
    
}
