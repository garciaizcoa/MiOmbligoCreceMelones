import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;


public class TableMenu extends JPanel {
	
	private TableDiagramMenu.Table table;
	private JPanel orderPanel;

	public TableMenu(Frame frame) {
		
		this.table = null;
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{75, 134, 148, 0};
		gbl_panel.rowHeights = new int[]{29, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnBack = new JButton("Back");
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnBack.insets = new Insets(0, 0, 5, 5);
		gbc_btnBack.gridx = 0;
		gbc_btnBack.gridy = 0;
		panel.add(btnBack, gbc_btnBack);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(frame.getTableDiagramMenu());
				frame.repaint();
				frame.revalidate();
				
			}
		});
		
		JButton btnKitcken = new JButton("Kitchen Ticket");
		GridBagConstraints gbc_btnKitcken = new GridBagConstraints();
		gbc_btnKitcken.anchor = GridBagConstraints.NORTH;
		gbc_btnKitcken.insets = new Insets(0, 0, 5, 5);
		gbc_btnKitcken.gridx = 1;
		gbc_btnKitcken.gridy = 0;
		panel.add(btnKitcken, gbc_btnKitcken);
		
		btnKitcken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.setContentPane(frame.getKitchenMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
				
			}
		});
		
		JButton btnCustomer = new JButton("Customer Ticket");
		GridBagConstraints gbc_btnCustomer = new GridBagConstraints();
		gbc_btnCustomer.insets = new Insets(0, 0, 5, 0);
		gbc_btnCustomer.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnCustomer.gridx = 2;
		gbc_btnCustomer.gridy = 0;
		panel.add(btnCustomer, gbc_btnCustomer);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		panel.add(scrollPane, gbc_scrollPane);
		
		orderPanel = new JPanel();
		orderPanel.setLayout(new GridLayout(0, 3, 0, 0));
		scrollPane.setViewportView(orderPanel);
		
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.setContentPane(frame.getCustomerTicketMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
				
				
			}
		});
		
	}

	//setters
	
	public void setTable(TableDiagramMenu.Table table) {
		this.table=table;
		setOrderPanel();
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
	
}
