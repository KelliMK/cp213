package cp213;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Utilities for working with Food objects.
 *
 * @author Kelli Knipe, 211827550, knip7550
 * @version 2022-10-13
 */
public class FoodUtilities {

    /**
     * Determines the average calories in a list of foods. No rounding necessary.
     * Foods list parameter may be empty.
     *
     * @param foods a list of Food
     * @return average calories in all Food objects
     */
    public static int averageCalories(final ArrayList<Food> foods) {
    	int result = 0;
    	int size = foods.size();
    	if (size == 0) {
    		result = 0;
    	} else {
    		for (int i = 0; i < foods.size(); i++) {
        		Food currentFood = foods.get(i);
        		result += currentFood.getCalories();
        	}
        	result = result / size;
    	}
    	return result;
    }

    /**
     * Determines the average calories in a list of foods for a particular origin.
     * No rounding necessary. Foods list parameter may be empty.
     *
     * @param foods  a list of Food
     * @param origin the origin of the Food
     * @return average calories for all Foods of the specified origin
     */
    public static int averageCaloriesByOrigin(final ArrayList<Food> foods, final int origin) {
    	int result = 0;
    	int divisor = 0;
    	for (int i = 0; i < foods.size(); i++) {
    		Food currentFood = foods.get(i);
    		if (currentFood.getOrigin() == origin) {
    			result += currentFood.getCalories();
    			divisor++;
    		}
    	}
    	if (divisor > 0) {
    		result = result / divisor;
    	}
    	return result;
    }

    /**
     * Creates a list of foods by origin.
     *
     * @param foods  a list of Food
     * @param origin a food origin
     * @return a list of Food from origin
     */
    public static ArrayList<Food> getByOrigin(final ArrayList<Food> foods, final int origin) {
    	ArrayList<Food> originFoods = new ArrayList<Food>();
    	for (int i = 0; i < foods.size(); i++) {
    		Food currentFood = foods.get(i);
    		if (currentFood.getOrigin() == origin) {
    			originFoods.add(currentFood);
    		}
    	}
    	return originFoods;
    }

    /**
     * Creates a Food object by requesting data from a user. Uses the format:
     *
     * <pre>
    Name: name
    Origins
     0 Canadian
     1 Chinese
    ...
    11 English
    Origin: origin number
    Vegetarian (Y/N): Y/N
    Calories: calories
     * </pre>
     *
     * @param keyboard a keyboard Scanner
     * @return a Food object
     */
    public static Food getFood(final Scanner keyboard) {
    	String name = "";
    	int origin = 0;
    	String veggieString = "";
    	boolean isVegetarian = false;
    	int calories = 0;
    	System.out.print("Name: ");
    	name = keyboard.nextLine();
    	Food.originsMenu();
    	System.out.print("Origin: ");
    	origin = keyboard.nextInt();
    	System.out.print("Vegetarian (Y/N): ");
    	veggieString = keyboard.next();
    	System.out.print("Calories: ");
    	calories = keyboard.nextInt();
    	if (veggieString.equals("Y") || veggieString.equals("y")) {
    		isVegetarian = true;
    	} else {
    		isVegetarian = false;
    	}
    	Food newFood = new Food(name, origin, isVegetarian, calories);
    	return newFood;
    }

    /**
     * Creates a list of vegetarian foods.
     *
     * @param foods a list of Food
     * @return a list of vegetarian Food
     */
    public static ArrayList<Food> getVegetarian(final ArrayList<Food> foods) {
    	ArrayList<Food> veggieFoods = new ArrayList<Food>();
    	for (int i = 0; i < foods.size(); i++) {
    		Food currentFood = foods.get(i);
    		if (currentFood.isVegetarian()) {
    			veggieFoods.add(currentFood);
    		}
    	}
    	return veggieFoods;
    }

    /**
     * Creates and returns a Food object from a line of string data.
     *
     * @param line a vertical bar-delimited line of food data in the format
     *             name|origin|isVegetarian|calories
     * @return the data from line as a Food object
     */
    public static Food readFood(final String line) {
    	String[] foodStuff = line.split("[|]");
    	int origin = Integer.parseInt(foodStuff[1]);
    	boolean isVegetarian = Boolean.parseBoolean(foodStuff[2]);
    	int calories = Integer.parseInt(foodStuff[3]);
    	Food newFood = new Food(foodStuff[0], origin, isVegetarian, calories);
    	return newFood;
    }

    /**
     * Reads a file of food strings into a list of Food objects.
     *
     * @param fileIn a Scanner of a Food data file in the format
     *               name|origin|isVegetarian|calories
     * @return a list of Food
     */
    public static ArrayList<Food> readFoods(final Scanner fileIn) {
    	ArrayList<Food> foodList = new ArrayList<Food>();
    	while (fileIn.hasNext()) {
    		String foodString = fileIn.nextLine();
    		String[] foodStuff = foodString.split("[|]");
        	int origin = Integer.parseInt(foodStuff[1]);
        	boolean isVegetarian = Boolean.parseBoolean(foodStuff[2]);
        	int calories = Integer.parseInt(foodStuff[3]);
        	Food newFood = new Food(foodStuff[0], origin, isVegetarian, calories);
        	foodList.add(newFood);
    	}
    	return foodList;
    }

    /**
     * Searches for foods that fit certain conditions.
     *
     * @param foods        a list of Food
     * @param origin       the origin of the food; if -1, accept any origin
     * @param maxCalories  the maximum calories for the food; if 0, accept any
     * @param isVegetarian whether the food is vegetarian or not; if false accept
     *                     any
     * @return a list of foods that fit the conditions specified
     */
    public static ArrayList<Food> foodSearch(final ArrayList<Food> foods, final int origin, final int maxCalories,
	    final boolean isVegetarian) {
    	ArrayList<Food> dietFood = new ArrayList<Food>();
    	for (int i = 0; i < foods.size(); i++) {
    		Food currFood = foods.get(i);
    		if ((currFood.getOrigin() == origin || origin == (-1)) & (currFood.getCalories() <= maxCalories || maxCalories == 0) & (currFood.isVegetarian() || !isVegetarian)) {
    			dietFood.add(currFood);
    		}
    	}
    	return dietFood;
    }

    /**
     * Writes the contents of a list of Food to a PrintStream.
     *
     * @param foods a list of Food
     * @param ps    the PrintStream to write to
     */
    public static void writeFoods(final ArrayList<Food> foods, PrintStream ps) {

	// your code here

    }
}
