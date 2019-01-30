package com.example.a.trainpage;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PERMISSION_REQUEST_CODE = 1;


    private TextView mIssue_d, mJouney_d, mCoach_N, mSeat_r, mNo_seat, mNo_adult, mNo_child, mFare, mVat, mTotal_f, mMobile, mPin;

    private TextView mTrain_No, mFrom_station, mTo_station, mClass_name;


    // private LinearLayout llScroll;
    private LinearLayout ll_pdflayout;
    // private Bitmap bitmap;
    private Button mPdf, btn_generate;


    public static int REQUEST_PERMISSIONS = 1;
    boolean boolean_permission;
    boolean boolean_save;
    Bitmap bitmap;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_second );


        fn_permission();

        // llScroll = findViewById( R.id.llScroll );

//        mPdf = findViewById( R.id.Pdf_file );


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


        String F_Issue = getIntent().getStringExtra( "Issure" );
        String F_Journey = getIntent().getStringExtra( "Journey" );
        String F_Coach = getIntent().getStringExtra( "Coach" );
        String F_Seat_r = getIntent().getStringExtra( "Seat_R" );
        String F_No_Seat = getIntent().getStringExtra( "No_Seat" );
        String F_adult = getIntent().getStringExtra( "adult" );
        String F_Child = getIntent().getStringExtra( "Child" );
        String F_fare_cost = getIntent().getStringExtra( "Fare_cost" );
        String F_Vat_cost = getIntent().getStringExtra( "Vat_cost" );
        String F_Total_cost = getIntent().getStringExtra( "Total_cost" );
        String F_CellNumber = getIntent().getStringExtra( "CellNumber" );
        String F_Pin = getIntent().getStringExtra( "Pin" );
        String F_T_name = getIntent().getStringExtra( "T_Name" );
        String F_From_Destination = getIntent().getStringExtra( "From_Destination" );
        String F_To_Departure = getIntent().getStringExtra( "To_Depature" );
        String F_Class_Name = getIntent().getStringExtra( "Class_Name" );


        mIssue_d.setText( F_Issue );
        mJouney_d.setText( F_Journey );
        mCoach_N.setText( F_Coach );
        mSeat_r.setText( F_Seat_r );
        mNo_seat.setText( F_No_Seat );
        mNo_adult.setText( F_adult );
        mNo_child.setText( F_Child );
        mFare.setText("BDT "+ F_fare_cost );
        mVat.setText("BDT "+ F_Vat_cost );
        mTotal_f.setText("BDT "+ F_Total_cost );
        mMobile.setText("+88 "+ F_CellNumber );
        mPin.setText( F_Pin );
        mTrain_No.setText( F_T_name );
        mFrom_station.setText( F_From_Destination );
        mTo_station.setText( F_To_Departure );
        mClass_name.setText( F_Class_Name );


        ll_pdflayout = findViewById( R.id.ll_pdflayout );


        btn_generate = findViewById( R.id.btn_generate );


        listener();


//        ///////////////////////////permission////////////////
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
//        ///////////////////////////permission////////////////

//
//        mPdf.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d( "size", " " + llScroll.getWidth() + "  " + llScroll.getWidth() );
//                bitmap = loadBitmapFromView( llScroll, llScroll.getWidth(), llScroll.getHeight() );
//                createPdf();
//            }
//        } );
//
//
//    }
//

