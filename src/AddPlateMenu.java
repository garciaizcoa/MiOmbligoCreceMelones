import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;

public class AddPlateMenu extends JPanel {
	

	private Frame frame;
	/**
	 * Create the panel.
	 */
	public AddPlateMenu(Frame frame){
		
		this.frame = frame;
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnCancel = new JButton("Cancel");
		add(btnCancel);

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(frame.getPlatesMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		}); 

		JPanel editPanel =new JPanel();
		editPanel.setLayout(new GridLayout(0, 3, 0, 0));
		add(editPanel);

		JButton btnDone = new JButton("Done");
		editPanel.add(btnDone);
		
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//Añadir al menu
			}
		}); 
		

		JTextField plateString = new JTextField();
		plateString.setToolTipText("Insert Item");
		editPanel.add(plateString);

		JTextField plateInteger = new JTextField();
		plateInteger.setToolTipText("Insert Amount");
		editPanel.add(plateInteger);

		DefaultListModel<String> model = new DefaultListModel<>();

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		JList<String> invList = new JList<>(model);
		scrollPane.setViewportView(invList);

		
	}

}
