package cp213;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.math.BigDecimal;

/**
 * Stores a List of MenuItems and provides a method return these items in a
 * formatted String. May be constructed from an existing List or from a file
 * with lines in the format:
 *
 * <pre>
1.25 hot dog
10.00 pizza
...
 * </pre>
 *
 * @author Kelli Knipe
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2022-11-29
 */
public class Menu {

	// Attributes.

	// your code here
	
	private List<MenuItem> items = new ArrayList<MenuItem>();
	private static final String itemFormat = "%-12s $%5.2f\n";

	
	/**
	 * Creates a new Menu from an existing Collection of MenuItems. MenuItems are
	 * copied into the Menu List.
	 *
	 * @param items an existing Collection of MenuItems.
	 */
	public Menu(Collection<MenuItem> items) {

		// your code here

		this.items = (List<MenuItem>) items;
	}

	/**
	 * Constructor from a Scanner of MenuItem strings. Each line in the Scanner
	 * corresponds to a MenuItem. You have to read the Scanner line by line and add
	 * each MenuItem to the List of items.
	 *
	 * @param fileScanner A Scanner accessing MenuItem String data.
	 */
	public Menu(Scanner fileScanner) {

		// your code here

		while (fileScanner.hasNextLine()) {
			String scanLine = fileScanner.nextLine();
			String[] lineContent = scanLine.split(" ", 2);
			String name = lineContent[1];
			String priceString = lineContent[0];
			BigDecimal price = new BigDecimal(priceString);
			MenuItem newItem = new MenuItem(name, price);
			this.items.add(newItem);
		}
	}

	/**
	 * Returns the List's i-th MenuItem.
	 *
	 * @param i Index of a MenuItem.
	 * @return the MenuItem at index i
	 */
	public MenuItem getItem(int i) {

		// your code here

		MenuItem result = items.get(i);
		return result;
	}

	/**
	 * Returns the number of MenuItems in the items List.
	 *
	 * @return Size of the items List.
	 */
	public int size() {

		// your code here

		return items.size();
	}

	/**
	 * Returns the Menu items as a String in the format:
	 *
	 * <pre>
	5) poutine      $ 3.75
	6) pizza        $10.00
	 * </pre>
	 *
	 * where n) is the index + 1 of the MenuItems in the List.
	 */
	@Override
	public String toString() {

		// your code here

		String result = "";
		
		for (int i = 0; i < this.size(); i++) {
			String newLine = "";
			newLine = String.format(itemFormat, this.items.get(i).getName(), this.items.get(i).getPrice());
			result = result + newLine;
		}
		return result;
	}
}