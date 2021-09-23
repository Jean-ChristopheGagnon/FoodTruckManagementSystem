package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller;

/**
 * This exception is meant to be thrown when the user tries to add a Supply of some type to a list
 * of supplies that already contain a Supply item with the same type. Instead, the quantity of
 * the same type should be updated.
 * @author Kevin Laframboise
 *
 */
public class DuplicateTypeException extends Exception {

	/**
	 * Create an exception with the given message.
	 * @param msg of the exception.
	 */
	public DuplicateTypeException(String msg){
		super(msg);
	}

}
