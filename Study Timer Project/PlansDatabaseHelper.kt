package com.example.study_timer

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class PlansDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "PlansDB"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "plans"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_DEADLINE = "deadline"
        private const val COLUMN_DURATION = "duration"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE $TABLE_NAME (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_TITLE TEXT, " +
                    "$COLUMN_DEADLINE TEXT, " +
                    "$COLUMN_DURATION TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertPlan(title: String, deadline: String, duration: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, title)
            put(COLUMN_DEADLINE, deadline)
            put(COLUMN_DURATION, duration)
        }
        val result = db.insert(TABLE_NAME, null, values)
        db.close()
        Log.d("DB_INSERT", "Inserted plan with result: $result")
        return result != -1L
    }

    fun getAllPlans(): MutableList<Plan> {
        val plans = mutableListOf<Plan>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        if (cursor.moveToFirst()) {
            do {
                plans.add(
                    Plan(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DEADLINE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DURATION))
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return plans
    }

    fun getPlanById(id: Int): Plan? {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = ?", arrayOf(id.toString()))
        var plan: Plan? = null
        if (cursor.moveToFirst()) {
            plan = Plan(
                cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DEADLINE)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DURATION))
            )
        }
        cursor.close()
        db.close()
        return plan
    }

    fun deletePlan(id: Int): Int {
        val db = writableDatabase
        val result = db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id.toString()))
        db.close()
        Log.d("DB_DELETE", "Deleted plan ID $id, result: $result")
        return result
    }

    fun updatePlan(id: Int, title: String, deadline: String, duration: String): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, title)
            put(COLUMN_DEADLINE, deadline)
            put(COLUMN_DURATION, duration)
        }
        val result = db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(id.toString()))
        db.close()
        Log.d("DB_UPDATE", "Updated plan ID $id, result: $result")
        return result
    }
}
