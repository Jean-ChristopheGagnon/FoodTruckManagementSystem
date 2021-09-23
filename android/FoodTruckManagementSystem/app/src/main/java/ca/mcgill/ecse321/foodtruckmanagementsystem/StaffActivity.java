package ca.mcgill.ecse321.foodtruckmanagementsystem;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.InvalidInputException;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.MenuController;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.MenuControllerAdapter;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.StaffController;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.StaffControllerAdapter;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruckManagementSystem;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Menu;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Staff;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.WorkShift;

public class StaffActivity extends FragmentActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener  {
    private HashMap<Integer, ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Staff> staffs;
    private HashMap<Integer, ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.WorkShift> shifts;
    private HashMap<Integer, ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck> foodTrucks;
    int timeSet = 0; // 1 is for startTime, 2 is for endTime
    java.sql.Date shiftDate;
    java.sql.Time shiftStartTime;
    java.sql.Time shiftEndTime;

    void showDateDialog(){

        FragmentManager fm = getSupportFragmentManager();
        DatePickerFragment newFragment = new DatePickerFragment(this);
        newFragment.show(fm, "date_picker");

    }

    void showTimeDialog(){

        FragmentManager fm = getSupportFragmentManager();
        TimePickerFragment newFragment = new TimePickerFragment(this);
        newFragment.show(fm, "time_picker");

    }


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        try {
            String dateString = ""+year+"/"+monthOfYear+"/"+dayOfMonth;
            String parseString = ""+year+"-"+monthOfYear+"-"+dayOfMonth;
            shiftDate = java.sql.Date.valueOf(parseString);
            TextView dateDisplay = (TextView) findViewById(R.id.shiftdate_display);
            dateDisplay.setText(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        try{
            String timeString = ""+hourOfDay+":"+minute;
            SimpleDateFormat stf = new SimpleDateFormat("hh:mm");
            if (timeSet == 1) {
                shiftStartTime = new java.sql.Time(hourOfDay, minute, 0);
                TextView startTimeDisplay = (TextView) findViewById(R.id.shiftstarttime_display);
                startTimeDisplay.setText(timeString);
            } else if (timeSet == 2) {
                shiftEndTime = new java.sql.Time(hourOfDay, minute, 0);
                TextView endTimeDisplay = (TextView) findViewById(R.id.shiftendtime_display);
                endTimeDisplay.setText(timeString);
            }
            timeSet = 0;
        } catch (Exception e){
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_activity);
        refreshData();

        Spinner staffSpinner = (Spinner) findViewById(R.id.selectstaff_spinner);
        staffSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            populateStaffSpinner();
            populateFoodTruckSpinner();
            //populateItemSpinner();
            populateGridView();
            TextView newStaffNameTV = (TextView) findViewById(R.id.newstaff_name);
            newStaffNameTV.setText("");
            TextView newStaffJobTV = (TextView) findViewById(R.id.newstaff_job);
            newStaffJobTV.setText("");
            TextView editStaffNameTV = (TextView) findViewById(R.id.editstaff_name);
            editStaffNameTV.setText("");
            TextView editStaffJobTV = (TextView) findViewById(R.id.editstaff_job);
            editStaffJobTV.setText("");
        } catch(Exception e) {

        }

    }

    public void populateStaffSpinner() {
        FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
        Spinner spinner = (Spinner) findViewById(R.id.selectstaff_spinner);
        ArrayAdapter<CharSequence> staffAdapter = new
                ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        staffAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        this.staffs = new HashMap<Integer, Staff>();
        int i = 0;
        try {
            for (Iterator<Staff> staffs = ftms.getStaffs().iterator();
                 staffs.hasNext(); i++) {
                ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Staff s = staffs.next();
                staffAdapter.add(s.getName());
                this.staffs.put(i, s);
            }
            spinner.setAdapter(staffAdapter);
        } catch (Exception e) {
        }
    }

