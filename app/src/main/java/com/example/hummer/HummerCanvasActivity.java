package com.example.hummer;

import android.os.Bundle;

import com.didi.hummer.HummerActivity;
import com.didi.hummer.adapter.navigator.NavPage;
import com.didi.hummer.context.HummerContext;
import com.didi.hummer.register.HummerRegister$$app;

/**
 * Hummer页面
 */
public class HummerCanvasActivity extends HummerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Hummer Common Canvas");
    }

    @Override
    protected void initHummerRegister(HummerContext context) {
        // 注册Canvas组件
        HummerRegister$$app.init(context);
    }

    @Override
    protected NavPage getPageInfo() {
        // URL来源一：通过Intent传入
        // return super.getPageInfo();

        // URL来源二：assets文件路径
        // return new NavPage("HelloWorld.js");

        // URL来源三：手机设备文件路径
        // return new NavPage("/sdcard/HelloWorld.js");

        // URL来源四：网络url
        // return new NavPage("http://x.x.x.x:8000/index.js");

        // 页面来源: assets/HummerCanvas.js
        return new NavPage("HummerCanvas.js");
    }
}