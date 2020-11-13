package com.dmd.keovecase

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dmd.keovecase.model.requestModels.LoginRequest
import com.dmd.keovecase.viewModels.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private lateinit var loginViewModel : LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())
            .get(LoginViewModel::class.java)

    }

    private fun observeLiveData(){
        loginViewModel.data.observe(this, { data ->
            data?.let { authenticateResponse ->
                val weatherIntent = Intent(applicationContext, WeatherActivity::class.java)
                weatherIntent.putExtra(applicationContext.packageName, authenticateResponse)
                startActivity(weatherIntent)
            }

        })
        loginViewModel.dataError.observe(this, { error ->
            error?.let {
                if(it) {
                    Toast.makeText(applicationContext,applicationContext.getString(R.string.user_password_wrong),Toast.LENGTH_LONG).show()
                }
            }

        })
        loginViewModel.dataLoading.observe(this, { loading->
            loading?.let {

            }

        })
    }

    override fun onStart() {
        super.onStart()

        observeLiveData()
    }

    fun btnLoginOnClick(view: View){
        if (checkInputs()){
            val login = LoginRequest(edtUsername.text.toString(), edtPassword.text.toString())
            loginViewModel.assignData(login)
            loginViewModel.refreshData()
            //input doğru servise git
        }
    }

    private fun checkInputs() : Boolean{
        if (edtUsername.text == null || edtUsername.text.toString() == ""){
            edtUsername.error = applicationContext.getString(R.string.username_empty)
            return false
        }
        return if (edtPassword == null || edtPassword.text.toString() == ""){
            edtPassword.error = applicationContext.getString(R.string.password_empty)
            false
        } else {
            true
        }
    }
}