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

	Inventory inv;
	private Menu men;

	private JPanel mainMenu;
	private JPanel adminMenu;
	private JPanel inventoryMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
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
	public Frame() {

		Inventory inv = new Inventory();
		Menu men = new Menu();
		
		
		
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

		//comienza adminMenu
		adminMenu = new JPanel();
		adminMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		adminMenu.setLayout(new BorderLayout(0, 0));

		

		JButton btnMenu = new JButton("Menu");
		adminMenu.add(btnMenu, BorderLayout.WEST);

		JButton btnInventory = new JButton("Inventory");
		adminMenu.add(btnInventory, BorderLayout.EAST);
		
		JButton btnMainMenu = new JButton("Main Menu");
		adminMenu.add(btnMainMenu, BorderLayout.NORTH);
		
		btnInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("I was clicked");
				setContentPane(inventoryMenu); //panel = panel you want to change too.
				repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				revalidate(); 
			}
		});
		
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("mainMenu was clicked");
				setContentPane(mainMenu); //panel = panel you want to change too.
				repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				revalidate(); 
			}
		});

		
		// Comienza inventoryMenu
		 inventoryMenu = new JPanel();
		inventoryMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		inventoryMenu.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnAdminMenu = new JButton("Admin Menu");
		inventoryMenu.add(btnAdminMenu);

		btnAdminMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setContentPane(adminMenu); //panel = panel you want to change too.
				repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				revalidate(); 

			}
		});

		JPanel editPanel =new JPanel();
		editPanel.setLayout(new GridLayout(0, 3, 0, 0));
		inventoryMenu.add(editPanel);

		JButton btnAdd = new JButton("Add");
		editPanel.add(btnAdd);

		JTextField inventoryString = new JTextField();
		editPanel.add(inventoryString);
		
		JTextField inventoryInteger = new JTextField();
		editPanel.add(inventoryInteger);
		
		DefaultListModel<String> model = new DefaultListModel<>();
		
		JScrollPane scrollPane = new JScrollPane();
		inventoryMenu.add(scrollPane);
		JList<String> invList = new JList<>(model);
		scrollPane.setViewportView(invList);
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Add was clicked");
				inv.addItemToInventory(inventoryString.getText(), Integer.parseInt( inventoryInteger.getText()));
				inv.printInventory();
				model.addElement(inventoryString.getText()+" "+ inventoryInteger.getText());
			
			}
		});

		setContentPane(mainMenu); 
	}

	public Inventory getInventory() {
		return inv;
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
//hola
}
