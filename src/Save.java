import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

public class Save {

	public static void saveToInventory(Inventory inv) throws IOException{

		Path path = Paths.get("memory/INVENTORY.txt");
		Files.delete(path);
		if(!inv.getInventoryList().isEmpty()) {
		for(Entry<String, Integer> e : inv.getInventoryList().entrySet()){
			Files.write(path, (e.getKey()+" "+Integer.toString(e.getValue())).getBytes(),StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			Files.write(path, "\n".getBytes(),StandardOpenOption.APPEND);
		}
		}
		else {
			Files.write(path, "Empty".getBytes(), StandardOpenOption.CREATE);
		}
		//
	}

	public static void saveToMenu(Plate plate) throws IOException{

		Path path = Paths.get("memory/MENU.txt");
		Files.write(path, (plate.getName()+" "+Double.toString(plate.getPrice())).getBytes(),StandardOpenOption.APPEND);

	}

	public static void savePlate(HashSet<Plate> plateSet) throws IOException{

		Path path = Paths.get("memory/PLATES.txt");
		Files.delete(path);
		if(!plateSet.isEmpty()){
			for(Plate plate: plateSet ){
				Files.write(path, (plate.getName()+"  "+plate.getPrice()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
				for(Entry<String, Integer> e : plate.getPlateIngredients().entrySet()){
					Files.write(path, ("  "+e.getKey()+"  "+Integer.toString(e.getValue())).getBytes(), StandardOpenOption.APPEND);

				}
				Files.write(path, "\n".getBytes(),StandardOpenOption.APPEND);
			}
		}
		else{
			Files.write(path, "Empty".getBytes(), StandardOpenOption.CREATE);
		}

	}
	
	public static void saveTableNumber(String tableNum) throws IOException{
		Path path = Paths.get("memory/TABLES.txt");
		Files.delete(path);
		Files.write(path, tableNum.getBytes(), StandardOpenOption.CREATE);
	}
	
	public static void savePassword(String password) throws IOException{
		Path path = Paths.get("memory/PASSWORD.txt");
		Files.delete(path);
		Files.write(path, password.getBytes(), StandardOpenOption.CREATE);
	}
	
	public static int readTableNumber() throws IOException{
		for(String line: Files.readAllLines(Paths.get("memory/TABLES.txt"))){
			return Integer.parseInt(line);
		}
		return 0;
	}

	public static void readInitialInventory(Inventory inv) throws IOException{
		for(String line: Files.readAllLines(Paths.get("memory/INVENTORY.txt"))){
			if(!line.equals("Empty")) {
			String[] arr = line.split(" ");
			String item = new String();
			for(int i=0; i<arr.length-1; i++){
				if(arr[i+1] == arr[arr.length-1]){
					item+= arr[i]; 
				}
				else{
					item+= arr[i]+" ";
				}
			}
			inv.addItemToInventory(item, Integer.parseInt(arr[arr.length-1]));		
			}
		}
	}

	public static void readInitialPassword(PasswordChanger password) throws IOException{
		for(String line: Files.readAllLines(Paths.get("memory/PASSWORD.txt"))){
			if(!line.equals("Empty")) {
			String[] arr = line.split(" ");
			String pass = new String();
			for(int i=0; i<arr.length-1; i++){
				if(arr[i+1] == arr[arr.length-1]){
					pass+= arr[i]; 
				}
			}
			pass= password.getPassword();
//			inv.addItemToInventory(item, Integer.parseInt(arr[arr.length-1]));		
			
			}
		}
	}

	public static void readInitialAddPlatesMenu(AddPlateMenu apm, Inventory inv) throws IOException{
		for(String line: Files.readAllLines(Paths.get("memory/PLATES.txt"))){
			if(!line.equals("Empty")){
			String[] arr = line.split("  ");
			Plate plt = new Plate(arr[0], Double.parseDouble(arr[1]), inv);
			for(int i=2; i< arr.length; i+=2){
				plt.addIngredient(arr[i], Integer.parseInt(arr[i+1]));
			}
			apm.addToAllPlates(plt);
			}
		}

	}
}