import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component;

public class CustomerTicketMenu extends JPanel {

	private JPanel unpaidPanel;
	private JPanel paidPanel;
	private JPanel amountsPanel;
	private Frame frame;
	private JLabel lblTotal;
	private JLabel lblExtras;
	private BufferedImage backgroundImg;
	
	private JLabel lblPendingPay;
	private JLabel lblBeingPayed;

	private TableDiagramMenu.Table table;
	private double totalAmount;
	private double taxAmount;
	private double extrasAmount;

	private JButton btnBack;
	private JButton btnPay;
	private JScrollPane scrollPane_unpaid;
	private JScrollPane scrollPane_paid;

	public CustomerTicketMenu(Frame frame) {

		this.frame= frame;
		this.table=null;
		this.totalAmount=0;
		this.taxAmount=0;
		this.setBackground(Color.BLACK);
		
		btnBack = new JButton("Back");
		btnPay = new JButton("Pay");
		scrollPane_unpaid = new JScrollPane();
		unpaidPanel = new JPanel();
		scrollPane_paid = new JScrollPane();
		paidPanel = new JPanel();
		amountsPanel = new JPanel();
		lblPendingPay = new JLabel("Pending");
		lblBeingPayed = new JLabel("Paying");
			
		lblPendingPay.setFont(frame.getFont().deriveFont(40f));
		lblBeingPayed.setFont(frame.getFont().deriveFont(40f));
		btnBack.setFont(frame.getFont().deriveFont(40f));
		btnPay.setFont(frame.getFont().deriveFont(40f));
		
		try {
			backgroundImg = ImageIO.read(new File("Images/Background.png"));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		amountsPanel.setBackground(Color.WHITE);
		unpaidPanel.setLayout(new BoxLayout(unpaidPanel, BoxLayout.Y_AXIS));
		scrollPane_unpaid.setViewportView(unpaidPanel);

		paidPanel.setLayout(new BoxLayout(paidPanel, BoxLayout.Y_AXIS));
		scrollPane_paid.setViewportView(paidPanel);

		amountsPanel.setLayout(new BoxLayout(amountsPanel, BoxLayout.Y_AXIS));
		JLabel lblTax = new JLabel("Tax: ");
		amountsPanel.add(lblTax);

		lblExtras = new JLabel("Extras: ");
		amountsPanel.add(lblExtras);

		lblTotal = new JLabel("Total:" + totalAmount);
		amountsPanel.add(lblTotal);

		setLayout();

		add(amountsPanel);
		add(btnPay);
		add(scrollPane_paid);
		add(btnBack);
		add(scrollPane_unpaid);

		//Action Listeners
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paidPanel.removeAll();
				refreshTotalAmount();

			}
		});

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				frame.setContentPane(frame.getTableMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 

			}
		});


	}

	public void setLayout(){
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(80,80,80)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(lblPendingPay, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(290, 290, 290)
                .addComponent(lblBeingPayed, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(410, 410, 410))
            .addGroup(layout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addComponent(scrollPane_unpaid, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(amountsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPane_paid, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnPay, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPendingPay, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBeingPayed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85,85,85)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,50,50)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPane_paid, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addComponent(scrollPane_unpaid))
                .addGap(18, 18, 18)
                .addComponent(amountsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnPay, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGap(77, 77, 77))
        );
	}

	//refreshers
	public void refresh() {
		unpaidPanel.removeAll();
		for(Plate plato : table.getOrderOfTable()) {
			ItemToPay item = new ItemToPay(frame, this, plato);
			unpaidPanel.add(item);
		}
	}

	public void refreshTotalAmount() { ////falta el tax
		setExtrasAmount(0);
		setTotalAmount(0);
		for (Component c : paidPanel.getComponents()) {
			totalAmount+= ((ItemToPay) c).getPlate().getPrice();

			for (Map.Entry<String, Integer> entry : frame.getMenu().getPlate(((ItemToPay) c).getPlate().getName()).getPlateIngredients().entrySet()){

				if(((ItemToPay) c).getPlate().hasExtra(frame.getMenu().getPlate(((ItemToPay) c).getPlate().getName()), entry.getKey())) {
					totalAmount+=.50;  /////////////////se debe dar la opcion de cambiar el fee de extras
					extrasAmount+=.50;

				}
			}

		}

		lblTotal.setText("Total: "+ totalAmount);
		lblExtras.setText("Extras: "+ extrasAmount);
		lblTotal.repaint();
		lblTotal.revalidate();
	}


	//setters

	public void setTable(TableDiagramMenu.Table table) {
		this.table=table;
		setTotalAmount(0);
		refresh();

	}

	public void setTotalAmount(int tot) {
		this.totalAmount=tot;
	}
	public void setExtrasAmount(int amount) {
		this.extrasAmount=amount;
	}

	public void setTaxAmount(int tax) {
		this.taxAmount=tax;
	}


	//getters
	public JPanel getUnpaidPanel() {
		return unpaidPanel;
	}
	public JPanel getPaidPanel() {
		return paidPanel;
	}
	public double getTotalAmount() {
		return totalAmount;
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