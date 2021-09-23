package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Equipment;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.EquipmentType;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruckManagementSystem;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Menu;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Staff;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Supply;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.SupplyType;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.persistence.PersistenceXStream;

/**
 * Implementation of FoodTruckController.
 * @author Kevin Laframboise
 *
 */
public class FoodTruckControllerAdapter implements FoodTruckController {

	/**
	 * Instance of the food truck management system
	 */
	private FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
	
	@Override
	public void createFoodTruck(String location, Menu menu) throws InvalidInputException {
		
		// Check for invalid arguments
		if(location == null || location.trim().length() == 0) {
			throw new InvalidInputException("Location cannot be empty!");
		}
		if(menu == null || !ftms.getFoodList().contains(menu)) {
			throw new InvalidInputException("The given menu is invalid!");
		}
		
		// Create food truck and add it to system
		FoodTruck ft = new FoodTruck(location, menu);
		ftms.addFoodTruck(ft);
		
		// Save with XStream
		saveModel();
	}


	@Override
	public void changeFoodTruckLocation(FoodTruck foodTruck, String newLocation) throws InvalidInputException {
		
		// Check for invalid arguments
		if(foodTruck == null || !ftms.getFoodTrucks().contains(foodTruck)) {
			throw new InvalidInputException("The given food truck is invalid!");
		}
		if(newLocation == null || newLocation.trim().length() == 0) {
			throw new InvalidInputException("Location cannot be empty!");
		}
		
		// Change location
		foodTruck.setLocation(newLocation);
		
		saveModel();

	}

	@Override
	public void changeFoodTruckMenu(FoodTruck foodTruck, Menu newMenu) throws InvalidInputException {
		
		// Check for invalid arguments
		if(foodTruck == null || !ftms.getFoodTrucks().contains(foodTruck)) {
			throw new InvalidInputException("The given food truck is invalid!");
		}
		if(newMenu == null || !ftms.getFoodList().contains(newMenu)) {
			throw new InvalidInputException("The given menu is invalid!");
		}
		
		// Change to new menu
		foodTruck.setMenu(newMenu);
		
		saveModel();

	}

	@Override
	public void deleteFoodTruck(FoodTruck foodTruck) throws InvalidInputException {
		
		// Check for invalid arguments
		if(foodTruck == null || !ftms.getFoodTrucks().contains(foodTruck)) {
			throw new InvalidInputException("The given food truck is invalid!");
		}
		
		// Delete food truck
		ftms.removeFoodTruck(foodTruck);
		
		saveModel();
	}

	@Override
	public void createEquipment(FoodTruck foodTruck, EquipmentType type, double qty, Date purchaseDate)
			throws InvalidInputException {

		// Check for invalid arguments
		if(foodTruck == null || !ftms.getFoodTrucks().contains(foodTruck)) {
			throw new InvalidInputException("The given food truck is invalid!");
		}
		if(type == null || !ftms.getEquipmentTypes().contains(type)) {
			throw new InvalidInputException("The given equipment type is invalid!");
		}
		if(purchaseDate == null) {
			throw new InvalidInputException("The given date is invalid!");
		}
		if(qty <= 0) {
			throw new InvalidInputException("The given quantity is invalid!");
		}
		
		// Create the equipment and add it to the food truck
		Equipment equipment = new Equipment(qty, purchaseDate, type);
		foodTruck.addEquipment(equipment);
		
		saveModel();
	}

