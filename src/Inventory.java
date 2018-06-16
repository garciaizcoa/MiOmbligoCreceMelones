import java.util.HashMap;

public class Inventory{

	private HashMap<String, Integer> inventoryList  = new HashMap<>();

	public Inventory(){
		inventoryList = new HashMap<>();
	}

	public void addItemToInventory(String item, int amount){
		inventoryList.put(item, amount);
	}

	public void useIngredient(String item, int amount){
		int curr = inventoryList.get(item);
		if(curr - amount >= 0){
			inventoryList.replace(item, curr-amount);
		}
		else{
			System.out.println(item+" "+"not available.");
		}
		// else remover el plato de plates y 
		//notificar que tiene que ir al super
	}

	public void restock(String item, int amount){
		int curr = inventoryList.get(item);
		inventoryList.replace(item, curr+amount);
	}

	public boolean isAvailable(String item, Integer num){
		int curr = inventoryList.get(item);
		return curr >= num;
	}
	
	public void removeFromInventory(String item){
		inventoryList.remove(item);
	}
	

}
