import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javax.swing.WindowConstants;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JScrollBar;

public class Frame extends JFrame  {

	private Inventory inv;
	private Menu men;

	private JPanel mainMenu;
	private AdminMenu adminMenu;
	private PlatesMenu platesMenu; 
	private AddPlateMenu addPlateMenu; 
	private InventoryMenu inventoryMenu; 
	private CustomerMenu customerMenu;
	private EditPlateMenu editPlateMenu;
	private CheckoutMenu checkoutMenu;
	private TableDiagramMenu tableDiagramMenu;
	private CustomizeMenu customizeMenu;
	private TableMenu tableMenu;
	private KitchenMenu kitchenMenu;
	private CustomerTicketMenu customerTicketMenu;
	
	private Dimension screenSize;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventory initialInventory = new Inventory();
					Save.readInitialInventory(initialInventory);
					Frame frame = new Frame(initialInventory, new Menu());
					
//					frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
//					frame.setUndecorated(true);
					
					 frame.setBounds(0,0,frame.getScreenSize().width, frame.getScreenSize().height);
				    
					
					frame.setResizable(false);
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
	 * @throws IOException 
	 */
	public Frame(Inventory inv, Menu men) throws IOException {

		this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.inv = inv;
		this.men = men;
		men.setFrame(this);
		this.adminMenu = new AdminMenu(this);
		this.addPlateMenu = new AddPlateMenu(this);
		Save.readInitialAddPlatesMenu(addPlateMenu, inv);
		//addPlateMenu.setInitialPlates();
		this.platesMenu = new PlatesMenu(this);
		this.inventoryMenu = new InventoryMenu(this);
		this.customerMenu = new CustomerMenu(this);
		this.editPlateMenu= new EditPlateMenu(this);
		this.checkoutMenu = new CheckoutMenu(this);
		this.tableDiagramMenu= new TableDiagramMenu(this);
		this.customizeMenu = new CustomizeMenu(this);
		this.tableMenu = new TableMenu(this);
		this.kitchenMenu = new KitchenMenu(this);
		this.customerTicketMenu = new CustomerTicketMenu(this);

		System.out.println("frame ");
		this.getInventory().printInventory();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 480);
		
		//Full Screen just in case
		//setBounds(0, 0 ,(int)screenSize.getWidth(), (int)screenSize.getHeight());

		mainMenu = new JPanel();
		mainMenu = new JPanel();
		mainMenu.setLayout(new BorderLayout(0, 0));

//		mainMenu.setBorder(new EmptyBorder(5, 5, 5, 5));

		JButton adminBTN = new JButton();
		JButton clientBTN = new JButton();
		JLabel jLabel2 = new JLabel();

		
		Image imgAdmin = new ImageIcon(this.getClass().getResource("/admin2.png")).getImage();
		adminBTN.setIcon(new ImageIcon(imgAdmin)); // ADMIN BUTTON
		mainMenu.add(adminBTN);
		
		//adminBTN.setBounds(360, 370, 146, 30);
		adminBTN.setBounds((int) (this.getScreenSize().width*.75), (int) (this.getScreenSize().height*.75), 146, 30);
		
		Image imgClient = new ImageIcon(this.getClass().getResource("/CLIENT.png")).getImage();
		clientBTN.setIcon(new ImageIcon(imgClient)); // CLIENT BUTTON
		mainMenu.add(clientBTN);
		
		//clientBTN.setBounds(40, 370, 146, 30);
		clientBTN.setBounds((int) (this.getScreenSize().width*.25)-146, (int) (this.getScreenSize().height*.75), 146, 30);


		clientBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Customer clicked");
				setContentPane(customerMenu); //panel = panel you want to change too.
				repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				revalidate(); 
			}
		});
		adminBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Admin clicked");
				setContentPane(adminMenu); //panel = panel you want to change too.
				repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				revalidate(); 
			}
		});
		
		jLabel2.setSize(screenSize.width,screenSize.height);

		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("Images/mt.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		
		Image dimg = img.getScaledInstance(jLabel2.getWidth(), jLabel2.getHeight(),
		        Image.SCALE_SMOOTH);
		
	//	Image backgroundIMG = new ImageIcon(this.getClass().getResource("/mt.png")).getImage();
		
		ImageIcon imageIcon = new ImageIcon(dimg);
		
		
		
		jLabel2.setIcon(imageIcon); // NOI18N
	
		
		mainMenu.add(jLabel2);
		jLabel2.setBounds(0, 0, 550, 430);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(mainMenu, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(mainMenu, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				);

		//starting pane
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
	
	public Dimension getScreenSize() {
		return screenSize;
	}

	// menu getters
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
	public CustomerMenu getCustomerMenu(){
		return customerMenu;
	}
	public EditPlateMenu getEditPlateMenu(){
		return editPlateMenu;
	}
	public CheckoutMenu getCheckoutMenu(){
		return checkoutMenu;
	}
	public TableDiagramMenu getTableDiagramMenu(){
		return tableDiagramMenu;
	}
	public CustomizeMenu getCustomizeMenu() {
		return customizeMenu;
	}
	public TableMenu getTableMenu() {
		return tableMenu;
	}
	public KitchenMenu getKitchenMenu() {
		return kitchenMenu;
	}
	public CustomerTicketMenu getCustomerTicketMenu() {
		return customerTicketMenu;
	}
	
	

	
	
}