//    ///////////////////////////permission////////////////
//
//    private boolean checkPermission() {
//
//        return ContextCompat.checkSelfPermission( this, WRITE_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED
//                && ContextCompat.checkSelfPermission( this, READ_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED
//                ;
//    }
//
//    private void requestPermissionAndContinue() {
//        if (ContextCompat.checkSelfPermission( this, WRITE_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED
//                && ContextCompat.checkSelfPermission( this, READ_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED) {
//
//            if (ActivityCompat.shouldShowRequestPermissionRationale( this, WRITE_EXTERNAL_STORAGE )
//                    && ActivityCompat.shouldShowRequestPermissionRationale( this, READ_EXTERNAL_STORAGE )) {
//                AlertDialog.Builder alertBuilder = new AlertDialog.Builder( this );
//                alertBuilder.setCancelable( true );
//                alertBuilder.setTitle( getString( R.string.permission_necessary ) );
//                alertBuilder.setMessage( R.string.storage_permission_is_encessary_to_wrote_event );
//
//                alertBuilder.setPositiveButton( android.R.string.yes, new DialogInterface.OnClickListener() {
//                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//                    public void onClick(DialogInterface dialog, int which) {
//                        ActivityCompat.requestPermissions( SecondActivity.this, new String[]{WRITE_EXTERNAL_STORAGE
//                                , READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE );
//                    }
//                } );
//                AlertDialog alert = alertBuilder.create();
//                alert.show();
//                Log.e( "", "permission denied, show dialog" );
//            } else {
//                ActivityCompat.requestPermissions( SecondActivity.this, new String[]{WRITE_EXTERNAL_STORAGE,
//                        READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE );
//            }
//        } else {
//            openActivity();
//        }
//    }
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        if (requestCode == PERMISSION_REQUEST_CODE) {
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
//            super.onRequestPermissionsResult( requestCode, permissions, grantResults );
//        }
//    }
//
//    private void openActivity() {
//
//
//        createPdf();
//        //add your further process after giving permission or to download images from remote server.
//    }
//
//
//    ///////////////////////////permission////////////////
//
//
//    public static Bitmap loadBitmapFromView(View v, int width, int height) {
//        Bitmap b = Bitmap.createBitmap( width, height, Bitmap.Config.ARGB_8888 );
//        Canvas c = new Canvas( b );
//        v.draw( c );
//
//        return b;
//    }
//
//
//    private void createPdf() {
//
//
//        WindowManager wm = (WindowManager) getSystemService( Context.WINDOW_SERVICE );
//        //  Display display = wm.getDefaultDisplay();
//        DisplayMetrics displaymetrics = new DisplayMetrics();
//        this.getWindowManager().getDefaultDisplay().getMetrics( displaymetrics );
//        float hight = displaymetrics.heightPixels;
//        float width = displaymetrics.widthPixels;
//
//        int convertHighet = (int) hight, convertWidth = (int) width;
//
////        Resources mResources = getResources();
////        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);
//
//        PdfDocument document = new PdfDocument();
//        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder( convertWidth, convertHighet, 1 ).create();
//        PdfDocument.Page page = document.startPage( pageInfo );
//
//        Canvas canvas = page.getCanvas();
//
//        Paint paint = new Paint();
//        canvas.drawPaint( paint );
//
//        bitmap = Bitmap.createScaledBitmap( bitmap, convertWidth, convertHighet, true );
//
//        paint.setColor( Color.BLUE );
//        canvas.drawBitmap( bitmap, 0, 0, null );
//        document.finishPage( page );
//
//        // write the document c    ontent
//        String targetPdf = "/sdcard/pdffromScroll.pdf";
//        File filePath;
//        filePath = new File( targetPdf );
//        try {
//            document.writeTo( new FileOutputStream( filePath ) );
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText( this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG ).show();
//        }
//
//        // close the document
//        document.close();
//        Toast.makeText( this, "PDF of Scroll is created!!!", Toast.LENGTH_SHORT ).show();
//
//        openGeneratedPDF();
//
//    }
//
//    private void openGeneratedPDF() {
//
//        File file = new File( "/sdcard/pdffromScroll.pdf" );
//        if (file.exists()) {
//            Intent intent = new Intent( Intent.ACTION_VIEW );
//            Uri uri = Uri.fromFile( file );
//            intent.setDataAndType( uri, "application/pdf" );
//            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
//
//            try {
//                startActivity( intent );
//            } catch (ActivityNotFoundException e) {
//                Toast.makeText( SecondActivity.this, "No Application available to view pdf", Toast.LENGTH_LONG ).show();
//            }
//        }
//    }

    }


    private void fn_permission() {
        if ((ContextCompat.checkSelfPermission( getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission( getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED)) {

            if ((ActivityCompat.shouldShowRequestPermissionRationale( SecondActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE ))) {
            } else {
                ActivityCompat.requestPermissions( SecondActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS );

            }

            if ((ActivityCompat.shouldShowRequestPermissionRationale( SecondActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE ))) {
            } else {
                ActivityCompat.requestPermissions( SecondActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS );

            }
        } else {
            boolean_permission = true;


        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
        if (requestCode == REQUEST_PERMISSIONS) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                boolean_permission = true;


            } else {
                Toast.makeText( getApplicationContext(), "Please allow the permission", Toast.LENGTH_LONG ).show();

            }
        }


    }


    private void listener() {

        btn_generate.setOnClickListener( this );
    }

    @Override
    public void onClick(View view) {


        btn_generate.setVisibility( View.GONE);

        switch (view.getId()) {
            case R.id.btn_generate:

                if (boolean_save) {
                  //  Intent intent = new Intent( getApplicationContext(), PDFViewActivity.class );
                   // startActivity( intent );

//
//                    Toast.makeText( getApplicationContext(),"sucessfully buy ticket",Toast.LENGTH_LONG ).show();
//
//
//                    Intent intent = new Intent( getApplicationContext(), MainActivity.class );


                }

         else{
            if (boolean_permission) {
                progressDialog = new ProgressDialog( SecondActivity.this );
                progressDialog.setMessage( "Please wait" );
                bitmap = loadBitmapFromView( ll_pdflayout, ll_pdflayout.getWidth(), ll_pdflayout.getHeight() );
                createPdf();
//                        saveBitmap(bitmap);
            } else {

            }

            createPdf();
            break;
        }
    }

}

    private void createPdf() {
        WindowManager wm = (WindowManager) getSystemService( Context.WINDOW_SERVICE );
        Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics( displaymetrics );
        float hight = displaymetrics.heightPixels;
        float width = displaymetrics.widthPixels;

        int convertHighet = (int) hight, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder( convertWidth, convertHighet, 1 ).create();
        PdfDocument.Page page = document.startPage( pageInfo );

        Canvas canvas = page.getCanvas();


        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#ffffff"));
        canvas.drawPaint( paint );



        bitmap = Bitmap.createScaledBitmap( bitmap, convertWidth, convertHighet, true );


        paint.setColor( Color.BLUE );
        canvas.drawBitmap( bitmap, 0, 0, null );
        document.finishPage( page );


        // write the document content
        String targetPdf = "/sdcard/TrainTicket.pdf";
        File filePath = new File( targetPdf );
        try {
            document.writeTo( new FileOutputStream( filePath ) );


            Toast.makeText( getApplicationContext(),"sucessfully buy ticket",Toast.LENGTH_LONG ).show();


            Intent intent = new Intent( getApplicationContext(), MainActivity.class );

            startActivity( intent );

          //  btn_generate.setText( "Check PDF" );
         //   boolean_save = true;

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText( this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG ).show();
        }
        // close the document
        document.close();
    }


    public static Bitmap loadBitmapFromView(View v, int width, int height) {

        Bitmap b = Bitmap.createBitmap( width, height, Bitmap.Config.ARGB_8888 );
        Canvas c = new Canvas( b );
        v.draw( c );
        return b;

    }


}






