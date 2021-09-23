package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.application;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.persistence.PersistenceFoodTruckManager;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.view.FoodTruckManagerPage;

/**
 * Run from this class.
 * @author Kevin Laframboise
 *
 */
public class FoodTruckManager {

	public static void main(String[] args) {
		
		// Load model
		String filename = "foodtruckmanager.xml";
		PersistenceFoodTruckManager.loadTruckManagerModel(filename);
		
		
		java.awt.EventQueue.invokeLater(new Runnable(){
			public void run(){
				new FoodTruckManagerPage().setVisible(true);
			}
		});

	}

}
