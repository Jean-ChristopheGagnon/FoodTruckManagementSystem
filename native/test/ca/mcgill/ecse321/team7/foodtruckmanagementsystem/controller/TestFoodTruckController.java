package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruckManagementSystem;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Supply;

/**
 * Test suite for the food truck controller.
 * @author Rami Djema
 *
 */
public class TestFoodTruckController {

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

	@Test
	public void testFoodTruckControllerInvalidInputs() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem
				.getInstance();

		assertEquals(ftms.getFoodTrucks().size(), 0);

		FoodTruckController ftc = new FoodTruckControllerAdapter();
		MenuController mc = new MenuControllerAdapter();
		String error = "";

		try {
			mc.createMenu("Mex");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		try {
			ftc.createFoodTruck("", ftms.getFoodList(0));
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(error, "Location cannot be empty!");

		try {
			ftc.createFoodTruck("Ft", null);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(error, "The given menu is invalid!");

		try {
			ftc.createFoodTruck("ft", ftms.getFoodList(0));
		} catch (InvalidInputException e1) {

			e1.printStackTrace();
		}

		try {
			ftc.changeFoodTruckLocation(ftms.getFoodTruck(0), "");
		} catch (InvalidInputException e) {

			error = e.getMessage();
		}

		assertEquals(error, "Location cannot be empty!");

		try {
			ftc.changeFoodTruckLocation(null, "Brossard");
		} catch (InvalidInputException e) {

			error = e.getMessage();
		}

		assertEquals(error, "The given food truck is invalid!");

		try {
			ftc.changeFoodTruckMenu(ftms.getFoodTruck(0), null);
		} catch (InvalidInputException e) {

			error = e.getMessage();
		}

		assertEquals(error, "The given menu is invalid!");

		try {
			ftc.changeFoodTruckMenu(null, ftms.getFoodList(0));
		} catch (InvalidInputException e) {

			error = e.getMessage();
		}

		assertEquals(error, "The given food truck is invalid!");

		try {
			ftc.deleteFoodTruck(null);
		} catch (InvalidInputException e) {

			error = e.getMessage();
		}

		assertEquals(error, "The given food truck is invalid!");

		SETypesControllerAdapter set = new SETypesControllerAdapter();
		Date date = new Date(2011, 11, 11);
		int quantity = 11;
		try {
			set.createEquipmentType("oven");
		} catch (InvalidInputException e1) {

			e1.printStackTrace();
		} catch (DuplicateTypeException e1) {

			e1.printStackTrace();
		}

		try {
			ftc.createEquipment(ftms.getFoodTruck(0), ftms.getEquipmentType(0),
					quantity, null);
		} catch (InvalidInputException e) {

			error = e.getMessage();
		}

		assertEquals(error, "The given date is invalid!");

		try {
			ftc.createEquipment(ftms.getFoodTruck(0), ftms.getEquipmentType(0),
					-1, date);
		} catch (InvalidInputException e) {

			error = e.getMessage();
		}

		assertEquals(error, "The given quantity is invalid!");

		try {
			ftc.createEquipment(null, ftms.getEquipmentType(0), quantity, date);
		} catch (InvalidInputException e) {

			error = e.getMessage();
		}

		assertEquals(error, "The given food truck is invalid!");

		try {
			ftc.createEquipment(ftms.getFoodTruck(0), null, quantity, date);
		} catch (InvalidInputException e) {

			error = e.getMessage();
		}

		assertEquals(error, "The given equipment type is invalid!");

		try {
			ftc.createEquipment(ftms.getFoodTruck(0), ftms.getEquipmentType(0),
					quantity, date);
		} catch (InvalidInputException e) {

			e.printStackTrace();
		}

		try {
			ftc.removeEquipment(ftms.getFoodTruck(0), ftms.getFoodTruck(0)
					.getEquipment(0), 1000);
		} catch (InvalidInputException e) {

			error = e.getMessage();
		}

		assertEquals(error,
				"The given quantity is larger than the remaining quantity of this equipment!");

		try {
			set.createSupplyType("Bun");
		} catch (InvalidInputException | DuplicateTypeException e) {

			e.printStackTrace();
		}
		try {
			ftc.createSupply(null, ftms.getSupplyType(0), quantity);
		} catch (InvalidInputException | DuplicateTypeException e) {

			error = e.getMessage();
		}

		assertEquals(error, "The given food truck is invalid!");

		try {
			ftc.createSupply(ftms.getFoodTruck(0), null, quantity);
		} catch (InvalidInputException | DuplicateTypeException e) {

			error = e.getMessage();
		}

		assertEquals(error, "The given supply type is invalid!");

		try {
			ftc.createSupply(ftms.getFoodTruck(0), ftms.getSupplyType(0), -1);
		} catch (InvalidInputException | DuplicateTypeException e) {

			error = e.getMessage();
		}

		assertEquals(error, "The given quantity is invalid!");

		try {
			ftc.createSupply(ftms.getFoodTruck(0), ftms.getSupplyType(0),
					quantity);
		} catch (InvalidInputException e) {

			e.printStackTrace();
		} catch (DuplicateTypeException e) {

			e.printStackTrace();
		}

		try {
			ftc.use(ftms.getFoodTruck(0), new Supply(1000, ftms.getSupplyType(0)));
		} catch (InvalidInputException e) {

			error = e.getMessage();
		}

		assertEquals(error,
				"The given quantity is larger than the remaining quantity of this supply!");

		try {
			ftc.restock(ftms.getFoodTruck(0), null);
		} catch (InvalidInputException e) {

			error = e.getMessage();
		}

		assertEquals(error, "The given supply is invalid!");

		try {
			ftc.restock(ftms.getFoodTruck(0), new Supply(-1, ftms.getSupplyType(0)));
		} catch (InvalidInputException e) {

			error = e.getMessage();
		}

		assertEquals(error, "The given quantity is invalid!");

		StaffControllerAdapter sc = new StaffControllerAdapter();

		try {
			sc.createStaffMember("John", "writingUnitTests");
		} catch (InvalidInputException e) {

			e.printStackTrace();
		}

		try {
			ftc.assignStaff(null, ftms.getStaff(0));
		} catch (InvalidInputException e) {

			error = e.getMessage();
		}

		assertEquals(error, "The given food truck is invalid!");

		try {
			ftc.assignStaff(ftms.getFoodTruck(0), null);
		} catch (InvalidInputException e) {

			error = e.getMessage();
		}

		assertEquals(error, "The given staff is invalid!");

		try {
			ftc.assignStaff(ftms.getFoodTruck(0), ftms.getStaff(0));
		} catch (InvalidInputException e) {

			e.printStackTrace();
		}

		try {
			ftc.unassignStaff(null, ftms.getStaff(0));
		} catch (InvalidInputException e) {

			error = e.getMessage();
		}

		assertEquals(error, "The given food truck is invalid!");

		try {
			ftc.unassignStaff(ftms.getFoodTruck(0), null);
		} catch (InvalidInputException e) {

			error = e.getMessage();
		}

		assertEquals(error, "The given staff is invalid!");

	}

	@Test
	public void testCreateFoodTruck() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem
				.getInstance();

		assertEquals(ftms.getFoodTrucks().size(), 0);

		createFoodTruckWithMenu();

		assertEquals(ftms.getFoodTrucks().size(), 1);
		assertEquals(ftms.getFoodTruck(0).getLocation(), "Location");
		assertEquals(ftms.getFoodTruck(0).getMenu().getName(), "Menu");

	}

	@Test
	public void testChangeFoodTruckLocation() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem
				.getInstance();
		FoodTruckControllerAdapter ftc = new FoodTruckControllerAdapter();

		assertEquals(ftms.getFoodTrucks().size(), 0);

		createFoodTruckWithMenu();

		assertEquals(ftms.getFoodTrucks().size(), 1);

		try {
			ftc.changeFoodTruckLocation(ftms.getFoodTruck(0), "newLocation");
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodTruck(0).getLocation(), "newLocation");

	}

