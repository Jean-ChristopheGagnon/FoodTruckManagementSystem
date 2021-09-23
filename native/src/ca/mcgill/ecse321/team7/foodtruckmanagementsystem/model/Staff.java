/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;

// line 34 "../../../../../../domain-model.ump"
public class Staff
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Staff Attributes
  private String name;
  private String job;

  //Staff Associations
  private List<WorkShift> workShifts;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Staff(String aName, String aJob)
  {
    name = aName;
    job = aJob;
    workShifts = new ArrayList<WorkShift>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setJob(String aJob)
  {
    boolean wasSet = false;
    job = aJob;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getJob()
  {
    return job;
  }

  public WorkShift getWorkShift(int index)
  {
    WorkShift aWorkShift = workShifts.get(index);
    return aWorkShift;
  }

  public List<WorkShift> getWorkShifts()
  {
    List<WorkShift> newWorkShifts = Collections.unmodifiableList(workShifts);
    return newWorkShifts;
  }

  public int numberOfWorkShifts()
  {
    int number = workShifts.size();
    return number;
  }

  public boolean hasWorkShifts()
  {
    boolean has = workShifts.size() > 0;
    return has;
  }

  public int indexOfWorkShift(WorkShift aWorkShift)
  {
    int index = workShifts.indexOf(aWorkShift);
    return index;
  }

  public static int minimumNumberOfWorkShifts()
  {
    return 0;
  }

  public boolean addWorkShift(WorkShift aWorkShift)
  {
    boolean wasAdded = false;
    if (workShifts.contains(aWorkShift)) { return false; }
    workShifts.add(aWorkShift);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeWorkShift(WorkShift aWorkShift)
  {
    boolean wasRemoved = false;
    if (workShifts.contains(aWorkShift))
    {
      workShifts.remove(aWorkShift);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addWorkShiftAt(WorkShift aWorkShift, int index)
  {  
    boolean wasAdded = false;
    if(addWorkShift(aWorkShift))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWorkShifts()) { index = numberOfWorkShifts() - 1; }
      workShifts.remove(aWorkShift);
      workShifts.add(index, aWorkShift);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWorkShiftAt(WorkShift aWorkShift, int index)
  {
    boolean wasAdded = false;
    if(workShifts.contains(aWorkShift))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWorkShifts()) { index = numberOfWorkShifts() - 1; }
      workShifts.remove(aWorkShift);
      workShifts.add(index, aWorkShift);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWorkShiftAt(aWorkShift, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    workShifts.clear();
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "job" + ":" + getJob()+ "]"
     + outputString;
  }
}