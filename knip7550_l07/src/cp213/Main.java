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