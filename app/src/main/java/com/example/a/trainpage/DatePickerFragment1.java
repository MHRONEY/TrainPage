package com.example.a.trainpage;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

import static com.example.a.trainpage.MainActivity.DateEdit;
import static com.example.a.trainpage.MainActivity.DateEdit1;

public class DatePickerFragment1 extends DialogFragment implements DatePickerDialog.OnDateSetListener {


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
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        DateEdit1.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
        // DateEdit1.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
    }
}
