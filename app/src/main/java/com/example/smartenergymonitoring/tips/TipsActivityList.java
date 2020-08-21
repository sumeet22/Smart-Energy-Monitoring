package com.example.smartenergymonitoring.tips;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.smartenergymonitoring.R;
import com.example.smartenergymonitoring.models.Tips;

import java.util.ArrayList;

public class TipsActivityList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Tips & Tricks");
        final ListView list = findViewById(R.id.list);
        ArrayList<Tips> arrayList = new ArrayList<Tips>();
        arrayList.add(new Tips("1. Turn off unnecessary lights", "", "https://www.bchydro.com/content/dam/BCHydro/customer-portal/photographs/people/public/woman-turning-off-light-people.jpg.thumb.90.140.png", "Two 100-watt incandescent bulbs switched off an extra two hours per day could save you $15 over a year. Better yet, switch to LED."));
        arrayList.add(new Tips("2. Use natural light", "", "https://www.bchydro.com/content/dam/BCHydro/customer-portal/photographs/places/building-interior/daylight-empty-office-full-width-place.jpg", "A single south-facing window can illuminate 20 to 100 times its area. Turning off one 60-watt bulb for four hours a day is a $9 saving over a year."));
        arrayList.add(new Tips("3. Use task lighting", "", "https://www.bchydro.com/content/dam/BCHydro/customer-portal/photographs/people/public/woman-and-boy-homework-task-lighting-people.jpg.thumb.90.140.png", "Turn off ceiling lights and use table lamps, track lighting and under-counter lights in work and hobby areas as well as in kitchens."));
        arrayList.add(new Tips("4. Take shorter showers", "", "https://www.bchydro.com/content/dam/BCHydro/customer-portal/photographs/objects-products/electronics/hand-turning-off-shower-product.jpg.thumb.90.140.png", "Hot water is expensive. If two people in your home cut their shower time by a minute each, you could save $30 over a year."));
        arrayList.add(new Tips("5. Turn water off when shaving, washing hands, brushing teeth", "", "https://www.bchydro.com/content/dam/BCHydro/customer-portal/photographs/objects-products/electronics/hand-turning-off-shower-product.jpg.thumb.90.140.png", "Reduce your hot water usage by 5% to save about $19."));
        arrayList.add(new Tips("6. Fix that leaky faucet", "", "https://www.bchydro.com/content/dam/BCHydro/customer-portal/photographs/objects-products/non-electric-and-water-fixtures/man-hands-shaving-cream-product.jpg.thumb.90.140.png", "Fixing a hot water leak in your faucet can save up to $9 per year in energy costs."));
        arrayList.add(new Tips("7. Unplug unused electronics", "", "https://www.bchydro.com/content/dam/BCHydro/customer-portal/photographs/objects-products/misc-household-items/man-unplugging-plug-product.jpg.thumb.90.140.png", "Standby power can account for 10% of an average household's annual electricity use. Unplug unused electronics and save $50 a year."));
        arrayList.add(new Tips("8. Ditch the desktop computer", "", "https://www.bchydro.com/content/dam/BCHydro/customer-portal/photographs/people/public/man-checking-myhydro-profile-laptop-people.jpg.thumb.90.140.png", "If you're still using that old desktop, recycle it and switch to your laptop. If you use your laptop two hours per day, you'll save $4 over a year."));
        arrayList.add(new Tips("9. Not home? Turn off the air conditioner", "", "https://www.bchydro.com/content/dam/BCHydro/customer-portal/photographs/objects-products/heating-cooling/window-unit-air-conditioner-140-90.jpg", "Turn off that old window unit air conditioner for five hours a day while you're away. Do that for 60 days over a summer and you'll save $16."));
        arrayList.add(new Tips("10. Recycle or donate that old TV", "", "https://www.bchydro.com/content/dam/BCHydro/customer-portal/photographs/people/public/old-television-product.jpg.thumb.90.140.png", " Recycle or donate your old T.V.. Even if you're just using it an hour a day, that 42-inch LCD is costing you six bucks a year."));
        arrayList.add(new Tips("11. Manage your thermostat", "", "https://www.bchydro.com/content/dam/BCHydro/customer-portal/photographs/objects-products/meters-plugs-thermostats/turn-down-thermostat-21-finger-product.jpg.thumb.90.140.png", "If you have electric heat, lower your thermostat by two degrees to save 5% on your heating bill. Lowering it five degrees could save 10%.  "));
        arrayList.add(new Tips("12. Be strategic with window coverings", "", "https://www.bchydro.com/content/dam/BCHydro/customer-portal/photographs/objects-products/misc-household-items/closing-window-blinds-full-width-object.jpg", "Promote airflow through your home and block the afternoon sun. You could save you up to $10 (2 fans) or $45 (1 window unit AC) during the summer."));
        arrayList.add(new Tips("13. Reduce heat in the kitchen", "", "https://www.bchydro.com/content/dam/BCHydro/customer-portal/photographs/people/public/man-bbq-people-full-width.jpg", "Avoid using the oven in summer – try salads, smoothies or barbecue. You'll reduce the heat in your home and save on your home cooling costs."));
        arrayList.add(new Tips("14. Run full loads", "", "https://www.bchydro.com/content/dam/BCHydro/customer-portal/photographs/people/public/girl-clothes-washing-people.jpg.thumb.90.140.png", "Cut one load of wash per week, even if you're already using cold water only, and you could save $18 a year on your laundry costs."));
        arrayList.add(new Tips("15. Wash laundry in cold", "", "https://www.bchydro.com/content/dam/BCHydro/customer-portal/photographs/people/public/woman-laundry-dryer-front-load-people.jpg.thumb.90.140.png", "By switching from hot to cold water for an average of three loads per week, you could save up to $22 per year on your energy bill."));


        TipsAdapter tipsAdapter = new TipsAdapter(this, arrayList);
        list.setAdapter(tipsAdapter);
    }
}