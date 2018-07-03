import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.BoxLayout;

public class PlateItem extends JPanel {

	/**
	 * Create the panel.
	 */

	private JLabel nameLabel;
	private JLabel amountLabel;
	private JButton editButton;
	private JButton removeButton;

	public PlateItem(Frame frame, String name, String amount) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		nameLabel= new JLabel(name);
		add(nameLabel);

		amountLabel= new JLabel("$ "+amount);
		add(amountLabel);

		editButton = new JButton("Edit");
		add(editButton);

		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {											
				System.out.println("Edit was clicked");
				

				frame.getAddPlateMenu().getPlateString().setText(name);
				frame.getAddPlateMenu().getPlateDouble().setText(amount);

				frame.getAddPlateMenu().getPanel().removeAll();

				for (Map.Entry<String, Integer> entry : frame.getInventory().getInventoryList().entrySet()){

					IngredientOption opt = new IngredientOption(frame.getAddPlateMenu().getPanel(), entry.getKey());	


					
					if(frame.getMenu().getPlate(name).getPlateIngredients().containsKey(entry.getKey())) {
						opt.getCheck().setSelected(true);
						opt.getCombo().setSelectedIndex(frame.getMenu().getPlate(name).getPlateIngredients().get(entry.getKey()));
					}
					else {
						opt.getCheck().setSelected(false); //por siacaso
					}


					frame.getAddPlateMenu().getPanel().add(opt);

					frame.getAddPlateMenu().getPanel().repaint();

					frame.setContentPane(frame.getAddPlateMenu());
				}









			}
		}); 

		removeButton = new JButton("Remove");
		add(removeButton);

		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {											
				System.out.println("Remove was clicked");
				frame.getMenu().removePlate(frame.getMenu().getPlate(name));
				frame.getPlatesMenu().refresh(frame.getMenu());
				frame.getMenu().printMenu();


				frame.getPlatesMenu().getPanel().repaint();
			}
		}); 





	}

}