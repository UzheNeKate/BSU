package by.pmvs.lab9task42

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent


class MainActivity : AppCompatActivity(), View.OnClickListener {

    val LOG_TAG = "myLogs"
    var btnAdd: Button? = null
    var btnRead: Button? = null
    var btnClear: Button? = null
    private lateinit var db: SQLiteDatabase
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAdd = findViewById(R.id.btnAdd)
        btnAdd!!.setOnClickListener(this)
        btnRead = findViewById(R.id.btnShow)
        btnRead!!.setOnClickListener(this)
        btnClear = findViewById(R.id.btnUpdate)
        btnClear!!.setOnClickListener(this)

        dbHelper = DBHelper(this)
        fillDb(dbHelper.writableDatabase)
    }

    private fun fillDb(db: SQLiteDatabase?) {
        db!!.delete("cars", null, null)
        val cv = ContentValues()
        cv.put("model", "Mercedes-Benz EQC")
        cv.put("manufacturer", "Germany")
        cv.put("year", 2000)
        db.insert("cars", null, cv)
        cv.clear()
        cv.put("model", "Skoda Octavia")
        cv.put("manufacturer", "Poland")
        cv.put("year", 1999)
        db.insert("cars", null, cv)
        cv.clear()
        cv.put("model", "Skoda Yeti")
        cv.put("manufacturer", "Switzerland")
        cv.put("year", 2015)
        db.insert("cars", null, cv)
        cv.clear()
        cv.put("model", "Opel Mokka Base Spec by X-Tomi")
        cv.put("manufacturer", "Germany")
        cv.put("year", 2020)
        db.insert("cars", null, cv)
        cv.clear()
        cv.put("model", "Volkswagen Golf Alltrack")
        cv.put("manufacturer", "Germany")
        cv.put("year", 2020)
        db.insert("cars", null, cv)
    }


    override fun onClick(v: View) {

        db = dbHelper.writableDatabase

        when (v.id) {
            R.id.btnAdd -> {
                val intentAdd = Intent(this, AddActivity::class.java)
                startActivityForResult(intentAdd, 2)
//                val data = intent.getStringArrayListExtra("data")
//                val cv = ContentValues()
//                cv.put("ID", data?.get(0))
//                cv.put("model", data?.get(1))
//                cv.put("manufacturer", data?.get(2))
//                cv.put("year", data?.get(3))
//                db.insert("cars", null, cv)
            }
            R.id.btnShow -> {
                Log.d(LOG_TAG, "--- Rows in mytable: ---")
                val c = db.query("cars", null, null, null,
                    null, null, null)
                val data: ArrayList<String> = ArrayList()
                if (c.moveToFirst()) {
                    // определяем номера столбцов по имени в выборке
                    val idColIndex = c.getColumnIndex("id")
                    val modelColIndex = c.getColumnIndex("model")
                    val manufColIndex = c.getColumnIndex("manufacturer")
                    val yearColIndex = c.getColumnIndex("year")
                    do {
                        data.add(c.getInt(idColIndex).toString())
                        data.add(c.getString(modelColIndex))
                        data.add(c.getString(manufColIndex))
                        data.add(c.getInt(yearColIndex).toString())
                    } while (c.moveToNext())
                } else
                    Log.d(LOG_TAG, "0 rows")
                c.close()

                val intent = Intent(this, ShowDBActivity::class.java)
                intent.putExtra("data", data)
                startActivity(intent)
            }
            R.id.btnUpdate -> {
                val intent = Intent(this, UpdateActivity::class.java)
                startActivityForResult(intent, 1)
            }
        }
        //dbHelper.close()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == 1) {
            val str = data!!.getStringArrayListExtra("data")
            if (str != null) {
                println(data)
                val cv = ContentValues()
                var col = ""
                for (i in 0..str.size - 3) {
                    if (str[i] != null) {
                        when (i) {
                            0 -> col = "model"
                            1 -> col = "manufacturer"
                            2 -> col = "year"
                        }
                        cv.put(col, str[i])
                    }
                }
                db.update("cars", cv, str[3], arrayOf(str[4]))
            }
        }
        if(resultCode == 2){
            val str = data!!.getStringArrayListExtra("data")
            if (str != null) {
                println(data)
                val cv = ContentValues()
                var col = ""
                for (i in 0 until str.size) {
                    if (str[i] != null) {
                        when (i) {
                            0 -> col = "model"
                            1 -> col = "manufacturer"
                            2 -> col = "year"
                        }
                        cv.put(col, str[i])
                    }
                }
                db.insert("cars", null, cv)
            }
        }
    }

    class DBHelper(context: Context) : SQLiteOpenHelper(context, "CarsDB",
        null, 1) {
        override fun onCreate(db: SQLiteDatabase) {
            Log.d("myLogs", "--- onUpdate database ---")
            db.execSQL(
                "create table if not exists cars (" +
                        "id integer primary key autoincrement,"
                        + "model text,"
                        + "manufacturer text,"
                        + "year integer)"
            )

        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        }
    }
}