package Food;

import java.text.DecimalFormat;

public class MenuItem extends Products {
    private int menuItemId;
    private static int nextMenuItemId = 1;
    private FoodType foodType;
    private String description;

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
        System.out.println("-------------------------------");
        System.out.println("Item ID: " + menuItemId);
        System.out.println("Name: " + name);
        if (foodType == FoodType.MAIN_COURSE) {
            System.out.println("Food Type: Main Course");
        }
        else if (foodType == FoodType.DRINKS) {
            System.out.println("Food Type: Drinks");
        }
        else {
            System.out.println("Food Type: Dessert");
        }
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("Price: $" + df.format(price));
        System.out.println("Description: " + description);
    }
}
