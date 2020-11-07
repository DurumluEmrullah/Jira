package com.emrullahdurumlu.jira.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.emrullahdurumlu.jira.R;
//import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class DetailsActivity extends AppCompatActivity {
    TextView currentTask,finishedTask;
    ArrayList<String> headers;
    ArrayList<Boolean> isFinished;
    String _finishedTask="Biten Görevler \n\n\n";
    String _currentTask="Mevcut Görevler \n\n\n";

    String email;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        email=intent.getStringExtra("email").trim();
        db=FirebaseFirestore.getInstance();
        currentTask=findViewById(R.id.tvCurrentTasks);
        finishedTask=findViewById(R.id.tvFinishedTask);

        headers=new ArrayList<>();
        isFinished=new ArrayList<>();
        getTask();

    }

    public void getFinishedTask(){


    }
    public void getCurrentTask(){

    }
    public void getTask(){


        CollectionReference collectionReference = db.collection("TASKS");
        collectionReference.whereEqualTo("ownerTask",email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull com.google.android.gms.tasks.Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot documentSnapshot: task.getResult()){
                    Map<String,Object> data = documentSnapshot.getData();
                    if((boolean)data.get("finished")){
                        _finishedTask +="\t-"+(String)data.get("taskHeader")+"\n";
                    }
                    else{
                        _currentTask +="\t-"+(String)data.get("taskHeader")+"\n";
                    }

                }
                finishedTask.setText(_finishedTask);
                currentTask.setText(_currentTask);
            }
        });

    }
}