package com.example.todo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Work extends AppCompatActivity {

    private  static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;
    public Button Done;
    String Assigment , date , date1;
    public TextView assigment;

    //  public  int i = 0 ;

    @Override
  protected void onCreate(Bundle savedInstanceState){
  super.onCreate(savedInstanceState);
  setContentView(R.layout.work);
  Done = (Button) findViewById(R.id.isoneortwo);
  Done.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

          Intent intent1 = new Intent(Work.this,MainActivity.class);
          intent1.putExtra("date",date );
          startActivity(intent1);
      }
  });
  // aca tengo que setear las cosas antes de que entre al metodo de modificacion de la fecha
  Toast.makeText(Work.this,"Press Done if you dont wanna pick two Dates",Toast.LENGTH_LONG).show();


  mCalendarView = (CalendarView) findViewById(R.id.calendarView);
  mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
                        date = year + "/" + month + "/"+ dayOfMonth ;
                        Log.d(TAG, "onSelectedDayChange: yyyy/mm/dd:" + date);
                }
            });
  mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
      @Override
      public void onSelectedDayChange(@NonNull CalendarView calendarView, int year2, int month2, int dayOfMonth2) {
                 date1 = year2 + "/" + month2 + "/" + dayOfMonth2 ;
                 Log.d(TAG, "onSelectedDayChange: yyyy/mm/dd:" + date1);
      }
  });
        }

    }
