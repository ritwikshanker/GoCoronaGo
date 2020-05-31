package com.example.gocoronago.stayHome

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gocoronago.R

class StayHomeActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, StayHomeActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stay_home_activity)
        val fragment = StayHomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }


    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fragment_fade_enter, R.anim.fragment_fade_exit)
//        setContentView(R.layout.main_fragment)
    }


}