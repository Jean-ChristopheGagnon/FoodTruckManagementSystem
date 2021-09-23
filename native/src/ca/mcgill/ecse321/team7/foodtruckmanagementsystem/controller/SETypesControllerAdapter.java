package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller;

import java.util.ArrayList;
import java.util.Iterator;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Equipment;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.EquipmentType;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruckManagementSystem;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Menu;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.MenuItem;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Supply;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.SupplyType;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.persistence.PersistenceXStream;

/**
 * Implementation of SETypesController.
 * @author Kevin Laframboise
 *
 */
public class SETypesControllerAdapter implements SETypesController {

	/**
	 * Instance of the food truck management system
	 */
	private FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
	
	@Override
	public void createSupplyType(String name) throws InvalidInputException, DuplicateTypeException {
		
		// Check for invalid argument
		if(name == null || name.trim().length() == 0) {
			throw new InvalidInputException("Name cannot be empty!");
		}
		
		// Check that this Supply Type doesn't already exist
		Iterator<SupplyType> it = ftms.getSupplyTypes().iterator();
		while(it.hasNext()) {
			if(it.next().getName().equals(name)) {
				throw new DuplicateTypeException("Supply type " + name + " already exists.");
			}
		}
		
		//create supply type and add it to the system
		SupplyType type = new SupplyType(name);
		ftms.addSupplyType(type);
		
		//save with XStream
		saveModel();
	}


	@Override
	public void changeSupplyTypeName(SupplyType type, String newName) throws InvalidInputException, DuplicateTypeException {
		
		// Check for invalid argument
		if (newName == null || newName.trim().length() == 0) {
			throw new InvalidInputException("Name cannot be empty!");
		}
		if(type == null || !ftms.getSupplyTypes().contains(type)) {
			throw new InvalidInputException("Given type does not exist!");
		}

		// Check that this Supply Type doesn't already exist
		Iterator<SupplyType> it = ftms.getSupplyTypes().iterator();
		while (it.hasNext()) {
			if (it.next().getName().equals(newName)) {
				throw new DuplicateTypeException("Supply type " + newName + " already exists.");
			}
		}
		
		// Change name of given type
		type.setName(newName);

		saveModel();
	}

	@Override
	public void deleteSupplyType(SupplyType type) throws InvalidInputException {
		
		// Check for invalid arguments
		if(type == null || !ftms.getSupplyTypes().contains(type)) {
			throw new InvalidInputException("The given type is invalid!");
		}
		
		// Prepare iterators for removal from food truck inventories
		Iterator<FoodTruck> itt = ftms.getFoodTrucks().iterator();
		Iterator<Supply> its;

		// Initialize list of supplies to be removed
		ArrayList<Supply> suppliesToRemove = new ArrayList<Supply>();
		// Current supply
		Supply supply;
		// Current food truck
		FoodTruck ft;
		// Iterate through all food trucks and supplies
		while(itt.hasNext()) {
			ft = itt.next();
			// Get iterator for this food truck's supplies
			its = ft.getSupplies().iterator();
			// Iterate through all supplies of the current truck
			while(its.hasNext()) {
				supply = its.next();
				if(supply.getSupplyType().equals(type)) {
					// Add the supply to the list of supplies to remove
					suppliesToRemove.add(supply);
				}
				// Initialize the iterator for the supplies to be removed
				its = suppliesToRemove.iterator();
				// Iterate through the list of supplies to be removed
				while(its.hasNext()) {
					// Remove the supply from the food truck
					ft.removeSupply(its.next());
				}
				
				// Reset the list of supplies to be removed for next truck
				suppliesToRemove.clear();
			}
		}
		
		// Prepare iterators for removal from menu items
		Iterator<Menu> itm = ftms.getFoodList().iterator();
		Iterator<MenuItem> iti;
		// Current menu
		Menu menu;
		// Current menu item
		MenuItem item;
		
		// Iterate through all menus to find menu items to find all instances of given type
		while(itm.hasNext()) {
			menu = itm.next();
			// Get iterator for this menu's items
			iti = menu.getMenuItems().iterator();
			while(iti.hasNext()) {
				item = iti.next();
				// Get iterator for this item's supplies
				its = item.getSupplies().iterator();
				while(its.hasNext()) {
					supply = its.next();
					if(supply.getSupplyType().equals(type)) {
						// Add the supply to the list of supplies to remove
						suppliesToRemove.add(supply);
					}
					// Initialize the iterator for the supplies to be removed
					its = suppliesToRemove.iterator();
					// Iterate through the list of supplies to be removed
					while(its.hasNext()) {
						// Remove the supply from the food truck
						item.removeSupply(its.next());
					}
					
					// Reset the list of supplies to be removed for next truck
					suppliesToRemove.clear();
				}
			}
		}
		
		// Remove supply type from system
		ftms.removeSupplyType(type);
		
		// Save with XStream
		saveModel();

	}

