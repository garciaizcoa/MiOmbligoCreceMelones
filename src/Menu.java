import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Menu {

	private HashSet<Plate> availablePlates; 
	public Frame frame;

	public Menu(){
		availablePlates = new HashSet<>();		
	}

	public void addPlate(Plate newPlate){
		if(!isOnMenu(newPlate)) { 
			availablePlates.add(newPlate); 
		}	
	}

	//Si el dueño quiere remover un plato
	public void removePlate(Plate ptr){
		availablePlates.remove(ptr);
	}

	public void updateMenu(){
//		for(int i=0; i< availablePlates.size(); i++){
//			if(!availablePlates.get(i).checkAvailability()){
//				availablePlates.remove(i);
//			}
//		}
		for(Plate pt: availablePlates){
			if(!pt.checkAvailability()){
				availablePlates.remove(pt);
			}
		}
	}


	public boolean isOnMenu(Plate food){ 
//		for(int i=0;i<availablePlates.size();i++) {
//			if(food.getName().equals(availablePlates.get(i).getName())) {
//				return true;
//			}
//		}
		if(availablePlates.contains(food)) return true;
		return false;
	}

	public HashSet<Plate> getAvailablePlates() {
		for(Plate plt: frame.getAddPlateMenu().getAllPlates()) {
			if(plt.checkAvailability()) {
				availablePlates.add(plt);
			}
		}
		return availablePlates;
	}
	
	public Plate getPlate(Plate plate) {
		for(Plate plato: availablePlates) {
			if(plate.equals(plato)) {
				return plato;
			}
		}
		System.out.println("plate doesnt exist");
		return null; ////////no me  gusta
	}
	
	public Plate getPlate (String name) {
		for(Plate plato: availablePlates) {
			if(name==plato.getName())
				return plato;
		}
		System.out.println("plate ("+name+") doesnt exist");
		return null;
	}
	
	
	public void printMenu(){
		for(Plate e: availablePlates){
			System.out.println(e.getName());
		}
	}
	
	public void setFrame(Frame frame) {
		this.frame = frame;
	}

}