	@Override
	public void removeEquipment(FoodTruck foodTruck, Equipment equipment, double qty) throws InvalidInputException {
		
		// Check for invalid arguments
		if(foodTruck == null || !ftms.getFoodTrucks().contains(foodTruck)) {
			throw new InvalidInputException("The given food truck is invalid!");
		}
		if(equipment == null || !foodTruck.getEquipment().contains(equipment)) {
			throw new InvalidInputException("The given equipment is invalid!");
		}
		if(qty <= 0) {
			throw new InvalidInputException("The given quantity is invalid!");
		}
		// Check that the quantity to remove is smaller or equal to the available quantity
		if(qty > equipment.getQuantity()) {
			throw new InvalidInputException("The given quantity is larger than the remaining quantity of this equipment!");
		}
		
		// Remove the given quantity of equipment
		double newQuantity = equipment.getQuantity() - qty;
		// If the new quantity is 0, remove the equipment from the list
		if(newQuantity == 0) {
			foodTruck.removeEquipment(equipment);
		}
		//else update the remaining quantity
		else {
			equipment.setQuantity(newQuantity);
		}
		
		saveModel();
	}

	@Override
	public void createSupply(FoodTruck foodTruck, SupplyType type, double qty) throws InvalidInputException, DuplicateTypeException {
		
		// Check for invalid arguments
		if (foodTruck == null || !ftms.getFoodTrucks().contains(foodTruck)) {
			throw new InvalidInputException("The given food truck is invalid!");
		}
		if (type == null || !ftms.getSupplyTypes().contains(type)) {
			throw new InvalidInputException("The given supply type is invalid!");
		}
		if (qty < 0) {
			throw new InvalidInputException("The given quantity is invalid!");
		}
		
		// Check for duplicate type
		Iterator<Supply> its = foodTruck.getSupplies().iterator();
		while(its.hasNext()) {
			if(its.next().getSupplyType().getName().equals(type.getName())) {
				throw new DuplicateTypeException("This truck already has " + type.getName() + " in its inventory!");
			}
		}

		// Create the supply and add it to the food truck
		Supply supply = new Supply(qty, type);
		foodTruck.addSupply(supply);

		saveModel();

	}
	
	@Override
	public void removeSupply(FoodTruck foodTruck, Supply supply) throws InvalidInputException {
		
		// Check for invalid arguments
		if (foodTruck == null || !ftms.getFoodTrucks().contains(foodTruck)) {
			throw new InvalidInputException("The given food truck is invalid!");
		}
		if (supply == null || !foodTruck.getSupplies().contains(supply)) {
			throw new InvalidInputException("The given supply is invalid!");
		}
		
		// Remove the supply
		foodTruck.removeSupply(supply);
		
		saveModel();
	}

	@Override
	public void changeSupplyQuantity(Supply supply, double newQuantity) throws InvalidInputException {
		
		// Check for invalid arguments
		if (supply == null) {
			throw new InvalidInputException("The given supply is invalid!");
		}
		if (newQuantity < 0) {
			throw new InvalidInputException("The given quantity is invalid!");
		}
		
		// Set the new quantity
		supply.setQuantity(newQuantity);

		saveModel();
		
	}
	
	
	@Override
	public void changeEquipmentQunatity(Equipment equipment, double newQuantity) throws InvalidInputException {
		
		// Check for invalid arguments
		if (equipment == null) {
			throw new InvalidInputException("The given equipment is invalid!");
		}
		if (newQuantity <= 0) {
			throw new InvalidInputException("The given quantity is invalid!");
		}

		// Set the new quantity
		equipment.setQuantity(newQuantity);

		saveModel();
		
	}
	
	@Override
	public void changeEquipmentPurchaseDate(Equipment equipment, Date newDate) throws InvalidInputException {
		
		// Check for invalid arguments
		if (equipment == null) {
			throw new InvalidInputException("The given equipment is invalid!");
		}
		if (newDate == null) {
			throw new InvalidInputException("The given date is invalid!");
		}
		
		// Set the new date
		equipment.setPurchaseDate(newDate);
		
		saveModel();
		
	}
	
