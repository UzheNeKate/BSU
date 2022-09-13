package by.pmvs.lab9task44

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), View.OnClickListener {

    var spinner: Spinner? = null
    private lateinit var db: SQLiteDatabase
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinner = findViewById(R.id.spinner)
        val adapter: ArrayAdapter<String>
        val list: MutableList<String>

        list = arrayListOf("name", "price", "count")
        adapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_spinner_item, list
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.adapter = adapter

        dbHelper = DBHelper(this, 1)
        fillDb(dbHelper.writableDatabase)
    }

    private fun fillDb(db: SQLiteDatabase?) {
        db!!.delete("items", null, null)
        db!!.delete("employees", null, null)
        val cv = ContentValues()
        val name = arrayOf(
            "Pen", "Pencil", "Sweet",
            "Cake", "Paper"
        )
        val count = intArrayOf(3, 5, 6, 8, 2)
        val saleCount = intArrayOf(3, 3, 4, 0, 1)
        val price = intArrayOf(500, 500, 800, 700, 300)
        for (i in name.indices) {
            cv.put("name", name[i])
            cv.put("count", count[i])
            cv.put("saleCount", saleCount[i])
            cv.put("price", price[i])
            db.insert("items", null, cv)
            cv.clear()
        }
        val names = arrayOf(
            "Petrov", "Protasenya", "Bulakh",
            "Gorbach", "Gushchenya"
        )
        val salary = intArrayOf(3500, 1000, 1850, 2400, 2700)
        val proceeds = intArrayOf(300, 300, 400, 120, 90)
        for (i in names.indices) {
            cv.put("name", names[i])
            cv.put("salary", salary[i])
            cv.put("proceeds", proceeds[i])
            db.insert("employees", null, cv)
            cv.clear()
        }
    }


    override fun onClick(v: View) {
        db = dbHelper.writableDatabase

        when (v.id) {
            R.id.btnGroup -> {
                var selected = spinner!!.selectedItem.toString()
                val c: Cursor = db.rawQuery(
                    "select $selected, count(*) as count from items group by $selected",
                null)
                val data: ArrayList<String> = ArrayList()
                if (c.moveToFirst()) {
                    val selColIndex = c.getColumnIndex(selected)
                    val cntColIndex = c.getColumnIndex("count")
                    do {
                        data.add(c.getString(selColIndex))
                        data.add(c.getInt(cntColIndex).toString())
                    } while (c.moveToNext())
                }
                c.close()

                data.add(selected)
                val intent = Intent(this, ShowGroupActivity::class.java)
                intent.putExtra("data", data)
                startActivity(intent)
            }
            R.id.btnCnt -> {
                val c: Cursor = db.rawQuery("select count(*) from items", null)
                c.moveToFirst()
                val count: Int = c.getInt(0)
                c.close()
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle("Aggregate function result")
                builder.setMessage("Number of rows in table item = $count")
                val alert = builder.create()
                alert.show()
            }
            R.id.btnAvg -> {
                val c: Cursor = db.rawQuery("select avg(price) from items", null)
                c.moveToFirst()
                val avg: Double = c.getDouble(0)
                c.close()
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle("Aggregate function result")
                builder.setMessage("Average price of items = $avg")
                val alert = builder.create()
                alert.show()
            }
            R.id.btnMin -> {
                val c: Cursor = db.rawQuery("select min(price) from items", null)
                c.moveToFirst()
                val min: Int = c.getInt(0)
                c.close()
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle("Aggregate function result")
                builder.setMessage("Minimal price in table item = $min")
                val alert = builder.create()
                alert.show()
            }
            R.id.btnMax -> {
                val c: Cursor = db.rawQuery("select max(price) from items", null)
                c.moveToFirst()
                val max: Int = c.getInt(0)
                c.close()
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle("Aggregate function result")
                builder.setMessage("Maximal price in table item = $max")
                val alert = builder.create()
                alert.show()
            }
            R.id.btnSum -> {
                val c: Cursor = db.rawQuery(
                    "select sum(price*saleCount) from items",
                    null
                )
                c.moveToFirst()
                val sum: Int = c.getInt(0)
                c.close()
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle("Aggregate function result")
                builder.setMessage("Total price of all items = $sum")
                val alert = builder.create()
                alert.show()
            }
            R.id.btnShow -> {
                val c = db.query(
                    "employees", null, null, null,
                    null, null, null
                )
                val data: ArrayList<String> = ArrayList()
                if (c.moveToFirst()) {
                    val idColIndex = c.getColumnIndex("id")
                    val nameColIndex = c.getColumnIndex("name")
                    val salaryColIndex = c.getColumnIndex("salary")
                    val procColIndex = c.getColumnIndex("proceeds")
                    do {
                        data.add(c.getInt(idColIndex).toString())
                        data.add(c.getString(nameColIndex))
                        data.add(c.getInt(salaryColIndex).toString())
                        data.add(c.getInt(procColIndex).toString())
                    } while (c.moveToNext())
                }
                c.close()

                val intent = Intent(this, ShowEmpsActivity::class.java)
                intent.putExtra("data", data)
                startActivity(intent)
            }
            R.id.btnShowItems -> {
                val c = db.query(
                    "items", null, null, null,
                    null, null, null
                )
                val data: ArrayList<String> = ArrayList()
                if (c.moveToFirst()) {
                    val idColIndex = c.getColumnIndex("id")
                    val nameColIndex = c.getColumnIndex("name")
                    val countColIndex = c.getColumnIndex("count")
                    val saleColIndex = c.getColumnIndex("saleCount")
                    val priceColIndex = c.getColumnIndex("price")
                    do {
                        data.add(c.getInt(idColIndex).toString())
                        data.add(c.getString(nameColIndex))
                        data.add(c.getInt(countColIndex).toString())
                        data.add(c.getInt(saleColIndex).toString())
                        data.add(c.getInt(priceColIndex).toString())
                    } while (c.moveToNext())
                }
                c.close()

                val intent = Intent(this, ShowItemsActivity::class.java)
                intent.putExtra("data", data)
                startActivity(intent)
            }
        }
        dbHelper.close()
    }

    fun onOrder(v: View){
        db = dbHelper.writableDatabase
        when(v.id){
            R.id.EmpByName ->{
                val c = db.query(
                    "employees", null, null, null,
                    null, null, "name"
                )
                val data: ArrayList<String> = ArrayList()
                if (c.moveToFirst()) {
                    val idColIndex = c.getColumnIndex("id")
                    val nameColIndex = c.getColumnIndex("name")
                    val salaryColIndex = c.getColumnIndex("salary")
                    val procColIndex = c.getColumnIndex("proceeds")
                    do {
                        data.add(c.getInt(idColIndex).toString())
                        data.add(c.getString(nameColIndex))
                        data.add(c.getInt(salaryColIndex).toString())
                        data.add(c.getInt(procColIndex).toString())
                    } while (c.moveToNext())
                }
                c.close()

                val intent = Intent(this, ShowEmpsActivity::class.java)
                intent.putExtra("data", data)
                startActivity(intent)
            }
            R.id.EmpByProc ->{
                val c = db.query(
                    "employees", null, null, null,
                    null, null, "proceeds"
                )
                val data: ArrayList<String> = ArrayList()
                if (c.moveToFirst()) {
                    val idColIndex = c.getColumnIndex("id")
                    val nameColIndex = c.getColumnIndex("name")
                    val salaryColIndex = c.getColumnIndex("salary")
                    val procColIndex = c.getColumnIndex("proceeds")
                    do {
                        data.add(c.getInt(idColIndex).toString())
                        data.add(c.getString(nameColIndex))
                        data.add(c.getInt(salaryColIndex).toString())
                        data.add(c.getInt(procColIndex).toString())
                    } while (c.moveToNext())
                }
                c.close()

                val intent = Intent(this, ShowEmpsActivity::class.java)
                intent.putExtra("data", data)
                startActivity(intent)
            }
            R.id.itByName ->{
                val c = db.query(
                    "items", null, null, null,
                    null, null, "name"
                )
                val data: ArrayList<String> = ArrayList()
                if (c.moveToFirst()) {
                    val idColIndex = c.getColumnIndex("id")
                    val nameColIndex = c.getColumnIndex("name")
                    val countColIndex = c.getColumnIndex("count")
                    val saleColIndex = c.getColumnIndex("saleCount")
                    val priceColIndex = c.getColumnIndex("price")
                    do {
                        data.add(c.getInt(idColIndex).toString())
                        data.add(c.getString(nameColIndex))
                        data.add(c.getInt(countColIndex).toString())
                        data.add(c.getInt(saleColIndex).toString())
                        data.add(c.getInt(priceColIndex).toString())
                    } while (c.moveToNext())
                }
                c.close()

                val intent = Intent(this, ShowItemsActivity::class.java)
                intent.putExtra("data", data)
                startActivity(intent)
            }
            R.id.itByPrice ->{
                val c = db.query(
                    "items", null, null, null,
                    null, null, "price"
                )
                val data: ArrayList<String> = ArrayList()
                if (c.moveToFirst()) {
                    val idColIndex = c.getColumnIndex("id")
                    val nameColIndex = c.getColumnIndex("name")
                    val countColIndex = c.getColumnIndex("count")
                    val saleColIndex = c.getColumnIndex("saleCount")
                    val priceColIndex = c.getColumnIndex("price")
                    do {
                        data.add(c.getInt(idColIndex).toString())
                        data.add(c.getString(nameColIndex))
                        data.add(c.getInt(countColIndex).toString())
                        data.add(c.getInt(saleColIndex).toString())
                        data.add(c.getInt(priceColIndex).toString())
                    } while (c.moveToNext())
                }
                c.close()

                val intent = Intent(this, ShowItemsActivity::class.java)
                intent.putExtra("data", data)
                startActivity(intent)
            }
        }
        dbHelper.close()
    }

    class DBHelper(context: Context, version: Int) : SQLiteOpenHelper(
        context, "CustomShop", null, version
    ) {
        override fun onCreate(db: SQLiteDatabase) {
            Log.d("myLogs", "--- onCreate database ---")
            db.execSQL(
                "create table if not exists items ("
                        + "id integer primary key autoincrement,"
                        + "name text,"
                        + "count integer,"
                        + "saleCount integer,"
                        + "price integer)"
            )
            db.execSQL(
                "create table if not exists employees ("
                        + "id integer primary key autoincrement,"
                        + "name text,"
                        + "salary integer,"
                        + "proceeds integer)"
            )
        }
        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        }
    }
}