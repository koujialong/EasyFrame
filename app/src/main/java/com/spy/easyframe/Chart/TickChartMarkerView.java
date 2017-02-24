package com.spy.easyframe.Chart;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.spy.easyframe.model.TickChartModel;
import com.spy.easyframe.util.TimeUtils;

/**
 * Created by Administrator on 2017/2/23.
 */
public class TickChartMarkerView implements IMarker {
    private String xValue,yLeftValue,yRightValue;
    private TickChartModel model;

    public TickChartMarkerView(TickChartModel model) {
        this.model = model;
    }

    @Override
    public MPPointF getOffset() {
        return null;
    }

    @Override
    public MPPointF getOffsetForDrawingAtPoint(float v, float v1) {
        return null;
    }

    @Override
    public void refreshContent(Entry entry, Highlight highlight) {
        int x=(int)entry.getX();
        xValue= TimeUtils.getTimeHHMM(Long.parseLong(model.getItem().getDatas().get(x).get(0)));
        yLeftValue=model.getItem().getDatas().get(x).get(1);
        yRightValue=model.getItem().getDatas().get(x).get(1);
    }

    @Override
    public void draw(Canvas canvas, float v, float v1) {
        //文字画笔
        Paint textPaint=new Paint();
        textPaint.setTextSize(30);
        textPaint.setColor(Color.WHITE);

        //lable标签画笔
        Paint lablePaint=new Paint();
        lablePaint.setColor(Color.BLACK);

        RectF rect;
        //x轴maker
        if (v-40>=90&&v-40<=canvas.getWidth()-170){
            rect = new RectF(v-40,50, v+40, 90);
            canvas.drawRoundRect(rect,8,8,lablePaint);
            canvas.drawText(xValue,v-40,80,textPaint);
        }else if (v-40>canvas.getWidth()-170){
            rect = new RectF(canvas.getWidth()-170,50, canvas.getWidth()-90, 90);
            canvas.drawRoundRect(rect,8,8,lablePaint);
            canvas.drawText(xValue,canvas.getWidth()-170,80,textPaint);
        }else{
            rect = new RectF(90,50, 170, 90);
            canvas.drawRoundRect(rect,8,8,lablePaint);
            canvas.drawText(xValue,90,80,textPaint);
        }

        //y轴左侧maker
        rect = new RectF(90,v1-20,170, v1+20);
        canvas.drawRoundRect(rect,8,8,lablePaint);
        canvas.drawText(yLeftValue,90,v1+12,textPaint);

        //y轴右侧maker
        rect = new RectF(canvas.getWidth()-170,v1-20,canvas.getWidth()-90, v1+20);
        canvas.drawRoundRect(rect,8,8,lablePaint);
        canvas.drawText(yRightValue,canvas.getWidth()-170,v1+12,textPaint);
    }
}
