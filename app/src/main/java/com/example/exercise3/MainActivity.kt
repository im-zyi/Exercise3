package com.example.exercise3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.content.SharedPreferences
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelProviders.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewmodel:myViewModel
  //  private val mPrefs: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      /*
        viewmodel=ViewModelProviders.of(this)
       */
        viewmodel=ViewModelProviders.of(this).get(myViewModel::class.java)
        display()
      btnCalculate.setOnClickListener{
            viewmodel.payment=getPayment()
            display()
        }
        btnReset.setOnClickListener{
            spinner.setSelection(0)
            radioGroup.clearCheck()
            chkSmoker.isChecked=false
            txtPyament.text=""
            viewmodel.payment=0.0
        }
    }
    fun display(){
        if(viewmodel.payment!=0.0){
            txtPyament.text=viewmodel.payment.toString()
        }
    }

    fun getPayment():Double{
        var premium:Double
        var extraPayGender=0
        var extraPaySmoker=0
        val pos =spinner.selectedItemPosition

        if(pos==1) {
            premium = 70.0
            if(radMale.isChecked)
                extraPayGender=50
            if(chkSmoker.isChecked)
                extraPaySmoker=100
        }
        else if(pos==2) {
            premium = 90.0
            if(radMale.isChecked)
                extraPayGender=100
            if(chkSmoker.isChecked)
                extraPaySmoker=150
        }
        else if(pos==3) {
            premium = 120.0
            if(radMale.isChecked)
                extraPayGender=150
            if(chkSmoker.isChecked)
                extraPaySmoker=200
        }
        else if(pos==4||pos==5){
            premium = 150.0
            if(radMale.isChecked)
                extraPayGender=200
            if(pos==4&&chkSmoker.isChecked)
                extraPaySmoker=250
            else
                extraPaySmoker=300
        }
        else
            premium=60.0

        return premium+extraPayGender+extraPaySmoker
    }

}
