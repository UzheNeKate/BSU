package by.pmvs.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    var currentEditText : Int = 0;
    var currentPower = 3;
    private var numericButtons: List<Int> = listOf(
        R.id.btNumber0,
        R.id.btNumber1,
        R.id.btNumber2,
        R.id.btNumber3,
        R.id.btNumber4,
        R.id.btNumber5,
        R.id.btNumber6,
        R.id.btNumber7,
        R.id.btNumber8,
        R.id.btNumber9,
        R.id.btSep,
    )

    private var inputsIds: List<Int> = listOf(
        R.id.etInputA,
        R.id.etInputB,
        R.id.etInputC,
        R.id.etInputD,
        R.id.etInputX,
    )
    private var inputs : MutableList<EditText> = ArrayList<EditText>()
    private var inputsLabelsIds: List<Int> = listOf(
        R.id.tvA,
        R.id.tvB,
        R.id.tvC,
        R.id.tvD,
        R.id.tvX,
    )
    private var inputLabels : MutableList<TextView> = ArrayList<TextView>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tvOperation = findViewById<TextView>(R.id.tvOperation)
        for (id in numericButtons) {
            val bt = findViewById<Button>(id)
            bt.setOnClickListener {
                System.out.println("$currentEditText $inputs")
                inputs[currentEditText].text.append(bt.text)
            }
        }
        for ((i, id) in inputsIds.withIndex()) {
            val et = findViewById<EditText>(id)
            et!!.showSoftInputOnFocus = false
            et.setOnFocusChangeListener { _, _ ->
                currentEditText = i
            }
            inputs.add(et)
        }
        for (id in inputsLabelsIds) {
            val tv = findViewById<TextView>(id)
            inputLabels.add(tv)
        }

        findViewById<Button>(R.id.btEquals).setOnClickListener{
            val values = ArrayList<Double>()
            for (et in inputs) {
                val str = et.text.toString()
                val v = if(str != "") et.text.toString().toDouble() else 0.0
                values.add(v)
            }
            var res = 0.00
            val x = values[values.size - 1]
            for ((i,v) in values.withIndex()) {
                if (i > currentPower) {
                    break;
                }
                val power = currentPower - i
                res += v * x.pow(power)
            }
            tvOperation.text = "y(x)=$res";
        }

        findViewById<Button>(R.id.btMinus).setOnClickListener {
            if (inputs[currentEditText].text.isEmpty()) {
                inputs[currentEditText].text.append("-")
            }
        }
        findViewById<Button>(R.id.btClear).setOnClickListener{clearInputs()}
        findViewById<Button>(R.id.btDelete).setOnClickListener {
            inputs[currentEditText].setText("")
        }
        findViewById<Button>(R.id.btLine).setOnClickListener { onClick(2) }
        findViewById<Button>(R.id.btSquare).setOnClickListener { onClick(3) }
        findViewById<Button>(R.id.btCube).setOnClickListener { onClick(4) }
    }

    private fun onClick(start : Int) {
        currentPower = start-1
        clearInputs()
        for (i in 0..start) {
            inputs[i].isVisible = true
            inputLabels[i].isVisible = true
        }
        for (i in start until inputLabels.size - 1) {
            inputs[i].isVisible = false
            inputLabels[i].isVisible = false
        }
    }

    private fun clearInputs(){
        for (et in inputs){
            et.setText("")
        }
    }
}