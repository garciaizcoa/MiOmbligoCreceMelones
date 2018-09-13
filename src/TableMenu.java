import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


public class TableMenu extends JPanel {
	
	private TableDiagramMenu.Table table;
	private JPanel orderPanel;
	
	private JButton btnBack;
	private JButton btnKitchen;
	private JButton btnCustomer;
	private JScrollPane scrollPane;
	private BufferedImage backgroundImg;
	
	public TableMenu(Frame frame) {
		
		this.table = null;
		this.setBackground(Color.BLACK);
		
		btnBack = new JButton("Back");
		btnKitchen = new JButton("Kitchen Ticket");
		btnCustomer = new JButton("Customer Ticket");
		scrollPane = new JScrollPane();
		
		btnBack.setBackground(Color.WHITE); //COLOR BTN
		btnBack.setContentAreaFilled(false);
		btnBack.setOpaque(true);
		
		btnKitchen.setBackground(Color.WHITE); //COLOR BTN
		btnKitchen.setContentAreaFilled(false);
		btnKitchen.setOpaque(true);
		
		btnCustomer.setBackground(Color.WHITE); //COLOR BTN
		btnCustomer.setContentAreaFilled(false);
		btnCustomer.setOpaque(true);
	
		
		try {
			backgroundImg = ImageIO.read(new File("Images/Background.png"));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		btnBack.setFont(frame.getFont().deriveFont(25f));
		btnKitchen.setFont(frame.getFont().deriveFont(25f));
		btnCustomer.setFont(frame.getFont().deriveFont(25f));
		
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		orderPanel = new JPanel();
		orderPanel.setFont(frame.getFont().deriveFont(25f));
		orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
		scrollPane.setViewportView(orderPanel);
		
		orderPanel.setBackground(Color.WHITE);
		
		setLayout();
		add(btnBack);
		add(btnCustomer);
		add(btnKitchen);
		add(scrollPane);
		
		
		//Action Listeners
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(frame.getTableDiagramMenu());
				frame.repaint();
				frame.revalidate();
				
			}
		});
		
		btnKitchen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.getKitchenMenu().setTable(table);
				
				frame.setContentPane(frame.getKitchenMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
				
			}
		});
		
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.getCustomerTicketMenu().setTable(table);
				
				frame.setContentPane(frame.getCustomerTicketMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
				
				
			}
		});
		
	}

	public void setOrderPanel() {
		orderPanel.removeAll();
		JLabel orderDetails = new JLabel("Order of Table #"+table.getTableNumber());
		orderPanel.add(orderDetails);
		
		JList lista = new JList(table.orderToStrings().toArray());
		orderPanel.add(lista);
		orderPanel.repaint();
		orderPanel.revalidate();

	}
	
	//setters
	
		public void setTable(TableDiagramMenu.Table table) {
			this.table=table;
			setOrderPanel();
		}
		
		public void setLayout(){

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	        this.setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
		            .addContainerGap(300, 300)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(50, 50, 50)
	                        .addComponent(btnBack,200, 200, 200)
	                        .addGap(50, 50,50)
	                        .addComponent(btnKitchen,200, 200, 200)
	                        .addGap(50, 50, 50)
	                        .addComponent(btnCustomer,200, 200, 200))
	                    .addGroup(layout.createSequentialGroup()
	                    	.addGap(55, 55, 55)
	                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addContainerGap(300, 300))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                .addContainerGap(160, 160)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(btnBack,javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
	                    .addComponent(btnKitchen,javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
	                    .addComponent(btnCustomer,javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
	                .addGap(18, 18, 18)
	                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(46, 46, 46))
	        );
	}
		
		
		@Override 
		public void paintComponent(Graphics g){
	        super.paintComponent(g);
	        g.drawImage(backgroundImg,0,0, getWidth(),getHeight(), this);
	    }
		
		private Graphics drawImage(Image image, int i, int j, int width, int height, CheckoutMenu cm) {
			// TODO Auto-generated method stub
			return null;
		}
		
	
}