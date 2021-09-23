<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-fcfceb9 modeling language!*/

class Staff
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Staff Attributes
  private $name;
  private $job;

  //Staff Associations
  private $workShifts;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aJob)
  {
    $this->name = $aName;
    $this->job = $aJob;
    $this->workShifts = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setName($aName)
  {
    $wasSet = false;
    $this->name = $aName;
    $wasSet = true;
    return $wasSet;
  }

  public function setJob($aJob)
  {
    $wasSet = false;
    $this->job = $aJob;
    $wasSet = true;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getJob()
  {
    return $this->job;
  }

  public function getWorkShift_index($index)
  {
    $aWorkShift = $this->workShifts[$index];
    return $aWorkShift;
  }

  public function getWorkShifts()
  {
    $newWorkShifts = $this->workShifts;
    return $newWorkShifts;
  }

  public function numberOfWorkShifts()
  {
    $number = count($this->workShifts);
    return $number;
  }

  public function hasWorkShifts()
  {
    $has = $this->numberOfWorkShifts() > 0;
    return $has;
  }

  public function indexOfWorkShift($aWorkShift)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->workShifts as $workShift)
    {
      if ($workShift->equals($aWorkShift))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfWorkShifts()
  {
    return 0;
  }

  public function addWorkShift($aWorkShift)
  {
    $wasAdded = false;
    if ($this->indexOfWorkShift($aWorkShift) !== -1) { return false; }
    $this->workShifts[] = $aWorkShift;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeWorkShift($aWorkShift)
  {
    $wasRemoved = false;
    if ($this->indexOfWorkShift($aWorkShift) != -1)
    {
      unset($this->workShifts[$this->indexOfWorkShift($aWorkShift)]);
      $this->workShifts = array_values($this->workShifts);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addWorkShiftAt($aWorkShift, $index)
  {  
    $wasAdded = false;
    if($this->addWorkShift($aWorkShift))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfWorkShifts()) { $index = $this->numberOfWorkShifts() - 1; }
      array_splice($this->workShifts, $this->indexOfWorkShift($aWorkShift), 1);
      array_splice($this->workShifts, $index, 0, array($aWorkShift));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveWorkShiftAt($aWorkShift, $index)
  {
    $wasAdded = false;
    if($this->indexOfWorkShift($aWorkShift) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfWorkShifts()) { $index = $this->numberOfWorkShifts() - 1; }
      array_splice($this->workShifts, $this->indexOfWorkShift($aWorkShift), 1);
      array_splice($this->workShifts, $index, 0, array($aWorkShift));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addWorkShiftAt($aWorkShift, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->workShifts = array();
  }

}
?>