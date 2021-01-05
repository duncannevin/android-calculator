package com.duncannevin.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private var calculator: Calculator = Calculator()

    private var operatorEle: TextView? = null
    private var newNumberEle: TextView? = null
    private var resultEle: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRenderElements()
        startNumListeners()
        startOperatorListeners()
    }

    private fun setRenderElements() {
        operatorEle = findViewById(R.id.operation)
        newNumberEle = findViewById(R.id.newNumber)
        resultEle = findViewById(R.id.result)
    }

    private fun startNumListeners() {
        for (num in 0..9) {
            val btnId = resources.getIdentifier("button$num", "id", packageName)
            val btnEle: Button? = findViewById(btnId)

            btnEle?.setOnClickListener {
                Log.d(TAG, "Num pressed: $num")
                calculator = calculator.numPressed(num.toString())
                updateNewNumberValue()
            }
        }

        val dotEle: Button? = findViewById(R.id.buttonDot)

        dotEle?.setOnClickListener {
            Log.d(TAG, "Dot pressed: .")
            calculator = calculator.dotPressed()
            updateNewNumberValue()
        }
    }

    private fun startOperatorListeners() {
        for (operator in Operator.values()) {
            val btnId = resources.getIdentifier("button$operator", "id", packageName)
            val btnEle: TextView? = findViewById(btnId)

            btnEle?.setOnClickListener {
                Log.d(TAG, "Operator Pressed: $operator")
                calculator = calculator.operatorPressed(operator)
                updateOperatorValue()
                updateResultValue()
                updateNewNumberValue()
            }
        }
    }

    private fun updateOperatorValue() {
        operatorEle?.text = calculator.pendingOperator?.toSymbol()
    }

    private fun updateNewNumberValue() {
        newNumberEle?.text = calculator.pendingNumber
    }

    private fun updateResultValue() {
        resultEle?.text = calculator.sum.toString()
    }
}