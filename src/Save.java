import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map.Entry;

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

	public static void savePlate(Plate plate) throws IOException{

		Path path = Paths.get("memory/PLATES.txt");
		Files.write(path, "\n".getBytes(),StandardOpenOption.APPEND);
		Files.write(path, plate.getName().getBytes(), StandardOpenOption.APPEND);
		for(Entry<String, Integer> e : plate.getPlateIngredients().entrySet()){
			Files.write(path, (e.getKey()+" "+Integer.toString(e.getValue())).getBytes(), StandardOpenOption.APPEND);
		}

	}
	
	public static void readInitialInventory(Inventory inv) throws IOException{
		for(String line: Files.readAllLines(Paths.get("memory/INVENTORY.txt"))){
			String[] arr = line.split(" ");
			inv.addItemToInventory(arr[0], Integer.parseInt(arr[1]));		
		}
	}
}