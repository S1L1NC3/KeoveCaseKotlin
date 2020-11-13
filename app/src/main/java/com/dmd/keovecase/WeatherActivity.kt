package com.dmd.keovecase

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmd.keovecase.adapters.WeatherAdapter
import com.dmd.keovecase.model.AuthenticateResponse
import com.dmd.keovecase.viewModels.WeatherViewModel
import kotlinx.android.synthetic.main.details_activity.*

class WeatherActivity : AppCompatActivity() {
    private lateinit var weatherViewModel : WeatherViewModel
    private lateinit var userDetails : AuthenticateResponse
    private val recyclerViewAdapter = WeatherAdapter(arrayListOf())
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)


        val newsExtra = intent.getSerializableExtra(applicationContext.packageName)
        userDetails = newsExtra as AuthenticateResponse

        weatherViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(WeatherViewModel::class.java)

        weatherViewModel.setBearer(userDetails.jwtToken)
        weatherViewModel.refreshData()

        recyclerView.layoutManager= LinearLayoutManager( applicationContext)
        recyclerView.adapter = recyclerViewAdapter
    }

    override fun onStart() {
        super.onStart()

        swipeRefreshLayout.setOnRefreshListener {
            recyclerView.visibility  = View.GONE
            weatherViewModel.refreshData()
            swipeRefreshLayout.isRefreshing = false
        }

        observeLiveDataForWeather()
    }


    private fun observeLiveDataForWeather(){
        weatherViewModel.data.observe(this, { data->
            data?.let {
                recyclerView.visibility = View.VISIBLE
                recyclerViewAdapter.updateList(data)
            }

        })
        weatherViewModel.dataError.observe(this, { error ->
            error?.let {
                //Potansiyel error handling
            }

        })
        weatherViewModel.dataLoading.observe(this, { loading->
            loading?.let {
                //Loading handling
            }

        })
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, applicationContext.getString(R.string.press_to_exit), Toast.LENGTH_SHORT).show()

        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }
}