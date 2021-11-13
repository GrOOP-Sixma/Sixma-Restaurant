package Food;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Menu {
    private final ArrayList<MenuItem> menu;
    private String name;

    // constructors
    public Menu(String name) {
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
    public String getName() {
        return name;
    }

    public ArrayList<MenuItem> getMenu() {
        return menu;
    }

    // methods
    public void addMenuItem(String name, double price, FoodType foodType, String description) {
        MenuItem menuItem = new MenuItem(name, price, foodType, description);
        menu.add(menuItem);
    }

    public void addMenuItem(String name, double price, int menuItemId, FoodType foodType, String description) {
        MenuItem menuItem = new MenuItem(name, price, menuItemId, foodType, description);
        menu.add(menuItem);
    }

    public void addMenuItem(MenuItem menuItem) {
        menu.add(menuItem);
    }

    public int removeMenuItem(int menuItemId) {
        for (int i=0; i<menu.size(); i++) {
            if (menu.get(i).getMenuItemId() == menuItemId) {
                menu.remove(i);
                return 1;
            }
        }
        return 0;
    }

    public MenuItem getMenuItem(int menuItemId) {
        for (MenuItem menuItem : menu) {
            if (menuItem.getMenuItemId() == menuItemId) {
                return menuItem;
            }
        }
        return null;
    }

    public void viewMenu() {
        System.out.println("\nAla Carte Menu:");
        for (MenuItem menuItem : menu) {
            menuItem.printMenuItem();
        }
    }

    public void writeInstances() {
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

    public void readInstances() {
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
