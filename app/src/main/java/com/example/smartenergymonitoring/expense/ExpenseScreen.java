package com.example.smartenergymonitoring.expense;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartenergymonitoring.Calculator;
import com.example.smartenergymonitoring.MainActivity;
import com.example.smartenergymonitoring.R;
import com.example.smartenergymonitoring.appliances.AppliancesList;
import com.example.smartenergymonitoring.database.SQLHelper;
import com.example.smartenergymonitoring.models.ExpenseModel;
import com.example.smartenergymonitoring.tips.TipsActivityList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ExpenseScreen extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton flatButton;
    TextView txtNotFound;
    ArrayList<ExpenseModel> expenseList = new ArrayList<>();
    SQLHelper myDb;
    public static boolean isRefresh = false;
    ExpenseItemAdapter expenseItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_screen);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Monthly Bill");
        txtNotFound = findViewById(R.id.txtNotFound);
        recyclerView = findViewById(R.id.recyclerView);
        flatButton = findViewById(R.id.fab);
        myDb = new SQLHelper(this);

        expenseList = myDb.getAllExpenseData();
        if (expenseList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            txtNotFound.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            txtNotFound.setVisibility(View.GONE);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            expenseItemAdapter = new ExpenseItemAdapter(ExpenseScreen.this, expenseList);
            recyclerView.setAdapter(expenseItemAdapter);
            expenseItemAdapter.notifyDataSetChanged();
        }
        flatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExpenseScreen.this, AddExpenseScreen.class);
                intent.putExtra("mode", "add");
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isRefresh) {
            recyclerView.setVisibility(View.VISIBLE);
            txtNotFound.setVisibility(View.GONE);
            expenseList = myDb.getAllExpenseData();
            expenseItemAdapter = new ExpenseItemAdapter(ExpenseScreen.this, expenseList);
            recyclerView.setAdapter(expenseItemAdapter);
            expenseItemAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.expense_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_barChart) {
            if (!expenseList.isEmpty()) {
                Intent intent = new Intent(ExpenseScreen.this, BarScreen.class);
                intent.putExtra("list", expenseList);
                startActivity(intent);
            } else {
                showToastMessage("You Don't have any expense record!");
            }

        } else if (id == R.id.nav_calculator) {
            startActivity(new Intent(ExpenseScreen.this, Calculator.class));

        }else if (id == R.id.nav_tips) {
            startActivity(new Intent(ExpenseScreen.this, TipsActivityList.class));

        }
        else if (id == R.id.nav_appliances) {
            startActivity(new Intent(ExpenseScreen.this, AppliancesList.class));

        }
        else if (id == R.id.weburl) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nea.gov.sg/our-services/climate-change-energy-efficiency/energy-efficiency/household-sector/the-energy-label"));
            startActivity(browserIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    //show toaster message................................................................
    public void showToastMessage(final String message) {
        ExpenseScreen.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ExpenseScreen.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}