<?php
/**
 * Controller used to manipulate the menu items in a menu.
 * @author maximeplante
 *
 */
class MenuItemController extends CRUDController {

	/**
	 * Constructor
	 */
	public function __construct() {

		parent::__construct('MenuItem', 'Menu item', 'FoodList', 'Menu');

	}

	/**
	 * Creates a new menu item
	 * 
	 * @param string $name the name of the menu item
	 * @param double $price the price (units are dollars)
	 * @param int $menuId the id of the containing menu
	 * 
	 * @throws Exception contains an error message that can be directly presented to the user.
	 * 
	 * @return MenuItem the menu item created
	 */
	public function createMenuItem($name, $price, $menuId) {

		$name = $this->validateAndCleanProperty($name, 'name', 'string');
		
		$price = $this->validateAndCleanProperty($price, 'price', 'double');

		$menuItem = new MenuItem($name, $price);

		$this->create($menuItem, $menuId);

		return $menuItem;

	}

	/**
	 * Modifies a menu item
	 * 
	 * @param string $name the new name of the menu item
	 * @param double $price the new price (units are dollars)
	 * @param int $id the id of the menu item to modify
	 * @param int $menuId the id of the containing menu
	 * 
	 * @throws Exception contains an error message that can be directly presented to the user.
	 */
	public function editMenuItem($name, $price, $id, $menuId) {

		$name = $this->validateAndCleanProperty($name, 'name', 'string');
		
		$price = $this->validateAndCleanProperty($price, 'price', 'double');

		$this->modify('name', $name, $id, $menuId);
		$this->modify('price', $price, $id, $menuId);

	}

	/**
	 * Get a menu item
	 * 
	 * @param int $id the id of the menu item
	 * @param int $menuId the id of the containing menu
	 * 
	 * @throws Exception contains an error message that can be directly presented to the user.
	 * 
	 * @return MenuItem the menu item associated with the id
	 */
	public function getMenuItem($id, $menuId) {

		return $this->read($id, $menuId);

	}
	
	/**
	 * Get all the menu items of a menu.
	 * 
	 * @param int $menuId the id of the menu.
	 * 
	 * @throws Exception contains an error message that can be directly presented to the user.
	 * 
	 * @return MenuItem[] the menu items
	 */
	public function getMenuItems($menuId) {
	
		return $this->readAll($menuId);
	
	}
	
	/**
	 * Deletes a menu
	 * 
	 * @param int $id the id of the menu to delete
	 * @param int $menuId the id of the containing menu
	 * 
	 * @throws Exception contains an error message that can be directly presented to the user.
	 */
	public function deleteMenuItem($id, $menuId) {

		$this->delete($id, $menuId);

	}

	/**
	 * Get the id of a menu item
	 * 
	 * @param MenuItem $menuItem the menu item
	 * @param int $menuId the id of the containing menu
	 * 
	 * @throws Exception contains an error message that can be directly presented to the user.
	 * 
	 * @return int the id of the menu item
	 */
	public function getMenuItemId(MenuItem $menuItem, $menuId) {

		return $this->getId($menuItem, $menuId);

	}

}