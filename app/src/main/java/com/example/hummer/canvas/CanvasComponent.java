package com.example.hummer.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.didi.hummer.annotation.Component;
import com.didi.hummer.annotation.JsMethod;
import com.didi.hummer.annotation.JsProperty;
import com.didi.hummer.context.HummerContext;
import com.didi.hummer.core.engine.JSValue;
import com.didi.hummer.render.component.view.HMBase;
import com.didi.hummer.render.style.HummerStyleUtils;
import com.didi.hummer.render.utility.YogaResUtils;
import com.didi.hummer.utils.JsSourceUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Canvas组件
 */
@Component("Canvas")
public class CanvasComponent extends HMBase<CanvasTargetView> {
    private static final String TAG = "CanvasComponent";

    public CanvasComponent(HummerContext context, JSValue jsValue, String viewID) {
        super(context, jsValue, viewID);
    }

    @Override
    protected CanvasTargetView createViewInstance(Context context) {
        return new CanvasTargetView(context);
    }

    /**
     * 获取Hummer上下文
     */
    public HummerContext getHummerContext() {
        return (HummerContext) getContext();
    }

    /**
     * 获取绘图表面提供 2D 渲染上下文
     */
    @JsMethod("getContext")
    public JSValue getContext(String type) {
        if (!TextUtils.equals(type, "2d")) {
            Log.e(TAG, "get context with not 2d");
        }
        return this.getJSValue();
    }

    /**
     * 绘制矩形 实心
     */
    @JsMethod("fillRect")
    public void fillRect(Object x, Object y, Object width, Object height) {
        getView().fillRect(x, y, width, height);
    }

    /**
     * 绘制矩形 镂空
     */
    @JsMethod("strokeRect")
    public void strokeRect(Object x, Object y, Object width, Object height) {
        getView().strokeRect(x, y, width, height);
    }

    /**
     * 绘制圆形 实心
     */
    @JsMethod("fillCircle")
    public void fillCircle(Object x, Object y, Object radius) {
        getView().fillCircle(x, y, radius);
    }

    /**
     * 绘制圆形 镂空
     */
    @JsMethod("strokeCircle")
    public void strokeCircle(Object x, Object y, Object radius) {
        getView().strokeCircle(x, y, radius);
    }

    /**
     * 使用和 CSS font 规范相同的字符串值。
     */
    @JsProperty("font")
    private String font;

