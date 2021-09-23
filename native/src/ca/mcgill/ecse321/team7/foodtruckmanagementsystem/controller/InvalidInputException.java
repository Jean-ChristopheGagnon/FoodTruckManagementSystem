package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller;

/**
 * This exception is meant to be thrown when an invalid input is provided by the user and passed
 * as an argument to a controller.
 * @author Kevin Laframboise
 *
 */
public class InvalidInputException extends Exception {

	/**
	 * Create an exception with the given message.
	 * @param msg of the exception.
	 */
	public InvalidInputException(String msg) {
		super(msg);
	}

}
