import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLDocument.Iterator;
import javax.swing.JToolBar;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JScrollBar;

public class Frame extends JFrame implements ActionListener {

	private Inventory inv;
	private Menu men;

	private JPanel mainMenu;
	private AdminMenu adminMenu = new AdminMenu(this);
	private PlatesMenu platesMenu = new PlatesMenu(this);
	private AddPlateMenu addPlateMenu = new AddPlateMenu(this);
	private InventoryMenu inventoryMenu = new InventoryMenu(this);


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame(new Inventory(), new Menu() );
					frame.setVisible(true);	
				}
				catch (Exception e) {
					e.printStackTrace();

				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public Frame(Inventory inv, Menu men) {

		this.inv = inv;
		this.men = men;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		mainMenu = new JPanel();
		mainMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainMenu.setLayout(new BorderLayout(0, 0));

		JButton btnCustomer = new JButton("Customer");
		mainMenu.add(btnCustomer, BorderLayout.WEST);

		JButton btnAdmin = new JButton("Admin");
		mainMenu.add(btnAdmin, BorderLayout.EAST);




		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Admin clicked");
				setContentPane(adminMenu); //panel = panel you want to change too.
				repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				revalidate(); 
			}
		});

		//	setContentPane(mainMenu);  //Starting Panel






		setContentPane(mainMenu); /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	}

	public Inventory getInventory() {
		return inv;
	}

	public void setInventory(Inventory inv) {
		this.inv=inv;
	}

	public Menu getMenu() {
		return men;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//		System.out.println("I was clicked");
		//		setContentPane(inventoryMenu); //panel = panel you want to change too.
		//		repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
		//		revalidate(); 
		//	
	}

	public AdminMenu getAdminMenu(){
		return adminMenu;
	}
	public JPanel getMainMenu(){
		return mainMenu;
	}
	public PlatesMenu getPlatesMenu(){
		return platesMenu;
	}
	public InventoryMenu getInventoryMenu(){
		return inventoryMenu;
	}

	public AddPlateMenu getAddPlateMenu() {
		return addPlateMenu;
	}
}
