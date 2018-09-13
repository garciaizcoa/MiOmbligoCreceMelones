import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

public class InventoryItem extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private JLabel labelItem;
	private JLabel labelAmount;
	private JButton btnRemove;
	
	public InventoryItem(Frame frame, String item , String amount){
		
		this.setSize(250, 50);
		new BoxLayout(this, BoxLayout.X_AXIS);
		
		
		labelItem = new JLabel(item);
		labelAmount = new JLabel(amount);		
		btnRemove= new JButton("REMOVE");
		btnRemove.setContentAreaFilled(false);
		btnRemove.setOpaque(true);
		
		labelItem.setFont(new Font("Century Gothic", Font.LAYOUT_LEFT_TO_RIGHT, 20));
		labelAmount.setFont(new Font("Century Gothic", Font.LAYOUT_LEFT_TO_RIGHT, 20));
		
		btnRemove.setLocation(270- btnRemove.WIDTH, 50/2);
		
		add(labelItem);
		add(labelAmount);
		add(btnRemove);
		
		btnRemove.addActionListener(new ActionListener() {
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