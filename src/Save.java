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
		for(Entry<String, Integer> e : inv.getInventoryList().entrySet()){
			Files.write(path, (e.getKey()+" "+Integer.toString(e.getValue())).getBytes(),StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			Files.write(path, "\n".getBytes(),StandardOpenOption.APPEND);
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

	public static void readInitialInventory(Inventory inv) throws IOException{
		for(String line: Files.readAllLines(Paths.get("memory/INVENTORY.txt"))){
			String[] arr = line.split(" ");
			inv.addItemToInventory(arr[0], Integer.parseInt(arr[1]));		
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