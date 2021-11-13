package Food;

import java.text.DecimalFormat;

public class MenuItem extends Products {
    private int menuItemId;
    private static int nextMenuItemId = 1;
    private FoodType foodType;
    private String description;

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
    public MenuItem(String name, double price, FoodType foodType, String description) {
        super(name, price);
        menuItemId = getNextMenuItemId();
        this.foodType = foodType;
        this.description = description;
    }

    public MenuItem(String name, double price, int menuItemId, FoodType foodType, String description) {
        super(name, price);
        this.menuItemId = menuItemId;
        this.foodType = foodType;
        this.description = description;
    }

    // getters
    public int getMenuItemId() {return menuItemId;}
    public FoodType getFoodType() {return foodType;}
    public String getDescription() {return description;}

    // setters
    public static void setNextMenuItemId(int nextMenuItemId) {MenuItem.nextMenuItemId = nextMenuItemId;}
    public void setFoodType(FoodType foodType) {this.foodType = foodType;}
    public void setDescription(String description) {this.description = description;}

    // methods
    private static int getNextMenuItemId() {
        return nextMenuItemId++;
    }

    public void printMenuItem() {
        System.out.println( ANSI_YELLOW + "-------------------------------" + ANSI_RESET);
        System.out.println( ANSI_YELLOW + "Item ID: " + menuItemId + ANSI_RESET);
        System.out.println( ANSI_YELLOW + "Name: " + name + ANSI_RESET);
        if (foodType == FoodType.MAIN_COURSE) {
            System.out.println( ANSI_YELLOW + "Food Type: Main Course" + ANSI_RESET);
        }
        else if (foodType == FoodType.DRINKS) {
            System.out.println( ANSI_YELLOW + "Food Type: Drinks" + ANSI_RESET);
        }
        else {
            System.out.println( ANSI_YELLOW + "Food Type: Dessert" + ANSI_RESET);
        }
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println( ANSI_YELLOW + "Price: $" + df.format(price) + ANSI_RESET);
        System.out.println( ANSI_YELLOW + "Description: " + description + ANSI_RESET);
    }
}
