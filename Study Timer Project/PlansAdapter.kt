package com.example.study_timer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
class PlansAdapter(
    private val context: Context,
    private val plans: List<Plan>
) : ArrayAdapter<Plan>(context, 0, plans) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_plans, parent, false)

        val titleText = view.findViewById<TextView>(R.id.planTitle)
        val deadlineText = view.findViewById<TextView>(R.id.planDeadline)
        val durationText = view.findViewById<TextView>(R.id.planDuration)

        val plan = plans[position]
        titleText.text = plan.title
        deadlineText.text = "Deadline: ${plan.deadline}"
        durationText.text = "Duration: ${plan.duration}"

        return view
    }
}
