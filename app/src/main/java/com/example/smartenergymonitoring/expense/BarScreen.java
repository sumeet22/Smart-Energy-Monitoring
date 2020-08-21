package com.example.smartenergymonitoring.expense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.example.smartenergymonitoring.R;
import com.example.smartenergymonitoring.models.ExpenseModel;
import com.example.smartenergymonitoring.util.DateUtil;
import com.whiteelephant.monthpicker.MonthPickerDialog;

import java.util.ArrayList;
import java.util.List;


public class BarScreen extends AppCompatActivity {

    ArrayList<ExpenseModel> expenseModelArrayList = new ArrayList<>();
    List<DataEntry> barChartModelList = new ArrayList<>();
    //    BarChart barChart;
    AnyChartView anyChartView;
    Cartesian cartesian;
    TextView txtNotFound;
    String selectedMonthString = "", currentDate = "", currentMonth = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_screen);
        currentDate = DateUtil.getCurrentDate();
        if (getIntent().getExtras() != null) {
            expenseModelArrayList = (ArrayList<ExpenseModel>) getIntent().getSerializableExtra("list");
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (currentDate != null && !currentDate.equals("")) {
            currentMonth = DateUtil.getMonthFromDate(currentDate);
        }
        anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        cartesian = AnyChart.column();
//        barChart = (BarChart) findViewById(R.id.barChart);
        txtNotFound = findViewById(R.id.txtNotFound);
//        barChart.setBarMaxValue(10000);
        for (int i = 0; i < expenseModelArrayList.size(); i++) {
            String month = DateUtil.getMonthFromDate(expenseModelArrayList.get(i).getTimeStamp());
//            if (month != null) {
//                if (currentMonth.equals(month)) {
                    barChartModelList.add(new ValueDataEntry(expenseModelArrayList.get(i).getTitle(), Integer.parseInt(expenseModelArrayList.get(i).getAmount())));
//                }
//            }
        }
        Column column = cartesian.column((List<DataEntry>) barChartModelList);

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("${%Value}{groupsSeparator: }");

        cartesian.animation(true);
        cartesian.title("Expense for month");

        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("${%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Expense");
        cartesian.yAxis(0).title("Expense Values");

        anyChartView.setChart(cartesian);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.bar_filter_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_filter) {
            int month = Integer.parseInt(currentMonth) - 1;
            Log.e("picker current month :", "" + month);
            MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(BarScreen.this, new MonthPickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(int selectedMonth, int selectedYear) {
                    int selected = selectedMonth + 1;
                    String monthString = "";
                    if (selected < 10) {
                        monthString = "0" + String.valueOf(selected);
                        selectedMonthString = monthString;
                    } else {
                        monthString = String.valueOf(selected);
                        selectedMonthString = monthString;
                    }
                    barChartModelList.clear();
//                    barChart.clearAll();
                    for (int i = 0; i < expenseModelArrayList.size(); i++) {
                        String month = DateUtil.getMonthFromDate(expenseModelArrayList.get(i).getTimeStamp());
                        Log.e("db month :", "" + month);
//                        if (month != null) {
//                            if (month.equals(selectedMonthString)) {
                                barChartModelList.add(new ValueDataEntry(expenseModelArrayList.get(i).getTitle(), Integer.parseInt(expenseModelArrayList.get(i).getAmount())));
//                             }
//                        }
                    }
                    if (barChartModelList.isEmpty()) {

//                        barChart.setVisibility(View.GONE);
                        anyChartView.setVisibility(View.GONE);
                        txtNotFound.setVisibility(View.VISIBLE);
                    } else {
//                        barChart.setVisibility(View.VISIBLE);
                        anyChartView.setVisibility(View.VISIBLE);

                        txtNotFound.setVisibility(View.GONE);
//                        barChart.addBar(barChartModelList);
                        Column column = cartesian.column((List<DataEntry>) barChartModelList);

                        column.tooltip()
                                .titleFormat("{%X}")
                                .position(Position.CENTER_BOTTOM)
                                .anchor(Anchor.CENTER_BOTTOM)
                                .offsetX(0d)
                                .offsetY(5d)
                                .format("${%Value}{groupsSeparator: }");

                        cartesian.animation(true);
                        cartesian.title("Expense for month");

                        cartesian.yScale().minimum(0d);

                        cartesian.yAxis(0).labels().format("${%Value}{groupsSeparator: }");

                        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
                        cartesian.interactivity().hoverMode(HoverMode.BY_X);

                        cartesian.xAxis(0).title("Expense");
                        cartesian.yAxis(0).title("Expense Values");

                        anyChartView.setChart(cartesian);
                    }


                    Log.e("selected month :", "" + monthString);
                }
            }, /* activated number in year */ 3, month);

            builder.showMonthOnly()
                    .build()
                    .show();
        }
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}