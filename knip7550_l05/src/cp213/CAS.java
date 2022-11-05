package cp213;

/**
 * Inherited class in simple example of inheritance / polymorphism.
 *
 * @author Kelli Knipe 211827550
 * @version 2022-11-04
 */
public class CAS extends Professor {
	private String term = null;
	public CAS(String lastName, String firstName, String department, String term) {
		super(lastName, firstName, department);
		this.term = term;
	}
	
	/**
	 * Returns Course name
	 */
	public String getTerm() {
		return term;
	}
	
	/**
	 * Returns a string formatted with the IA data:
	 * McGarrity, Ivan
	 * Department: English
	 * Term: 202208
	 */
	@Override
	public String toString() {
		String result = "";
		result = (super.toString() + "\nTerm: " + getTerm());
		return result;
	}
}
