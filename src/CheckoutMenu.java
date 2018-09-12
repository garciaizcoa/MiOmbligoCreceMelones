import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

public class CheckoutMenu extends JPanel {
	
	private JPanel panel;
	private Frame frame;
	
	private JButton btnBack;
	private JScrollPane scrollPane_1;
	private JButton btnOrder;
	private BufferedImage backgroundImg;
	
	
	public CheckoutMenu(Frame frame) {
		//setLayout(null);
		this.frame=frame;
		this.setBackground(Color.BLACK);
		
		btnBack = new JButton("Back");
		btnBack.setBackground(Color.WHITE); //COLOR BTN
		btnBack.setContentAreaFilled(false);
		btnBack.setOpaque(true);
		
         
		scrollPane_1 = new JScrollPane();
		btnOrder = new JButton("Order!");
		btnOrder.setBackground(Color.WHITE); //COLOR BTN
		btnOrder.setContentAreaFilled(false);
		btnOrder.setOpaque(true);
	
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		scrollPane_1.setViewportView(panel);
		
		try {
			backgroundImg = ImageIO.read(new File("Images/Background.png"));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		for(Plate plato :frame.getCustomerMenu().getPlatesList()) {
			 OrderItem item = new OrderItem(plato, frame); 
			 panel.add(item);
		}
		
		btnBack.setFont(frame.getFont().deriveFont(40f));
		btnOrder.setFont(frame.getFont().deriveFont(40f));
		
		//Action Listeners
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(frame.getCustomerMenu()); //panel = panel you want to change too.
				frame.getCustomerMenu().refresh();
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
				btnOrder.setEnabled(true);
			}
		});
		
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Place order pressed");
				for(Plate plato : frame.getCustomerMenu().getPlatesList()) {
					System.out.println(plato.getName());
					System.out.println(frame.getMenu().isOnMenu(frame.getMenu().getPlate(plato.getName())));
				}
				System.out.println("printing menu");
				frame.getMenu().printMenu();
				
				panel.removeAll();
				TableDiagramMenu.Table tab = frame.getTableDiagramMenu().getTableByNumber(frame.getCustomerMenu().getTableNumber());
				tab.setOrderOfTable(frame.getCustomerMenu().getPlatesList());
				JLabel thanku = new JLabel("Your order has been placed!");
				thanku.setAlignmentX(CENTER_ALIGNMENT);
				Ticket tk = new Ticket(frame.getMenu(), frame.getInventory());
				tk.orderQueue(frame.getCustomerMenu().getPlatesList());
				for(Plate plt: frame.getCustomerMenu().getPlatesList()) {
					tk.placeOrder(tab.getTableNumber(), frame.getMenu().getPlate(plt.getName()));
				}
				frame.getCustomerMenu().refresh();
				frame.getInventoryMenu().refresh(frame.getInventory());
				panel.add(thanku);
				panel.repaint();
				panel.revalidate();
				btnOrder.setEnabled(false);
			}
		});
		
		setLayout();
		
		add(btnBack);
		add(scrollPane_1);
		add(btnOrder);
		
		
	}
	
	public void refresh() {
		panel.removeAll();
		for(Plate plato :frame.getCustomerMenu().getPlatesList()) {
			 OrderItem item = new OrderItem(plato, frame); 
			 panel.add(item);
			 panel.repaint();
				panel.revalidate();
		}
		
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
	                    .addComponent(btnOrder,javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        )
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
	                    .addComponent(btnBack,javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
	                    .addComponent(btnOrder,javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
	                .addGap(18, 18, 18)
	                .addComponent(scrollPane_1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
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
