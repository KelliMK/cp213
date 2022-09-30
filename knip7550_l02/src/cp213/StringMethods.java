package cp213;

/**
 * Sample string methods.
 *
 * @author Kelli M Knipe, 211827550, knip7550@mylaurier.ca
 * @version 2022-09-28
 */
public class StringMethods {
    // Constants
    /**
     * String of vowels.
     * String of digits
     */
    public static final String VOWELS = "aeiouAEIOU";
    public static final String DIGITS = "1234567890";

    /**
     * Counts the number of vowels in a string. Does not include 'y'.
     *
     * @param string A string.
     * @return Number of vowels in string.
     */
    public static int vowelCount(final String string) {
	int count = 0;
	int stringLen = string.length();
	int i = 0;
	while (i < stringLen) {
	    char currentChar = string.charAt(i);
	    String currentString = "" + currentChar;
	    if (VOWELS.contains(currentString)) {
		count += 1;
	    }
	    i++;
	}
	return count;
    }

    /**
     * Counts the number of digits in a string.
     *
     * @param string A string.
     * @return Number of digits in string.
     */
    public static int digitCount(final String string) {
	int count = 0;
	int stringLen = string.length();
	int i = 0;
	while (i < stringLen) {
	    char currentChar = string.charAt(i);
	    String currentString = "" + currentChar;
	    if (DIGITS.contains(currentString)) {
		count += 1;
	    }
	    i++;
	}
	return count;
    }

    /**
     * Totals the individual digits in a string.
     *
     * @param string A string.
     * @return The integer total of all individuals digits in string.
     */
    public static int totalDigits(final String string) {
	int total = 0;
	int stringLen = string.length();
	int i = 0;
	while (i < stringLen) {
	    char currentChar = string.charAt(i);
	    String currentString = "" + currentChar;
	    if (DIGITS.contains(currentString)) {
		total += Integer.parseInt(currentString);
	    }
	    i++;
	}
	return total;
    }

    /**
     * Compares string1 and string2 and returns a comma-delimited concatenated
     * string consisting of the string that is first lexically followed by the
     * string that is second lexically. If the strings are equal, then only string1
     * is returned.
     *
     * @param string1 String to compare against string2.
     * @param string2 String to compare against string1.
     * @return A concatenation of string1 and string2 in order.
     */
    public static String pairs(String string1, String string2) {
	String line = null;
	int comparison = string1.compareTo(string2);
	if (comparison == 0) {
	    line = "{" + string1 + "}";
	} else if (comparison < 0) {
	    line = "{" + string1 + "," + string2 + "}";
	} else if (comparison > 0) {
	    line = "{" + string2 + "," + string1 + "}";
	}
	return line;
    }

    /**
     * Finds the distance between the s1 and s2. The distance between two strings of
     * the same length is the number of positions in the strings at which their
     * characters are different. If two strings are not the same length, the
     * distance is unknown (-1). If the distance is zero, then two strings are
     * equal.
     *
     * @param string1 String to compare against string2.
     * @param string2 String to compare against string1.
     * @return The distance between string1 and string2.
     */
    public static int stringDistance(String string1, String string2) {
	int distance = 0;
	int len1 = string1.length();
	int len2 = string2.length();
	if (len1 != len2) {
	    distance = -1;
	} else {
	    int i = 0;
	    while (i < len1) {
		char char1 = string1.charAt(i);
		char char2 = string2.charAt(i);
		if (char1 != char2) {
		    distance++;
		}
		i++;
	    }
	}
	return distance;
    }
}
