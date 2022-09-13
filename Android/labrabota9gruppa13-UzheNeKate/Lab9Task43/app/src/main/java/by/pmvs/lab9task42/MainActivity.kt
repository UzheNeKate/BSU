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

        dbHelper = DBHelper(this, 2)
        fillDb(dbHelper.writableDatabase)
    }

    private fun fillDb(db: SQLiteDatabase?) {
        db!!.delete("cars", null, null)
        val cv = ContentValues()
        val model = arrayOf("Mercedes-Benz", "Skoda Octavia", "Skoda Yeti",
            "Opel Mokka", "Volkswagen Golf")
        val manuf = arrayOf("Germany", "Poland", "Switzerland", "Germany", "Germany")
        val year = intArrayOf(2000, 1999, 2015, 2020, 2020)
        val engineCapacity = intArrayOf(500, 400, 800, 700, 300)
        val bodyType = arrayOf(
            "sedan", "hatchback", "minivan", "compartment", "crossover"
        )
        val horsePower = intArrayOf(15000, 13000, 10000, 8000, 9000)
        for (i in model.indices) {
            cv.put("model", model[i])
            cv.put("manufacturer", manuf[i])
            cv.put("year", year[i])
            cv.put("engineCapacity", engineCapacity[i])
            cv.put("horsepower", horsePower[i])
            cv.put("bodyType", bodyType[i])
            db.insert("cars", null, cv)
            cv.clear()
        }
    }


    override fun onClick(v: View) {

        db = dbHelper.writableDatabase

        when (v.id) {
            R.id.btnAdd -> {
                val intentAdd = Intent(this, AddActivity::class.java)
                startActivityForResult(intentAdd, 2)
            }
            R.id.btnShow -> {
                Log.d(LOG_TAG, "--- Rows in mytable: ---")
                val c = db.query(
                    "cars", null, null, null,
                    null, null, null
                )
                val data: ArrayList<String> = ArrayList()
                if (c.moveToFirst()) {
                    // определяем номера столбцов по имени в выборке
                    val idColIndex = c.getColumnIndex("id")
                    val modelColIndex = c.getColumnIndex("model")
                    val manufColIndex = c.getColumnIndex("manufacturer")
                    val yearColIndex = c.getColumnIndex("year")
                    val engColIndex = c.getColumnIndex("engineCapacity")
                    val horseColIndex = c.getColumnIndex("horsepower")
                    val bodyColIndex = c.getColumnIndex("bodyType")
                    do {
                        data.add(c.getInt(idColIndex).toString())
                        data.add(c.getString(modelColIndex))
                        data.add(c.getString(manufColIndex))
                        data.add(c.getInt(yearColIndex).toString())
                        data.add(c.getInt(engColIndex).toString())
                        data.add(c.getInt(horseColIndex).toString())
                        data.add(c.getString(bodyColIndex))
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
        if (resultCode == 1) {
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
                            3 -> col = "engineCapacity"
                            4 -> col = "horsepower"
                            5 -> col = "bodyType"
                        }
                        cv.put(col, str[i])
                    }
                }
                db.update("cars", cv, str[6], arrayOf(str[7]))
            }
        }
        if (resultCode == 2) {
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
                            3 -> col = "engineCapacity"
                            4 -> col = "horsepower"
                            5 -> col = "bodyType"
                        }
                        cv.put(col, str[i])
                    }
                }
                db.insert("cars", null, cv)
            }
        }
    }

    class DBHelper(context: Context, version: Int) : SQLiteOpenHelper(
        context, "CarsDB", null, version) {
        override fun onCreate(db: SQLiteDatabase) {
            Log.d("myLogs", "--- onCreate database ---")
            db.execSQL(
                "create table if not exists cars ("
                        + "id integer primary key autoincrement,"
                        + "model text,"
                        + "manufacturer text,"
                        + "year integer,"
                        + "engineCapacity integer,"
                        + "horsepower integer,"
                        + "bodyType char)"
            )
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            Log.d("myLogs", " --- onUpgrade database from " + oldVersion
                    + " to " + newVersion + " version --- ")
            if (oldVersion == 1 && newVersion == 2) {

                db.beginTransaction()
                try {
                    val engineCapacity = intArrayOf(500, 400, 800, 700, 300)
                    val bodyType = arrayOf(
                        "sedan", "hatchback", "minivan", "compartment", "crossover"
                    )
                    val horsePower = intArrayOf(15000, 13000, 10000, 8000, 9000)
                    val cv = ContentValues()
                    db.execSQL("alter table cars add column engineCapacity integer;")
                    db.execSQL("alter table cars add column horsepower integer;")
                    db.execSQL("alter table cars add column bodyType char;")
                    for (i in engineCapacity.indices) {
                        cv.clear()
                        cv.put("engineCapacity", engineCapacity[i])
                        cv.put("horsepower", horsePower[i])
                        cv.put("bodyType", bodyType[i])
                        db.update("cars", cv, null, null)
                    }
                    db.execSQL(
                        "create temporary table cars_tmp ("
                                + "id integer primary key autoincrement,"
                                + "model text,"
                                + "manufacturer text,"
                                + "year integer,"
                                + "engineCapacity integer,"
                                + "horsepower integer,"
                                + "bodyType char)"
                    )
                    db.execSQL("insert into cars_tmp select id, model, manufacturer, year, " +
                            "engineCapacity, horsepower, bodyType from cars;")
                    db.execSQL("drop table cars;")
                    db.execSQL(
                        "create table if not exists cars ("
                                + "id integer primary key autoincrement,"
                                + "model text,"
                                + "manufacturer text,"
                                + "year integer,"
                                + "engineCapacity integer,"
                                + "horsepower integer,"
                                + "bodyType char)"
                    )
                    db.execSQL("insert into cars select id, model, manufacturer, year, " +
                            "engineCapacity, horsepower, bodyType from cars_tmp;")
                    db.execSQL("drop table cars_tmp;");
                    db.setTransactionSuccessful()
                } finally {
                    db.endTransaction()
                }

            }

        }
    }
}