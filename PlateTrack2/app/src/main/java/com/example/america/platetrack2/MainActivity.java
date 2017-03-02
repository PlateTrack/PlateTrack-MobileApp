package com.example.america.platetrack2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.TextView;


import java.util.EventListener;
import java.util.Calendar;
import java.text.DateFormat;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        EditText platebox = (EditText)findViewById(R.id.platebox);
        String plate_number = platebox.getText().toString();
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


        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            //eventually these dates will be sent to the cloud to retrieve the information about the plates numbers.
            Calendar cal = new GregorianCalendar(year, month, day);
            /* final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
            ((TextView) findViewById(R.id.dateHere)).setText(dateFormat.format(cal.getTime()));*/
            //setDate(cal);
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
    private void setDate(final Calendar calendar) {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        ((TextView) findViewById(R.id.dateHere)).setText(dateFormat.format(calendar.getTime()));
    }
}
