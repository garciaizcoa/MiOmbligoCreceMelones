import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
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
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;

public class CustomerMenu extends JPanel {

	private JPanel panel;
	private Frame frame;
	private int tableNumber = 1;

	private ArrayList<Plate> platesList = new ArrayList<>();
	
	private JButton backBTN;
	private JButton btnTable ;
	private JButton btnCheckout;
	private JComboBox<Integer> tableComboBox;
	private JScrollPane scrollPane_1;
	private JPanel orderPanel;
	private BufferedImage backgroundImg;

	/**
	 * Create the panel.
	 */
	private static final long serialVersionUID = 1L;
	public CustomerMenu(Frame frame) {

		this.frame=frame;


		System.out.println("Customer "+frame.getInventory());
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		orderPanel = new JPanel();
		tableComboBox = new JComboBox<>();
		backBTN = new JButton("Back");
		btnCheckout = new JButton("Checkout");
		btnTable = new JButton("Table #"+ 1);
		scrollPane_1 = new JScrollPane();
		panel = new JPanel();
		
		try {
			backgroundImg = ImageIO.read(new File("Images/Background.png"));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	   // this.paintComponent(drawImage(backgroundImg,250,250,getWidth(),getHeight(), this));

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

		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		scrollPane_1.setViewportView(panel);
		//orderPanelLayout();
		
		backBTN.setFont(frame.getFont().deriveFont(40f));
		btnCheckout.setFont(frame.getFont().deriveFont(40f));
		
		setLayout();
		add(backBTN);
		add(scrollPane_1);
		add(btnCheckout);
		add(btnTable);
		add(tableComboBox);


		//Action Listeners
		backBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("mainMenu was clicked");
				frame.setContentPane(frame.getMainMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});
		
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

		refresh();


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
	                        .addGap(55, 55, 55)
	                        .addComponent(backBTN,javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
	                        .addGap(128, 128, 128)
	                        .addComponent(btnTable)
	                        .addGap(18, 18, 18)
	                        .addComponent(tableComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(128, 128, 128)
	                        .addComponent(btnCheckout,javax.swing.GroupLayout.DEFAULT_SIZE, 120, 120))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(46, 46, 46)
	                        .addComponent(scrollPane_1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addContainerGap(300, 300))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                .addContainerGap(160, 160)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(backBTN,javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
	                    .addComponent(btnTable)
	                    .addComponent(tableComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(btnCheckout,javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
	                .addGap(18, 18, 18)
	                .addComponent(scrollPane_1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(46, 46, 46))
	        );
	}
	
	public void orderPanelLayout(){
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
	}

	public void refresh() {
		panel.removeAll();
		frame.getMenu().updateMenu();
		for(Plate plato: frame.getMenu().getAvailablePlates()) {

			ProductPanel product = new ProductPanel(frame, plato);
			panel.add(product);
			panel.repaint();
			panel.revalidate();
		}
		 
	}

	@Override 
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroundImg,0,0, getWidth(),getHeight(), this);
    }
	
	private Graphics drawImage(Image image, int i, int j, int width, int height, CustomerMenu cm) {
		// TODO Auto-generated method stub
		return null;
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