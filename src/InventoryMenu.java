import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.TextUI;

import java.awt.event.MouseAdapter;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;

public class InventoryMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Frame frame;
	private JPanel panel;
	private JScrollPane scrollPane;

	private JTextField inventoryString;
	private JTextField inventoryInteger;

	private JTextFieldHintUI stringHint;
	private JTextFieldHintUI integerHint;

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
				refreshText();
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




		inventoryString = new JTextField();
		//inventoryString.setForeground(Color.GRAY);
		inventoryString.setHorizontalAlignment(WIDTH/2);

		stringHint = new JTextFieldHintUI(" Insert Item",Color.RED);
		inventoryString.setUI(stringHint);

		editPanel.add(inventoryString);

		inventoryInteger = new JTextField();
		//		inventoryInteger.setForeground(Color.GRAY);
		//		inventoryInteger.setHorizontalAlignment(WIDTH/2);
		//inventoryInteger.setText("Insert Amount");

		integerHint = new JTextFieldHintUI(" Insert Amount",Color.RED);
		inventoryInteger.setUI(integerHint);



		//		inventoryInteger.addMouseListener(new MouseAdapter() {
		//			@Override
		//			public void mouseClicked(MouseEvent e) {
		//				if(inventoryString.getText().equals("")){
		//					inventoryString.setText("Insert Item");
		//				}
		//				inventoryInteger.setText("");
		//			}
		//		});
		//
		//		inventoryString.addMouseListener(new MouseAdapter() {
		//			@Override
		//			public void mouseClicked(MouseEvent e) {
		//				if(inventoryInteger.getText().equals("")){
		//					inventoryInteger.setText("Insert Amount");
		//				}
		//				inventoryString.setText("");
		//			}
		//		});
		editPanel.add(inventoryInteger);

		//		DefaultListModel<String> model = new DefaultListModel<>();

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane);

		//		JList<String> invList = new JList<>(model);
		//		scrollPane.setViewportView(invList);

		panel = new JPanel();
		scrollPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


		for (Map.Entry<String, Integer> entry : frame.getInventory().getInventoryList().entrySet())
		{

			InventoryItem item = new InventoryItem(frame, entry.getKey(),String.valueOf(entry.getValue()));
			panel.add(item);
			panel.repaint();
		}

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Add was clicked");
				System.out.println(frame.getInventory());

				// validar textos

				validateInteger(inventoryInteger.getText());
				if(validateString(inventoryString.getText())) {//comienza if
					frame.getInventory().addItemToInventory(inventoryString.getText().trim().replaceAll(" +", " "), Integer.parseInt(inventoryInteger.getText().trim()));
					frame.getInventory().printInventory();
					//model.clear();
					panel.removeAll();
					for (Map.Entry<String, Integer> entry : frame.getInventory().getInventoryList().entrySet())
					{
						//	model.addElement(entry.getKey() + "/" + entry.getValue());
						InventoryItem item = new InventoryItem(frame, entry.getKey(),String.valueOf(entry.getValue()));
						panel.add(item);

					}

					try {
						Save.saveToInventory(frame.getInventory());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					scrollPane.setViewportView(panel);

					getFrame().getAddPlateMenu().refresh(frame.getInventory());
				} //termina if
			}
		});
	}

	public void refresh(Inventory inv) {
		panel.removeAll();
		for (Map.Entry<String, Integer> entry : inv.getInventoryList().entrySet()){
			InventoryItem item = new InventoryItem(frame, entry.getKey(),String.valueOf(entry.getValue()));
			panel.add(item);
		}
		frame.revalidate();
	}

	public void refreshText() {
		this.getInventoryInteger().setText("");
		this.getInventoryString().setText("");
		this.getInventoryInteger().setUI(this.integerHint);
		this.getInventoryString().setUI(this.stringHint);
	}

	public boolean validateString(String word) {
		if(word.trim().length()==0) {
			this.getInventoryString().setText("");
			this.getInventoryString().setUI(new JTextFieldHintUI("Inventory name must be a word.", Color.RED));
			//this.getInventoryString().setText("Inventory name must be a word.");
			return false;
		}
		Pattern p = Pattern.compile("^[ A-Za-z]+$"); //verifica que sean espacios y letras solamente
		Matcher m = p.matcher(word);
		if(! m.matches()) {
			this.getInventoryString().setText("");
			//this.getInventoryString().setText("The ingredient can only contain letters and spaces");
			this.getInventoryString().setUI(new JTextFieldHintUI("The ingredient can only contain letters and spaces.", Color.RED));
			return false;
		}
		return true;
	}

	public void validateInteger(String number) {
		try {
			Integer.parseInt(number);
		}
		catch(NumberFormatException nfe) {
			this.getInventoryInteger().setText("");
			this.getInventoryInteger().setUI(new JTextFieldHintUI("The amount must be a number.", Color.RED));
		}
	}

	//getters
	public Frame getFrame() {
		return frame;
	}
	public JPanel getPanel() {
		return panel;
	}

	public JScrollPane getScrollPane(){
		return scrollPane;
	}

	public JTextField getInventoryString() {
		return inventoryString;
	}

	public JTextField getInventoryInteger() {
		return inventoryInteger;
	}

}