package cp213;

import java.awt.GridLayout;
import java.awt.print.PrinterException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.print.PrinterJob;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
    private JLabel dummyLabel = new JLabel("", JLabel.LEFT);

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

        @Override
        public void actionPerformed(final ActionEvent e) {
            
            PrinterJob pj = PrinterJob.getPrinterJob();
            pj.setPrintable(order);
            String datePattern = "yyyy-MM-dd-HH-mm-ss";
            DateFormat df = new SimpleDateFormat(datePattern);
            datePattern = df.toString();
            try {
            	pj.setJobName("Receipt " + df);
                pj.printDialog();
            	pj.print();
            }
            catch (PrinterException ex) {
                ex.printStackTrace();
            }        
        }
    }

	/**
	 * Implements a FocusListener on a quantityField. Requires appropriate
	 * FocusListener methods.
	 */
	private class QuantityListener implements FocusListener {

        private int i = 0;
        
        @Override
        public void focusGained(FocusEvent e) {
            // TODO Auto-generated method stub
            JTextField thisQuantity = (JTextField) e.getComponent();            
            OrderPanel thisOrder = (OrderPanel) thisQuantity.getParent();
            for (int j = 0; j < thisOrder.quantityFields.length; j++) {
                if (thisQuantity.equals(thisOrder.quantityFields[j])) {
                    this.i = j;
                }
            }            
        }

        @Override
        public void focusLost(FocusEvent e) {
            // TODO Auto-generated method stub
        	JTextField thisQuantity = (JTextField) e.getComponent();
            try {
                String input = thisQuantity.getText();
                int addition = Integer.parseInt(input);
                OrderPanel thisOrder = (OrderPanel) thisQuantity.getParent();
                MenuItem thisItem = thisOrder.menu.getItem(i);
                
                if (thisOrder.order.map.get(thisItem) == null) {
                    thisOrder.order.add(thisItem, addition);
                } else {
                    thisOrder.order.update(thisItem, addition);
                }
                
                thisQuantity.setText(Integer.toString(addition));                
                subtotalLabel.setText("$" + thisOrder.order.getSubTotal().toString() + " ");
                taxLabel.setText("$" + thisOrder.order.getTaxes().toString() + " ");
                totalLabel.setText("$" + thisOrder.order.getTotal().toString() + " ");        
                i = 0;                
                
            } catch (NumberFormatException ee) {
                thisQuantity.setText("0");
                System.out.println("Non-Numeric input in text field " + i);
                i = 0;
            }
        }
              
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