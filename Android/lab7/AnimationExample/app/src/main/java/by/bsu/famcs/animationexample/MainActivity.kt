package by.bsu.famcs.animationexample

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView

class MainActivity : Activity(), View.OnClickListener {
    private var startFrameAnim: Button? = null
    private var startTransformAnim: Button? = null
    private var cancelAnim: Button? = null
    private var animationView: ImageView? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startFrameAnim = findViewById(R.id.frameAnimationStart)
        startTransformAnim = findViewById(R.id.transformAnimationStart)
        cancelAnim = findViewById(R.id.cancelAnimation)
        animationView = findViewById(R.id.animationView)
        startFrameAnim!!.setOnClickListener(this)
        startTransformAnim!!.setOnClickListener(this)
        cancelAnim!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (startFrameAnim == v) {
            animationView!!.setBackgroundResource(R.drawable.frame_anim)
            val animation = animationView!!.background as AnimationDrawable
            animation.start()
        } else if (startTransformAnim == v) {
            animationView!!.setBackgroundResource(R.drawable.wolf2)
            val transformAnimation = AnimationUtils.loadAnimation(this, R.anim.transform_anim)
            animationView!!.startAnimation(transformAnimation)
        } else if (cancelAnim == v) {
            animationView!!.setBackgroundColor(Color.BLACK)
        }
    }
}