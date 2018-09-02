import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;
import java.awt.FlowLayout;
import java.awt.Font;

public class InventoryItem extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private JLabel labelItem;
	private JLabel labelAmount;
	private JButton button;
	private JTable table;
	
	public InventoryItem(Frame frame, String item , String amount){
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		labelItem = new JLabel(item);
		labelAmount = new JLabel(amount);		
		button= new JButton("Remove");
		
		Object[] columns = {"Item", "Amount","Remove"};
		Object[][] data = {{labelItem.getText(), labelAmount.getText(), button}};
		table = new JTable(data, columns);
		
		table.setLayout(new BoxLayout(table, BoxLayout.Y_AXIS));
		
		TableColumn col;
		for (int i = 0; i < table.getColumnCount(); i++) {
			col = table.getColumnModel().getColumn(i);
			col.setMaxWidth(250);
			if (i == 2) { 
				ButtonRenderer br = new ButtonRenderer();
				col.setCellRenderer(br);
				col.setCellEditor(br.getButtonEditor());
			}
			
		}
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)
						)
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
						)
				);
		
		table.setFont(new Font("Century Gothic", Font.LAYOUT_LEFT_TO_RIGHT, 30));
		table.setRowHeight(50);
		add(table);
		
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