import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class EditPlateMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Frame frame;
	private JPanel panel;

	private JTextField plateString;
	private JTextField plateDouble;
	private JButton btnCancel;
	private JButton btnDone;
	private JScrollPane scrollPane;

	private Plate plate;


	public EditPlateMenu(Frame frame){ 

		System.out.println("Edit mode, activate!!!");

		plate= null;
		
		this.setBackground(Color.WHITE);

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new GridLayout(0, 1, 0, 0));

		btnCancel = new JButton("Cancel");
		btnDone = new JButton("Done");
		
		btnCancel.setFont(frame.getFont().deriveFont(30f));
		btnDone.setFont(frame.getFont().deriveFont(30f));


		
		plateString = new JTextField();
		plateString.setText("Name of Plate");
		plateString.setForeground(Color.GRAY);
		plateString.setHorizontalAlignment(WIDTH/2);
		
		plateDouble = new JTextField();
		plateDouble.setText("Price");
		plateDouble.setHorizontalAlignment(WIDTH/2);
		plateDouble.setForeground(Color.GRAY);	

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		

		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		btnCancel.setFont(frame.getFont().deriveFont(30f));
		btnDone.setFont(frame.getFont().deriveFont(30f));
		
		btnCancel.setBackground(Color.WHITE);
		btnDone.setBackground(Color.WHITE);

		
		add(plateString);
		add(plateDouble);
		add(btnDone);
		add(scrollPane);
		add(btnCancel);

		setLayout();

		for (Map.Entry<String, Integer> entry : frame.getInventory().getInventoryList().entrySet()){

			IngredientOption opt = new IngredientOption(panel, entry.getKey());
			opt.getCheck().setSelected(false);
			panel.add(opt);
			//

		}

		//Action Listeners
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.setContentPane(frame.getPlatesMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		}); 
		
		btnDone.addActionListener(new ActionListener() { //edit existing plate
			public void actionPerformed(ActionEvent e) {	

				System.out.println("Done was clicked");

				validateDouble(plateDouble.getText());

				if(	validateString(plateString.getText()) && validateCheckBoxes()) { //start if
					plate.setName(plateString.getText());
					plate.setPrice(Double.parseDouble(plateDouble.getText()));
					plate.getPlateIngredients().clear();

					for (Component opt : panel.getComponents()) {
						if(((IngredientOption) opt).getCheck().isSelected())
							plate.addIngredient(((IngredientOption) opt).getCheck().getText(), ((IngredientOption) opt).getSelectedInt()); // no existe opcion todavia
					}
					plate.printIngredients();

					frame.getMenu().printMenu();


					for (Plate plato : frame.getMenu().getAvailablePlates()){

						PlateItem item = new PlateItem(frame,plato, plato.getName(),String.valueOf(plato.getPrice()));
						frame.getPlatesMenu().getPanel().add(item);

					}
					plateString.setText("Name of Plate");
					plateDouble.setText("Price");
					frame.getPlatesMenu().refresh();
					frame.getCustomerMenu().refresh();
					frame.setContentPane(frame.getPlatesMenu());
				}

				frame.getAddPlateMenu().getAllPlates().add(plate);

				try {
					Save.savePlate(frame.getAddPlateMenu().getAllPlates());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	public void refresh(Inventory inv) {
		panel.removeAll();
		for (Map.Entry<String, Integer> entry : inv.getInventoryList().entrySet()){

			//			JCheckBox checkBox = new JCheckBox(entry.getKey());
			//			panel.add(checkBox);

			IngredientOption opt = new IngredientOption(panel, entry.getKey());
			opt.getCheck().setSelected(false);
			panel.add(opt);

		}
	}

	public boolean validateString(String word) {
		if(word.trim().length()==0) {
			this.getPlateString().setText("");
			this.getPlateString().setUI(new JTextFieldHintUI("Plate must have a name.", Color.RED));
			return false;
		}
		Pattern p = Pattern.compile("^[ A-Za-z]+$"); //verifica que sean espacios y letras solamente
		Matcher m = p.matcher(word);
		if(! m.matches()) {
			this.getPlateString().setText("");
			this.getPlateString().setUI(new JTextFieldHintUI("The ingredient can only contain letters and spaces.", Color.RED));
			return false;
		}
		return true;
	}

	public void validateDouble(String number) {
		try {
			Double.parseDouble(number);
		}
		catch(NumberFormatException nfe) {
			this.getPlateDouble().setText("");
			this.getPlateDouble().setUI(new JTextFieldHintUI("The amount must be a number.", Color.RED));
		}
	}

	public boolean validateCheckBoxes() {
		for (Component opt : panel.getComponents()) {
			if(((IngredientOption) opt).getCheck().isSelected() && ((IngredientOption) opt).getSelectedInt()!=0) {
				return true;
			}
		}
		this.getPlateDouble().setText("");
		this.getPlateString().setText("");
		this.getPlateDouble().setUI(new JTextFieldHintUI("The plate must contain one ingredient",Color.RED));
		this.getPlateString().setUI(new JTextFieldHintUI("The plate must contain one ingredient",Color.RED));

		return false;
	}

	public void setLayout(){

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
								.addComponent(plateString)
								.addComponent(plateDouble)
								.addComponent(btnDone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
						.addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createSequentialGroup()
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, 200)
						.addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(27, 27, 27)
						.addComponent(plateString, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(plateDouble, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(27, 27, 27)
						.addComponent(btnDone, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(52, Short.MAX_VALUE))
				);
	}

	public Frame getFrame() {
		return frame;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JTextField getPlateString() {
		return plateString;
	}

	public JTextField getPlateDouble() {
		return plateDouble;
	}

	public Plate getPlate() {
		return plate;
	}

	public void setPlate(Plate plate) {
		this.plate=plate;
	}
}
