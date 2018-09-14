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
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SpringLayout;
import javax.swing.BoxLayout;
import javax.swing.DropMode;
import java.awt.GridLayout;

public class AdvanceMenu extends JPanel {


	Frame frame;
	private JTextField textField;
	private String percent;
	private String taxAmount ;

	public AdvanceMenu(Frame frame){


		this.frame = frame;
		this.setBackground(Color.WHITE);
		percent = "%";


		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(2);

		try {
			taxAmount = Save.readTax();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		JLabel lblCurrentTax = new JLabel(taxAmount + percent);
		lblCurrentTax.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblTax = new JLabel("Set Tax");
		lblTax.setHorizontalAlignment(SwingConstants.CENTER);
		lblTax.setFont(frame.getFont().deriveFont(35f));
		lblCurrentTax.setFont(frame.getFont().deriveFont(25f));



		JButton btnSave = new JButton("Save Tax");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taxAmount = textField.getText();
				lblCurrentTax.setText(taxAmount + percent);
				try {
					Save.saveTax(taxAmount);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnSave.setFont(frame.getFont().deriveFont(25f));
		btnSave.setBackground(Color.WHITE);


		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(frame.getSettingMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});

		JLabel current = new JLabel("Current Tax");
		current.setFont(frame.getFont().deriveFont(20f));
		
		btnBack.setBackground(Color.WHITE);
		btnBack.setFont(frame.getFont().deriveFont(25f));
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setAutoCreateContainerGaps(true);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTax, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
										.addGap(41)
										.addComponent(btnSave))
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(current, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblCurrentTax, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
										.addGap(122)))
						.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(lblTax, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(current, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblCurrentTax)))
						.addContainerGap(99, Short.MAX_VALUE))
				);
		setLayout(groupLayout);


	}
	
	public double getTax(){
		Double tx = Double.parseDouble(taxAmount);
		return tx*Math.pow(10, -2);
	}
}