	@Test
	public void testChangeFoodTruckMenu() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem
				.getInstance();
		MenuControllerAdapter mc = new MenuControllerAdapter();
		FoodTruckControllerAdapter ftc = new FoodTruckControllerAdapter();

		assertEquals(ftms.getFoodTrucks().size(), 0);

		createFoodTruckWithMenu();

		assertEquals(ftms.getFoodTrucks().size(), 1);

		try {
			mc.createMenu("Mexican");
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		try {
			ftc.changeFoodTruckMenu(ftms.getFoodTruck(0), ftms.getFoodList(1));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodTruck(0).getMenu().getName(), "Mexican");

	}

	@Test
	public void testDeleteFoodTruck() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem
				.getInstance();
		FoodTruckControllerAdapter ftc = new FoodTruckControllerAdapter();

		assertEquals(ftms.getFoodTrucks().size(), 0);

		createFoodTruckWithMenu();

		assertEquals(ftms.getFoodTrucks().size(), 1);

		try {
			ftc.deleteFoodTruck(ftms.getFoodTruck(0));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodTrucks().size(), 0);

	}

	@SuppressWarnings("deprecation")
	@Test
	public void testCreateEquipment() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem
				.getInstance();
		FoodTruckControllerAdapter ftc = new FoodTruckControllerAdapter();
		SETypesControllerAdapter set = new SETypesControllerAdapter();

		assertEquals(ftms.getFoodTrucks().size(), 0);

		createFoodTruckWithMenu();

		assertEquals(ftms.getFoodTrucks().size(), 1);

		Date date = new Date(2011, 11, 11);
		int quantity = 11;

		try {
			set.createEquipmentType("Oven");
		} catch (InvalidInputException e) {
			e.printStackTrace();
		} catch (DuplicateTypeException e) {
			e.printStackTrace();
		}

		try {
			ftc.createEquipment(ftms.getFoodTruck(0), ftms.getEquipmentType(0),
					quantity, date);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodTruck(0).getEquipment(0).getEquipmentType()
				.getName(), "Oven");
		assertEquals(ftms.getFoodTruck(0).getEquipment(0).getPurchaseDate(),
				date);
		assertEquals((int) ftms.getFoodTruck(0).getEquipment(0).getQuantity(),
				quantity);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testRemoveEquipment() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem
				.getInstance();
		FoodTruckControllerAdapter ftc = new FoodTruckControllerAdapter();
		SETypesControllerAdapter set = new SETypesControllerAdapter();

		assertEquals(ftms.getFoodTrucks().size(), 0);

		createFoodTruckWithMenu();

		assertEquals(ftms.getFoodTrucks().size(), 1);

		Date date = new Date(2011, 11, 11);
		int quantity = 11;

		try {
			set.createEquipmentType("Oven");
		} catch (InvalidInputException e) {

			e.printStackTrace();
		} catch (DuplicateTypeException e) {

			e.printStackTrace();
		}

		try {
			ftc.createEquipment(ftms.getFoodTruck(0), ftms.getEquipmentType(0),
					quantity, date);
		} catch (InvalidInputException e) {

			e.printStackTrace();
		}

		assertEquals(ftms.getFoodTruck(0).getEquipment(0).getEquipmentType()
				.getName(), "Oven");
		assertEquals(ftms.getFoodTruck(0).getEquipment(0).getPurchaseDate(),
				date);
		assertEquals((int) ftms.getFoodTruck(0).getEquipment(0).getQuantity(),
				quantity);

		try {
			ftc.removeEquipment(ftms.getFoodTruck(0), ftms.getFoodTruck(0)
					.getEquipment(0), 11);
		} catch (InvalidInputException e) {

			e.printStackTrace();
		}

		assertEquals(ftms.getFoodTruck(0).getEquipment().size(), 0);

	}

	@Test
	public void testCreateSupply() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem
				.getInstance();
		FoodTruckControllerAdapter ftc = new FoodTruckControllerAdapter();
		SETypesControllerAdapter set = new SETypesControllerAdapter();

		assertEquals(ftms.getFoodTrucks().size(), 0);

		createFoodTruckWithMenu();

		assertEquals(ftms.getFoodTrucks().size(), 1);

		int quantity = 11;

		try {
			set.createSupplyType("Bun");
		} catch (InvalidInputException e) {

			e.printStackTrace();
		} catch (DuplicateTypeException e) {

			e.printStackTrace();
		}

		try {
			ftc.createSupply(ftms.getFoodTruck(0), ftms.getSupplyType(0),
					quantity);
		} catch (InvalidInputException | DuplicateTypeException e) {

			e.printStackTrace();
		}

		assertEquals(ftms.getFoodTruck(0).getSupply(0).getSupplyType()
				.getName(), "Bun");
		assertEquals((int) ftms.getFoodTruck(0).getSupply(0).getQuantity(),
				quantity);
	}

	@Test
	public void testRemoveSupply() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem
				.getInstance();
		FoodTruckControllerAdapter ftc = new FoodTruckControllerAdapter();
		SETypesControllerAdapter set = new SETypesControllerAdapter();

		assertEquals(ftms.getFoodTrucks().size(), 0);

		createFoodTruckWithMenu();

		assertEquals(ftms.getFoodTrucks().size(), 1);

		int quantity = 11;

		try {
			set.createSupplyType("Bun");
		} catch (InvalidInputException e) {

			e.printStackTrace();
		} catch (DuplicateTypeException e) {

			e.printStackTrace();
		}

		try {
			ftc.createSupply(ftms.getFoodTruck(0), ftms.getSupplyType(0),
					quantity);
		} catch (InvalidInputException | DuplicateTypeException e) {

			e.printStackTrace();
		}

		assertEquals(ftms.getFoodTruck(0).getSupply(0).getSupplyType()
				.getName(), "Bun");
		assertEquals((int) ftms.getFoodTruck(0).getSupply(0).getQuantity(),
				quantity);

		try {
			ftc.removeSupply(ftms.getFoodTruck(0), ftms.getFoodTruck(0)
					.getSupply(0));
		} catch (InvalidInputException e) {

			e.printStackTrace();
		}

		assertEquals(ftms.getFoodTruck(0).getSupplies().size(), 0);

	}

	@Test
	public void testUse() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem
				.getInstance();
		FoodTruckControllerAdapter ftc = new FoodTruckControllerAdapter();
		SETypesControllerAdapter set = new SETypesControllerAdapter();

		assertEquals(ftms.getFoodTrucks().size(), 0);

		createFoodTruckWithMenu();

		assertEquals(ftms.getFoodTrucks().size(), 1);

		int quantity = 11;

		try {
			set.createSupplyType("Bun");
		} catch (InvalidInputException e) {

			e.printStackTrace();
		} catch (DuplicateTypeException e) {

			e.printStackTrace();
		}

		try {
			ftc.createSupply(ftms.getFoodTruck(0), ftms.getSupplyType(0),
					quantity);
		} catch (InvalidInputException | DuplicateTypeException e) {

			e.printStackTrace();
		}

		assertEquals(ftms.getFoodTruck(0).getSupply(0).getSupplyType()
				.getName(), "Bun");
		assertEquals((int) ftms.getFoodTruck(0).getSupply(0).getQuantity(),
				quantity);

		try {
			ftc.use(ftms.getFoodTruck(0), new Supply(1, ftms.getSupplyType(0)));
		} catch (InvalidInputException e) {

			e.printStackTrace();
		}

		assertEquals((int) ftms.getFoodTruck(0).getSupply(0).getQuantity(), 10);
	}

	@Test
	public void testRestock() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem
				.getInstance();
		FoodTruckControllerAdapter ftc = new FoodTruckControllerAdapter();
		SETypesControllerAdapter set = new SETypesControllerAdapter();

		assertEquals(ftms.getFoodTrucks().size(), 0);

		createFoodTruckWithMenu();

		assertEquals(ftms.getFoodTrucks().size(), 1);

		int quantity = 11;

		try {
			set.createSupplyType("Bun");
		} catch (InvalidInputException e) {

			e.printStackTrace();
		} catch (DuplicateTypeException e) {

			e.printStackTrace();
		}

		try {
			ftc.createSupply(ftms.getFoodTruck(0), ftms.getSupplyType(0),
					quantity);
		} catch (InvalidInputException | DuplicateTypeException e) {

			e.printStackTrace();
		}

		assertEquals(ftms.getFoodTruck(0).getSupply(0).getSupplyType()
				.getName(), "Bun");
		assertEquals((int) ftms.getFoodTruck(0).getSupply(0).getQuantity(),
				quantity);

		try {
			ftc.restock(ftms.getFoodTruck(0), new Supply(1, ftms.getSupplyType(0)));
		} catch (InvalidInputException e) {

			e.printStackTrace();
		}

		assertEquals((int) ftms.getFoodTruck(0).getSupply(0).getQuantity(), 12);
	}

	@Test
	public void testAssignStaff() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem
				.getInstance();
		StaffControllerAdapter sc = new StaffControllerAdapter();
		FoodTruckController ftc = new FoodTruckControllerAdapter();

		assertEquals(ftms.getFoodTrucks().size(), 0);

		createFoodTruckWithMenu();

		assertEquals(ftms.getFoodTrucks().size(), 1);
		assertEquals(ftms.getFoodTruck(0).getLocation(), "Location");
		assertEquals(ftms.getFoodTruck(0).getMenu().getName(), "Menu");

		try {
			sc.createStaffMember("Bob", "Job");
		} catch (InvalidInputException e) {

			e.printStackTrace();
		}

		try {
			ftc.assignStaff(ftms.getFoodTruck(0), ftms.getStaff(0));
		} catch (InvalidInputException e) {

			e.printStackTrace();
		}

		assertEquals(ftms.getFoodTruck(0).getStaff(0).getName(), "Bob");
		assertEquals(ftms.getFoodTruck(0).getStaff(0).getJob(), "Job");

	}

	@Test
	public void testUnassignStaff() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem
				.getInstance();
		StaffControllerAdapter sc = new StaffControllerAdapter();
		FoodTruckController ftc = new FoodTruckControllerAdapter();

		assertEquals(ftms.getFoodTrucks().size(), 0);

		createFoodTruckWithMenu();

		assertEquals(ftms.getFoodTrucks().size(), 1);
		assertEquals(ftms.getFoodTruck(0).getLocation(), "Location");
		assertEquals(ftms.getFoodTruck(0).getMenu().getName(), "Menu");

		try {
			sc.createStaffMember("Bob", "Job");
		} catch (InvalidInputException e) {

			e.printStackTrace();
		}

		try {
			ftc.assignStaff(ftms.getFoodTruck(0), ftms.getStaff(0));
		} catch (InvalidInputException e) {

			e.printStackTrace();
		}

		try {
			ftc.unassignStaff(ftms.getFoodTruck(0), ftms.getFoodTruck(0)
					.getStaff(0));
		} catch (InvalidInputException e) {

			e.printStackTrace();
		}

		assertEquals(ftms.getFoodTruck(0).getStaffs().size(), 0);

	}

	private void createFoodTruckWithMenu() {

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
			ftc.createFoodTruck("Location", ftms.getFoodList(0));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

	}

}
