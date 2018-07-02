import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;
import java.awt.FlowLayout;

public class InventoryItem extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private JLabel labelItem;
	private JLabel labelAmount;
	private JButton button;
	
	public InventoryItem(Frame frame, String item , String amount){
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		labelItem = new JLabel(item);
		add(labelItem);
		
		labelAmount = new JLabel(amount);
		add(labelAmount);
		
		button= new JButton("Remove");
		add(button);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Remove was clicked");
				frame.getInventory().removeFromInventory(item);
				frame.getInventoryMenu().refresh(frame.getInventory());
				frame.getAddPlateMenu().refresh(frame.getInventory());
				frame.getInventory().printInventory();
				frame.getInventoryMenu().getPanel().repaint();
				
				

				try {
					Save.saveToInventory(frame.getInventory());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
		//
	}
	
	
}