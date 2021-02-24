package com.example.studygroup;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class MykakaoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        KakaoSdk.init(this,"fb7c4f32b8ff6dfde087a0dc171301d0");

    }
}
