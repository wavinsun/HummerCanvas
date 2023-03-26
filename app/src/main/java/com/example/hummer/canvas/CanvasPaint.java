package com.example.hummer.canvas;

import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;

/**
 * 画笔
 */
public class CanvasPaint {

    // 画线画笔
    private Paint linePaint = new Paint();
    // 填充画笔
    private Paint fillPaint = new Paint();
    // 文本画笔
    private TextPaint textPaint = new TextPaint();
    // 清空画笔
    private Paint clearPaint = new Paint();

    public CanvasPaint() {
        fillPaint.setAntiAlias(true);
        linePaint.setAntiAlias(true);
        textPaint.setAntiAlias(true);
        clearPaint.setAntiAlias(true);
    }

    /**
     * 获取清空画笔
     */
    public Paint getClearPaint() {
        return clearPaint;
    }

    /**
     * 获取画线画笔
     */
    public Paint getLinePaint() {
        return linePaint;
    }

    /**
     * 获取文本画笔
     */
    public TextPaint getTextPaint() {
        return textPaint;
    }

    /**
     * 获取填充画笔
     */
    public Paint getFillPaint() {
        return fillPaint;
    }

    /**
     * 设置线段宽度
     */
    public void lineWidth(float w) {
        linePaint.setStrokeWidth(w);
        textPaint.setStrokeWidth(w);
    }

    /**
     * 设置线段末端的属性。
     */
    public void lineCap(int cap) {
        switch (cap) {
            case 0:
                linePaint.setStrokeCap(Paint.Cap.BUTT);
                break;
            case 1:
                linePaint.setStrokeCap(Paint.Cap.ROUND);
                break;
            case 2:
                linePaint.setStrokeCap(Paint.Cap.SQUARE);
                break;
            default:
                linePaint.setStrokeCap(Paint.Cap.BUTT);
                break;
        }
    }

    /**
     * 设置线段连接方式
     */
    public void lineJoin(int join) {
        switch (join) {
            case 0:
                linePaint.setStrokeJoin(Paint.Join.MITER);
                break;
            case 1:
                linePaint.setStrokeJoin(Paint.Join.ROUND);
                break;
            case 2:
                linePaint.setStrokeJoin(Paint.Join.BEVEL);
                break;
            default:
                linePaint.setStrokeJoin(Paint.Join.MITER);
                break;
        }
    }

    /**
     * 设置线段颜色
     */
    public void lineColor(String color) {
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(Color.parseColor(color));
    }

    /**
     * 设置填充颜色
     */
    public void fillColor(String color) {
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setColor(Color.parseColor(color));
    }

    /**
     * 设置文本颜色
     */
    public void textColor(String color) {
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.parseColor(color));
    }

}
