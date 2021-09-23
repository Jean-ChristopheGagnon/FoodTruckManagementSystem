<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-fcfceb9 modeling language!*/

class FoodTruck
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FoodTruck Attributes
  private $location;

  //FoodTruck Associations
  private $equipment;
  private $staffs;
  private $menu;
  private $supplies;
  private $orders;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aLocation, $aMenu)
  {
    $this->location = $aLocation;
    $this->equipment = array();
    $this->staffs = array();
    if (!$this->setMenu($aMenu))
    {
      throw new Exception("Unable to create FoodTruck due to aMenu");
    }
    $this->supplies = array();
    $this->orders = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setLocation($aLocation)
  {
    $wasSet = false;
    $this->location = $aLocation;
    $wasSet = true;
    return $wasSet;
  }

  public function getLocation()
  {
    return $this->location;
  }

  public function getEquipment_index($index)
  {
    $aEquipment = $this->equipment[$index];
    return $aEquipment;
  }

  public function getEquipment()
  {
    $newEquipment = $this->equipment;
    return $newEquipment;
  }

  public function numberOfEquipment()
  {
    $number = count($this->equipment);
    return $number;
  }

  public function hasEquipment()
  {
    $has = $this->numberOfEquipment() > 0;
    return $has;
  }

  public function indexOfEquipment($aEquipment)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->equipment as $equipment)
    {
      if ($equipment->equals($aEquipment))
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

  public function getMenu()
  {
    return $this->menu;
  }

  public function getSupply_index($index)
  {
    $aSupply = $this->supplies[$index];
    return $aSupply;
  }

  public function getSupplies()
  {
    $newSupplies = $this->supplies;
    return $newSupplies;
  }

  public function numberOfSupplies()
  {
    $number = count($this->supplies);
    return $number;
  }

  public function hasSupplies()
  {
    $has = $this->numberOfSupplies() > 0;
    return $has;
  }

  public function indexOfSupply($aSupply)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->supplies as $supply)
    {
      if ($supply->equals($aSupply))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getOrder_index($index)
  {
    $aOrder = $this->orders[$index];
    return $aOrder;
  }

  public function getOrders()
  {
    $newOrders = $this->orders;
    return $newOrders;
  }

  public function numberOfOrders()
  {
    $number = count($this->orders);
    return $number;
  }

  public function hasOrders()
  {
    $has = $this->numberOfOrders() > 0;
    return $has;
  }

  public function indexOfOrder($aOrder)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->orders as $order)
    {
      if ($order->equals($aOrder))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfEquipment()
  {
    return 0;
  }

  public function addEquipment($aEquipment)
  {
    $wasAdded = false;
    if ($this->indexOfEquipment($aEquipment) !== -1) { return false; }
    $this->equipment[] = $aEquipment;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeEquipment($aEquipment)
  {
    $wasRemoved = false;
    if ($this->indexOfEquipment($aEquipment) != -1)
    {
      unset($this->equipment[$this->indexOfEquipment($aEquipment)]);
      $this->equipment = array_values($this->equipment);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addEquipmentAt($aEquipment, $index)
  {  
    $wasAdded = false;
    if($this->addEquipment($aEquipment))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEquipment()) { $index = $this->numberOfEquipment() - 1; }
      array_splice($this->equipment, $this->indexOfEquipment($aEquipment), 1);
      array_splice($this->equipment, $index, 0, array($aEquipment));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveEquipmentAt($aEquipment, $index)
  {
    $wasAdded = false;
    if($this->indexOfEquipment($aEquipment) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEquipment()) { $index = $this->numberOfEquipment() - 1; }
      array_splice($this->equipment, $this->indexOfEquipment($aEquipment), 1);
      array_splice($this->equipment, $index, 0, array($aEquipment));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addEquipmentAt($aEquipment, $index);
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

  public function setMenu($aNewMenu)
  {
    $wasSet = false;
    if ($aNewMenu != null)
    {
      $this->menu = $aNewMenu;
      $wasSet = true;
    }
    return $wasSet;
  }

  public static function minimumNumberOfSupplies()
  {
    return 0;
  }

  public function addSupply($aSupply)
  {
    $wasAdded = false;
    if ($this->indexOfSupply($aSupply) !== -1) { return false; }
    $this->supplies[] = $aSupply;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeSupply($aSupply)
  {
    $wasRemoved = false;
    if ($this->indexOfSupply($aSupply) != -1)
    {
      unset($this->supplies[$this->indexOfSupply($aSupply)]);
      $this->supplies = array_values($this->supplies);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addSupplyAt($aSupply, $index)
  {  
    $wasAdded = false;
    if($this->addSupply($aSupply))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfSupplies()) { $index = $this->numberOfSupplies() - 1; }
      array_splice($this->supplies, $this->indexOfSupply($aSupply), 1);
      array_splice($this->supplies, $index, 0, array($aSupply));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveSupplyAt($aSupply, $index)
  {
    $wasAdded = false;
    if($this->indexOfSupply($aSupply) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfSupplies()) { $index = $this->numberOfSupplies() - 1; }
      array_splice($this->supplies, $this->indexOfSupply($aSupply), 1);
      array_splice($this->supplies, $index, 0, array($aSupply));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addSupplyAt($aSupply, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfOrders()
  {
    return 0;
  }

  public function addOrder($aOrder)
  {
    $wasAdded = false;
    if ($this->indexOfOrder($aOrder) !== -1) { return false; }
    $this->orders[] = $aOrder;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeOrder($aOrder)
  {
    $wasRemoved = false;
    if ($this->indexOfOrder($aOrder) != -1)
    {
      unset($this->orders[$this->indexOfOrder($aOrder)]);
      $this->orders = array_values($this->orders);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addOrderAt($aOrder, $index)
  {  
    $wasAdded = false;
    if($this->addOrder($aOrder))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfOrders()) { $index = $this->numberOfOrders() - 1; }
      array_splice($this->orders, $this->indexOfOrder($aOrder), 1);
      array_splice($this->orders, $index, 0, array($aOrder));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveOrderAt($aOrder, $index)
  {
    $wasAdded = false;
    if($this->indexOfOrder($aOrder) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfOrders()) { $index = $this->numberOfOrders() - 1; }
      array_splice($this->orders, $this->indexOfOrder($aOrder), 1);
      array_splice($this->orders, $index, 0, array($aOrder));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addOrderAt($aOrder, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->equipment = array();
    $this->staffs = array();
    $this->menu = null;
    $this->supplies = array();
    $this->orders = array();
  }

}
?>