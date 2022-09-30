package cp213;

/**
 * @author Your name and id here
 * @version 2022-09-28
 */
public class Cipher {
    // Constants
    public static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final int ALPHA_LENGTH = ALPHA.length();

    /**
     * Encipher a string using a shift cipher. Each letter is replaced by a letter
     * 'n' letters to the right of the original. Thus for example, all shift values
     * evenly divisible by 26 (the length of the English alphabet) replace a letter
     * with itself. Non-letters are left unchanged.
     *
     * @param s string to encipher
     * @param n the number of letters to shift
     * @return the enciphered string in all upper-case
     */
    public static String shift(final String s, final int n) {
	int newN = n % 26;
	int sLen = s.length();
	String cipherS = "";
	int i = 0;
	while (i < sLen) {
	    char oldChar = s.charAt(i);
	    String newSChar = "" + oldChar;
	    newSChar.toUpperCase();
	    if (ALPHA.contains(newSChar)) {
		int newIndex = ALPHA.indexOf(newSChar) + newN;
		newIndex = newIndex % 26;
		cipherS = cipherS + ALPHA.charAt(newIndex);
	    } else {
		cipherS = cipherS + newSChar;
	    }
	    i++;
	}
	return cipherS;
    }

    /**
     * Encipher a string using the letter positions in ciphertext. Each letter is
     * replaced by the letter in the same ordinal position in the ciphertext.
     * Non-letters are left unchanged. Ex:
     *
     * <pre>
    Alphabet:   ABCDEFGHIJKLMNOPQRSTUVWXYZ
    Ciphertext: AVIBROWNZCEFGHJKLMPQSTUXYD
     * </pre>
     *
     * A is replaced by A, B by V, C by I, D by B, E by R, and so on. Non-letters
     * are ignored.
     *
     * @param s          string to encipher
     * @param ciphertext ciphertext alphabet
     * @return the enciphered string in all upper-case
     */
    public static String substitute(final String s, final String ciphertext) {
	ciphertext.toUpperCase();
	int i = 0;
	int sLen = s.length();
	while (i < sLen) {
	    char oldChar = s.charAt(i);
	    
	}
	return null;
    }

}
