package by.pmvs.lab6task32

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity



class LoginActivity : AppCompatActivity() {
    // UI references.
    private var mEmailView: EditText? = null
    private var mPasswordView: EditText? = null
    private var checkBoxRememberMe: CheckBox? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
        // Set up the login form.
        mEmailView = findViewById<View>(R.id.email) as EditText
        mPasswordView = findViewById<View>(R.id.password) as EditText
        mPasswordView!!.setOnEditorActionListener(OnEditorActionListener { textView, id, keyEvent ->
            if (id == R.id.login || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })
        val mEmailSignInButton = findViewById<View>(R.id.email_sign_in_button) as Button
        mEmailSignInButton.setOnClickListener { attemptLogin() }
        checkBoxRememberMe = findViewById<View>(R.id.checkBoxRememberMe) as CheckBox
        //Here we will validate saved preferences
        if (!PrefManager(this).isUserLogedOut()) {
            //user's email and password both are saved in preferences
            startHomeActivity()
        }
    }

    private fun attemptLogin() {

        // Reset errors.
        mEmailView!!.error = null
        mPasswordView!!.error = null

        // Store values at the time of the login attempt.
        val email = mEmailView!!.text.toString()
        val password = mPasswordView!!.text.toString()
        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView!!.error = getString(R.string.error_invalid_password)
            focusView = mPasswordView
            cancel = true
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView!!.error = getString(R.string.error_field_required)
            focusView = mEmailView
            cancel = true
        } else if (!isEmailValid(email)) {
            mEmailView!!.error = getString(R.string.error_invalid_email)
            focusView = mEmailView
            cancel = true
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView!!.requestFocus()
        } else {
            // save data in local shared preferences
            if (checkBoxRememberMe!!.isChecked) saveLoginDetails(email, password)
            startHomeActivity()
        }
    }

    private fun startHomeActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun saveLoginDetails(email: String, password: String) {
        PrefManager(this).saveLoginDetails(email, password)
    }

    private fun isEmailValid(email: String): Boolean {
        return email.contains("@")
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 4
    }
}