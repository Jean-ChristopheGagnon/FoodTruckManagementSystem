package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.Iterator;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruckManagementSystem;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.MenuItem;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Order;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.persistence.PersistenceXStream;

/**
 * Implementation of OrderController.
 * @author Kevin Laframboise
 *
 */
public class OrderControllerAdapter implements OrderController {
	
	/**
	 * Instance of the food truck management system
	 */
	private FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();

	@Override
	public void createOrder(FoodTruck foodTruck, Date orderDate, Time orderTime) throws InvalidInputException {
		
		// Check for invalid arguments
		if(foodTruck == null || !ftms.getFoodTrucks().contains(foodTruck)) {
			throw new InvalidInputException("The given food truck is invalid!");
		}
		if(orderDate == null) {
			throw new InvalidInputException("The given date is invalid!");
		}
		if(orderTime == null) {
			throw new InvalidInputException("The given time is invalid!");
		}
		
		// Create order and add it to given food truck
		Order order = new Order(orderDate, orderTime, false, false);
		foodTruck.addOrder(order);
		
		// Save with XStream 
		saveModel();

	}


	@Override
	public void serve(Order order) throws InvalidInputException {
		
		// Check for invalid arguments
		if(order == null) {
			throw new InvalidInputException("The given order is invalid!");
		}
		
		// Set the order as served
		order.setServed(true);
		
		saveModel();

	}

	@Override
	public void markAsPaid(Order order) throws InvalidInputException {

		// Check for invalid arguments
		if (order == null) {
			throw new InvalidInputException("The given order is invalid!");
		}

		// Set the order as served
		order.setPaid(true);

		saveModel();

	}

	@Override
	public void addItem(FoodTruck foodTruck, Order order, MenuItem item) throws InvalidInputException {
		
		// Check for invalid arguments
		if(foodTruck == null || !ftms.getFoodTrucks().contains(foodTruck)) {
			throw new InvalidInputException("The given food truck is invalid!");
		}
		if(order == null) {
			throw new InvalidInputException("The given order is invalid!");
		}
		if(item == null) {
			throw new InvalidInputException("The given item is invalid!");
		}
		// Check that the given order was placed at the given food truck
		if(!foodTruck.getOrders().contains(order)) {
			throw new InvalidInputException("The given order was not placed at the given food truck!");
		}
		// Check that the item is available at the given food truck
		Iterator<MenuItem> it = foodTruck.getMenu().getMenuItems().iterator();
		MenuItem current;
		boolean found = false;
		while(it.hasNext()) {
			current = it.next();
			if(current.getName().equals(item.getName()) && current.getPrice() == item.getPrice()) {
				found = true;
				break;
			}
		}
		if(!found) throw new InvalidInputException("The given menu item is not the given food truck's supplies!");
		
		// Add item to order
		order.addMenuItem(item);
		
		saveModel();

	}

	@Override
	public void removeItem(Order order, MenuItem item) throws InvalidInputException {
		
		// Check for invalid arguments
		if(order == null) {
			throw new InvalidInputException("The given order is invalid!");
		}
		if(item == null) {
			throw new InvalidInputException("The given item is invalid!");
		}
		// Check that there is at least one instance of the given item in the order
		if(!order.getMenuItems().contains(item)) {
			throw new InvalidInputException("The given item is not on the given order!");
		}
		
		// Remove the item
		order.removeMenuItem(item);
		
		saveModel();
		
	}

	@Override
	public int getOrderTotal(Order order) throws InvalidInputException {
		
		// Check for invalid arguments
		if(order == null) {
			throw new InvalidInputException("The given order is invalid!");
		}
		
		// Compute the total
		Iterator<MenuItem> it = order.getMenuItems().iterator();
		int sum = 0;
		while(it.hasNext()) {
			sum += it.next().getPrice();
		}
		
		return sum;
	}

	@Override
	public void cancelOrder(FoodTruck foodTruck, Order order) throws InvalidInputException {
		
		// Check for invalid arguments
		if (foodTruck == null || !ftms.getFoodTrucks().contains(foodTruck)) {
			throw new InvalidInputException("The given food truck is invalid!");
		}
		if (order == null) {
			throw new InvalidInputException("The given order is invalid!");
		}
		// Check that the given order was placed at the given food truck
		if (!foodTruck.getOrders().contains(order)) {
			throw new InvalidInputException("The given order was not placed at the given food truck!");
		}
		
		// Remove the order from food truck
		foodTruck.removeOrder(order);
		
		saveModel();

	}

	/**
	 * Saves model to XML using XStream
	 */
	private void saveModel() {
		PersistenceXStream.saveToXMLwithXStream(ftms);
	}
}
