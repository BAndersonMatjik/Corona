package com.beone.bestpractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.beone.bestpractice.ui.CountryFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val fragmentTransaction = this.supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.framelayout, CountryFragment(), "state")
                    .commit()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }
}