package Food;

import java.util.HashMap;
import java.util.Scanner;

public class SetMenuFactory {
    private HashMap<String, SetMenu> setMenuList; // name of set menu to set menu object. // ? example: fridayHappyHour = setMenu
    private Menu menu;

    public SetMenuFactory(Menu menu) {
        setMenuList = new HashMap<>();
        this.menu = menu;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Boolean done = false;
        System.out.println("Welcome to Set Menu Options");
        while (!done) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Modify a set menu");
            System.out.println("2. Add a set menu");
            System.out.println("3. Print the list of set menus");
            System.out.println("4. Print the set items in a specific set menu");
            System.out.println("0. Exit");
            switch (scanner.nextLine()) {
                case "1":
                    modifySetMenu();
                    break;
                case "2":
                    createSetMenu();
                    break;
                case "3":
                    printSetMenus();
                    break;
                case "4":
                    System.out.println("Enter name of set menu you wish to print");
                    printSetItems(scanner.nextLine());
                    break;
                case "0":
                    done = true;
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }

        }
        scanner.close();
    }

    // create a new menu
    public void createSetMenu() {
        Scanner scanner = new Scanner(System.in);
        SetMenu newSetMenu = new SetMenu();
        System.out.println("Enter the name of the set menu: ");
        newSetMenu.setName(scanner.nextLine());
        setMenuList.put(newSetMenu.getName(), newSetMenu);
        System.out.println("Set menu created successfully");
        scanner.close();
    }

    // modify set menu
    public void modifySetMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the set menu: ");
        String name = scanner.nextLine();
        if (setMenuList.containsKey(name)) {
            SetMenu setMenu = setMenuList.get(name);
            int choice = -1;
            while (choice != 0) {
                System.out.println("1. Add a set to the set menu");
                System.out.println("2. Remove a set from the set menu");
                System.out.println("3. Delete the set menu");
                System.out.println("0. Back");
                System.out.println("Enter your choice: ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        createSetItem(setMenu);
                        break;
                    case 2:
                        removeSetItem(setMenu);
                        break;
                    case 3:
                        setMenuList.remove(name);
                        System.out.println("Set menu deleted successfully");
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            }
        } else {
            System.out.println("Set menu not found");
        }
        scanner.close();
    }

    // add item to a set menu
    public void createSetItem(SetMenu setMenu) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the set");
        String name = scanner.nextLine();
        System.out.println("How many items are included in this set?");
        int numOfItems = scanner.nextInt();
        MenuItem[] items = new MenuItem[numOfItems];
        for (int i = 0; i < numOfItems; i++) {
            System.out.println("Enter name of menu item to be included");
            String itemName = scanner.nextLine();
            // ! im assuming the menu item is already in the menu
            MenuItem menuItem = menu.getMenuItem(itemName);
            items[i] = menuItem;
        }
        System.out.println("Enter the price of the set");
        double price = scanner.nextDouble();
        setMenu.addSet(items, name, price);
        scanner.close();
    }

    // remove an item from a set menu
    public void removeSetItem(SetMenu setMenu) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the id of the set");
        int id = scanner.nextInt();
        setMenu.deleteSet(id);
        scanner.close();
    }

    // print all set menus
    public void printSetMenus() {
        for (String name : setMenuList.keySet()) {
            System.out.println(name);
        }
    }

    // print all set items in a set menu
    public void printSetItems(String name) {
        if (setMenuList.containsKey(name)) {
            SetMenu setMenu = setMenuList.get(name);
            setMenu.printSetMenu();
        } else {
            System.out.println("Set menu not found");
        }
    }

    public HashMap<String, SetMenu> getSetMenuList() {
        return setMenuList;
    }

    public SetMenu getSetMenu(String name) {
        return setMenuList.get(name);
    }


}

