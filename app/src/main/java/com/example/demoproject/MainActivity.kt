package com.example.demoproject

import android.app.DatePickerDialog
import android.app.Dialog
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.demoproject.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

/**
 * This is my main activity
 */

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding



    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        binding.btnDatePicker.setOnClickListener { view -> clickDatePicker(view)
            Toast.makeText(this, "Button Works", Toast.LENGTH_LONG).show()
        }

        binding.button.setOnClickListener { view -> age(view)
        Toast.makeText(this, "Button works",Toast.LENGTH_LONG).show()
        }
        setContentView(view)




    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun age (view: View) {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val today = Date()
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)



        DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->
            Toast.makeText(this, "Chosen year is $selectedYear", Toast.LENGTH_LONG).show()

            val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"

            val theDate = sdf.parse(selectedDate)

            val currentDate = Date()

            val dateDifference : Long  = (currentDate.time - theDate.time)/60000

            val dateDifferenceYes = dateDifference.toString()

            binding.selectedDate.setText(selectedDate)

            binding.ageInMinutes.setText(dateDifferenceYes)

            Toast.makeText(this, "What is the difference: $dateDifference?",Toast.LENGTH_LONG).show()

        } , year, month, day).show()

    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDatePicker(view:View){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val today = Date()
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)



        DatePickerDialog( this, DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDay ->
            Toast.makeText(this, "Chosen year is $selectedYear, the month is $selectedMonth, the day is $selectedDay", Toast.LENGTH_LONG).show()
            //Toast.makeText(this,"Today is $today",Toast.LENGTH_LONG).show()

            val selectedDate = "$selectedDay/${selectedMonth+1}/$selectedYear"

            binding.selectedDate.setText(selectedDate)

            val theDate = sdf.parse(selectedDate)

           // println(theDate)

          //  var minutes = (today.time - theDate.time)% 86400000%360000/60000

            //Toast.makeText(this,"Your age is $minutes",Toast.LENGTH_LONG).show()

            //var minutes : Long = (today.time - theDate)%864000000%3600000/60000

            //var minutesToInt = minutes.toInt()

            //binding.ageInMinutes.setText(minutesToInt)


        }, year , month, day).show()
    }

}

private fun View.setOnClickListener() {
    println("Button is Clicked")
}
