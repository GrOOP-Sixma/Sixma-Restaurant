package RestaurantBack;
public class Staff {

	private String name; // name of the staff member
	private int id; // id of the staff member
    private String role; // role of the staff member

    public Staff(String name, int id, String role) {
        this.name = name;
        this.id = id;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void changeRole(String newRole) {
        this.role = newRole;
    }
}