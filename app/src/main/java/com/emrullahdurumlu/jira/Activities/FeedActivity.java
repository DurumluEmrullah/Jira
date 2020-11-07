package com.emrullahdurumlu.jira.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.emrullahdurumlu.jira.Adapters.RecyclerViewFeedAdapter;
import com.emrullahdurumlu.jira.Classes.Developer;
import com.emrullahdurumlu.jira.Classes.MyTask;
import com.emrullahdurumlu.jira.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class FeedActivity extends AppCompatActivity {
    private ArrayList<Developer> developers;
    private RecyclerView recyclerView;
    private ArrayList<MyTask> currentTasks;
    private ArrayList<MyTask> finishedTasks;
    private RecyclerViewFeedAdapter recyclerViewFeedAdapter;
    TextView tvToplantı;
    int currentTask,finishedTask;

    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        tvToplantı=findViewById(R.id.tvToplanti);
        recyclerView=findViewById(R.id.recyclerView);
        developers=new ArrayList<Developer>();
        db= FirebaseFirestore.getInstance();
        currentTasks=new ArrayList<MyTask>();
        finishedTasks=new ArrayList<>();
        getToplanti();
        viewSetings();
        CollectionReference collectionReference = db.collection("DEVELOPERS");

        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null)
                    Toast.makeText(getApplicationContext(),error.getLocalizedMessage().toString(),Toast.LENGTH_SHORT).show();
                int i =0;
                if(value !=null){
                    for(DocumentSnapshot documentSnapshot : value.getDocuments()){
                        Map<String,Object> data= documentSnapshot.getData();

                        developers.add(new Developer((String)data.get("email"),(String)data.get("name"),(String)data.get("lastName") ));
                        recyclerViewFeedAdapter.notifyDataSetChanged();
                    }
                }
            }
        });




    }


    public void getToplanti(){
        CollectionReference collectionReference = db.collection("MEETINGS");

        collectionReference.orderBy("addTime", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error !=null){
                    Toast.makeText(getApplicationContext(),"Bir Hata Oluştu",Toast.LENGTH_SHORT).show();
                }
                if(value!=null){
                    for(DocumentSnapshot snapshot: value.getDocuments()){
                        String msg ="Toplantı Saati :"+snapshot.get("time")+"\n" + "Toplantı Tarihi: "+snapshot.get("date")+"\n"
                                +"Toplantıyı Düzenleyen :"+snapshot.get("owner")+"\n"+"Toplantı Konusu: "+snapshot.get("topic");
                        tvToplantı.setText(msg);
                        break;
                    }
                }
            }
        });


    }

    public void addMission(View view) {
        Intent intent = new Intent(FeedActivity.this,AddMissionActivity.class);
        startActivity(intent);

    }


    public void myProfile(View view) {
        Intent intent = new Intent(FeedActivity.this,MyProfileActivity.class);
        startActivity(intent);
    }

    private void viewSetings(){

        recyclerViewFeedAdapter=new RecyclerViewFeedAdapter(developers,getApplicationContext());
        recyclerView.setAdapter(recyclerViewFeedAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void addMeating(View view) {
        Intent intent = new Intent(FeedActivity.this, AddMeetingActivity.class);
        startActivity(intent);
    }
}