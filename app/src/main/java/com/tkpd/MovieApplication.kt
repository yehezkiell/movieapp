package com.tkpd

import android.app.Application
import com.facebook.stetho.Stetho

/**
 * Created by Yehezkiel on 11/10/20
 */
class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this);
    }
}