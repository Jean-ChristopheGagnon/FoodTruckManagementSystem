package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller;

import static org.junit.Assert.*;

import java.sql.Time;
import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruckManagementSystem;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Menu;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Order;

/**
 * Test suite for the order controller.
 * @author Rami Djema
 *
 */
public class TestOrderController {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		FoodTruckManagementSystem ftms = FoodTruckManagementSystem
				.getInstance();
		ftms.delete();
		// clear all
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testCreateOrder() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem
				.getInstance();
		createAFoodTruckWithMenu();
		String error = "";

		assertEquals(ftms.getFoodTruck(0).getOrders().size(), 0);

		Time time1 = new Time(5, 5, 5);
		Date date1 = new Date(2011, 11, 11);

		OrderControllerAdapter oc = new OrderControllerAdapter();

		try {
			oc.createOrder(null, date1, time1);
		} catch (InvalidInputException e) {

			error = e.getMessage();
		}

		assertEquals(error, "The given food truck is invalid!");

		try {
			oc.createOrder(ftms.getFoodTruck(0), null, time1);
		} catch (InvalidInputException e) {

			error = e.getMessage();
		}

		assertEquals(error, "The given date is invalid!");

		try {
			oc.createOrder(ftms.getFoodTruck(0), date1, null);
		} catch (InvalidInputException e) {

			error = e.getMessage();
		}

		assertEquals(error, "The given time is invalid!");

