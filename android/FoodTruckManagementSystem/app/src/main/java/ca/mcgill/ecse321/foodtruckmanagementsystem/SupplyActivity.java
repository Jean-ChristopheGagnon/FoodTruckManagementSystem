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

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.FoodTruckController;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.FoodTruckControllerAdapter;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.InvalidInputException;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.MenuController;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.MenuControllerAdapter;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.SETypesController;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.SETypesControllerAdapter;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruckManagementSystem;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Menu;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Supply;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.SupplyType;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.WorkShift;

public class SupplyActivity extends AppCompatActivity {
    private HashMap<Integer, ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.SupplyType> supplyTypes;
    private HashMap<Integer, ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck> foodTrucks;
    private HashMap<Integer, ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Supply> supplies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supply_activity);
        refreshData();
        Spinner foodTruckSpinner = (Spinner) findViewById(R.id.selecttrucksupply_spinner);
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

    private void refreshData() {
        try {
            populateSupplyTypeSpinner();
            populateFoodTruckSpinner();
            //populateItemSpinner();
            populateGridView();
            TextView newIngredientNameTV = (TextView) findViewById(R.id.newingredient_name);
            newIngredientNameTV.setText("");
            TextView newEquipmentNameTV = (TextView) findViewById(R.id.newequipment_name);
            newEquipmentNameTV.setText("");
            TextView editSupplyNameTV = (TextView) findViewById(R.id.editsupply_name);
            editSupplyNameTV.setText("");
            TextView addSupplyAmountTV = (TextView) findViewById(R.id.addsupply_amount);
            addSupplyAmountTV.setText("");
            //Spinner itemSpinner = (Spinner) findViewById(R.id.itemspinner);
            //itemSpinner.setSelection(0);
        } catch(Exception e) {

        }

    }

    public void populateSupplyTypeSpinner() {
        FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
        Spinner spinner = (Spinner) findViewById(R.id.selectsupply_spinner);
        ArrayAdapter<CharSequence> supplyTypeAdapter = new
                ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        supplyTypeAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        this.supplyTypes = new HashMap<Integer, SupplyType>();
        int i = 0;
        try {
            for (Iterator<SupplyType> supplyTypes = ftms.getSupplyTypes().iterator();
                 supplyTypes.hasNext(); i++) {
                ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.SupplyType st = supplyTypes.next();
                supplyTypeAdapter.add(st.getName());
                this.supplyTypes.put(i, st);
            }
            spinner.setAdapter(supplyTypeAdapter);
        } catch (Exception e) {
        }
    }

    public void populateFoodTruckSpinner() {
        FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
        Spinner spinner = (Spinner) findViewById(R.id.selecttrucksupply_spinner);
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
        try{
            Spinner foodTruckSpinner = (Spinner) findViewById(R.id.selecttrucksupply_spinner);
            GridView gridview = (GridView) findViewById(R.id.gridview);
            ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck ft = this.foodTrucks.get(foodTruckSpinner.getSelectedItemPosition());
            FoodTruckController ftc = new FoodTruckControllerAdapter();
            final ArrayList<String> supplyList = new ArrayList<String>();

            this.supplies = new HashMap<Integer, ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Supply>();
            int i = 0;
            for (Iterator<Supply> supplies = ft.getSupplies().iterator();
                 supplies.hasNext(); i++) {
                ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Supply sup = supplies.next();
                this.supplies.put(i, sup);
            }
            for (int j = 0; j < this.supplies.size(); j++) {
                supplyList.add(this.supplies.get(j).getSupplyType().getName());
                supplyList.add(String.valueOf(this.supplies.get(j).getQuantity()));
            }
            ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,supplyList);
            gridview.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        }



    }

    public void addIngredient(View v) {
        TextView tv = (TextView) findViewById(R.id.newingredient_name);
        SETypesController stc = new SETypesControllerAdapter();

        try {
            stc.createSupplyType("Ingredient - " + tv.getText().toString());
        } catch (InvalidInputException e) {
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        }

        refreshData();
    }

    public void addEquipment(View v) {
        TextView tv = (TextView) findViewById(R.id.newequipment_name);
        SETypesController stc = new SETypesControllerAdapter();

        try {
            stc.createEquipmentType("Equipment - " + tv.getText().toString());
        } catch (InvalidInputException e) {
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        }

        refreshData();
    }

    public void deleteSupplyType(View v) {
        Spinner supplySpinner = (Spinner) findViewById(R.id.selectsupply_spinner);
        SETypesController stc = new SETypesControllerAdapter();

        try {
            ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.SupplyType st = this.supplyTypes.get(supplySpinner.getSelectedItemPosition());
            stc.deleteSupplyType(st);
        } catch (InvalidInputException e) {
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        }

        refreshData();
    }

    public void editSupply(View v) {
        TextView tvname = (TextView) findViewById(R.id.editsupply_name);
        Spinner supplySpinner = (Spinner) findViewById(R.id.selectsupply_spinner);
        SETypesController stc = new SETypesControllerAdapter();
        String newName = "";
        try {
            ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.SupplyType st = this.supplyTypes.get(supplySpinner.getSelectedItemPosition());
            String name = tvname.getText().toString();
            if (st.getName().charAt(0) == "I".charAt(0)) {
                newName = "Ingredient - " + name;
            } else if (st.getName().charAt(0) == "E".charAt(0)) {
                newName = "Equipment - " + name;
            }
            stc.changeSupplyTypeName(st, newName);
        } catch (InvalidInputException e) {
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        }

        refreshData();
    }

    public void addSupply(View v) {
        try {
            Spinner supplyTypeSpinner = (Spinner) findViewById(R.id.selectsupply_spinner);
            Spinner truckSpinner = (Spinner) findViewById(R.id.selecttrucksupply_spinner);
            TextView tvamount = (TextView) findViewById(R.id.addsupply_amount);
            Double qty = Double.parseDouble(tvamount.getText().toString());
            FoodTruckController ftc = new FoodTruckControllerAdapter();
            ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.SupplyType st = this.supplyTypes.get(supplyTypeSpinner.getSelectedItemPosition());
            Supply supp = new Supply(qty, st);
            ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck ft = this.foodTrucks.get(truckSpinner.getSelectedItemPosition());
            ftc.createSupply(ft, st, qty);
        } catch (InvalidInputException e) {
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        }
        refreshData();
    }

    public void removeSupply(View v){
        try{
            Spinner supplyTypeSpinner = (Spinner) findViewById(R.id.selectsupply_spinner);
            Spinner truckSpinner = (Spinner) findViewById(R.id.selecttrucksupply_spinner);
            ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.SupplyType st = this.supplyTypes.get(supplyTypeSpinner.getSelectedItemPosition());
            ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck ft = this.foodTrucks.get(truckSpinner.getSelectedItemPosition());
            FoodTruckController ftc = new FoodTruckControllerAdapter();
            for (int i =0; i < supplies.size(); i++) {
                ftc.removeSupply(ft, supplies.get(i));
            }

            refreshData();
        } catch (InvalidInputException e) {
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        }
    }

    public void displayExceptionMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
