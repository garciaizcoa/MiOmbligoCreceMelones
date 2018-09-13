import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import java.awt.GridLayout;

public class SettingMenu extends JPanel{
	
	private Frame frame;
	
	public SettingMenu(Frame frame) {
		
		
		this.frame = frame;
		
		JButton changePassBtn = new JButton("Change Password");
		JButton advance = new JButton("Advance");
		JButton backBTN = new JButton("Back");
		
		changePassBtn.setBackground(Color.WHITE);
		changePassBtn.setFont(frame.getFont().deriveFont(50f));

		advance.setBackground(Color.WHITE);
		advance.setFont(frame.getFont().deriveFont(50f));
		
		backBTN.setBackground(Color.WHITE);
		backBTN.setFont(frame.getFont().deriveFont(50f));

		
		changePassBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPane(frame.getPasswordChanger());
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});
		advance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPane(frame.getAdvanceMenu());
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});
		backBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPane(frame.getAdminMenu());
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});
		setLayout(new GridLayout(3, 1, 20, 10));
		add(changePassBtn);
		add(advance);
		add(backBTN);

		
		advance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		
		
	}
}
