package Restaurant.RestaurantBack;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import Food.Menu;
import Order.OrderInvoice;
import Food.Menu;
import Food.MenuItem;
public class SalesReport {

	private ArrayList<OrderInvoice> invoices; // list of invoices to report
	private Menu restaurantMenu; // menu of the restaurant

    // todo make it able to accept set menus

    // constructor
    public SalesReport(ArrayList<OrderInvoice> invoices, Menu restaurantMenu) {
        this.invoices = invoices;
        this.restaurantMenu = restaurantMenu;
    }
	
	
	// Method to calculate the total sales revenue for a period
	public double getPeriodRevenue(int month, int year) {
		double total = 0;
		// for every invoice in invoices
        for (OrderInvoice invoice : invoices) {
            // if the invoice is for the given month and year
            if (invoice.getDate().get(Calendar.MONTH) == month && invoice.getDate().get(Calendar.YEAR) == year) {
                // add the invoice's total to the total
                total += invoice.getTotal();
            }
        }
		return total;
	}

	// Calculate how many of each food item in menu was sold
    public HashMap<MenuItem, Integer> getFoodItemCounts(int month, int year) {
        HashMap<MenuItem, Integer> foodItemCounts = new HashMap<MenuItem, Integer>();
        // for every food item in the menu
        for (MenuItem item : restaurantMenu.getAllItems()) {
            // set the count to 0
            foodItemCounts.put(item, 0);
        }
        
        // for every invoice in invoices
        for (OrderInvoice invoice : invoices) {
            // if the invoice is for the given month and year
            if (invoice.getDate().get(Calendar.MONTH) == month && invoice.getDate().get(Calendar.YEAR) == year) {
                // for every food item in the invoice
                for (MenuItem item : invoice.getOrderedItems()) {
                    // add 1 to the count of the food item
                    foodItemCounts.put(item, foodItemCounts.get(item) + 1);
                }
            }
        }
        return foodItemCounts;
    }

	// Method to print out the sales revenue for a period
	// Prints out the PeriodRevenue and PeriodItemSale
	public void printSalesRevenueReport(int month, int year) {
		double revenue = getPeriodRevenue(month,year);
		HashMap<MenuItem, Integer> foodItemCounts = getFoodItemCounts(month,year);

        System.out.println("Sales Report for " + DateFormatSymbols.getInstance().getMonths()[month] + " " + year);
        System.out.println("Total Revenue: $" + revenue);
        System.out.println("Food Item Sales:");
        // for every food item in the menu
        for (MenuItem item : restaurantMenu.getAllItems()) {
            // print out the food item and the count
            System.out.println(item.getName() + ": " + foodItemCounts.get(item));
        }
	}
}