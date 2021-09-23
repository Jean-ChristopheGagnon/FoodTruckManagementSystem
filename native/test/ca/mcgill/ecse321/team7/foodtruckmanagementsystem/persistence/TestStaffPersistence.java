package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.persistence;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;
import java.sql.Time;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruckManagementSystem;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Menu;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Staff;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.WorkShift;

public class TestStaffPersistence {
	
	Menu menu = new Menu("Dummy Menu");
	FoodTruck ft = new FoodTruck("Dummy truck", menu);
	Date d1 = Date.valueOf("2016-11-12");
	Date d2 = Date.valueOf("2016-11-13");
	Time start = Time.valueOf("08:00:00");
	Time end = Time.valueOf("16:00:00");

	@Before
	public void setUp() throws Exception {
		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
		
		//create staff
		Staff s1 = new Staff("Kevin", "Cook");
		Staff s2 = new Staff("Maxime", "Manager");
		
		//create workshift
		WorkShift ws1 = new WorkShift(d1, start, end, ft);
		WorkShift ws2 = new WorkShift(d2, start, end, ft);
		WorkShift ws3 = new WorkShift(d1, start, end, ft);
		
		//add workshifts to staff members
		s1.addWorkShift(ws1);
		s1.addWorkShift(ws2);
		s2.addWorkShift(ws3);
		
		//add staffs to manager
		ftms.addStaff(s1);
		ftms.addStaff(s2);
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

		// load model
		PersistenceFoodTruckManager.loadTruckManagerModel("test" + File.separator + "ca" + File.separator + "mcgill"
				+ File.separator + "ecse321" + File.separator + "team7" + File.separator + "foodtruckmanagementsystem"
				+ File.separator + "persistence" + File.separator + "data.xml");

		ftms = FoodTruckManagementSystem.getInstance();
		if (ftms == null)
			fail("Could not load file");
		
		//check staff
		assertEquals(2, ftms.getStaffs().size());
		assertEquals(2, ftms.getStaff(0).getWorkShifts().size());
		assertEquals(1, ftms.getStaff(1).getWorkShifts().size());
		assertEquals("Kevin", ftms.getStaff(0).getName());
		assertEquals("Maxime", ftms.getStaff(1).getName());
		assertEquals("Cook", ftms.getStaff(0).getJob());
		assertEquals("Manager", ftms.getStaff(1).getJob());
		
		//check work shifts
		assertEquals(d1, ftms.getStaff(0).getWorkShift(0).getWorkDate());
		assertEquals(d2, ftms.getStaff(0).getWorkShift(1).getWorkDate());
		assertEquals(d1, ftms.getStaff(1).getWorkShift(0).getWorkDate());
		assertEquals(start, ftms.getStaff(0).getWorkShift(0).getStartTime());
		assertEquals(start, ftms.getStaff(0).getWorkShift(1).getStartTime());
		assertEquals(start, ftms.getStaff(1).getWorkShift(0).getStartTime());
		assertEquals(end, ftms.getStaff(0).getWorkShift(0).getEndTime());
		assertEquals(end, ftms.getStaff(0).getWorkShift(1).getEndTime());
		assertEquals(end, ftms.getStaff(1).getWorkShift(0).getEndTime());
		assertEquals(ft.getLocation(), ftms.getStaff(0).getWorkShift(0).getFoodTruck().getLocation());
		assertEquals(ft.getLocation(), ftms.getStaff(0).getWorkShift(1).getFoodTruck().getLocation());
		assertEquals(ft.getLocation(), ftms.getStaff(1).getWorkShift(0).getFoodTruck().getLocation());
		assertEquals(ft.getMenu().getName(), ftms.getStaff(0).getWorkShift(0).getFoodTruck().getMenu().getName());
		assertEquals(ft.getMenu().getName(), ftms.getStaff(0).getWorkShift(1).getFoodTruck().getMenu().getName());
		assertEquals(ft.getMenu().getName(), ftms.getStaff(1).getWorkShift(0).getFoodTruck().getMenu().getName());
	}

}
