package com.example.dobcalc

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var textSelectedDate: TextView? = null
    private var textAgeInMinutes: TextView? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        textSelectedDate = findViewById(R.id.selectedDate)
        textAgeInMinutes = findViewById(R.id.ageInMinutes)

        btnDatePicker.setOnClickListener{
            clickDatePiker()
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun clickDatePiker()
    {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        //DatePickerDialog(this,
        //DatePickerDialog.OnDateSetListener{
        //    view, year, month, dayOfMonth ->
        //    Toast.makeText(this, "Date picker works", Toast.LENGTH_SHORT).show()
        //}, year, month, day).show()
        val datePickerDialog = DatePickerDialog(this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this, "Date picker works", Toast.LENGTH_SHORT).show()

                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                textSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)
                theDate?.let{
                    val selectedDateInMinutes = theDate.time / 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentdateInMinutes = currentDate.time / 60000

                        val differenceInMinutes = currentdateInMinutes - selectedDateInMinutes
                        textAgeInMinutes?.text = differenceInMinutes.toString()
                    }
                }

            }, year, month, day)

        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 86400000
        datePickerDialog.show()
    }
}