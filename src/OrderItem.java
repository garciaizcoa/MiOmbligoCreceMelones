import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class OrderItem extends JPanel {
	
	private Frame frame;
	
	private Plate plate;
	
	public OrderItem(Plate plate, Frame frame) {
		
		this.frame=frame;
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel = new JLabel(plate.getName());
//		lblNewLabel.setFont(new Font("Century Gothic", Font.LAYOUT_RIGHT_TO_LEFT, 30));
		lblNewLabel.setFont(frame.getFont().deriveFont(35f));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel);
		
		JButton btnCustomize = new JButton("Customize");
		btnCustomize.setFont(frame.getFont().deriveFont(25f));
		btnCustomize.setBackground(Color.WHITE);
		add(btnCustomize);
		
		btnCustomize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getCustomizeMenu().setPlate(plate);
				frame.getCustomizeMenu().refresh();
				frame.setContentPane(frame.getCustomizeMenu());
			 //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});
		
		JButton btnRemove = new JButton("Remove Plate");
		btnRemove.setBackground(Color.WHITE);
		btnRemove.setFont(frame.getFont().deriveFont(25f));
		add(btnRemove);
		
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getCustomerMenu().getPlatesList().remove(plate);
				frame.getCheckoutMenu().refresh();; //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});
	}

	
	
}