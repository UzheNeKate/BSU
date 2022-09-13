package by.pmvs.lab9task42

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.*

class AddActivity : AppCompatActivity() {
    private var btUpd: Button? = null
    var etModel: EditText? = null
    var etManuf: EditText? = null
    var etYear: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        btUpd = findViewById(R.id.btnOk)
        etModel = findViewById(R.id.etModel)
        etManuf = findViewById(R.id.etManuf)
        etYear = findViewById(R.id.etYear)
        val toast = Toast(applicationContext)
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        toast.duration = Toast.LENGTH_LONG
        val data = ArrayList<String>()
        btUpd!!.setOnClickListener {
            if (etModel!!.text.contentEquals("")
                || etManuf!!.text.contentEquals("") || etYear!!.text.contentEquals("")
            ) {
                etModel!!.setText("")
                etManuf!!.setText("")
                etYear!!.setText("")
                toast.setText(R.string.message_invalid)
                toast.show()
            } else {
                data.add(etModel!!.text.toString())
                data.add(etManuf!!.text.toString())
                val year = etYear!!.text.toString().toIntOrNull()
                if (year == null || year < 0 || year > 2022) {
                    toast.setText("Invalid year")
                } else {
                    data.add(year.toString())
                    intent.putStringArrayListExtra("data", data)
                    setResult(2, intent)
                    finish()
                }
            }
        }
    }
}