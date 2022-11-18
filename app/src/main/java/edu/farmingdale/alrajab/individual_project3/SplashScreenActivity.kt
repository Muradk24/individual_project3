package edu.farmingdale.alrajab.individual_project3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(Looper.getMainLooper()).postDelayed({//we are giving a delay of three seconds
            val intent= Intent(this,LoginAcitivity::class.java)//we are creating an intent
            startActivity(intent)
            finish()
        },3000)


    }
}

