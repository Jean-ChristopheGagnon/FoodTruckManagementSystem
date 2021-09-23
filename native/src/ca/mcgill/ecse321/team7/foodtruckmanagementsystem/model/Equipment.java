/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model;
import java.sql.Date;

// line 47 "../../../../../../domain-model.ump"
public class Equipment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Equipment Attributes
  private double quantity;
  private Date purchaseDate;

  //Equipment Associations
  private EquipmentType equipmentType;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Equipment(double aQuantity, Date aPurchaseDate, EquipmentType aEquipmentType)
  {
    quantity = aQuantity;
    purchaseDate = aPurchaseDate;
    if (!setEquipmentType(aEquipmentType))
    {
      throw new RuntimeException("Unable to create Equipment due to aEquipmentType");
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

  public boolean setPurchaseDate(Date aPurchaseDate)
  {
    boolean wasSet = false;
    purchaseDate = aPurchaseDate;
    wasSet = true;
    return wasSet;
  }

  public double getQuantity()
  {
    return quantity;
  }

  public Date getPurchaseDate()
  {
    return purchaseDate;
  }

  public EquipmentType getEquipmentType()
  {
    return equipmentType;
  }

  public boolean setEquipmentType(EquipmentType aNewEquipmentType)
  {
    boolean wasSet = false;
    if (aNewEquipmentType != null)
    {
      equipmentType = aNewEquipmentType;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    equipmentType = null;
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "quantity" + ":" + getQuantity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "purchaseDate" + "=" + (getPurchaseDate() != null ? !getPurchaseDate().equals(this)  ? getPurchaseDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "equipmentType = "+(getEquipmentType()!=null?Integer.toHexString(System.identityHashCode(getEquipmentType())):"null")
     + outputString;
  }
}