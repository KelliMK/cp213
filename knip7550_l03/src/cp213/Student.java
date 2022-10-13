package cp213;

import java.time.LocalDate;

/**
 * Student class definition.
 *
 * @author your name here
 * @version 2022-01-17
 */
public class Student implements Comparable<Student> {

    // Attributes
	private int id = 0;
	private String surname = "";
	private String forename = "";
    private LocalDate birthDate = null;
    

    /**
     * Instantiates a Student object.
     *
     * @param id        student ID number
     * @param surname   student surname
     * @param forename  name of forename
     * @param birthDate birthDate in 'YYYY-MM-DD' format
     */
    public Student(int id, String surname, String forename, LocalDate birthDate) {
    	this.id = id;
    	this.surname = surname;
    	this.forename = forename;
    	this.birthDate = birthDate;
    	return;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString() Creates a formatted string of student data.
     */
    @Override
    public String toString() {
    	String string = "Name:      " + surname + ", " + forename + "\nID:        " + id + "\nBirthdate: " + birthDate;
    	return string;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(final Student target) {
    	int result = 0;
    	if (surname.compareToIgnoreCase(target.surname) < 0) {
    		result = -1;
    	} else if (surname.compareToIgnoreCase(target.surname) > 0) {
    		result = 1;
    	} else {
    		if (forename.compareToIgnoreCase(target.forename) < 0) {
    			result = -1;
    		} else if (forename.compareToIgnoreCase(target.forename) > 0) {
    			result = 1;
    		} else {
    			if (id < target.id) {
    				result = -1;
    			} else if (id > target.id) {
    				result = 1;
    			}
    		}
    	}
    	return result;
    }

    /**
     * @return birthDate The student's birth date
     */
    public LocalDate getBirthDate() {
    	return birthDate;
    }
    
    /**
     * @param newDate 	The new birth date for the student
     */
    public void setBirthDate(LocalDate newDate) {
    	birthDate = newDate;
    	return;
    }
    
    /**
     * @return forename 	Student's first name
     */
    public String getForename() {
    	return forename;
    }
    
    /**
     * @param newForename	Student's new first name
     */
    public void setForename(String newForename) {
    	forename = newForename;
    	return;
    }
    
    /**
     * @return Surname 	Student's first name
     */
    public String getSurname() {
    	return surname;
    }
    
    /**
     * @param newSurname	Student's new last name
     */
    public void setSurname(String newSurname) {
    	surname = newSurname;
    	return;
    }
    
    /**
     * @return id	Student's ID number
     */
    public int getId() {
    	return id;
    }
    
    /**
     * @param newID		New student ID
     */
    public void setId(int newID) {
    	id = newID;
    	return;
    }
}
