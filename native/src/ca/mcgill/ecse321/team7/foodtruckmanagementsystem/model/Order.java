/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

// line 80 "../../../../../../domain-model.ump"
public class Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  private Date orderDate;
  private Time orderTime;
  private boolean paid;
  private boolean served;

  //Order Associations
  private List<MenuItem> menuItems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(Date aOrderDate, Time aOrderTime, boolean aPaid, boolean aServed)
  {
    orderDate = aOrderDate;
    orderTime = aOrderTime;
    paid = aPaid;
    served = aServed;
    menuItems = new ArrayList<MenuItem>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setOrderDate(Date aOrderDate)
  {
    boolean wasSet = false;
    orderDate = aOrderDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setOrderTime(Time aOrderTime)
  {
    boolean wasSet = false;
    orderTime = aOrderTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setPaid(boolean aPaid)
  {
    boolean wasSet = false;
    paid = aPaid;
    wasSet = true;
    return wasSet;
  }

  public boolean setServed(boolean aServed)
  {
    boolean wasSet = false;
    served = aServed;
    wasSet = true;
    return wasSet;
  }

  public Date getOrderDate()
  {
    return orderDate;
  }

  public Time getOrderTime()
  {
    return orderTime;
  }

  /**
   * Has the order been paid by the customer ?
   */
  public boolean getPaid()
  {
    return paid;
  }

  /**
   * Has the order been served to the customer ?
   */
  public boolean getServed()
  {
    return served;
  }

  public boolean isPaid()
  {
    return paid;
  }

  public boolean isServed()
  {
    return served;
  }

  public MenuItem getMenuItem(int index)
  {
    MenuItem aMenuItem = menuItems.get(index);
    return aMenuItem;
  }

  public List<MenuItem> getMenuItems()
  {
    List<MenuItem> newMenuItems = Collections.unmodifiableList(menuItems);
    return newMenuItems;
  }

  public int numberOfMenuItems()
  {
    int number = menuItems.size();
    return number;
  }

  public boolean hasMenuItems()
  {
    boolean has = menuItems.size() > 0;
    return has;
  }

  public int indexOfMenuItem(MenuItem aMenuItem)
  {
    int index = menuItems.indexOf(aMenuItem);
    return index;
  }

  public static int minimumNumberOfMenuItems()
  {
    return 0;
  }

  public boolean addMenuItem(MenuItem aMenuItem)
  {
    boolean wasAdded = false;
    if (menuItems.contains(aMenuItem)) { return false; }
    menuItems.add(aMenuItem);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMenuItem(MenuItem aMenuItem)
  {
    boolean wasRemoved = false;
    if (menuItems.contains(aMenuItem))
    {
      menuItems.remove(aMenuItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addMenuItemAt(MenuItem aMenuItem, int index)
  {  
    boolean wasAdded = false;
    if(addMenuItem(aMenuItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMenuItems()) { index = numberOfMenuItems() - 1; }
      menuItems.remove(aMenuItem);
      menuItems.add(index, aMenuItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMenuItemAt(MenuItem aMenuItem, int index)
  {
    boolean wasAdded = false;
    if(menuItems.contains(aMenuItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMenuItems()) { index = numberOfMenuItems() - 1; }
      menuItems.remove(aMenuItem);
      menuItems.add(index, aMenuItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMenuItemAt(aMenuItem, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    menuItems.clear();
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "paid" + ":" + getPaid()+ "," +
            "served" + ":" + getServed()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "orderDate" + "=" + (getOrderDate() != null ? !getOrderDate().equals(this)  ? getOrderDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "orderTime" + "=" + (getOrderTime() != null ? !getOrderTime().equals(this)  ? getOrderTime().toString().replaceAll("  ","    ") : "this" : "null")
     + outputString;
  }
}