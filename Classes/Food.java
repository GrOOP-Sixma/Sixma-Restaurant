public class Food {
    private FoodType type; // type of food
    private String name; // name of food
    private String description; // description of food
    private double price; // price of food

    public Food(FoodType type, String name, String description, double price) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public FoodType getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
