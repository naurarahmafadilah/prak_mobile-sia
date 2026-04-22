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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Jalankan logika pengecekan di background
        checkUserSession()
    }

    private fun checkUserSession() {
        lifecycleScope.launch {
            // 1. Simulasi loading/branding (misal: 2 detik)
            delay(2000)

            // 2. Ambil status login dari SharedPreferences
            val sharedPref = getSharedPreferences("UserSession", Context.MODE_PRIVATE)
            val isLogin = sharedPref.getBoolean("isLogin", false)

            // 3. Tentukan arah navigasi
            val targetActivity = if (isLogin) {
                MainActivity::class.java
            } else {
                AuthActivity::class.java
            }

            // 4. Berpindah ke Activity tujuan
            startActivity(Intent(this@SplashScreenActivity, targetActivity))

            // 5. Tutup SplashScreen agar tidak bisa kembali dengan tombol back
            finish()
        }
    }
}