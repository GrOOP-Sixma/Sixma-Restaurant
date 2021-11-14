package Food;

import java.text.DecimalFormat;

/**
 * Represents the menuItems in the Menu and SetMenu class
 * @author tengwei
 * @version 1.0
 * @since 2021-11-14
 */
public class MenuItem extends Products {
	/**
	 * The unique menuItemId of this MenuItem
	 */
    private int menuItemId;
    /**
     * The initialisation of the menuItemId
     */
    private static int nextMenuItemId = 1;
    /**
     * The foodType of this MenuItem
     */
    private FoodType foodType;
    /**
     * The description of this MenuItem
     */
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
    /**
     * Creates a new MenuItem with the MenuItem's name, price, foodType and description
     * @param name This MenuItem's name
     * @param price This MenuItem's price
     * @param foodType This MenuItem's foodType
     * @param description This MenuItem's description
     */
    public MenuItem(String name, double price, FoodType foodType, String description) {
        super(name, price);
        menuItemId = getNextMenuItemId();
        this.foodType = foodType;
        this.description = description;
    }
    /**
     * Creates a new MenuItem with the MenuItem's name, price, menuItemId, foodType and description
     * @param name This MenuItem's name
     * @param price This MenuItem's price
     * @param menuItemId This MenuItem's menuItemId
     * @param foodType This MenuItem's foodType
     * @param description This MenuItem's description
     */
    public MenuItem(String name, double price, int menuItemId, FoodType foodType, String description) {
        super(name, price);
        this.menuItemId = menuItemId;
        this.foodType = foodType;
        this.description = description;
    }

    // getters
    /**
     * Gets the menuItemId of this MenuItem
     * @return this MenuItem's menuItemId
     */
    public int getMenuItemId() {return menuItemId;}
    /**
     * Gets the foodType of this MenuItem
     * @return this MenuItem's foodType
     */
    public FoodType getFoodType() {return foodType;}
    /**
     * Gets the description of this MenuItem
     * @return this MenuItem's description
     */
    public String getDescription() {return description;}

    // setters
    /**
     * Sets the menuItemId of this MenuItem
     * @param nextMenuItemId This MenuItem's menuItemId
     */
    public static void setNextMenuItemId(int nextMenuItemId) {MenuItem.nextMenuItemId = nextMenuItemId;}
    /**
     * Sets the foodType of this MenuItem
     * @param foodType This MenuItem's foodtype
     */
    public void setFoodType(FoodType foodType) {this.foodType = foodType;}
    /**
     * Sets the description of this MenuItem
     * @param description This MenuItem's description
     */
    public void setDescription(String description) {this.description = description;}

    // methods
    /**
     * Gets the next menuItemId
     * @return nextMenuItemId incremented by 1
     */
    private static int getNextMenuItemId() {
        return nextMenuItemId++;
    }
    
    /**
     * Prints the details of this MenuItem
     */
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
