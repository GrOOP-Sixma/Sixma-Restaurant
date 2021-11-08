package Food;

import java.io.Serializable;

public class MenuItem implements Serializable {
    private String name;
    private FoodType foodType;
    private String description;
    private double price;

    // constructor
    public MenuItem(String name, FoodType foodType, String description, double price) {
        this.name = name;
        this.foodType = foodType;
        this.description = description;
        this.price = price;
    }

    // getters
    public String getName() {return name;}
    public FoodType getFoodType() {return foodType;}
    public String getDescription() {return description;}
    public double getPrice() {return price;}

    // setters
    public void setName(String name) {this.name = name;}
    public void setFoodType(FoodType foodType) {this.foodType = foodType;}
    public void setDescription(String description) {this.description = description;}
    public void setPrice(double price) {this.price = price;}

    // toString
    public void print() {
        System.out.println("---------------------");
        System.out.println("Name: " + name);
        System.out.println("Food Type: " + foodType);
        System.out.println("Description: " + description);
        System.out.println("Price: " + price);
    }

    // convert to byte array
    public byte[] toByteArray() {
        byte[] byteArray = new byte[1024];
        byteArray[0] = (byte) name.length();
        byteArray[1] = (byte) foodType.ordinal();
        byteArray[2] = (byte) description.length();
        byteArray[3] = (byte) price;
        for (int i = 0; i < name.length(); i++) {
            byteArray[i + 4] = (byte) name.charAt(i);
        }
        for (int i = 0; i < description.length(); i++) {
            byteArray[i + 4 + name.length()] = (byte) description.charAt(i);
        }
        return byteArray;
    }

    // convert to file
    public void toFile(String fileName) {
        byte[] byteArray = toByteArray();
        System.out.println("bytes: " + byteArray.length);
        java.io.FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new java.io.FileOutputStream(fileName);
            fileOutputStream.write(byteArray);
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e);
        } finally {
            try {
                fileOutputStream.close();
            } catch (Exception e) {
                System.out.println("Error closing file: " + e);
            }
        }
    }
}