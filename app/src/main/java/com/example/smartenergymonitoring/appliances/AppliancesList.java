package com.example.smartenergymonitoring.appliances;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.smartenergymonitoring.R;
import com.example.smartenergymonitoring.models.SubjectData;

import java.util.ArrayList;

public class AppliancesList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliances_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Appliances & their Consumtions");
        final ListView list = findViewById(R.id.list);
        ArrayList<SubjectData> arrayList = new ArrayList<SubjectData>();
        arrayList.add(new SubjectData("AC", "", R.drawable.ac3000,"3000"));
        arrayList.add(new SubjectData("Computer", "", R.drawable.computer200,"200"));
        arrayList.add(new SubjectData("Fan", "", R.drawable.fan75,"75"));
        arrayList.add(new SubjectData("Fridge", "", R.drawable.fridge250,"250"));
        arrayList.add(new SubjectData("Hair Dryer", "", R.drawable.hairdryer800,"800"));
        arrayList.add(new SubjectData("Light", "", R.drawable.light20,"20"));
        arrayList.add(new SubjectData("Microwave", "", R.drawable.microwave600,"600"));
        arrayList.add(new SubjectData("Rice Cooker", "", R.drawable.ricecooker500,"500"));
        arrayList.add(new SubjectData("TV", "", R.drawable.tv140,"140"));
        arrayList.add(new SubjectData("Water Heater", "", R.drawable.waterheater3500,"3500"));

        CustomAdapter customAdapter = new CustomAdapter(this, arrayList);
        list.setAdapter(customAdapter);
    }
}