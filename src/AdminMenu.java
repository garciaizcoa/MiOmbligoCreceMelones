import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.ImageIcon;
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

	public AdminMenu(Frame frame) {

		setLayout(new GridLayout(1, 4, 1, 5));

		setBorder(new EmptyBorder(200, 50, 200, 50));
		setBackground(Color.WHITE);


		JButton btnMainMenu = new JButton("Main Menu");
		add(btnMainMenu, BorderLayout.NORTH);
//		btnMainMenu.setFont(frame.getFont().deriveFont(50f));
//		btnMainMenu.setForeground(Color.ORANGE);
		Image imgMain = new ImageIcon(this.getClass().getResource("/main.jpg")).getImage();
		btnMainMenu.setIcon(new ImageIcon(imgMain)); 

		JButton btnTableDiagram = new JButton("Table Diagram");
		add(btnTableDiagram);
//		btnTableDiagram.setFont(frame.getFont().deriveFont(50f));
//		btnTableDiagram.setForeground(Color.ORANGE);
		Image imgTable = new ImageIcon(this.getClass().getResource("/table.jpg")).getImage();
		btnTableDiagram.setIcon(new ImageIcon(imgTable)); 

		JButton btnPlates = new JButton("Plates");
		add(btnPlates, BorderLayout.WEST);
//		btnPlates.setFont(frame.getFont().deriveFont(50f));
//		btnPlates.setForeground(Color.ORANGE);
		Image imgPlate = new ImageIcon(this.getClass().getResource("/plate.jpg")).getImage();
		btnPlates.setIcon(new ImageIcon(imgPlate));

		JButton btnInventory = new JButton("Inventory");
		add(btnInventory, BorderLayout.EAST);
//		btnInventory.setFont(frame.getFont().deriveFont(50f));
//		btnInventory.setForeground(Color.ORANGE);
		Image imgTask = new ImageIcon(this.getClass().getResource("/tasklist.jpg")).getImage();
		btnInventory.setIcon(new ImageIcon(imgTask)); 

		btnInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Inventory was clicked");
				frame.setContentPane(frame.getInventoryMenu()); //panel = panel you want to change too.
				frame.getInventoryMenu().getScrollPane().setViewportView(frame.getInventoryMenu().getPanel());
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

		btnTableDiagram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(frame.getTableDiagramMenu()); //panel = panel you want to change too.
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
