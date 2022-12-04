package cp213;

import java.awt.GridLayout;
import java.awt.PrintJob;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.print.PrinterJob;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.DialogTypeSelection;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The GUI for the Order class.
 *
 * @author your name here
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2022-11-20
 */
@SuppressWarnings("serial")
public class OrderPanel extends JPanel {

	// Attributes
	private Menu menu = null; // Menu attached to panel.
    private final Order order = new Order(); // Order to be printed by panel.
    private GridLayout gridLayout = new GridLayout();
    // GUI Widgets
    private final JButton printButton = new JButton("Print");
    private final JLabel subtotalLabel = new JLabel("$" + order.getSubTotal().toString(), JLabel.RIGHT);
    private final JLabel taxLabel = new JLabel("$" + order.getTaxes(), JLabel.RIGHT);
    private final JLabel totalLabel = new JLabel("$" + order.getTotal(), JLabel.RIGHT);
    private final JLabel itemColumnLabel = new JLabel(" Item", JLabel.LEFT);
    private final JLabel priceColumnLabel = new JLabel(" Price", JLabel.LEFT);
    private final JLabel quantityColumnLabel = new JLabel(" Quantity", JLabel.LEFT);
    private final JLabel subtotalTextLabel = new JLabel(" Subtotal: ", JLabel.LEFT);
    private final JLabel taxTextLabel = new JLabel(" Tax: ", JLabel.LEFT);
    private final JLabel totalTextLabel = new JLabel(" Total: ", JLabel.LEFT);

    private JLabel nameLabels[] = null;
    private JLabel priceLabels[] = null;
    // TextFields for menu item quantities.
    private JTextField quantityFields[] = null;

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
		this.gridLayout = new GridLayout((5 + this.menu.size()), 3);
		this.registerListeners();
	}

	/**
	 * Implements an ActionListener for the 'Print' button. Prints the current
	 * contents of the Order to a system printer or PDF.
	 */
	private class PrintListener implements ActionListener {
		// note to self that this is not a method but a class
		
		@Override
        public void actionPerformed(final ActionEvent e) {
            final PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
            attributes.add(DialogTypeSelection.NATIVE);
            PrinterJob printJob = PrinterJob.getPrinterJob();
            printJob.printDialog(attributes);
    
        }
	}

	/**
	 * Implements a FocusListener on a quantityField. Requires appropriate
	 * FocusListener methods.
	 */
	private class QuantityListener implements FocusListener {
		// note to self that this is not a method but a class
		@SuppressWarnings("unused")
		private int i = 0;
		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			System.out.println(e.getSource().toString());
			
			
			/*System.out.println(e.getSource().toString());
			JTextField thisQuantity = (JTextField) e.getComponent();
			OrderPanel yeah = (OrderPanel) thisQuantity.getParent();
			for (int j = 0; j < yeah.quantityFields.length; j++) {
				if (thisQuantity.equals(yeah.quantityFields[j])) {
					this.i = j;
				}
			}*/
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			JTextField thisQuantity = (JTextField) e.getComponent();
			/*try {
				String userInput = thisQuantity.getText();
				int addedNumber = Integer.parseInt(userInput);
				OrderPanel yeah = (OrderPanel) thisQuantity.getParent();
				MenuItem fuck = yeah.menu.getItem(i);
				yeah.order.update(fuck, addedNumber);
				thisQuantity.setText("0");
				i = 0;
			} catch (NumberFormatException ee) {
				thisQuantity.setText("0");
				i = 0;
			}*/
		}
		// your code here
		
	}

	/**
	 * Layout the panel.
	 */
	private void layoutView() {
		setLayout(new GridLayout(0,3));
        add(itemColumnLabel);
        add(priceColumnLabel);
        add(quantityColumnLabel);
        for (int i = 0; i < this.menu.size(); i++) {
            MenuItem thisItem = menu.getItem(i);
            this.nameLabels[i] = new JLabel(" " + thisItem.getName(), JLabel.LEFT);
            add(this.nameLabels[i]);
            this.priceLabels[i] = new JLabel("$" + thisItem.getPrice().toString(), JLabel.RIGHT);
            add(this.priceLabels[i]);        
            this.quantityFields[i] = new JTextField();
            this.quantityFields[i].setHorizontalAlignment(4);
            add(this.quantityFields[i]);
        }
        add(subtotalTextLabel);
        add(new JLabel (""));
        add(this.subtotalLabel);
        add(taxTextLabel);
        add(new JLabel (""));
        add(this.taxLabel);
        add(totalTextLabel);
        add(new JLabel (""));
        add(this.totalLabel);
        add(new JLabel (""));
        add(printButton);
        add(new JLabel (""));
	}

	/**
	 * Register the widget listeners.
	 */
	private void registerListeners() {
		// Register the PrinterListener with the print button.
		// connects the PrintListener with the print button
		this.printButton.addActionListener(new PrintListener());
		// we need to add a focuslistener for each quantity textfield
		// connects the textfields with the QuantityListeners
		for (int i = 0; i < this.menu.size(); i++) {
			JTextField thisField = this.quantityFields[i];
			thisField.setName(menu.getItem(i).getName() + " value field");
			thisField.addFocusListener(new QuantityListener());
			System.out.println("Quantity Listener added to " + thisField.getName());
		}
		
		// your code here
	}

}