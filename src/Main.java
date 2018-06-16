
public class Main {

	public static void main(String[] args){
		
		Inventory inventory = new Inventory();
		Menu menu = new Menu();
		
		inventory.addItemToInventory("buns", 5);
		inventory.addItemToInventory("meat", 3);
		inventory.addItemToInventory("bbq sauce", 2);
		
		Plate bbqBurger = new Plate("bbqBurger", inventory);
		bbqBurger.addIngredient("buns", 2);
		bbqBurger.addIngredient("meat", 1);
		bbqBurger.addIngredient("bbq sauce", 1);
		
		menu.addPlate(bbqBurger);
	
		
		Ticket ticket = new Ticket(menu, inventory);
		ticket.placeOrder(1,bbqBurger);
		ticket.placeOrder(1,bbqBurger);
		ticket.placeOrder(1,bbqBurger);
	
		
	}
}
