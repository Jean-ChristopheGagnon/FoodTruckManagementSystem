<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-fcfceb9 modeling language!*/

class FoodTruckManagementSystem
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static $theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FoodTruckManagementSystem Associations
  private $foodTrucks;
  private $staffs;
  private $equipmentTypes;
  private $supplyTypes;

  /**
   * 
   * List of all the menus. It couldn't have been named menus because of a bug in umple.
   * 
   * @see https://github.com/umple/umple/issues/903
   */
  private $foodList;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private function __construct()
  {
    $this->foodTrucks = array();
    $this->staffs = array();
    $this->equipmentTypes = array();
    $this->supplyTypes = array();
    $this->foodList = array();
  }

  public static function getInstance()
  {
    if(self::$theInstance == null)
    {
      self::$theInstance = new FoodTruckManagementSystem();
    }
    return self::$theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getFoodTruck_index($index)
  {
    $aFoodTruck = $this->foodTrucks[$index];
    return $aFoodTruck;
  }

  public function getFoodTrucks()
  {
    $newFoodTrucks = $this->foodTrucks;
    return $newFoodTrucks;
  }

  public function numberOfFoodTrucks()
  {
    $number = count($this->foodTrucks);
    return $number;
  }

  public function hasFoodTrucks()
  {
    $has = $this->numberOfFoodTrucks() > 0;
    return $has;
  }

  public function indexOfFoodTruck($aFoodTruck)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->foodTrucks as $foodTruck)
    {
      if ($foodTruck->equals($aFoodTruck))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getStaff_index($index)
  {
    $aStaff = $this->staffs[$index];
    return $aStaff;
  }

  public function getStaffs()
  {
    $newStaffs = $this->staffs;
    return $newStaffs;
  }

  public function numberOfStaffs()
  {
    $number = count($this->staffs);
    return $number;
  }

  public function hasStaffs()
  {
    $has = $this->numberOfStaffs() > 0;
    return $has;
  }

  public function indexOfStaff($aStaff)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->staffs as $staff)
    {
      if ($staff->equals($aStaff))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getEquipmentType_index($index)
  {
    $aEquipmentType = $this->equipmentTypes[$index];
    return $aEquipmentType;
  }

  public function getEquipmentTypes()
  {
    $newEquipmentTypes = $this->equipmentTypes;
    return $newEquipmentTypes;
  }

  public function numberOfEquipmentTypes()
  {
    $number = count($this->equipmentTypes);
    return $number;
  }

  public function hasEquipmentTypes()
  {
    $has = $this->numberOfEquipmentTypes() > 0;
    return $has;
  }

  public function indexOfEquipmentType($aEquipmentType)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->equipmentTypes as $equipmentType)
    {
      if ($equipmentType->equals($aEquipmentType))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getSupplyType_index($index)
  {
    $aSupplyType = $this->supplyTypes[$index];
    return $aSupplyType;
  }

  public function getSupplyTypes()
  {
    $newSupplyTypes = $this->supplyTypes;
    return $newSupplyTypes;
  }

  public function numberOfSupplyTypes()
  {
    $number = count($this->supplyTypes);
    return $number;
  }

  public function hasSupplyTypes()
  {
    $has = $this->numberOfSupplyTypes() > 0;
    return $has;
  }

  public function indexOfSupplyType($aSupplyType)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->supplyTypes as $supplyType)
    {
      if ($supplyType->equals($aSupplyType))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getFoodList_index($index)
  {
    $aFoodList = $this->foodList[$index];
    return $aFoodList;
  }

  public function getFoodList()
  {
    $newFoodList = $this->foodList;
    return $newFoodList;
  }

  public function numberOfFoodList()
  {
    $number = count($this->foodList);
    return $number;
  }

  public function hasFoodList()
  {
    $has = $this->numberOfFoodList() > 0;
    return $has;
  }

  public function indexOfFoodList($aFoodList)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->foodList as $foodList)
    {
      if ($foodList->equals($aFoodList))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfFoodTrucks()
  {
    return 0;
  }

  public function addFoodTruck($aFoodTruck)
  {
    $wasAdded = false;
    if ($this->indexOfFoodTruck($aFoodTruck) !== -1) { return false; }
    $this->foodTrucks[] = $aFoodTruck;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeFoodTruck($aFoodTruck)
  {
    $wasRemoved = false;
    if ($this->indexOfFoodTruck($aFoodTruck) != -1)
    {
      unset($this->foodTrucks[$this->indexOfFoodTruck($aFoodTruck)]);
      $this->foodTrucks = array_values($this->foodTrucks);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addFoodTruckAt($aFoodTruck, $index)
  {  
    $wasAdded = false;
    if($this->addFoodTruck($aFoodTruck))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfFoodTrucks()) { $index = $this->numberOfFoodTrucks() - 1; }
      array_splice($this->foodTrucks, $this->indexOfFoodTruck($aFoodTruck), 1);
      array_splice($this->foodTrucks, $index, 0, array($aFoodTruck));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveFoodTruckAt($aFoodTruck, $index)
  {
    $wasAdded = false;
    if($this->indexOfFoodTruck($aFoodTruck) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfFoodTrucks()) { $index = $this->numberOfFoodTrucks() - 1; }
      array_splice($this->foodTrucks, $this->indexOfFoodTruck($aFoodTruck), 1);
      array_splice($this->foodTrucks, $index, 0, array($aFoodTruck));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addFoodTruckAt($aFoodTruck, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfStaffs()
  {
    return 0;
  }

  public function addStaff($aStaff)
  {
    $wasAdded = false;
    if ($this->indexOfStaff($aStaff) !== -1) { return false; }
    $this->staffs[] = $aStaff;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeStaff($aStaff)
  {
    $wasRemoved = false;
    if ($this->indexOfStaff($aStaff) != -1)
    {
      unset($this->staffs[$this->indexOfStaff($aStaff)]);
      $this->staffs = array_values($this->staffs);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addStaffAt($aStaff, $index)
  {  
    $wasAdded = false;
    if($this->addStaff($aStaff))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfStaffs()) { $index = $this->numberOfStaffs() - 1; }
      array_splice($this->staffs, $this->indexOfStaff($aStaff), 1);
      array_splice($this->staffs, $index, 0, array($aStaff));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveStaffAt($aStaff, $index)
  {
    $wasAdded = false;
    if($this->indexOfStaff($aStaff) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfStaffs()) { $index = $this->numberOfStaffs() - 1; }
      array_splice($this->staffs, $this->indexOfStaff($aStaff), 1);
      array_splice($this->staffs, $index, 0, array($aStaff));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addStaffAt($aStaff, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfEquipmentTypes()
  {
    return 0;
  }

  public function addEquipmentType($aEquipmentType)
  {
    $wasAdded = false;
    if ($this->indexOfEquipmentType($aEquipmentType) !== -1) { return false; }
    $this->equipmentTypes[] = $aEquipmentType;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeEquipmentType($aEquipmentType)
  {
    $wasRemoved = false;
    if ($this->indexOfEquipmentType($aEquipmentType) != -1)
    {
      unset($this->equipmentTypes[$this->indexOfEquipmentType($aEquipmentType)]);
      $this->equipmentTypes = array_values($this->equipmentTypes);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addEquipmentTypeAt($aEquipmentType, $index)
  {  
    $wasAdded = false;
    if($this->addEquipmentType($aEquipmentType))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEquipmentTypes()) { $index = $this->numberOfEquipmentTypes() - 1; }
      array_splice($this->equipmentTypes, $this->indexOfEquipmentType($aEquipmentType), 1);
      array_splice($this->equipmentTypes, $index, 0, array($aEquipmentType));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveEquipmentTypeAt($aEquipmentType, $index)
  {
    $wasAdded = false;
    if($this->indexOfEquipmentType($aEquipmentType) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEquipmentTypes()) { $index = $this->numberOfEquipmentTypes() - 1; }
      array_splice($this->equipmentTypes, $this->indexOfEquipmentType($aEquipmentType), 1);
      array_splice($this->equipmentTypes, $index, 0, array($aEquipmentType));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addEquipmentTypeAt($aEquipmentType, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfSupplyTypes()
  {
    return 0;
  }

  public function addSupplyType($aSupplyType)
  {
    $wasAdded = false;
    if ($this->indexOfSupplyType($aSupplyType) !== -1) { return false; }
    $this->supplyTypes[] = $aSupplyType;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeSupplyType($aSupplyType)
  {
    $wasRemoved = false;
    if ($this->indexOfSupplyType($aSupplyType) != -1)
    {
      unset($this->supplyTypes[$this->indexOfSupplyType($aSupplyType)]);
      $this->supplyTypes = array_values($this->supplyTypes);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addSupplyTypeAt($aSupplyType, $index)
  {  
    $wasAdded = false;
    if($this->addSupplyType($aSupplyType))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfSupplyTypes()) { $index = $this->numberOfSupplyTypes() - 1; }
      array_splice($this->supplyTypes, $this->indexOfSupplyType($aSupplyType), 1);
      array_splice($this->supplyTypes, $index, 0, array($aSupplyType));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveSupplyTypeAt($aSupplyType, $index)
  {
    $wasAdded = false;
    if($this->indexOfSupplyType($aSupplyType) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfSupplyTypes()) { $index = $this->numberOfSupplyTypes() - 1; }
      array_splice($this->supplyTypes, $this->indexOfSupplyType($aSupplyType), 1);
      array_splice($this->supplyTypes, $index, 0, array($aSupplyType));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addSupplyTypeAt($aSupplyType, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfFoodList()
  {
    return 0;
  }

  public function addFoodList($aFoodList)
  {
    $wasAdded = false;
    if ($this->indexOfFoodList($aFoodList) !== -1) { return false; }
    $this->foodList[] = $aFoodList;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeFoodList($aFoodList)
  {
    $wasRemoved = false;
    if ($this->indexOfFoodList($aFoodList) != -1)
    {
      unset($this->foodList[$this->indexOfFoodList($aFoodList)]);
      $this->foodList = array_values($this->foodList);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addFoodListAt($aFoodList, $index)
  {  
    $wasAdded = false;
    if($this->addFoodList($aFoodList))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfFoodList()) { $index = $this->numberOfFoodList() - 1; }
      array_splice($this->foodList, $this->indexOfFoodList($aFoodList), 1);
      array_splice($this->foodList, $index, 0, array($aFoodList));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveFoodListAt($aFoodList, $index)
  {
    $wasAdded = false;
    if($this->indexOfFoodList($aFoodList) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfFoodList()) { $index = $this->numberOfFoodList() - 1; }
      array_splice($this->foodList, $this->indexOfFoodList($aFoodList), 1);
      array_splice($this->foodList, $index, 0, array($aFoodList));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addFoodListAt($aFoodList, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->foodTrucks = array();
    $this->staffs = array();
    $this->equipmentTypes = array();
    $this->supplyTypes = array();
    $this->foodList = array();
  }

}
?>