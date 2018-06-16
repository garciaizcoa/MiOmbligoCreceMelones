import java.util.HashMap;

public class Plate {
	
	private HashMap<String, Integer> ingredients;
	private Inventory inventory;
	private String name;
	
	public Plate(String name, Inventory inv){
		this.name = name;
		ingredients = new HashMap<>();
		inventory = inv;
		
	}
	
	public boolean checkAvailability(){
		for(String e : ingredients.keySet()){
			if(!inventory.isAvailable(e, ingredients.get(e))){
				return false;
			}
		}
		return true;
	}
	
	public void addIngredient(String name, Integer amount){
		ingredients.put(name, amount);
	}
	
	public HashMap<String, Integer> getPlateIngredients(){
		return ingredients;
	}
	
	public String getName(){
		return name;
	}
	
	//PRICE
	//Kitchen Ticket
	
	
}
