import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

public class PlateItem extends JPanel {

	/**
	 * Create the panel.
	 */

	private JLabel nameLabel;
	private JLabel amountLabel;
	private JButton editButton;
	private JButton removeButton;
	private Plate plate;
	private JTable table;

	public PlateItem(Frame frame, Plate plate, String name, String amount) {
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setSize(250, 50);
		setBackground(Color.WHITE);

		this.plate=plate;

		nameLabel= new JLabel(name);
		nameLabel.setFont(frame.getFont().deriveFont(18f));
		DecimalFormat df = new DecimalFormat("0.00");
		Double amt = Double.parseDouble(amount);		
		amountLabel= new JLabel(" $ "+df.format(amt));
		amountLabel.setFont(new Font("Century Gothic", Font.LAYOUT_LEFT_TO_RIGHT, 15));
		editButton = new JButton("Edit");
		editButton.setFont(frame.getFont().deriveFont(25f));
		editButton.setBackground(Color.WHITE);
		removeButton = new JButton();
		removeButton.setFont(new Font("Century Gothic", Font.LAYOUT_LEFT_TO_RIGHT, 20));
//		removeButton.setContentAreaFilled(false);
//		removeButton.setOpaque(true);

//		btnAdminMenu.setText("Admin Menu");
		removeButton.setBackground(Color.RED);
		Image imgRemoveBTN = new ImageIcon(this.getClass().getResource("/x.png")).getImage();
		removeButton.setIcon(new ImageIcon(imgRemoveBTN)); 
		
		JLabel space = new JLabel("   ");
		
		add(nameLabel);
		add(amountLabel);
		add(space);
		add(editButton);
		add(removeButton);
		
		//Action Listeners
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

		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {											
				System.out.println("Edit was clicked");


				frame.getEditPlateMenu().getPlateString().setText(name);
				frame.getEditPlateMenu().getPlateDouble().setText(amount);

				frame.getEditPlateMenu().getPanel().removeAll();

				for (Map.Entry<String, Integer> entry : frame.getInventory().getInventoryList().entrySet()){

					IngredientOption opt = new IngredientOption(frame.getEditPlateMenu().getPanel(), entry.getKey());	



					if(plate.getPlateIngredients().containsKey(entry.getKey())) {
						opt.getCheck().setSelected(true);
						opt.getCombo().setSelectedIndex(plate.getPlateIngredients().get(entry.getKey()));
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


	}

}