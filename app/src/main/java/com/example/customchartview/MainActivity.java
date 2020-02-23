package com.example.customchartview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ChartView chartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chartView = findViewById(R.id.chart);
//        chartView.setGraphTitle("绘图实战之直方图");
        chartView.setDivideAxisCountX(8);
        chartView.setDivideAxisCountY(10);
        chartView.setMaxAxisValueY(100);
        chartView.setMaxAxisValueX(8);//没用到maxvalueX
        int[][] columnInfo=new int[][]{
                {6, Color.BLUE},
                {5, Color.GREEN},
                {9, Color.RED},
                {2, Color.GRAY},
                {1, Color.MAGENTA},
                {3, Color.CYAN},
                {8, Color.YELLOW},
        };
        chartView.setColumnInfo(columnInfo);
    }
}
