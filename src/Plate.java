import java.util.HashMap;

public class Plate {
	
	private HashMap<String, Integer> ingredients;
	private Inventory inventory;
	private String name;
	private double price;
	
	public Plate(String name,double price, Inventory inv){
		this.name = name;
		this.price=price;
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
	
	public double getPrice() {
		return price;
	}
	
	//PRICE
	//Kitchen Ticket
	
	
}
