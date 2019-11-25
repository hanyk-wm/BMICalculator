package com.example.bmicalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.calculate).setOnClickListener{
            calculateBMI(it)
        }
        findViewById<Button>(R.id.reset).setOnClickListener {
            resetAll()
        }
    }

    private fun calculateBMI(view:View)
    {
        val weight:Double = weight.text.toString().toDouble()
        val height:Double = height.text.toString().toDouble()
        val result:String
        val bmivalue:Double = weight/(height*height)
        bmi.visibility = View.VISIBLE

        if(bmivalue<=18.5) {
            result ="UNDERWEIGHT"
            bmiimage.setImageResource(R.drawable.under)
        }
        else if(bmivalue>=25){
            result ="OBESITY"
            bmiimage.setImageResource(R.drawable.over)
        }
        else{
           result = "NORMAL"
            bmiimage.setImageResource(R.drawable.normal)
        }
        bmi.text = "BMI: %.2f (%s)".format(bmivalue,result)
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE)as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken,0)
    }
    private fun resetAll(){


        height.text.clear()
        weight.text.clear()
        bmi.visibility = View.GONE
        bmiimage.setImageResource(R.drawable.empty)
        weight.requestFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE)as InputMethodManager
       imm.showSoftInput(bmi,0)

    }
}
