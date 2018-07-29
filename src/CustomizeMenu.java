import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.ScrollPaneConstants;

public class CustomizeMenu extends JPanel {
	
	private Plate plate;
	
	private JPanel panel;


	public CustomizeMenu(Frame frame) {
		
		this.plate=null;

		JButton btnBack = new JButton("Back");
		add(btnBack);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(frame.getCheckoutMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane);

		 panel = new JPanel();
		 panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		scrollPane.setViewportView(panel);
//		panel.setLayout(new FormLayout(new ColumnSpec[] {},
//				new RowSpec[] {}));
		
		
		
	}

	public class ItemOption extends JPanel{


		public ItemOption(String name) {
			setLayout(new GridLayout(1, 0, 0, 0));


			JLabel lblName = new JLabel(name);
			add(lblName);

			JButton btnExtra = new JButton("Extra");
			add(btnExtra);
			
			
			JButton btnRemove = new JButton("Remove Ingredient");
			add(btnRemove);

		}
	}
	
	public void refresh() {
		panel.removeAll();
		for(String item: plate.getPlateIngredients().keySet()) {
			panel.add(new ItemOption(item));
		}
	}
	
	//setters
	public void setPlate(Plate plate) {
		this.plate=plate;
	}
}
