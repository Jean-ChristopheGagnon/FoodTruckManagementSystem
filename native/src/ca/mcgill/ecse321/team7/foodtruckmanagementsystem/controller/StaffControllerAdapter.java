package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.Iterator;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruckManagementSystem;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Staff;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.WorkShift;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.persistence.PersistenceXStream;

/**
 * Implementation of SatffController.
 * @author Kevin Laframboise
 *
 */
public class StaffControllerAdapter implements StaffController {

	/**
	 * Instance of the food truck management system
	 */
	private FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
	
	@Override
	public void createStaffMember(String name, String job) throws InvalidInputException {
		//check for invalid input
		if(name == null || name.trim().length() == 0) {
			throw new InvalidInputException("Name cannot be empty!");
		}
		if(job == null || job.trim().length() == 0) {
			throw new InvalidInputException("Job cannot be empty!");
		}
		//add staff member
		ftms.addStaff(new Staff(name, job));
		
		saveModel();	
	}


	@Override
	public void deleteStaffMember(Staff staff) throws InvalidInputException {
		
		//check for valid argument
		if(staff == null || ftms.getStaffs().indexOf(staff) < 0) {
			throw new InvalidInputException("The given staff member is invalid!");
		}
		
		//unassign from all food trucks
		Iterator<FoodTruck> it = ftms.getFoodTrucks().iterator();
		while(it.hasNext()) {
			it.next().removeStaff(staff);
		}
		
		//remove staff
		ftms.removeStaff(staff);

		saveModel();
	}

	@Override
	public void changeStaffMemberName(Staff staff, String newName) throws InvalidInputException {
		//check for invalid arguments
		if(newName == null || newName.trim().length() == 0) {
			throw new InvalidInputException("Name cannot be empty!");
		}
		if(staff == null || ftms.getStaffs().indexOf(staff) < 0) {
			throw new InvalidInputException("The given staff member is invalid!");
		}
		//change name
		staff.setName(newName);

		saveModel();
	}

	@Override
	public void changeStaffMemberJob(Staff staff, String newJob) throws InvalidInputException {
		//check for invalid arguments
		if(newJob == null || newJob.trim().length() == 0) {
			throw new InvalidInputException("Job cannot be empty!");
		}
		if(staff == null || ftms.getStaffs().indexOf(staff) < 0) {
			throw new InvalidInputException("The given staff member is invalid!");
		}
		//change job
		staff.setJob(newJob);

		saveModel();
	}

	@Override
	public void createWorkShift(Staff staff, FoodTruck foodTruck, Date workDate, Time startTime, Time endTime)
			throws InvalidInputException {
		//check for invalid arguments
		if(staff == null || ftms.getStaffs().indexOf(staff) < 0) {
			throw new InvalidInputException("The given staff member is invalid!");
		}
		if(workDate == null) {
			throw new InvalidInputException("The given date is invalid!");
		}
		if(startTime == null) {
			throw new InvalidInputException("The given start time is invalid!");
		}
		if(endTime == null) {
			throw new InvalidInputException("The given end time is invalid!");
		}
		if(startTime.compareTo(endTime) > 0 ) {
			System.out.println("Start time: " + startTime.toString());
			System.out.println("End time: " + endTime.toString());
			throw new InvalidInputException("End time cannot be before start time!");
		}
		//create the workshift and add it to the staff's record
		WorkShift workShift = new WorkShift(workDate, startTime, endTime, foodTruck);
		staff.addWorkShift(workShift);
		
		// Assign staff to food truck if not assigned already.
		if(!foodTruck.getStaffs().contains(staff)) {
			foodTruck.addStaff(staff);
		}
		
		saveModel();
	}
	
	@Override
	public void changeWorkFoodTruck(WorkShift workShift, FoodTruck newTruck) throws InvalidInputException {
		//check for invalid arguments
		if(workShift == null || !workShiftExists(workShift)) {
			throw new InvalidInputException("The given work shift is invalid!");
		}
		//change workshift's food truck
		workShift.setFoodTruck(newTruck);
		
		saveModel();
	}
	
	@Override
	public void changeWorkDate(WorkShift workShift, Date newWorkDate) throws InvalidInputException {
		//check for invalid arguments
		if(workShift == null || !workShiftExists(workShift)) {
			throw new InvalidInputException("The given work shift is invalid!");
		}
		if(newWorkDate == null) {
			throw new InvalidInputException("The given date is invalid!");
		}
		//change the work date
		workShift.setWorkDate(newWorkDate);
		
		saveModel();
	}
	
	@Override
	public void changeWorkStartTime(WorkShift workShift, Time newStartTime) throws InvalidInputException {
		//check for invalid arguments
		if(workShift == null || !workShiftExists(workShift)) {
			throw new InvalidInputException("The given work shift is invalid!");
		}
		if(newStartTime == null) {
			throw new InvalidInputException("The given start time is invalid!");
		}
		//change the start time
		workShift.setStartTime(newStartTime);
		
		saveModel();
	}
	
	@Override
	public void changeWorkEndTime(WorkShift workShift, Time newEndTime) throws InvalidInputException {
		//check for invalid arguments
		if (workShift == null || !workShiftExists(workShift)) {
			throw new InvalidInputException("The given work shift is invalid!");
		}
		if(newEndTime == null) {
			throw new InvalidInputException("The given end time is invalid!");
		}
		//change the end time
		workShift.setEndTime(newEndTime);
		
		saveModel();
	}
	
	@Override
	public void deleteWorkShift(Staff staff, WorkShift workShift) throws InvalidInputException {
		//check for invalid arguments
		if(staff == null || ftms.getStaffs().indexOf(staff) < 0) {
			throw new InvalidInputException("The given staff member is invalid!");
		}
		if (workShift == null || !workShiftExists(workShift)) {
			throw new InvalidInputException("The given work shift is invalid!");
		}
		//remove given workshift
		staff.removeWorkShift(workShift);

		saveModel();
	}

	@Override
	public void clearWorkShifts(Staff staff) throws InvalidInputException {
		//check for invalid arguments
		if (staff == null || ftms.getStaffs().indexOf(staff) < 0) {
			throw new InvalidInputException("The given staff member is invalid!");
		}
		//remove all workshifts for given staff member
		while(staff.hasWorkShifts()) {
			staff.removeWorkShift(staff.getWorkShift(staff.getWorkShifts().size()-1));
		}

		saveModel();
	}
	
	/**
	 * Looks through all staff members to check that the given work shift
	 * exists in the management system.
	 * @param workShift that needs to be checked.
	 * @return true if the work shift exists in the management system.
	 */
	private boolean workShiftExists(WorkShift workShift) {
		
		Iterator<Staff> it = ftms.getStaffs().iterator();
		Staff staff;
		
		// Iterate through all work shifts
		while(it.hasNext()) {
			staff = it.next();
			if(staff.getWorkShifts().contains(workShift)) return true;
			else continue;
		}
		
		return false;
	}

	/**
	 * Saves model to XML via XStream
	 */
	private void saveModel() {
		PersistenceXStream.saveToXMLwithXStream(ftms);
	}

}
