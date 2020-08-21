package com.example.smartenergymonitoring;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.example.smartenergymonitoring.appliances.AppliancesList;
import com.example.smartenergymonitoring.expense.ExpenseScreen;
import com.example.smartenergymonitoring.tips.TipsActivityList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Intent i=new Intent(getApplicationContext(), TipsActivityList.class);
        startActivity(new Intent(MainActivity.this, ExpenseScreen.class));

    }
}