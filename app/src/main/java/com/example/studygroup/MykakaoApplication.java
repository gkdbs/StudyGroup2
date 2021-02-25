package com.example.studygroup;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class MykakaoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        KakaoSdk.init(this,"aebddd0770eaf1d1d9fc45cc876ef081");

    }
}