    /**
     * 设置字体
     */
    public void setFont(String font) {
        this.font = font;
        if (TextUtils.isEmpty(font)) {
            return;
        }
        float fontSize = 10;
        String[] fonts = font.split(" ");
        if (fonts != null) {
            for (String fontItem : fonts) {
                if (fontItem.endsWith("px")) {
                    try {
                        fontSize = Float.valueOf(fontItem.substring(0, fontItem.length() - 2));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        getView().fontSize(fontSize);
    }

    /**
     * Text 绘制改为左上角起始绘制 该方式和IOS 保持一致 且符合StaticLayout 绘制能力
     */
    @JsMethod("fillText")
    public void fillText(String text, Object x, Object y, Object maxWidth) {
        getView().fillText(text, x, y, maxWidth);
    }

    /**
     * Text 绘制改为左上角起始绘制 该方式和IOS 保持一致 且符合StaticLayout 绘制能力
     */
    @JsMethod("strokeText")
    public void strokeText(String text, Object x, Object y, Object maxWidth) {
        getView().strokeText(text, x, y, maxWidth);
    }

    /**
     * 绘制圆弧
     */
    @JsMethod("arc")
    public void arc(Object x, Object y, Object radius, Object startAngle, Object endAngle, Object clockwise) {
        getView().arc(x, y, radius, startAngle, endAngle, clockwise);
    }

    /**
     * 绘制直线
     */
    @JsMethod("drawLine")
    public void drawLine(Object startX, Object startY, Object stopX, Object stopY) {
        getView().drawLine(startX, startY, stopX, stopY);
    }

    /**
     * 绘制多条直线
     */
    @JsMethod("drawLines")
    public void drawLines(Object[] points) {
        getView().drawLines(points);
    }

    /**
     * 绘制路径集合
     */
    private Path path;

    /**
     * 通过清空子路径列表开始一个新路径的方法。当你想创建一个新的路径时，调用此方法。
     */
    @JsMethod("beginPath")
    public void beginPath() {
        path = new Path();
    }

    /**
     * 单位转换：dp转px
     */
    private float dp2px(Object v) {
        return HummerStyleUtils.convertNumber(v);
    }

    /**
     * 将一个新的子路径的起始点移动到 (x，y) 坐标的方法。
     */
    @JsMethod("moveTo")
    public void moveTo(Object x, Object y) {
        try {
            Log.i(TAG, "x:" + x);
            Log.i(TAG, "y:" + y);
            float x_px = dp2px(x);
            float y_px = dp2px(y);
            path.moveTo(x_px, y_px);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用直线连接子路径的终点到 x，y 坐标的方法（并不会真正地绘制）。
     */
    @JsMethod("lineTo")
    public void lineTo(Object x, Object y) {
        try {
            float x_px = dp2px(x);
            float y_px = dp2px(y);
            path.lineTo(x_px, y_px);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将笔点返回到当前子路径起始点的方法。
     * 它尝试从当前点到起始点绘制一条直线。
     * 如果图形已经是封闭的或者只有一个点，那么此方法不会做任何操作。
     */
    @JsMethod("closePath")
    public void closePath() {
        try {
            path.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用非零环绕规则，根据当前的画线样式，绘制当前或已经存在的路径的方法。
     */
    @JsMethod("stroke")
    public void stroke() {
        try {
            getView().drawPath(path);
            path = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据当前的填充样式，填充当前或已存在的路径的方法。采取非零环绕或者奇偶环绕规则。
     */
    @JsMethod("fill")
    public void fill() {
        try {
            getView().drawPath(path, getView().getCanvasPaint().getFillPaint());
            path = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建矩形路径的方法，矩形的起点位置是 (x, y)，尺寸为 width 和 height。
     * 矩形的 4 个点通过直线连接，子路径做为闭合的标记，所以你可以填充或者描边矩形。
     */
    @JsMethod("rect")
    public void rect(Object x, Object y, Object width, Object height) {
        if (path == null) {
            path = new Path();
        }
        try {
            float x_px = dp2px(x);
            float y_px = dp2px(y);
            float width_px = dp2px(width);
            float height_px = dp2px(height);
            RectF rect = new RectF(x_px, y_px, x_px + width_px, y_px + height_px);
            path.addRect(rect, Path.Direction.CW);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 这个方法通过把像素设置为透明以达到擦除一个矩形区域的目的。
     */
    @JsMethod("clearRect")
    public void clearRect(Object x, Object y, Object width, Object height) {
        getView().clearRect(x, y, width, height);
    }

    /**
     * 绘制椭圆
     */
    @JsMethod("strokeEllipse")
    public void ellipse(Object left, Object top, Object right, Object bottom) {
        getView().strokeEllipse(left, top, right, bottom);
    }

    /**
     * 填充方式绘制椭圆
     */
    @JsMethod("fillEllipse")
    public void fillEllipse(Object left, Object top, Object right, Object bottom) {
        getView().fillEllipse(left, top, right, bottom);
    }

    /**
     * 使用内部方式描述颜色和样式的属性。默认值是 #000 （黑色）。
     */
    @JsProperty("fillStyle")
    private String fillStyle;

    /**
     * 设置填充样式
     */
    public void setFillStyle(String fillStyle) {
        this.fillStyle = fillStyle;
        getView().getCanvasPaint().getFillPaint().setStyle(Paint.Style.FILL);
        getView().fillColor(fillStyle);
        getView().textColor(fillStyle);
    }

    /**
     * 线段厚度的属性（即线段的宽度）。
     */
    @JsProperty("lineWidth")
    private float lineWidth;

    /**
     * 设置线段厚度
     */
    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
        getView().lineWidth(lineWidth);
    }

    /**
     * 描述画笔（绘制图形）颜色或者样式的属性。默认值是 #000 (black)。
     */
    @JsProperty("strokeStyle")
    private String strokeStyle;

    /**
     * 设置画笔（绘制图形）颜色或者样式
     */
    public void setStrokeStyle(String strokeStyle) {
        this.strokeStyle = strokeStyle;
        getView().lineColor(strokeStyle);
        getView().textColor(strokeStyle);
    }

    /**
     * 连接属性合法属性列表
     */
    private static final List<String> LINE_JION_LIST = Collections.unmodifiableList(Arrays.asList("round", "bevel", "miter"));

    /**
     * 2个长度不为 0 的相连部分（线段、圆弧、曲线）如何连接在一起的属性
     * （长度为 0 的变形部分，其指定的末端和控制点在同一位置，会被忽略）。
     */
    @JsProperty("lineJoin")
    private String lineJoin;

    /**
     * 设置连接属性
     */
    public void setLineJoin(String lineJoin) {
        this.lineJoin = lineJoin;
        int joinVal = LINE_JION_LIST.indexOf(lineJoin);
        getView().lineJoin(joinVal);
    }

    /**
     * 填充颜色
     */
    @JsProperty("fillColor")
    private String fillColor;

    /**
     * 设置填充颜色
     */
    public void setFillColor(String color) {
        this.fillColor = color;
        getView().getCanvasPaint().getFillPaint().setStyle(Paint.Style.FILL);
        getView().fillColor(color);
        getView().textColor(color);
    }

    /**
     * 线段末端的属性合法取值列表
     */
    private static final List<String> LINE_GAP_LIST = Collections.unmodifiableList(Arrays.asList("butt", "round", "square"));

    /**
     * 指定如何绘制每一条线段末端的属性。
     * 有 3 个可能的值，分别是：butt, round and square。默认值是 butt。
     */
    @JsProperty("lineCap")
    private String lineCap;

    /**
     * 设置线段末端的属性。
     */
    public void setLineCap(String lineCap) {
        this.lineCap = lineCap;
        int capVal = LINE_GAP_LIST.indexOf(this.lineCap);
        getView().lineCap(capVal);
    }

    /**
     * 绘制图片
     */
    @JsMethod("drawImage")
    public void drawImage(Object bitmap, Object x, Object y, Object dWidth, Object dHeight) {
        if (bitmap instanceof Bitmap) {
            getView().drawImage((Bitmap) bitmap, x, y, dWidth, dHeight);
        } else if (bitmap instanceof String) {
            String url = (String) bitmap;
            if (isRemoteImage(url)) {
                if (!TextUtils.isEmpty(url) && url.startsWith("//")) {
                    url = "https:" + url;
                }
                loadImageWithGlide(url, x, y, dWidth, dHeight);
            } else if (isLocalAbsoluteImage((String) bitmap)) {
                loadImageWithGlide((String) bitmap, x, y, dWidth, dHeight);
            } else if (isLocalRelativeImage((String) bitmap)) {
                String imageSrc = (String) bitmap;
                int jsSourceType = JsSourceUtil.getJsSourceType(getHummerContext().getJsSourcePath());
                imageSrc = JsSourceUtil.getRealResourcePath(imageSrc, getHummerContext().getJsSourcePath());
                switch (jsSourceType) {
                    case JsSourceUtil.JS_SOURCE_TYPE_ASSETS:
                        imageSrc = "file:///android_asset/" + imageSrc;
                        loadImageWithGlide(imageSrc, x, y, dWidth, dHeight);
                        break;
                    case JsSourceUtil.JS_SOURCE_TYPE_FILE:
                        loadImageWithGlide(imageSrc, x, y, dWidth, dHeight);
                        break;
                    default:
                        break;
                }
            } else {
                int imageId = YogaResUtils.getResourceId((String) bitmap, "drawable", null);
                Bitmap bmp = BitmapFactory.decodeResource(getView().getContext().getResources(), imageId);
                getView().drawImage(bmp, x, y, dWidth, dHeight);
            }
        }
    }

    /**
     * 使用glide组件加载图片
     */
    private void loadImageWithGlide(String url, Object x, Object y, Object dWidth, Object dHeight) {
        Glide.with(getContext()).asBitmap().load(url).into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap bmp, @Nullable Transition<? super Bitmap> transition) {
                getView().drawImage(bmp, x, y, dWidth, dHeight);
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }
        });
    }

    /**
     * 是否是远程http图片地址
     */
    private static boolean isRemoteImage(String imageSrc) {
        return imageSrc != null && (imageSrc.startsWith("//") || imageSrc.toLowerCase().startsWith("http"));
    }

    /**
     * 是否是本地绝对路径图片地址
     */
    private static boolean isLocalAbsoluteImage(String imageSrc) {
        return imageSrc != null && imageSrc.startsWith("/");
    }

    /**
     * 是否是本地相对路径图片地址
     */
    private static boolean isLocalRelativeImage(String imageSrc) {
        return imageSrc != null && imageSrc.startsWith("./");
    }
}
