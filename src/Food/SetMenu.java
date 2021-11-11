package Food;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class SetMenu {
    private final Menu menu;
    private final ArrayList<SetItem> setMenu;

    // constructors
    public SetMenu(Menu menu) {
        this.menu = menu;
        setMenu = new ArrayList<>();
        readInstances();
    }

    // methods
    public void addSetItem(String name, double price, ArrayList<MenuItem> setItems) {
        SetItem setItem = new SetItem(name, price, setItems);
        setMenu.add(setItem);
    }

    public void addSetItem(String name, double price, int setItemId, ArrayList<MenuItem> setItems) {
        SetItem setItem = new SetItem(name, price, setItemId, setItems);
        setMenu.add(setItem);
    }

    public void addSetItem(SetItem setItem) {
        setMenu.add(setItem);
    }

    public int removeSetItem(int setItemId) {
        for (int i=0; i<setMenu.size(); i++) {
            if (setMenu.get(i).getSetItemId() == setItemId) {
                setMenu.remove(i);
                return 1;
            }
        }
        return 0;
    }

    public SetItem getSetItem(int setItemId) {
        for (SetItem setItem : setMenu) {
            if (setItem.getSetItemId() == setItemId) {
                return setItem;
            }
        }
        return null;
    }

    public void viewSetMenu() {
        System.out.println("\nSet Menu:");
        for (SetItem setItem : setMenu) {
            setItem.printSetItem();
        }
    }

    public void writeInstances() {
        String name;
        double price;
        int setItemId;
        int setSize;
        String menuItemsIds;
        try {
            FileWriter myWriter = new FileWriter(System.getProperty("user.dir") + "/tmp/SetItem.txt");
            for (SetItem setItem : setMenu) {
                name = setItem.getName();
                price = setItem.getPrice();
                setItemId = setItem.getSetItemId();
                ArrayList<MenuItem> setItems = setItem.getSetItems();
                setSize = setItems.size();
                menuItemsIds = "";
                for (MenuItem menuItem : setItems) {
                    menuItemsIds = menuItemsIds + menuItem.getMenuItemId() + ";";
                }
                menuItemsIds = menuItemsIds.substring(0, menuItemsIds.length() - 1);
                myWriter.write(name + ";" + price + ";" + setItemId + ";" + setSize + ";" + menuItemsIds + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void readInstances() {
        String name;
        double price;
        int setItemId;
        int setSize;
        ArrayList<MenuItem> setItems;
        int maxSetItemId = 0;
        try {
            File myObj = new File(System.getProperty("user.dir") + "/tmp/SetItem.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] attributes = data.split(";");
                name = attributes[0];
                price = Double.parseDouble(attributes[1]);
                setItemId = Integer.parseInt(attributes[2]);
                setSize = Integer.parseInt(attributes[3]);
                setItems = new ArrayList<>();
                for (int i=0; i<setSize; i++) {
                    setItems.add(menu.getMenuItem(Integer.parseInt(attributes[4 + i])));
                }
                addSetItem(name, price, setItemId, setItems);
                if (setItemId > maxSetItemId) {
                    maxSetItemId = setItemId;
                }
            }
            SetItem.setNextSetItemId(maxSetItemId + 1);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
