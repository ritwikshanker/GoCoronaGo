package com.example.gocoronago.stayHome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gocoronago.R

class StayHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stay_home_vp)
        StayHomeFragment.start(supportFragmentManager)

        val fragment = StayHomeFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.stay_home_vp, fragment, "Stay Home Fragment")
            .commit()
    }


    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fragment_fade_enter, R.anim.fragment_fade_exit)
//        setContentView(R.layout.main_fragment)
    }


}