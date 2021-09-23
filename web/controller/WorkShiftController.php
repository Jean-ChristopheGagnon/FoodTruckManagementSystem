<?php

class WorkShiftController extends CRUDController {
	
	/**
	 * Contructor
	 */
	public function __construct() {
		
		parent::__construct('WorkShift', 'work shift', 'Staff', 'staff');
		
	}
	
	// TODO: Verify the start time is before the end time
	public function createWorkShift($workDate, $startTime, $endTime, FoodTruck $foodTruck, $staffId) {
		
		$workDate = $this->validateAndCleanProperty($workDate, 'date', 'date');
		$startTime = $this->validateAndCleanProperty($startTime, 'start time', 'time');
		$endTime = $this->validateAndCleanProperty($endTime, 'end time', 'time');
		
		$workShift = new WorkShift($workDate, $startTime, $endTime, $foodTruck);
		
		$this->create($workShift, $staffId);
		
	}
	
	public function editWorkShift($workDate, $startTime, $endTime, $foodTruck, $id, $staffId) {
		
		$workDate = $this->validateAndCleanProperty($workDate, 'date', 'date');
		$startTime = $this->validateAndCleanProperty($startTime, 'start time', 'time');
		$endTime = $this->validateAndCleanProperty($endTime, 'end time', 'time');
		
		$this->modify('workDate', $workDate, $id, $staffId);
		$this->modify('startTime', $startTime, $id, $staffId);
		$this->modify('endTime', $endTime, $id, $staffId);
		$this->modify('foodTruck', $foodTruck, $id, $staffId);
		
	}
	
	public function getWorkShift($id, $staffId) {
		
		return $this->read($id, $staffId);
		
	}
	
	public function getWorkShifts($staffId) {
		
		return $this->readAll($staffId);
		
	}
	
	public function deleteWorkShift($id, $staffId) {
		
		$this->delete($id, $staffId);
		
	}
	
	public function getWorkShiftId(WorkShift $workShift, $staffId) {
		
		return $this->getId($workShift, $staffId);
		
	}
	
}
	