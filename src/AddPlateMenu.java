import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPopupMenu;



public class AddPlateMenu extends JPanel {

	private Frame frame;
	private JPanel panel;
	

	/**
	 * Create the panel.
	 */
	public AddPlateMenu(Frame frame){

		System.out.println("AddPlateMenu "+frame.getInventory());

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnCancel = new JButton("Cancel");
		add(btnCancel);

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(frame.getPlatesMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		}); 

		JPanel editPanel =new JPanel();
		editPanel.setLayout(new GridLayout(0, 3, 0, 0));
		add(editPanel);

		JButton btnDone = new JButton("Done");
		editPanel.add(btnDone);

		JTextField plateString = new JTextField();
		plateString.setToolTipText("Insert Item");
		editPanel.add(plateString);

		JTextField plateDouble = new JTextField();
		plateDouble.setToolTipText("Insert Amount");
		editPanel.add(plateDouble);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);

		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		

		for (Map.Entry<String, Integer> entry : frame.getInventory().getInventoryList().entrySet()){

			IngredientOption opt = new IngredientOption(panel, entry.getKey());
			panel.add(opt);
			

		}


		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {											
				System.out.println("Done was clicked");
				Plate plate = new Plate(plateString.getText(), Double.parseDouble(plateDouble.getText()), frame.getInventory());
				frame.getMenu().addPlate(plate);

				for (Component opt : panel.getComponents()) {
					if(((IngredientOption) opt).getCheck().isSelected())
						plate.addIngredient(((IngredientOption) opt).getCheck().getText(), ((IngredientOption) opt).getSelectedInt()); // no existe opcion todavia
				}
				plate.printIngredients();

				frame.getMenu().printMenu();
				frame.getPlatesMenu().getModel().clear();
				for (Plate plato : frame.getMenu().getAvailablePlates()){

					frame.getPlatesMenu().getModel().addElement(plato.getName()+" $"+plato.getPrice());
				}
				frame.setContentPane(frame.getPlatesMenu());
			}
		}); 

	}


	public void refresh(Inventory inv) {
		panel.removeAll();
		for (Map.Entry<String, Integer> entry : inv.getInventoryList().entrySet()){

//			JCheckBox checkBox = new JCheckBox(entry.getKey());
//			panel.add(checkBox);
			
			IngredientOption opt = new IngredientOption(panel, entry.getKey());
			panel.add(opt);
			
		}
	}
	//hola

	public Frame getFrame() {
		return frame;
	}

	public JPanel getPanel() {
		return panel;
	}
}