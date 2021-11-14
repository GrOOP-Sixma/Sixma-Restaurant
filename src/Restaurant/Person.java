package Restaurant;

/**
 * Represents the superclass of Staff and Customer class
 * @author chris
 * @version 1.0
 * @since 2021-11-14
 */
public class Person {
    /**
     * The name of This Person
     */
    private String name;

    // constructor
    /**
     * Creates a new Person with the person's name
     * @param name This Person's name
     */
    public Person(String name) {this.name = name;}

    // getters
    /**
     * Gets the name of This Person's name
     * @return This Person's name
     */
    public String getName() {return name;}

    // setters
    /**
     * Sets the name of This Person to this Person's name
     * @param name this Person's name
     */
    public void setName(String name) {this.name = name;}
}
