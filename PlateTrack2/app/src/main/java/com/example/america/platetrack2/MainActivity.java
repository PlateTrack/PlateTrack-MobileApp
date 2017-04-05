package com.example.america.platetrack2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.EventListener;
import java.util.Calendar;
import java.text.DateFormat;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

//*******************************************************************
    protected static TextView displayCurrentTime;
    protected static TextView displayLastDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //4/3**********************************
        displayCurrentTime = (TextView)findViewById(R.id.dateHere);
        displayLastDate = (TextView)findViewById(R.id.lastdateHere);

                //4/3**************************

        Button btn = (Button)findViewById(R.id.buttonCamera);
        Button searchButton = (Button) findViewById(R.id.button);

        searchButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText platebox = (EditText)findViewById(R.id.platebox);
                String plate_number = platebox.getText().toString();
                startActivity(new Intent(MainActivity.this, MapsActivity.class));

            }

        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VideoCaptureActivity.class));
            }
        });


    }

   // 4/4 DatePickerDialog.OnDateSetListener from_dateListener,to_dateListener;

    DatePickerDialog.OnDateSetListener from_dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            displayCurrentTime.setText(String.valueOf(year) + '/' + String.valueOf(month + 1) + '/' + String.valueOf(day));
        }
    };

    DatePickerDialog.OnDateSetListener to_dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            displayLastDate.setText(String.valueOf(year) + '/' + String.valueOf(month + 1) + '/' + String.valueOf(day));
        }
    };



    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {


            public void onDateSet (DatePicker view,int year, int month, int day){
            // Do something with the date chosen by the user
            //eventually these dates will be sent to the cloud to retrieve the information about the plates numbers.
            //4/3     Calendar cal = new GregorianCalendar(year, month, day);
            /* final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
            ((TextView) findViewById(R.id.dateHere)).setText(dateFormat.format(cal.getTime()));*/
            //4/3     setDate(cal);

            displayCurrentTime.setText(String.valueOf(month + 1) + '/' + String.valueOf(day) + '/' + String.valueOf(year));
            //  displayLastDate.setText(String.valueOf(year)+'/'+ String.valueOf(month+1)+'/'+String.valueOf(day));

        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

    }
/**
    private void setDate(final Calendar calendar){
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        ((TextView) findViewById(R.id.dateHere)).setText(dateFormat.format(calendar.getTime()));
    }**/
    public void calendarClick(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
// this function is the responsible for putting the date in the input box.
    /*  //not necessary for now
    private void setDate(final Calendar calendar) {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        ((TextView) findViewById(R.id.dateHere)).setText(dateFormat.format(calendar.getTime()));

    }
    */
}
