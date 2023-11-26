package com.sumonkmr.detailskeeper;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    TextView textViewName, textViewEmail, textViewPhone, textViewAddress;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
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
        GetValue();


    }//onCreate

    private void HookUps() {
        textViewName = findViewById(R.id.textViewName);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewPhone = findViewById(R.id.textViewPhone);
        textViewAddress = findViewById(R.id.textViewAddress);
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
                showAlertDialog();
            } else if (item.getItemId() == R.id.menu_item2) {
                // Handle item 2 click
                ShowToast("This is Item 2");
            } else {
                // Add more cases as needed
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


    private void showAlertDialog() {
        // Get the alert dialog view
        LayoutInflater inflater = getLayoutInflater();
        View alertDialogView = inflater.inflate(R.layout.alert_dialog, null);

        // Get the dialog title and message
        name = alertDialogView.findViewById(R.id.name);
        email = alertDialogView.findViewById(R.id.email);
        phone = alertDialogView.findViewById(R.id.mobile);
        address = alertDialogView.findViewById(R.id.address);
        Button submit = alertDialogView.findViewById(R.id.submit);
        Button cancel = alertDialogView.findViewById(R.id.cencel);

        // Create the alert dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Set the alert dialog view and other configurations
        builder.setView(alertDialogView);
        // Create and show the alert dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        cancel.setOnClickListener(v -> {
            alertDialog.dismiss();
        });
        submit.setOnClickListener(v -> {
            editor.putString("name", name.getText().toString());
            editor.putString("email", email.getText().toString());
            editor.putString("phone", phone.getText().toString());
            editor.putString("address", address.getText().toString());
            editor.apply();
            alertDialog.dismiss();
        });
    }

    private void GetValue(){
        String nameString = sharedPreferences.getString("name", "Nothing found!");
        String emailString = sharedPreferences.getString("email", "Nothing found!");
        String phoneString = sharedPreferences.getString("phone", "Nothing found!");
        String addressString = sharedPreferences.getString("address", "Nothing found!");
        textViewName.setText(nameString);
        textViewEmail.setText(emailString);
        textViewPhone.setText(phoneString);
        textViewAddress.setText(addressString);
    }

    public void ShowToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}//Main