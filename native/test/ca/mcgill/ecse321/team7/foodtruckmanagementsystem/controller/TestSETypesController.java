package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruckManagementSystem;

public class TestSETypesController {

	FoodTruckManagementSystem ftms;
	SETypesController setc;

	@Before
	public void setUp() throws Exception {
		ftms = FoodTruckManagementSystem.getInstance();
		setc = new SETypesControllerAdapter();
	}

	@After
	public void tearDown() throws Exception {
		// clear records
		ftms.delete();
	}

	@Test
	public void testCreateSupplyType() {

		assertEquals(0, ftms.numberOfSupplyTypes());

		String name = "Tomatoes";

		try {
			setc.createSupplyType(name);
		} catch (InvalidInputException | DuplicateTypeException e) {

			e.printStackTrace();
		}

		assertEquals(1, ftms.numberOfSupplyTypes());
		assertEquals(name, ftms.getSupplyType(0).getName());

	}

	@Test
	public void testChangeSupplyTypeName() {

		createASupplyType();

		String newName = "Bacon";
		String error = "";
		assertNotEquals(newName, ftms.getSupplyType(0).getName());

		try {
			setc.changeSupplyTypeName(null, newName);
		} catch (InvalidInputException e1) {
			// TODO Auto-generated catch block
			error = e1.getMessage();
		} catch (DuplicateTypeException e1) {
			// TODO Auto-generated catch block
			error = e1.getMessage();
		}
		
		assertEquals(error, "Given type does not exist!");
		
		try {
			setc.changeSupplyTypeName(ftms.getSupplyType(0), newName);
		} catch (InvalidInputException | DuplicateTypeException e) {

			e.printStackTrace();
		}

		assertEquals(newName, ftms.getSupplyType(0).getName());
	}

	@Test
	public void testDeleteSupplyType() {
		createASupplyType();
		String newName = "Bacon";
		String error = "";
		try {
			setc.changeSupplyTypeName(ftms.getSupplyType(0), newName);
		} catch (InvalidInputException | DuplicateTypeException e) {

			e.printStackTrace();
		}

		try {
			setc.deleteSupplyType(null);
		} catch (InvalidInputException e1) {
			// TODO Auto-generated catch block
			error = e1.getMessage();
		}
		
		assertEquals(error, "The given type is invalid!");
		
		try {
			setc.deleteSupplyType(ftms.getSupplyType(0));
		} catch (InvalidInputException e) {

			e.printStackTrace();
		}

		assertEquals(ftms.getSupplyTypes().size(), 0);

	}

	@Test
	public void testCreateEquipmentType() {

		assertEquals(0, ftms.numberOfEquipmentTypes());
		String error = "";
		String name = "Oven";

		try {
			setc.createEquipmentType("");
		} catch (InvalidInputException e1) {
			// TODO Auto-generated catch block
			error = e1.getMessage();
		} catch (DuplicateTypeException e1) {
			// TODO Auto-generated catch block
			error = e1.getMessage();
		}
		
		assertEquals(error,"Name cannot be empty!" );
		
		try {
			setc.createEquipmentType(name);
		} catch (InvalidInputException | DuplicateTypeException e) {

			e.printStackTrace();
		}

		assertEquals(1, ftms.numberOfEquipmentTypes());
		assertEquals(name, ftms.getEquipmentType(0).getName());
		
		try {
			setc.createEquipmentType(name);
		} catch (InvalidInputException | DuplicateTypeException e) {

			error = e.getMessage();
		}
		
		assertEquals(error, "Equipment type " + name + " already exists.");
	}

