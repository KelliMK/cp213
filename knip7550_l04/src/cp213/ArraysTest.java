package cp213;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Arrays Lab task methods.
 *
 * @author Kelli Knipe
 * @version 2022-10-17
 */
public class ArraysTest {

    /**
     * Tests arrays.
     *
     * @param args unused default parameter
     */
    public static void main(final String[] args) {
	System.out.println("Task 1");
	System.out.println(ArraysTest.task1());
	System.out.println();
	System.out.println("Task 2");
	System.out.println(ArraysTest.task2());
	System.out.println();
	System.out.println("Task 3");
	System.out.println(ArraysTest.task3());
	System.out.println();
	System.out.println("Task 4");
	System.out.println(ArraysTest.task4());
	System.out.println();
	System.out.println("Task 5");
	System.out.println(Arrays.toString(ArraysTest.task5()));
    }

    /**
     * Print the contents of the arrays first and second using a standard for loop.
     *
     * @return true if second contains the same values as first, false otherwise
     */
    public static boolean task1() {
    	final int[] first = { 1, 3, 5, 7, 9 };
		final int[] second = first;
		boolean result = false;
	
		System.out.println("Values in first:");
	
		for (int i = 0; i < first.length; i++) {
			System.out.println(first[i]);
		}
		System.out.println("Values in second:");
	
		for (int i = 0; i < second.length; i++) {
			System.out.println(second[i]);
		}
		
		if (first.equals(second)) {
			result = true;
		}
	
		return result;
    }

    /**
     * Double the contents of the array first with a standard for loop.
     *
     * @return true if second contains the same values as first, false otherwise
     */
    public static boolean task2() {
    	final int[] first = { 1, 3, 5, 7, 9 };
    	final int[] second = first;
    	boolean result = false;
    	
    	for (int i = 0; i < first.length; i++) {
    		first[i] *= 2;
    	}
    	
    	System.out.println("Values in first:");
    	
		for (int i = 0; i < first.length; i++) {
			System.out.println(first[i]);
		}
		System.out.println("Values in second:");
	
		for (int i = 0; i < second.length; i++) {
			System.out.println(second[i]);
		}
		
    	if (first.equals(second)) {
    		result = true;
    	}

    	return result;
    }

    /**
     * Double the contents of the array first with an enhanced for loop.
     *
     * @return true if the values in first are permanently changed, false otherwise
     */
    public static boolean task3() {
    	final int[] first = { 1, 3, 5, 7, 9 };
    	final int[] comparison = { 2, 6, 10, 14, 18 };
    	boolean result = false;
    	for(int i: first) {
    		System.out.println(i * 2);
    	}
    	
    	if (first.equals(comparison)) {
    		result = true;
    	}
    	return result;
    }

    /**
     * Attempt to assign the array first to a row of the 2D array third.
     *
     * @return true if this is allowed, false otherwise
     */
    public static boolean task4() {
    	final int[] first = { 1, 3, 5, 7, 9 };
    	final int[][] third = {{0}};
    	boolean result = false;
    	
    	third[0] = first;
    	
    	if (third[0].equals(first)) {
    		result = true;
    	}
    	return result;
    }

    /**
     * Assign the values 1 through 10 to an Integer ArrayList.
     *
     * @return the contents of the ArrayList as an Integer[] array.
     */
    public static Integer[] task5() {
    	final ArrayList<Integer> values = new ArrayList<>();

    	for(int i = 0 ; i < 10 ; i++) {
    	    values.add(i);
    	}
    	
    	Integer[] result = values.toArray(new Integer[values.size()]);
    	
    	return result;
    	
    }
}
