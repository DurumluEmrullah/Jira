package com.emrullahdurumlu.jira.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emrullahdurumlu.jira.Activities.FeedActivity;
import com.emrullahdurumlu.jira.Classes.MyTask;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;

public class RecyclerViewTaskAdapter extends RecyclerView.Adapter<RecyclerViewTaskAdapter.TaskHolder> {
    private ArrayList<MyTask> tasks;
    private Context context;
    public RecyclerViewTaskAdapter(ArrayList<MyTask> _tasks, Context _context){
        tasks=_tasks;
        context=_context;
    }
    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(com.emrullahdurumlu.jira.R.layout.task_simple_item,parent,false);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, final int position) {
        String taskHeader=tasks.get(position).getTaskHeader();
        String taskComment = tasks.get(position).getTaskComment();
        int taskPriority=tasks.get(position).getPriority();
        holder.taskHeader.setText(taskHeader);
        holder.taskComment.setText(taskComment);
        switch (taskPriority){
            case 0:
                holder.taskPriority.setBackgroundColor(Color.GREEN);
                break;
            case 1:
                holder.taskPriority.setBackgroundColor(Color.YELLOW);
                break;
            case 2:
                holder.taskPriority.setBackgroundColor(Color.RED);
                break;
        }
        holder.taskFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                tasks.get(position).setFinished(true);
                db.collection("TASKS").document(tasks.get(position).getId())
                        .set(tasks.get(position), SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent intent=new Intent(context,FeedActivity.class);
                        context.startActivity(intent);
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class TaskHolder extends RecyclerView.ViewHolder {
        TextView taskHeader,taskComment,taskPriority;
        Button taskFinish;
        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            taskHeader=itemView.findViewById(com.emrullahdurumlu.jira.R.id.taskHeader);
            taskComment=itemView.findViewById(com.emrullahdurumlu.jira.R.id.taskComment);
            taskPriority = itemView.findViewById(com.emrullahdurumlu.jira.R.id.taskPriority);
            taskFinish=itemView.findViewById(com.emrullahdurumlu.jira.R.id.taskFinish);


        }
    }
}
