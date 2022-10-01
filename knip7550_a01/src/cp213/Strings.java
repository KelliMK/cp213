package cp213;

/**
 * @author Your name and id here
 * @version 2022-09-23
 */
public class Strings {
    // Constants
    public static final String VOWELS = "aeiouAEIOU";

    /**
     * Determines if string is a "palindrome": a word, verse, or sentence (such as
     * "Able was I ere I saw Elba") that reads the same backward or forward. Ignores
     * case, spaces, digits, and punctuation in the string parameter s.
     *
     * @param string a string
     * @return true if string is a palindrome, false otherwise
     */
    public static boolean isPalindrome(final String string) {
    	String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	boolean answer = true;
    	int i = 0;
    	String evalStr = "";
    	while (i < string.length()) {
    		char oldChar = string.charAt(i);
    		String newChar = "" + oldChar;
    		if (alpha.contains(newChar.toUpperCase())) {
    			evalStr = evalStr + newChar.toUpperCase();
    		}
    		i++;
    	}
    	int evalLen = evalStr.length();
    	i = 0;
    	while (i < evalLen) {
    		char charA = evalStr.charAt(i);
    		String strA = "" + charA;
    		char charB = evalStr.charAt(evalLen - (1 + i));
    		String strB = "" + charB;
    		if (!strA.equalsIgnoreCase(strB)) {
    			answer = false;
    		}
    		i++;
    	}
    	return answer;
    }

    /**
     * Determines if name is a valid Java variable name. Variables names must start
     * with a letter or an underscore, but cannot be an underscore alone. The rest
     * of the variable name may consist of letters, numbers and underscores.
     *
     * @param name a string to test as a Java variable name
     * @return true if name is a valid Java variable name, false otherwise
     */
    public static boolean isValid(final String name) {
    	boolean answer = true;
    	String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	String digits = "0123456789";
    	int nameLen = name.length();
    	char oldChar = name.charAt(0);
    	String newChar = "" + oldChar;
    	newChar.toUpperCase();
    	if (alpha.contains(newChar.toUpperCase()) || newChar.equalsIgnoreCase("_")) {
    		int i = 1;
    		while (i < nameLen) {
    			oldChar = name.charAt(i);
    			newChar = "" + oldChar;
    			if (!alpha.contains(newChar.toUpperCase()) & !digits.contains(newChar) & !newChar.equals("_")) {
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
     * Converts a word to Pig Latin. The conversion is:
     * <ul>
     * <li>if a word begins with a vowel, add "way" to the end of the word.</li>
     * <li>if the word begins with consonants, move the leading consonants to the
     * end of the word and add "ay" to the end of that. "y" is treated as a
     * consonant if it is the first character in the word, and as a vowel for
     * anywhere else in the word.</li>
     * </ul>
     * Preserve the case of the word - i.e. if the first character of word is
     * upper-case, then the new first character should also be upper case.
     *
     * @param word The string to convert to Pig Latin
     * @return the Pig Latin version of word
     */
    public static String pigLatin(String word) {
    	String newWord = "";
    	String exWord = "";
    	char oldChar = word.charAt(0);
    	boolean upperCheck = Character.isUpperCase(oldChar);
    	String newChar = "" + oldChar;
    	if (VOWELS.contains(newChar)) {
    		newWord = word + "way";
    	} else {
    		newWord = newChar;
    		int wordLen = word.length();
    		int i = 1;
    		boolean trigger = false;
    		while (i < wordLen) {
    			oldChar = word.charAt(i);
    			newChar = "" + oldChar;
    			if (VOWELS.contains(newChar) || newChar.equalsIgnoreCase("y")) {
    				trigger = true;
    			} else if (!trigger) {
    				newChar.toLowerCase();
    				newWord = newWord + newChar;
    			}
    			if (trigger) {
    				exWord = word.substring(i);
    				newWord = exWord + newWord + "ay";
    				i = wordLen;
    			}
    			++i;
    		}
    	}
    	if (upperCheck) {
    		newWord = newWord.substring(0,1).toUpperCase() + newWord.substring(1).toLowerCase();
    	}
    	return newWord;
    }

}
