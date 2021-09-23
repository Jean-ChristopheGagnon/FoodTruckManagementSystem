/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model;
import java.sql.Date;
import java.sql.Time;

// line 40 "../../../../../../domain-model.ump"
public class WorkShift
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //WorkShift Attributes
  private Date workDate;
  private Time startTime;
  private Time endTime;

  //WorkShift Associations
  private FoodTruck foodTruck;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public WorkShift(Date aWorkDate, Time aStartTime, Time aEndTime, FoodTruck aFoodTruck)
  {
    workDate = aWorkDate;
    startTime = aStartTime;
    endTime = aEndTime;
    if (!setFoodTruck(aFoodTruck))
    {
      throw new RuntimeException("Unable to create WorkShift due to aFoodTruck");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setWorkDate(Date aWorkDate)
  {
    boolean wasSet = false;
    workDate = aWorkDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartTime(Time aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(Time aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public Date getWorkDate()
  {
    return workDate;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public Time getEndTime()
  {
    return endTime;
  }

  public FoodTruck getFoodTruck()
  {
    return foodTruck;
  }

  public boolean setFoodTruck(FoodTruck aNewFoodTruck)
  {
    boolean wasSet = false;
    if (aNewFoodTruck != null)
    {
      foodTruck = aNewFoodTruck;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    foodTruck = null;
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "workDate" + "=" + (getWorkDate() != null ? !getWorkDate().equals(this)  ? getWorkDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "foodTruck = "+(getFoodTruck()!=null?Integer.toHexString(System.identityHashCode(getFoodTruck())):"null")
     + outputString;
  }
}