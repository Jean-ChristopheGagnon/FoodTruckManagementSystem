/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model;

// line 62 "../../../../../../domain-model.ump"
public class SupplyType
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SupplyType Attributes
  private String name;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SupplyType(String aName)
  {
    name = aName;
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

  public String getName()
  {
    return name;
  }

  public void delete()
  {}


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "]"
     + outputString;
  }
}