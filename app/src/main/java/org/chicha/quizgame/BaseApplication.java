package org.chicha.quizgame;

import android.app.Application;

import java.util.HashMap;
import java.util.Map;

import org.chicha.quizgame.utils.ResponseAnswer;
import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class BaseApplication extends Application {
    public static Map<Long, ResponseAnswer> answersMapSession;

    @Override
    public void onCreate() {
        super.onCreate();

        answersMapSession = new HashMap<>();
    }
}