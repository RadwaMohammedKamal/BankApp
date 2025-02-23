package com.example.bankapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.LeadingMarginSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add("")?.apply {
            // تحميل الأيقونة الأصلية
            val originalBitmap = BitmapFactory.decodeResource(resources, R.drawable.logout)
            // تصغير الأيقونة إلى 50x50 بكسل
            val resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, 60, 60, true)
            // تحويل الـ Bitmap إلى Drawable
            val iconDrawable = BitmapDrawable(resources, resizedBitmap)

            // جعل الأيقونة بيضاء
            iconDrawable.setTint(Color.WHITE)
            setIcon(iconDrawable) // تعيين الأيقونة المصغرة
            setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)

            // ضبط العنوان مع مسافة، جعله Bold، وتغيير لونه للأبيض
            val titleWithSpace = SpannableString("   Logout").apply {
                setSpan(LeadingMarginSpan.Standard(40, 40), 0, length, 0) // إضافة مسافة قبل النص
                setSpan(ForegroundColorSpan(Color.WHITE), 0, length, 0) // تغيير لون النص إلى الأبيض
                setSpan(StyleSpan(Typeface.BOLD), 0, length, 0) // جعل النص Bold
                setSpan(RelativeSizeSpan(1.4f), 0, length, 0) // تكبير الحجم 1.4x
            }
            title = titleWithSpace

            // حدث تسجيل الخروج
            setOnMenuItemClickListener {
                Toast.makeText(this@HomeActivity, "Logging Out...", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@HomeActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
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

        // جعل العنوان أكبر و Bold
        supportActionBar?.apply {
            val spannableTitle = SpannableString(title).apply {
                setSpan(StyleSpan(Typeface.BOLD), 0, title.length, 0) // جعل النص Bold
                setSpan(RelativeSizeSpan(1.2f), 0, title.length, 0) // تكبير الحجم 1.4x
                setSpan(ForegroundColorSpan(Color.WHITE), 0, title.length, 0) // تغيير لون العنوان إلى الأبيض
            }
            this.title = spannableTitle
        }
    }
}