import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;

public class CheckoutMenu extends JPanel {
	
	private JPanel panel;
	private Frame frame;
	
	public CheckoutMenu(Frame frame) {
		setLayout(null);
		this.frame=frame;
		
		JButton btnBack = new JButton("Back");
		
		JButton btnOrder = new JButton("Order!");
		btnOrder.setBounds(327, 265, 117, 29);
		add(btnOrder);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(frame.getCustomerMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
				btnOrder.setEnabled(true);
			}
		});
		btnBack.setBounds(166, 5, 117, 29);
		add(btnBack);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(6, 32, 438, 232);
		add(scrollPane_1);
		
		 panel = new JPanel();
		 panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		scrollPane_1.setViewportView(panel);
		
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				TableDiagramMenu.Table tab = frame.getTableDiagramMenu().getTableByNumber(frame.getCustomerMenu().getTableNumber());
				tab.setOrderOfTable(frame.getCustomerMenu().getPlatesList());
				JLabel thanku = new JLabel("Your order has been placed!");
				panel.add(thanku);
				panel.repaint();
				panel.revalidate();
				btnOrder.setEnabled(false);
			}
		});
		
		for(Plate plato :frame.getCustomerMenu().getPlatesList()) {
			 OrderItem item = new OrderItem(plato, frame); 
			 panel.add(item);
		}
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
}
