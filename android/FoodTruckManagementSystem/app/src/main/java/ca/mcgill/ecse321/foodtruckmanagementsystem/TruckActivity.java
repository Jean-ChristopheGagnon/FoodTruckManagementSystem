package ca.mcgill.ecse321.foodtruckmanagementsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.InvalidInputException;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.FoodTruckController;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.FoodTruckControllerAdapter;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.MenuController;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.MenuControllerAdapter;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruckManagementSystem;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Menu;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.persistence.PersistenceFoodTruckManager;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.persistence.PersistenceXStream;

public class TruckActivity extends AppCompatActivity {
    private HashMap<Integer, Menu> menus;
    private HashMap<Integer, ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck> foodTrucks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.truck_activity);
        refreshData();

        Spinner foodTruckSpinner = (Spinner) findViewById(R.id.selecttruck_spinner);
        foodTruckSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                populateGridView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                populateGridView();
            }
        });
    }
    void refreshData(){
        try{
            populateMenuSpinners();
            populateFoodTruckSpinner();
            populateGridView();
            TextView newTruckLocationTV = (TextView) findViewById(R.id.newtruck_location);
            newTruckLocationTV.setText("");
            TextView editTruckLocationTV = (TextView) findViewById(R.id.edittruck_location);
            editTruckLocationTV.setText("");
        } catch (Exception e) {

        }
    }

    public void populateMenuSpinners() {
        FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
        Spinner spinner1 = (Spinner) findViewById(R.id.newtruckmenu_spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.edittruckmenu_spinner);
        ArrayAdapter<CharSequence> menuAdapter = new
                ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        menuAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        this.menus = new HashMap<Integer, ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Menu>();
        int i = 0;
        try {
            for (Iterator<Menu> menus = ftms.getFoodList().iterator();
                 menus.hasNext(); i++) {
                ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Menu m = menus.next();
                menuAdapter.add(m.getName());
                this.menus.put(i, m);
            }
            spinner1.setAdapter(menuAdapter);
            spinner2.setAdapter(menuAdapter);
        } catch (Exception e) {

        }
    }
    public void populateFoodTruckSpinner() {
        FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
        Spinner spinner = (Spinner) findViewById(R.id.selecttruck_spinner);
        ArrayAdapter<CharSequence> foodTruckAdapter = new
                ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        foodTruckAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        this.foodTrucks = new HashMap<Integer, ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck>();
        int i = 0;
        try {
            for (Iterator<FoodTruck> foodTrucks = ftms.getFoodTrucks().iterator();
                 foodTrucks.hasNext(); i++) {
                ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck ft = foodTrucks.next();
                foodTruckAdapter.add(ft.getLocation());
                this.foodTrucks.put(i, ft);
            }
            spinner.setAdapter(foodTruckAdapter);
        } catch (Exception e) {

        }
    }

    public void populateGridView(){
        GridView gridview = (GridView) findViewById(R.id.gridview);
        final ArrayList<String> foodTruckList = new ArrayList<String>();
        try{
            for (int i = 0; i < this.foodTrucks.size(); i++) {
                foodTruckList.add(this.foodTrucks.get(i).getLocation());
                foodTruckList.add(this.foodTrucks.get(i).getMenu().getName());
            }
            ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,foodTruckList);
            gridview.setAdapter(adapter);
        } catch (Exception e) {

        }


    }

    public void addTruck(View v) {
        TextView tv = (TextView) findViewById(R.id.newtruck_location);
        Spinner menuSpinner = (Spinner) findViewById(R.id.newtruckmenu_spinner);
        FoodTruckController ftc = new FoodTruckControllerAdapter();
        try {
            ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Menu m = this.menus.get(menuSpinner.getSelectedItemPosition());
            ftc.createFoodTruck(tv.getText().toString(), m);
        } catch (InvalidInputException e) {
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        }

        refreshData();
    }

    public void editTruckMenu(View v){

        try {
            Spinner truckSpinner = (Spinner) findViewById(R.id.selecttruck_spinner);
            Spinner menuSpinner = (Spinner) findViewById(R.id.edittruckmenu_spinner);
            FoodTruckController ftc = new FoodTruckControllerAdapter();
            ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck ft = this.foodTrucks.get(truckSpinner.getSelectedItemPosition());
            ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Menu m = this.menus.get(menuSpinner.getSelectedItemPosition());
            ftc.changeFoodTruckMenu(ft, m);
        } catch (InvalidInputException e) {
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        }

        refreshData();
    }
    public void editTruckLocation(View v){

        try {
            Spinner truckSpinner = (Spinner) findViewById(R.id.selecttruck_spinner);
            TextView tv = (TextView) findViewById(R.id.edittruck_location);
            FoodTruckController ftc = new FoodTruckControllerAdapter();
            ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck ft = this.foodTrucks.get(truckSpinner.getSelectedItemPosition());
            String loc = tv.getText().toString();
            ftc.changeFoodTruckLocation(ft, loc);
        } catch (InvalidInputException e) {
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        }

        refreshData();
    }

    public void displayExceptionMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
