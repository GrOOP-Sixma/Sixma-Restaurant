package Food;

public class Products {
    protected String name;
    protected double price;

    // constructors
    public Products(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // getters
    public String getName() {return name;}
    public double getPrice() {return price;}

    // setters
    public void setName(String name) {this.name = name;}
    public void setPrice(double price) {this.price = price;}
}
