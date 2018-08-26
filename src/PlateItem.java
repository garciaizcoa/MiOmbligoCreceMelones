import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;

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
		setBackground(Color.WHITE);

		this.plate=plate;

		nameLabel= new JLabel(name);
		nameLabel.setFont(new Font("Century Gothic", Font.LAYOUT_LEFT_TO_RIGHT, 40));
		amountLabel= new JLabel(" $"+amount);
		editButton = new JButton("Edit");
		removeButton = new JButton("Remove");	

		Object[] columns = {"Name", "Price", "Edit", "Remove"};
		Object[][] data = {{nameLabel.getText(), amountLabel.getText(), editButton, removeButton}};
		table = new JTable(data, columns);
		
		table.setLayout(new BoxLayout(table, BoxLayout.Y_AXIS));
		

		TableColumn col;
		for (int i = 0; i < table.getColumnCount(); i++) {
			col = table.getColumnModel().getColumn(i);
			col.setMaxWidth(250);
			if (i == 2 || i==3) { 
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
						.addContainerGap()
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