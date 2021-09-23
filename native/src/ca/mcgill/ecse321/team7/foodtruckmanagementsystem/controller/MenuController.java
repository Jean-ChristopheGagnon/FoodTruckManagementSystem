package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Menu;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.MenuItem;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Supply;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.SupplyType;

/**
 * Handles create and update operations for Menu objects.
 * @author Kevin Laframboise
 *
 */
public interface MenuController {
	
	/**
	 * Creates a menu with given name. 
	 * @param name of the new menu.
	 * @throws InvalidInputException if the given name is invalid.
	 */
	public void createMenu(String name) throws InvalidInputException;
	
	/**
	 * Changes the name of the specified menu to the given name.
	 * @param menu for which the name has to be changed.
	 * @param newName of the menu.
	 * @throws InvalidInputException if given name is invalid.
	 */
	public void changeMenuName(Menu menu, String newName) throws InvalidInputException;
	
	/**
	 * Deletes the given menu from the management system. 
	 * @param menu to be deleted
	 * @throws InvalidInputException if the given menu is invalid/does not exist.
	 */
	public void deleteMenu(Menu menu) throws InvalidInputException;
	
	/**
	 * Creates a menu item with the given name and price on the given menu. 
	 * @param menu to which the new item belongs.
	 * @param name of the new item.
	 * @param price of the new item.
	 * @throws InvalidInputException if given name is invalid or if given price is negative or if given menu is invalid/does
	 * not exist.
	 */
	public void createMenuItem(Menu menu, String name, int price) throws InvalidInputException;

	/**
	 * Changes the name of the given menu item on the given menu.
	 * @param menu in which the item to update is located.
	 * @param menuItem to be updated.
	 * @param newName of the item.
	 * @throws InvalidInputException if given name is invalid or if given menu is invalid/does not exist or if the
	 * given menu item is invalid/does not exist.
	 */
	public void changeMenuItemName(Menu menu, MenuItem menuItem, String newName) throws InvalidInputException;
	
	/**
	 * Changes the price of the given menu item on the given menu.
	 * @param menu in which the item is located.
	 * @param menuItem for which the price has to be changed.
	 * @param newPrice of the item.
	 * @throws InvalidInputException if given price is negative or if given menu is invalid/does not exist or if the
	 * given menu item is invalid/does not exist.
	 */
	public void changeMenuItemPrice(Menu menu, MenuItem menuItem, int newPrice) throws InvalidInputException;
	
	/**
	 * Removes the given menu item from the given menu. Throws an exception 
	 * @param menu in which the item is located.
	 * @param menuItem to be deleted.
	 * @throws InvalidInputException if given menu is invalid/does not exist or if the given menu item
	 * is invalid/does not exist.
	 */
	public void deleteMenuItem(Menu menu, MenuItem menuItem) throws InvalidInputException;
	
	/**
	 * Creates a Supply object with the given supply type and quantity and adds it to the list of supplies
	 * for the given menu item.
	 * @param item to which the ingredient is added.
	 * @param type of the ingredient to add.
	 * @param qty required to produce item.
	 * @throws InvalidInputException if item or type are invalid. If qty is smaller than or equal to 0.
	 * @throws DuplicateTypeException if a supply with the given type is already in the item's list of supplies.
	 */
	public void addIngredientToItem(MenuItem item, SupplyType type, double qty) throws InvalidInputException, DuplicateTypeException;
	
	/**
	 * Returns a reference to the Supply object of the given type that is in the given item's supplies list.
	 * @param item the menu item.
	 * @param type for which to return the Supply object.
	 * @return a reference to the Supply object of the given type in the item's supplies list. Returns null
	 * if the Supply object of given type is not found.
	 * @throws InvalidInputException if the item or type are invalid.
	 */
	public Supply getSupplyObject(MenuItem item, SupplyType type) throws InvalidInputException;
	
	/**
	 * Changes the quantity associated to the given supply object.
	 * @param supply for which the quantity has to be changed.
	 * @param newQty of the supply.
	 * @throws InvalidInputException if the supply is invalid or if the quantity is less than or equal to 0.
	 */
	public void changeIngredientQuantity(Supply supply, double newQty) throws InvalidInputException;
	
	/**
	 * Removes the given Supply from the list of supplies of the given item.
	 * @param item from which the supply is removed.
	 * @param supply to be removed.
	 * @throws InvalidInputException if the item or supply are invalid.
	 */
	public void removeIngredient(MenuItem item, Supply supply) throws InvalidInputException;
	
}
