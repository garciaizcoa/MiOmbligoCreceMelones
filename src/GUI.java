import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class GUI extends JFrame implements ActionListener {

	private Inventory inv;
	private Menu men;

	//private JFrame frame;
	private JPanel mainMenu;
	private JPanel inventoryMenu;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {


		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {

		Inventory inv = new Inventory();
		Menu men = new Menu();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		mainMenu = new JPanel();
		mainMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainMenu.setLayout(new BorderLayout(0, 0));

		setContentPane(mainMenu);  //Starting Panel

		JButton btnMenu = new JButton("Menu");
		mainMenu.add(btnMenu, BorderLayout.WEST);

		JButton btnInventory = new JButton("Inventory");
		mainMenu.add(btnInventory, BorderLayout.EAST);

		btnInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("I was clicked");
				setContentPane(inventoryMenu); //panel = panel you want to change too.
				repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				revalidate(); 
			}
		});

		// comienza panel de inventario

		inventoryMenu = new JPanel();
		inventoryMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		inventoryMenu.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnMainMenu = new JButton("Main Menu");
		inventoryMenu.add(btnMainMenu);



		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setContentPane(mainMenu); //panel = panel you want to change too.
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
		JList<String> invList = new JList<>(model);
		inventoryMenu.add(invList);
		
		
		  
	
		
		

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Add was clicked");
				inv.addItemToInventory(inventoryString.getText(), Integer.parseInt( inventoryInteger.getText()));
				inv.printInventory();
				model.addElement(inventoryString.getText()+" "+ inventoryInteger.getText());
			
			}
		});

		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//		JList<String> invList = new JList<>();
		//
		//		DefaultListModel<String> listModel=new DefaultListModel<>();
		//
		//		Map<String,Integer> newMap= inv.getInventoryList();
		//		for (Map.Entry<String, Integer> entry : newMap.entrySet()){
		//			listModel.addElement(entry.getKey() );
		//		}
		//		invList=new JList<String>(listModel);
		//
		//		inventoryScroll.add(invList);
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
		
		


	}

	//	public void refreshInventory() {
	//		for (int i = 0; i < num; i++) {
	//		    JLabel label = new JLabel();
	//		    inventoryScroll.add(label);
	//		}
	//	}


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

}
