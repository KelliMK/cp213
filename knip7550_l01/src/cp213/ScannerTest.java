package cp213;

import java.util.Scanner;

/**
 * Class to demonstrate the use of Scanner with a keyboard and File objects.
 *
 * @author Kelli Knipe
 * @version 2022-09-21
 */
public class ScannerTest {
    /**
     * Count lines in the scanned file.
     *
     * @param source Scanner to process
     * @return number of lines in scanned file
     */
    public static int countLines(final Scanner source) {
	int count = 0;
	while (source.hasNextLine()) {
	    count++;
	    source.nextLine();
	}
	return count;
    }

    /**
     * Count tokens in the scanned object.
     *
     * @param source Scanner to process
     * @return number of tokens in scanned object
     */
    public static int countTokens(final Scanner source) {
	int tokens = 0;
	while (source.hasNext()) {
	    tokens++;
	    source.next();
	}
	return tokens;
    }

    /**
     * Ask for and total integers from the keyboard.
     *
     * @param keyboard Scanner to process
     * @return total of positive integers entered from keyboard
     */
    public static int readNumbers(final Scanner keyboard) {
	int total = 0;
	String check = "";
	while (!check.equals("q")) {
	    if (!keyboard.hasNextInt()) {
		check = keyboard.nextLine();
		if (!check.equals("")) {
		    System.out.println("'" + check + "' is not an integer");
		}
	    }
	    else {
		total += keyboard.nextInt();
	    }
	}
	return total;
    }


}
