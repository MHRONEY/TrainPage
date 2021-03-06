package com.example.a.trainpage;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

import static com.example.a.trainpage.MainActivity.DateEdit;
import static com.example.a.trainpage.MainActivity.DateEdit1;

public class TimePickerFragment1 extends DialogFragment implements TimePickerDialog.OnTimeSetListener {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        DateEdit1.setText(DateEdit1.getText() + " -" + hourOfDay + ":" + minute);
        // DateEdit1.setText(DateEdit1.getText() + " -" + hourOfDay + ":" + minute);
    }

}
