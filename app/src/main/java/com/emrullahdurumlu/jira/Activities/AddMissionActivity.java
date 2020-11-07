package com.emrullahdurumlu.jira.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.emrullahdurumlu.jira.Classes.MyTask;
import com.emrullahdurumlu.jira.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Random;
import java.util.UUID;

public class AddMissionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    MyTask newTask;
    EditText taskHeader,taskComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mission);
        newTask = new MyTask();
        spinner=findViewById(R.id.spinner);
        taskHeader=findViewById(R.id.etTaskHeader);
        taskComment=findViewById(R.id.etTaskComment);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(AddMissionActivity.this,R.array.develoers,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        if(parent.getItemAtPosition(pos).toString().trim().equals("Artan Skorra") )
            newTask.setOwnerTask("artanskorra@gmail.com");
        if(parent.getItemAtPosition(pos).toString().trim().equals("Bekir Şencan"))
            newTask.setOwnerTask("bekirsencan@gmail.com");
       if(parent.getItemAtPosition(pos).toString().trim().equals("Emrullah Durumlu"))
           newTask.setOwnerTask("emrullahdurumlu@gmail.com");
        if(parent.getItemAtPosition(pos).toString().trim().equals("Muhammed Burak Çakır"))
            newTask.setOwnerTask("burakcakir@gmail.com");
        if(parent.getItemAtPosition(pos).toString().trim().equals("Mustecep Şahin"))
            newTask.setOwnerTask("mustecepsahin@gmail.com");

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void radioClicked(View view) {
        boolean checked = ((RadioButton)view).isChecked();

        switch (view.getId()){
            case R.id.rbDusuk:
                if(checked)
                Toast.makeText(getApplication(),"Düşük",Toast.LENGTH_SHORT).show();
                newTask.setPriority(0);
                break;
            case R.id.rbOrta:
                if(checked)
                newTask.setPriority(1);
                break;
            case R.id.rbYuksek:
                if(checked)
                newTask.setPriority(2);
                break;
        }

    }

    public void addMission(View view) {
        newTask.setTaskHeader(taskHeader.getText().toString().trim());
        newTask.setTaskComment(taskComment.getText().toString().trim());
        UUID uuid = UUID.randomUUID();
        newTask.setId(uuid+"");
        newTask.setTime(FieldValue.serverTimestamp());
        FirebaseFirestore firebaseFirestore =FirebaseFirestore.getInstance();
        firebaseFirestore.collection("TASKS").add(newTask).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Intent intent = new Intent(AddMissionActivity.this,FeedActivity.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),e.getLocalizedMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}