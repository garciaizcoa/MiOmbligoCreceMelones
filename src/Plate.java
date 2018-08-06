import java.util.HashMap;
import java.util.Map;

public class Plate {

	private HashMap<String, Integer> ingredients;
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

	public boolean hasExtra(Plate plato, String ingrediente) {
		System.out.println(plato.getName());
		System.out.println(ingrediente);
		if(this.getPlateIngredients().get(ingrediente)!=null) {
			if(this.getPlateIngredients().get(ingrediente)>plato.getPlateIngredients().get(ingrediente)) {
				return true;
			}
		}
		return false;
	}
	
	

	public boolean hasNone( String ingrediente) { //esta funcionando al reves
		if(this.getPlateIngredients().get(ingrediente)==null) 
			return true;
		return false;
	}




	//getters

	public HashMap<String, Integer> getPlateIngredients(){
		return ingredients;
	}

	public String getName(){
		return name;
	}
	public Inventory getInventory(){
		return inventory;
	}

	//setters

	public void setName(String name) {
		this.name=name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price=price;
	}

	protected Plate clone(){
		Plate np =  new Plate(this.getName(),this.getPrice(),this.getInventory());
		for(Map.Entry<String, Integer> e: this.getPlateIngredients().entrySet()){
			np.addIngredient(e.getKey(), e.getValue());
		}
		return np;
	}


	public void printIngredients() {
		for (Map.Entry<String, Integer> entry : ingredients.entrySet())
		{
			System.out.println("The ingredients in: "+ this.getName()+" "+entry.getKey() + "/" + entry.getValue());
		}

	}




}
