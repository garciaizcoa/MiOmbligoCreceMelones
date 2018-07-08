import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

public class PlatesMenu extends JPanel {

	/**
	 * Create the panel.
	 *
	 */

	private Frame frame;
	private JPanel panel;


	private DefaultListModel<String> model;

	public PlatesMenu(Frame frame) {

		this.frame = frame;


		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JButton btnAdminMenu = new JButton("Admin Menu");
		add(btnAdminMenu);

		btnAdminMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(frame.getAdminMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});

		JButton btnAdd = new JButton("Add");
		add(btnAdd);

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getAddPlateMenu().refresh(frame.getInventory());
				frame.setContentPane(frame.getAddPlateMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
				

			}
		});

		//model = new DefaultListModel<>();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane);

		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		//		JList list = new JList(model);
		//		scrollPane.setViewportView(list);
		
		

//		for (Plate plate : frame.getMenu().getAvailablePlates())
//		{

		for (Plate plate : frame.getAddPlateMenu().getAllPlates())
		{
			PlateItem item = new PlateItem(frame, plate, plate.getName(),String.valueOf(plate.getPrice()));
			panel.add(item);
			panel.repaint();
		}




	}

	public DefaultListModel<String> getModel() {
		return model;
	}

	public JPanel getPanel() {
		return panel;

	}
	
	public void refresh() {
		panel.removeAll();
		for (Plate plate : frame.getAddPlateMenu().getAllPlates()){	
			PlateItem item = new PlateItem(frame, plate, plate.getName(),String.valueOf(plate.getPrice()));
			panel.add(item);
			panel.repaint();
		}
		frame.revalidate();
	}	
}