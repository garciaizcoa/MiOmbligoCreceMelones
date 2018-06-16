import java.util.ArrayList;

public class Menu {

	private ArrayList<Plate> availablePlates; 

	public Menu(){
		availablePlates = new ArrayList<>();
	}

	public void addPlate(Plate newPlate){
		availablePlates.add(newPlate);
	}

	//Si el dueño quiere remover un plato
	public void removePlate(Plate ptr){
		availablePlates.remove(ptr);
	}

	public void updateMenu(){
		for(int i=0; i< availablePlates.size(); i++){
				if(!availablePlates.get(i).checkAvailability()){
					availablePlates.remove(i);
				}
			}
		}
	

	public boolean isOnMenu(Plate food){
		if (availablePlates.contains(food)){
			return true;
		}
		return false;
	}
	
	public void printMenu(){
		for(Plate e: availablePlates){
			System.out.println(e.getName());
		}
	}



}
