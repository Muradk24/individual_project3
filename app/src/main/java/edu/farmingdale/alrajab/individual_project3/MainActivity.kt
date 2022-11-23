package edu.farmingdale.alrajab.individual_project3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import java.util.concurrent.TransferQueue

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val builder = AlertDialog.Builder(this)
            .create()
        val view = layoutInflater.inflate(R.layout.dialog_result,null)
        val tvYoufailed=view.findViewById<TextView>(R.id.tvYoufailed)
        val directmessege=view.findViewById<TextView>(R.id.tvGamehelp)
        val retrying=view.findViewById<Button>(R.id.retryButton)
        setContentView(R.layout.activity_main)
        retrying.setOnClickListener{

        }
    }








}