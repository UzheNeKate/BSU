package by.pmvs.lab9task42

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.*

class AddActivity : AppCompatActivity() {
    private var btUpd: Button? = null
    private var etModel: EditText? = null
    private var etManuf: EditText? = null
    private var etYear: EditText? = null
    var etEng: EditText? = null
    var etHp: EditText? = null
    var etBT: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        btUpd = findViewById(R.id.btnOk)
        etModel = findViewById(R.id.etModel)
        etManuf = findViewById(R.id.etManuf)
        etYear = findViewById(R.id.etYear)
        etEng = findViewById(R.id.etEngCap)
        etHp = findViewById(R.id.etHP)
        etBT = findViewById(R.id.etBT)
        val toast = Toast(applicationContext)
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        toast.duration = Toast.LENGTH_LONG
        val data = ArrayList<String>()
        btUpd!!.setOnClickListener {
            if (etModel!!.text.contentEquals("")
                || etManuf!!.text.contentEquals("") || etYear!!.text.contentEquals("")
                || etEng!!.text.contentEquals("")
                || etHp!!.text.contentEquals("") || etBT!!.text.contentEquals("")
            ) {
                etModel!!.setText("")
                etManuf!!.setText("")
                etYear!!.setText("")
                etEng!!.setText("")
                etHp!!.setText("")
                etBT!!.setText("")
                toast.setText(R.string.message_invalid)
                toast.show()
            } else {
                data.add(etModel!!.text.toString())
                data.add(etManuf!!.text.toString())
                val year = etYear!!.text.toString().toIntOrNull()
                if (year == null || year < 0 || year > 2022) {
                    toast.setText("Invalid year")
                    etModel!!.setText("")
                } else {
                    data.add(year.toString())
                    val engCap = etEng!!.text.toString().toIntOrNull()
                    if(engCap != null)
                        data.add(engCap.toString())
                    else data.add("")
                    val hp = etHp!!.text.toString().toIntOrNull()
                    if(hp != null)
                        data.add(hp.toString())
                    else data.add("")
                    data.add(etBT!!.text.toString())
                    intent.putStringArrayListExtra("data", data)
                    setResult(2, intent)
                    finish()
                }
            }
        }
    }
}