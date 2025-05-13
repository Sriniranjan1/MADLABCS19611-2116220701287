package com.example.study_timer

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddPlanActivity : AppCompatActivity() {

    private lateinit var titleInput: EditText
    private lateinit var deadlineInput: EditText
    private lateinit var durationInput: EditText
    private lateinit var saveButton: Button
    private lateinit var dbHelper: PlansDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_plan)

        dbHelper = PlansDatabaseHelper(this)

        titleInput = findViewById(R.id.titleInput)
        deadlineInput = findViewById(R.id.deadlineInput)
        durationInput = findViewById(R.id.durationInput)
        saveButton = findViewById(R.id.saveButton)

        saveButton.setOnClickListener {
            savePlan()
        }
    }

    private fun savePlan() {
        val title = titleInput.text.toString().trim()
        val deadline = deadlineInput.text.toString().trim()
        val duration = durationInput.text.toString().trim()

        if (title.isEmpty() || deadline.isEmpty() || duration.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val success = dbHelper.insertPlan(title, deadline, duration)
        if (success) {
            Toast.makeText(this, "Plan Added Successfully!", Toast.LENGTH_SHORT).show()
            finish() // go back to AllPlansActivity
        } else {
            Toast.makeText(this, "Failed to add plan.", Toast.LENGTH_SHORT).show()
        }
    }
}
