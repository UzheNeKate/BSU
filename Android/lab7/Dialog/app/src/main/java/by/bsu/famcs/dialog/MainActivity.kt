package by.bsu.famcs.dialog

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : Activity(), View.OnClickListener {
    private var bgButton: Button? = null
    var relativeLayout: ConstraintLayout? = null
    var context: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bgButton = findViewById<View>(R.id.background_button) as Button
        bgButton!!.setOnClickListener(this)
        context = this@MainActivity
        relativeLayout = findViewById<View>(R.id.layout) as ConstraintLayout
    }

    override fun onClick(v: View) {
        val items = arrayOf(
            getText(R.string.red),
            getText(R.string.yellow), getText(R.string.green)
        )
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.message)
        builder.setItems(items) { dialog, item ->
            when (item) {
                0 -> {
                    relativeLayout!!.setBackgroundResource(R.color.red)
                    Toast.makeText(context, R.string.red, Toast.LENGTH_LONG).show()
                }
                1 -> {
                    relativeLayout!!.setBackgroundResource(R.color.yellow)
                    Toast.makeText(
                        context, R.string.yellow,
                        Toast.LENGTH_LONG
                    ).show()
                }
                2 -> {
                    relativeLayout!!.setBackgroundResource(R.color.green)
                    Toast.makeText(context, R.string.green, Toast.LENGTH_LONG).show()
                }
            }
        }
        val alert = builder.create()
        alert.show()
    }
}