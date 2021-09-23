<?php

require_once __DIR__.'/../autoload.php';

class MenuControllerTest extends PHPUnit_Framework_TestCase {

    protected $controller;
    protected $persistence;
    protected $manager;

    protected function setUp() {
	    // Reset everything for every single test
        $this->controller = new MenuController();
        $this->persistence = new PersistenceFoodTruckManager();
        $this->manager = $this->persistence->load();
        $this->manager->delete();
        $this->persistence->store($this->manager);
    }
    
    protected tearDown() {
	    // To be sure nothing is remaining from the tests
	    $this->manager->delete();
        $this->persistence->store($this->manager);
    }
    
    public function testCreate() {
	    
	    // GOOD TEST
	    $name = "MenuName";
	    
	    $menu = $this->controller->createMenu($name);
	    
	    if ($menu->getName() !== $name) {
		    $this->fail();
	    }
	    
	    $this->reloadManager();
	    
	    if ($this->manager->numberOfFoodList() !== 1) {
		    $this->fail();
	    }
	    
	    if ($this->manager->getFoodList_index(0)->getName() !== $name) {
		    $this->fail();
	    }
	    
	    // TEST WITH SPACE NAME
	    $name = "  ";
	    
	    $exception = null;
	    try {
		    $menu = $this->controller->createMenu($name);
	    } catch (Exception $e) {
		    $exception = $e;
	    }
	    if ($e === null) {
		    $this->fail();
	    }
	    
	    // TEST WITH NULL NAME
	    $name = null;
	    
	    $exception = null;
	    try {
		    $menu = $this->controller->createMenu($name);
	    } catch (Exception $e) {
		    $exception = $e;
	    }
	    if ($e === null) {
		    $this->fail();
	    }
	    
    }
    
    public function testEdit() {
	    
	    // GOOD TEST
	    $name = "MenuName";
	    $newName = "NewMenuName";
	    
	    $menu = $this->controller->createMenu($name);
	    
	    $this->controller->editMenu(0, $newName);
	    
	    $this->reloadManager();
	    
	    if ($this->manager->numberOfFoodList() !== 1) {
		    $this->fail();
	    }
	    
	    if ($this->manager->getFoodList_index(0)->getName() !== $newName) {
		    $this->fail();
	    }
	    
	    // TEST WITH SPACE NAME
	    $newName = "  ";
	    
	    $exception = null;
	    try {
		    $menu = $this->controller->editMenu(0, $newName);
	    } catch (Exception $e) {
		    $exception = $e;
	    }
	    if ($e === null) {
		    $this->fail();
	    }
	    
	    // TEST WITH NULL NAME
	    $newName = null;
	    
	    $exception = null;
	    try {
		    $menu = $this->controller->editMenu(0, $newName);
	    } catch (Exception $e) {
		    $exception = $e;
	    }
	    if ($e === null) {
		    $this->fail();
	    }
	    
    }
    
    public function testGet() {
	    
	    $name = "MenuName";
	    
	    $this->controller->createMenu($name);
	    
	    // GET AN EXISTING MENU
	    try {
		    $menu = $this->controller->getMenu(0);
	    } catch (Exception $e) {
		    $this->fail();
	    }
	    
	    if ($menu->getName() !== $name) {
		    $this->fail();
	    }
	    
	    // TRY TO GET A NON-EXISTENT MENU
	    $exception = null;
	    try {
		    $this->controller->getMenu(1);
	    } catch (Exception $e) {
		    $exception = $e;
	    }
	    if ($exception === null) {
		    $this->fail();
	    }
	    
	    // TRY TO CREATE MORE THAN ONE ELEMENT
	    $name2 = "MenuName2";
	    
	    $this->controller->createMenu($name2);
	    
	    try {
		    $menus = $this->controller->getMenus();
	    } catch (Exception $e) {
		    $this->fail();
	    }
	    
	    if (count($menus) !== 2) {
		    $this->fail();
	    }
	    
	    if ($menus[1]->getName() !== $name2) {
		    $this->fail();
	    }
	    
    }
    
    public function testDelete() {
	    
	    // GOOD TEST
	    $name = "MenuName";
	    
	    $this->controller->createMenu($name);
	    
	    if (count($this->controller->getMenus()) !== 1) {
		    $this->fail();
	    }
	    
	    $this->controller->deleteMenu(0);
	    
	    if (count($this->controller->getMenus()) !== 0) {
		    $this->fail();
	    }
	    
	    // TRY TO DELETE WHILE THERE IS NOTHING
	    $exception = null;
	    try {
		    $this->controller->deleteMenu(0);
	    } catch (Exception $e) {
		    $exception = $e;
	    }
	    if ($exception === null) {
		    $this->fail();
	    }
	    
    }
    
    public function testGetId() {
	    
	    // GOOD TEST
	    $name = "MenuName";
	    $name2 = "MenuName2";
	    
	    $this->controller->createMenu($name);
		$menu2 = $this->controller->createMenu($name2);
		
		if ($this->controller->getMenuId($menu2) !== 1) {
			$this->fail();
		}
		
		// TEST WITH A MENU THAT IS NOT IN THE PERSISTENCE
		$menu3 = new Menu("RandomMenu");
		
		$exception = null;
	    try {
		    var_dump($this->controller->getMenuId($menu3));
	    } catch (Exception $e) {
		    $exception = $e;
	    }
	    if ($exception === null) {
		    $this->fail();
	    }
	    
    }
    
    /**
	 * Helper function to refresh the manager used by the tests
	 */
    private function reloadManager() {
	    
	    $this->persistence = new PersistenceFoodTruckManager();
        $this->manager = $this->persistence->load();
	    
    }

}
