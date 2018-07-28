//import java.awt.BorderLayout;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import javax.swing.SwingConstants;
//import javax.swing.JLabel;
//import java.awt.Color;
//import java.awt.Container;
//
//import javax.swing.JTable;
//import java.awt.FlowLayout;
//import javax.swing.ImageIcon;
//import javax.swing.BoxLayout;
//import javax.swing.JSplitPane;
//import javax.swing.GroupLayout;
//import javax.swing.GroupLayout.Alignment;
//import java.awt.SystemColor;
//import javax.swing.LayoutStyle.ComponentPlacement;
//import java.awt.Font;
//
//public class CustomerMenu extends JPanel {
//
//	/**
//	 * Create the panel.
//	 */
//	private static final long serialVersionUID = 1L;
//	
//	private JPanel panel;
//	
//	public CustomerMenu(Frame frame) {
//
//		/**
//		 * 
//		 */
//
//		/**
//		 * Create the panel.
//		 */
//
//		System.out.println("Customer "+frame.getInventory());
//				setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//				
//				JPanel orderNumber = new JPanel();
//				add(orderNumber);
//				
//				
//						JButton backBTN = new JButton("");
//						backBTN.setHorizontalAlignment(SwingConstants.LEFT);
//						backBTN.setForeground(SystemColor.info);
//						backBTN.setBackground(SystemColor.text);
//						backBTN.setIcon(new ImageIcon("C:\\Users\\chris\\Desktop\\backEVER.png"));
//						
//						
//						
//								backBTN.addActionListener(new ActionListener() {
//									public void actionPerformed(ActionEvent e) {
//										System.out.println("mainMenu was clicked");
//										frame.setContentPane(frame.getMainMenu()); //panel = panel you want to change too.
//										frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
//										frame.revalidate(); 
//									}
//								});
//		
//				JLabel orderlbl = new JLabel("Order #");
//				orderlbl.setVerticalAlignment(SwingConstants.TOP);
//				orderlbl.setHorizontalAlignment(SwingConstants.CENTER);
//				orderlbl.setFont(new Font("Roboto Black", Font.PLAIN, 33));
//				
//						orderlbl.setBackground(Color.WHITE);
//						GroupLayout gl_panel_1 = new GroupLayout(orderNumber);
//						gl_panel_1.setHorizontalGroup(
//							gl_panel_1.createParallelGroup(Alignment.LEADING)
//								.addGroup(gl_panel_1.createSequentialGroup()
//									.addContainerGap()
//									.addComponent(backBTN)
//									.addGap(47)
//									.addComponent(orderlbl)
//									.addGap(87))
//						);
//						gl_panel_1.setVerticalGroup(
//							gl_panel_1.createParallelGroup(Alignment.LEADING)
//								.addGroup(gl_panel_1.createSequentialGroup()
//									.addGap(5)
//									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
//										.addComponent(orderlbl)
//										.addComponent(backBTN))
//									.addContainerGap())
//						);
//						orderNumber.setLayout(gl_panel_1);
//						
//						panel = new JPanel();
//						add(panel);
//						
//						
//						for (Plate plato : frame.getMenu().getAvailablePlates()){
//
//							ProductPanel product = new ProductPanel(frame,plato);
//							frame.getCustomerMenu().getPanel().add(product);
//
//						}
//						
//						System.out.println("costumer menu "+frame.getCustomerMenu());
//						
////						for(int i=0;i<10;i++) {
////							ProductPanel prod = new ProductPanel(frame,new Plate("sopa", 2.33, frame.getInventory()));
////							frame.getCustomerMenu().getPanel().add(prod);
////						}
//
//	}
//	
//	//getters
//	
//	public JPanel getPanel() {
//		return panel;
//	}
//}

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

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

	/**
	 * Create the panel.
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private Frame frame;

	public CustomerMenu(Frame frame) {
		
		this.frame = frame;

		/**
		 * 
		 */

		/**
		 * Create the panel.
		 */

		System.out.println("Customer "+frame.getInventory());
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel orderNumber = new JPanel();
		add(orderNumber);


		JButton backBTN = new JButton("");

		orderNumber.add(backBTN);


		backBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("mainMenu was clicked");
				frame.setContentPane(frame.getMainMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});

		JLabel orderlbl = new JLabel("Order #");

		orderNumber.add(orderlbl);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane);

		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));



System.out.println("customerMenu "+frame.getMenu());
		
		for (Plate plato : frame.getMenu().getAvailablePlates()){

			System.out.println("Customer Menu!!! "+plato.getName());
			ProductPanel product = new ProductPanel(frame,plato);
			this.getPanel().add(product);
			panel.repaint();
			panel.revalidate();
		}
	}
	
	public void refresh(Menu menu) {
		panel.removeAll();
		for (Plate plato : menu.getAvailablePlates()){


			ProductPanel product = new ProductPanel(frame,plato);
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

