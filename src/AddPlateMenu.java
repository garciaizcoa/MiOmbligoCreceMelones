import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;



public class AddPlateMenu extends JPanel {

	private Frame frame;
	private JPanel panel;

	private JTextField plateString;
	private JTextField plateDouble;

	private JTextFieldHintUI stringHint;
	private JTextFieldHintUI doubleHint;

	private HashSet<Plate> allPlates = new HashSet<>();


	/**
	 * Create the panel.
	 */
	public AddPlateMenu(Frame frame){

		System.out.println("AddPlateMenu "+frame.getInventory());
		System.out.println("hola "+frame.getAddPlateMenu());

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnCancel = new JButton("Cancel");
		add(btnCancel);

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshText();

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

		plateString = new JTextField();
		//		plateString.setText("Name of Plate");
		//		plateString.setForeground(Color.GRAY);
		//		plateString.setHorizontalAlignment(WIDTH/2);

		stringHint = new JTextFieldHintUI(" Name of Plate",Color.RED);
		plateString.setUI(stringHint);


		editPanel.add(plateString);

		plateDouble = new JTextField();

		doubleHint = new JTextFieldHintUI(" Price",Color.RED);
		plateDouble.setUI(doubleHint);


		//		plateDouble.addMouseListener(new MouseAdapter() {
		//			@Override
		//			public void mouseClicked(MouseEvent e) {
		//				if(plateString.getText().equals("")){
		//					plateString.setText("Name of Plate");
		//				}
		//				plateDouble.setText("");
		//			}
		//		});
		//		plateDouble.setText("Price");
		//		plateDouble.setHorizontalAlignment(WIDTH/2);
		//		plateDouble.setForeground(Color.GRAY);
		//		plateString.addMouseListener(new MouseAdapter() {
		//			@Override
		//			public void mouseClicked(MouseEvent e) {
		//				if(plateDouble.getText().equals("")){
		//					plateDouble.setText("Price");
		//				}
		//				plateString.setText("");
		//			}
		//		});

		editPanel.add(plateDouble);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane);

		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));



		for (Map.Entry<String, Integer> entry : frame.getInventory().getInventoryList().entrySet()){

			IngredientOption opt = new IngredientOption(panel, entry.getKey());
			opt.getCheck().setSelected(false);
			panel.add(opt);
			//

		}


		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {											
				System.out.println("Done was clicked");

				//validate texts
				validateDouble(plateDouble.getText());

				if(	validateString(plateString.getText()) && validateCheckBoxes()) { //start if
					Plate plate = new Plate(plateString.getText(), Double.parseDouble(plateDouble.getText()), frame.getInventory());
					frame.getMenu().addPlate(plate);
					allPlates.add(plate);

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
					refreshText();
					frame.getPlatesMenu().refresh();
					frame.getCustomerMenu().refresh(frame.getMenu());///////////////////////
					frame.setContentPane(frame.getPlatesMenu());

					try {
						Save.savePlate(allPlates);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} //end if
			}
		});


	}

	public void setFrame(Frame frame){
		this.frame = frame;
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

	public void setInitialPlates(){

		for(Plate plate: allPlates){
			frame.getMenu().addPlate(plate);
		}
	}

	public void refreshText() {
		this.getPlateDouble().setText("");
		this.getPlateString().setText("");
		this.getPlateDouble().setUI(doubleHint);
		this.getPlateString().setUI(stringHint);
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

	public void addToAllPlates(Plate plate){
		allPlates.add(plate);
	}

	public HashSet<Plate> getAllPlates(){
		return allPlates;
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
}