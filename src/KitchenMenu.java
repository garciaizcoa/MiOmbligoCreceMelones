import javax.swing.JPanel;

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
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;

public class KitchenMenu extends JPanel {

	private JPanel orderPanel;
	private TableDiagramMenu.Table table;

	private JButton btnBack;
	private JButton btnOrderUp;
	private JScrollPane scrollPane;
	private BufferedImage backgroundImg;

	public KitchenMenu(Frame frame) {

		this.table=null;

		//setLayout(new GridLayout(0, 3, 0, 0));
		this.setBackground(Color.WHITE);
		
		btnBack = new JButton("Back");
		btnBack.setBackground(Color.WHITE); //COLOR BTN
		btnBack.setContentAreaFilled(false);
		btnBack.setOpaque(true);
		
		btnOrderUp = new JButton("Order Up!");
		btnOrderUp.setBackground(Color.WHITE); //COLOR BTN
		btnOrderUp.setContentAreaFilled(false);
		btnOrderUp.setOpaque(true);
		
		scrollPane = new JScrollPane();
		orderPanel = new JPanel();
		
		orderPanel.setBackground(Color.WHITE);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		scrollPane.setViewportView(orderPanel);
		orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
		
		try {
			backgroundImg = ImageIO.read(new File("Images/Background.png"));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		btnBack.setFont(frame.getFont().deriveFont(25f));
		btnOrderUp.setFont(frame.getFont().deriveFont(25f));

		setLayout();

		add(btnOrderUp);
		add(btnBack);
		add(scrollPane);


		//Action Listeners
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.setContentPane(frame.getTableMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 

			}
		});


		btnOrderUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//frame.setContentPane(frame.getKitchenMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 

			}
		});

	}

	public void setOrderPanel() {
		orderPanel.removeAll();
		JLabel orderDetails = new JLabel("Order of Table #"+table.getTableNumber());
		orderPanel.add(orderDetails);

		JList lista = new JList(table.kitchenOrderToString().toArray());
		lista.setAlignmentX(CENTER_ALIGNMENT); //tratando de poner la lista en el centro... :(
		lista.setAlignmentY(CENTER_ALIGNMENT);
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
										.addGap(55, 55, 55)
										.addComponent(btnBack,javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
										.addGap(20, 20, 20)
										.addComponent(btnOrderUp,javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
										)
								.addGroup(layout.createSequentialGroup()
										.addGap(46, 46, 46)
										.addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(300, 300))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addContainerGap(160, 160)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnBack,javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
								.addComponent(btnOrderUp,javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
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