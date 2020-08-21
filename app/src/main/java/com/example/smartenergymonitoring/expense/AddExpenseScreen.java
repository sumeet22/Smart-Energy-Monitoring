package com.example.smartenergymonitoring.expense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartenergymonitoring.R;
import com.example.smartenergymonitoring.database.SQLHelper;
import com.example.smartenergymonitoring.models.ExpenseModel;
import com.example.smartenergymonitoring.util.DateUtil;

public class AddExpenseScreen extends AppCompatActivity implements View.OnClickListener {

    EditText edtTitle,edtDesc,edtAmount;
    Button btnAdd;
    String mode,currentTimeStamp;
    SQLHelper myDb;
    ExpenseModel _expenseModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense_screen);
        myDb = new SQLHelper(this);
        currentTimeStamp = DateUtil.getCurrentTimeStamp();
        edtTitle = findViewById(R.id.edtTitle);
        edtDesc = findViewById(R.id.edtDesc);
        edtAmount = findViewById(R.id.edtAmount);
        btnAdd = findViewById(R.id.btnAdd);
        //get data from intent...............................
        if(getIntent().getExtras() != null){
            mode = getIntent().getStringExtra("mode");
        }
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //for add mode.......................................
        if(mode.equals("add")){
            getSupportActionBar().setTitle("Create Monthly Bill");
            btnAdd.setText("Add");
        }else {
            //for edit mode....................................
            getSupportActionBar().setTitle("Edit Monthly Bill");
            btnAdd.setText("Update");
            _expenseModel = (ExpenseModel) getIntent().getSerializableExtra("model");
            if(_expenseModel != null){
                edtTitle.setText(_expenseModel.getTitle());
                edtDesc.setText(_expenseModel.getDesc());
                edtAmount.setText(_expenseModel.getAmount());
            }
        }
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnAdd){
            if(validate(edtTitle.getText().toString().trim(),edtDesc.getText().toString().trim(),edtAmount.getText().toString().trim())){
                ExpenseModel expenseModel = new ExpenseModel();
                expenseModel.setTitle(edtTitle.getText().toString().trim());
                expenseModel.setDesc(edtDesc.getText().toString().trim());
                expenseModel.setAmount(edtAmount.getText().toString().trim());
                expenseModel.setTimeStamp(currentTimeStamp);
                boolean result = false;
                //for add mode........................................
                if(mode.equals("add")){
                    hideKeyboard();
                   result = myDb.insertExpenseData(expenseModel);
                   if (result){
                       ExpenseScreen.isRefresh = true;
                      showToastMessage("Expense Added Successfully.");
                      finish();
                   }
                }else {
                    hideKeyboard();
                    //for edit mode....................................
                   result = myDb.updateExpenseData(_expenseModel.getId(),expenseModel);
                    if (result){
                        ExpenseScreen.isRefresh = true;
                        showToastMessage("Expense Updated Successfully.");
                        finish();
                    }
                }

            }
        }
    }

    public boolean validate(String title,String desc,String amount){
        boolean result = true;
        if(title.equals("null") || title.equals("")){
            showToastMessage("Please enter title.");
            result = false;
        }else if(desc.equals("null") || desc.equals("")){
            showToastMessage("Please enter Description.");
            result = false;
        }else if(amount.equals("null") || amount.equals("")){
            showToastMessage("Please enter Amount.");
            result = false;
        }
        return result;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //show toaster message................................................................
    public void showToastMessage(final String message){
        AddExpenseScreen.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(AddExpenseScreen.this,message,Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void hideKeyboard() {
        try {
            InputMethodManager inputmanager = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputmanager != null) {
                inputmanager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception var2) {
        }

    }

}