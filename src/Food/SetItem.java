package Food;

public class SetItem extends Products {
    private int setSize;
    private MenuItem[] setItems;
    private double setPrice;
    
    public SetItem() {
        super();
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSetSize() {
        this.setSize = this.setItems.length;
    }

    public void setSetItems(MenuItem[] setItems) {
        this.setItems = setItems;
        setSetSize();
    }

    public void setSetPrice(double setPrice) {
        this.setPrice = setPrice;
    }

    // getters
    public String getName() {
        return name;
    }

    public int getSetSize() {
        return setSize;
    }

    public MenuItem[] getSetItems() {
        return setItems;
    }

    public double getSetPrice() {
        return setPrice;
    }

    public void printSet() {
        System.out.println("Set size: " + setSize);
        System.out.println("Set items: ");
        for (int i = 0; i < setItems.length; i++) {
            System.out.println(setItems[i].getName());
        }
        System.out.println("Set price: " + setPrice);
    }    
}
