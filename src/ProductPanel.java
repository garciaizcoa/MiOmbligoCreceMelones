import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class ProductPanel extends JPanel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductPanel(Frame frame, Plate plate) {

		
				JButton productBtn = new JButton("");
				productBtn.setIcon(new ImageIcon("C:\\Users\\chris\\Desktop\\testMeal.png"));
				
				JButton menos = new JButton("");
				menos.setForeground(Color.BLACK);
				menos.setIcon(new ImageIcon("C:\\Users\\chris\\Desktop\\menos.png"));
				
				JLabel number = new JLabel("0");
				number.setHorizontalAlignment(SwingConstants.CENTER);
				number.setFont(new Font("Tahoma", Font.BOLD, 12));
				
				JButton mas = new JButton("");
				mas.setForeground(Color.BLACK);
				mas.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});
				mas.setIcon(new ImageIcon("C:\\Users\\chris\\Desktop\\mas.png"));
				GroupLayout gl_productPanel = new GroupLayout(this);
				gl_productPanel.setHorizontalGroup(
					gl_productPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_productPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_productPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_productPanel.createSequentialGroup()
									.addComponent(menos, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(number, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(mas, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
								.addComponent(productBtn, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
							.addGap(22))
				);
				gl_productPanel.setVerticalGroup(
					gl_productPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_productPanel.createSequentialGroup()
							.addComponent(productBtn, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_productPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_productPanel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(menos, GroupLayout.PREFERRED_SIZE, 21, Short.MAX_VALUE)
									.addComponent(mas, GroupLayout.PREFERRED_SIZE, 21, Short.MAX_VALUE))
								.addComponent(number, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
							.addContainerGap())
				);
				this.setLayout(gl_productPanel);
				
		
		
		
		
	}
	
}
