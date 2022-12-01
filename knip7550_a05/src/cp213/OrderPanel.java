package cp213;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The GUI for the Order class.
 *
 * @author Kelli Knipe
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2022-11-20
 */
@SuppressWarnings("serial")
public class OrderPanel extends JPanel {

	// Attributes
	private Menu menu = null; // Menu attached to panel.
	private final Order order = new Order(); // Order to be printed by panel.
	// GUI Widgets
	private final JButton printButton = new JButton("Print");
	private final JLabel subtotalLabel = new JLabel("0");
	private final JLabel taxLabel = new JLabel("0");
	private final JLabel totalLabel = new JLabel("0");
	private final JLabel itemColumnLabel = new JLabel("Item", 0);
	private final JLabel priceColumnLabel = new JLabel("Price", 0);
	private final JLabel quantityColumnLabel = new JLabel("Quantity", 0);

	private JLabel nameLabels[] = null;
	private JLabel priceLabels[] = null;
	// TextFields for menu item quantities.
	private JTextField quantityFields[] = null;
	
	// GridLayout object
	private GridLayout gridLayout = null;

	/**
	 * Displays the menu in a GUI.
	 *
	 * @param menu The menu to display.
	 */
	public OrderPanel(final Menu menu) {
		this.menu = menu;
		this.nameLabels = new JLabel[this.menu.size()];
		this.priceLabels = new JLabel[this.menu.size()];
		this.quantityFields = new JTextField[this.menu.size()];
		this.layoutView();
		this.registerListeners();
		this.gridLayout = new GridLayout((5 + this.menu.size()), 3);
	}

	/**
	 * Implements an ActionListener for the 'Print' button. Prints the current
	 * contents of the Order to a system printer or PDF.
	 */
	private class PrintListener implements ActionListener {

		@Override
		public void actionPerformed(final ActionEvent e) {

			// your code here

			
		}
	}

	/**
	 * Implements a FocusListener on a quantityField. Requires appropriate
	 * FocusListener methods.
	 */
	private class QuantityListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub

		}
		// your code here
	}

	/**
	 * Layout the panel.
	 */
	private void layoutView() {
		for (int i = 0; i < this.menu.size(); i++) {
			MenuItem thisItem = menu.getItem(i);
			nameLabels[i].setText(thisItem.getName());
			nameLabels[i].setHorizontalTextPosition(0);
			priceLabels[i].setText(thisItem.getPrice().toString());
			priceLabels[i].setHorizontalTextPosition(2);
		}
		this.gridLayout.addLayoutComponent("itemColumnLabelTop", itemColumnLabel);
		this.gridLayout.addLayoutComponent("priceColumnLabelTop", priceColumnLabel);
		this.gridLayout.addLayoutComponent("quantityColumnLabelTop", quantityColumnLabel);
		for (int i = 0; i < this.menu.size(); i++) {
			this.gridLayout.addLayoutComponent(("nameLabel" + Integer.toString(i)), this.nameLabels[i]);
			this.gridLayout.addLayoutComponent(("priceLabel" + Integer.toString(i)), this.priceLabels[i]);
			this.gridLayout.addLayoutComponent(("quantityField" + Integer.toString(i)), this.quantityFields[i]);
		}
		
	}

	/**
	 * Register the widget listeners.
	 */
	private void registerListeners() {
		// Register the PrinterListener with the print button.
		this.printButton.addActionListener(new PrintListener());

		// your code here
	}

}