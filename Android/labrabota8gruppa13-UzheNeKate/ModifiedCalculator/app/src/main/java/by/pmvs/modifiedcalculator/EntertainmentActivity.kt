package by.pmvs.modifiedcalculator

import android.gesture.Gesture
import android.gesture.GestureOverlayView
import android.gesture.Prediction
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.ProgressBar
import java.util.ArrayList

class EntertainmentActivity : AppCompatActivity() {
    var count = 0
    var imgView: ImageView? = null
    var chBRotate: CheckBox? = null
    var chBAlpha: CheckBox? = null
    var chScale: CheckBox? = null
    var btnRun: Button? = null
    var progressBar: ProgressBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entertainment)
        imgView = findViewById<View>(R.id.imageView) as ImageView
        chBRotate = findViewById<View>(R.id.checkBoxRotate) as CheckBox
        chBAlpha = findViewById<View>(R.id.checkBoxAlpha) as CheckBox
        chScale = findViewById<View>(R.id.checkBoxScale) as CheckBox
        btnRun = findViewById<View>(R.id.btnAnimate) as Button
        progressBar = findViewById<View>(R.id.progressBar) as ProgressBar

        val rotateAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim)
        val alphaAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim)
        val scaleAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.scale_anim)
        val translateAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.translate_anim)

        btnRun?.setOnClickListener {
            val animationSet = AnimationSet(true)

            if(count < 10) {
                postProgress(count++)
                animationSet.addAnimation(translateAnimation)

                if (chBRotate?.isChecked!!)
                    animationSet.addAnimation(rotateAnimation)
                if(chBAlpha?.isChecked!!)
                    animationSet.addAnimation(alphaAnimation)
                if(chScale?.isChecked!!)
                    animationSet.addAnimation(scaleAnimation)
                imgView?.startAnimation(animationSet)
            }
            else
                btnRun?.isEnabled = false
        }
    }

    private fun postProgress(progress: Int) {
        progressBar?.progress = progress
        if (progress == 0) {
            progressBar?.secondaryProgress = 0
        } else {
            progressBar?.secondaryProgress = progress + 1
        }
    }
}