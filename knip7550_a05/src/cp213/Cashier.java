package cp213;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Wraps around an Order object to ask for MenuItems and quantities.
 *
 * @author Kelli Knipe
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2022-11-29
 */
public class Cashier {

	// Attributes
	private Menu menu = null;
	private static final String LINE = "-".repeat(40);

	/**
	 * Constructor.
	 *
	 * @param menu A Menu.
	 */
	public Cashier(Menu menu) {
		this.menu = menu;
	}

	/**
	 * Prints the menu.
	 */
	private void printCommands() {
		System.out.println("\nMenu:");
		System.out.println(menu.toString());
		System.out.println("Press 0 when done.");
		System.out.println("Press any other key to see the menu again.\n");
	}

	/**
	 * Asks for commands and quantities. Prints a receipt when all orders have been
	 * placed.
	 *
	 * @return the completed Order.
	 */
	public Order takeOrder() {
		System.out.println("Welcome to WLU Foodorama!");

		// your code here

		printCommands();
		Order order = new Order();
		System.out.println();
		Scanner inputScan = new Scanner(System.in);
		System.out.print("Command: ");
		String command = inputScan.nextLine();
		String amount = "";
		while (!command.contentEquals("0") && !amount.contentEquals("0")) {
			try {
				if (Integer.parseInt(command) > 0 && Integer.parseInt(command) < menu.size() + 1) {
					int i = Integer.parseInt(command) - 1;
					MenuItem item = menu.getItem(i);
					System.out.print("How many do you want? ");
					amount = inputScan.nextLine();
					try {
						int quantity = Integer.parseInt(amount);
						if (quantity > 0) {
							order.add(item, quantity);
						}
					} catch (NumberFormatException e) {
						System.out.println("Not a valid number");
					}
				} else if (Integer.parseInt(command) != 0) {
					printCommands();
				}
			} catch (NumberFormatException e) {
				System.out.println("Not a valid number");
				System.out.println();
				printCommands();
			}
			System.out.print("Command: ");
			command = inputScan.nextLine();
		}
		inputScan.close();
		System.out.println(LINE);
		System.out.println("Receipt");
		System.out.println(order.toString());
		return order;
	}
}