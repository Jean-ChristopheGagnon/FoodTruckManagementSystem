<?php


class EquipmentTypeController extends CRUDController {
	
	/**
	 * Contructor
	 */
	public function __construct() {
		
		parent::__construct('EquipmentType', 'equipment type');
		
	}
	
	public function createEquipmentType($name) {
		
		$name = $this->validateAndCleanProperty($name, 'name', 'string');
		
		$equipmentType = new EquipmentType($name);
		
		$this->create($equipmentType);
		
		return $equipmentType;
		
	}
	
	public function editEquipmentType($name, $id) {
		
		$name = $this->validateAndCleanProperty($name, 'name', 'string');
		
		$this->modify('name', $name, $id);
		
	}
	
	public function getEquipmentType($id) {
		
		return $this->read($id);
		
	}
	
	public function getEquipmentTypes() {
		
		return $this->readAll();
		
	}
	
	public function deleteEquipmentType($id) {
		
		$this->delete($id);
		
	}
	
	public function getEquipmentTypeId(EquipmentType $equipmentType) {
		
		return $this->getId($equipmentType);
		
	}
	
}