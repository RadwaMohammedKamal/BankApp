package com.example.bankapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {
    private lateinit var usernameEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var usernameInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        // تغيير لون شريط الحالة
        window.statusBarColor = ContextCompat.getColor(this, R.color.default_white)

        // ربط العناصر بملفات XML
        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        usernameInputLayout = findViewById(R.id.usernameInputLayout)
        passwordInputLayout = findViewById(R.id.passwordInputLayout)
    }

    fun Register(view: View) {
        val username = usernameEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        // التحقق مما إذا كان كلا الحقلين فارغين معًا
        if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password)) {
            usernameInputLayout.error = "Enter your username"
            passwordInputLayout.error = "Enter your password"
            passwordInputLayout.endIconMode = TextInputLayout.END_ICON_NONE
            return
        }

        // التحقق من اسم المستخدم فقط
        if (TextUtils.isEmpty(username)) {
            usernameInputLayout.error = "Enter your username"
            return
        } else {
            usernameInputLayout.error = null
        }

        // التحقق من كلمة المرور فقط
        if (TextUtils.isEmpty(password)) {
            passwordInputLayout.error = "Enter your password"
            passwordInputLayout.endIconMode = TextInputLayout.END_ICON_NONE
            return
        } else {
            passwordInputLayout.error = null
            passwordInputLayout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
        }

        // إذا كانت البيانات صحيحة، الانتقال إلى شاشة تسجيل الدخول
        Toast.makeText(this, "Registered Successfully!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun Login(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}