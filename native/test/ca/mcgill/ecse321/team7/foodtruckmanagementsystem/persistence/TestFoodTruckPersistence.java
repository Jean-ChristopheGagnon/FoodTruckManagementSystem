package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.persistence;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruckManagementSystem;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Menu;

public class TestFoodTruckPersistence {

	@Before
	public void setUp() throws Exception {
		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
		
		//Create Locations
		String location1 = "Brossard";
		String location2 = "Montreal";
		
		//Create menus
		Menu m1 = new Menu("Mexican");
		Menu m2 = new Menu("Asian");
		
		FoodTruck ft1 = new FoodTruck(location1, m1);
		FoodTruck ft2 = new FoodTruck(location2, m2);
		
		//Add food trucks
		ftms.addFoodTruck(ft1);
		ftms.addFoodTruck(ft2);
		
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
		
		//Check Locations
		assertEquals(ftms.getFoodTruck(0).getLocation(),"Brossard");
		assertEquals(ftms.getFoodTruck(1).getLocation(),"Montreal");
		//Check Food Trucks
		assertEquals(ftms.getFoodTrucks().size(), 2);
		//Check Menus
		assertEquals("Mexican", ftms.getFoodTruck(0).getMenu().getName());
		assertEquals("Asian", ftms.getFoodTruck(1).getMenu().getName());
		
		
	}

}
