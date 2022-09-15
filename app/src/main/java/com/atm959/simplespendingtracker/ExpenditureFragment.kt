package com.atm959.simplespendingtracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.text.NumberFormat
import java.util.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_EXPENDITURE_NAME = "expenditureName"
private const val ARG_SPENT_AMOUNT = "spentAmount"
private const val ARG_MAX_AMOUNT = "maxAmount"

/**
 * A simple [Fragment] subclass.
 * Use the [ExpenditureFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExpenditureFragment : Fragment() {
    private var expenditureName: String? = null
    private var spentAmount: Float? = null
    private var maxAmount: Float? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            expenditureName = it.getString(ARG_EXPENDITURE_NAME)
            spentAmount = it.getFloat(ARG_SPENT_AMOUNT)
            maxAmount = it.getFloat(ARG_MAX_AMOUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflatedView = inflater.inflate(R.layout.fragment_expenditure, container, false)

        val expenditureTypeLabel =
            inflatedView?.findViewById<TextView>(R.id.expenditure_type_name_label);
        val spentAmountLabel = inflatedView?.findViewById<TextView>(R.id.spent_amount_label);
        val maxAmountLabel = inflatedView?.findViewById<TextView>(R.id.max_amount_label)
        val amountLeftLabel = inflatedView?.findViewById<TextView>(R.id.amount_left_label)

        expenditureTypeLabel?.text = expenditureName

        val numberFormat = NumberFormat.getCurrencyInstance()
        numberFormat.maximumFractionDigits = 2
        numberFormat.currency = Currency.getInstance(Locale.getDefault())

        spentAmountLabel?.text = numberFormat.format(spentAmount) + " "
        maxAmountLabel?.text = "/ " + numberFormat.format(maxAmount)

        var amountLeft = maxAmount!! - spentAmount!!
        amountLeftLabel?.text =
            numberFormat.format(amountLeft) + " " + getString(R.string.left_string)

        var inOutBudget: String
        var color: Long
        if (spentAmount!! <= maxAmount!!) {
            color = 0xFF00FF00
        } else {
            color = 0xFFFF0000
        }
        spentAmountLabel?.setTextColor(color.toInt())
        maxAmountLabel?.setTextColor(color.toInt())

        // Inflate the layout for this fragment
        return inflatedView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param expenditureName The name of this expenditure
         * @param spentAmount Money already spent on this type of expenditure
         * @param maxAmount Maximum budget for this type of expenditure
         * @return A new instance of fragment ExpenditureFragment.
         */
        @JvmStatic
        fun newInstance(expenditureName: String, spentAmount: Float, maxAmount: Float) =
            ExpenditureFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_EXPENDITURE_NAME, expenditureName)
                    putFloat(ARG_SPENT_AMOUNT, spentAmount)
                    putFloat(ARG_MAX_AMOUNT, maxAmount)
                }
            }
    }
}