package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller;

import java.sql.Date;
import java.sql.Time;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Staff;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.WorkShift;

/**
 * Handles create and update operations for Staff and related objects.
 * @author Kevin Laframboise
 *
 */
public interface StaffController {
	
	/**
	 * Creates a new staff member with given name and job title. 
	 * @param name of the new staff.
	 * @param job of the new staff.
	 * @throws InvalidInputException if the name or job title is empty.
	 */
	public void createStaffMember(String name, String job) throws InvalidInputException;
	
	/**
	 * Deletes the given Staff member from the management system.
	 * @param staff yo be deleted.
	 * @throws InvalidInputException if the staff member doesn't exist/is invalid.
	 */
	public void deleteStaffMember(Staff staff) throws InvalidInputException;
	
	/**
	 * Changes the name of the given staff member.
	 * @param staff for which the name has to be changed.
	 * @param newName of the staff.
	 * @throws InvalidInputException if the given staff member doesn't exist/is invalid or
	 * if the new name is invalid.
	 */
	public void changeStaffMemberName(Staff staff, String newName) throws InvalidInputException;;
	/**
	 * Changes the given staff member's job title.
	 * @param staff for which the job has to be changed.
	 * @param newJob of the staff.
	 * @throws InvalidInputException if the given staff member doesn't exist/is invalid or
	 * if the new job title is invalid.
	 */
	public void changeStaffMemberJob(Staff staff, String newJob) throws InvalidInputException;
	
	/**
	 * Creates a work shift and assigns it to the given staff member.
	 * @param staff for which the new work shift is created.
	 * @param foodTruck where the work shift will take place.
	 * @param workDate of the work shift.
	 * @param startTime of the work shift.
	 * @param endTime of the work shift.
	 * @throws InvalidInputException if the given staff doesn't exist/is invalid or if the given
	 * food truck doesn't exist/is invalid or if the work date is invalid, or if the start or
	 * end times are invalid.
	 */
	public void createWorkShift(Staff staff, FoodTruck foodTruck, Date workDate, Time startTime, Time endTime) throws InvalidInputException;

	/**
	 * Changes the food truck of the given work shift.
	 * @param workshift for which the food truck has to be changed.
	 * @param newTruck of the work shift.
	 * @throws InvalidInputException if the given work shift doesn't exist/is invalid
	 * or if the given food truck doesn't exist/is invalid.
	 */
	public void changeWorkFoodTruck(WorkShift workShift, FoodTruck newTruck) throws InvalidInputException;
	/**
	 * Changes the date of the given work shift for the give staff member.
	 * @param workShift for which the date has to be changed.
	 * @param newWorkDate of the work shift.
	 * @throws InvalidInputException if the work shift provided does not exist/is invalid
	 * or if the new date is invalid.
	 */
	public void changeWorkDate(WorkShift workShift, Date newWorkDate) throws InvalidInputException;

	/**
	 * Changes the start time of the given work shift for the given staff member.
	 * @param workShift for which the start time has to be changed.
	 * @param newStartTime of the work shift.
	 * @throws InvalidInputException if the work shift provided does not exist/is
	 * invalid or if the new start time is invalid.
	 */
	public void changeWorkStartTime(WorkShift workShift, Time newStartTime) throws InvalidInputException;
	
	/**
	 * Changes the end time of the give work shift for the given staff member.
	 * @param workShift for which the end time has to be changed.
	 * @param newEndTime of the work shift.
	 * @throws InvalidInputException if the work shift provided does not exist/is
	 * invalid or if the new end time is invalid.
	 */
	public void changeWorkEndTime(WorkShift workShift, Time newEndTime) throws InvalidInputException;

	/**
	 * Deletes the given work shift from the given staff member's record.
	 * @param staff who's work shift has to be deleted.
	 * @param workShift to be deleted.
	 * @throws InvalidInputException if the staff provided doesn't exist/is invalid or if the
	 * work shift provided does not exist/is invalid.
	 */
	public void deleteWorkShift(Staff staff, WorkShift workShift) throws InvalidInputException;

	/**
	 * Deletes all work shift from the given staff member's record.
	 * @param staff who's work shifts are to be cleared.
	 * @throws InvalidInputException if the staff provided doesn't exist/is invalid.
	 */
	public void clearWorkShifts(Staff staff) throws InvalidInputException;
}
