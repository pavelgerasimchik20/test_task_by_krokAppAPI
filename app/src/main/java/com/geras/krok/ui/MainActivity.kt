package com.geras.krok.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.geras.krok.R
import com.geras.krok.domain.model.City
import com.geras.krok.domain.model.PointOfInterest
import com.geras.krok.ui.city.CityFragment
import com.geras.krok.ui.details.DetailsFragment
import com.geras.krok.ui.points.PointsFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openCityFragment()
    }

    private fun openCityFragment() {
        val fragment = CityFragment.newInstance()
        commitTransaction(fragment)
    }

    fun openPointsFragment(city: City) {
        val fragment = PointsFragment.newInstance(city)
        commitTransaction(fragment)
    }

    fun openDetailsFragment(point: PointOfInterest, url: String, name: String) {
        val fragment = DetailsFragment.newInstance(point, url, name)
        commitTransaction(fragment)
    }

    private fun commitTransaction(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.animator.reversal_in_right,
                R.animator.reversal_out_right,
                R.animator.reversal_in_left,
                R.animator.reversal_out_left
            )
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(fragment.javaClass.name)
        transaction.commit()
    }
}