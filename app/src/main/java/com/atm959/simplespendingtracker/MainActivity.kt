package com.atm959.simplespendingtracker

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

val nameArray = arrayOf<String>(
    "Applesauce",
    "Oranges",
    "Cottage Cheese",
    "Peaches",
    "Art",
    "Toothpaste",
    "Spaghetti",
    "Spaghetti Sauce",
    "Parmesan Cheese",
    "Water"
)
val spentArray =
    arrayOf<Float>(10.0f, 1339.05f, 1234.56f, 5894.03f, 50.0f, 2.0f, 1.0f, 2.0f, 545.0f, 0.0f)
val maxArray = arrayOf<Float>(
    1000000.0f,
    1337.69f,
    45.0f,
    6000.0f,
    100.0f,
    4.0f,
    5000.0f,
    5000.0f,
    600.0f,
    0.0f
)

fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()
fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var expenditureLinearLayout = findViewById<LinearLayout>(R.id.expenditure_linear_layout);

        for (i in 0..9) {
            var newLinearLayout = LinearLayout(application.applicationContext)
            newLinearLayout.id = View.generateViewId()
            newLinearLayout.setBackgroundColor(0xFF424242.toInt())
            newLinearLayout.layoutParams = expenditureLinearLayout.layoutParams
            newLinearLayout.setPadding(5.toPx(), 5.toPx(), 5.toPx(), 5.toPx())
            expenditureLinearLayout.addView(newLinearLayout)
            var frag = ExpenditureFragment.newInstance(nameArray[i], spentArray[i], maxArray[i])
            var fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(newLinearLayout.id, frag)
            fragmentTransaction.commit()
        }

        val deleteIntent = Intent(this, DeleteExpenditureActivity::class.java)
        val editIntent = Intent(this, EditExpenditureActivity::class.java)
        val spentIntent = Intent(this, AddAmountSpentActivity::class.java)
        val addIntent = Intent(this, AddExpenditureActivity::class.java)

        val deleteButton = findViewById<FloatingActionButton>(R.id.delete_expenditure_button)
        deleteButton?.setOnTouchListener { p0, p1 ->
            if (p1!!.action == MotionEvent.ACTION_DOWN) startActivity(deleteIntent)
            true
        }
        val editButton = findViewById<FloatingActionButton>(R.id.edit_expenditure_button)
        editButton?.setOnTouchListener { p0, p1 ->
            if (p1!!.action == MotionEvent.ACTION_DOWN) startActivity(editIntent)
            true
        }
        val spentMoneyButton = findViewById<FloatingActionButton>(R.id.spent_money_button)
        spentMoneyButton?.setOnTouchListener { p0, p1 ->
            if (p1!!.action == MotionEvent.ACTION_DOWN) startActivity(spentIntent)
            true
        }
        val addButton = findViewById<FloatingActionButton>(R.id.add_expenditure_button)
        addButton?.setOnTouchListener { p0, p1 ->
            if (p1!!.action == MotionEvent.ACTION_DOWN) startActivity(addIntent)
            true
        }
    }
}