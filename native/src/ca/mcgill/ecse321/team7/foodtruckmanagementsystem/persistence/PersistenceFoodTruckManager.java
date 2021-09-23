package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.persistence;

import java.util.Iterator;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Equipment;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.EquipmentType;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruckManagementSystem;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Menu;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.MenuItem;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Order;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Staff;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Supply;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.SupplyType;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.WorkShift;

/**
 * Sets all aliases for XStream and loads model.
 * @author Kevin Laframboise
 *
 */
public class PersistenceFoodTruckManager {
	
	/**
	 * Initializes all aliases used with XStream and sets the filename to the given filename.
	 * @param filename under which the model is saved.
	 */
	public static void initializeXStream(String filename){
		PersistenceXStream.setFilename(filename);
		PersistenceXStream.setAlias("food_truck_manager", FoodTruckManagementSystem.class);
		PersistenceXStream.setAlias("menu", Menu.class);
		PersistenceXStream.setAlias("menu_item", MenuItem.class);
		PersistenceXStream.setAlias("staff", Staff.class);
		PersistenceXStream.setAlias("work_shift", WorkShift.class);
		PersistenceXStream.setAlias("equipment", Equipment.class);
		PersistenceXStream.setAlias("supply", Supply.class);
		PersistenceXStream.setAlias("equipment_type", EquipmentType.class);
		PersistenceXStream.setAlias("supply_type", SupplyType.class);
		PersistenceXStream.setAlias("food_truck", FoodTruck.class);
		PersistenceXStream.setAlias("order", Order.class);
	}
	
	/**
	 * Loads the model from the xml file and copies all the data to the current instance of the management system.
	 * @param filename under which the model is saved.
	 */
	public static void loadTruckManagerModel(String filename){
		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
		PersistenceFoodTruckManager.initializeXStream(filename);
		FoodTruckManagementSystem ftms2 = (FoodTruckManagementSystem) PersistenceXStream.loadFromXMLwithXStream();
		if(ftms2 != null){
			
			// Load all staff
			Iterator<Staff> sIt = ftms2.getStaffs().iterator();
			while(sIt.hasNext()) {
				ftms.addStaff(sIt.next());
			}
			
			// Load all equipment types
			Iterator<EquipmentType> etIt = ftms2.getEquipmentTypes().iterator();
			while(etIt.hasNext()) {
				ftms.addEquipmentType(etIt.next());
			}
			
			// Load all supply types
			Iterator<SupplyType> stIt = ftms2.getSupplyTypes().iterator();
			while(stIt.hasNext()) {
				ftms.addSupplyType(stIt.next());
			}
			
			//load all menus
			Iterator<Menu> mIt = ftms2.getFoodList().iterator();
			while(mIt.hasNext()){
				ftms.addFoodList(mIt.next());
			}
			
			// Load all food trucks
			Iterator<FoodTruck> ftIt = ftms2.getFoodTrucks().iterator();
			while(ftIt.hasNext()) {
				ftms.addFoodTruck(ftIt.next());
			}
		}
	}

}
