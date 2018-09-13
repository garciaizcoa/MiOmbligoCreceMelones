import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SpringLayout;

public class AdvanceMenu extends JPanel {
	
	
	Frame frame;
	private JTextField textField;
	
	public AdvanceMenu(Frame frame){
		
		
		this.frame = frame;
		this.setBackground(Color.WHITE);
		
		
		
		
	
	
		
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(10);
		
		
		
		JButton btnSave = new JButton("Save Tax");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(frame.getSettingMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});
		btnBack.setBackground(Color.WHITE);
		btnBack.setFont(frame.getFont().deriveFont(25f));

		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(frame.getSettingMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 

			}
		});
		
		btnSave.setFont(frame.getFont().deriveFont(25f));
		btnSave.setBackground(Color.WHITE);
		
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
				
					}
				});
		
		JLabel current = new JLabel("Current Tax");
		current.setFont(frame.getFont().deriveFont(20f));
		
		JLabel lblCurrentTax = new JLabel("0.00");
		
		JLabel lblTax = new JLabel("Set Tax");
		lblTax.setHorizontalAlignment(SwingConstants.CENTER);
		lblTax.setFont(frame.getFont().deriveFont(35f));
		lblTax.setFont(frame.getFont().deriveFont(35f));
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, btnBack, 0, SpringLayout.NORTH, current);
		springLayout.putConstraint(SpringLayout.WEST, btnBack, 200, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblCurrentTax, 13, SpringLayout.SOUTH, current);
		springLayout.putConstraint(SpringLayout.EAST, lblCurrentTax, 0, SpringLayout.EAST, current);
		springLayout.putConstraint(SpringLayout.NORTH, current, 142, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, current, -64, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, btnSave, 6, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.EAST, btnSave, -10, SpringLayout.EAST, textField);
		springLayout.putConstraint(SpringLayout.NORTH, textField, 6, SpringLayout.SOUTH, lblTax);
		springLayout.putConstraint(SpringLayout.EAST, textField, -249, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblTax, 28, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblTax, 51, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lblTax, -275, SpringLayout.EAST, this);
		setLayout(springLayout);
		add(btnBack);
		add(btnSave);
		add(textField);
		add(lblCurrentTax);
		add(current);
		add(lblTax);
	
		
	}
}
