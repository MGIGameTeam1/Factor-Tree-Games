package com.mgi.factortree;

import android.app.Application;

import com.eyro.mesosfer.Mesosfer;

public class MesosferApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // uncomment this line below to show Mesosfer log in verbose mode
        // Mesosfer.setLogLevel(Mesosfer.LOG_LEVEL_VERBOSE);
        Mesosfer.setPushNotification(true);
        // initialize Mesosfer SDK
        Mesosfer.initialize(this, "YmDzjJ9gOM", "8tA03bi9FIWj379cAkEuwappgTN3C1Jc");
    }
}