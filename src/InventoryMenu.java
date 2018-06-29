//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.DefaultListModel;
//import javax.swing.JButton;
//import javax.swing.JList;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//import javax.swing.border.EmptyBorder;
//
//public class InventoryMenu extends Frame {
//
//	private JPanel inventoryMenu;
//	
//	InventoryMenu(){
//		// comienza panel de inventario
//
//				inventoryMenu = new JPanel();
//				inventoryMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
//				inventoryMenu.setLayout(new GridLayout(0, 1, 0, 0));
//
//				JButton btnMainMenu = new JButton("Main Menu");
//				inventoryMenu.add(btnMainMenu);
//
//				btnMainMenu.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//
//						setContentPane(mainMenu); //panel = panel you want to change too.
//						repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
//						revalidate(); 
//
//					}
//				});
//
//				JPanel editPanel =new JPanel();
//				editPanel.setLayout(new GridLayout(0, 3, 0, 0));
//				inventoryMenu.add(editPanel);
//
//				JButton btnAdd = new JButton("Add");
//				editPanel.add(btnAdd);
//
//				JTextField inventoryString = new JTextField();
//				editPanel.add(inventoryString);
//				
//				JTextField inventoryInteger = new JTextField();
//				editPanel.add(inventoryInteger);
//				
//				DefaultListModel<String> model = new DefaultListModel<>();
//				JList<String> invList = new JList<>(model);
//				inventoryMenu.add(invList);
//				
//				btnAdd.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//						System.out.println("Add was clicked");
//						inv.addItemToInventory(inventoryString.getText(), Integer.parseInt( inventoryInteger.getText()));
//						inv.printInventory();
//						model.addElement(inventoryString.getText()+" "+ inventoryInteger.getText());
//					
//					}
//				});
//	}
//}
