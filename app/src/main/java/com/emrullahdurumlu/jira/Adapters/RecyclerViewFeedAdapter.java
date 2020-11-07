package com.emrullahdurumlu.jira.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emrullahdurumlu.jira.Classes.Developer;
import com.emrullahdurumlu.jira.Activities.DetailsActivity;
import com.emrullahdurumlu.jira.R;

import java.util.ArrayList;

public class RecyclerViewFeedAdapter extends RecyclerView.Adapter<RecyclerViewFeedAdapter.MyViewHolder> {

    private ArrayList<Developer> developers;
    private Context context;

    public RecyclerViewFeedAdapter(ArrayList<Developer> _develepers,Context _context){
        developers=_develepers;
        context=_context;

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcyclerview_feed,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        String fullName=developers.get(position).getName() +" "+developers.get(position).getLastName();

        holder.nameAndSurname.setText(fullName);
        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), DetailsActivity.class);
                intent.putExtra("email",developers.get(position).getEmail());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return developers.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameAndSurname;
        ImageView details;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameAndSurname=itemView.findViewById(R.id.nameAndSurname);
            details=itemView.findViewById(R.id.details);

        }
    }



}
