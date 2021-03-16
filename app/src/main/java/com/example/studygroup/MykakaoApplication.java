package com.example.studygroup;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class MykakaoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        KakaoSdk.init(this,"0c339b736e2241fcf5210bd8f5a3b019");

    }
}
