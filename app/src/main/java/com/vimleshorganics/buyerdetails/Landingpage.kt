package com.vimleshorganics.buyerdetails

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase


class Landingpage : AppCompatActivity() {
    private var TAG:String= Landingpage::class.java.toString()
    lateinit var tabLayout: TabLayout
    private val adapter: TabAdapter? = null
    private val viewPager: ViewPager? = null

    @SuppressLint("InvalidAnalyticsName")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout=findViewById(R.id.tablayout)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                var fragment: Fragment? = null
                when (tab!!.position) {
                    0 -> fragment = MaliFragment("%Mali%")
                    1 -> fragment = MaliFragment("Customer")
                    2 -> fragment = MaliFragment("Farmers")
                }
                val fm: FragmentManager = supportFragmentManager
                val ft: FragmentTransaction = fm.beginTransaction()
                if (fragment != null) {
                    ft.replace(R.id.simpleFrameLayout, fragment)
                }
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                ft.commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.d(TAG,"with out selection")
                Log.i(TAG,"with out selection")
                Log.e(TAG,"with out selection")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
        findViewById<Button>(R.id.addnewbuyer).setOnClickListener {
            startActivity(Intent(this, AddBuyer::class.java))
            Firebase.analytics.logEvent("button click",null)
        }
    }
}