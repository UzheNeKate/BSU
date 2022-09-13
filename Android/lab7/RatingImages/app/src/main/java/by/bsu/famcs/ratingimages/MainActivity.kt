package by.bsu.famcs.ratingimages

import android.content.Context
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {
    private fun getResourceId(varName: String?, resourceName: String?, packageName: String?): Int? {
        return try {
            resources.getIdentifier(varName, resourceName, packageName)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private lateinit var btGo: Button
    private lateinit var img: ImageView
    private lateinit var etEnterText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etEnterText = findViewById(R.id.etEnterText)
        img = findViewById(R.id.ivImg)
        btGo = findViewById(R.id.btGo)
        btGo.setOnClickListener { btGoListener(it) }
        findViewById<Button>(R.id.btLike).setOnClickListener {
            Snackbar.make(btGo, resources.getText(R.string.liked_reaction), Snackbar.LENGTH_SHORT)
                .show()
        }

        findViewById<Button>(R.id.btDislike).setOnClickListener {
            Snackbar.make(
                btGo,
                resources.getText(R.string.disliked_reaction),
                Snackbar.LENGTH_SHORT
            ).show()
        }

    }

    private fun btGoListener(it: View) {
        val mbId = getResourceId(etEnterText.text.toString().lowercase(), "drawable", packageName)
        if (mbId != null && mbId != 0) {
            img.setImageResource(mbId)
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        } else {
            val msgStringId = R.string.img_not_found
            Snackbar.make(btGo, resources.getText(msgStringId), Snackbar.LENGTH_SHORT).show()
        }
    }
}