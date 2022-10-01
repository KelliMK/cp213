package cp213;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Your name and id here
 * @version 2022-09-23
 */
public class SerialNumber {

    // Constants
    public static final String DIGITS = "0123456789";
    
    /**
     * Determines if a string contains all digits.
     *
     * @param str The string to test.
     * @return true if str is all digits, false otherwise.
     */
    public static boolean allDigits(final String str) {
	int strLen = str.length();
	int i = 0;
	boolean answer = true;
	while (i < strLen) {
	    char oldChar = str.charAt(i);
	    String newChar = "" + oldChar;
	    if (DIGITS.contains(newChar)) {
		answer = false;
	    }
	    i++;
	}
	return answer;
    }

    /**
     * Determines if a string is a good serial number. Good serial numbers are of
     * the form 'SN/nnnn-nnn', where 'n' is a digit.
     *
     * @param sn The serial number to test.
     * @return true if the serial number is valid in form, false otherwise.
     */
    public static boolean validSn(final String sn) {
	boolean answer = true;
	if (sn.length() == 11) {
	    int i = 0;
	    while (i < 11 & answer == true) {
		char oldChar = sn.charAt(i);
		String newChar = "" + oldChar;
		if (i == 0 & !newChar.contentEquals("S")) {
		    answer = false;
		} else if (i == 1 & !newChar.contentEquals("N")) {
		    answer = false;
		} else if (i == 2 & !newChar.contentEquals("/")) {
		    answer = false;
		} else if (i == 7 & !newChar.contentEquals("-")) {
		    answer = false;
		} else if (!DIGITS.contains(newChar)) {
		    answer = false;
		}
		i++;
	    }
	} else {
	    answer = false;
	}
	return answer;
    }

    /**
     * Evaluates serial numbers from a file. Writes valid serial numbers to
     * good_sns, and invalid serial numbers to bad_sns.
     *
     * @param fileIn  a file already open for reading
     * @param goodSns a file already open for writing
     * @param badSns  a file already open for writing
     */
    public static void validSnFile(final Scanner fileIn, final PrintStream goodSns, final PrintStream badSns) {
	
	return;
    }

}
