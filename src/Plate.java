import java.util.HashMap;
import java.util.Map;

public class Plate {
	
	private Map<String, Integer> ingredients;
	private Inventory inventory;
	private String name;
	private double price;
	
	public Plate(String name,double price, Inventory inv){
		this.name = name;
		this.price=price;
		this.ingredients = new HashMap<>();
		this.inventory = inv;
		
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
	
//	public void resetIngredients() {
//		for
//	}
	
	public Map<String, Integer> getPlateIngredients(){
		return ingredients;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price=price;
	}
	

	public void printIngredients() {
		for (Map.Entry<String, Integer> entry : ingredients.entrySet())
		{
		    System.out.println("The ingredients in: "+ this.getName()+" "+entry.getKey() + "/" + entry.getValue());
		}
		
	}
	
	//PRICE
	//Kitchen Ticket
	
	
}
