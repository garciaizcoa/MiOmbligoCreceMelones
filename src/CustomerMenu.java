import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.SystemColor;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

public class CustomerMenu extends JPanel {

	private JPanel panel;
	private Frame frame;

	/**
	 * Create the panel.
	 */
	private static final long serialVersionUID = 1L;
	public CustomerMenu(Frame frame) {

		this.frame=frame;

		/**
		 * 
		 */

		/**
		 * Create the panel.
		 */

		System.out.println("Customer "+frame.getInventory());
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel orderPanel = new JPanel();
		add(orderPanel);


		JButton backBTN = new JButton("");
		backBTN.setHorizontalAlignment(SwingConstants.LEFT);
		backBTN.setForeground(SystemColor.info);
		backBTN.setBackground(SystemColor.text);
		backBTN.setIcon(new ImageIcon("C:\\Users\\chris\\Desktop\\backEVER.png"));



		backBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("mainMenu was clicked");
				frame.setContentPane(frame.getMainMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});

		JLabel orderlbl = new JLabel("Order #");
		orderlbl.setVerticalAlignment(SwingConstants.TOP);
		orderlbl.setHorizontalAlignment(SwingConstants.CENTER);
		orderlbl.setFont(new Font("Roboto Black", Font.PLAIN, 33));

		orderlbl.setBackground(Color.WHITE);
		GroupLayout gl_orderPanel = new GroupLayout(orderPanel);
		gl_orderPanel.setHorizontalGroup(
				gl_orderPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_orderPanel.createSequentialGroup()
						.addContainerGap()
						.addComponent(backBTN)
						.addGap(47)
						.addComponent(orderlbl)
						.addGap(87))
				);
		gl_orderPanel.setVerticalGroup(
				gl_orderPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_orderPanel.createSequentialGroup()
						.addGap(5)
						.addGroup(gl_orderPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(orderlbl)
								.addComponent(backBTN))
						.addContainerGap())
				);
		orderPanel.setLayout(gl_orderPanel);

		 panel = new JPanel();
		add(panel);

		refresh();


	}	

	public void refresh() {
		panel.removeAll();
		for(Plate plato: frame.getAddPlateMenu().getAllPlates()) {
			
			ProductPanel product = new ProductPanel(frame, plato);
			this.getPanel().add(product);
			panel.repaint();
			panel.revalidate();
		}

	}


	//getters
	public JPanel getPanel() {

		return panel;
	}


}
