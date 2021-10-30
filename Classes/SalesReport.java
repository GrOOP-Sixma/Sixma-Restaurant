import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;

public class SalesReport {

	private ArrayList<OrderInvoice> invoice;
	private ArrayList<MenuItems> food;
	
	
	// Method to calculate the total sales revenue for a period
	public double getPeriodRevenue(int month, int year) {
		double total = 0;
		// For every invoice in OrderInvoice
		for (OrderInvoice i: invoice) {
			// If month and year on the invoices == period we want, then sum the total of the invoices
			// Month + 1 as Calendar.MONTH is indexed from 0 to 11
			if ((i.cal.get(Calendar.MONTH) + 1 == month) && (i.cal.get(Calendar.YEAR) == year )) {
				total += i.getTotalPrice();
			}
		}
		return total;
	}

	// Method to calculate the total number of individual items sold on MenuItems
	public int[] getPeriodItemSale(int month, int year) {
		// Initialise an array of MenuItems size to keep track of every number of every item sold
		int[] itemSale = new int[MenuItems.size];
		
		// For every invoice in OrderInvoice
		for (OrderInvoice i : invoice) {
			// If the period of the invoices matches the period
			if ((i.cal.get(Calendar.MONTH) + 1 == month && (i.cal.get(Calendar.YEAR) == year)){
				// Increase the count of the index of the item in MenuItems
				// by calling getOrderItems from Order class
				itemSale[MenuItems[i.Order.getOrderItems]] += 1;
			}
		}
		return itemSale;
		
	}

	// Method to print out the sales revenue for a period
	// Prints out the PeriodRevenue and PeriodItemSale
	public void printSalesRevenueReport(int month, int year) {
		double revenue = getPeriodRevenue(month,year);
		int[] itemSale = getPeriodItemSale(month,year);
		
		String monthName = new DateFormatSymbols().getMonths()[month-1];
		System.out.println("Sales Revenue Report for " + monthName + " of year " + year);
		System.out.println("Total Sales Revenue: " + revenue);
		System.out.println("Total Number of Food Items sold: ");
		
		// Prints out the number of items sold for every item of MenuItems
		for (food f: MenuItems) {
			System.out.println(f.getMenuItem() + ": " + itemSale[MenuItems.indexOf(f)]);
		}
	}

}