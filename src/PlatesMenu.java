import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JList;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PlatesMenu extends JPanel {

	/**
	 * Create the panel.
	 *
	 */

	private Frame frame;
	private javax.swing.JButton btnAdd;
	private javax.swing.JButton btnAdminMenu;
	private javax.swing.JPanel panel;
	private javax.swing.JScrollPane scrollPane;
	private DefaultListModel<String> model;
	final int GAP = 224;

	public PlatesMenu(Frame frame) {

		this.frame = frame;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		scrollPane = new javax.swing.JScrollPane();
		panel = new javax.swing.JPanel();
		btnAdminMenu = new javax.swing.JButton("Admin Menu");
		btnAdd = new javax.swing.JButton("Add");
		

		btnAdminMenu.setFont(frame.getFont().deriveFont(40f));
		btnAdd.setFont(frame.getFont().deriveFont(40f));
		
		btnAdminMenu.setForeground(Color.ORANGE);
		btnAdd.setForeground(Color.ORANGE);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.WHITE);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		layout.setHorizontalGroup(
				layout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createSequentialGroup()
						.addGap(GAP, GAP, GAP)
						.addComponent(btnAdminMenu, GroupLayout.PREFERRED_SIZE, 50,Short.MAX_VALUE)
						.addGap(GAP, GAP, GAP)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
						.addGap(GAP, GAP, GAP)
						.addContainerGap())
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGap(GAP/2, GAP/2, GAP/2)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAdminMenu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(GAP/2, GAP/2, GAP/2)
						)
				);
		this.setLayout(layout);
		this.setBackground(Color.BLACK);


		btnAdminMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(frame.getAdminMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getAddPlateMenu().refresh(frame.getInventory());
				frame.setContentPane(frame.getAddPlateMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 

			}
		});
		
		add(btnAdd);
		add(btnAdminMenu);
		add(scrollPane);	
		
		scrollPane.setViewportView(panel);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JLabel title = new JLabel("PLATES");
		title.setFont(frame.getFont().deriveFont(50f));
		//title.setLocation(scrollPane.getWidth()/2 - 200, 15);
		panel.add(title);
		for (Plate plate : frame.getAddPlateMenu().getAllPlates()){	
			PlateItem item = new PlateItem(frame, plate, plate.getName(),String.valueOf(plate.getPrice()));
			panel.add(item);
			panel.repaint();
		}
		
//
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