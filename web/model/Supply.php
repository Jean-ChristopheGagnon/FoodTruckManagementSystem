<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-fcfceb9 modeling language!*/

class Supply
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Supply Attributes
  private $quantity;

  //Supply Associations
  private $supplyType;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aQuantity, $aSupplyType)
  {
    $this->quantity = $aQuantity;
    if (!$this->setSupplyType($aSupplyType))
    {
      throw new Exception("Unable to create Supply due to aSupplyType");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setQuantity($aQuantity)
  {
    $wasSet = false;
    $this->quantity = $aQuantity;
    $wasSet = true;
    return $wasSet;
  }

  public function getQuantity()
  {
    return $this->quantity;
  }

  public function getSupplyType()
  {
    return $this->supplyType;
  }

  public function setSupplyType($aNewSupplyType)
  {
    $wasSet = false;
    if ($aNewSupplyType != null)
    {
      $this->supplyType = $aNewSupplyType;
      $wasSet = true;
    }
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->supplyType = null;
  }

}
?>