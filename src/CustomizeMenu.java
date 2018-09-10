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

import java.awt.Color;
import java.awt.GridBagConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.ScrollPaneConstants;

public class CustomizeMenu extends JPanel {
	
	private Plate plate;
	
	private JPanel panel;
	private Frame frame;
	private JLabel lblExtraMessage;


	public CustomizeMenu(Frame frame) {
		
		this.plate=null;
		this.frame = frame;
		this.setBackground(Color.WHITE);
		JButton btnBack = new JButton("Back");btnBack.setBackground(Color.WHITE); //COLOR BTN
		btnBack.setContentAreaFilled(false);
		btnBack.setOpaque(true);
		btnBack.setContentAreaFilled(false);
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
		
		lblExtraMessage = new JLabel("Yo existo");
		lblExtraMessage.setVisible(false);
		add(lblExtraMessage);
//		panel.setLayout(new FormLayout(new ColumnSpec[] {},
//				new RowSpec[] {}));
		
		
		
	}

	public class ItemOption extends JPanel{


		public ItemOption(String name) {
			setLayout(new GridLayout(1, 0, 0, 0));

			this.setBackground(Color.WHITE);
			JLabel lblName = new JLabel(name);
			add(lblName);

			JButton btnExtra = new JButton("Extra");
			btnExtra.setBackground(Color.WHITE); //COLOR BTN
			btnExtra.setContentAreaFilled(false);
			btnExtra.setOpaque(true);
			btnExtra.setContentAreaFilled(false);
			add(btnExtra);
			
			//VERIFICAR SI HAY SUFICIENTES INGREDIENTES
			btnExtra.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					plate.getPlateIngredients().put(name, plate.getPlateIngredients().get(name)+1);
					System.out.println(plate.getPlateIngredients().entrySet().toString());
					setExtraLabel("Extra "+name+" was added!");
					lblExtraMessage.setVisible(true);
				}
			});
			
			JButton btnRemove = new JButton("Remove Ingredient");
			btnRemove.setBackground(Color.WHITE); //COLOR BTN
			btnRemove.setContentAreaFilled(false);
			btnRemove.setOpaque(true);
			btnRemove.setContentAreaFilled(false);
			add(btnRemove);

			btnRemove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					plate.getPlateIngredients().remove(name);
					frame.getCustomizeMenu().refresh();
					System.out.println(plate.getPlateIngredients().entrySet().toString());
					setExtraLabel(name+" was removed!");
					lblExtraMessage.setVisible(true);
				}
			});
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
	
	public void setExtraLabel(String name){
		lblExtraMessage.setText(name);
	}
}
