package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    Toolbar tool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tool = findViewById(R.id.toolbar);
        setSupportActionBar(tool);




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