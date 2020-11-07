package com.emrullahdurumlu.jira.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.emrullahdurumlu.jira.Adapters.RecyclerViewTaskAdapter;
import com.emrullahdurumlu.jira.Classes.MyTask;
import com.emrullahdurumlu.jira.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class MyProfileActivity extends AppCompatActivity {
    public ArrayList<MyTask> tasks;
    private RecyclerView recyclerView;
    private RecyclerViewTaskAdapter recyclerViewTaskAdapter;
    private FirebaseUser user;
    private FirebaseFirestore db;
    TextView tvfinishedTask,tvCurrentTask;
    int finishedTask=0,currentTask=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db=FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        tasks=new ArrayList<>();
        tvfinishedTask=findViewById(R.id.tvFinishedTask);
        tvCurrentTask=findViewById(R.id.tvCurrentTasksCount);
        viewSetings();
        fillTheList();
        recyclerViewTaskAdapter.notifyDataSetChanged();
        System.out.println("Bitenler "+finishedTask);
        System.out.println(user.getEmail());
    }
    private void fillTheList(){

        CollectionReference collectionReference = db.collection("TASKS");
        collectionReference.whereEqualTo("ownerTask",user.getEmail()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull com.google.android.gms.tasks.Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot documentSnapshot: task.getResult()){
                    Map<String,Object> data = documentSnapshot.getData();
                  if(!(boolean)data.get("finished"))
                  {
                      currentTask++;
                      tvCurrentTask.setText("Devam Eden Görevler\n"+currentTask);
                      String ownerTask= (String) data.get("ownerTask");
                      long priority=(long) data.get("priority") ;
                      String taskHeader = (String) data.get("taskHeader");
                      String taskComment=(String)data.get("taskComment");
                      boolean isFinished =(boolean)data.get("finished");
                      String id= (String) documentSnapshot.getId();
                      MyTask newMyTask= new MyTask((String) data.get("ownerTask") ,(int) priority,(String) data.get("taskHeader"),(String) data.get("taskComment"),(boolean) data.get("finished"));
                      newMyTask.setId(id);
                      tasks.add(newMyTask);
                      recyclerViewTaskAdapter.notifyDataSetChanged();
                  }
                  else{
                      finishedTask++;
                      tvfinishedTask.setText("Tamamlanan Görevler\n"+finishedTask);
                  }
                                     }
            }
        });
    }
    private void viewSetings(){
        recyclerView=findViewById(R.id.receyclerView);
        tasks=new ArrayList<MyTask>();
        recyclerViewTaskAdapter=new RecyclerViewTaskAdapter(tasks,getApplicationContext());
        recyclerView.setAdapter(recyclerViewTaskAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}