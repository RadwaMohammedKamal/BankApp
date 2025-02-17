package com.example.bankapp
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var loginButton: MaterialButton
    private lateinit var usernameInputLayout: TextInputLayout
   private lateinit var passwordInputLayout: TextInputLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.statusBarColor = ContextCompat.getColor(this, R.color.default_white)

        // Initialize UI elements using ViewBinding
        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        usernameInputLayout = findViewById(R.id.usernameInputLayout)
        passwordInputLayout = findViewById(R.id.passwordInputLayout)
        // التحقق من الخطأ عند فقدان التركيز على خانة كلمة المرور
        passwordEditText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) { // عند فقدان التركيز، تحقق من وجود خطأ
                val passwordText = passwordEditText.text.toString().trim()

                if (passwordText.isEmpty()) {
                    passwordInputLayout.error = "Enter your password"
                    passwordInputLayout.endIconMode = TextInputLayout.END_ICON_NONE // إخفاء زر إظهار/إخفاء كلمة المرور
                } else {
                    passwordInputLayout.error = null
                    passwordInputLayout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE // إعادة زر إظهار/إخفاء كلمة المرور
                }
            }
        }

        // Handle login button click
        loginButton.setOnClickListener {
            Login()
        }

    }

    private fun Login() {
        val username = usernameEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password)) {
            usernameInputLayout.error = "Enter your username"
            passwordInputLayout.error = "Enter your password"
            passwordInputLayout.endIconMode = TextInputLayout.END_ICON_NONE
            return
        }
        // التحقق من صحة الإدخال
        if (TextUtils.isEmpty(username)) {
            usernameInputLayout.error = "Enter your username"
            // لا نقوم بإخفاء الأيقونة هنا
            return
        } else {
            usernameInputLayout.error = null
        }

        if (TextUtils.isEmpty(password)) {
            passwordInputLayout.error = "Enter your password"
            passwordInputLayout.endIconMode = TextInputLayout.END_ICON_NONE
            return
        } else {
            passwordInputLayout.error = null
            passwordInputLayout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
        }

        // التحقق من بيانات تسجيل الدخول
        if (username == "1" && password == "1") {
          //  Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish() // إغلاق صفحة تسجيل الدخول بعد النجاح
        } else {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
        }
    }


    fun Register(view: View) {
        val intent=Intent(this,RegisterActivity::class.java)
        startActivity(intent)
       // finish()  // Finish LoginActivity to remove it from back stack
    }
}






