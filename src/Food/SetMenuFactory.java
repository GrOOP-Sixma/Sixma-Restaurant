package Food;

import java.util.ArrayList;
import java.util.Scanner;

public class SetMenuFactory {
    private Menu menu;
    private SetMenu setMenu;
    private String name;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

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
                System.out.println(ANSI_RED + "Invalid input." + ANSI_RESET);
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
                System.out.println(ANSI_RED + "Invalid input." + ANSI_RESET);
                sc.next();
            }
        }
    }

    public void run() {
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        System.out.println(ANSI_YELLOW + """
        $$$$$$\\              $$\\           $$\\      $$\\                                     $$\\      $$\\                                                             
        $$  __$$\\             $$ |          $$$\\    $$$ |                                    $$$\\    $$$ |                                                            
        $$ /  \\__| $$$$$$\\  $$$$$$\\         $$$$\\  $$$$ | $$$$$$\\  $$$$$$$\\  $$\\   $$\\       $$$$\\  $$$$ | $$$$$$\\  $$$$$$$\\   $$$$$$\\   $$$$$$\\   $$$$$$\\   $$$$$$\\  
        \\$$$$$$\\  $$  __$$\\ \\_$$  _|        $$\\$$\\$$ $$ |$$  __$$\\ $$  __$$\\ $$ |  $$ |      $$\\$$\\$$ $$ | \\____$$\\ $$  __$$\\  \\____$$\\ $$  __$$\\ $$  __$$\\ $$  __$$\\ 
         \\____$$\\ $$$$$$$$ |  $$ |          $$ \\$$$  $$ |$$$$$$$$ |$$ |  $$ |$$ |  $$ |      $$ \\$$$  $$ | $$$$$$$ |$$ |  $$ | $$$$$$$ |$$ /  $$ |$$$$$$$$ |$$ |  \\__|
        $$\\   $$ |$$   ____|  $$ |$$\\       $$ |\\$  /$$ |$$   ____|$$ |  $$ |$$ |  $$ |      $$ |\\$  /$$ |$$  __$$ |$$ |  $$ |$$  __$$ |$$ |  $$ |$$   ____|$$ |      
        \\$$$$$$  |\\$$$$$$$\\   \\$$$$  |      $$ | \\_/ $$ |\\$$$$$$$\\ $$ |  $$ |\\$$$$$$  |      $$ | \\_/ $$ |\\$$$$$$$ |$$ |  $$ |\\$$$$$$$ |\\$$$$$$$ |\\$$$$$$$\\ $$ |      
         \\______/  \\_______|   \\____/       \\__|     \\__| \\_______|\\__|  \\__| \\______/       \\__|     \\__| \\_______|\\__|  \\__| \\_______| \\____$$ | \\_______|\\__|      
                                                                                                                                        $$\\   $$ |                    
                                                                                                                                        \\$$$$$$  |                    
                                                                                                                                         \\______/                     
        """ + ANSI_RESET);
        while (choice != 0) {
            System.out.println("");
            System.out.println(ANSI_YELLOW + "1. Create set menu item" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "2. Update set menu item" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "3. Remove set menu item" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "4. View set menu items" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "0. Exit" + ANSI_RESET);
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
                        System.out.println(ANSI_RED + "Invalid choice" + ANSI_RESET);
                }
            }
        }
    }

    // create a new menu
    public void addSetItem() {
        Scanner sc = new Scanner(System.in);
        System.out.println(ANSI_YELLOW + "Enter name of set menu item:" + ANSI_RESET);
        String name = sc.nextLine();

        System.out.println(ANSI_YELLOW + "Enter price of set menu item:" + ANSI_RESET);
        double price = getDoubleInput();
        while (price <= 0) {
            System.out.println(ANSI_RED + "Invalid price" + ANSI_RESET);
            price = getDoubleInput();
        }

        System.out.println(ANSI_YELLOW + "Enter number of menu items in set menu item:" + ANSI_RESET);
        int setSize = getIntInput();
        while (setSize <= 0) {
            System.out.println(ANSI_RED + "Invalid size" + ANSI_RESET);
            setSize = getIntInput();
        }

        menu.viewMenu();
        ArrayList<MenuItem> setItems = new ArrayList<>();
        for (int i=0; i<setSize; i++) {
            MenuItem menuItem = null;
            while (menuItem == null) {
                System.out.println(ANSI_YELLOW + "Menu item " + (i + 1));
                System.out.println(ANSI_YELLOW + "Enter id of menu item to be added to set menu item:" + ANSI_RESET);
                int id = getIntInput();
                while (id <= 0) {
                    System.out.println(ANSI_RED + "Invalid id" + ANSI_RESET);
                    id = getIntInput();
                }
                menuItem = menu.getMenuItem(id);
                if (menuItem == null) {
                    System.out.println(ANSI_RED + "There is no menu item with id " + id);
                }
            }
            setItems.add(menuItem);
        }

        SetItem setItem = new SetItem(name, price, setItems);
        setMenu.addSetItem(setItem);
    }

    public void removeSetItem() {
        System.out.println(ANSI_YELLOW + "Enter id of set menu item to be removed:" + ANSI_RESET);
        int id = getIntInput();
        while (id <= 0) {
            System.out.println(ANSI_RED + "Invalid id" + ANSI_RESET);
            id = getIntInput();
        }

        if (setMenu.removeSetItem(id) == 0) {
            System.out.println(ANSI_RED + "There is no menu item with id "+ id);
        }
    }

    public void modifySetItem() {
        Scanner sc = new Scanner(System.in);
        System.out.println(ANSI_YELLOW + "Enter id of set menu item to be modified:" + ANSI_RESET);
        int setItemId = getIntInput();
        while (setItemId <= 0) {
            System.out.println(ANSI_RED + "Invalid id" + ANSI_RESET);
            setItemId = getIntInput();
        }
        SetItem setItem = setMenu.getSetItem(setItemId);
        if (setItem == null) {
            System.out.println(ANSI_RED + "There is no set menu item with id " + setItemId);
            return;
        }

        int choice = -1;
        while (choice != 0) {
            System.out.println(ANSI_YELLOW + "1. Modify name" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "2. Modify price" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "3. Add menu item" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "4. Remove menu item" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "0. Back" + ANSI_RESET);
            loop: while (choice != 0) {
                choice = getIntInput();
                switch (choice) {
                    case 0:
                        continue;
                    case 1:
                        System.out.println(ANSI_YELLOW + "Enter new name of set menu item:" + ANSI_RESET);
                        String name = sc.nextLine();
                        setItem.setName(name);
                        break loop;
                    case 2:
                        System.out.println(ANSI_YELLOW + "Enter new price of set menu item:" + ANSI_RESET);
                        double price = getDoubleInput();
                        while (price <= 0) {
                            System.out.println(ANSI_RED + "Invalid price" + ANSI_RESET);
                            price = getDoubleInput();
                        }
                        setItem.setPrice(price);
                        break loop;
                    case 3:
                        menu.viewMenu();
                        System.out.println(ANSI_YELLOW + "Enter id of menu item to be added to set menu item:" + ANSI_RESET);
                        int menuItemId = getIntInput();
                        while (menuItemId <= 0) {
                            System.out.println(ANSI_RED + "Invalid id" + ANSI_RESET);
                            menuItemId = getIntInput();
                        }
                        MenuItem menuItem = menu.getMenuItem(menuItemId);
                        if (menuItem == null) {
                            System.out.println(ANSI_RED + "There is no menu item with id " + menuItemId);
                            break loop;
                        }
                        setItem.addMenuItem(menuItem);
                        break loop;
                    case 4:
                        setItem.printSetItem();
                        System.out.println(ANSI_YELLOW + "Enter id of menu item to be removed from set menu item:" + ANSI_RESET);
                        menuItemId = getIntInput();
                        while (menuItemId <= 0) {
                            System.out.println(ANSI_RED + "Invalid id" + ANSI_RESET);
                            menuItemId = getIntInput();
                        }
                        menuItem = menu.getMenuItem(menuItemId);
                        if (menuItem == null) {
                            System.out.println(ANSI_RED + "There is no menu item with id " + menuItemId);
                            break loop;
                        }
                        if (!setItem.getSetItems().contains(menuItem)) {
                            System.out.println(ANSI_RED + "Set menu item does not contain menu item with id " + menuItemId);
                            break loop;
                        }
                        setItem.removeMenuItem(menuItem);
                        break loop;
                    default:
                        System.out.println(ANSI_RED + "Invalid choice" + ANSI_RESET);
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

