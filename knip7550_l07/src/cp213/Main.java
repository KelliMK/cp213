package cp213;

import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 * Testing simple example of GUI widgets and inner classes.
 *
 * @author Kelli Knipe
 * @version 2022-11-17
 */
public class Main {

	/**
	 * Test code.
	 *
	 * @param args Unused.
	 */
	public static void main(String[] args) {
		// Create the display panel.
		final InnerClassPanel view = new InnerClassPanel();
		// Create the program frame and add the panel to it.
		final JFrame f = new JFrame("Inner Class Examples");
		f.setContentPane(view);
		f.setSize(300, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}

/*
GUI elements
	private final JButton button = new JButton("Push Me");
	private final ArrayList<String> checkBoxesSelected = new ArrayList<>();
	private final JCheckBox ketchup = new JCheckBox("Ketchup");
	private final JLabel label = new JLabel();
	private final JCheckBox mustard = new JCheckBox("Mustard");
	private final JCheckBox onions = new JCheckBox("Onions");
	private final JSlider slider = new JSlider(JSlider.HORIZONTAL, START, END, INC);
	private final JSpinner spinner = new JSpinner(MONTH_MODEL);
	private final ButtonGroup starGroup = new ButtonGroup();
	private final JRadioButton starTrek = new JRadioButton("Star Trek");
	private final JRadioButton starWars = new JRadioButton("Star Wars");
	private final JTextField textField = new JTextField();
*/