package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Time;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruckManagementSystem;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Menu;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Staff;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.WorkShift;

/**
 * Test suite for the staff controller.
 * @author Kevin Laframboise
 *
 */
public class TestStaffController {

	FoodTruckManagementSystem ftms;
	StaffController sc;
	
	@Before
	public void setUp() throws Exception {
		ftms = FoodTruckManagementSystem.getInstance();
		sc = new StaffControllerAdapter();
	}
	
	@After
	public void tearDown() throws Exception {
		//clear records
		ftms.delete();
	}

	@Test
	public void testCreateStaff() {
		
		assertEquals(0, ftms.getStaffs().size());
		
		String name = "Kevin";
		String job = "Cook";

		createAStaffMember(name, job);
		
		assertEquals(1, ftms.getStaffs().size());
		assertEquals(name, ftms.getStaff(0).getName());
		assertEquals(job, ftms.getStaff(0).getJob());
		
	}
	
	@Test
	public void testDeleteStaff() {
		
		createAStaffMember();
		
		assertEquals(1, ftms.getStaffs().size());
		
		try {
			sc.deleteStaffMember(ftms.getStaff(0));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		
		assertEquals(0, ftms.getStaffs().size());
		
	}
	
	@Test
	public void testChangeStaffName() {
		
		createAStaffMember();
		
		assertEquals("Kevin", ftms.getStaff(0).getName());
		
		try {
			sc.changeStaffMemberName(ftms.getStaff(0), "Rami");
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		
		assertEquals("Rami", ftms.getStaff(0).getName());
	}
	
	@Test
	public void testChangeStaffJob() {
		
		createAStaffMember();
		
		assertEquals("Cook", ftms.getStaff(0).getJob());
		
		try {
			sc.changeStaffMemberJob(ftms.getStaff(0), "Manager");
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		
		assertEquals("Manager", ftms.getStaff(0).getJob());
	}
	
	@Test
	public void testCreateWorkShift() {
		
		FoodTruck foodTruck = new FoodTruck("Dummy Truck", new Menu("Dummy menu"));
		Date date = new Date(System.currentTimeMillis());
		Time start = Time.valueOf("08:00:00");
		Time end = Time.valueOf("16:00:00");
		
		createAWorkShift(foodTruck, date, start, end);
		
		assertEquals(1, ftms.getStaff(0).getWorkShifts().size());
		assertEquals(foodTruck, ftms.getStaff(0).getWorkShift(0).getFoodTruck());
		assertEquals(date, ftms.getStaff(0).getWorkShift(0).getWorkDate());
		assertEquals(start, ftms.getStaff(0).getWorkShift(0).getStartTime());
		assertEquals(end, ftms.getStaff(0).getWorkShift(0).getEndTime());
	}
	
	@Test
	public void testChangeWorkFoodTruck() {
		
		createAWorkShift();
		
		FoodTruck newFoodTruck = new FoodTruck("Dumme Truck 2", new Menu("Dummy menu"));
		
		try {
			sc.changeWorkFoodTruck(ftms.getStaff(0).getWorkShift(0), newFoodTruck);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		
		assertEquals(newFoodTruck, ftms.getStaff(0).getWorkShift(0).getFoodTruck());
	}
	
	@Test
	public void testChangeWorkDate() {
		
		createAWorkShift();
		
		Date newDate = new Date(0);
		
		try {
			sc.changeWorkDate(ftms.getStaff(0).getWorkShift(0), newDate);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		
		assertEquals(newDate, ftms.getStaff(0).getWorkShift(0).getWorkDate());
		
	}
	
	@Test
	public void testChangeWorkStarTime() {
		
		createAWorkShift();
		
		Time newStartTime = Time.valueOf("09:00:00");
		
		try {
			sc.changeWorkStartTime(ftms.getStaff(0).getWorkShift(0), newStartTime);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		
		assertEquals(newStartTime, ftms.getStaff(0).getWorkShift(0).getStartTime());
	}
	
	@Test
	public void testChangeWorkEndTime() {
		
		createAWorkShift();
		
		Time newEndTime = Time.valueOf("17:00:00");
		
		try {
			sc.changeWorkEndTime(ftms.getStaff(0).getWorkShift(0), newEndTime);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		
		assertEquals(newEndTime, ftms.getStaff(0).getWorkShift(0).getEndTime());
	}
	
	@Test
	public void testDeleteWorkShift() {
		
		createAWorkShift();
		
		try {
			sc.deleteWorkShift(ftms.getStaff(0), ftms.getStaff(0).getWorkShift(0));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		
		assertEquals(0, ftms.getStaff(0).getWorkShifts().size());
	}
	
	@Test
	public void testClearWorkShifts() {
		
		for(int i = 0; i < 5; i++) {
			createAWorkShift();
		}
		
		assertEquals(5, ftms.getStaff(0).getWorkShifts().size());
		
		try {
			sc.clearWorkShifts(ftms.getStaff(0));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		
		assertEquals(0, ftms.getStaff(0).getWorkShifts().size());
	}
	
	@Test
	public void testInvalidStrings() {
		testInvalidString(null);
		testInvalidString("");
		testInvalidString(" ");
	}
	
	@Test
	public void testInvalidStaffMembers() {
		//test with a reference to a staff member that is not part of the system
		testInvalidStaffMember(new Staff("Kevin", "Cook"));
		//test with a null pointer
		testInvalidStaffMember(null);
	}
	
	@Test
	public void testInvalidWorkShifts() {
		Date date = new Date(System.currentTimeMillis());
		Time start = Time.valueOf("08:00:00");
		Time end = Time.valueOf("16:00:00");
		//test with a reference to a work shift that is not part of the system
		testInvalidWorkShift(new WorkShift(date, start, end, new FoodTruck("Dummy Truck", new Menu("Dummy Menu"))));
		//test with a null pointer
		testInvalidWorkShift(null);
	}
	
	@Test
	public void testInvalidDate() {
		
		String error;
		createAStaffMember();
		
		//test create workshift
		error = "";
		try {
			sc.createWorkShift(ftms.getStaff(0), new FoodTruck("Dummy truck", new Menu("Dummy menu")), null, new Time(0), new Time(1));
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("The given date is invalid!", error);
		
		//test change work date
		error = "";
		createAWorkShift();
		try {
			sc.changeWorkDate(ftms.getStaff(0).getWorkShift(0), null);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("The given date is invalid!", error);
	}
	
	@Test
	public void testInvalidTime() {
		
		String error;
		createAStaffMember();
		
		//test create workshift, start time
		error = "";
		try {
			sc.createWorkShift(ftms.getStaff(0), new FoodTruck("Dummy truck", new Menu("Dummy menu")), new Date(0), null, new Time(1));
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("The given start time is invalid!", error);
		
		//test create workshift, end time
		error = "";
		try {
			sc.createWorkShift(ftms.getStaff(0), new FoodTruck("Dummy truck", new Menu("Dummy menu")), new Date(0), new Time(0), null);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("The given end time is invalid!", error);
		
		//test change start time
		error = "";
		createAWorkShift();
		try {
			sc.changeWorkStartTime(ftms.getStaff(0).getWorkShift(0), null);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("The given start time is invalid!", error);
		
		//test change start time
		error = "";
		try {
			sc.changeWorkEndTime(ftms.getStaff(0).getWorkShift(0), null);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("The given end time is invalid!", error);
	}
	
	@Test
	public void testEndTimeBeforeStartTime() {
		Date date = new Date(System.currentTimeMillis());
		Time end = Time.valueOf("08:00:00");
		Time start = Time.valueOf("16:00:00");
		String error = "";
		createAStaffMember();
		
		try {
			sc.createWorkShift(ftms.getStaff(0), new FoodTruck("Dummy truck", new Menu("Dummy menu")), date, start, end);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("End time cannot be before start time!", error);
	}
	
	@Test
	public void testInvalidFoodTruck() {
		// TODO implement test once FoodTruck is implemented
	}
	
	private void testInvalidWorkShift(WorkShift workShift) {
		
		//reset for tests
		try {
			setUp();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		String error;
		
		//test change work food truck
		error = "";
		try {
			sc.changeWorkFoodTruck(workShift, null);	// TODO implement fully once FoodTruck is implemented
		} catch (InvalidInputException e) {
			error = e.getMessage();
		} 
		assertEquals("The given work shift is invalid!", error);
		
		//test change work date
		error = "";
		try {
			sc.changeWorkDate(workShift, new Date(0)); // TODO implement fully once FoodTruck is implemented
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("The given work shift is invalid!", error);
		
		//test change work date
		error = "";
		try {
			sc.changeWorkStartTime(workShift, new Time(0)); // TODO implement fully once FoodTruck is implemented
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("The given work shift is invalid!", error);
		
		//test change work end time
		error = "";
		try {
			sc.changeWorkEndTime(workShift, new Time(0)); // TODO implement fully once FoodTruck is implemented
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("The given work shift is invalid!", error);
		
		//test delete work shift
		error = "";
		createAStaffMember();
		try {
			sc.deleteWorkShift(ftms.getStaff(0), workShift); // TODO implement fully once FoodTruck is implemented
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("The given work shift is invalid!", error);
		
		//delete records created by tests
		try {
			tearDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void testInvalidStaffMember(Staff staff) {
		
		//reset for tests
		try {
			setUp();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		String error;

		//test delete staff
		error = "";
		try {
			sc.deleteStaffMember(staff);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("The given staff member is invalid!", error);
		
		//test change staff name
		error = "";
		try {
			sc.changeStaffMemberName(staff, "New Name");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("The given staff member is invalid!", error);
		
		//test change staff name
		error = "";
		try {
			sc.changeStaffMemberJob(staff, "New Job");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("The given staff member is invalid!", error);
		
		//test create workshift
		error = "";
		Date date = new Date(System.currentTimeMillis());
		Time start = Time.valueOf("08:00:00");
		Time end = Time.valueOf("16:00:00");
		try {
			sc.createWorkShift(staff, null, date, start, end); // TODO implement fully once FoodTruck is implemented
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("The given staff member is invalid!", error);
		
		//test delete workshift
		error = "";
		createAWorkShift();
		try {
			sc.deleteWorkShift(staff, ftms.getStaff(0).getWorkShift(0));
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("The given staff member is invalid!", error);
		
		//test clear workshifts
		error = "";
		try {
			sc.clearWorkShifts(staff);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("The given staff member is invalid!", error);
		
		//delete records created by tests
		try {
			tearDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void testInvalidString(String str) {
		
		try {
			setUp();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		String error = "";
		
		//new staff, invalid name
		try {
			sc.createStaffMember(str, "Test job");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals(0, ftms.numberOfStaffs());
		assertEquals("Name cannot be empty!", error);
		//new staff, invalid job
		try {
			sc.createStaffMember("Test name", str);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals(0, ftms.numberOfStaffs());
		assertEquals("Job cannot be empty!", error);
		
		//change staff name, invalid name
		error = "";
		createAStaffMember();
		try {
			sc.changeStaffMemberName(ftms.getStaff(0), str);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Kevin", ftms.getStaff(0).getName());
		assertEquals("Name cannot be empty!", error);
		
		// change staff job, invalid job
		error = "";
		createAStaffMember();
		try {
			sc.changeStaffMemberJob(ftms.getStaff(0), str);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Cook", ftms.getStaff(0).getJob());
		assertEquals("Job cannot be empty!", error);
		
		try {
			tearDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createAWorkShift() {
		FoodTruck foodTruck = new FoodTruck("Dummy Truck", new Menu("Dummy menu"));
		Date date = new Date(System.currentTimeMillis());
		Time start = Time.valueOf("08:00:00");
		Time end = Time.valueOf("16:00:00");
		createAWorkShift(foodTruck, date, start, end);
	}
	
	private void createAStaffMember() {
		createAStaffMember("Kevin", "Cook");
	}
	
	private void createAStaffMember(String name, String job) {
		try {
			sc.createStaffMember(name, job);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
	}
	
	private void createAWorkShift(FoodTruck foodTruck, Date date, Time start, Time end) {
	
		if (ftms.getStaffs().isEmpty()) {
			createAStaffMember();
		}
		
		int currentAmountofWorkShifts = ftms.getStaff(0).getWorkShifts().size();
		
		try {
			sc.createWorkShift(ftms.getStaff(0), foodTruck, date, start, end);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		
		assertEquals(currentAmountofWorkShifts+1, ftms.getStaff(0).getWorkShifts().size());
		
	}

}
