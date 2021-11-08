package Food;
// super class for SetItem and MenuItem to extend from
public class Products {
    protected String name;
    protected double price;

    public Products(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Products() {
        this.name = "";
        this.price = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
