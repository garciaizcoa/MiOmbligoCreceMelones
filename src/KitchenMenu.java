import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class KitchenMenu extends JPanel {

	private JPanel orderPanel;

	private TableDiagramMenu.Table table;

	public KitchenMenu(Frame frame) {

		this.table=null;

		setLayout(new GridLayout(0, 3, 0, 0));

		JButton btnBack = new JButton("Back");
		add(btnBack);

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.setContentPane(frame.getTableMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 

			}
		});

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);

		orderPanel = new JPanel();
		scrollPane.setViewportView(orderPanel);

		orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));

		JButton btnOrderUp = new JButton("Order Up!");
		add(btnOrderUp);

		btnOrderUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//frame.setContentPane(frame.getKitchenMenu()); //panel = panel you want to change too.
				frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
				frame.revalidate(); 

			}
		});

	}

	public void setOrderPanel() {
		orderPanel.removeAll();
		JLabel orderDetails = new JLabel("Order of Table #"+table.getTableNumber());
		orderPanel.add(orderDetails);

		JList lista = new JList(table.kitchenOrderToString().toArray());
		lista.setAlignmentX(CENTER_ALIGNMENT); //tratando de poner la lista en el centro... :(
		lista.setAlignmentY(CENTER_ALIGNMENT);
		orderPanel.add(lista);


		orderPanel.repaint();
		orderPanel.revalidate();

	}

	//setters

	public void setTable(TableDiagramMenu.Table table) {
		this.table=table;
		setOrderPanel();
	}

}