package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FindFriends extends AppCompatActivity {

    //Appbar customize
    Toolbar tool;
    //Appbar customize

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    FirebaseAuth mAuth;

    FirebaseDatabase database;
    DatabaseReference reference;


    ArrayList<Users> datalist;

    FindFriendsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friends);

        //Appbar customize
        tool = findViewById(R.id.toolbar);
        setSupportActionBar(tool);
        //Appbar customize

        datalist = new ArrayList<Users>();

        database = FirebaseDatabase.getInstance();
        reference =database.getReference();

        reference.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                datalist.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    Toast.makeText(FindFriends.this, ""+ds.getValue(Users.class).getName(), Toast.LENGTH_SHORT).show();
                    Users u= new Users();
                    u.id = ds.getKey();
                    u.name = ds.getValue(Users.class).getName();
                    u.email = ds.getValue(Users.class).getEmail();
                    u.token = ds.getValue(Users.class).getToken();

                    datalist.add(u);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FindFriends.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        adapter = new FindFriendsAdapter(FindFriends.this,datalist);
        recyclerView = findViewById(R.id.findfriendrecyclerview);
        linearLayoutManager = new LinearLayoutManager(FindFriends.this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);





        recyclerView.setAdapter(adapter);







    }


    @Override
    protected void onStart() {
        super.onStart();
       
    }

    public void login(){
        Intent i = new Intent(FindFriends.this,LoginActivity.class);
        startActivity(i);
        finish();
    }
}