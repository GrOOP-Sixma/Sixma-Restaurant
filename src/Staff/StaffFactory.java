package Staff;

import java.util.Scanner;

import Factory.Restaurant;
import Restaurant.RestaurantBack.Gender;

public class StaffFactory {
    private Restaurant restaurant;
    public StaffFactory(Restaurant restaurant){
        this.restaurant = restaurant;
    }

    public void run(){
        int choice = -1;
        Scanner scanner = new Scanner(System.in);
        while (choice != 0){
            System.out.println("1. Hire Staff");
            System.out.println("2. Fire Staff");
            System.out.println("3. View Staff");
            System.out.println("0. Exit");
            choice = scanner.nextInt();
            switch (choice){
                case 1:
                    hireStaff();
                    break;
                case 2:
                    fireStaff();
                    break;
                case 3:
                    viewStaff();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
        scanner.close();
    }

    public void hireStaff(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of new staff");
        String name = scanner.nextLine();
        System.out.println("Enter gender of new staff");
        String gender = scanner.nextLine();
        Gender staffGender = Gender.valueOf(gender);
        System.out.println("Enter role of new staff");
        String role = scanner.nextLine();

        // create the staff
        Staff newStaff = new Staff(name, staffGender, role);
        restaurant.addStaff(newStaff);
        scanner.close();
    }

    public void fireStaff(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of staff to fire");
        String name = scanner.nextLine();
        System.out.println("Enter id of staff");
        int id = scanner.nextInt();
        restaurant.removeStaff(name, id);
        scanner.close();
    }

    public void viewStaff(){
        restaurant.viewStaff();
    }
}
