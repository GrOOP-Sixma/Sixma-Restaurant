package Restaurant.RestaurantBack;

import Restaurant.Person;

public class Staff extends Person {
    private Gender gender;
	private int id;
    private String role;

    // constructor
    public Staff(String name, Gender gender, int id, String role) {
        super(name);
        this.gender = gender;
        this.id = id;
        this.role = role;
    }

    // getters
    public Gender getGender() {return gender;}
    public int getId() {return id;}
    public String getRole() {return role;}

    // setters
    public void setGender(Gender gender) {this.gender = gender;}
    public void setId(int id) {this.id = id;}
    public void setRole(String role) {this.role = role;}
}