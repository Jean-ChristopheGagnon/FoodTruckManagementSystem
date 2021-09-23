<?php

class FoodTruckController extends CRUDController {

	public function __construct() {

		parent::__construct('FoodTruck', 'food truck');

	}
	
	public function createFoodTruck($location, Menu $menu) {
		
		$location = $this->validateAndCleanProperty($location, 'location', 'string');
		
		$foodTruck = new FoodTruck($location, $menu);
		
		$this->create($foodTruck);
		
		return $foodTruck;
		
	}
	
	public function editFoodTruck($location, Menu $menu, $id) {
		
		$location = $this->validateAndCleanProperty($location, 'location', 'string');
		
		$this->modify('location', $location, $id);
		$this->modify('menu', $menu, $id);
		
	}
	
	public function getFoodTruck($id) {
		
		return $this->read($id);
		
	}
	
	public function getFoodTrucks() {
		
		return $this->readAll();
		
	}
	
	public function deleteFoodTruck($id) {
		
		$this->delete($id);
		
	}
	
	public function getFoodTruckId(FoodTruck $foodTruck) {
		
		return $this->getId($foodTruck);
		
	}
	
}