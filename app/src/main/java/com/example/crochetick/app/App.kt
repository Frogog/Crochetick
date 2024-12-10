package com.example.crochetick.app

import android.app.Application
import com.example.crochetick.provider.DBProvider

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        DBProvider.create(this)
    }
}