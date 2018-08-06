import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class ItemToPay extends JPanel{

	private Plate plate;
	private CustomerTicketMenu customerTicketMenu;
	private Frame frame;



	public ItemToPay(Frame frame,CustomerTicketMenu customerTicketMenu, Plate plate) {
		this.customerTicketMenu=customerTicketMenu;
		this.plate=plate;

		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblName = new JLabel(plate.getName());
		add(lblName);

		JLabel lblPrice = new JLabel("$ " +Double.toString(plate.getPrice()));
		add(lblPrice);

		JLabel lblExtras = new JLabel("");
		add(lblExtras);

		JButton btnMove = new JButton("Move");
		add(btnMove);

		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshByMovement();


			}
		});

		//Extras
		for (Map.Entry<String, Integer> entry : frame.getMenu().getPlate(plate.getName()).getPlateIngredients().entrySet()){

			if(plate.hasExtra(frame.getMenu().getPlate(plate.getName()),entry.getKey()) )
				lblExtras.setText("Extra "+ entry.getKey() + " ");

			if(plate.hasNone( entry.getKey()))
				lblExtras.setText("No "+ entry.getKey());

		}
	}

	//refreshers
	public void refreshByMovement() {
		if(this.getParent()==customerTicketMenu.getUnpaidPanel()) {
			customerTicketMenu.getUnpaidPanel().remove(this);
			customerTicketMenu.getPaidPanel().add(this);

		}
		else {
			customerTicketMenu.getPaidPanel().remove(this);
			customerTicketMenu.getUnpaidPanel().add(this);
		}
		customerTicketMenu.getUnpaidPanel().repaint();
		customerTicketMenu.getUnpaidPanel().revalidate();
		customerTicketMenu.getPaidPanel().repaint();
		customerTicketMenu.getPaidPanel().revalidate();
		
		customerTicketMenu.refreshTotalAmount();
	}

	public void refreshbyPaying() {

	}
	
	//getters
	
	public Plate getPlate() {
		return plate;
	}


}
