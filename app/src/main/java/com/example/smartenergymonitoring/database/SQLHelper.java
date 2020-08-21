package com.example.smartenergymonitoring.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.smartenergymonitoring.models.ExpenseModel;

import java.util.ArrayList;

public class SQLHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Technician.db";
    public static final String EXPENSE_TABLE = "Expense";


    //User column name....................................
    private String COL_1 = "id";
    private String COL_2 = "title";
    private String COL_3 = "description";
    private String COL_4 = "amount";
    private String COL_5 = "timeStamp";


    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ EXPENSE_TABLE +"(id INTEGER PRIMARY KEY AUTOINCREMENT ,title TEXT,description TEXT ,amount TEXT,timeStamp TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    //insert into Expense table.................................................................
    public boolean insertExpenseData(ExpenseModel expenseModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,expenseModel.getTitle());
        contentValues.put(COL_3,expenseModel.getDesc());
        contentValues.put(COL_4,expenseModel.getAmount());
        contentValues.put(COL_5,expenseModel.getTimeStamp());

        long result = db.insert(EXPENSE_TABLE,null,contentValues);
        db.close();

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    //get all product data.............................................................................
    public ArrayList<ExpenseModel> getAllExpenseData(){
        ArrayList<ExpenseModel> data=new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+EXPENSE_TABLE,null);
        StringBuffer stringBuffer = new StringBuffer();
        ExpenseModel expenseModel = null;

        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
            String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
            String amount = cursor.getString(cursor.getColumnIndexOrThrow("amount"));
            String timeStamp = cursor.getString(cursor.getColumnIndexOrThrow("timeStamp"));
            expenseModel= new ExpenseModel(id,title,description,amount,timeStamp);

            stringBuffer.append(expenseModel);
            data.add(expenseModel);
        }
        return data;
    }


    //update product table after borrowed.................................................................
    public boolean updateExpenseData(String id,ExpenseModel expenseModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,expenseModel.getTitle());
        contentValues.put(COL_3,expenseModel.getDesc());
        contentValues.put(COL_4,expenseModel.getAmount());
        contentValues.put(COL_5,expenseModel.getTimeStamp());

        long result = db.update(EXPENSE_TABLE,contentValues,COL_1+" =?",new String[]{id});
        db.close();

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }
    //delete borrowed record from borrow table...............................................................
    public void deleteExpenseData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ EXPENSE_TABLE+" where "+COL_1+" =?", new String[]{id});
    }
}
