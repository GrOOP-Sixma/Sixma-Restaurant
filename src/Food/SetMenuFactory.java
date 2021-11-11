package Food;

import java.util.ArrayList;
import java.util.Scanner;

public class SetMenuFactory {
    private Menu menu;
    private SetMenu setMenu;
    private String name;

    // constructors
    public SetMenuFactory(String name, Menu menu) {
        this.name = name;
        this.menu = menu;
        setMenu = new SetMenu(name, menu);
    }

    // getters
    public Menu getMenu() {return menu;}
    public SetMenu getSetMenu() {return setMenu;}

    // methods
    public double getDoubleInput() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (sc.hasNextDouble()) {
                return sc.nextDouble();
            }
            else {
                System.out.println("Invalid input.");
                sc.next();
            }
        }
    }

    public int getIntInput() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (sc.hasNextInt()) {
                return sc.nextInt();
            }
            else {
                System.out.println("Invalid input.");
                sc.next();
            }
        }
    }

    public void run() {
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        while (choice != 0) {
            System.out.println("\nSet Menu Manager:");
            System.out.println("1. Create set menu item");
            System.out.println("2. Update set menu item");
            System.out.println("3. Remove set menu item");
            System.out.println("4. View set menu items");
            System.out.println("0. Exit");
            loop: while (choice != 0) {
                choice = getIntInput();
                switch (choice) {
                    case 0:
                        continue;
                    case 1:
                        addSetItem();
                        break loop;
                    case 2:
                        modifySetItem();
                        break loop;
                    case 3:
                        removeSetItem();
                        break loop;
                    case 4:
                        viewSetItems();
                        break loop;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }
    }

    // create a new menu
    public void addSetItem() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of set menu item:");
        String name = sc.nextLine();

        System.out.println("Enter price of set menu item:");
        double price = getDoubleInput();
        while (price <= 0) {
            System.out.println("Invalid price");
            price = getDoubleInput();
        }

        System.out.println("Enter number of menu items in set menu item:");
        int setSize = getIntInput();
        while (setSize <= 0) {
            System.out.println("Invalid size");
            setSize = getIntInput();
        }

        menu.viewMenu();
        ArrayList<MenuItem> setItems = new ArrayList<>();
        for (int i=0; i<setSize; i++) {
            MenuItem menuItem = null;
            while (menuItem == null) {
                System.out.println("Menu item " + (i + 1));
                System.out.println("Enter id of menu item to be added to set menu item:");
                int id = getIntInput();
                while (id <= 0) {
                    System.out.println("Invalid id");
                    id = getIntInput();
                }
                menuItem = menu.getMenuItem(id);
                if (menuItem == null) {
                    System.out.println("There is no menu item with id " + id);
                }
            }
            setItems.add(menuItem);
        }

        SetItem setItem = new SetItem(name, price, setItems);
        setMenu.addSetItem(setItem);
    }

    public void removeSetItem() {
        System.out.println("Enter id of set menu item to be removed:");
        int id = getIntInput();
        while (id <= 0) {
            System.out.println("Invalid id");
            id = getIntInput();
        }

        if (setMenu.removeSetItem(id) == 0) {
            System.out.println("There is no menu item with id "+ id);
        }
    }

    public void modifySetItem() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id of set menu item to be modified:");
        int setItemId = getIntInput();
        while (setItemId <= 0) {
            System.out.println("Invalid id");
            setItemId = getIntInput();
        }
        SetItem setItem = setMenu.getSetItem(setItemId);
        if (setItem == null) {
            System.out.println("There is no set menu item with id " + setItemId);
            return;
        }

        int choice = -1;
        while (choice != 0) {
            System.out.println("1. Modify name");
            System.out.println("2. Modify price");
            System.out.println("3. Add menu item");
            System.out.println("4. Remove menu item");
            System.out.println("0. Back");
            loop: while (choice != 0) {
                choice = getIntInput();
                switch (choice) {
                    case 0:
                        continue;
                    case 1:
                        System.out.println("Enter new name of set menu item:");
                        String name = sc.nextLine();
                        setItem.setName(name);
                        break loop;
                    case 2:
                        System.out.println("Enter new price of set menu item:");
                        double price = getDoubleInput();
                        while (price <= 0) {
                            System.out.println("Invalid price");
                            price = getDoubleInput();
                        }
                        setItem.setPrice(price);
                        break loop;
                    case 3:
                        menu.viewMenu();
                        System.out.println("Enter id of menu item to be added to set menu item:");
                        int menuItemId = getIntInput();
                        while (menuItemId <= 0) {
                            System.out.println("Invalid id");
                            menuItemId = getIntInput();
                        }
                        MenuItem menuItem = menu.getMenuItem(menuItemId);
                        if (menuItem == null) {
                            System.out.println("There is no menu item with id " + menuItemId);
                            break loop;
                        }
                        setItem.addMenuItem(menuItem);
                        break loop;
                    case 4:
                        setItem.printSetItem();
                        System.out.println("Enter id of menu item to be removed from set menu item:");
                        menuItemId = getIntInput();
                        while (menuItemId <= 0) {
                            System.out.println("Invalid id");
                            menuItemId = getIntInput();
                        }
                        menuItem = menu.getMenuItem(menuItemId);
                        if (menuItem == null) {
                            System.out.println("There is no menu item with id " + menuItemId);
                            break loop;
                        }
                        if (!setItem.getSetItems().contains(menuItem)) {
                            System.out.println("Set menu item does not contain menu item with id " + menuItemId);
                            break loop;
                        }
                        setItem.removeMenuItem(menuItem);
                        break loop;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }
    }

    public void viewSetItems() {
        setMenu.viewSetMenu();
    }

    public void writeInstances() {
        setMenu.writeInstances();
    }
}

