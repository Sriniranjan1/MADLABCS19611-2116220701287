package com.example.ex_2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ex_2.R.id.buttondot
import com.example.ex_2.ui.theme.Ex_2Theme
import androidx.appcompat.app.AppCompatActivity as AppCompatActivity1

class MainActivity : AppCompatActivity1() {
    var input1:Double=0.0
    var input2:Double=0.0
    var Add:Boolean=false
    var Sub:Boolean=false
    var Mul:Boolean=false
    var Div:Boolean=false
    var Rem:Boolean=false
    var dec:Boolean=false
    var cos:Boolean=false
    var sin:Boolean=false
    var tan:Boolean=false
    var pow:Boolean=false
    var sq:Boolean=false
    var sqrt:Boolean=false
    var log:Boolean=false
    var exp:Boolean=false

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button0=findViewById<Button>(R.id.button0)
        val button1=findViewById<Button>(R.id.button1)
        val button2=findViewById<Button>(R.id.button2)
        val button3=findViewById<Button>(R.id.button3)
        val button4=findViewById<Button>(R.id.button4)
        val button5=findViewById<Button>(R.id.button5)
        val button6=findViewById<Button>(R.id.button6)
        val button7=findViewById<Button>(R.id.button7)
        val button8=findViewById<Button>(R.id.button8)
        val button9=findViewById<Button>(R.id.button9)

        val buttonDot=findViewById<Button>(R.id.buttondot)
        val buttonAdd=findViewById<Button>(R.id.buttonadd)
        val buttonSub=findViewById<Button>(R.id.buttonsub)
        val buttonMul=findViewById<Button>(R.id.buttonmul)
        val buttonDiv=findViewById<Button>(R.id.buttondiv)
        val buttonRem=findViewById<Button>(R.id.buttonrem)
        val buttonCos=findViewById<Button>(R.id.buttoncos)
        val buttonSin=findViewById<Button>(R.id.buttonsin)
        val buttonTan=findViewById<Button>(R.id.buttontan)
        val buttonSqrt=findViewById<Button>(R.id.buttonsqrt)
        val buttonPow=findViewById<Button>(R.id.buttonpow)
        val buttonLog=findViewById<Button>(R.id.buttonlog)
        val buttonExp=findViewById<Button>(R.id.buttonexp)
        val buttonSq=findViewById<Button>(R.id.buttonsq)
        val buttonClr=findViewById<Button>(R.id.buttonclr)
        val buttonEqual=findViewById<Button>(R.id.buttoneql)
        val edtInput=findViewById<EditText>(R.id.input)
        val editDisplay=findViewById<TextView>(R.id.display)

        button1.setOnClickListener (){
            edtInput.setText("${edtInput.text}1")
        }
        button2.setOnClickListener (){
            edtInput.setText("${edtInput.text}2")
        }
        button3.setOnClickListener (){
            edtInput.setText("${edtInput.text}3")
        }
        button4.setOnClickListener (){
            edtInput.setText("${edtInput.text}4")
        }
        button5.setOnClickListener (){
            edtInput.setText("${edtInput.text}5")
        }
        button6.setOnClickListener (){
            edtInput.setText("${edtInput.text}6")
        }
        button7.setOnClickListener (){
            edtInput.setText("${edtInput.text}7")
        }
        button8.setOnClickListener (){
            edtInput.setText("${edtInput.text}8")
        }
        button9.setOnClickListener (){
            edtInput.setText("${edtInput.text}9")
        }
        button0.setOnClickListener (){
            edtInput.setText("${edtInput.text}0")
        }
        buttonAdd.setOnClickListener {
            if (edtInput.text.isNotEmpty()) {
                input1 = edtInput.text.toString().toDouble()
                Add = true
                dec = false
                edtInput.setText("")
            }
        }
        buttonSub.setOnClickListener {
            if (edtInput.text.isNotEmpty()) {
                input1 = edtInput.text.toString().toDouble()
                Sub = true
                dec = false
                edtInput.setText("")
            }
        }
        buttonMul.setOnClickListener {
            if (edtInput.text.isNotEmpty()) {
                input1 = edtInput.text.toString().toDouble()
                Mul = true
                dec = false
                edtInput.setText("")
            }
        }

        buttonDiv.setOnClickListener {
            if (edtInput.text.isNotEmpty()) {
                input1 = edtInput.text.toString().toDouble()
                Div = true
                dec = false
                edtInput.setText("")
            }
        }

