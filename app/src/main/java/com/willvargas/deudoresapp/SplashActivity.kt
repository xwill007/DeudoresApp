package com.willvargas.deudoresapp

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.layout_splash)

        val timer = Timer()
        timer.schedule(
            timerTask {
            goToActivity()
        },1000
        )
    }

    private fun goToActivity() {
        val intent = Intent (this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}