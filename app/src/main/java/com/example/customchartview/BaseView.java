package com.example.customchartview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public abstract class BaseView extends View {
    protected String graphTitle;
    protected String xAxisName;
    protected String yAxisName;
    protected int textSize=20;
    protected int textColor= Color.BLACK;
    private Paint paint;

    private Context mContext;
    //图表的宽高
    protected int width;
    protected int height;
    //图表的起始值//现在最小手机都是480x800,这里只是给默认值，具体根据手机的宽高来
    protected int originX=100;
    protected int originY=1000;
    //x轴最大值和分成几等份
    protected int maxAxisValueX;
    protected int divideAxisCountX=5;
    //y轴最大值和分成几等份
    protected int maxAxisValueY;
    protected int divideAxisCountY=5;

    //第一个维度为值，第二个维度为颜色
    protected int[][] columnInfo;

    public void setGraphTitle(String graphTitle) {
        this.graphTitle = graphTitle;
    }

    public void setxAxisName(String xAxisName) {
        this.xAxisName = xAxisName;
    }

    public void setyAxisName(String yAxisName) {
        this.yAxisName = yAxisName;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setMaxAxisValueX(int maxAxisValueX) {
        this.maxAxisValueX = maxAxisValueX;
    }

    public void setDivideAxisCountX(int divideAxisCountX) {
        this.divideAxisCountX = divideAxisCountX;
    }

    public void setMaxAxisValueY(int maxAxisValueY) {
        this.maxAxisValueY = maxAxisValueY;
    }

    public void setDivideAxisCountY(int divideAxisCountY) {
        this.divideAxisCountY = divideAxisCountY;
    }

    public void setColumnInfo(int[][] columnInfo) {
        this.columnInfo = columnInfo;
    }

    public BaseView(Context context) {
        this(context,null);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.ChartViewStyle);
        graphTitle=typedArray.getString(R.styleable.ChartViewStyle_graphTitle);
        xAxisName=typedArray.getString(R.styleable.ChartViewStyle_xAxisName);
        yAxisName=typedArray.getString(R.styleable.ChartViewStyle_yAxisName);
        textSize=typedArray.getDimensionPixelSize(R.styleable.ChartViewStyle_textSize,textSize);
        textColor=typedArray.getColor(R.styleable.ChartViewStyle_textColor,textColor);
        if(typedArray!=null){
            typedArray.recycle();
        }
        initPaint();
    }

    private void initPaint() {
        if(paint==null){
            paint=new Paint();
            paint.setDither(true);
            paint.setAntiAlias(true);
            paint.setTextSize(textSize);
            paint.setColor(textColor);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        width=getWidth()-originX-100;
        height=(originY>getHeight()?getHeight():originY)-300;
        drawGraphTitle(canvas);//图表底部标题
        drawXAxisArrow(canvas);//x轴箭头
        drawYAxisArrow(canvas);//y轴箭头
        drawXAxis(canvas,paint);
        drawYAxis(canvas,paint);
        drawXAxisScale(canvas,paint);
        drawYAxisScale(canvas,paint);
        drawXAxisScaleValue(canvas,paint);
        drawYAxisScaleValue(canvas,paint);
        drawColumn(canvas,paint);
    }

    protected abstract void drawColumn (Canvas canvas,Paint paint);

    protected abstract void drawYAxisScaleValue(Canvas canvas,Paint paint);

    protected abstract void drawXAxisScaleValue(Canvas canvas,Paint paint);

    protected abstract void drawYAxisScale(Canvas canvas,Paint paint);

    protected abstract void drawXAxisScale(Canvas canvas,Paint paint);

    protected abstract void drawYAxis(Canvas canvas,Paint paint);

    protected abstract void drawXAxis(Canvas canvas,Paint paint);

    private void drawYAxisArrow(Canvas canvas) {
        Path path = new Path();
        path.moveTo(originX,originY-height-30);
        path.lineTo(originX-10,originY-height);
        path.lineTo(originX+10,originY-height);
        path.close();
        canvas.drawPath(path,paint);
        if(!TextUtils.isEmpty(yAxisName)) {
            canvas.drawText(yAxisName, originX - 75, originY - height, paint);
        }
    }

    private void drawXAxisArrow(Canvas canvas) {
        Path path = new Path();
        path.moveTo(originX+width+30,originY);
        path.lineTo(originX+width,originY-10);
        path.lineTo(originX+width,originY+10);
        path.close();
        canvas.drawPath(path,paint);
        if(!TextUtils.isEmpty(xAxisName)){
            canvas.drawText(xAxisName,originX+width,originY+30,paint);
        }

    }

    private void drawGraphTitle(Canvas canvas) {
        if(!TextUtils.isEmpty(graphTitle)){
            paint.setFakeBoldText(true);
            canvas.drawText(graphTitle,getWidth()/2-paint.measureText(graphTitle)/2,originY+50,paint);
        }
    }
}