        buttonRem.setOnClickListener {
            if (edtInput.text.isNotEmpty()) {
                input1 = edtInput.text.toString().toDouble()
                Rem = true
                dec = false
                edtInput.setText("")
            }
        }
        buttonCos.setOnClickListener {
            if (edtInput.text.isNotEmpty()) {
                input1 = edtInput.text.toString().toDouble()
                cos = true
                dec = false
                edtInput.setText("")
            }
        }
        buttonSin.setOnClickListener {
            if (edtInput.text.isNotEmpty()) {
                input1 = edtInput.text.toString().toDouble()
                sin = true
                dec = false
                edtInput.setText("")
            }
        }
        buttonTan.setOnClickListener {
            if (edtInput.text.isNotEmpty()) {
                input1 = edtInput.text.toString().toDouble()
                tan = true
                dec = false
                edtInput.setText("")
            }
        }
        buttonPow.setOnClickListener {
            if (edtInput.text.isNotEmpty()) {
                input1 = edtInput.text.toString().toDouble()
                pow = true
                dec = false
                edtInput.setText("")
            }
        }
        buttonSq.setOnClickListener {
            if (edtInput.text.isNotEmpty()) {
                input1 = edtInput.text.toString().toDouble()
                sq = true
                dec = false
                edtInput.setText("")
            }
        }
        buttonSqrt.setOnClickListener {
            if (edtInput.text.isNotEmpty()) {
                input1 = edtInput.text.toString().toDouble()
                sqrt = true
                dec = false
                edtInput.setText("")
            }
        }
        buttonLog.setOnClickListener {
            if (edtInput.text.isNotEmpty()) {
                input1 = edtInput.text.toString().toDouble()
                log = true
                dec = false
                edtInput.setText("")
            }
        }
        buttonExp.setOnClickListener {
            if (edtInput.text.isNotEmpty()) {
                input1 = edtInput.text.toString().toDouble()
                exp = true
                dec = false
                edtInput.setText("")
            }
        }
        buttonDot.setOnClickListener {
            if (dec) {
                //do nothing or you can show the error
            } else {
                edtInput.setText("${edtInput.text}.")
                dec = true
            }
        }
        buttonClr.setOnClickListener() {
            edtInput.setText("")
            editDisplay.setText("")
            input1 = 0.0
            input2 = 0.0
        }
        buttonEqual.setOnClickListener(){
            if (Add || Sub || Mul || Div || Rem || pow) {
                input2 = "${edtInput.text}".toDouble()
            }
            if (Add) {
                edtInput.setText("${input1.toInt()}+${input2.toInt()}")
                val radd: Double = input1 + input2
                editDisplay.setText("${radd.toInt()}")
                Add = false
            }
            if (Sub) {
                edtInput.setText("${input1.toInt()}-${input2.toInt()}")
                val rsub: Double = input1 - input2
                editDisplay.setText("${rsub.toInt()}")
                Sub = false
            }
            if (Mul) {
                edtInput.setText("${input1.toInt()}*${input2.toInt()}")
                val rmul: Double = input1 * input2
                editDisplay.setText("${rmul.toInt()}")
                Mul = false
            }
            if (Div) {
                edtInput.setText("${input1.toInt()}/${input2.toInt()}")
                val rdiv: Double = input1 / input2
                editDisplay.setText("${rdiv}")
                Div = false
            }
            if (Rem) {
                edtInput.setText("${input1.toInt()}%${input2.toInt()}")
                val rrem: Double = input1 % input2
                editDisplay.setText("${rrem.toInt()}")
                Rem = false
            }
            if (cos) {
                edtInput.setText("cos(${input1.toInt()})")
                val ceql: Double = Math.cos(Math.toRadians(input1))
                editDisplay.setText("${ceql}")
                cos = false
            }
            if (sin) {
                edtInput.setText("sin(${input1.toInt()})")
                val seql: Double = Math.sin(Math.toRadians(input1))
                editDisplay.setText("${seql}")
                sin = false
            }
            if (tan) {
                edtInput.setText("tan(${input1.toInt()})")
                val teql: Double = Math.tan(Math.toRadians(input1))
                editDisplay.setText("${teql}")
                tan = false
            }
            if (sqrt) {
                edtInput.setText("sqrt(${input1.toInt()})")
                val sqrteql: Double = Math.sqrt(input1)
                editDisplay.setText("${sqrteql}")
                sqrt = false
            }
            if (sq) {
                edtInput.setText("${input1.toInt()}^2")
                val sqeql: Double = Math.pow(input1, 2.0)
                editDisplay.setText("${sqeql.toInt()}")
                sq = false
            }
            if (pow) {
                edtInput.setText("${input1.toInt()}^${input2.toInt()}")
                val peql: Double = Math.pow(input1, input2)
                editDisplay.setText("${peql.toInt()}")
                pow = false
            }
            if (log) {
                edtInput.setText("log(${input1.toInt()})")
                val lgeql: Double = Math.log10(input1)
                editDisplay.setText("${lgeql}")
                log = false
            }
            if (exp) {
                edtInput.setText("e^${input1.toInt()}")
                val expeql: Double = Math.exp(input1)
                editDisplay.setText("${expeql}")
                exp = false
            }
        }

    }
}