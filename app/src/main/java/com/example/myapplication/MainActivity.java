package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

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



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Logout :
                logout();
                return true;
            case R.id.setting :
                setting();
                return true;
            case R.id.Profile :
                profile();
                return true;
            default:
            return super.onOptionsItemSelected(item);

        }

    }



    public void logout(){
        Toast.makeText(this, "Logout click", Toast.LENGTH_SHORT).show();
    }


    public void setting(){
        Toast.makeText(this, "Setting click", Toast.LENGTH_SHORT).show();
    }


    public void profile(){
        Toast.makeText(this, "Profile click", Toast.LENGTH_SHORT).show();
    }



}