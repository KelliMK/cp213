package cp213;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	/**
	 * @param args unused
	 */
	public static void main(String[] args) {
		System.out.println("Test scannerTest");
		System.out.println();
		Scanner keyboard = new Scanner(System.in);
		int total = scannerTest(keyboard);
		keyboard.close();
		System.out.println("Total: " + total);
		System.out.println();

		System.out.print("Test stringPrinter");
		System.out.println();

		try {
			String output = stringPrinter(5, "*");
			System.out.println(output);
			output = stringPrinter(-5, "*");
			System.out.println(output);
		} catch (Exception e) {
			System.out.println();
			System.out.println("getMessage:");
			System.out.println(e.getMessage());
			System.out.println();
			System.out.println("toString:");
			System.out.println(e.toString());
			System.out.println();
			System.out.println("printStackTrace:");
			e.printStackTrace();
		}
	}

	/**
	 * Uses exceptions to capture bad input from a keyboard Scanner.
	 *
	 * @return The total of all the integers entered.
	 */
	public static int scannerTest(final Scanner keyboard) {
		int total = 0;
		int num = 0;
		String exceptionCheck = "";
		System.out.print("Enter an integer (0 to stop): ");
		try {
			num = keyboard.nextInt();
			total += num;
		} catch (InputMismatchException e) {
			System.out.println("That is not an integer!");
		}
		if (total != 0) {
			while (!exceptionCheck.equals("Quit")) {
				try {
					System.out.print("Enter an integer ('Quit' to stop): ");
					total += keyboard.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("That is not an integer!");
					exceptionCheck = keyboard.next();
				}
			}
		}
		return total;
	}

	/**
	 * Repeats a string.
	 *
	 * @param n   Number of times to repeat a string.
	 * @param str The string to print.
	 * @return The repeated string.
	 * @throws Exception If n is negative.
	 */
	public static String stringPrinter(int n, String str) throws Exception {
		String result = "";
		String err = "Please Enter a Positive Number!";
		try {
			if (n < 0) {
				throw new Exception(err);
			} else {
				for(int i = 0; i < n; i++) {
					result += str;
				}
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return result;
	}

}
