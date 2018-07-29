import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckoutMenu extends JPanel {
	public CheckoutMenu(Frame frame) {
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBack.setBounds(166, 5, 117, 29);
		add(btnBack);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 32, 438, 232);
		add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		JButton btnOrder = new JButton("Order!");
		btnOrder.setBounds(327, 265, 117, 29);
		add(btnOrder);
	}
}
