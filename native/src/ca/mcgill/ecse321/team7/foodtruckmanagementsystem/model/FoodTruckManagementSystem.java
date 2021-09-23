/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model;
import java.util.*;

// line 11 "../../../../../../domain-model.ump"
public class FoodTruckManagementSystem
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static FoodTruckManagementSystem theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FoodTruckManagementSystem Associations
  private List<FoodTruck> foodTrucks;
  private List<Staff> staffs;
  private List<EquipmentType> equipmentTypes;
  private List<SupplyType> supplyTypes;
  private List<Menu> foodList;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private FoodTruckManagementSystem()
  {
    foodTrucks = new ArrayList<FoodTruck>();
    staffs = new ArrayList<Staff>();
    equipmentTypes = new ArrayList<EquipmentType>();
    supplyTypes = new ArrayList<SupplyType>();
    foodList = new ArrayList<Menu>();
  }

  public static FoodTruckManagementSystem getInstance()
  {
    if(theInstance == null)
    {
      theInstance = new FoodTruckManagementSystem();
    }
    return theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public FoodTruck getFoodTruck(int index)
  {
    FoodTruck aFoodTruck = foodTrucks.get(index);
    return aFoodTruck;
  }

  public List<FoodTruck> getFoodTrucks()
  {
    List<FoodTruck> newFoodTrucks = Collections.unmodifiableList(foodTrucks);
    return newFoodTrucks;
  }

  public int numberOfFoodTrucks()
  {
    int number = foodTrucks.size();
    return number;
  }

  public boolean hasFoodTrucks()
  {
    boolean has = foodTrucks.size() > 0;
    return has;
  }

  public int indexOfFoodTruck(FoodTruck aFoodTruck)
  {
    int index = foodTrucks.indexOf(aFoodTruck);
    return index;
  }

  public Staff getStaff(int index)
  {
    Staff aStaff = staffs.get(index);
    return aStaff;
  }

  public List<Staff> getStaffs()
  {
    List<Staff> newStaffs = Collections.unmodifiableList(staffs);
    return newStaffs;
  }

  public int numberOfStaffs()
  {
    int number = staffs.size();
    return number;
  }

  public boolean hasStaffs()
  {
    boolean has = staffs.size() > 0;
    return has;
  }

  public int indexOfStaff(Staff aStaff)
  {
    int index = staffs.indexOf(aStaff);
    return index;
  }

  public EquipmentType getEquipmentType(int index)
  {
    EquipmentType aEquipmentType = equipmentTypes.get(index);
    return aEquipmentType;
  }

  public List<EquipmentType> getEquipmentTypes()
  {
    List<EquipmentType> newEquipmentTypes = Collections.unmodifiableList(equipmentTypes);
    return newEquipmentTypes;
  }

  public int numberOfEquipmentTypes()
  {
    int number = equipmentTypes.size();
    return number;
  }

  public boolean hasEquipmentTypes()
  {
    boolean has = equipmentTypes.size() > 0;
    return has;
  }

  public int indexOfEquipmentType(EquipmentType aEquipmentType)
  {
    int index = equipmentTypes.indexOf(aEquipmentType);
    return index;
  }

  public SupplyType getSupplyType(int index)
  {
    SupplyType aSupplyType = supplyTypes.get(index);
    return aSupplyType;
  }

  public List<SupplyType> getSupplyTypes()
  {
    List<SupplyType> newSupplyTypes = Collections.unmodifiableList(supplyTypes);
    return newSupplyTypes;
  }

  public int numberOfSupplyTypes()
  {
    int number = supplyTypes.size();
    return number;
  }

  public boolean hasSupplyTypes()
  {
    boolean has = supplyTypes.size() > 0;
    return has;
  }

  public int indexOfSupplyType(SupplyType aSupplyType)
  {
    int index = supplyTypes.indexOf(aSupplyType);
    return index;
  }

  public Menu getFoodList(int index)
  {
    Menu aFoodList = foodList.get(index);
    return aFoodList;
  }

  /**
   * 
   * List of all the menus. It couldn't have been named menus because of a bug in umple.
   * 
   * @see https://github.com/umple/umple/issues/903
   */
  public List<Menu> getFoodList()
  {
    List<Menu> newFoodList = Collections.unmodifiableList(foodList);
    return newFoodList;
  }

  public int numberOfFoodList()
  {
    int number = foodList.size();
    return number;
  }

  public boolean hasFoodList()
  {
    boolean has = foodList.size() > 0;
    return has;
  }

  public int indexOfFoodList(Menu aFoodList)
  {
    int index = foodList.indexOf(aFoodList);
    return index;
  }

  public static int minimumNumberOfFoodTrucks()
  {
    return 0;
  }

  public boolean addFoodTruck(FoodTruck aFoodTruck)
  {
    boolean wasAdded = false;
    if (foodTrucks.contains(aFoodTruck)) { return false; }
    foodTrucks.add(aFoodTruck);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeFoodTruck(FoodTruck aFoodTruck)
  {
    boolean wasRemoved = false;
    if (foodTrucks.contains(aFoodTruck))
    {
      foodTrucks.remove(aFoodTruck);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addFoodTruckAt(FoodTruck aFoodTruck, int index)
  {  
    boolean wasAdded = false;
    if(addFoodTruck(aFoodTruck))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFoodTrucks()) { index = numberOfFoodTrucks() - 1; }
      foodTrucks.remove(aFoodTruck);
      foodTrucks.add(index, aFoodTruck);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveFoodTruckAt(FoodTruck aFoodTruck, int index)
  {
    boolean wasAdded = false;
    if(foodTrucks.contains(aFoodTruck))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFoodTrucks()) { index = numberOfFoodTrucks() - 1; }
      foodTrucks.remove(aFoodTruck);
      foodTrucks.add(index, aFoodTruck);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addFoodTruckAt(aFoodTruck, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfStaffs()
  {
    return 0;
  }

  public boolean addStaff(Staff aStaff)
  {
    boolean wasAdded = false;
    if (staffs.contains(aStaff)) { return false; }
    staffs.add(aStaff);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeStaff(Staff aStaff)
  {
    boolean wasRemoved = false;
    if (staffs.contains(aStaff))
    {
      staffs.remove(aStaff);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addStaffAt(Staff aStaff, int index)
  {  
    boolean wasAdded = false;
    if(addStaff(aStaff))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStaffs()) { index = numberOfStaffs() - 1; }
      staffs.remove(aStaff);
      staffs.add(index, aStaff);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveStaffAt(Staff aStaff, int index)
  {
    boolean wasAdded = false;
    if(staffs.contains(aStaff))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStaffs()) { index = numberOfStaffs() - 1; }
      staffs.remove(aStaff);
      staffs.add(index, aStaff);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addStaffAt(aStaff, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfEquipmentTypes()
  {
    return 0;
  }

  public boolean addEquipmentType(EquipmentType aEquipmentType)
  {
    boolean wasAdded = false;
    if (equipmentTypes.contains(aEquipmentType)) { return false; }
    equipmentTypes.add(aEquipmentType);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEquipmentType(EquipmentType aEquipmentType)
  {
    boolean wasRemoved = false;
    if (equipmentTypes.contains(aEquipmentType))
    {
      equipmentTypes.remove(aEquipmentType);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addEquipmentTypeAt(EquipmentType aEquipmentType, int index)
  {  
    boolean wasAdded = false;
    if(addEquipmentType(aEquipmentType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipmentTypes()) { index = numberOfEquipmentTypes() - 1; }
      equipmentTypes.remove(aEquipmentType);
      equipmentTypes.add(index, aEquipmentType);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentTypeAt(EquipmentType aEquipmentType, int index)
  {
    boolean wasAdded = false;
    if(equipmentTypes.contains(aEquipmentType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipmentTypes()) { index = numberOfEquipmentTypes() - 1; }
      equipmentTypes.remove(aEquipmentType);
      equipmentTypes.add(index, aEquipmentType);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentTypeAt(aEquipmentType, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfSupplyTypes()
  {
    return 0;
  }

  public boolean addSupplyType(SupplyType aSupplyType)
  {
    boolean wasAdded = false;
    if (supplyTypes.contains(aSupplyType)) { return false; }
    supplyTypes.add(aSupplyType);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSupplyType(SupplyType aSupplyType)
  {
    boolean wasRemoved = false;
    if (supplyTypes.contains(aSupplyType))
    {
      supplyTypes.remove(aSupplyType);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addSupplyTypeAt(SupplyType aSupplyType, int index)
  {  
    boolean wasAdded = false;
    if(addSupplyType(aSupplyType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSupplyTypes()) { index = numberOfSupplyTypes() - 1; }
      supplyTypes.remove(aSupplyType);
      supplyTypes.add(index, aSupplyType);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSupplyTypeAt(SupplyType aSupplyType, int index)
  {
    boolean wasAdded = false;
    if(supplyTypes.contains(aSupplyType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSupplyTypes()) { index = numberOfSupplyTypes() - 1; }
      supplyTypes.remove(aSupplyType);
      supplyTypes.add(index, aSupplyType);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSupplyTypeAt(aSupplyType, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfFoodList()
  {
    return 0;
  }

  public boolean addFoodList(Menu aFoodList)
  {
    boolean wasAdded = false;
    if (foodList.contains(aFoodList)) { return false; }
    foodList.add(aFoodList);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeFoodList(Menu aFoodList)
  {
    boolean wasRemoved = false;
    if (foodList.contains(aFoodList))
    {
      foodList.remove(aFoodList);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addFoodListAt(Menu aFoodList, int index)
  {  
    boolean wasAdded = false;
    if(addFoodList(aFoodList))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFoodList()) { index = numberOfFoodList() - 1; }
      foodList.remove(aFoodList);
      foodList.add(index, aFoodList);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveFoodListAt(Menu aFoodList, int index)
  {
    boolean wasAdded = false;
    if(foodList.contains(aFoodList))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFoodList()) { index = numberOfFoodList() - 1; }
      foodList.remove(aFoodList);
      foodList.add(index, aFoodList);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addFoodListAt(aFoodList, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    foodTrucks.clear();
    staffs.clear();
    equipmentTypes.clear();
    supplyTypes.clear();
    foodList.clear();
  }

}