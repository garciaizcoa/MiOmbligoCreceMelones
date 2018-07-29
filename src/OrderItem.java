import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class OrderItem extends JPanel {
	
	private Plate plate;
	
	public OrderItem(Plate plate) {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel = new JLabel(plate.getName());
		add(lblNewLabel);
		
		JButton btnEditIngredients = new JButton("New button");
		add(btnEditIngredients);
		
		JButton btnRemove = new JButton("Remove");
		add(btnRemove);
	}

	
	
}
