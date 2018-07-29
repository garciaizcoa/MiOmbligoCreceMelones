import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

public class TableDiagramMenu extends JPanel {

	private int numTables;

	private JPanel panel;
	private JTextField numTablesTextBox;
	private JPanel buttonPanel;
	private ArrayList<TableDiagramMenu.Table> tables = new ArrayList<>();

	public TableDiagramMenu(Frame frame) {
		setLayout(new GridLayout(1, 0, 0, 0));

		panel = new JPanel();
		add(panel);

		buttonPanel = new JPanel();
		panel.add(buttonPanel);

		JButton btnBack = new JButton("Back");
		buttonPanel.add(btnBack);

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.setContentPane(frame.getAdminMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});

		JButton btnEdit = new JButton("Edit");
		buttonPanel.add(btnEdit);


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


	}

	public void refreshTables(){
		panel.removeAll();
		panel.repaint();
		panel.revalidate();
		panel.add(buttonPanel);
		for(int i=1;i<=numTables;i++){
			TableDiagramMenu.Table tab = new Table(i);
			panel.add(tab);
			tables.add(tab);
			
		}
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
			JButton btnTable = new JButton(tableNumber+"");
			add(btnTable);
			
			btnTable.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					panel.removeAll();
				
					JButton btnBack = new JButton("Back");
					panel.add(btnBack);
					
					btnBack.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							panel.removeAll();
							panel.add(buttonPanel);
							for(int i=1; i<=numTables; i++){
								panel.add(getTableByNumber(i));
							}
							panel.repaint();
							panel.revalidate();
							
						}
					});
					
					JLabel orderDetails = new JLabel("Order of Table #"+tableNumber);
					panel.add(orderDetails);
					JList lista = new JList(orderToStrings().toArray());
					panel.add(lista);
					panel.repaint();
					panel.revalidate();
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

}
