<?php
/**
 * Controller for manipulating the menus.
 * @author maximeplante
 *
 */
class MenuController extends CRUDController {
	
	/**
	 * Contructor
	 */
	public function __construct() {
		
		parent::__construct('FoodList', 'Menu');
		
	}
	
	/**
	 * Creates a Menu.
	 * 
	 * @param string $name the name of the menu
	 * 
	 * @throws Exception contains an error message that can be directly presented to the user.
	 * 
	 * @return Menu the new menu
	 */
	public function createMenu($name) {
		
		$name = $this->validateAndCleanProperty($name, 'name', 'string');
		
		$menu = new Menu($name);
		
		$this->create($menu);
		
		return $menu;
		
	}
	
	/**
	 * Modifies a Menu's properties
	 * 
	 * @param int $id the id of the menu to edit
	 * @param string $name the new name of the menu
	 * 
	 * @throws Exception contains an error message that can be directly presented to the user.
	 */
	public function editMenu($id, $name) {
		
		$name = $this->validateAndCleanProperty($name, 'name', 'string');
		
		$this->modify('name', $name, $id);
		
	}
	
	/**
	 * Get the menu associated wit the id.
	 * 
	 * @param int $id the id of the menu
	 * 
	 * @throws Exception contains an error message that can be directly presented to the user.
	 * 
	 * @return Menu the menu associated with the id
	 */
	public function getMenu($id) {
		
		return $this->read($id);
		
	}
	
	/**
	 * Returns all the menus
	 * 
	 * @throws Exception contains an error message that can be directly presented to the user.
	 * 
	 * @return Menu[] the menus
	 */
	public function getMenus() {
		
		return $this->readAll();
		
	}
	
	/**
	 * Delete the menu associated with the id
	 * 
	 * @param int $id the id of the menu
	 * 
	 * @throws Exception contains an error message that can be directly presented to the user.
	 */
	public function deleteMenu($id) {
		
		$this->delete($id);
		
	}
	
	/**
	 * Get the id of a menu
	 * 
	 * @param Menu $menu the menu
	 * 
	 * @throws Exception contains an error message that can be directly presented to the user.
	 * 
	 * @return int the id of the menu
	 */
	public function getMenuId(Menu $menu) {
		
		return $this->getId($menu);
		
	}
	
}