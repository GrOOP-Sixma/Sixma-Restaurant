package Food;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
/**
 * Represents a control class to execute the methods on the SetItem class
 * @author tengwei
 * @version 1.0
 * @since 2021-11-14
 */
public class SetMenu {
	/**
	 * The menu of this SetMenu
	 */
    private final Menu menu;
    /**
     * The list of setItem in this SetMenu's setMenu
     */
    private final ArrayList<SetItem> setMenu;
    /**
     * The name of this SetMenu
     */
    private String name;

    // constructors
    /**
     * Creates a new SetMenu with the given name, menu
     * @param name This SetMenu's name
     * @param menu This SetMenu's menu
     */
    public SetMenu(String name, Menu menu) {
        this.name = name;
        this.menu = menu;
        setMenu = new ArrayList<>();
        readInstances();
    }

    /**
     * Gets all of the setItems in this SetMenu's setMenu
     * @return This SetMenu's setMenu
     */
    public ArrayList<SetItem> getSetMenu() {
        return setMenu;
    }

    /**
     * Gets the name of this SetMenu
     * @return This SetMenu's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the menu of this SetMenu
     * @return This SetMenu's menu
     */
    public Menu getMenu() {
        return menu;
    }

    // methods
    /**
     * Adds a setItem into this SetMenu's setMenu
     * @param name this setItem's name
     * @param price this setItem's price
     * @param setItems this setItem's menuItems
     */
    public void addSetItem(String name, double price, ArrayList<MenuItem> setItems) {
        SetItem setItem = new SetItem(name, price, setItems);
        setMenu.add(setItem);
    }

    /**
     * Adds a setItem into this SetMenu's setMenu
     * @param name this setItem's name
     * @param price this setItem's price
     * @param setItemId this setItem's setItemId
     * @param setItems this setItem's menuItems
     */
    public void addSetItem(String name, double price, int setItemId, ArrayList<MenuItem> setItems) {
        SetItem setItem = new SetItem(name, price, setItemId, setItems);
        setMenu.add(setItem);
    }

    /**
     * Adds a setItem into this SetMenu's setMenu
     * @param setItem this new setItem object
     */
    public void addSetItem(SetItem setItem) {
        setMenu.add(setItem);
    }

    /**
     * Removes a setItem from this SetMenu's setMenu
     * if the setMenu contains the setItem
     * @param setItemId this setItem's setItemId
     * @return whether this setItem has been removed from the setMenu
     */
    public int removeSetItem(int setItemId) {
        for (int i=0; i<setMenu.size(); i++) {
            if (setMenu.get(i).getSetItemId() == setItemId) {
                setMenu.remove(i);
                return 1;
            }
        }
        return 0;
    }

    /**
     * Gets the setItem from this SetMenu's setMenu
     * if the setMenu contains the setItem
     * @param setItemId this setItem's setItemId
     * @return whether this setImte is in the setMenu
     */
    public SetItem getSetItem(int setItemId) {
        for (SetItem setItem : setMenu) {
            if (setItem.getSetItemId() == setItemId) {
                return setItem;
            }
        }
        return null;
    }

    /**
     * View all of the setItems in this SetMenu's setMenu
     */
    public void viewSetMenu() {
        System.out.println("\nSet Menu:");
        for (SetItem setItem : setMenu) {
            setItem.printSetItem();
        }
    }

    /**
     * Save all of the setItem in this SetMenu's setMenu into a file
     * @throws IOException If an input or output exception has occurred
     */
    public void writeInstances() {
        String name;
        double price;
        int setItemId;
        int setSize;
        String menuItemsIds;
        try {
            File myObj = new File(System.getProperty("user.dir") + "/tmp/" + this.name  + "SetItem.txt");
            myObj.createNewFile(); // if file already exists will do nothing
            FileWriter myWriter = new FileWriter(System.getProperty("user.dir") + "/tmp/" + this.name  + "SetItem.txt");
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

    /**
     * Read all of the setItem in this SetMenu's setMenu
     * @throws FileNotFoundException If the file cannot be found
     * @throws IOException If an input or output exception has occurred
     */
    public void readInstances() {
        String name;
        double price;
        int setItemId;
        int setSize;
        ArrayList<MenuItem> setItems;
        int maxSetItemId = 0;
        try {
            File myObj = new File(System.getProperty("user.dir") + "/tmp/" + this.name  + "SetItem.txt");
            myObj.createNewFile(); // if file already exists will do nothing
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
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
