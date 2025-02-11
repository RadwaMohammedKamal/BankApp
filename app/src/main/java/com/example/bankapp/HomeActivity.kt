package com.example.bankapp

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.LeadingMarginSpan
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import com.example.bankapp.databinding.ActivityHomeBinding
import com.example.mybank.AccountsFragment
import com.example.mybank.AddaccountsFragment
import com.example.mybank.NewsFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar) // ربط الـ Toolbar كـ ActionBar

        // إضافة مسافة أعلى الـ Toolbar لجعل الأيقونة مرتفعة قليلاً
        binding.toolbar.updatePadding(top = 40) // رفع محتوى الـ AppBar بمقدار 40 بكسل

        if (savedInstanceState == null) {
            replaceFragment(AccountsFragment(), "Accounts") // تعيين أول شاشة واسمها في الـ AppBar
        }

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.account -> {
                    replaceFragment(AccountsFragment(), "Accounts")
                    true
                }
                R.id.add -> {
                    replaceFragment(AddaccountsFragment(), "Add Account")
                    true
                }
                R.id.news -> {
                    replaceFragment(NewsFragment(), "News")
                    true
                }
                else -> false
            }
        }

        // إزالة اللون من شريط الحالة
        window.decorView.systemUiVisibility = (
                android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                )
        window.statusBarColor = android.graphics.Color.TRANSPARENT
    }

    // إضافة زر تسجيل الخروج في الـ Toolbar بدون `toolbar_menu.xml`
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add("")?.apply {
            setIcon(android.R.drawable.ic_lock_power_off) // أيقونة القفل (يمكنك استبدالها بـ logout.svg)
            setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)

            // إضافة مسافة إلى النص
            val titleWithSpace = SpannableString("   Logout") // ثلاث مسافات قبل النص
            titleWithSpace.setSpan(LeadingMarginSpan.Standard(40, 40), 0, titleWithSpace.length, 0)
            title = titleWithSpace

            // عند الضغط على زر Logout، يتم الانتقال إلى شاشة تسجيل الدخول
            setOnMenuItemClickListener {
                Toast.makeText(this@HomeActivity, "Logging Out...", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@HomeActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // حذف الـ BackStack
                startActivity(intent)
                true
            }
        }
        return true
    }

    private fun replaceFragment(fragment: Fragment, title: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
        supportActionBar?.title = title // تحديث عنوان الـ Toolbar
    }
}





//package com.example.bankapp
//
//import android.os.Bundle
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.content.ContextCompat
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import androidx.fragment.app.Fragment
//import com.example.bankapp.databinding.ActivityHomeBinding
//import com.example.bankapp.databinding.ActivityMainBinding
//import com.example.mybank.AccountsFragment
//import com.example.mybank.AddaccountsFragment
//import com.example.mybank.NewsFragment
//import com.google.android.material.bottomnavigation.BottomNavigationView
//
//
//class HomeActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityHomeBinding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityHomeBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        if (savedInstanceState == null) {
//            replaceFragment(AccountsFragment())
//        }
//
//
//        binding.bottomNavigationView.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.account -> {
//                    replaceFragment(AccountsFragment())
//                    true
//                }
//                R.id.add -> {
//                    replaceFragment(AddaccountsFragment())
//                    true
//                }
//                R.id.news -> {
//                    replaceFragment(NewsFragment())
//                    true
//                }
//                else -> false
//            }
//        }
//        window.decorView.systemUiVisibility = (
//                android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        or android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                )
//        window.statusBarColor = android.graphics.Color.TRANSPARENT
////        enableEdgeToEdge()
////        //setContentView(R.layout.activity_home)
////        window.statusBarColor = ContextCompat.getColor(this, R.color.default_white)
////        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
////            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
////            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
////            insets
////        }
//    }
//
//    private fun replaceFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.frame_layout, fragment)
//            .commit()
//    }
//}