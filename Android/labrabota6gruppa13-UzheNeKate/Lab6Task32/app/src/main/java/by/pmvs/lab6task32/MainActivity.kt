package by.pmvs.lab6task32

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    var tvInfo: TextView? = null
    var etInput: EditText? = null
    var bControl: Button? = null
    var guess = 0
    var gameFinished = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInfo = findViewById<View>(R.id.tvGuess) as TextView
        etInput = findViewById<View>(R.id.editTextNumber) as EditText
        bControl = findViewById<View>(R.id.btnInput) as Button
        guess = (Math.random() * 100).toInt()
        gameFinished = false
    }

    fun onClick(v: View?) {
        var msgId = 0
        var input = -1
        if (!gameFinished) {
            try {
                input = etInput!!.text.toString().toInt()
            } catch (e: Exception) {
                Snackbar.make(bControl!!, resources.getText(R.string.empty), Snackbar.LENGTH_SHORT)
                    .show()
            }
            if (input < 1 || input > 100)
                msgId = R.string.not_valid
            else {
                if (input > guess)
                    msgId = R.string.ahead
                else if (input < guess)
                    msgId = R.string.behind
                else {
                    msgId = R.string.hit
                    bControl!!.text = resources.getString(R.string.play_more)
                    gameFinished = true
                }
            }
            Snackbar.make(bControl!!, resources.getText(msgId), Snackbar.LENGTH_SHORT).show()
        } else {
            guess = (Math.random() * 100).toInt()
            bControl!!.text = resources.getString(R.string.input_value)
            tvInfo!!.text = resources.getString(R.string.try_to_guess)
            gameFinished = false
        }
        etInput!!.setText("")
    }

    fun onExit(view: View?) {
        finish()
    }
}