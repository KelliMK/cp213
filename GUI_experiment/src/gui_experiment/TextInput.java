package gui_experiment;

import javax.swing.*;
import java.awt.*;

class TextInput {
	private final static String newline = "\n";

	public static void main(String args[]) {

		// Creating the Frame
		JFrame frame = new JFrame("Passages");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);

		// Creating the MenuBar and adding components
		JMenuBar mb = new JMenuBar();
		JMenu m1 = new JMenu("FILE");
		JMenu m2 = new JMenu("Help");
		mb.add(m1);
		mb.add(m2);
		JMenuItem m11 = new JMenuItem("Open");
		JMenuItem m22 = new JMenuItem("Save as");
		m1.add(m11);
		m1.add(m22);

		// Creating the panel at bottom and adding components
		JPanel panel = new JPanel(); // the panel is not visible in output
		JLabel label = new JLabel("Enter Text");
		JTextField tf = new JTextField(10); // accepts up to 10 characters
		JButton send = new JButton("Send");
		JButton reset = new JButton("Reset");
		panel.add(label); // Components Added using Flow Layout
		panel.add(tf);
		panel.add(send);
		panel.add(reset);

		// Text Area at the Center
		JTextArea ta = new JTextArea();
		ta.getText();
		ta.setEditable(false);
		ta.setLineWrap(true);
		ta.setWrapStyleWord(true);
		send.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				convertInputIntoTextField(evt, tf, ta);
			}
		});
		reset.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				clearAndReset(evt, tf, ta);
			}
		});
		JScrollPane areaPane = new JScrollPane(ta);

		// Adding Components to the frame.
		frame.getContentPane().add(BorderLayout.SOUTH, panel);
		frame.getContentPane().add(BorderLayout.NORTH, mb);
		frame.getContentPane().add(BorderLayout.CENTER, areaPane);
		frame.setVisible(true);
	}

	private static void convertInputIntoTextField(java.awt.event.ActionEvent evt, JTextField tf, JTextArea ta) {
		String newText = tf.getText();
		ta.append(newText + newline);
		tf.selectAll();
		ta.setCaretPosition(ta.getDocument().getLength());
		passageCheck(newText, ta);
	}

	private static void clearAndReset(java.awt.event.ActionEvent evt, JTextField tf, JTextArea ta) {
		ta.selectAll();
		ta.replaceSelection("");
		tf.selectAll();
		tf.replaceSelection("");
	}

	private static void passageCheck(String inputText, JTextArea ta) {
		switch (inputText) {
		case "rainbow":
		case "Rainbow":
		case "RAINBOW":
			ta.append(newline + "When the sunlight strikes raindrops in the air, they act as a prism and form a rainbow. The rainbow is a division of white light into many beautiful colors. These take the shape of a long round arch, with its path high above, and its two ends apparently beyond the horizon. There is , according to legend, a boiling pot of gold at one end. People look, but no one ever finds it. When a man looks for something beyond his reach, his friends say he is looking for the pot of gold at the end of the rainbow. Throughout the centuries people have explained the rainbow in various ways. Some have accepted it as a miracle without physical explanation. To the Hebrews it was a token that there would be no more universal floods. The Greeks used to imagine that it was a sign from the gods to foretell war or heavy rain. The Norsemen considered the rainbow as a bridge over which the gods passed from earth to their home in the sky. Others have tried to explain the phenomenon physically. Aristotle thought that the rainbow was caused by reflection of the sun's rays by the rain. Since then physicists have found that it is not reflection, but refraction by the raindrops which causes the rainbows. Many complicated ideas about the rainbow have been formed. The difference in the rainbow depends considerably upon the size of the drops, and the width of the colored band increases as the size of the drops increases. The actual primary rainbow observed is said to be the effect of super-imposition of a number of bows. If the red of the second bow falls upon the green of the first, the result is to give a bow with an abnormally wide yellow band, since red and green light when mixed form yellow. This is a very common type of bow, one showing mainly red andyellow, with little or no green or blue." + newline + newline);
			break;
		case "North":
		case "north":
		case "NORTH":
		case "Wind":
		case "wind":
		case "WIND":
		case "sun":
		case "Sun":
		case "SUN":
			ta.append(newline + "The North Wind and the Sun were disputing which was the stronger, when a traveller came along wrapped in a warm cloak. They agreed that the one who first succeeded in making the traveller take his cloak off should be considered stronger than the other. Then the North Wind blew as hard as he could, but the more he blew the more closely did the traveller fold his cloak around him, and at last the North Wind gave up the attempt. Then the Sun shone out warmly, and immediately the traveller took off his cloak. And so the North Wind was obliged to confess that the Sun was the stronger of the two." + newline + newline);
			break;
		case "Grandfather":
		case "grandfather":
		case "GRANDFATHER":
		case "Grandpa":
		case "grandpa":
		case "GRANDPA":
			ta.append(newline + "You wished to know all about my grandfather. Well, he is nearly ninety-three years old; he dresses himself in an ancient black frock coat, usually minus several buttons; yet he still thinks as swiftly as ever. A long, flowing beard clings to his chin, giving those who observe him a pronounced feeling of the utmost respect. When he speaks, his voice is just a bit cracked and quivers a trifle. Twice each day he plays skillfully and with zest upon our small organ. Except in the winter when the ooze or snow or ice prevents, he slowly takes a short walk in the open air each day. We have often urged him to walk more and smoke less, but he always answers, “Banana oil!” Grandfather likes to be modern in his language." + newline + newline);
			break;
		}
	}
}