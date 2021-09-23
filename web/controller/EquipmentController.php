<?php


class EquipmentController extends CRUDController {
	
	/**
	 * Contructor
	 */
	public function __construct() {
		
		parent::__construct('Equipment', 'equipment', 'FoodTruck', 'food truck');
		
	}
	
	public function createEquipment($quantity, $purchaseDate, EquipmentType $type, $parentId) {
		
		$quantity = $this->validateAndCleanProperty($quantity, 'quantity', 'int');
		$purchaseDate = $this->validateAndCleanProperty($purchaseDate, 'purchase date', 'date');
		
		$equipment = new Equipment($quantity, $purchaseDate, $type);
		
		$this->create($equipment, $parentId);
		
		return $equipment;
		
	}
	
	public function editEquipment($quantity, $purchaseDate, EquipmentType $type, $id, $parentId) {
		
		$quantity = $this->validateAndCleanProperty($quantity, 'quantity', 'int');
		$purchaseDate = $this->validateAndCleanProperty($purchaseDate, 'purchase date', 'date');
		
		$this->modify('quantity', $quantity, $id, $parentId);
		$this->modify('purchaseDate', $purchaseDate, $id, $parentId);
		$this->modify('equipmentType', $type, $id, $parentId);
		
	}
	
	public function getEquipment($id, $parentId) {
		
		return $this->read($id, $parentId);
		
	}
	
	public function getEquipments($parentId) {
		
		return $this->readAll($parentId);
		
	}
	
	public function deleteEquipment($id, $parentId) {
		
		$this->delete($id, $parentId);
		
	}
	
	public function getEquipmentId(Equipment $equipment, $parentId) {
		
		return $this->getId($equipment, $parentId);
		
	}
	
}