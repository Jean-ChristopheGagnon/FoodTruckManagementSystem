package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruckManagementSystem;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Menu;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.MenuItem;

/**
 * Test suite for the menu controller.
 * @author Kevin
 *
 */
public class TestMenuController {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		// clear all
		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
		ftms.delete();
	}

	@Test
	public void testCreateMenu() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
		assertEquals(ftms.getFoodList().size(), 0);

		String name = "Menu 1";
		MenuControllerAdapter mc = new MenuControllerAdapter();

		try {
			mc.createMenu(name);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodList().size(), 1);
		assertEquals(ftms.getFoodList(0).getName(), name);

	}

	@Test
	public void testCreateMenuItem() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
		assertEquals(ftms.getFoodList().size(), 0);

		String name = "Menu Item 1";
		int price = 200;
		MenuControllerAdapter mc = new MenuControllerAdapter();

		try {
			mc.createMenu("Menu");
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodList(0).getMenuItems().size(), 0);

		try {
			mc.createMenuItem(ftms.getFoodList(0), name, price);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodList(0).getMenuItems().size(), 1);
		assertEquals(ftms.getFoodList(0).getMenuItem(0).getName(), name);
		assertEquals(ftms.getFoodList(0).getMenuItem(0).getPrice(), price);
	}

	@Test
	public void testChangeMenuName() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
		assertEquals(ftms.getFoodList().size(), 0);

		String name = "Menu 1";
		MenuControllerAdapter mc = new MenuControllerAdapter();

		try {
			mc.createMenu(name);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodList().size(), 1);
		assertEquals(ftms.getFoodList(0).getName(), name);

		name = "Menu 2";

		try {
			mc.changeMenuName(ftms.getFoodList(0), name);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodList().size(), 1);
		assertEquals(ftms.getFoodList(0).getName(), name);

	}

	@Test
	public void testChangeMenuItemName() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
		assertEquals(ftms.getFoodList().size(), 0);

		MenuControllerAdapter mc = new MenuControllerAdapter();

		try {
			mc.createMenu("Menu 1");
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		String name = "Menu Item 1";

		try {
			mc.createMenuItem(ftms.getFoodList(0), name, 1);
		} catch (InvalidInputException e1) {
			e1.printStackTrace();
		}

		assertEquals(ftms.getFoodList(0).getMenuItems().size(), 1);
		assertEquals(ftms.getFoodList(0).getMenuItem(0).getName(), name);

		name = "Menu Item 2";

		try {
			mc.changeMenuItemName(ftms.getFoodList(0), ftms.getFoodList(0).getMenuItem(0), name);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodList(0).getMenuItems().size(), 1);
		assertEquals(ftms.getFoodList(0).getMenuItem(0).getName(), name);

	}

	@Test
	public void testDeleteMenu() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
		assertEquals(ftms.getFoodList().size(), 0);

		String name = "Menu 1";
		MenuControllerAdapter mc = new MenuControllerAdapter();

		try {
			mc.createMenu(name);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodList().size(), 1);

		try {
			mc.deleteMenu(ftms.getFoodList(0));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodList().size(), 0);

	}

	@Test
	public void testInvalidMenu() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
		assertEquals(ftms.getFoodList().size(), 0);

		String name = "Menu 1";
		String error = "";
		MenuControllerAdapter mc = new MenuControllerAdapter();

		try {
			mc.createMenu(name);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodList().size(), 1);

		// delete menu, invalid menu
		try {
			mc.deleteMenu(new Menu("Test"));
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(ftms.getFoodList().size(), 1);
		assertEquals(error, "Invalid menu selected!");

		// change menu name, invalid menu
		error = "";
		try {
			mc.changeMenuName(new Menu("Test"), "Menu");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(ftms.getFoodList().size(), 1);
		assertEquals(error, "Invalid menu selected!");

		// create menu item, invalid menu
		assertEquals(ftms.getFoodList(0).getMenuItems().size(), 0);
		error = "";
		try {
			mc.createMenuItem(new Menu("Test"), "Menu Item", 1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(ftms.getFoodList(0).getMenuItems().size(), 0);
		assertEquals(error, "Invalid menu selected!");

		// change menu item name, invalid menu
		assertEquals(ftms.getFoodList(0).getMenuItems().size(), 0);
		error = "";
		try {
			mc.changeMenuItemName(new Menu("Test"), new MenuItem("Test", 1), "Test");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(ftms.getFoodList(0).getMenuItems().size(), 0);
		assertEquals(error, "Invalid menu selected!");

		// change menu item price, invalid menu
		assertEquals(ftms.getFoodList(0).getMenuItems().size(), 0);
		error = "";
		try {
			mc.changeMenuItemPrice(new Menu("Test"), new MenuItem("Test", 1), 1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(ftms.getFoodList(0).getMenuItems().size(), 0);
		assertEquals(error, "Invalid menu selected!");

		// delete menu item, invalid menu
		assertEquals(ftms.getFoodList(0).getMenuItems().size(), 0);
		error = "";
		try {
			mc.deleteMenuItem(new Menu("Test"), new MenuItem("Test", 1));
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(ftms.getFoodList(0).getMenuItems().size(), 0);
		assertEquals(error, "Invalid menu selected!");

	}

	@Test
	public void testInvalidMenuItem() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();

		String error = "";
		String name = "Menu Item 1";
		int price = 1;
		MenuControllerAdapter mc = new MenuControllerAdapter();

		try {
			mc.createMenu("Menu 1");
			mc.createMenuItem(ftms.getFoodList(0), name, price);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodList(0).getMenuItems().size(), 1);
		assertEquals(ftms.getFoodList(0).getMenuItem(0).getName(), name);

		// change menu item name, invalid menu item
		try {
			mc.changeMenuItemName(ftms.getFoodList(0), new MenuItem("Test", 1), "New name");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(ftms.getFoodList(0).getMenuItems().size(), 1);
		assertEquals(ftms.getFoodList(0).getMenuItem(0).getName(), name);
		assertEquals(error, "Invalid menu item selected!");

		// change menu item price, invalid menu item
		try {
			mc.changeMenuItemPrice(ftms.getFoodList(0), new MenuItem("Test", 1), 2);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(ftms.getFoodList(0).getMenuItems().size(), 1);
		assertEquals(ftms.getFoodList(0).getMenuItem(0).getPrice(), price);
		assertEquals(error, "Invalid menu item selected!");

		// delete menu item, invalid menu item
		try {
			mc.deleteMenuItem(ftms.getFoodList(0), new MenuItem("Test", 1));
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(ftms.getFoodList(0).getMenuItems().size(), 1);
		assertEquals(error, "Invalid menu item selected!");

	}

	@Test
	public void testCreateMenuInvalidName() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
		assertEquals(ftms.getFoodList().size(), 0);

		String name = "";
		String error = "";
		MenuControllerAdapter mc = new MenuControllerAdapter();

		try {
			mc.createMenu(name);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(ftms.getFoodList().size(), 0);
		assertEquals(error, "Menu name cannot be empty!");

		name = " ";
		error = "";

		try {
			mc.createMenu(name);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(ftms.getFoodList().size(), 0);
		assertEquals(error, "Menu name cannot be empty!");

		name = null;
		error = "";

		try {
			mc.createMenu(name);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(ftms.getFoodList().size(), 0);
		assertEquals(error, "Menu name cannot be empty!");

	}

	@Test
	public void testCreateMenuItemInvalidName() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
		assertEquals(ftms.getFoodList().size(), 0);

		String name = "Menu 1";
		String error = "";
		MenuControllerAdapter mc = new MenuControllerAdapter();

		try {
			mc.createMenu(name);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodList().size(), 1);

		name = "";

		try {
			mc.createMenuItem(ftms.getFoodList(0), name, 1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(ftms.getFoodList().size(), 1);
		assertEquals(error, "Menu item name cannot be empty!");

		name = " ";
		error = "";

		try {
			mc.createMenuItem(ftms.getFoodList(0), name, 1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(ftms.getFoodList().size(), 1);
		assertEquals(error, "Menu item name cannot be empty!");

		name = null;
		error = "";

		try {
			mc.createMenuItem(ftms.getFoodList(0), name, 1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(ftms.getFoodList().size(), 1);
		assertEquals(error, "Menu item name cannot be empty!");

	}

	@Test
	public void testChangeMenuInvalidName() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
		assertEquals(ftms.getFoodList().size(), 0);

		String name = "Menu 1";
		String error = "";
		MenuControllerAdapter mc = new MenuControllerAdapter();

		try {
			mc.createMenu(name);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(ftms.getFoodList().size(), 1);

		name = "";

		try {
			mc.changeMenuName(ftms.getFoodList(0), name);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(ftms.getFoodList().size(), 1);
		assertEquals(error, "Menu name cannot be empty!");

		name = " ";
		error = "";

		try {
			mc.changeMenuName(ftms.getFoodList(0), name);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(ftms.getFoodList().size(), 1);
		assertEquals(error, "Menu name cannot be empty!");

		name = null;
		error = "";

		try {
			mc.changeMenuName(ftms.getFoodList(0), name);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(ftms.getFoodList().size(), 1);
		assertEquals(error, "Menu name cannot be empty!");

	}

	@Test
	public void testInvalidPrice() {
		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();

		MenuControllerAdapter mc = new MenuControllerAdapter();

		try {
			mc.createMenu("Menu 1");
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// create menu item, invalid price
		assertEquals(ftms.getFoodList(0).getMenuItems().size(), 0);

		String error = "";

		try {
			mc.createMenuItem(ftms.getFoodList(0), "Menu Item 1", -1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(ftms.getFoodList(0).getMenuItems().size(), 0);
		assertEquals(error, "Price cannot be negative or zero!");

		// change menu item price, invalid price
		error = "";
		try {
			mc.createMenuItem(ftms.getFoodList(0), "Menu Item 1", 1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals(ftms.getFoodList(0).getMenuItems().size(), 1);

		try {
			mc.changeMenuItemPrice(ftms.getFoodList(0), ftms.getFoodList(0).getMenuItem(0), -1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(ftms.getFoodList(0).getMenuItems().size(), 1);
		assertEquals(error, "Price cannot be negative or zero!");
	}
}
