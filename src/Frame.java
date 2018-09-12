import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Image;
import java.awt.Toolkit;

public class Frame extends JFrame  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private PasswordMenu passwordMenu;
	private SettingMenu settingMenu;
	private PasswordChanger passwordChanger;
	
	private Dimension screenSize;
	private Font JuiceboxFont;


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

		setAllFonts();
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
		this.passwordMenu = new PasswordMenu(this);
//		this.settingMenu = new SettingMenu(this);
		this.passwordChanger = new PasswordChanger(this);


		System.out.println("frame ");
		this.getInventory().printInventory();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 480);

		//Full Screen just in case
		//setBounds(0, 0 ,(int)screenSize.getWidth(), (int)screenSize.getHeight());

		mainMenu = new JPanel();
		mainMenu.setLayout(new BorderLayout(250, 0));
		

		
		

//		mainMenu.setBackground(new java.awt.Color(0, 0, 50));
		mainMenu.setBackground(Color.BLACK);
		ImageIcon icon = new ImageIcon("/mt.png");
		mainMenu.paintComponents(drawImage(icon.getImage(),250,250,getWidth(),getHeight(), this));

		//		mainMenu.setBorder(new EmptyBorder(5, 5, 5, 5));

		JButton adminBTN = new JButton();
		JButton clientBTN = new JButton();
		JLabel jLabel2 = new JLabel();

		
		
//		this.getContentPane().add(jLabel2);

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
//				setContentPane(adminMenu); //panel = panel you want to change too.
				setContentPane(passwordMenu);
				repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				revalidate(); 
			}
		});

		jLabel2.setSize(250,250);
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("Images/mt.png"));
			//			img.getScaledInstance(250, 250, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}


		Image dimg = img.getScaledInstance(this.getScreenSize().width/3, this.getScreenSize().height/2,
				Image.SCALE_SMOOTH);

//			Image backgroundIMG = new ImageIcon(this.getClass().getResource("/mt.png")).getImage();

		ImageIcon imageIcon = new ImageIcon(dimg);



		jLabel2.setIcon(imageIcon); // NOI18N
		mainMenu.add(jLabel2);
		
		//		jLabel2.setBounds(100, 0, 350, 350);

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
		setContentPane(mainMenu);
	}

	private Graphics drawImage(Image image, int i, int j, int width, int height, Frame frame) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setAllFonts(){
		try {
			//create the font to use. Specify the size!
			JuiceboxFont = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/Juicebox.otf")).deriveFont(12f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			//register the font
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/Juicebox.otf")));
		} catch (IOException e) {
			e.printStackTrace();
		} catch(FontFormatException e) {
			e.printStackTrace();
		}
	}


	public Font getFont(){
		return JuiceboxFont;
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
	public PasswordMenu getPasswordMenu() {
		return passwordMenu;
	}
	public PasswordChanger getPasswordChanger() {
		return passwordChanger;
	}
	public SettingMenu getSettingMenu() {
		return settingMenu;
	}
	


}