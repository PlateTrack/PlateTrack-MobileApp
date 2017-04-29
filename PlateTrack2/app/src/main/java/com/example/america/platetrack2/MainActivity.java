package com.example.america.platetrack2;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
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

    //send_string_from and send_string_to are going to eventually be sent to the database,
    // the values are in the onDateSet(DatePicker view, int year, int month, int day) method
    static String send_string_from, send_string_to;
    // the plate number is already in the plate_number string variable; this needs to be sent.



    protected static TextView displayCurrentTime;
    protected static TextView displayLastDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayCurrentTime = (TextView)findViewById(R.id.dateHere);
        displayLastDate = (TextView)findViewById(R.id.lastdateHere);

        Button btn = (Button)findViewById(R.id.buttonCamera);
        Button searchButton = (Button) findViewById(R.id.button);

        //the send_string_from and send_string_to should be added here so that the dates are sent to the database

        searchButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                EditText platebox = (EditText)findViewById(R.id.platebox);
                String plate_number = platebox.getText().toString();

                //here, send the dates and plate number to the database:
                //plate_number, send_string_from, send_string_to will be sent.

                try {
//                    new PlateTracker(MainActivity.this, plate_number, send_string_from, send_string_to).execute();
                    new PlateTracker(MainActivity.this, "VAANG0", "4/28/2017", "4/30/2017").execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VideoCaptureActivity.class));
            }
        });
    }


    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
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

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {

            send_string_from = String.valueOf(month + 1) + '/' + String.valueOf(day) + '/' + String.valueOf(year);

            displayCurrentTime.setText(String.valueOf(month + 1) + '/' + String.valueOf(day) + '/' + String.valueOf(year));
        }
    }

    public static class DatePicker2Fragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

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

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            // DatePicker test = view;
            send_string_to = String.valueOf(month + 1) + '/' + String.valueOf(day) + '/' + String.valueOf(year);

           // displayLastDate.setText(String.valueOf(month + 1) + '/' + String.valueOf(day) + '/' + String.valueOf(year));
            displayLastDate.setText(send_string_to);
        }
    }

    public void calendarClick(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void calendarClick2(View v){
        DialogFragment newFragment = new DatePicker2Fragment();
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
