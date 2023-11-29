package com.sumonkmr.detailskeeper;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    RecyclerView myRecView;
   static SharedPreferences sharedPreferences;
   static SharedPreferences.Editor editor;
    HashMap<String, String> notesTemp;
    List<HashMap<String, String>> notes;
    String savedTitle, savedMassage;
    EditText titleEd, massageEd;
    FloatingActionButton floatingBtn;
    AlertDialog alertDialog;


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

        floatingBtn.setOnClickListener(v-> {
            FloatingDialog(this);
        });

    }//onCreate

    private void FloatingDialog(Context context) {
        // Create an AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Get the layout inflater
        LayoutInflater inflater = getLayoutInflater();

        // Inflate the custom layout for the dialog
        View customView = inflater.inflate(R.layout.alert_dialog, null);

        // Set the custom view to the AlertDialog
        builder.setView(customView);

        // Get references to views in the custom layout if needed
        Button okButton = customView.findViewById(R.id.submit);
        Button cancelButton = customView.findViewById(R.id.cencel);
        EditText titleEd = customView.findViewById(R.id.titleEd);
        EditText massageEd = customView.findViewById(R.id.massageEd);

        // Set click listeners for buttons in the custom layout
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle OK button click
                // You can add code here to handle the OK button click
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle Cancel button click
                // You can add code here to handle the Cancel button click
                alertDialog.dismiss();
            }
        });

        // Create and show the AlertDialog
        alertDialog = builder.create();
        alertDialog.show();
    }

    public void GetValue() {
        // Retrieve data from SharedPreferences if available, otherwise use default values
        savedTitle = sharedPreferences.getString("title", "Title is not Found!");
        savedMassage = sharedPreferences.getString("massage", "Massage is not Found!");
        Log.d("onBindViewHolder", "onBindViewHolder: "+savedTitle+" "+ savedMassage);
    }

    private void HookUps() {
        myRecView = findViewById(R.id.myRecView);
        floatingBtn = findViewById(R.id.floatingBtn);
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

//    private void SetValue(EditText titleEd, EditText massageEd) {
//        notes = new ArrayList<>();
//        notes.add(getNotesTemp(String.valueOf(titleEd),String.valueOf(massageEd)));
//
//
//
//        myAdapter adapter = new myAdapter(this,notes);
//        myRecView.setAdapter(adapter);
//        myRecView.setLayoutManager(new LinearLayoutManager(this));
//    }
    private void SetValue() {
        notes = new ArrayList<>();
        notes.add(getNotesTemp("this is Title","this is Massage!"));
        notes.add(getNotesTemp("this is Title","this is Massage!"));
        notes.add(getNotesTemp("this is Title","this is Massage!"));
        notes.add(getNotesTemp("this is Title","this is Massage!"));
        notes.add(getNotesTemp("this is Title","this is Massage!"));
        notes.add(getNotesTemp("this is Title","this is Massage!"));
        notes.add(getNotesTemp("this is Title","this is Massage!"));
        notes.add(getNotesTemp("this is Title","this is Massage!"));



        myAdapter adapter = new myAdapter(this,notes);
        myRecView.setAdapter(adapter);
        myRecView.setLayoutManager(new LinearLayoutManager(this));
    }


    public void ShowToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}//Main