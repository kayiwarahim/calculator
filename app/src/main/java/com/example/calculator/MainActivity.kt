package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var inputTextView: TextView
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputTextView = findViewById(R.id.inputTextView)
        resultTextView = findViewById(R.id.resultTextView)

        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5,
            R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDot,
            R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide,
            R.id.btnOpenBracket, R.id.btnCloseBracket
        )

        buttons.forEach { id ->
            findViewById<Button>(id).setOnClickListener { button ->
                val text = (button as Button).text.toString()
                if (inputTextView.text.toString() == "0") {
                    inputTextView.text = text
                } else {
                    inputTextView.append(text)
                }
            }
        }

        findViewById<Button>(R.id.btnEquals).setOnClickListener {
            val expression = inputTextView.text.toString()
                .replace('×', '*')
                .replace('÷', '/')

            try {
                val result = ExpressionBuilder(expression).build().evaluate()
                resultTextView.text = result.toString()
            } catch (e: Exception) {
                resultTextView.text = "Error"
            }
        }

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            inputTextView.text = "0"
            resultTextView.text = ""
        }

        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            val text = inputTextView.text.toString()
            if (text.length > 1) {
                inputTextView.text = text.dropLast(1)
            } else {
                inputTextView.text = "0"
            }
        }
    }
}
