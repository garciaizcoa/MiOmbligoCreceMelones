import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Component;

public class CustomerTicketMenu extends JPanel {

	private JPanel unpaidPanel;
	private JPanel paidPanel;
	private Frame frame;
	private JLabel lblTotal;
	private JLabel lblExtras;

	private TableDiagramMenu.Table table;
	private double totalAmount;
	private double taxAmount;
	private double extrasAmount;

	public CustomerTicketMenu(Frame frame) {

		this.frame= frame;
		this.table=null;
		this.totalAmount=0;
		this.taxAmount=0;
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JButton btnBack = new JButton("Back");
		add(btnBack);

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				frame.setContentPane(frame.getTableMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 

			}
		});

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);

		unpaidPanel = new JPanel();
		unpaidPanel.setLayout(new BoxLayout(unpaidPanel, BoxLayout.Y_AXIS));
		scrollPane.setViewportView(unpaidPanel);

		JScrollPane scrollPane_1 = new JScrollPane();
		add(scrollPane_1);

		paidPanel = new JPanel();
		paidPanel.setLayout(new BoxLayout(paidPanel, BoxLayout.Y_AXIS));
		scrollPane_1.setViewportView(paidPanel);

		JPanel amountsPanel = new JPanel();
		add(amountsPanel);
		amountsPanel.setLayout(new BoxLayout(amountsPanel, BoxLayout.Y_AXIS));

		JLabel lblTax = new JLabel("Tax: ");
		amountsPanel.add(lblTax);

		 lblExtras = new JLabel("Extras: ");
		amountsPanel.add(lblExtras);

		lblTotal = new JLabel("Total:" + totalAmount);
		amountsPanel.add(lblTotal);



		JButton btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paidPanel.removeAll();
				refreshTotalAmount();
				
			}
		});
		add(btnPay);

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


}