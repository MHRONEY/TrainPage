package com.example.a.trainpage;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {


//    private static final int REQ_PERMISSION= 120;


    private EditText mIssue_d, mJouney_d, mCoach_N, mSeat_r, mNo_seat, mNo_adult, mNo_child, mFare, mVat, mTotal_f, mMobile, mPin;

    private Spinner mTrain_No, mFrom_station, mTo_station, mClass_name;


    static EditText DateEdit;
    static EditText DateEdit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


       // regPermission();


        mIssue_d = findViewById( R.id.data_time );
        mJouney_d = findViewById( R.id.data_time1 );
        mCoach_N = findViewById( R.id.coach_name );
        mSeat_r = findViewById( R.id.seat_range );
        mNo_seat = findViewById( R.id.no_of_seat );
        mNo_adult = findViewById( R.id.no_of_adult );
        mNo_child = findViewById( R.id.no_of_child );
        mFare = findViewById( R.id.fare );
        mVat = findViewById( R.id.vat );
        mTotal_f = findViewById( R.id.total );
        mMobile = findViewById( R.id.mobile );
        mPin = findViewById( R.id.pin );
        mTrain_No = findViewById( R.id.train_name );
        mFrom_station = findViewById( R.id.from_station );
        mTo_station = findViewById( R.id.to_station );
        mClass_name = findViewById( R.id.class_name );

//
//        if (!checkPermission()) {
//            openActivity();
//        } else {
//            if (checkPermission()) {
//                requestPermissionAndContinue();
//            } else {
//                openActivity();
//            }
//        }




        DateEdit = (EditText) findViewById( R.id.data_time );
        DateEdit1 = (EditText) findViewById( R.id.data_time1 );


        DateEdit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                showTruitonTimePickerDialog( v );
                showTruitonDatePickerDialog( v );
            }
        } );


        DateEdit1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTruitonTimePickerDialog1( v );
                showTruitonDatePickerDialog1( v );
            }
        } );

    }



//
//    public void regPermission(){
//        int regEx =(ContextCompat.checkSelfPermission( this, Manifest.permission.READ_EXTERNAL_STORAGE ) ) ;
//
//        if (regEx!=PackageManager.PERMISSION_GRANTED){
//
//            ActivityCompat.requestPermissions( this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQ_PERMISSION);
//
//        }
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
//
//        if (requestCode==REQ_PERMISSION && grantResults[0] ==PackageManager.PERMISSION_GRANTED){
//           berita("permission ok");
//
//        }
//        else {
//            berita("permission failed");
//        }
//
//    }
//
//    public void berita(String s){
//        Toast.makeText( getApplicationContext(),s,Toast.LENGTH_LONG ).show();
//    }
//
//
//
//




    private void showTruitonDatePickerDialog1(View v) {

        DialogFragment newFragment = new DatePickerFragment1();
        newFragment.show( this.getFragmentManager(), "datePicker" );

    }


    public static class DatePickerFragment1 extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get( Calendar.YEAR );
            int month = c.get( Calendar.MONTH );
            int day = c.get( Calendar.DAY_OF_MONTH );

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog( getActivity(), this, year, month, day );
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            DateEdit1.setText( day + "/" + (month + 1) + "/" + year );
            //   DateEdit1.setText(day + "/" + (month + 1) + "/" + year);
        }
    }


    private void showTruitonTimePickerDialog1(View v) {
        DialogFragment newFragment = new TimePickerFragment1();
        newFragment.show( this.getFragmentManager(), "timePicker" );

    }


    public static class TimePickerFragment1 extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get( Calendar.HOUR_OF_DAY );
            int minute = c.get( Calendar.MINUTE );

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog( getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat( getActivity() ) );
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            // DateEdit.setText(DateEdit.getText() + " -" + hourOfDay + ":" + minute);
            DateEdit1.setText( DateEdit1.getText() + " -" + hourOfDay + ":" + minute );
        }
    }


    public void showTruitonDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show( this.getFragmentManager(), "datePicker" );
    }

    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get( Calendar.YEAR );
            int month = c.get( Calendar.MONTH );
            int day = c.get( Calendar.DAY_OF_MONTH );

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog( getActivity(), this, year, month, day );
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            DateEdit.setText( day + "/" + (month + 1) + "/" + year );
            //   DateEdit1.setText(day + "/" + (month + 1) + "/" + year);
        }
    }

    public void showTruitonTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show( this.getFragmentManager(), "timePicker" );
    }

    public static class TimePickerFragment extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get( Calendar.HOUR_OF_DAY );
            int minute = c.get( Calendar.MINUTE );

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog( getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat( getActivity() ) );
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            DateEdit.setText( DateEdit.getText() + " -" + hourOfDay + ":" + minute );
            //DateEdit1.setText(DateEdit1.getText() + " -" + hourOfDay + ":" + minute);
        }
    }


    public void booking(View view) {


        String Issue_data = mIssue_d.getText().toString();
        String Journey_date = mJouney_d.getText().toString();
        String Coach_Name = mCoach_N.getText().toString();
        String Seat_Range = mSeat_r.getText().toString();
        String No_of_Seat = mNo_seat.getText().toString();
        String No_of_adult = mNo_adult.getText().toString();
        String No_of_Child = mNo_child.getText().toString();
        String Fare = mFare.getText().toString();
        String Vat = mVat.getText().toString();
        String Total = mTotal_f.getText().toString();
        String Mobile_No = mMobile.getText().toString();
        String Pin_No = mPin.getText().toString();
        String Train_Name = mTrain_No.getSelectedItem().toString();
        String From_station = mFrom_station.getSelectedItem().toString();
        String To_station = mTo_station.getSelectedItem().toString();
        String Class_name = mClass_name.getSelectedItem().toString();


        Intent intent = new Intent( this, SecondActivity.class );


        intent.putExtra( "Issure", Issue_data );
        intent.putExtra( "Journey", Journey_date );
        intent.putExtra( "Coach", Coach_Name );
        intent.putExtra( "Seat_R", Seat_Range );
        intent.putExtra( "No_Seat", No_of_Seat );
        intent.putExtra( "adult", No_of_adult );
        intent.putExtra( "Child", No_of_Child );
        intent.putExtra( "Fare_cost", Fare );
        intent.putExtra( "Vat_cost", Vat );
        intent.putExtra( "Total_cost", Total );
        intent.putExtra( "CellNumber", Mobile_No );
        intent.putExtra( "Pin", Pin_No );
        intent.putExtra( "T_Name", Train_Name );
        intent.putExtra( "From_Destination", From_station );
        intent.putExtra( "To_Depature", To_station );
        intent.putExtra( "Class_Name", Class_name);


        startActivity(intent);



    }



