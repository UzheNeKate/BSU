package by.pmvs.lab5task4kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setContentView(R.layout.activity_main)
        val button = findViewById<View>(R.id.btnSubscribe)
        button.setOnClickListener { view: View? ->
            Snackbar.make(
                view!!, "The link is under the photo", Snackbar.LENGTH_LONG
            )
                .setAction("Action", null).show()
        }
    }
}