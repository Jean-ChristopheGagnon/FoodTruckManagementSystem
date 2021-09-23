package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller;

import java.sql.Date;
import java.sql.Time;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.MenuItem;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Order;

/**
 * Controls create and update operations on Order and related objects.
 * @author Kevin Laframboise
 *
 */
public interface OrderController {
	
	/**
	 * Creates an order and marks it has placed at the give time. The order is set to unpaid and unserved by default.
	 * @param foodTruck at which the order was placed.
	 * @param orderDate at which the order was placed.
	 * @param orderTime time at which the order was placed.
	 * @throws InvalidInputException if the given time is invalid.
	 */
	public void createOrder(FoodTruck foodTruck, Date orderDate, Time orderTime) throws InvalidInputException;
	
	/**
	 * Marks the given order as served.
	 * @param order to be marked as served.
	 * @throws InvalidInputException if the given order is invalid.
	 */
	public void serve(Order order) throws InvalidInputException;
	
	/**
	 * Marks the given order as paid.
	 * @param order to be marked as paid.
	 * @throws InvalidInputException if the given order is invalid.
	 */
	public void markAsPaid(Order order) throws InvalidInputException;

	/**
	 * Adds the given menu item to the order.
	 * @param foodTruck at which the order was placed.
	 * @param order to which an item has to be added.
	 * @param item to be added to given order.
	 * @throws InvalidInputException if the given food truck, order or item is invalid. If the given item is not 
	 * available at the given food truck. If the given order was not placed at the given food truck.
	 */
	public void addItem(FoodTruck foodTruck, Order order, MenuItem item) throws InvalidInputException;
	
	/**
	 * Removes the given item from the given order.
	 * @param order from which an item has to be removed.
	 * @param item to be removed.
	 * @throws InvalidInputException if the item or order are invalid. If the given item is not on the given order.
	 */
	public void removeItem(Order order, MenuItem item) throws InvalidInputException;
	
	/**
	 * Computes the total price of the order.
	 * @param order for which the total has to be computed.
	 * @return the total of the order, in cents.
	 * @throws InvalidInputException if the given order is invalid.
	 */
	public int getOrderTotal(Order order) throws InvalidInputException;
	
	/**
	 * Cancels the given order (i.e. removes it from the given food truck's records).
	 * @param foodTruck at which the order was placed.
	 * @param order to be cancelled.
	 * @throws InvalidInputException if the given order or food truck don't exist. If the order was not originally
	 * placed at the given food truck.
	 */
	public void cancelOrder(FoodTruck foodTruck, Order order) throws InvalidInputException;
	
}
