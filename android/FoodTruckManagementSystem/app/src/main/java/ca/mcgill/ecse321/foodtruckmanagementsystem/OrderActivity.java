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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.InvalidInputException;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.MenuController;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.MenuControllerAdapter;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruckManagementSystem;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.MenuItem;

public class OrderActivity extends AppCompatActivity {
    private HashMap<Integer, ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck> foodTrucks;
    private HashMap<Integer, ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.MenuItem> items;
    private HashMap<Integer, ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.MenuItem> orderItems;
    int key = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);
        this.orderItems = new HashMap<Integer, MenuItem>();
        refreshData();
    }

    private void refreshData() {
        try {
            populateFoodTruckSpinner();
            populateItemSpinner();
            populateGridView();
            setTotal();
            TextView newMenuNameTV = (TextView) findViewById(R.id.selectitemqtyorder);
            newMenuNameTV.setText("");
            //Spinner itemSpinner = (Spinner) findViewById(R.id.itemspinner);
            //itemSpinner.setSelection(0);
        } catch(Exception e) {

        }

    }

    public void populateFoodTruckSpinner() {
        FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
        Spinner spinner = (Spinner) findViewById(R.id.selecttruckorder_spinner);
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

    public void populateItemSpinner() {
        Spinner itemSpinner = (Spinner) findViewById(R.id.selectitemorder_spinner);
        FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
        Spinner truckSpinner = (Spinner) findViewById(R.id.selecttruckorder_spinner);
        ArrayAdapter<CharSequence> itemAdapter = new
                ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        itemAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        this.items = new HashMap<Integer, MenuItem>();
        int i = 0;
        try {
            ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck ft = this.foodTrucks.get(truckSpinner.getSelectedItemPosition());
            ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Menu m = ft.getMenu();
            if (m != null) {
                for (Iterator<MenuItem> items = m.getMenuItems().iterator();
                     items.hasNext(); i++) {
                    ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.MenuItem item = items.next();
                    itemAdapter.add(item.getName());
                    this.items.put(i, item);
                }
            } else {

            }
            itemSpinner.setAdapter(itemAdapter);
        } catch (Exception e) {

        }
    }

    public void populateGridView(){
        GridView gridview = (GridView) findViewById(R.id.gridview);
        final ArrayList<String> menuItemList = new ArrayList<String>();
        try{
            for (int i = 0; i < this.orderItems.size(); i++) {
                menuItemList.add(this.orderItems.get(i).getName());
                menuItemList.add(String.format("%.2f", ((double)this.orderItems.get(i).getPrice())/100));
            }
            ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,menuItemList);
            gridview.setAdapter(adapter);
        } catch (Exception e) {

        }


    }

    private void setTotal(){
        TextView totalTV = (TextView) findViewById(R.id.ordertotal_value);
        double total = 0;
        for (int i = 0; i < orderItems.size(); i++) {
            total = total + orderItems.get(i).getPrice();
        }
        totalTV.setText(""+(total/100));
    }

    public void addToOrder(View v) {

        try {
            TextView tv = (TextView) findViewById(R.id.selectitemqtyorder);
            Spinner itemSpinner = (Spinner) findViewById(R.id.selectitemorder_spinner);
            ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.MenuItem mi = this.items.get(itemSpinner.getSelectedItemPosition());
            MenuController mc = new MenuControllerAdapter();
            int qty = Integer.parseInt(tv.getText().toString());
            this.orderItems = new HashMap<Integer, MenuItem>();
            for (int i = 0; i < qty; i++) {
                this.orderItems.put(key, mi);
                key++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        }

        refreshData();
    }

    public void clearOrder(View v){
        try {
            this.orderItems = new HashMap<Integer, MenuItem>();
            this.key = 0;
            refreshData();
        } catch (Exception e) {
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        }
    }

    public void displayExceptionMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
