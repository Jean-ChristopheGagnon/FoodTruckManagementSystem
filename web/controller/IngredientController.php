<?php

class IngredientController extends CRUDController {

	/**
	 * Constructor
	 */
	public function __construct() {

		parent::__construct('Supply', 'ingredient', 'MenuItem', 'menu item', 'foodList', 'menu');

	}

	public function createIngredient($quantity, SupplyType $ingredientType, $menuItemId, $menuId) {
		
		$quantity = $this->validateAndCleanProperty($quantity, 'quantity', 'int');

		$ingredient = new Supply($quantity, $ingredientType);

		$this->create($ingredient, $menuItemId, $menuId);

		return $ingredient;

	}


	public function editIngredient($quantity, SupplyType $ingredientType, $id, $menuItemId, $menuId) {

		$quantity = $this->validateAndCleanProperty($quantity, 'quantity', 'int');

		$this->modify('quantity', $quantity, $id, $menuItemId, $menuId);
		$this->modify('supplyType', $ingredientType, $id, $menuItemId, $menuId);

	}

	public function getIngredient($id, $menuItemId, $menuId) {

		return $this->read($id, $menuItemId, $menuId);

	}
	
	public function getIngredients($menuItemId, $menuId) {
	
		return $this->readAll($menuItemId, $menuId);
	
	}
	
	public function deleteIngredient($id, $menuItemId, $menuId) {

		$this->delete($id, $menuItemId, $menuId);

	}

	public function getIngredientId(MenuItem $menuItem, $menuItemId, $menuId) {

		return $this->getId($menuItem, $menuItemId, $menuId);

	}

}