//
//
////    ///////////////////////////permission////////////////
//
//    private boolean checkPermission() {
//
//        return ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
//                && ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
//                ;
//    }
//
//    private void requestPermissionAndContinue() {
//        if (ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
//                && ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, WRITE_EXTERNAL_STORAGE)
//                    && ActivityCompat.shouldShowRequestPermissionRationale(this, READ_EXTERNAL_STORAGE)) {
//                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
//                alertBuilder.setCancelable(true);
//                alertBuilder.setTitle("permission_necessary");
//                alertBuilder.setMessage("storage_permission_is_encessary_to_wrote_event");
//
//
//
//
//                alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//                    public void onClick(DialogInterface dialog, int which) {
//                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{WRITE_EXTERNAL_STORAGE
//                                , READ_EXTERNAL_STORAGE}, REQ_PERMISSION);
//                    }
//                });
//                AlertDialog alert = alertBuilder.create();
//                alert.show();
//                Log.e("", "permission denied, show dialog");
//            } else {
//                ActivityCompat.requestPermissions(MainActivity.this, new String[]{WRITE_EXTERNAL_STORAGE,
//                        READ_EXTERNAL_STORAGE}, REQ_PERMISSION);
//            }
//        } else {
//            openActivity();
//        }
//    }
//
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        if (requestCode == REQ_PERMISSION) {
//            if (permissions.length > 0 && grantResults.length > 0) {
//
//                boolean flag = true;
//                for (int i = 0; i < grantResults.length; i++) {
//                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
//                        flag = false;
//                    }
//                }
//                if (flag) {
//                    openActivity();
//                } else {
//                    finish();
//                }
//
//            } else {
//                finish();
//            }
//        } else {
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        }
//    }
//
//    private void openActivity() {
//
//
//       // createPdf();
//
//     Toast.makeText( MainActivity.this,"permission is success",Toast.LENGTH_LONG ).show();
//
//
//        //add your further process after giving permission or to download images from remote server.
//    }
//
//
//
//
//    ///////////////////////////permission////////////////
//






}