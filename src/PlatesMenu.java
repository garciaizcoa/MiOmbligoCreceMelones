import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;

public class PlatesMenu extends JPanel {

	/**
	 * Create the panel.
	 *
	 */
	
	private Inventory inv; 
	private Frame frame;
	
	public PlatesMenu(Frame frame) {
		this.inv = inv;
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
				frame.setContentPane(frame.getAddPlateMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		
		
	}
	
	
	
	

}
