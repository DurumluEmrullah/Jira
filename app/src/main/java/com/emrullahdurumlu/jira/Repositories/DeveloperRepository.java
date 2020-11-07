package com.emrullahdurumlu.jira.Repositories;

import androidx.annotation.NonNull;

import com.emrullahdurumlu.jira.Classes.Developer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeveloperRepository {
    String result="";
    public ArrayList<Developer>  developers;
    private FirebaseFirestore db;
    public DeveloperRepository(FirebaseFirestore _db){
        db=_db;
        developers=new ArrayList<>();
    }



}
