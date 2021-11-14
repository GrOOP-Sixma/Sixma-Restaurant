package Food;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
/**
 * Represents a control class to execute the methods on the MenuItem class
 * @author tengwei
 * @version 1.0
 * @since 2021-11-14
 */
public class Menu {
	/**
	 * The list of menuItem in this Menu
	 */
    private final ArrayList<MenuItem> menu;
    /**
     * The name of this Menu
     */
    private String name;

    // constructors
    /**
     * Creates a new Menu with the given name
     * @param name This Menu's name
     */
    public Menu(String name) throws FileNotFoundException, IOException{
        menu = new ArrayList<>();
        this.name = name;
        readInstances();
        // try {
        //     File myObj = new File("../tmp/" + this.name  + "MenuItem.txt");
        //     myObj.createNewFile();  // if file already exists will do nothing
        // } catch (IOException e) {
        //     System.out.println("An error occurred.");
        //     e.printStackTrace();
        // }
    }

    // getters
    /**
     * Gets the name of this Menu
     * @return This Menu's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets all of the menuItems in this Menu's menu
     * @return This Menu's menu
     */
    public ArrayList<MenuItem> getMenu() {
        return menu;
    }

    // methods
    /**
     * Adds a menuItem into this Menu's menu
     * @param name this menuItem's name
     * @param price this menuItem's price
     * @param foodType this menuItem's foodType
     * @param description this menuItem's description
     */
    public void addMenuItem(String name, double price, FoodType foodType, String description) {
        MenuItem menuItem = new MenuItem(name, price, foodType, description);
        menu.add(menuItem);
    }

    /**
     * Adds a menuItem into this Menu's menu
     * @param name this menuItem's name
     * @param price this menuItem's price
     * @param menuItemId this menuItem's menuItemId
     * @param foodType this menuItem's foodType
     * @param description this menuItem's description
     */
    public void addMenuItem(String name, double price, int menuItemId, FoodType foodType, String description) {
        MenuItem menuItem = new MenuItem(name, price, menuItemId, foodType, description);
        menu.add(menuItem);
    }
    
    /**
     * Adds a menuItem into this Menu's menu
     * @param menuItem this new menuItem object
     */
    public void addMenuItem(MenuItem menuItem) {
        menu.add(menuItem);
    }
    
    /**
     * Removes a menuItem from this Menu's menu
     * if the menu contains the menuItem
     * @param menuItemId this menuItem's menuItemId
     * @return whether this menuItem has been removed from the menu
     */
    public int removeMenuItem(int menuItemId) {
        for (int i=0; i<menu.size(); i++) {
            if (menu.get(i).getMenuItemId() == menuItemId) {
                menu.remove(i);
                return 1;
            }
        }
        return 0;
    }

    /**
     * Gets the menuItem from this Menu's menu
     * if the menu contains the menuItem
     * @param menuItemId this menuItem's menuItemId
     * @return whether this menuItem is in the menu
     */
    public MenuItem getMenuItem(int menuItemId) {
        for (MenuItem menuItem : menu) {
            if (menuItem.getMenuItemId() == menuItemId) {
                return menuItem;
            }
        }
        return null;
    }

    /**
     * View all of the menuItems in this Menu's menu
     */
    public void viewMenu() {
        System.out.println("\nAla Carte Menu:");
        for (MenuItem menuItem : menu) {
            menuItem.printMenuItem();
        }
    }

    /**
     * Save all of the menuItem in this Menu's menu into a file
     * @throws IOException If an input or output exception has occurred
     */
    public void writeInstances() throws IOException {
        String name;
        double price;
        int menuItemId;
        FoodType foodType;
        String description;
        try {
            File myObj = new File(System.getProperty("user.dir") + "/tmp/" + this.name  + "MenuItem.txt");
            myObj.createNewFile();  // if file already exists will do nothing
            Scanner myReader = new Scanner(myObj);
            FileWriter myWriter = new FileWriter(System.getProperty("user.dir") + "/tmp/" + this.name  + "MenuItem.txt");
            for (MenuItem menuItem : menu) {
                name = menuItem.getName();
                price = menuItem.getPrice();
                menuItemId = menuItem.getMenuItemId();
                foodType = menuItem.getFoodType();
                description = menuItem.getDescription();
                if (foodType == FoodType.MAIN_COURSE) {
                    myWriter.write(name + ";" + price + ";" + menuItemId + ";0;" + description + "\n");
                }
                else if (foodType == FoodType.DRINKS) {
                    myWriter.write(name + ";" + price + ";" + menuItemId + ";1;" + description + "\n");
                }
                else {
                    myWriter.write(name + ";" + price + ";" + menuItemId + ";2;" + description + "\n");
                }
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Read all of the menuItem in this Menu's menu
     * @throws FileNotFoundException If the file cannot be found
     * @throws IOException If any input or output exception occurred
     */
    public void readInstances() throws FileNotFoundException, IOException{
        String name;
        double price;
        int menuItemId;
        int foodType;
        String description;
        int maxMenuItemId = 0;
        try {
            File myObj = new File(System.getProperty("user.dir") + "/tmp/" + this.name  + "MenuItem.txt");
            myObj.createNewFile();  // if file already exists will do nothing
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] attributes = data.split(";");
                name = attributes[0];
                price = Double.parseDouble(attributes[1]);
                menuItemId = Integer.parseInt(attributes[2]);
                foodType = Integer.parseInt(attributes[3]);
                description = attributes[4];
                if (foodType == 0) {
                    addMenuItem(name, price, menuItemId, FoodType.MAIN_COURSE, description);
                }
                else if (foodType == 1) {
                    addMenuItem(name, price, menuItemId, FoodType.DRINKS, description);
                }
                else {
                    addMenuItem(name, price, menuItemId, FoodType.DESSERT, description);
                }
                if (menuItemId > maxMenuItemId) {
                    maxMenuItemId = menuItemId;
                }
            }
            MenuItem.setNextMenuItemId(maxMenuItemId + 1);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
