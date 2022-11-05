package cp213;

/**
 * Inherited class in simple example of inheritance / polymorphism.
 *
 * @author Kelli Knipe 211827550
 * @version 2022-11-04
 */
public class IA extends Student {
	
	private String course = null;
	
	public IA(String lastName, String firstName, String id, String course) {
		super(lastName, firstName, id);
		this.course = course;
	}
	
	/**
	 * Returns Course name
	 */
	public String getCourse() {
		return course;
	}
	
	/**
	 * Returns a string formatted with the IA data:
	 * Chin, Li-meng: 987478
	 * Course: CP213
	 */
	@Override
	public String toString() {
		String result = "";
		result = (super.toString() + "\nCourse: " + getCourse());
		return result;
	}
}
