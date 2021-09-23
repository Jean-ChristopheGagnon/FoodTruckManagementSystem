<?php

require_once __DIR__.'/../autoload.php';

class SupplyTypeControllerTest extends PHPUnit_Framework_TestCase {

    protected $controller;
    protected $persistence;
    protected $manager;

    protected function setUp() {
	    // Reset everything for every single test
        $this->controller = new SupplyTypeController();
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
	    $name = "SupplyTypeName";
	    
	    $supplyType = $this->controller->createSupplyType($name);
	    
	    if ($supplyType->getName() !== $name) {
		    $this->fail();
	    }
	    
	    $this->reloadManager();
	    
	    if ($this->manager->numberOfSupplyTypes() !== 1) {
		    $this->fail();
	    }
	    
	    if ($this->manager->getSupplyType_index(0)->getName() !== $name) {
		    $this->fail();
	    }
	    
	    // TEST WITH SPACE NAME
	    $name = "  ";
	    
	    $exception = null;
	    try {
		    $supplyType = $this->controller->createSupplyType($name);
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
		    $supplyType = $this->controller->createSupplyType($name);
	    } catch (Exception $e) {
		    $exception = $e;
	    }
	    if ($e === null) {
		    $this->fail();
	    }
	    
    }
    
    public function testEdit() {
	    
	    // GOOD TEST
	    $name = "SupplyTypeName";
	    $newName = "NewSupplyTypeName";
	    
	    $supplyType = $this->controller->createSupplyType($name);
	    
	    $this->controller->editSupplyType($newName, 0);
	    
	    $this->reloadManager();
	    
	    if ($this->manager->numberOfSupplyTypes() !== 1) {
		    $this->fail();
	    }
	    
	    if ($this->manager->getSupplyType_index(0)->getName() !== $newName) {
		    $this->fail();
	    }
	    
	    // TEST WITH SPACE NAME
	    $newName = "  ";
	    
	    $exception = null;
	    try {
		    $supplyType = $this->controller->editSupplyType($newName, 0);
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
		    $supplyType = $this->controller->editSupplyType($newName, 0);
	    } catch (Exception $e) {
		    $exception = $e;
	    }
	    if ($e === null) {
		    $this->fail();
	    }
	    
    }
    
    public function testGet() {
	    
	    $name = "SupplyTypeName";
	    
	    $this->controller->createSupplyType($name);
	    
	    // GET AN EXISTING SUPPLY TYPE
	    try {
		    $supplyType = $this->controller->getSupplyType(0);
	    } catch (Exception $e) {
		    $this->fail();
	    }
	    
	    if ($supplyType->getName() !== $name) {
		    $this->fail();
	    }
	    
	    // TRY TO GET A NON-EXISTENT SUPPLY TYPE
	    $exception = null;
	    try {
		    $this->controller->getSupplyType(1);
	    } catch (Exception $e) {
		    $exception = $e;
	    }
	    if ($exception === null) {
		    $this->fail();
	    }
	    
	    // TRY TO CREATE MORE THAN ONE ELEMENT
	    $name2 = "SupplyTypeName2";
	    
	    $this->controller->createSupplyType($name2);
	    
	    try {
		    $supplyTypes = $this->controller->getSupplyTypes();
	    } catch (Exception $e) {
		    $this->fail();
	    }
	    
	    if (count($supplyTypes) !== 2) {
		    $this->fail();
	    }
	    
	    if ($supplyTypes[1]->getName() !== $name2) {
		    $this->fail();
	    }
	    
    }
    
    public function testDelete() {
	    
	    // GOOD TEST
	    $name = "SupplyTypeName";
	    
	    $this->controller->createSupplyType($name);
	    
	    if (count($this->controller->getSupplyTypes()) !== 1) {
		    $this->fail();
	    }
	    
	    $this->controller->deleteSupplyType(0);
	    
	    if (count($this->controller->getSupplyTypes()) !== 0) {
		    $this->fail();
	    }
	    
	    // TRY TO DELETE WHILE THERE IS NOTHING
	    $exception = null;
	    try {
		    $this->controller->deleteSupplyType(0);
	    } catch (Exception $e) {
		    $exception = $e;
	    }
	    if ($exception === null) {
		    $this->fail();
	    }
	    
    }
    
    public function testGetId() {
	    
	    // GOOD TEST
	    $name = "SupplyTypeName";
	    $name2 = "SupplyTypeName2";
	    
	    $this->controller->createSupplyType($name);
		$supplyType2 = $this->controller->createSupplyType($name2);
		
		if ($this->controller->getSupplyTypeId($supplyType2) !== 1) {
			$this->fail();
		}
		
		// TEST WITH A SUPPLY TYPE THAT IS NOT IN THE PERSISTENCE
		$supplyType3 = new SupplyType("RandomSupplyType");
		
		$exception = null;
	    try {
		    var_dump($this->controller->getSupplyTypeId($supplyType3));
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
