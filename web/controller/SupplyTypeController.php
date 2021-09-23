<?php


class SupplyTypeController extends CRUDController {
	
	/**
	 * Contructor
	 */
	public function __construct() {
		
		parent::__construct('SupplyType', 'supply type');
		
	}
	
	public function createSupplyType($name) {
		
		$name = $this->validateAndCleanProperty($name, 'name', 'string');
		
		$supplyType = new SupplyType($name);
		
		$this->create($supplyType);
		
		return $supplyType;
		
	}
	
	public function editSupplyType($name, $id) {
		
		$name = $this->validateAndCleanProperty($name, 'name', 'string');
		
		$this->modify('name', $name, $id);
		
	}
	
	public function getSupplyType($id) {
		
		return $this->read($id);
		
	}
	
	public function getSupplyTypes() {
		
		return $this->readAll();
		
	}
	
	public function deleteSupplyType($id) {
		
		$this->delete($id);
		
	}
	
	public function getSupplyTypeId(SupplyType $supplyType) {
		
		return $this->getId($supplyType);
		
	}
	
}