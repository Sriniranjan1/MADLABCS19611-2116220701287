package com.example.study_timer

import com.example.study_timer.AddPlanActivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class AllPlansActivity : AppCompatActivity() {

    private lateinit var dbHelper: PlansDatabaseHelper
    private lateinit var plansListView: ListView
    private lateinit var homeButton: Button
    private lateinit var addPlanButton: Button
    // corrected name
    private lateinit var adapter: PlansAdapter
    private lateinit var plansList: MutableList<Plan>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_plans)

        supportActionBar?.hide()

        dbHelper = PlansDatabaseHelper(this)
        plansListView = findViewById(R.id.listViewPlans)
        homeButton = findViewById(R.id.goToTimerButton)
        addPlanButton = findViewById(R.id.addPlanButton)  // 🛠️ link to your button id

        plansList = dbHelper.getAllPlans()
        checkUpcomingDeadlines()

        adapter = PlansAdapter(this, plansList)
        plansListView.adapter = adapter

        plansListView.setOnItemClickListener { _, _, position, _ ->
            val plan = plansList[position]
            showEditDeleteDialog(plan)
        }

        homeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        addPlanButton.setOnClickListener {
            val intent = Intent(this, AddPlanActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        refreshList()
        checkUpcomingDeadlines() // 🛠 move it here!
    }


    private fun refreshList() {
        plansList.clear()
        plansList.addAll(dbHelper.getAllPlans())
        adapter.notifyDataSetChanged()
    }


    private fun showEditDeleteDialog(plan: Plan) {
        val intent = Intent(this, EditPlanActivity::class.java)
        intent.putExtra("plan_id", plan.id)
        intent.putExtra("plan_title", plan.title)
        intent.putExtra("plan_deadline", plan.deadline)
        intent.putExtra("plan_duration", plan.duration)
        startActivity(intent)
    }
    private fun checkUpcomingDeadlines() {
        val todayPlans = mutableListOf<String>()
        val tomorrowPlans = mutableListOf<String>()

        val calendar = java.util.Calendar.getInstance()

        val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
        val todayDate = dateFormat.format(calendar.time)

        calendar.add(java.util.Calendar.DAY_OF_MONTH, 1)
        val tomorrowDate = dateFormat.format(calendar.time)

        for (plan in plansList) {
            if (plan.deadline == todayDate) {
                todayPlans.add(plan.title)
            } else if (plan.deadline == tomorrowDate) {
                tomorrowPlans.add(plan.title)
            }
        }

        if (todayPlans.isNotEmpty() || tomorrowPlans.isNotEmpty()) {
            val message = buildString {
                if (todayPlans.isNotEmpty()) {
                    append("Expiring Today:\n")
                    todayPlans.forEach { append("- $it\n") }
                }
                if (tomorrowPlans.isNotEmpty()) {
                    append("\nExpiring Tomorrow:\n")
                    tomorrowPlans.forEach { append("- $it\n") }
                }
            }

            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Upcoming Deadlines")
                .setMessage(message.trim())
                .setPositiveButton("OK", null)
                .show()
        }
    }

}
