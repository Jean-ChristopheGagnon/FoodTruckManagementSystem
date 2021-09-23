/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model;

// line 57 "../../../../../../domain-model.ump"
public class Supply
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Supply Attributes
  private double quantity;

  //Supply Associations
  private SupplyType supplyType;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Supply(double aQuantity, SupplyType aSupplyType)
  {
    quantity = aQuantity;
    if (!setSupplyType(aSupplyType))
    {
      throw new RuntimeException("Unable to create Supply due to aSupplyType");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setQuantity(double aQuantity)
  {
    boolean wasSet = false;
    quantity = aQuantity;
    wasSet = true;
    return wasSet;
  }

  public double getQuantity()
  {
    return quantity;
  }

  public SupplyType getSupplyType()
  {
    return supplyType;
  }

  public boolean setSupplyType(SupplyType aNewSupplyType)
  {
    boolean wasSet = false;
    if (aNewSupplyType != null)
    {
      supplyType = aNewSupplyType;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    supplyType = null;
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "quantity" + ":" + getQuantity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "supplyType = "+(getSupplyType()!=null?Integer.toHexString(System.identityHashCode(getSupplyType())):"null")
     + outputString;
  }
}