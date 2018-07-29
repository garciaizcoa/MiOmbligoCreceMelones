import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class TableDiagramMenu extends JPanel {
	
	private int numTables;
	
	private JPanel panel;
	private JTextField numTablesTextBox;
	
	public TableDiagramMenu(Frame frame) {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		
		JPanel buttonPanel = new JPanel();
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
		
		JTextFieldHintUI numTablesHint = new JTextFieldHintUI(" Insert Amount",Color.RED);
		numTablesTextBox.setUI(numTablesHint);
		
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnEdit.getText().equals("Edit")){
				numTablesTextBox.setVisible(true);
				btnEdit.setText("Done");
				}
				else{
					if(validateNum(numTablesTextBox.getText())) {
					numTables=Integer.parseInt(numTablesTextBox.getText());
					
					panel.removeAll();
					panel.repaint();
					panel.revalidate();
					panel.add(buttonPanel);
					for(int i=1;i<=numTables;i++)
						panel.add(new Table(i));
					numTablesTextBox.setVisible(false);
					btnEdit.setText("Edit");
					}
				}
			}
		});
		
		
		
	}
	
	public class Table extends JPanel{
		
		private int tableNumber;
		//ticket
		
		public Table(int tableNumber){
		JButton btnTable = new JButton(tableNumber+"");
		add(btnTable);
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
	
}
