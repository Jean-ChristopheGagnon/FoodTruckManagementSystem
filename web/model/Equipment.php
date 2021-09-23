<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-fcfceb9 modeling language!*/

class Equipment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Equipment Attributes
  private $quantity;
  private $purchaseDate;

  //Equipment Associations
  private $equipmentType;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aQuantity, $aPurchaseDate, $aEquipmentType)
  {
    $this->quantity = $aQuantity;
    $this->purchaseDate = $aPurchaseDate;
    if (!$this->setEquipmentType($aEquipmentType))
    {
      throw new Exception("Unable to create Equipment due to aEquipmentType");
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

  public function setPurchaseDate($aPurchaseDate)
  {
    $wasSet = false;
    $this->purchaseDate = $aPurchaseDate;
    $wasSet = true;
    return $wasSet;
  }

  public function getQuantity()
  {
    return $this->quantity;
  }

  public function getPurchaseDate()
  {
    return $this->purchaseDate;
  }

  public function getEquipmentType()
  {
    return $this->equipmentType;
  }

  public function setEquipmentType($aNewEquipmentType)
  {
    $wasSet = false;
    if ($aNewEquipmentType != null)
    {
      $this->equipmentType = $aNewEquipmentType;
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
    $this->equipmentType = null;
  }

}
?>