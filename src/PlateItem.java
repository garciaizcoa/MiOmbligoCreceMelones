import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;

public class PlateItem extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private JLabel nameLabel;
	private JLabel amountLabel;
	private JButton editButton;
	private JButton removeButton;
	
	public PlateItem(Frame frame, String name, String amount) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		nameLabel= new JLabel(name);
		add(nameLabel);
		
		amountLabel= new JLabel("$ "+amount);
		add(amountLabel);
		
		editButton = new JButton("Edit");
		add(editButton);
		
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {											
				System.out.println("Edit was clicked");
				frame.setContentPane(frame.getAddPlateMenu());
				frame.getAddPlateMenu().getPlateString().setText(name);
				frame.getAddPlateMenu().getPlateDouble().setText(amount);
				
				
				
			}
		}); 
		
		removeButton = new JButton("Remove");
		add(removeButton);
		
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {											
				System.out.println("Remove was clicked");
				frame.getMenu().removePlate(frame.getMenu().getPlate(name));
				frame.getPlatesMenu().refresh(frame.getMenu());
				frame.getMenu().printMenu();
				
			
				frame.getPlatesMenu().getPanel().repaint();
			}
		}); 
		
		
		
		
		
	}

}