	@Override
	public void use(FoodTruck foodTruck, Supply supply) throws InvalidInputException {
		
		// Check for invalid arguments
		if(foodTruck == null || !ftms.getFoodTrucks().contains(foodTruck)) {
			throw new InvalidInputException("The given food truck is invalid!");
		}
		if (supply == null) {
			throw new InvalidInputException("The given supply is invalid!");
		}
		if (supply.getQuantity() <= 0) {
			throw new InvalidInputException("The given quantity is invalid!");
		}
		// Find the supply object in the truck's supplies with the same type as the given supply object
		Supply truckSupply = findSupplyObject(supply.getSupplyType(), foodTruck.getSupplies());
		
		// Check that a supply was found
		if(truckSupply == null) {
			throw new InvalidInputException("The given food truck does not contain the given supply!");
		}
		
		// Check that the quantity to remove is smaller or equal to the available quantity
		if (supply.getQuantity() > truckSupply.getQuantity()) {
			throw new InvalidInputException("The given quantity is larger than the remaining quantity of this supply!");
		}

		// Remove the given quantity of supply
		double newQuantity = truckSupply.getQuantity() - supply.getQuantity();
		truckSupply.setQuantity(newQuantity);

		saveModel();

	}



	@Override
	public void restock(FoodTruck foodTruck, Supply supply) throws InvalidInputException {

		// Check for invalid arguments
		if (foodTruck == null || !ftms.getFoodTrucks().contains(foodTruck)) {
			throw new InvalidInputException("The given food truck is invalid!");
		}
		if (supply == null) {
			throw new InvalidInputException("The given supply is invalid!");
		}
		if (supply.getQuantity() <= 0) {
			throw new InvalidInputException("The given quantity is invalid!");
		}
		// Find the supply object in the truck's supplies with the same type as
		// the given supply object
		Supply truckSupply = findSupplyObject(supply.getSupplyType(), foodTruck.getSupplies());

		// Check that a supply was found
		if (truckSupply == null) {
			throw new InvalidInputException("The given food truck does not contain the given supply!");
		}

		// Remove the given quantity of supply
		double newQuantity = truckSupply.getQuantity() + supply.getQuantity();
		truckSupply.setQuantity(newQuantity);

		saveModel();

	}

	@Override
	public void assignStaff(FoodTruck foodTruck, Staff staff) throws InvalidInputException {
		
		// Check for invalid argument
		if (foodTruck == null || !ftms.getFoodTrucks().contains(foodTruck)) {
			throw new InvalidInputException("The given food truck is invalid!");
		}
		if (staff == null || !ftms.getStaffs().contains(staff)) {
			throw new InvalidInputException("The given staff is invalid!");
		}
		
		// Add staff to food truck
		foodTruck.addStaff(staff);
		
		saveModel();
	}

	@Override
	public void unassignStaff(FoodTruck foodTruck, Staff staff) throws InvalidInputException {
		
		// Check for invalid argument
		if (foodTruck == null || !ftms.getFoodTrucks().contains(foodTruck)) {
			throw new InvalidInputException("The given food truck is invalid!");
		}
		if (staff == null || !ftms.getStaffs().contains(staff)) {
			throw new InvalidInputException("The given staff is invalid!");
		}
		
		// Remove staff from food truck
		foodTruck.removeStaff(staff);
		
		saveModel();

	}

	/**
	 * Saves model to XML using XStream
	 */
	private void saveModel() {
		PersistenceXStream.saveToXMLwithXStream(ftms);
	}

	/**
	 * This method finds the first occurrence of a Supply object with the given supply type in the given list.
	 * @param supplyType to be located in the list.
	 * @param supplies from which the SupplyType has to be located.
	 * @return the first occurrence of a Supply object with the given SupplyType. Null if an object with
	 * given type is not found.
	 */
	private Supply findSupplyObject(SupplyType supplyType, List<Supply> supplies) {
		
		// Iterate through all supplies of the list.
		Supply current;
		Iterator<Supply> it = supplies.iterator();
		while(it.hasNext()) {
			current = it.next();
			// Check if name of current supply is what we are looking for
			if(current.getSupplyType().getName().equals(supplyType.getName())) {
				return current;
			}
		}
		// If not found, return null
		return null;
	}



	
}
