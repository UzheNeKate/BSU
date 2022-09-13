package by.pmvs.lab6task32

import android.app.AlertDialog
import android.gesture.*
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import java.util.ArrayList


class MainActivity : AppCompatActivity(), GestureOverlayView.OnGesturePerformedListener {

    var alert: AlertDialog? = null
    var tvInfo: TextView? = null
    var etInput: EditText? = null
    var bControl: Button? = null
    var pbAttempts: ProgressBar? = null
    private var tvAttempts: TextView? = null
    var imageView: ImageView? = null
    private var guess = 0
    var gameFinished = false
    private var count = 0

    var gLib: GestureLibrary? = null
    var gestures: GestureOverlayView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pbAttempts = findViewById<View>(R.id.prBarAttempts) as ProgressBar
        tvAttempts = findViewById<View>(R.id.tvAttempts) as TextView
        tvInfo = findViewById<View>(R.id.tvGuess) as TextView
        etInput = findViewById<View>(R.id.editTextNumber) as EditText
        bControl = findViewById<View>(R.id.btnInput) as Button
        guess = (Math.random() * 100).toInt()
        gameFinished = false

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.hit)
        builder.setMessage(R.string.congratulations)
        builder.setView(R.layout.dialog_view)
        alert = builder.create()

        //Загрузка жестов (gestures) из res/raw/gestures
        gLib = GestureLibraries.fromRawResource(this, R.raw.gesture)
        if (!gLib!!.load()) {
            //Если жесты не загружены, то выход из приложения
            finish()
        }
        gestures = findViewById<View>(R.id.gov1) as GestureOverlayView
        gestures!!.addOnGesturePerformedListener(this)
    }

    fun onClick(v : View?) {
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
                count++
                postProgress(count)
                if (input > guess)
                    msgId = R.string.ahead
                else if (input < guess)
                    msgId = R.string.behind
                else {
                    msgId = R.string.hit
                    bControl!!.text = resources.getString(R.string.play_more)
                    gameFinished = true
                    count = 0

                    val rotateAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim)
                    val alphaAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim)
                    val scaleAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.scale_anim)
                    val translateAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.translate_anim)

                    alert?.show()
                    imageView = alert?.findViewById<View>(R.id.imageView) as ImageView
                    val animationSet = AnimationSet(true)
                    animationSet.addAnimation(translateAnimation)
                    animationSet.addAnimation(alphaAnimation)
                    animationSet.addAnimation(scaleAnimation)
                    animationSet.addAnimation(rotateAnimation)
                    imageView!!.startAnimation(animationSet)
                    alert?.window?.setLayout(1000, 1100)
                }
            }
            Snackbar.make(bControl!!, resources.getText(msgId), Snackbar.LENGTH_SHORT).show()
        } else {
            guess = (Math.random() * 100).toInt()
            bControl!!.text = resources.getString(R.string.input_value)
            tvInfo!!.text = resources.getString(R.string.try_to_guess)
            gameFinished = false
            postProgress(count)
        }
        etInput!!.setText("")
    }

    fun onExit() {
        finish()
    }

    private fun postProgress(progress: Int) {
        val strProgress = "$progress"
        pbAttempts?.progress = progress
        if (progress == 0) {
            pbAttempts?.secondaryProgress = 0
        } else {
            pbAttempts?.secondaryProgress = progress + 1
        }
        tvAttempts?.text = strProgress
    }

    override fun onGesturePerformed(overlay: GestureOverlayView?, gesture: Gesture?) {
        val predictions: ArrayList<Prediction> = gLib!!.recognize(gesture)
        if (predictions.size > 0) {
            //если загружен хотя бы один жест из gestures
            val prediction = predictions[0]
            if (prediction.score > 1.0) {
                when (prediction.name) {
                    "one" -> etInput!!.append("1")
                    "two" -> etInput!!.append("2")
                    "three" -> etInput!!.append("3")
                    "four" -> etInput!!.append("4")
                    "five" -> etInput!!.append("5")
                    "six" -> etInput!!.append("6")
                    "seven" -> etInput!!.append("7")
                    "eight" -> etInput!!.append("8")
                    "nine" -> etInput!!.append("9")
                    "zero" -> etInput!!.append("0")
                    "stop input" -> onClick(findViewById(R.id.btnInput))
                }
            }
        }
    }
}