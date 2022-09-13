package com.example.lab9task2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.lifecycle.asLiveData
import androidx.lifecycle.observe
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var userManager: UserManager
    var age = 0
    var fname = ""
    var lname = ""
    var gender = ""
    private lateinit var tv_age: TextView
    private lateinit var tv_fname: TextView
    private lateinit var tv_lname: TextView
    private lateinit var tv_gender: TextView
    private lateinit var et_age: EditText
    private lateinit var et_fname: EditText
    private lateinit var et_lname: EditText
    private lateinit var btn_save: Button
    private lateinit var switch_gender: SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get reference to our userManager class
        userManager = UserManager(dataStore)

        tv_age = findViewById(R.id.tv_age)
        tv_fname = findViewById(R.id.tv_fname)
        tv_lname = findViewById(R.id.tv_lname)
        tv_gender = findViewById(R.id.tv_gender)
        et_age = findViewById(R.id.et_age)
        et_fname = findViewById(R.id.et_fname)
        et_lname = findViewById(R.id.et_lname)
        btn_save = findViewById(R.id.btn_save)
        switch_gender = findViewById(R.id.switch_gender)

        buttonSave()
        observeData()
    }

    private fun observeData() {
        //Updates age
        userManager.userAgeFlow.asLiveData().observe(this, {
            if (it != null) {
                age = it
                tv_age.text = it.toString()
            }
        })

        //Updates firstname
        userManager.userFirstNameFlow.asLiveData().observe(this, {
            if (it != null) {
                fname = it
                tv_fname.text = it
            }
        })

        //Updates lastname
        userManager.userLastNameFlow.asLiveData().observe(this, {
            if (it != null) {
                lname = it
                tv_lname.text = it
            }
        })

        //Updates gender
        userManager.userGenderFlow.asLiveData().observe(this, {
            if (it != null) {
                gender = if (it) "Male" else "Female"
                tv_gender.text = gender
            }
        })
    }

    private fun buttonSave() {
        //Gets the user input and saves it
        btn_save.setOnClickListener {
            fname = et_fname.text.toString()
            lname = et_lname.text.toString()
            age = et_age.text.toString().toInt()
            val isMale = switch_gender.isChecked

            //Stores the values
            GlobalScope.launch {
                userManager.storeUser(age, fname, lname, isMale)
            }
        }
    }
}