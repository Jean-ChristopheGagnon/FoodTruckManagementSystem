<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-fcfceb9 modeling language!*/

class WorkShift
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //WorkShift Attributes
  private $workDate;
  private $startTime;
  private $endTime;

  //WorkShift Associations
  private $foodTruck;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aWorkDate, $aStartTime, $aEndTime, $aFoodTruck)
  {
    $this->workDate = $aWorkDate;
    $this->startTime = $aStartTime;
    $this->endTime = $aEndTime;
    if (!$this->setFoodTruck($aFoodTruck))
    {
      throw new Exception("Unable to create WorkShift due to aFoodTruck");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setWorkDate($aWorkDate)
  {
    $wasSet = false;
    $this->workDate = $aWorkDate;
    $wasSet = true;
    return $wasSet;
  }

  public function setStartTime($aStartTime)
  {
    $wasSet = false;
    $this->startTime = $aStartTime;
    $wasSet = true;
    return $wasSet;
  }

  public function setEndTime($aEndTime)
  {
    $wasSet = false;
    $this->endTime = $aEndTime;
    $wasSet = true;
    return $wasSet;
  }

  public function getWorkDate()
  {
    return $this->workDate;
  }

  public function getStartTime()
  {
    return $this->startTime;
  }

  public function getEndTime()
  {
    return $this->endTime;
  }

  public function getFoodTruck()
  {
    return $this->foodTruck;
  }

  public function setFoodTruck($aNewFoodTruck)
  {
    $wasSet = false;
    if ($aNewFoodTruck != null)
    {
      $this->foodTruck = $aNewFoodTruck;
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
    $this->foodTruck = null;
  }

}
?>