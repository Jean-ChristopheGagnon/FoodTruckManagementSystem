<?php
	
class StaffController extends CRUDController {
	
	/**
	 * Contructor
	 */
	public function __construct() {
		
		parent::__construct('Staff', 'Staff');
		
	}
	
	public function createStaff($name, $job, $workShifts) {
		
		$name = $this->validateAndCleanProperty($name, 'name', 'string');
		$job = $this->validateAndCleanProperty($job, 'job', 'string');
		
		$staff = new Staff($name, $job);
		
		foreach ($workShifts as $workShift) {
			$staff->addWorkShift($staff);
		}
		
		$this->create($staff);
		
	}
	
	public function editStaff($name, $job, $workShifts, $id) {
		
		$name = $this->validateAndCleanProperty($name, 'name', 'string');
		$job = $this->validateAndCleanProperty($job, 'job', 'string');
		
		$this->modify('name', $name, $id);
		$this->modify('job', $job, $id);
		$this->modify('workShifts', $workShifts, $id);
		
	}
	
	public function getStaff($id) {
		
		return $this->read($id);
		
	}
	
	public function getStaffs() {
		
		return $this->readAll();
		
	}
	
	public function deleteStaff($id) {
		
		$this->delete($id);
		
	}
	
	public function getStaffId(Staff $staff) {
		
		return $this->getId($staff);
		
	}
	
}