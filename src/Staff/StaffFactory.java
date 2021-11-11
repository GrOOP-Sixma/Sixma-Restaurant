package Staff;

import java.util.Scanner;

import Restaurant.RestaurantBack.Gender;

public class StaffFactory {
    private StaffController staffController;
    private String name;

    // constructors
    public StaffFactory(String name) {
        staffController = new StaffController(name);
        this.name = name;
    }

    // getters
    public StaffController getStaffController() {return staffController;}

    // methods
    public int getIntInput() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (sc.hasNextInt()) {
                return sc.nextInt();
            }
            else {
                System.out.println("Invalid input.");
                sc.next();
            }
        }
    }

    public void run(){
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        while (choice != 0){
            System.out.println("\nStaff Manager:");
            System.out.println("1. Hire Staff");
            System.out.println("2. Fire Staff");
            System.out.println("3. View Staff");
            System.out.println("0. Exit");
            loop: while (choice != 0) {
                choice = getIntInput();
                switch (choice) {
                    case 0:
                        continue;
                    case 1:
                        hireStaff();
                        break loop;
                    case 2:
                        fireStaff();
                        break loop;
                    case 3:
                        viewStaff();
                        break loop;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }
    }

    public void hireStaff(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of new staff:");
        String name = sc.nextLine();

        System.out.println("Enter gender of new staff:");
        System.out.println("1. Male");
        System.out.println("2. Female");
        int gender = getIntInput();
        while (gender != 1 && gender != 2) {
            System.out.println("Invalid choice");
            gender = getIntInput();
        }

        System.out.println("Enter role of new staff:");
        String role = sc.nextLine();

        Staff newStaff;
        if (gender == 1) {
            newStaff = new Staff(name, Gender.MALE, role);
        }
        else {
            newStaff = new Staff(name, Gender.FEMALE, role);
        }
        staffController.addStaff(newStaff);
    }

    public void fireStaff(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of staff to fire:");
        String name = sc.nextLine();

        System.out.println("Enter id of staff:");
        int id = getIntInput();
        while (id <= 0) {
            System.out.println("Invalid id");
            id = getIntInput();
        }
        if (staffController.removeStaff(name, id) == 0) {
            System.out.println(name + " is not a registered staff");
        }
    }

    public void viewStaff(){
        staffController.viewStaff();
    }

    public void writeInstances() {
        staffController.writeInstances();
    }
}
