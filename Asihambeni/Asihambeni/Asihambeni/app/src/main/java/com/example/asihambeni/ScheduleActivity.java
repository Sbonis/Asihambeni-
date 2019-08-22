package com.example.asihambeni;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import java.text.DateFormat;
import java.util.Calendar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ScheduleActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{
    private EditText pickUp,destination;
    private Button date_,time_,schedule_;
    private DatabaseReference databaseReference;
    private RadioGroup radioTypeGroup;
    private RadioButton radioTypeButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);

        databaseReference = FirebaseDatabase.getInstance().getReference("schedule");

        pickUp =(EditText)findViewById(R.id.editlocation);
        destination =(EditText)findViewById(R.id.editDestination);

        date_ = (Button)findViewById(R.id.date);
        time_ = (Button)findViewById(R.id.time);
        radioTypeGroup = (RadioGroup)findViewById(R.id.radioType);

        schedule_ = (Button)findViewById(R.id.schedule);



        date_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datepick = new DatePickerFragment();
                datepick.show(getSupportFragmentManager(),"date picker");
            }
        });

        time_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"time picker");
            }
        });

        schedule_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String point = pickUp.getText().toString();
                String destination_ = destination.getText().toString();
                String date = date_.getText().toString();
                String time = time_.getText().toString();

                int selectedId = radioTypeGroup.getCheckedRadioButtonId();

                radioTypeButton = (RadioButton) findViewById(selectedId);
                String type = radioTypeButton.getText().toString();


                if(!TextUtils.isEmpty(point) && !TextUtils.isEmpty(destination_) && !date.equals("Pick Date") && !time.equals("Pick Time")){
                    String id = databaseReference.push().getKey();
                    Schedule schedule = new Schedule(id,date,destination_,point,time,type);
                    databaseReference.child(id).setValue(schedule);
                    pickUp.setText("");
                    destination.setText("");
                    date_.setText("Pick Date");
                    time_.setText("Pick Time");
                    startActivity(new Intent(ScheduleActivity.this, MainMenuActivity.class));
                    finish();

                }
                else if (TextUtils.isEmpty(point)) {
                    Toast.makeText(getApplicationContext(), "Enter pick up point", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if (TextUtils.isEmpty(destination_)) {
                    Toast.makeText(getApplicationContext(), "Enter your destination", Toast.LENGTH_SHORT).show();
                    return;
                }
                //String pickD = "PICK DATE";
               // String pickT = "PICK TIME";

                else if (date.equals("Pick Date")) {
                    Toast.makeText(getApplicationContext(), "Pick a date", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if (time.equals("Pick Time")) {
                    Toast.makeText(getApplicationContext(), "Pick time", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });


    }

    @Override
    public void onDateSet(DatePicker datepick, int i, int i1, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,i);
        calendar.set(Calendar.MONTH,i1);
        calendar.set(Calendar.DAY_OF_MONTH,i2);
        String pickedDate = DateFormat.getDateInstance().format(calendar.getTime());
        Button date_ = (Button)findViewById(R.id.date);
        date_.setText(pickedDate);

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        Button time_ = (Button)findViewById(R.id.time);
        time_.setText(i+":"+i1);
    }

}
