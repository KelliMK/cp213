package cp213;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map.Entry;


/**
 * Stores a HashMap of MenuItem objects and the quantity of each MenuItem
 * ordered. Each MenuItem may appear only once in the HashMap.
 *
 * @author Kelli Knipe
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2022-11-29
 */
public class Order implements Printable {

	/**
	 * The current tax rate on menu items.
	 */
	public static final BigDecimal TAX_RATE = new BigDecimal(0.13);

	// Attributes

	// your code here

	private HashMap<MenuItem, Integer> map = new HashMap<>();
	
	/**
	 * Increments the quantity of a particular MenuItem in an Order with a new
	 * quantity. If the MenuItem is not in the order, it is added.
	 *
	 * @param item     The MenuItem to purchase - the HashMap key.
	 * @param quantity The number of the MenuItem to purchase - the HashMap value.
	 */
	public void add(final MenuItem item, final int quantity) {

		// your code here

		
		if (!map.containsKey(item)) {
			this.map.put(item, quantity);
		} else {
			int i = this.map.get(item);
			this.map.replace(item, i, quantity + i);
		}
	}

	/**
	 * Calculates the total value of all MenuItems and their quantities in the
	 * HashMap.
	 *
	 * @return the total price for the MenuItems ordered.
	 */
	public BigDecimal getSubTotal() {

		// your code here

		BigDecimal subtotal = BigDecimal.ZERO;
		for (Entry<MenuItem, Integer> entry : this.map.entrySet()) {
			MenuItem item = entry.getKey();
			Integer quantity = entry.getValue();
			BigDecimal price = item.getPrice();
			price = price.multiply(BigDecimal.valueOf(quantity));
			subtotal = subtotal.add(price);
		}
		subtotal = subtotal.setScale(2, RoundingMode.HALF_UP);
		return subtotal;
	}

	/**
	 * Calculates and returns the total taxes to apply to the subtotal of all
	 * MenuItems in the order. Tax rate is TAX_RATE.
	 *
	 * @return total taxes on all MenuItems
	 */
	public BigDecimal getTaxes() {

		// your code here

		BigDecimal taxes = BigDecimal.ZERO;
		taxes = taxes.add(TAX_RATE);
		taxes = taxes.multiply(this.getSubTotal());
		taxes = taxes.setScale(2, RoundingMode.HALF_UP);
		return taxes;
	}

	/**
	 * Calculates and returns the total price of all MenuItems order, including tax.
	 *
	 * @return total price
	 */
	public BigDecimal getTotal() {

		// your code here

		BigDecimal total = BigDecimal.ZERO;
		total = this.getSubTotal().add(this.getTaxes());
		total = total.setScale(2, RoundingMode.HALF_UP);
		return total;
	}

	/*
	 * Implements the Printable interface print method. Prints lines to a Graphics2D
	 * object using the drawString method. Prints the current contents of the Order.
	 */
	@Override
	public int print(final Graphics graphics, final PageFormat pageFormat, final int pageIndex)
			throws PrinterException {
		int result = PAGE_EXISTS;

		if (pageIndex == 0) {
			final Graphics2D g2d = (Graphics2D) graphics;
			g2d.setFont(new Font("MONOSPACED", Font.PLAIN, 12));
			g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
			// Now we perform our rendering
			final String[] lines = this.toString().split("\n");
			int y = 100;
			final int inc = 12;

			for (final String line : lines) {
				g2d.drawString(line, 100, y);
				y += inc;
			}
		} else {
			result = NO_SUCH_PAGE;
		}
		return result;
	}

	/**
	 * Returns a String version of a receipt for all the MenuItems in the order.
	 */
	@Override
	public String toString() {

		// your code here

		String result = "";
		for (@SuppressWarnings("unused") Entry<MenuItem, Integer> entry : map.entrySet()) {
			MenuItem item = entry.getKey();
			String name = item.getName();
			String quantity = Integer.toString(entry.getValue());
			BigDecimal price = item.getPrice();
			BigDecimal itemTotal = item.getPrice().multiply(BigDecimal.valueOf(entry.getValue()));
			result = result + String.format("%-13s %3s @ $%5.2f = $%6.2f \n", name, quantity, price, itemTotal);
		}
		result += String.format("\n%-27s $%6.2f\n%-27s $%6.2f\n%-27s $%6.2f\n", "Subtotal:", this.getSubTotal(), "Taxes:", this.getTaxes(), "Total:", this.getTotal());
		return result;
	}

	/**
	 * Replaces the quantity of a particular MenuItem in an Order with a new
	 * quantity. If the MenuItem is not in the order, it is added. If quantity is 0
	 * or negative, the MenuItem is removed from the Order.
	 *
	 * @param item     The MenuItem to update
	 * @param quantity The quantity to apply to item
	 */
	public void update(final MenuItem item, final int quantity) {

		// your code here

		if (quantity < 0) {
			this.map.remove(item);
		} else {
			this.map.replace(item, quantity);
		}
	}
}