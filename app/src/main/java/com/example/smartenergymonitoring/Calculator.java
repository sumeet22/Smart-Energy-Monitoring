package com.example.smartenergymonitoring;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Calculator extends AppCompatActivity implements View.OnClickListener {
    Spinner country, appliances, wattOrKwh, currency;
    EditText edtPowerConsumption;
    EditText edtHours;
    EditText edtKwhPerHour;
    EditText edtCostPerMonth, edtCostPerDay, edtCostPerYear;
    Button calculate, reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        initialize();


    }


    public void toastMsg(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    public void initialize() {
        country = findViewById(R.id.country);
        appliances = findViewById(R.id.appliances);
        wattOrKwh = findViewById(R.id.wattOrKwh);
        currency = findViewById(R.id.currency);
        edtPowerConsumption = findViewById(R.id.edtPowerConsumption);
        edtHours = findViewById(R.id.edtHours);
        edtKwhPerHour = findViewById(R.id.edtKwhPerHour);
        edtCostPerMonth = findViewById(R.id.edtCostPerMonth);
        edtCostPerDay = findViewById(R.id.edtCostPerDay);
        edtCostPerYear = findViewById(R.id.edtCostPerYear);
        calculate = findViewById(R.id.calculate);
        calculate.setOnClickListener(this);
        reset = findViewById(R.id.reset);
        reset.setOnClickListener(this);
        countryData();
        appliancesData();
        wattOrKwhData();
        currencyData();
    }

    public void countryData() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Singapore");
        arrayList.add("Australia");
        arrayList.add("Canada");
        arrayList.add("France");
        arrayList.add("Germany");
        arrayList.add("India");
        arrayList.add("Philippines");
        arrayList.add("Singapore");
        arrayList.add("United Kingdom");
        arrayList.add("United States");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(arrayAdapter);
        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String tutorialsName = parent.getItemAtPosition(position).toString();
//                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void appliancesData() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("--Select--");
        arrayList.add("Fridge");
        arrayList.add("Lights");
        arrayList.add("Fan");
        arrayList.add("Air Conditioner");
        arrayList.add("Rice Cooker");
        arrayList.add("Microwave");
        arrayList.add("Television");
        arrayList.add("Computer");
        arrayList.add("Water Heater");
        arrayList.add("Hair Dryer");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        appliances.setAdapter(arrayAdapter);
        appliances.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String tutorialsName = parent.getItemAtPosition(position).toString();
                switch (position) {
                    case 1: {
                        edtPowerConsumption.setText("250");
                    }
                    break;
                    case 2: {
                        edtPowerConsumption.setText("20");
                    }
                    break;
                    case 3: {
                        edtPowerConsumption.setText("75");
                    }
                    break;
                    case 4: {
                        edtPowerConsumption.setText("3000");
                    }
                    break;
                    case 5: {
                        edtPowerConsumption.setText("500");
                    }
                    break;
                    case 6: {
                        edtPowerConsumption.setText("600");
                    }
                    break;
                    case 7: {
                        edtPowerConsumption.setText("140");
                    }
                    break;
                    case 8: {
                        edtPowerConsumption.setText("200");
                    }
                    break;
                    case 9: {
                        edtPowerConsumption.setText("3500");
                    }
                    break;
                    case 10: {
                        edtPowerConsumption.setText("800");
                    }
                    break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void wattOrKwhData() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("watts (W)");
        arrayList.add("kilowatts (kW)");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wattOrKwh.setAdapter(arrayAdapter);
        wattOrKwh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String tutorialsName = parent.getItemAtPosition(position).toString();
//                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void currencyData() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("cent");
        arrayList.add("pence");
        arrayList.add("rupee");
        arrayList.add("peso");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currency.setAdapter(arrayAdapter);
        currency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String tutorialsName = parent.getItemAtPosition(position).toString();
//                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    public double OnEnergyCalc(double wtOrKwh, double powerConsumtion, double hourPerDay)         //on changing watt to kilowatt
    {
        double kwh = 0;
        // i2 = 0 	//watt/kilowatts:0/1
        double p = powerConsumtion;     //power consumtion 100
        double h = hourPerDay;        //hours per day   8
        if (p == 0 || h == 0) {
            return 0;
        }
        ;
        if (wtOrKwh == 0) {
            p /= 1000;
            kwh = p * h;
            //     kwh = roundnum(kwh, 5);
            return kwh;
        }
        kwh = p * h;
        return kwh;
    }

    private static DecimalFormat df = new DecimalFormat("0.0000");

    public void OnCalcCost(double powerConsumtion, String wtOrKwh, double hourPerDay, double energyCunsumerPerDay, double kwtCost, double currencyindex) {
        double i3 = currencyindex;      //currency
        double kwh_cost = kwtCost;   //1 kilowatt-hour (kWh) cost:
        double kwh = 0;
        // kwh = energyCunsumerPerDay         //Energy consumed per day:
        if (wtOrKwh.equals("watts (W)")) {
            kwh = OnEnergyCalc(0, powerConsumtion, hourPerDay);
        } else {
            kwh = OnEnergyCalc(1, powerConsumtion, hourPerDay);
        }
        if (i3 > 1) {
            kwh_cost *= 100;
        }
        double day_cost = kwh * kwh_cost / 100;
        double month_cost = day_cost * 30;
        double year_cost = day_cost * 365;
        edtCostPerDay.setText("$" + df.format(day_cost));
        edtCostPerMonth.setText("$" + df.format(month_cost));
        edtCostPerYear.setText("$" + df.format(year_cost));

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calculate:
                if (validation()) {
                    toastMsg("Validated");
                    double power = Double.parseDouble(edtPowerConsumption.getText().toString());
                    String wtorkwh = wattOrKwh.getSelectedItem().toString();
                    double hr = Double.parseDouble(edtHours.getText().toString());
                    double kwperhour = Double.parseDouble(edtKwhPerHour.getText().toString());
                    double innercurrency = currency.getSelectedItemPosition();
                    OnCalcCost(power, wtorkwh, hr, 0, kwperhour, innercurrency);

                }
                break;
            case R.id.reset:
                countryData();
                appliancesData();
                wattOrKwhData();
                currencyData();
                edtPowerConsumption.setText("");
                edtHours.setText("");
                edtKwhPerHour.setText("");
                edtCostPerDay.setText("");
                edtCostPerMonth.setText("");
                edtCostPerYear.setText("");
                break;

        }
    }

    public boolean validation() {
        if (appliances.getSelectedItem().toString().equals("--Select--")) {
            toastMsg("Select Appliances");

            return false;
        } else if (edtPowerConsumption.getText().toString().equals("")) {
            toastMsg("Enter Power Consumtion");
            return false;
        } else if (edtHours.getText().toString().equals("")) {
            toastMsg("Enter Hours");
            return false;
        } else if (edtKwhPerHour.getText().toString().equals("")) {
            toastMsg("Enter kilowatt-hour (kWh)");
            return false;
        } else {
//            toastMsg(appliances);
            return true;
        }
    }
}