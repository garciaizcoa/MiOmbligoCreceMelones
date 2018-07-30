import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventory{

	private Map<String, Integer> inventoryList  = new HashMap<>();

	public Inventory(){
		inventoryList = new HashMap<>();
	}

	public void addItemToInventory(String item, int amount){
		if(inventoryList.containsKey(item)){
			this.restock(item, amount);
		}
		else{
			inventoryList.put(item, amount);
		}	
	}

	public void useIngredient(String item, int amount){
		int curr = inventoryList.get(item);
		if(curr - amount >= 0){
			inventoryList.replace(item, curr-amount);
			System.out.println(item + "current amount is now "+inventoryList.get(item));
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
		int curr = 0;
		try {
		curr = inventoryList.get(item);
		}
		catch(Exception e){
			return false;
		}
		return curr >= num;
	}
	
	public void removeFromInventory(String item){
		inventoryList.remove(item);
	}
	
	
	public Map<String, Integer> getInventoryList() {
		return inventoryList;
	}
	
	public void printInventory() {
		for (Map.Entry<String, Integer> entry : inventoryList.entrySet())
		{
		    System.out.println(entry.getKey() + "/" + entry.getValue());
		}
	}
}
