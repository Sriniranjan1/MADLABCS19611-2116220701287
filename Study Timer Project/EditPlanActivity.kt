package com.example.study_timer

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class EditPlanActivity : AppCompatActivity() {

    private lateinit var dbHelper: PlansDatabaseHelper
    private lateinit var titleEditText: EditText
    private lateinit var deadlineEditText: EditText
    private lateinit var durationEditText: EditText
    private lateinit var updateButton: Button

    private var planId: Int = -1
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_plan)

        supportActionBar?.hide()

        dbHelper = PlansDatabaseHelper(this)

        titleEditText = findViewById(R.id.editTitleEditText)
        deadlineEditText = findViewById(R.id.editDeadlineEditText)
        durationEditText = findViewById(R.id.editDurationEditText)
        updateButton = findViewById(R.id.updatePlanButton)

        // Get data passed from AllPlansActivity
        planId = intent.getIntExtra("plan_id", -1)
        val planTitle = intent.getStringExtra("plan_title")
        val planDeadline = intent.getStringExtra("plan_deadline")
        val planDuration = intent.getStringExtra("plan_duration")

        // Fill the fields
        titleEditText.setText(planTitle)
        deadlineEditText.setText(planDeadline)
        durationEditText.setText(planDuration)

        durationEditText.setOnClickListener {
            showDatePicker()
        }

        updateButton.setOnClickListener {
            updatePlan()
        }
    }

    private fun showDatePicker() {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val formattedDate = String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay)
            durationEditText.setText(formattedDate)
        }, year, month, day)

        datePicker.show()
    }

    private fun updatePlan() {
        val newTitle = titleEditText.text.toString().trim()
        val newDeadline = deadlineEditText.text.toString().trim()
        val newDuration = durationEditText.text.toString().trim()

        if (newTitle.isNotEmpty() && newDeadline.isNotEmpty() && newDuration.isNotEmpty()) {
            val updatedRows = dbHelper.updatePlan(planId, newTitle, newDeadline, newDuration)

            if (updatedRows > 0) {
                Toast.makeText(this, "Plan updated successfully", Toast.LENGTH_SHORT).show()
                finish()
                startActivity(Intent(this, AllPlansActivity::class.java))
            } else {
                Toast.makeText(this, "Failed to update plan", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }
}