	@Test
	public void testChangeEquipmentTypeName() {

		createAnEquipmentType();
		String error = "";
		String newName = "Freezer";

		assertNotEquals(newName, ftms.getEquipmentType(0).getName());

		try {
			setc.changeEquipmentTypeName(ftms.getEquipmentType(0), "");
		} catch (InvalidInputException e1) {
			// TODO Auto-generated catch block
			error = e1.getMessage();
		} catch (DuplicateTypeException e1) {
			// TODO Auto-generated catch block
			error = e1.getMessage();
		}
		
		assertEquals(error, "Name cannot be empty!");
		
		try {
			setc.changeEquipmentTypeName(null, "Oven");
		} catch (InvalidInputException e1) {
			// TODO Auto-generated catch block
			error = e1.getMessage();
		} catch (DuplicateTypeException e1) {
			// TODO Auto-generated catch block
			error = e1.getMessage();
		}
		
		assertEquals(error, "Given type does not exist!");
		
		try {
			setc.changeEquipmentTypeName(ftms.getEquipmentType(0), newName);
		} catch (InvalidInputException | DuplicateTypeException e) {

			e.printStackTrace();
		}

		assertEquals(newName, ftms.getEquipmentType(0).getName());
	}

	@Test
	public void testDeleteEquipmentType() {

		assertEquals(0, ftms.numberOfEquipmentTypes());
		String error = "";
		String name = "Oven";

		try {
			setc.createEquipmentType(name);
		} catch (InvalidInputException | DuplicateTypeException e) {

			e.printStackTrace();
		}

		assertEquals(1, ftms.numberOfEquipmentTypes());
		assertEquals(name, ftms.getEquipmentType(0).getName());

		try {
			setc.deleteEquipmentType(null);
		} catch (InvalidInputException e1) {
			error = e1.getMessage();
		}
		
		assertEquals(error, "The given type is invalid!");
		
		try {
			setc.deleteEquipmentType(ftms.getEquipmentType(0));
		} catch (InvalidInputException e) {

			e.printStackTrace();
		}

		assertEquals(ftms.getEquipmentTypes().size(), 0);
	}

	@Test
	public void testInvalidStrings() {
		testInvalidString("");
		testInvalidString(" ");
		testInvalidString(null);
	}

	@Test
	public void testDuplicateType() {

		// test for supply type
		createASupplyType();

		String error = "";
		String name = "Tomato";

		try {
			setc.createSupplyType(name);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		} catch (DuplicateTypeException e) {
			error = e.getMessage();
		}

		assertEquals(1, ftms.numberOfSupplyTypes());
		assertEquals("Supply type " + name + " already exists.", error);

		// test for equipment type
		createAnEquipmentType();

		error = "";
		name = "Oven";

		try {
			setc.createEquipmentType(name);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		} catch (DuplicateTypeException e) {
			error = e.getMessage();
		}

		assertEquals(1, ftms.numberOfEquipmentTypes());
		assertEquals("Equipment type " + name + " already exists.", error);

	}

	private void testInvalidString(String str) {

		// prepare for tests
		try {
			setUp();
		} catch (Exception e) {

			e.printStackTrace();
		}

		String error = "";

		// test create supply type
		assertEquals(0, ftms.numberOfSupplyTypes());

		try {
			setc.createSupplyType(str);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		} catch (DuplicateTypeException e) {
			e.printStackTrace();
		}
		// remove records
		try {
			tearDown();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private void createASupplyType() {
		int previousNumberOfTypes = ftms.numberOfSupplyTypes();
		try {
			setc.createSupplyType("Tomato");
		} catch (InvalidInputException | DuplicateTypeException e) {
			e.printStackTrace();
		}
		// verify was created successfully
		assertEquals(previousNumberOfTypes + 1, ftms.numberOfSupplyTypes());
	}

	private void createAnEquipmentType() {
		int previousNumberOfTypes = ftms.numberOfEquipmentTypes();
		try {
			setc.createEquipmentType("Oven");
		} catch (InvalidInputException | DuplicateTypeException e) {

			e.printStackTrace();
		}
		// verify was created successfully
		assertEquals(previousNumberOfTypes + 1, ftms.numberOfEquipmentTypes());
	}

}
