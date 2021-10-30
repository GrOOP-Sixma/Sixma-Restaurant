package Food;
public class Food {
    protected FoodType type; // type of food
    protected String name; // name of food
    protected String description; // description of food

    public Food(FoodType type, String name, String description) {
        this.type = type;
        this.name = name;
        this.description = description;
    }

    public FoodType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
