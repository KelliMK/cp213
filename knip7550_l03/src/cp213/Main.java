package cp213;

import java.time.LocalDate;

/**
 * Tests the Student class.
 *
 * @author your name here
 * @version 2022-01-17
 */
public class Main {

    public static void main(String[] args) {
	final String line = "-".repeat(40);
	int id = 123456;
	String surname = "Brown";
	String forename = "David";
	LocalDate birthDate =  LocalDate.parse("1962-10-25");
	Student student = new Student(id, surname, forename, birthDate);
	System.out.println("New Student:");
	System.out.println(student.toString());
	System.out.println(line);
	System.out.println("Test Getters");
	System.out.println(student.getBirthDate());
	System.out.println(student.getForename());
	System.out.println(student.getSurname());
	System.out.println(student.getId());
	System.out.println(line);
	System.out.println("Test Setters");
	int testID = 234567;
	String testSurname = "Blue";
	String testForename = "Peter";
	LocalDate testBirthDate =  LocalDate.parse("1969-06-09");
	student.setBirthDate(testBirthDate);
	student.setForename(testForename);
	student.setSurname(testSurname);
	student.setId(testID);
	System.out.println("Updated Student:");
	System.out.println(student.getBirthDate());
	System.out.println(student.getForename());
	System.out.println(student.getSurname());
	System.out.println(student.getId());
	System.out.println(line);
	System.out.println("Test compareTo");
	int firstID = 000000;
	int lastID = 999999;
	String firstSurname = "AAAAAA";
	String lastSurname = "ZZZZZZ";
	String firstForename = "AAAAA";
	String lastForename = "ZZZZZ";
	LocalDate firstBirthDate =  LocalDate.parse("1900-01-01");
	LocalDate lastBirthDate =  LocalDate.parse("3000-12-30");
	Student firstStudent = new Student(firstID, firstSurname, firstForename, firstBirthDate);
	Student lastStudent = new Student(lastID, lastSurname, lastForename, lastBirthDate);
	// create new Students - call comparisons here
	System.out.println("The next line should say 1");
	System.out.println(student.compareTo(firstStudent));
	System.out.println("The next line should say -1");
	System.out.println(student.compareTo(lastStudent));
    }

}
