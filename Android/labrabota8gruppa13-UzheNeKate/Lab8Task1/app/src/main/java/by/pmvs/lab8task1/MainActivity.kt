package by.pmvs.lab8task1

import android.content.res.Resources
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat


class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener,
GestureDetector.OnDoubleTapListener {

    var mDetector: GestureDetectorCompat? = null
    var imgView: ImageView? = null
    private var curImg = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imgView = findViewById(R.id.imageView)
        mDetector = GestureDetectorCompat(this, this)
        mDetector!!.setOnDoubleTapListener(this)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        this.mDetector!!.onTouchEvent(event)
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event)
    }

    override fun onLongPress(event: MotionEvent) {
        val rotateAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim)
        imgView!!.startAnimation(rotateAnimation)
    }

    override fun onScroll(
        e1: MotionEvent, e2: MotionEvent, distanceX: Float,
        distanceY: Float
    ): Boolean {
        val alphaAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim)
        imgView!!.startAnimation(alphaAnimation)
        return true
    }

    override fun onDoubleTap(event: MotionEvent): Boolean {
        val scaleAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.scale_anim)
        imgView!!.startAnimation(scaleAnimation)
        return true
    }

    override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
        val res: Resources = resources
        val mDrawableName = "img$curImg"
        val resID: Int = res.getIdentifier(mDrawableName, "drawable", packageName)
        imgView!!.setImageResource(resID)
        curImg %= 5
        curImg++
        return true
    }

    override fun onDoubleTapEvent(event: MotionEvent): Boolean {
        return true
    }

    override fun onShowPress(event: MotionEvent) {

    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        return true
    }

    override fun onDown(event: MotionEvent): Boolean {
        return false
    }

    override fun onFling(
        event1: MotionEvent, event2: MotionEvent,
        velocityX: Float, velocityY: Float
    ): Boolean {
        return true
    }
}
