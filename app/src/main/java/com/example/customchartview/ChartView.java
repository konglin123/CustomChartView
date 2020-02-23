package com.example.customchartview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

public class ChartView extends BaseView {
    public ChartView(Context context) {
        super(context);
    }

    public ChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void drawColumn(Canvas canvas, Paint paint) {
       if(columnInfo!=null){
           int cellwidth=width/divideAxisCountX;
           for (int i = 0; i < columnInfo.length; i++) {
               paint.setColor(columnInfo[i][1]);
               int leftTopY=originY-height*(columnInfo[i][0])/divideAxisCountY;
               canvas.drawRect(originX+cellwidth*(i+1),leftTopY,originX+cellwidth*(i+2),originY,paint);
           }
       }
    }

    @Override
    protected void drawYAxisScaleValue(Canvas canvas, Paint paint) {
         int cellheight=height/divideAxisCountY;
         int cellvalue=maxAxisValueY/divideAxisCountY;
        for (int i = 1; i < divideAxisCountY; i++) {
            canvas.drawText(cellvalue*i+"",originX-35,originY-cellheight*i,paint);
        }
    }

    @Override
    protected void drawXAxisScaleValue(Canvas canvas, Paint paint) {

        int cellwidth=width/divideAxisCountX;
        for (int i = 0; i < divideAxisCountX; i++) {
            canvas.drawText(i+"",cellwidth*i+originX-35,originY+30,paint);
        }

    }

    @Override
    protected void drawYAxisScale(Canvas canvas, Paint paint) {
         int cellheight=height/divideAxisCountY;
        for (int i = 0; i < divideAxisCountY; i++) {
            canvas.drawLine(originX,originY-(i+1)*cellheight,originX+10,originY-(i+1)*cellheight,paint);
        }

    }

    @Override
    protected void drawXAxisScale(Canvas canvas, Paint paint) {

           int cellwidth=width/divideAxisCountX;
        for (int i = 0; i < divideAxisCountX; i++) {
            canvas.drawLine(originX+(i+1)*cellwidth,originY,originX+(i+1)*cellwidth,originY-10,paint);
        }
    }

    @Override
    protected void drawYAxis(Canvas canvas, Paint paint) {
    canvas.drawLine(originX,originY,originX,originY-height,paint);
    }

    @Override
    protected void drawXAxis(Canvas canvas, Paint paint) {
        paint.setStrokeWidth(5);
        canvas.drawLine(originX,originY,originX+width,originY,paint);
    }

}
