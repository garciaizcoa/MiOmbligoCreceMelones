import java.util.ArrayList;
import java.util.HashMap;

public class Ticket {
	
	private Menu menu;
	private Inventory inventory;
	private HashMap<Integer, ArrayList<Plate>> tables;
	
	public Ticket(Menu men, Inventory inv){
		this.menu = men;
		this.inventory = inv;
		tables = new HashMap<>();
	}
	
	public void placeOrder(int num, Plate food){
	
		if(menu.isOnMenu(food)){
			HashMap<String, Integer> itr = food.getPlateIngredients();
			for(String item : itr.keySet()){
				inventory.useIngredient(item, itr.get(item));
			}
			System.out.println("Enjoy your "+food.getName());
			menu.updateMenu();
		}
		
		if(tables.containsKey(num)){
			tables.get(num).add(food);
		}
		else{
			ArrayList<Plate> nl = new ArrayList<>();
			nl.add(food);
			tables.put(num, nl);
		}
		
	}
	
	

}
