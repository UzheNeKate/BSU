package by.pmvs.lab9task42

import android.os.Bundle
import android.widget.TableLayout
import androidx.appcompat.app.AppCompatActivity
import android.view.Gravity
import android.widget.TextView
import android.graphics.Color
import android.view.View
import android.view.Window
import android.widget.TableRow


class ShowDBActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_show_dbactivity)
        val data = intent.getStringArrayListExtra("data")
        println(data)
        init(data)
    }

    private fun init(data: ArrayList<String>?) {
        val stk = findViewById<View>(R.id.table_main) as TableLayout
        val tbrow0 = TableRow(this)
        val tv0 = TextView(this)
        tv0.text = " ID "
        tv0.setTextColor(Color.WHITE);
        tbrow0.addView(tv0)
        val tv1 = TextView(this)
        tv1.text = " Model "
        tv1.setTextColor(Color.WHITE);
        tbrow0.addView(tv1)
        val tv2 = TextView(this)
        tv2.text = " Manufac "
        tv2.setTextColor(Color.WHITE);
        tbrow0.addView(tv2)
        val tv3 = TextView(this)
        tv3.text = " Year "
        tv3.setTextColor(Color.WHITE);
        tbrow0.addView(tv3)
        val tv4 = TextView(this)
        tv4.text = " Engine "
        tv4.setTextColor(Color.WHITE);
        tbrow0.addView(tv4)
        val tv5 = TextView(this)
        tv5.text = " HP "
        tv5.setTextColor(Color.WHITE);
        tbrow0.addView(tv5)
        val tv6 = TextView(this)
        tv6.text = " Body type "
        tv6.setTextColor(Color.WHITE);
        tbrow0.addView(tv6)
        stk.addView(tbrow0)
        for (i in 0 until data!!.size / 7) {
            val j = i * 7
            val tbrow = TableRow(this)
            val t1v = TextView(this)
            t1v.text = data[j]
            t1v.gravity = Gravity.CENTER
            tbrow.addView(t1v)
            val t2v = TextView(this)
            t2v.text = data[j+1]
            t2v.gravity = Gravity.CENTER
            tbrow.addView(t2v)
            val t3v = TextView(this)
            t3v.text = data[j+2]
            t3v.gravity = Gravity.CENTER
            tbrow.addView(t3v)
            val t4v = TextView(this)
            t4v.text = data[j+3]
            t4v.gravity = Gravity.CENTER
            tbrow.addView(t4v)
            val t5v = TextView(this)
            t5v.text = data[j+4]
            t5v.gravity = Gravity.CENTER
            tbrow.addView(t5v)
            val t6v = TextView(this)
            t6v.text = data[j+5]
            t6v.gravity = Gravity.CENTER
            tbrow.addView(t6v)
            val t7v = TextView(this)
            t7v.text = data[j+6]
            t7v.gravity = Gravity.CENTER
            tbrow.addView(t7v)
            tbrow.setBackgroundColor(Color.WHITE)
            stk.addView(tbrow)
        }
    }
}