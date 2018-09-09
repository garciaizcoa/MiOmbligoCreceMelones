import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.GridLayout;

public class IngredientOption extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private JCheckBox check;
	private JComboBox<Integer> combo;
	
	public IngredientOption(JPanel panel, String ingredient) {
		setLayout(new GridLayout(0, 2, 0, 0));
		
		check = new JCheckBox(ingredient);
		this.add(check);
		check.setFont(new Font("Lucida Grande", 0,24));
		
		combo =  new JComboBox<>();
		for(int i=0;i<10;i++) {
			combo.addItem(i);
		}
		this.add(combo);		
	}
	
	public JCheckBox getCheck() {
		return check;
	}
	
	public JComboBox<Integer> getCombo() {
		return combo;
	}
	
	public int getSelectedInt() {
		return (int) this.getCombo().getSelectedItem();
	}
	

}