    public void populateFoodTruckSpinner() {
        FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
        Spinner spinner = (Spinner) findViewById(R.id.selecttruckstaff_spinner);
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
            FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
            Spinner staffSpinner = (Spinner) findViewById(R.id.selectstaff_spinner);
            GridView gridview = (GridView) findViewById(R.id.gridview);
            ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Staff s = this.staffs.get(staffSpinner.getSelectedItemPosition());
            MenuController mc = new MenuControllerAdapter();


            final ArrayList<String> shiftList = new ArrayList<String>();
            this.shifts = new HashMap<Integer, ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.WorkShift>();
            int i = 0;
            for (Iterator<WorkShift> shifts = s.getWorkShifts().iterator();
                 shifts.hasNext(); i++) {
                ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.WorkShift ws = shifts.next();
                this.shifts.put(i, ws);
            }
            for (int j = 0; j < this.shifts.size(); j++) {
                shiftList.add(this.shifts.get(j).getFoodTruck().getLocation());
                shiftList.add(this.shifts.get(j).getWorkDate().toString());
                shiftList.add(this.shifts.get(j).getStartTime().toString());
                shiftList.add(this.shifts.get(j).getEndTime().toString());
            }
            ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,shiftList);
            gridview.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        }



    }

    public void addStaff(View v) {
        try {
            TextView staffNameTV = (TextView) findViewById(R.id.newstaff_name);
            TextView staffJobTV = (TextView) findViewById(R.id.newstaff_job);
            StaffController sc = new StaffControllerAdapter();
            sc.createStaffMember(staffNameTV.getText().toString(), staffJobTV.getText().toString());
        } catch (InvalidInputException e) {
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        }

        refreshData();
    }

    public void deleteStaff(View v) {
        Spinner staffSpinner = (Spinner) findViewById(R.id.selectstaff_spinner);
        StaffController sc = new StaffControllerAdapter();

        try {
            ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Staff s = this.staffs.get(staffSpinner.getSelectedItemPosition());
            sc.deleteStaffMember(s);
        } catch (InvalidInputException e) {
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        }

        refreshData();
    }

    public void updateStaff(View v) {
        TextView tvname = (TextView) findViewById(R.id.editstaff_name);
        TextView tvjob = (TextView) findViewById(R.id.editstaff_job);
        Spinner staffSpinner = (Spinner) findViewById(R.id.selectstaff_spinner);
        StaffController sc = new StaffControllerAdapter();
        try {
            ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Staff s = this.staffs.get(staffSpinner.getSelectedItemPosition());
            String name = tvname.getText().toString();
            String job = tvjob.getText().toString();
            if (job.length() > 0) {
                sc.changeStaffMemberJob(s, job);
            }
            if (name.length() > 0) {
                sc.changeStaffMemberName(s, name);
            }

            if (job.length() <= 0 && name.length() <= 0) {
                if (s == null) {
                    displayExceptionMessage("Staff selected invalid!");
                } else {
                    displayExceptionMessage("Please enter a new name or job.");
                }
            }
        } catch (InvalidInputException e) {
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
            displayExceptionMessage(e.getMessage());
        }
        refreshData();
    }

    public void setShiftDate(View v) {
        showDateDialog();
    }

    public void setShiftStartTime(View v) {
        timeSet = 1;
        showTimeDialog();
    }

    public void setShiftEndTime(View v) {
        timeSet = 2;
        showTimeDialog();
    }

    public void addShift(View v) {
        try {
            Spinner staffSpinner = (Spinner) findViewById(R.id.selectstaff_spinner);
            ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Staff s = this.staffs.get(staffSpinner.getSelectedItemPosition());
            Spinner foodTruckSpinner = (Spinner) findViewById(R.id.selecttruckstaff_spinner);
            ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck ft = this.foodTrucks.get(foodTruckSpinner.getSelectedItemPosition());
            StaffController sc = new StaffControllerAdapter();
            sc.createWorkShift(s, ft, shiftDate, shiftStartTime, shiftEndTime);
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
