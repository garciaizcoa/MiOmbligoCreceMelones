import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PasswordChanger extends JPanel {
	private JButton jButton1;
	private JButton jButtonBack;
	private JButton jButton0;
	private JButton jButtonSave;
	private JButton jButton2;
	private JButton jButton3;
	private JButton jButton4;
	private JButton jButton5;
	private JButton jButton6;
	private JButton jButton7;
	private JButton jButton8;
	private JButton jButton9;
	private JPanel jPanel1;
	private JTextField textField;
	private JPanel panel;
	private Frame frame;
	
	
	private String password;
	// End of variables declaration 
	
	public PasswordChanger(Frame frm) {

		this.frame = frm;
	
		textField = new javax.swing.JTextField();
		panel = new JPanel();
		jPanel1 = new JPanel();
		jButton1 = new JButton();
		jButton2 = new JButton();
		jButton3 = new JButton();
		jButton4 = new JButton();
		jButton5 = new JButton();
		jButton6 = new JButton();
		jButton7 = new JButton();
		jButton8 = new JButton();
		jButton9 = new JButton();
		jButtonBack = new JButton();
		jButton0 = new JButton();
		jButtonSave = new JButton();	

		jButton1.setText("1");
		jButton2.setText("2");
		jButton3.setText("3");
		jButton4.setText("4");
		jButton5.setText("5");
		jButton6.setText("6");
		jButton7.setText("7");
		jButton8.setText("8");
		jButton9.setText("9");
		jButtonBack.setText("Back");
		jButton0.setText("0");
		jButtonSave.setText("Save");
		
		jButton1.setBackground(Color.WHITE);
		jButton2.setBackground(Color.WHITE);
		jButton3.setBackground(Color.WHITE);
		jButton4.setBackground(Color.WHITE);
		jButton5.setBackground(Color.WHITE);
		jButton6.setBackground(Color.WHITE);
		jButton7.setBackground(Color.WHITE);
		jButton8.setBackground(Color.WHITE);
		jButton9.setBackground(Color.WHITE);
		jButton0.setBackground(Color.WHITE);
		jButtonBack.setBackground(Color.WHITE);
		jButtonSave.setBackground(Color.WHITE);
		
		jButton1.setFont(frame.getFont().deriveFont(25f));
		jButton2.setFont(frame.getFont().deriveFont(25f));
		jButton3.setFont(frame.getFont().deriveFont(25f));
		jButton4.setFont(frame.getFont().deriveFont(25f));
		jButton5.setFont(frame.getFont().deriveFont(25f));
		jButton6.setFont(frame.getFont().deriveFont(25f));
		jButton7.setFont(frame.getFont().deriveFont(25f));
		jButton8.setFont(frame.getFont().deriveFont(25f));
		jButton9.setFont(frame.getFont().deriveFont(25f));
		jButton0.setFont(frame.getFont().deriveFont(25f));
		jButtonBack.setFont(frame.getFont().deriveFont(25f));
		jButtonSave.setFont(frame.getFont().deriveFont(25f));
		
		try {
			password = Save.readPassword();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		String s = textField.getText();
		
		frame.getContentPane().setLayout(new javax.swing.BoxLayout(frame.getContentPane(), javax.swing.BoxLayout.Y_AXIS));
		
		setLayout(new java.awt.GridLayout(1, 1));

		textField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		textField.setText("");
		setLayout();

		add(panel);
		
		panel.setBackground(Color.decode("#FFCC33"));
//		panel.setBackground(Color.WHITE);
		buttonActions();
		
		

	}
	
	public void setPassword() {
		password = textField.getText();
		try {
			Save.savePassword(password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getPassword() {
		return password;
	}
	
	public void buttonActions() {
		
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				textField.setText(textField.getText() + "1");

				
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});
		
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				textField.setText(textField.getText() + "2");
				
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});
		jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				textField.setText(textField.getText() + "3");
				
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});
		jButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				textField.setText(textField.getText() + "4");
				
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});
		jButton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				textField.setText(textField.getText() + "5");
				
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});
		jButton6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				textField.setText(textField.getText() + "6");
				
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});
		jButton7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				textField.setText(textField.getText() + "7");
				
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});
		jButton8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				textField.setText(textField.getText() + "8");
				
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});
		jButton9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				textField.setText(textField.getText() + "9");
				
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});
		jButton0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				textField.setText(textField.getText() + "0");
				
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});
		
		jButtonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				textField.setText("");
				frame.setContentPane(frame.getSettingMenu());
				
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 
			}
		});	
		
		jButtonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				if(textField.getText().equals("0000")) {
					setPassword();
					System.out.println(getPassword());
					textField.setText("");
					frame.setContentPane(frame.getAdminMenu());
					frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
					frame.revalidate(); 
					
//				}
			}
		});	
	}
	
	public void setLayout() {

		javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel);
		panel.setLayout(panel1Layout);
		panel1Layout.setHorizontalGroup(		 
				panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)	
				.addGroup(panel1Layout.createSequentialGroup()
						.addGap(550, 550, 550)
						.addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGroup(panel1Layout.createSequentialGroup()
						.addGap(550, 550, 550)
						.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, 0)
						.addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, 0)
						.addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGroup(panel1Layout.createSequentialGroup()
						.addGap(550, 550, 550)
						.addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, 0)
						.addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, 0)
						.addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGroup(panel1Layout.createSequentialGroup()
						.addGap(550, 550, 550)
						.addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, 0)
						.addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, 0)
						.addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGroup(panel1Layout.createSequentialGroup()
						.addGap(550, 550, 550)
						.addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, 0)
						.addComponent(jButton0, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, 0)
						.addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))

				);

		panel1Layout.setVerticalGroup(

				panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panel1Layout.createSequentialGroup()
						.addGap(200,200,200)
						.addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton0, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))

				);

	}
}
