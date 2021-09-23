package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller;

import java.sql.Date;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Equipment;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.EquipmentType;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Menu;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Staff;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Supply;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.SupplyType;

/**
 * Handles create and update operations for FoodTruck and related objects.
 * @author Kevin Laframboise
 *
 */
public interface FoodTruckController {

	/**
	 * Creates a food truck with the given location and given menu.
	 * @param location of the food truck.
	 * @param menu to be assigned to the food truck.
	 * @throws InvalidInputException if the location string is invalid. If the given menu does not exist.
	 */
	public void createFoodTruck(String location, Menu menu) throws InvalidInputException;
	
	/**
	 * Changes the location of the given food truck.
	 * @param foodTruck to be relocated.
	 * @param newLocation of the food truck.
	 * @throws InvalidInputException if the new location string is invalid. If the given food truck does not exist.
	 */
	public void changeFoodTruckLocation(FoodTruck foodTruck, String newLocation) throws InvalidInputException;
	
	/**
	 * Changes the menu of the given food truck.
	 * @param foodTruck for which that menu has to be changed.
	 * @param newMenu of the food truck.
	 * @throws InvalidInputException if the given food truck or menu don't exist.
	 */
	public void changeFoodTruckMenu(FoodTruck foodTruck, Menu newMenu) throws InvalidInputException;
	
	/**
	 * Removes the given food truck from the management system. All order, equipment and supplies associated
	 * with this food truck are also deleted.
	 * @param foodTruck to be deleted.
	 * @throws InvalidInputException if the given food truck does not exist.
	 */
	public void deleteFoodTruck(FoodTruck foodTruck) throws InvalidInputException;
	
	/**
	 * Creates an equipment that is available to the given food truck in the given quantity.
	 * @param foodTruck to which the equipment was added.
	 * @param type of the equipment.
	 * @param qty available to the food truck.
	 * @param purchaseDate of the equipment.
	 * @throws InvalidInputException if the given food truck or type don't exist. If the given quantity is invalid
	 * (<= 0). If the given purchase date is invalid.
	 */
	public void createEquipment(FoodTruck foodTruck, EquipmentType type, double qty, Date purchaseDate) throws InvalidInputException;
	
	/**
	 * Changes the quantity associated with the given equipment.
	 * @param equipment of which the quantity has to be changed.
	 * @param newQuantity of the given equipment.
	 * @throws InvalidInputException if the given equipment does not exist. If the given quantity is invalid (< 0).
	 */
	public void changeEquipmentQunatity(Equipment equipment, double newQuantity) throws InvalidInputException; 
	
	/**
	 * Changes the purchase date of the given equipment.
	 * @param equipment for which the date has to be changed.
	 * @param newDate new purchase date of the equipment.
	 * @throws InvalidInputException if the equipment or date are invalid.
	 */
	public void changeEquipmentPurchaseDate(Equipment equipment, Date newDate) throws InvalidInputException;
	
	/**
	 * Removes the given equipment from the given food truck in the given quantity.
	 * @param foodTruck from which the equipment was removed.
	 * @param equipment that was removed from the food truck.
	 * @param qty removed from the food truck.
	 * @throws InvalidInputException if the given food truck or equipment don't exist. If the quantity is invalid
	 * (<= 0). If the quantity removed is greater than the quantity available.
	 */
	public void removeEquipment(FoodTruck foodTruck, Equipment equipment, double qty) throws InvalidInputException;
	
	/**
	 * Creates a supply that is available to the given food truck in the given quantity.
	 * @param foodTruck to which the supply was added.
	 * @param type of the supply.
	 * @param qty available to the food truck.
	 * @throws InvalidInputException if the given food truck or type don't exist. If the given quantity is invalid
	 * (< 0).
	 */
	public void createSupply(FoodTruck foodTruck, SupplyType type, double qty) throws InvalidInputException, DuplicateTypeException;
	
	/**
	 * Removes the supply from the given food truck.
	 * @param foodTruck from which the supply is removed.
	 * @param supply to be removed.
	 * @throws InvalidInputException if the given food truck or supply are invalid.
	 */
	public void removeSupply(FoodTruck foodTruck, Supply supply) throws InvalidInputException;
	
	/**
	 * Changes the quantity associated with the given supply.
	 * @param supply of which the quantity has to be changed.
	 * @param newQuantity of the given supply.
	 * @throws InvalidInputException if the given supply does not exist. If the given quantity is invalid (< 0).
	 */
	public void changeSupplyQuantity(Supply supply, double newQuantity) throws InvalidInputException;
	
	
	/**
	 * Reduces the available quantity of the given supply by the given amount.
	 * @param foodTruck from which the supply was used.
	 * @param supply object with the type of supply that was used and how much of it.
	 * @throws InvalidInputException if the given supply does not exist. If the given quantity is invalid (<= 0).
	 * If the quantity used is greater than quantity available.
	 */
	public void use(FoodTruck foodTruck, Supply supply) throws InvalidInputException;
	
	/**
	 * Adds to the available quantity of the given supply the given quantity.
	 * @param foodTruck to which the supply was restocked.
	 * @param supply object with the type of supply to be restocked and how much of it.
	 * @throws InvalidInputException if the given Supply does not exist. If the given quantity is invalid (<= 0).
	 */
	public void restock(FoodTruck foodTruck, Supply supply) throws InvalidInputException;
	
	/**
	 * Assigns the given staff member to the given food truck.
	 * @param foodTruck to which the staff was assigned.
	 * @param staff to be assigned to the food truck.
	 * @throws InvalidInputException if the given food truck or staff are invalid.
	 */
	public void assignStaff(FoodTruck foodTruck, Staff staff) throws InvalidInputException;
	
	/**
	 * Unassigns the given staff member from the given food truck.
	 * @param foodTruck from which the staff was unassigned.
	 * @param staff to be unassigned from the food truck.
	 * @throws InvalidInputException if the given food truck or staff are invalid.
	 */
	public void unassignStaff(FoodTruck foodTruck, Staff staff) throws InvalidInputException;

	
}
