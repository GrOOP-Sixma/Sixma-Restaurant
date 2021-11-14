package Food;
/**
 * Represents the superclass for MenuItem and SetItem classes
 * @author tengwei
 * @version 1.0
 * @since 2021-11-14
 */
public class Products {
	/**
	 * The name of this Products
	 */
    protected String name;
    /**
     * The price of this Products
     */
    protected double price;

    // constructors
    /**
     * Creates a new Products with this Products' name and price
     * @param name This Products' name
     * @param price This Products' price
     */
    public Products(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // getters
    /**
     * Gets the name of this Products
     * @return This Products' name
     */
    public String getName() {return name;}
    /**
     * Gets the price of this Products
     * @return This Products' price
     */
    public double getPrice() {return price;}

    // setters
    /**
     * Sets the name of this Products
     * @param name This Products' name
     */
    public void setName(String name) {this.name = name;}
    /**
     * Sets the price of this Products
     * @param price This Products' price
     */
    public void setPrice(double price) {this.price = price;}
}
