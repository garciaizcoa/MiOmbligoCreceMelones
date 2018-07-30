import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

public class CustomerTicketMenu extends JPanel {

	public CustomerTicketMenu(Frame frame) {
		setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnBack = new JButton("Back");
		add(btnBack);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				frame.setContentPane(frame.getTableDiagramMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		
		JButton btnPay = new JButton("Pay");
		add(btnPay);

	}
}
