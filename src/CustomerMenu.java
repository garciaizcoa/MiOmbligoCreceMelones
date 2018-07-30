import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;
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
import java.awt.GridBagLayout;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;

public class CustomerMenu extends JPanel {

	private JPanel panel;
	private Frame frame;
	private int tableNumber = 1;

	private ArrayList<Plate> platesList = new ArrayList<>();

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

		JComboBox<Integer> tableComboBox = new JComboBox<>();

		int numTables = 0;

		try {
			numTables = Save.readTableNumber();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(numTables>0) {
			for(int i=1;i<=numTables;i++) {
				tableComboBox.addItem(i);
			}
		}
		tableComboBox.setVisible(false);

		JButton btnCheckout = new JButton("Checkout");
		add(btnCheckout);

		JButton btnTable = new JButton("Table #"+ tableComboBox.getSelectedItem());
		btnTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnTable.getText().contains("Table #")) {

					btnCheckout.setEnabled(false);
					tableComboBox.setVisible(true);
					btnTable.setText("Done");
				}else {
					btnTable.setText("Table #"+ tableComboBox.getSelectedItem());
					tableNumber= (int) tableComboBox.getSelectedItem();
					btnCheckout.setEnabled(true);
					tableComboBox.setVisible(false);
				}

			}
		});


		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		panel = new JPanel();
		scrollPane_1.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		GroupLayout gl_orderPanel = new GroupLayout(orderPanel);
		gl_orderPanel.setHorizontalGroup(
				gl_orderPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_orderPanel.createSequentialGroup()
						.addGroup(gl_orderPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_orderPanel.createSequentialGroup()
										.addGroup(gl_orderPanel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_orderPanel.createSequentialGroup()
														.addGap(32)
														.addComponent(btnTable))
												.addGroup(gl_orderPanel.createSequentialGroup()
														.addContainerGap()
														.addComponent(backBTN, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(tableComboBox, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_orderPanel.createSequentialGroup()
										.addContainerGap()
										.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		gl_orderPanel.setVerticalGroup(
				gl_orderPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_orderPanel.createSequentialGroup()
						.addGap(5)
						.addGroup(gl_orderPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_orderPanel.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(backBTN, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
										.addGap(11)
										.addComponent(btnTable))
								.addGroup(gl_orderPanel.createSequentialGroup()
										.addGap(16)
										.addComponent(tableComboBox, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
						.addContainerGap())
				);
		orderPanel.setLayout(gl_orderPanel);



		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("checkout was clicked");

				platesList.removeAll(platesList);
				for (Component opt : panel.getComponents()) {
					if(((ProductPanel) opt).getPlateNumber()!=0) {
						for(int i=0; i<((ProductPanel) opt).getPlateNumber();i++) {

							platesList.add((((ProductPanel) opt).getSelectedPlate()).clone());
							System.out.println("comida " +platesList.get(i).getName());
						}
					}

				}


				frame.setContentPane(frame.getCheckoutMenu()); //panel = panel you want to change too.
				frame.getCheckoutMenu().refresh();
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});

		refresh();


	}	

	public void refresh() {
		panel.removeAll();
		for(Plate plato: frame.getMenu().getAvailablePlates()) {

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

	public ArrayList<Plate> getPlatesList(){
		return platesList;
	}

	public int getTableNumber(){
		return tableNumber;
	}
}