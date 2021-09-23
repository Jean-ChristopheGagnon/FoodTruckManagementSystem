package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.EquipmentType;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.SupplyType;

/**
 * Handles create and edit operations for supply and equipment type objects.
 * @author Kevin Laframboise
 *
 */
public interface SETypesController {

	/**
	 * Creates a supply type with the given name.
	 * @param name of the supply type to be created.
	 * @throws InvalidInputException if the given name is invalid
	 * @throws DuplicateTypeException if a supply type with the same name already exists
	 */
	public void createSupplyType(String name) throws InvalidInputException, DuplicateTypeException;
	
	/**
	 * Changes the name of the given supply type to the given new name.
	 * @param type for which name has to be changed
	 * @param newName of the type
	 * @throws DuplicateTypeException if a supply type with the same name already exists
	 * @throws InvalidInputException if the given name is invalid or the given SupplyType
	 * 								 is invalid or doesn't exist in the management system
	 */
	public void changeSupplyTypeName(SupplyType type, String newName) throws InvalidInputException, DuplicateTypeException;
	
	/**
	 * Deletes the given supply type from the management system. All Supply
	 * objects that are of this type are also removed from the system.
	 * @param type to be deleted
	 * @throws InvalidInputException if the given SupplyType is invalid or doesn't exist
	 * 								 in the management system 
	 */
	public void deleteSupplyType(SupplyType type) throws InvalidInputException;
	
	/**
	 * Creates an equipment type with the given name.
	 * @param name of the equipment type to be created	
	 * @throws DuplicateTypeException if an equipment type with the same name already exists
	 * @throws InvalidInputException if the given name is invalid or the given EquipmentType
	 * 								 is invalid or doesn't exist in the management system
	 */
	public void createEquipmentType(String name) throws InvalidInputException, DuplicateTypeException;
	
	/**
	 * Changes the name of the given equipment type to the given new name.
	 * @param type for which the name has to be changed
	 * @param newName of the type
	 * @throws DuplicateTypeException if an equipment type with the same name already exists
	 * @throws InvalidInputException if the given name is invalid or the given EquipmentType
	 * 								 is invalid or doesn't exist in the management system
	 */
	public void changeEquipmentTypeName(EquipmentType type, String newName) throws InvalidInputException, DuplicateTypeException;
	
	/**
	 * Deletes the given equipment type from the management system. All Equipment
	 * objects that are of this type are also removed from the system.
	 * @param type to be deleted
	 * @throws InvalidInputException if the given EquipmentType is invalid or doesn't exist
	 * 								 in the management system 
	 */
	public void deleteEquipmentType(EquipmentType type) throws InvalidInputException;
}
