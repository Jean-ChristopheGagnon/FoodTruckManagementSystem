package ca.mcgill.ecse321.team7.foodtruckmanagementsystem;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.TestFoodTruckController;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.TestMenuController;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.TestOrderController;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.TestSETypesController;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.TestStaffController;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.persistence.TestFoodTruckPersistence;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.persistence.TestMenuPersistence;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.persistence.TestStaffPersistence;

@RunWith(Suite.class)
@SuiteClasses({TestMenuController.class, TestStaffController.class, TestOrderController.class, TestFoodTruckController.class, TestSETypesController.class,TestMenuPersistence.class, TestStaffPersistence.class, TestFoodTruckPersistence.class})
public class AllTests {

}
