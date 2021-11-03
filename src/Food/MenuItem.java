package Food;

public class MenuItem {
    private String name;
    private FoodType foodType;
    private String description;
    private double price;

    // constructor
    public MenuItem(String name, FoodType foodType, String description, double price) {
        this.name = name;
        this.foodType = foodType;
        this.description = description;
        this.price = price;
    }

    // getters
    public String getName() {return name;}
    public FoodType getFoodType() {return foodType;}
    public String getDescription() {return description;}
    public double getPrice() {return price;}

    // setters
    public void setName(String name) {this.name = name;}
    public void setFoodType(FoodType foodType) {this.foodType = foodType;}
    public void setDescription(String description) {this.description = description;}
    public void setPrice(double price) {this.price = price;}

    // toString
    public void print() {
        System.out.println("---------------------");
        System.out.println("Name: " + name);
        System.out.println("Food Type: " + foodType);
        System.out.println("Description: " + description);
        System.out.println("Price: " + price);
    }
}