import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Ticket {
	
	private Menu menu;
	private Inventory inventory;
	private Map<Integer, ArrayList<Plate>> tables;
	
	public Ticket(Menu men, Inventory inv){
		this.menu = men;
		this.inventory = inv;
		this.tables = new HashMap<>();
	}
	
	public void placeOrder(int num, Plate food){
	
		if(menu.isOnMenu(food)){
			Map<String, Integer> itr = food.getPlateIngredients();
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
