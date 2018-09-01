import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

public class InventoryMenu extends JPanel {

	private static final long serialVersionUID = 1L;

	private Frame frame;
	private JPanel panel;
	private JScrollPane scrollPane;

	private JTextField inventoryString;
	private JTextField inventoryInteger;

	private JTextFieldHintUI stringHint;
	private JTextFieldHintUI integerHint;

	private javax.swing.JButton btnAdd;
	private javax.swing.JButton btnAdminMenu;
	/**
	 * Create the panel.
	 */

	public InventoryMenu( Frame frame) {

		this.frame=frame;

		System.out.println("InventoryMenu "+frame.getInventory());

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new GridLayout(0, 1, 0, 0));
		setBackground(Color.BLACK);

		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		scrollPane = new JScrollPane();
		btnAdminMenu = new JButton();
		inventoryString = new JTextField();
		inventoryInteger = new JTextField(); 
		btnAdd = new JButton();

		btnAdminMenu.setText("Admin Menu");
		stringHint = new JTextFieldHintUI(" Insert Item",Color.RED);
		inventoryString.setUI(stringHint);
		integerHint = new JTextFieldHintUI(" Insert Amount",Color.RED);
		inventoryInteger.setUI(integerHint);
		btnAdd.setText("Add");
		
		btnAdminMenu.setFont(frame.getFont().deriveFont(40f));
		btnAdd.setFont(frame.getFont().deriveFont(40f));
		btnAdminMenu.setForeground(Color.ORANGE);
		btnAdd.setForeground(Color.ORANGE);
		inventoryString.getFont().deriveFont(25);
		inventoryInteger.getFont().deriveFont(25);
		inventoryString.setAlignmentX(CENTER_ALIGNMENT);
		inventoryInteger.setAlignmentX(CENTER_ALIGNMENT);

		setLayout();
		
		btnAdminMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getCustomerMenu().refresh();
				refreshText();
				frame.setContentPane(frame.getAdminMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 

			}
		}); 

		add(btnAdminMenu);
		add(inventoryString);
		add(inventoryInteger);
		add(btnAdd);
		
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane);
		scrollPane.add(panel);


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

	public void setLayout(){
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(btnAdminMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
								.addComponent(inventoryString)
								.addComponent(inventoryInteger)
								.addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
						.addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createSequentialGroup()
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, 200)
						.addComponent(btnAdminMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(27, 27, 27)
						.addComponent(inventoryString, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(inventoryInteger, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(27, 27, 27)
						.addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(52, Short.MAX_VALUE))
				);
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