		try {
			oc.createOrder(ftms.getFoodTruck(0), date1, time1);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodTruck(0).getOrder(0).getOrderDate(), date1);
		assertEquals(ftms.getFoodTruck(0).getOrder(0).getOrderTime(), time1);

	}

	@Test
	public void testServe() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem
				.getInstance();
		createAFoodTruckWithMenu();
		String error = "";
		assertEquals(ftms.getFoodTruck(0).getOrders().size(), 0);

		Time time1 = new Time(5, 5, 5);
		Date date1 = new Date(2011, 11, 11);

		OrderControllerAdapter oc = new OrderControllerAdapter();

		try {
			oc.createOrder(ftms.getFoodTruck(0), date1, time1);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodTruck(0).getOrder(0).isServed(), false);

		try {
			oc.serve(null);
		} catch (InvalidInputException e) {

			error = e.getMessage();
		}

		assertEquals(error, "The given order is invalid!");

		try {
			oc.serve(ftms.getFoodTruck(0).getOrder(0));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodTruck(0).getOrder(0).isServed(), true);

	}

	@Test
	public void testMarkAsPaid() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem
				.getInstance();
		createAFoodTruckWithMenu();
		String error = "";
		assertEquals(ftms.getFoodTruck(0).getOrders().size(), 0);

		Time time1 = new Time(5, 5, 5);
		Date date1 = new Date(2011, 11, 11);

		OrderControllerAdapter oc = new OrderControllerAdapter();

		try {
			oc.createOrder(ftms.getFoodTruck(0), date1, time1);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodTruck(0).getOrder(0).isPaid(), false);

		try {
			oc.markAsPaid(null);
		} catch (InvalidInputException e) {

			error = e.getMessage();
		}

		assertEquals(error, "The given order is invalid!");

		try {
			oc.markAsPaid(ftms.getFoodTruck(0).getOrder(0));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodTruck(0).getOrder(0).isPaid(), true);

	}

	@Test
	public void testAddItem() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem
				.getInstance();
		createAFoodTruckWithMenu();
		String error = "";
		assertEquals(ftms.getFoodTruck(0).getOrders().size(), 0);

		Time time1 = new Time(5, 5, 5);
		Date date1 = new Date(2011, 11, 11);

		OrderControllerAdapter oc = new OrderControllerAdapter();

		try {
			oc.createOrder(ftms.getFoodTruck(0), date1, time1);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodTruck(0).getOrder(0).getMenuItems().size(), 0);

		MenuController mc = new MenuControllerAdapter();

		try {
			mc.createMenuItem(ftms.getFoodList(0), "Item1", 5);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		try {
			oc.addItem(null, ftms.getFoodTruck(0).getOrder(0), ftms
					.getFoodList(0).getMenuItem(0));
		} catch (InvalidInputException e1) {

			error = e1.getMessage();
		}

		assertEquals(error, "The given food truck is invalid!");

		try {
			oc.addItem(ftms.getFoodTruck(0), null, ftms.getFoodList(0)
					.getMenuItem(0));
		} catch (InvalidInputException e1) {

			error = e1.getMessage();
		}

		assertEquals(error, "The given order is invalid!");

		try {
			oc.addItem(ftms.getFoodTruck(0), ftms.getFoodTruck(0).getOrder(0),
					null);
		} catch (InvalidInputException e1) {

			error = e1.getMessage();
		}

		assertEquals(error, "The given item is invalid!");

		try {
			oc.addItem(ftms.getFoodTruck(0), ftms.getFoodTruck(0).getOrder(0),
					ftms.getFoodList(0).getMenuItem(0));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodTruck(0).getOrder(0).getMenuItem(0).getName(),
				"Item1");
		assertEquals(
				ftms.getFoodTruck(0).getOrder(0).getMenuItem(0).getPrice(), 5);
		assertEquals(ftms.getFoodTruck(0).getOrder(0).getMenuItems().size(), 1);

	}

	@Test
	public void testRemoveItem() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem
				.getInstance();
		createAFoodTruckWithMenu();
		String error = "";

		assertEquals(ftms.getFoodTruck(0).getOrders().size(), 0);

		Time time1 = new Time(5, 5, 5);
		Date date1 = new Date(2011, 11, 11);

		OrderControllerAdapter oc = new OrderControllerAdapter();

		try {
			oc.createOrder(ftms.getFoodTruck(0), date1, time1);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodTruck(0).getOrder(0).getMenuItems().size(), 0);

		MenuController mc = new MenuControllerAdapter();

		try {
			oc.addItem(ftms.getFoodTruck(0), ftms.getFoodTruck(0).getOrder(0),
					ftms.getFoodList(0).getMenuItem(0));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodTruck(0).getOrder(0).getMenuItem(0).getName(),
				"Item1");
		assertEquals(
				ftms.getFoodTruck(0).getOrder(0).getMenuItem(0).getPrice(), 5);

		try {
			oc.removeItem(null, ftms.getFoodTruck(0).getOrder(0).getMenuItem(0));
		} catch (InvalidInputException e1) {

			error = e1.getMessage();
		}

		assertEquals(error, "The given order is invalid!");

		try {
			oc.removeItem(ftms.getFoodTruck(0).getOrder(0), null);
		} catch (InvalidInputException e1) {

			error = e1.getMessage();
		}

		assertEquals(error, "The given item is invalid!");

		try {
			oc.removeItem(ftms.getFoodTruck(0).getOrder(0), ftms
					.getFoodTruck(0).getOrder(0).getMenuItem(0));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodTruck(0).getOrder(0).getMenuItems().size(), 0);

	}

	@Test
	public void testGetOrderTotal() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem
				.getInstance();
		createAFoodTruckWithMenu();
		String error = "";
		assertEquals(ftms.getFoodTruck(0).getOrders().size(), 0);

		Time time1 = new Time(5, 5, 5);
		Date date1 = new Date(2011, 11, 11);

		OrderControllerAdapter oc = new OrderControllerAdapter();

		try {
			oc.createOrder(ftms.getFoodTruck(0), date1, time1);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodTruck(0).getOrder(0).getMenuItems().size(), 0);

		MenuController mc = new MenuControllerAdapter();

		try {
			oc.addItem(ftms.getFoodTruck(0), ftms.getFoodTruck(0).getOrder(0),
					ftms.getFoodList(0).getMenuItem(0));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		try {
			oc.addItem(ftms.getFoodTruck(0), ftms.getFoodTruck(0).getOrder(0),
					ftms.getFoodList(0).getMenuItem(1));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodTruck(0).getOrder(0).getMenuItems().size(), 2);

		int total = 0;

		try {
			total = oc.getOrderTotal(null);
		} catch (InvalidInputException e1) {

			error = e1.getMessage();
		}

		assertEquals(error, "The given order is invalid!");

		try {
			total = oc.getOrderTotal(ftms.getFoodTruck(0).getOrder(0));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(total, 15);

	}

	@Test
	public void cancelOrder() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem
				.getInstance();
		createAFoodTruckWithMenu();
		String error = "";
		assertEquals(ftms.getFoodTruck(0).getOrders().size(), 0);

		Time time1 = new Time(5, 5, 5);
		Date date1 = new Date(2011, 11, 11);

		OrderControllerAdapter oc = new OrderControllerAdapter();

		try {
			oc.createOrder(ftms.getFoodTruck(0), date1, time1);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodTruck(0).getOrders().size(), 1);

		try {
			oc.cancelOrder(null, ftms.getFoodTruck(0).getOrder(0));
		} catch (InvalidInputException e1) {

			error = e1.getMessage();
		}

		assertEquals(error, "The given food truck is invalid!");

		try {
			oc.cancelOrder(ftms.getFoodTruck(0), null);
		} catch (InvalidInputException e1) {

			error = e1.getMessage();
		}

		assertEquals(error, "The given order is invalid!");

		try {
			oc.cancelOrder(ftms.getFoodTruck(0),
					ftms.getFoodTruck(0).getOrder(0));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodTruck(0).getOrders().size(), 0);

	}

	private void createAFoodTruckWithMenu() {
		FoodTruckManagementSystem ftms = FoodTruckManagementSystem
				.getInstance();
		FoodTruckController ftc = new FoodTruckControllerAdapter();
		MenuController mc = new MenuControllerAdapter();

		try {
			mc.createMenu("Menu");
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		try {
			mc.createMenuItem(ftms.getFoodList(0), "Item1", 5);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		try {
			mc.createMenuItem(ftms.getFoodList(0), "Item2", 10);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		try {
			ftc.createFoodTruck("Location", ftms.getFoodList(0));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
	}

}
