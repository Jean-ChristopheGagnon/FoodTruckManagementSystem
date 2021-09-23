package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller;

import java.util.Iterator;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruckManagementSystem;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Menu;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.MenuItem;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Supply;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.SupplyType;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.persistence.PersistenceXStream;

/**
 * Implementation of MenuController.
 * @author Kevin Laframboise
 *
 */
public class MenuControllerAdapter implements MenuController {

	/**
	 * Instance of the food truck management system
	 */
	private FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();

	public void createMenu(String name) throws InvalidInputException {

		// check for invalid name
		if (name == null || name.trim().length() == 0) {
			throw new InvalidInputException("Menu name cannot be empty!");
		}

		ftms.addFoodList(new Menu(name));
		saveModel();
	}


	public void changeMenuName(Menu menu, String newName) throws InvalidInputException {

		// check for invalid new name
		if (newName == null || newName.trim().length() == 0) {
			throw new InvalidInputException("Menu name cannot be empty!");
		}

		// check for invalid menu selection
		int indexOfMenu = ftms.indexOfFoodList(menu);
		if (indexOfMenu < 0) {
			throw new InvalidInputException("Invalid menu selected!");
		}

		ftms.getFoodList(indexOfMenu).setName(newName);
		saveModel();
	}

	public void deleteMenu(Menu menu) throws InvalidInputException {

		// check for invalid menu selection
		int indexOfMenu = ftms.indexOfFoodList(menu);
		if (indexOfMenu < 0) {
			throw new InvalidInputException("Invalid menu selected!");
		}

		ftms.removeFoodList(menu);
		saveModel();

	}

	public void createMenuItem(Menu menu, String name, int price) throws InvalidInputException {

		// check for invalid menu selection
		int indexOfMenu = ftms.indexOfFoodList(menu);
		if (indexOfMenu < 0) {
			throw new InvalidInputException("Invalid menu selected!");
		}

		// check for invalid new name
		if (name == null || name.trim().length() == 0) {
			throw new InvalidInputException("Menu item name cannot be empty!");
		}

		// check for invalid price
		if (price <= 0) {
			throw new InvalidInputException("Price cannot be negative or zero!");
		}

		menu.addMenuItem(new MenuItem(name, price));
		saveModel();
	}

	public void changeMenuItemName(Menu menu, MenuItem menuItem, String newName) throws InvalidInputException {

		// check for invalid menu selection
		int indexOfMenu = ftms.indexOfFoodList(menu);
		if (indexOfMenu < 0) {
			throw new InvalidInputException("Invalid menu selected!");
		}

		// check for invalid menu item selection
		int indexOfMenuItem = menu.indexOfMenuItem(menuItem);
		if (indexOfMenuItem < 0) {
			throw new InvalidInputException("Invalid menu item selected!");
		}

		// check for invalid new name
		if (newName == null || newName.trim().length() == 0) {
			throw new InvalidInputException("Menu item name cannot be empty!");
		}

		menu.getMenuItem(indexOfMenuItem).setName(newName);
		saveModel();

	}

	public void changeMenuItemPrice(Menu menu, MenuItem menuItem, int newPrice) throws InvalidInputException {

		// check for invalid menu selection
		int indexOfMenu = ftms.indexOfFoodList(menu);
		if (indexOfMenu < 0) {
			throw new InvalidInputException("Invalid menu selected!");
		}

		// check for invalid menu item selection
		int indexOfMenuItem = menu.indexOfMenuItem(menuItem);
		if (indexOfMenuItem < 0) {
			throw new InvalidInputException("Invalid menu item selected!");
		}

		// check for invalid new name
		if (newPrice < 0) {
			throw new InvalidInputException("Price cannot be negative or zero!");
		}

		menu.getMenuItem(indexOfMenuItem).setPrice(newPrice);
		saveModel();

	}

	public void deleteMenuItem(Menu menu, MenuItem menuItem) throws InvalidInputException {

		// check for invalid menu selection
		int indexOfMenu = ftms.indexOfFoodList(menu);
		if (indexOfMenu < 0) {
			throw new InvalidInputException("Invalid menu selected!");
		}

		// check for invalid menu item selection
		int indexOfMenuItem = menu.indexOfMenuItem(menuItem);
		if (indexOfMenuItem < 0) {
			throw new InvalidInputException("Invalid menu item selected!");
		}
		
		menu.removeMenuItem(menuItem);
		saveModel();

	}

	@Override
	public void addIngredientToItem(MenuItem item, SupplyType type, double qty)
			throws InvalidInputException, DuplicateTypeException {
		
		// Check for invalid arguments
		if(item == null) {
			throw new InvalidInputException("The given menu item is invalid!");
		}
		if(type == null || !ftms.getSupplyTypes().contains(type)) {
			throw new InvalidInputException("The given supply type is invalid!");
		}
		if (qty <= 0) {
			throw new InvalidInputException("Quantity cannot be less than or equal to 0!");
		}
		
		// Check for duplicate type in given item's supplies list
		Iterator<Supply> it = item.getSupplies().iterator();
		Supply current;
		while(it.hasNext()) {
			current = it.next();
			// If the supply types object are the same or they have the same name, throw a DuplicateTypeException
			if(current.getSupplyType().equals(type) || current.getSupplyType().getName().equals(type.getName())) {
				throw new DuplicateTypeException("Supply type " + type.getName() + " is already in the supplies list of this item!");
			}
		}
		
		// Create supply object from given type and qty and add it to the item
		Supply supply = new Supply(qty, type);
		item.addSupply(supply);
		
		// Save with XStream
		saveModel();
	}
	
	
	@Override
	public Supply getSupplyObject(MenuItem item, SupplyType type) throws InvalidInputException {
		
		// Check for invalid arguments
		if(item == null) {
			throw new InvalidInputException("The given menu item is invalid!");
		}
		if(type == null || !ftms.getSupplyTypes().contains(type)) {
			throw new InvalidInputException("The given supply type is invalid!");
		}
		
		// Iterate over supplies list to find the given type
		Iterator<Supply> it = item.getSupplies().iterator();
		Supply current;
		while (it.hasNext()) {
			current = it.next();
			// If the supply types object are the same or they have the same name, return it
			if (current.getSupplyType().equals(type) || current.getSupplyType().getName().equals(type.getName())) {
				return current;
			}
		}
		
		// A Supply with given type was not found, return null by default
		return null;
	}
	
	
	@Override
	public void changeIngredientQuantity(Supply supply, double newQty) throws InvalidInputException {
		
		// Check for invalid arguments
		if(supply == null) {
			throw new InvalidInputException("The given supply is invalid!");
		}
		if(newQty <= 0) {
			throw new InvalidInputException("Quantity cannot be less than or equal to 0!");
		}
		
		// Change the quantity
		supply.setQuantity(newQty);
		
		// Save with XStream
		saveModel();
	}
	
	
	@Override
	public void removeIngredient(MenuItem item, Supply supply) throws InvalidInputException {
		
		// Check for invalid arguments
		if (item == null) {
			throw new InvalidInputException("The given menu item is invalid!");
		}
		if (supply == null) {
			throw new InvalidInputException("The given supply is invalid!");
		}
		
		// Remove supply from item
		item.removeSupply(supply);
		
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
