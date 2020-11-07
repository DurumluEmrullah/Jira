package com.emrullahdurumlu.jira.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.emrullahdurumlu.jira.Classes.Meeting;
import com.emrullahdurumlu.jira.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddMeetingActivity extends AppCompatActivity {
    CalendarView calendarView;
    EditText time,topic;
    FirebaseFirestore db;
    String _time,_topic,_owner,_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meating);
        calendarView=findViewById(R.id.calenderView);
        time=findViewById(R.id.etTime);
        topic=findViewById(R.id.etTopic);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                _date=year+"/"+month+"/"+dayOfMonth;

            }
        });

    }

    public void addMeeting(View view) {
        _time=time.getText().toString().trim();
        _topic=topic.getText().toString().trim();
        _owner= FirebaseAuth.getInstance().getCurrentUser().getEmail().trim();
        if(_owner.trim().equals("artanskorra@gmail.com")){
            _owner="Artan Skorra";
        }
        if(_owner.trim().equals("mustecepsahin@gmail.com")){
            _owner="Mustecep Şahin";
        }
        if(_owner.trim().equals("bekirsencan@gmail.com")){
            _owner="Bekir Şencan";
        }
        if(_owner.trim().equals("burakcakir@gmail.com")){
            _owner="Muhammed Burak Çakır";
        }
        if(_owner.trim().equals("emrullahdurumlu@gmail.com")){
            _owner="Emrullah Durumlu";
        }
        Meeting meeting = new Meeting(_time,_date,_owner,_topic, FieldValue.serverTimestamp());
        if(!_time.isEmpty()&&!_topic.isEmpty()&&_date!=null){
            db=FirebaseFirestore.getInstance();
            db.collection("MEETINGS").add(meeting).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Intent intent = new Intent(getApplicationContext(),FeedActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

        }
        else{
            Toast.makeText(getApplicationContext(),"Tüm Alanları Doldurunuz",Toast.LENGTH_SHORT).show();
        }
        /*


*/

    }
}