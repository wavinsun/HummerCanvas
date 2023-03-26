package com.example.hummer;

import android.app.Application;

import com.didi.hummer.Hummer;

/**
 * 应用
 */
public class HummerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化Hummer
        Hummer.init(this);
    }
}
