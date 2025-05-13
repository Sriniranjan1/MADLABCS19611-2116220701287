package com.example.study_timer

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class PlansActivity() : AppCompatActivity(), Parcelable {

    private lateinit var dbHelper: PlansDatabaseHelper
    private lateinit var plansListView: ListView
    private lateinit var homeButton: Button
    private lateinit var savePlanButton: Button
    private lateinit var adapter: PlansAdapter
    private lateinit var plansList: MutableList<Plan>

    constructor(parcel: Parcel) : this() {

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plans)

        supportActionBar?.hide()

        dbHelper = PlansDatabaseHelper(this)
        plansListView = findViewById(R.id.listViewPlans)
        homeButton = findViewById(R.id.goToTimerButton)
        savePlanButton = findViewById(R.id.savePlanButton)

        plansList = dbHelper.getAllPlans()
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

        savePlanButton.setOnClickListener {
            val intent = Intent(this, PlansActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showEditDeleteDialog(plan: Plan) {
        val options = arrayOf("Edit", "Delete")

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose option")
        builder.setItems(options) { _, which ->
            when (which) {
                0 -> editPlan(plan)
                1 -> deletePlan(plan)
            }
        }
        builder.show()
    }

    private fun editPlan(plan: Plan) {
        val intent = Intent(this, EditPlanActivity::class.java)
        intent.putExtra("plan_id", plan.id)
        intent.putExtra("plan_title", plan.title)
        intent.putExtra("plan_deadline", plan.deadline)
        intent.putExtra("plan_duration", plan.duration)
        startActivity(intent)
        finish()
    }

    private fun deletePlan(plan: Plan) {
        val deletedRows = dbHelper.deletePlan(plan.id)
        if (deletedRows > 0) {
            Toast.makeText(this, "Plan deleted", Toast.LENGTH_SHORT).show()
            refreshList()
        } else {
            Toast.makeText(this, "Failed to delete plan", Toast.LENGTH_SHORT).show()
        }
    }

    private fun refreshList() {
        plansList.clear()
        plansList.addAll(dbHelper.getAllPlans())
        adapter.notifyDataSetChanged()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlansActivity> {
        override fun createFromParcel(parcel: Parcel): PlansActivity {
            return PlansActivity(parcel)
        }

        override fun newArray(size: Int): Array<PlansActivity?> {
            return arrayOfNulls(size)
        }
    }
}
