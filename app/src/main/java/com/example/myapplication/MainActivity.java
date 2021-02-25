package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Appbar customize
    Toolbar tool;
    //Appbar customize

    // Tablayout and Frangments
    ViewPager myViewPager;
    TabLayout myTabLayout;
    SampleFragmentPagerAdapter SampleFragmentPagerAdapter;
    // Tablayout and Frangments


    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Appbar customize
        tool = findViewById(R.id.toolbar);
        setSupportActionBar(tool);
//Appbar customize

        // Tablayout and Frangments
        myViewPager=(ViewPager) findViewById(R.id.viewpager);
        SampleFragmentPagerAdapter = new SampleFragmentPagerAdapter(getSupportFragmentManager());
        myViewPager.setAdapter(SampleFragmentPagerAdapter);

        myTabLayout=(TabLayout) findViewById(R.id.sliding_tabs);
        myTabLayout.setupWithViewPager(myViewPager);
        // Tablayout and Frangments

        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            login();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

      //  MenuInflater inflater = getMenuInflater();
    //    inflater.inflate(R.menu.main_menu,menu);


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.setting :
                setting();
            case R.id.Profile :
                profile();
            case R.id.Logout :
                logout();
            case R.id.find_friends :
                findfriends();
            default:
            return super.onOptionsItemSelected(item);

        }

    }



    public void logout(){
        FirebaseAuth.getInstance().signOut();
        login();
    }


    public void setting(){
        Toast.makeText(this, "Setting click", Toast.LENGTH_SHORT).show();
    }


    public void profile(){
        Toast.makeText(this, "Profile click", Toast.LENGTH_SHORT).show();
    }

    public void login(){
        Intent i = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(i);
        finish();
    }

    public void findfriends(){
        Intent i = new Intent(MainActivity.this,FindFriends.class);
        startActivity(i);
    }


}