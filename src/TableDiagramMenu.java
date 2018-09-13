import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class TableDiagramMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int numTables;

	private Frame frame;
	private JPanel panel;
	private JTextField numTablesTextBox;
	private JPanel buttonPanel;
	private ArrayList<TableDiagramMenu.Table> tables = new ArrayList<>();
	private JButton btnBack;
	private JButton btnEdit;
	private JScrollPane scrollPane;
	private BufferedImage backgroundImg;

	public TableDiagramMenu(Frame frame) {

		this.frame=frame;
		this.setBackground(Color.BLACK);
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setSize(800, 800);
		scrollPane.add(panel);
		scrollPane.setViewportView(panel);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 0, 0, 0));

		btnBack = new JButton("Back");
		btnBack.setBackground(Color.WHITE); //COLOR BTN
		btnBack.setContentAreaFilled(false);
		btnBack.setOpaque(true);
		buttonPanel.add(btnBack);

		btnEdit = new JButton("Edit");
		btnEdit.setBackground(Color.WHITE); //COLOR BTN
		btnEdit.setContentAreaFilled(false);
		btnEdit.setOpaque(true);
		buttonPanel.add(btnEdit);
		
		btnBack.setFont(frame.getFont().deriveFont(40f));
		btnEdit.setFont(frame.getFont().deriveFont(40f));

		numTablesTextBox = new JTextField();
		numTablesTextBox.setVisible(false);
		buttonPanel.add(numTablesTextBox);
		numTablesTextBox.setColumns(10);

		numTables = 0;
		try {
			numTables = Save.readTableNumber();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		System.out.println(numTables);
		if (numTables==0){
			JTextFieldHintUI numTablesHint = new JTextFieldHintUI(" Insert Amount",Color.RED);
			numTablesTextBox.setUI(numTablesHint);
		}
		else{
			numTablesTextBox.setText(numTables+"");
			refreshTables();
		}

		setLayout();
		add(buttonPanel);
		add(scrollPane);
		
		try {
			backgroundImg = ImageIO.read(new File("Images/Background.png"));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		//Action Listeners

		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnEdit.getText().equals("Edit")){
					numTablesTextBox.setVisible(true);
					btnEdit.setText("Done");
				}
				else{
					if(validateNum(numTablesTextBox.getText())) {
						numTables=Integer.parseInt(numTablesTextBox.getText());
						try {
							Save.saveTableNumber(numTablesTextBox.getText());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						refreshTables();
						numTablesTextBox.setVisible(false);
						btnEdit.setText("Edit");
					}
				}
			}
		});

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.setContentPane(frame.getAdminMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});



	}

	public void refreshTables(){
		panel.removeAll();
		panel.repaint();
		panel.revalidate();
		//panel.add(buttonPanel);
		for(int i=1;i<=numTables;i++){
			TableDiagramMenu.Table tab = new Table(i);
			panel.add(tab);
			tables.add(tab);

		}
	}

	public void setLayout() {
		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(buttonPanel);
		buttonPanel.setLayout(jPanel2Layout);
		buttonPanel.setBackground(new Color(0,0,0,0));
		jPanel2Layout.setHorizontalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup()
						.addContainerGap(292, 292)
						.addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(numTablesTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(292, 292, 292))
				);
		jPanel2Layout.setVerticalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(numTablesTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(109, 109, 109))
				);
	}

	public Table getTableByNumber(int numTable){
		System.out.println("NUMTABLE"+numTable);
		return tables.get(numTable-1);
	}

	public class Table extends JPanel{


		private int tableNumber;
		private ArrayList<Plate> orderOfTable = new ArrayList<>();


		public Table(int tableNumber){
			this.tableNumber = tableNumber;
			JButton btnTable = new CircleButton(tableNumber+"");
			//btnTable.setBackground(Color.BLACK);

			add(btnTable);

			btnTable.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					frame.getTableMenu().setTable(getTableByNumber(tableNumber));
					frame.setContentPane(frame.getTableMenu());
					frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
					frame.revalidate();


				}
			});

		}



		public int getTableNumber(){
			return tableNumber;
		}

		public void setOrderOfTable(ArrayList<Plate> order){
			orderOfTable = order;
		}
		public ArrayList<Plate> getOrderOfTable(){
			return orderOfTable;
		}

		public ArrayList<String> orderToStrings(){
			ArrayList<String> atr = new ArrayList<>();
			for(Plate plt: getOrderOfTable()){
				atr.add(plt.getName());
			}
			return atr;
		}

		public ArrayList<String> kitchenOrderToString(){
			ArrayList<String> atr = new ArrayList<>();

			for(Plate plt: getOrderOfTable()){
				atr.add(plt.getName());

				for (Map.Entry<String, Integer> entry : frame.getMenu().getPlate(plt.getName()).getPlateIngredients().entrySet()){

					if(plt.hasExtra(frame.getMenu().getPlate(plt.getName()),entry.getKey()) )
						atr.add("		Extra "+ entry.getKey());

					else if(plt.hasNone( entry.getKey()))
						atr.add("		No "+ entry.getKey());
					else
						atr.add("		"+entry.getKey());
				}


			}
			return atr;
		}
	}



	public boolean validateNum(String num){
		try{
			Integer.parseInt(num);
		}catch(NumberFormatException nfe){
			numTablesTextBox.setText("");
			numTablesTextBox.setUI(new JTextFieldHintUI(" Must be a number. ",Color.RED));
			return false;
		}
		if(Integer.parseInt(num)<=0){
			numTablesTextBox.setText("");
			numTablesTextBox.setUI(new JTextFieldHintUI(" Can't be a number less than 1. ",Color.RED));
			return false;
		}
		return true;
	}

	//getters

	public int getNumTables() {
		return numTables;
	}
	
	@Override 
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroundImg,0,0, getWidth(),getHeight(), this);
    }
	

}