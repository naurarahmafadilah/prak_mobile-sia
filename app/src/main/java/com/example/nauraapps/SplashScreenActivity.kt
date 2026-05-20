package com.example.nauraapps

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        // Menggunakan findViewById(R.id.main), pastikan root layout di activity_splash_screen.xml id-nya "main"
        // Jika ragu, Anda bisa menggantinya dengan View Binding seperti activity lainnya.
        findViewById<android.view.View>(R.id.main)?.let { rootView ->
            ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        // Jalankan logika pengecekan di background
        checkUserSession()
    }

    private fun checkUserSession() {
        lifecycleScope.launch {
            // 1. Simulasi loading/branding selama 2 detik
            delay(2000)

            // 2. Ambil status login dari SharedPreferences (Pastikan nama pref & key sama di seluruh app)
            val sharedPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE)
            val isLogin = sharedPref.getBoolean("is_logged_in", false)

            // 3. Tentukan arah navigasi (DIUBAH KE BaseActivity)
            val targetActivity = if (isLogin) {
                BaseActivity::class.java  // Mengarah ke BaseActivity yang memuat HomeFragment
            } else {
                AuthActivity::class.java  // Mengarah ke Login
            }

            // 4. Berpindah ke Activity tujuan
            startActivity(Intent(this@SplashScreenActivity, targetActivity))

            // 5. Tutup SplashScreen agar tidak bisa kembali dengan tombol back
            finish()
        }
    }
}