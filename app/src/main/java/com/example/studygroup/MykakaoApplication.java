package com.example.studygroup;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class MykakaoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        KakaoSdk.init(this,"572a071b189f9c9c3c56de4d28ad7596");

    }
}
