package by.pmvs.lab9task44

import android.os.Bundle
import android.widget.TableLayout
import androidx.appcompat.app.AppCompatActivity
import android.view.Gravity
import android.widget.TextView
import android.graphics.Color
import android.view.View
import android.view.Window
import android.widget.TableRow


class ShowItemsActivity : AppCompatActivity() {

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
        tv1.text = " Name "
        tv1.setTextColor(Color.WHITE);
        tbrow0.addView(tv1)
        val tv2 = TextView(this)
        tv2.text = " Count "
        tv2.setTextColor(Color.WHITE);
        tbrow0.addView(tv2)
        val tv3 = TextView(this)
        tv3.text = " SaleCount "
        tv3.setTextColor(Color.WHITE);
        tbrow0.addView(tv3)
        val tv4 = TextView(this)
        tv4.text = " Price "
        tv4.setTextColor(Color.WHITE);
        tbrow0.addView(tv4)
        stk.addView(tbrow0)
        for (i in 0 until data!!.size / 5) {
            val j = i * 5
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
            tbrow.setBackgroundColor(Color.WHITE)
            val t5v = TextView(this)
            t5v.text = data[j+4]
            t5v.gravity = Gravity.CENTER
            tbrow.addView(t5v)
            tbrow.setBackgroundColor(Color.WHITE)
            stk.addView(tbrow)
        }
    }
}