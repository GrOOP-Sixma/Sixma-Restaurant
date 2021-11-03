package Food;

import java.util.ArrayList;

public class SetMenu{
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
}
