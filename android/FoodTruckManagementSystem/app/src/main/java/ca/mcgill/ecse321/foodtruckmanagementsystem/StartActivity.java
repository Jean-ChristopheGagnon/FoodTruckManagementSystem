package ca.mcgill.ecse321.foodtruckmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.util.Iterator;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruckManagementSystem;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Menu;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.persistence.PersistenceFoodTruckManager;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.persistence.PersistenceXStream;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        loadFoodTruckModel();
    }

    public void loadMenuActivity(View view)
    {
        Intent intent = new Intent(StartActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    public void loadTruckActivity(View view)
    {
        Intent intent = new Intent(StartActivity.this, TruckActivity.class);
        startActivity(intent);
    }

    public void loadStaffActivity(View view)
    {
        Intent intent = new Intent(StartActivity.this, StaffActivity.class);
        startActivity(intent);
    }



    public void loadSupplyActivity(View view)
    {
        Intent intent = new Intent(StartActivity.this, SupplyActivity.class);
        startActivity(intent);
    }

    public void loadOrderActivity(View view)
    {
        Intent intent = new Intent(StartActivity.this, OrderActivity.class);
        startActivity(intent);
    }

    public void loadFoodTruckModel(){
        FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
        PersistenceFoodTruckManager.initializeXStream(getFilesDir().getAbsolutePath() + "/foodtruckmanager.xml");
        PersistenceXStream.setFilename(getFilesDir().getAbsolutePath() + "/foodtruckmanager.xml");
        FoodTruckManagementSystem ftms2 = (FoodTruckManagementSystem) PersistenceXStream.loadFromXMLwithXStream();
        if(ftms2 != null){

            Iterator<Menu> pIt = ftms2.getFoodList().iterator();
            while(pIt.hasNext()){
                ftms.addFoodList(pIt.next());
            }
            Iterator<ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck> ftIt = ftms2.getFoodTrucks().iterator();
            while(ftIt.hasNext()){
                ftms.addFoodTruck(ftIt.next());
            }
            Iterator<ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Staff> sIt = ftms2.getStaffs().iterator();
            while(sIt.hasNext()){
                ftms.addStaff(sIt.next());
            }
            Iterator<ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.SupplyType> stIt = ftms2.getSupplyTypes().iterator();
            while(stIt.hasNext()){
                ftms.addSupplyType(stIt.next());
            }
        }
    }
}
