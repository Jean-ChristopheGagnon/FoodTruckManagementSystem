package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.persistence;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.persistence.PersistenceXStream;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruckManagementSystem;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Menu;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.MenuItem;

public class TestMenuPersistence {

	@Before
	public void setUp() throws Exception {
		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
		
		//create menus
		Menu m1 = new Menu("Mexican");
		Menu m2 = new Menu("Asian");
		
		//create menu items
		MenuItem i1 = new MenuItem("Tacos", 350);
		MenuItem i2 = new MenuItem("Nachos", 650);
		MenuItem i3 = new MenuItem("Pad Thai", 800);
		
		//add menu items to menus
		m1.addMenuItem(i1);
		m1.addMenuItem(i2);
		m2.addMenuItem(i3);
		
		//add menus to manager
		ftms.addFoodList(m1);
		ftms.addFoodList(m2);
	}

	@After
	public void tearDown() throws Exception {
		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
		ftms.delete();
	}

	@Test
	public void test() {

		// save model
		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
		PersistenceFoodTruckManager.initializeXStream("test" + File.separator + "ca" + File.separator + "mcgill"
				+ File.separator + "ecse321" + File.separator + "team7" + File.separator + "foodtruckmanagementsystem"
				+ File.separator + "persistence" + File.separator + "data.xml");
		if (!PersistenceXStream.saveToXMLwithXStream(ftms))
			fail("XStream was unable to save to XML");

		// clear model from memory
		ftms.delete();
		assertEquals(0, ftms.getFoodList().size());
		
		//load model
		PersistenceFoodTruckManager.loadTruckManagerModel("test"+File.separator+"ca"+File.separator+"mcgill"+File.separator+"ecse321"+
				File.separator+"team7"+File.separator+"foodtruckmanagementsystem"+File.separator+"persistence"+File.separator+"data.xml");
		
		ftms = FoodTruckManagementSystem.getInstance();
		if(ftms == null)
			fail("Could not load file");
		
		//check menus
		assertEquals(2, ftms.getFoodList().size());	
		assertEquals(2, ftms.getFoodList(0).getMenuItems().size());
		assertEquals(1, ftms.getFoodList(1).getMenuItems().size());
		assertEquals("Mexican", ftms.getFoodList(0).getName());
		assertEquals("Asian", ftms.getFoodList(1).getName());
		
		//check menu items
		assertEquals("Tacos", ftms.getFoodList(0).getMenuItem(0).getName());
		assertEquals("Nachos", ftms.getFoodList(0).getMenuItem(1).getName());
		assertEquals("Pad Thai", ftms.getFoodList(1).getMenuItem(0).getName());
		
	}

}
