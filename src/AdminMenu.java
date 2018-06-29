import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AdminMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	
	public AdminMenu(Inventory inv, Frame frame) {
		
		setLayout(new GridLayout(1, 0, 0, 0));
		
	
				
				setBorder(new EmptyBorder(5, 5, 5, 5));
				setLayout(new BorderLayout(0, 0));



				JButton btnPlates = new JButton("Plates");
				add(btnPlates, BorderLayout.WEST);

				JButton btnInventory = new JButton("Inventory");
				add(btnInventory, BorderLayout.EAST);

				JButton btnMainMenu = new JButton("Main Menu");
				add(btnMainMenu, BorderLayout.NORTH);

				btnInventory.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println("Inventory was clicked");
						frame.setContentPane(frame.getInventoryMenu()); //panel = panel you want to change too.
						frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
						frame.revalidate(); 
					}
				});
				
				btnPlates.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println("Plates was clicked");
						frame.setContentPane(frame.getPlatesMenu()); //panel = panel you want to change too.
						frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
						frame.revalidate(); 
					}
				});

				btnMainMenu.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println("mainMenu was clicked");
						frame.setContentPane(frame.getMainMenu()); //panel = panel you want to change too.
						frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
						frame.revalidate(); 
					}
				});

		
	}

}
