package Food;
public class MenuItem extends Food {
    private double price;

    public MenuItem(FoodType type, String name, String description, double price) {
        super(type, name, description);
        this.price = price;
    }

    // getters
    public double getPrice() {
        return price;
    }

    // modifiers

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changePrice(double price) {
        this.price = price;
    }
  
    // toString
    public void print() {
        System.out.println("---------------------\n" + "Type: " + type + "\nName: " + name + "\nDescription: " + description + "\nPrice: " + price);
    }
}