	@Override
	public void createEquipmentType(String name) throws InvalidInputException, DuplicateTypeException {
		
		// Check for invalid argument
		if (name == null || name.trim().length() == 0) {
			throw new InvalidInputException("Name cannot be empty!");
		}

		// Check that this Supply Type doesn't already exist
		Iterator<EquipmentType> it = ftms.getEquipmentTypes().iterator();
		while (it.hasNext()) {
			if (it.next().getName().equals(name)) {
				throw new DuplicateTypeException("Equipment type " + name + " already exists.");
			}
		}
		
		// Create Equipment type and add it to system
		EquipmentType type = new EquipmentType(name);
		ftms.addEquipmentType(type);
		
		saveModel();
	}

	@Override
	public void changeEquipmentTypeName(EquipmentType type, String newName) throws InvalidInputException, DuplicateTypeException {

		// Check for invalid argument
		if (newName == null || newName.trim().length() == 0) {
			throw new InvalidInputException("Name cannot be empty!");
		}
		if(type == null || !ftms.getEquipmentTypes().contains(type)) {
			throw new InvalidInputException("Given type does not exist!");
		}

		// Check that this Supply Type doesn't already exist
		Iterator<EquipmentType> it = ftms.getEquipmentTypes().iterator();
		while (it.hasNext()) {
			if (it.next().getName().equals(newName)) {
				throw new DuplicateTypeException("Equipment type " + newName + " already exists.");
			}
		}
		
		// Change name of given type
		type.setName(newName);
		
		saveModel();

	}

	@Override
	public void deleteEquipmentType(EquipmentType type) throws InvalidInputException {
		
		// Check for invalid arguments
		if (type == null || !ftms.getEquipmentTypes().contains(type)) {
			throw new InvalidInputException("The given type is invalid!");
		}

		// Prepare iterators
		Iterator<FoodTruck> truckIt = ftms.getFoodTrucks().iterator();
		Iterator<Equipment> equipmentIt;
		Iterator<Equipment> equipmentToRemoveIt;
		// Initialize list of equipment to be removed
		ArrayList<Equipment> equipmentToRemove = new ArrayList<Equipment>();
		// Current equipment
		Equipment equipment;
		// Current food truck
		FoodTruck ft;
		// Iterate through all food trucks and equipment
		while (truckIt.hasNext()) {
			ft = truckIt.next();
			// Get iterator for this food truck's equipment
			equipmentIt = ft.getEquipment().iterator();
			// Iterate through all equipment of the current truck
			while (equipmentIt.hasNext()) {
				equipment = equipmentIt.next();
				if (equipment.getEquipmentType().equals(type)) {
					// Add the equipment to the list of equipment to remove
					equipmentToRemove.add(equipment);
				}
				// Initialize the iterator for the equipment to be removed
				equipmentToRemoveIt = equipmentToRemove.iterator();
				// Iterate through the list of equipment to be removed
				while (equipmentToRemoveIt.hasNext()) {
					// Remove the equipment from the food truck
					ft.removeEquipment(equipmentToRemoveIt.next());
				}

				// Reset the list of equipment to be removed for next truck
				equipmentToRemove.clear();
			}
		}

		// Remove equipment type from system
		ftms.removeEquipmentType(type);

		// Save with XStream
		saveModel();

	}

	/**
	 * Saves model to XML using XStream
	 */
	private void saveModel() {
		PersistenceXStream.saveToXMLwithXStream(ftms);
	}
	
}
