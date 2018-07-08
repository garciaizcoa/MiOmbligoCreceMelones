import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
	private Plate plate;

	public PlateItem(Frame frame, Plate plate, String name, String amount) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		this.plate=plate;

		nameLabel= new JLabel(name);
		add(nameLabel);

		amountLabel= new JLabel("$ "+amount);
		add(amountLabel);

		editButton = new JButton("Edit");
		add(editButton);

		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {											
				System.out.println("Edit was clicked");
				

				frame.getEditPlateMenu().getPlateString().setText(name);
				frame.getEditPlateMenu().getPlateDouble().setText(amount);

				frame.getEditPlateMenu().getPanel().removeAll();

				for (Map.Entry<String, Integer> entry : frame.getInventory().getInventoryList().entrySet()){

					IngredientOption opt = new IngredientOption(frame.getEditPlateMenu().getPanel(), entry.getKey());	


					
					if(frame.getMenu().getPlate(plate).getPlateIngredients().containsKey(entry.getKey())) {
						opt.getCheck().setSelected(true);
						opt.getCombo().setSelectedIndex(frame.getMenu().getPlate(plate).getPlateIngredients().get(entry.getKey()));
					}
					else {
						opt.getCheck().setSelected(false); //por siacaso
					}


					frame.getEditPlateMenu().getPanel().add(opt);

					frame.getEditPlateMenu().getPanel().repaint();

					frame.getEditPlateMenu().setPlate(plate);
					frame.setContentPane(frame.getEditPlateMenu());
					frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
					frame.revalidate(); 
				}

			}
		}); 

		removeButton = new JButton("Remove");
		add(removeButton);

		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {											
				System.out.println("Remove was clicked");
				frame.getMenu().removePlate(frame.getMenu().getPlate(plate));
				frame.getAddPlateMenu().getAllPlates().remove(plate);
				frame.getPlatesMenu().refresh();
				frame.getMenu().printMenu();
				
				try {
					Save.savePlate(frame.getAddPlateMenu().getAllPlates());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


				frame.getPlatesMenu().getPanel().repaint();
			}
		}); 
	}

}