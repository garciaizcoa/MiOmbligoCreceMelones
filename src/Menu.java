import java.util.ArrayList;

public class Menu {

	private ArrayList<Plate> availablePlates; 

	public Menu(){
		availablePlates = new ArrayList<>();
	}

	public void addPlate(Plate newPlate){
		if(!isOnMenu(newPlate)) 
			availablePlates.add(newPlate); //no sirve
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
//		if (availablePlates.contains(food)){      ///////no sirve
//			return true;
//		}
//		return false;
		for(int i=0;i<availablePlates.size();i++) {
			if(food.getName().equals(availablePlates.get(i).getName())) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Plate> getAvailablePlates() {
		return availablePlates;
	}
	
	public Plate getPlate(String name) {
		for(Plate plate: availablePlates) {
			if(plate.getName()==name) {
				return plate;
			}
		}
		System.out.println("plate doesnt exist");
		return null; ////////no me  gusta
	}
	
	public void printMenu(){
		for(Plate e: availablePlates){
			System.out.println(e.getName());
		}
	}

}