package by.pmvs.lab9task42

import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import android.widget.ArrayAdapter


class UpdateActivity : AppCompatActivity() {

    private var selected: String? = null
    private var btUpd: Button? = null
    var etModel: EditText? = null
    var etManuf: EditText? = null
    var etYear: EditText? = null
    private var etWhere: EditText? = null
    private var spWhere: Spinner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        btUpd = findViewById(R.id.btnOk)
        etModel = findViewById(R.id.etModel)
        etManuf = findViewById(R.id.etManuf)
        etYear = findViewById(R.id.etYear)
        etWhere = findViewById(R.id.etWhere)
        spWhere = findViewById(R.id.where)
        val adapter: ArrayAdapter<String>
        val list: MutableList<String>

        list = arrayListOf("id", "model", "manufacturer", "year")
        adapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_spinner_item, list
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spWhere!!.adapter = adapter
        val toast = Toast(applicationContext)
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        toast.duration = Toast.LENGTH_LONG
        val data = ArrayList<String>()
        btUpd!!.setOnClickListener {
            if (etModel!!.text.contentEquals("")
                || etManuf!!.text.contentEquals("") || etYear!!.text.contentEquals("")
                || etWhere!!.text.contentEquals("")
            ) {
                etModel!!.setText("")
                etManuf!!.setText("")
                etYear!!.setText("")
                etWhere!!.setText("")
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
                    selected = spWhere!!.selectedItem.toString()
                    when (selected) {
                        "id" -> {
                            val id = etWhere!!.text.toString().toIntOrNull()
                            if (id == null)
                                toast.setText("Invalid id")
                            else {
                                data.add("id = ?")
                                data.add(etWhere!!.text.toString())
                            }
                        }
                        "model" -> {
                            data.add("model = ?")
                            data.add(etWhere!!.text.toString())
                        }
                        "manufacturer" -> {
                            data.add("manufacturer = ?")
                            data.add(etWhere!!.text.toString())
                        }
                        "year" -> {
                            val year = etWhere!!.text.toString().toIntOrNull()
                            if (year == null || year < 0 || year > 2022) {
                                toast.setText("Invalid year")
                            } else {
                                data.add("year = ?")
                                data.add(etWhere!!.text.toString())
                            }
                        }
                    }
                    intent.putStringArrayListExtra("data", data)
                    setResult(1, intent)
                    finish()
                }
            }
        }
    }
}