package com.example.mbook_api.activity.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.example.mbook_api.R
import com.example.mbook_api.activity.mainActivity.MainActivity

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val manuBukuCerita : CardView = findViewById(R.id.menu_buku_cerita)
        manuBukuCerita.setOnClickListener {
            val intent = Intent(this@DashboardActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}