package com.sumonkmr.detailskeeper;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    RecyclerView myRecView;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    HashMap<String, String> notesTemp;
    List<HashMap<String, String>> notes;
    EditText name, email, phone, address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(String.valueOf(R.string.app_name), MODE_PRIVATE);
        editor = sharedPreferences.edit();
        //Method's
        HookUps();
        DrawerSetup();
        SetValue();
        GetValue();


    }//onCreate

    private void HookUps() {
        myRecView = findViewById(R.id.myRecView);
    }

    public void DrawerSetup() {
        // Find the toolbar view inside the activity layout
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            // Handle item selection here
            if (item.getItemId() == R.id.menu_item1) {
                // Handle item 1 click
            } else {
                // Add more cases as needed
                drawerLayout.closeDrawer(GravityCompat.START);
            }

            // Close the drawer after handling the item click
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private HashMap<String,String> getNotesTemp(String title, String massage){
        notesTemp = new HashMap<>();
        notesTemp.put("title",title);
        notesTemp.put("massage",massage);

        return notesTemp;
    }

    private void SetValue() {
        notes = new ArrayList<>();
        notes.add(getNotesTemp("Hello World!","This is my first massage!"));
        notes.add(getNotesTemp("Hello World!","This is my first massage!"));
        notes.add(getNotesTemp("Hello World!","This is my first massage!"));



        myAdapter adapter = new myAdapter(this,notes);
        myRecView.setAdapter(adapter);
        myRecView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void GetValue(){
        String nameString = sharedPreferences.getString("name", "Nothing found!");
        String phoneString = sharedPreferences.getString("phone", "Nothing found!");
    }

    public void ShowToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}//Main