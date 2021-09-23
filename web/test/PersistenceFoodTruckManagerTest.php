<?php
/*
    NOTICE : This file is greatly inspired from PersistenceEventRegistrationTest.php
    created during the assignment 1 of ECSE 321.
*/

require_once __DIR__.'/../persistence/PersistenceFoodTruckManager.php';
require_once __DIR__.'/../model/FoodTruckManagementSystem.php';
require_once __DIR__.'/../model/Menu.php';

class PersistenceFoodTruckManagerTest extends PHPUnit_Framework_TestCase {

    protected $pft;

    protected function setUp() {
        $this->pft = new PersistenceFoodTruckManager();
    }

    public function testPersistence() {
        // Creating a basic menu
        $ftm = FoodTruckManagementSystem::getInstance();
        $menu = new Menu("Menu 1");
        $ftm->addFoodList($menu);

        // Store the data
        $this->pft->writeDataToStore($ftm);

        /* Delete the menu to be sure that when the data is reloaded there is
           no previous menu instance already existing.
        */
        $ftm->delete();

        // Check if it was effectively deleted
        $this->assertEquals(0, count($ftm->getFoodList()));

        // Reload the stored data (the menu)
        $ftm = $this->pft->loadDataFromStore();

        // Check if the menu previously created has been successfully loaded.
        $this->assertEquals(1, count($ftm->getFoodList()));
        $restoredMenu = $ftm->getFoodList_index(0);
        $this->assertEquals("Menu 1", $restoredMenu->getName());
    }

}
