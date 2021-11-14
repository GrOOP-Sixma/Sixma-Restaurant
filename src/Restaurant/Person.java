package Restaurant;

/**
 * Represents a person to initialise a Restaurant
 * @author chris
 *
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
     */
    public void setName(String name) {this.name = name;}
}
