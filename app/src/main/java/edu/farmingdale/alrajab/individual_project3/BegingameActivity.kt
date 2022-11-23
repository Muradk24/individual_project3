package edu.farmingdale.alrajab.individual_project3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class BegingameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_begingame)
       val easyLevelBtn= findViewById<Button>(R.id.easylevelbutton)
        easyLevelBtn.setOnClickListener {
            val easyLevelIntent = Intent(this, EasylevelActivity::class.java)
            startActivity(  easyLevelIntent  )
        }

        val hardLevelBtn= findViewById<Button>(R.id.hardlevelbutton)
        hardLevelBtn.setOnClickListener {
            val hardLevelIntent = Intent(this, HardlevelActivity::class.java)
            startActivity(  hardLevelIntent  )
        }
    }
}