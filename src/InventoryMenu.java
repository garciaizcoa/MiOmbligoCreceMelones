import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class InventoryMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Frame frame;
	private JPanel panel;

	/**
	 * Create the panel.
	 */

	public InventoryMenu( Frame frame) {
		
		this.frame=frame;
		
		System.out.println("InventoryMenu "+frame.getInventory());
		
		// Comienza inventoryMenu
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnAdminMenu = new JButton("Admin Menu");
		add(btnAdminMenu);

		btnAdminMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.setContentPane(frame.getAdminMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 

			}
		}); 

		JPanel editPanel =new JPanel();
		editPanel.setLayout(new GridLayout(0, 3, 0, 0));
		add(editPanel);

		JButton btnAdd = new JButton("Add");
		editPanel.add(btnAdd);

		JTextField inventoryString = new JTextField();
		inventoryString.setToolTipText("Insert Item");
		editPanel.add(inventoryString);

		JTextField inventoryInteger = new JTextField();
		inventoryInteger.setToolTipText("Insert Amount");
		editPanel.add(inventoryInteger);

//		DefaultListModel<String> model = new DefaultListModel<>();

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
//		JList<String> invList = new JList<>(model);
//		scrollPane.setViewportView(invList);
		
		panel = new JPanel();
		scrollPane.add(panel);
		
		
		for (Map.Entry<String, Integer> entry : frame.getInventory().getInventoryList().entrySet())
		{
			
			InventoryItem item = new InventoryItem(frame, entry.getKey(),String.valueOf(entry.getValue()));
			panel.add(item);
		}

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Add was clicked");
				System.out.println(frame.getInventory());
				frame.getInventory().addItemToInventory(inventoryString.getText(), Integer.parseInt(inventoryInteger.getText()));
				frame.getInventory().printInventory();
				//model.clear();
				panel.removeAll();
				for (Map.Entry<String, Integer> entry : frame.getInventory().getInventoryList().entrySet())
				{
				//	model.addElement(entry.getKey() + "/" + entry.getValue());
					InventoryItem item = new InventoryItem(frame, entry.getKey(),String.valueOf(entry.getValue()));
					panel.add(item);
					
				}
				
				scrollPane.setViewportView(panel);
				
				getFrame().getAddPlateMenu().refresh(frame.getInventory());
				
			}
		});
	}
	
	public void refresh(Inventory inv) {
		panel.removeAll();
		for (Map.Entry<String, Integer> entry : inv.getInventoryList().entrySet()){
			InventoryItem item = new InventoryItem(frame, entry.getKey(),String.valueOf(entry.getValue()));
			panel.add(item);
		}
	}
	
	public Frame getFrame() {
		return frame;
	}
	public JPanel getPanel() {
		return panel;
	}

}