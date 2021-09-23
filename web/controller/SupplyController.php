<?php


class SupplyController extends CRUDController {
	
	/**
	 * Contructor
	 */
	public function __construct() {
		
		parent::__construct('Supply', 'supply', 'FoodTruck', 'food truck');
		
	}
	
	public function createSupply($quantity, SupplyType $type, $parentId) {
		
		$quantity = $this->validateAndCleanProperty($quantity, 'quantity', 'int');
		
		$supply = new Supply($quantity, $type);
		
		$this->create($supply, $parentId);
		
		return $supply;
		
	}
	
	public function editSupply($quantity, SupplyType $type, $id, $parentId) {
		
		$quantity = $this->validateAndCleanProperty($quantity, 'quantity', 'int');
		
		$this->modify('quantity', $quantity, $id, $parentId);
		$this->modify('supplyType', $type, $id, $parentId);
		
	}
	
	public function getSupply($id, $parentId) {
		
		return $this->read($id, $parentId);
		
	}
	
	public function getSupplies($parentId) {
		
		return $this->readAll($parentId);
		
	}
	
	public function deleteSupply($id, $parentId) {
		
		$this->delete($id, $parentId);
		
	}
	
	public function getSupplyId(Supply $supply, $parentId) {
		
		return $this->getId($supply, $parentId);
		
	}
	
}