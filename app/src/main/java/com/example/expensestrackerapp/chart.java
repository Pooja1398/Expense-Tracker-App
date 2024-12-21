package com.example.expensestrackerapp;

import static android.R.color.black;
import static android.R.color.darker_gray;
import static android.R.color.holo_blue_bright;
import static android.R.color.holo_blue_light;
import static android.R.color.holo_green_dark;
import static android.R.color.holo_orange_dark;
import static android.R.color.holo_red_dark;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collection;

public class chart extends AppCompatActivity {

    private ArrayList<Object> records = new ArrayList<>();
    private Object ColorTemplate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chart);

        PieChart pieChart=(PieChart) findViewById(R.id.pieChart);

        ArrayList<PieEntry> records=new ArrayList<>();
        records.add(new PieEntry(32, "Food"));
        records.add(new PieEntry(50, "Transport"));
        records.add(new PieEntry(100, "Shopping"));
        records.add(new PieEntry(600, "trip"));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(holo_red_dark));
        colors.add(getResources().getColor(holo_green_dark));
        colors.add(getResources().getColor(holo_orange_dark));
        colors.add(getResources().getColor(black));

        PieDataSet dataset = new PieDataSet(records, "Expenses");
        dataset.setColors(colors); // Set multiple colors for the dataset


        PieData pieData=new PieData(dataset);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(true);
        pieChart.setCenterText("Expenses");
        pieChart.animate();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom);
            return insets;
        });
    }
}
