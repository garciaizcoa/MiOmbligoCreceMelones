
public class Main {

	public static void main(String[] args){
		
		Inventory inventory = new Inventory();
		Menu menu = new Menu();
		
		inventory.addItemToInventory("buns", 5);
		inventory.addItemToInventory("meat", 3);
		inventory.addItemToInventory("bbq sauce", 2);
		
		inventory.addItemToInventory("lettuce", 1);
		inventory.addItemToInventory("tomato", 1);
		
		Plate bbqBurger = new Plate("bbqBurger",5.99,  inventory);
		bbqBurger.addIngredient("buns", 2);
		bbqBurger.addIngredient("meat", 1);
		bbqBurger.addIngredient("bbq sauce", 1);
		
		Plate salad = new Plate("salad", 2.99,inventory);
		salad.addIngredient("lettuce", 1);
		salad.addIngredient("tomato", 1);
		
		
		menu.addPlate(bbqBurger);
		menu.addPlate(salad);
	
		
		Ticket ticket = new Ticket(menu, inventory);
		ticket.placeOrder(1,bbqBurger);
		ticket.placeOrder(1,bbqBurger);
		ticket.placeOrder(1,bbqBurger);
		ticket.placeOrder(1, salad);
	
		
	}
}
