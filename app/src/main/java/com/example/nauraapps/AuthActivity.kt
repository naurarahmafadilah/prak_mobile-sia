package com.example.nauraapps

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Menambahkan pendukung Edge-to-Edge agar serasi

        // 1. DISERAGAMKAN: Menggunakan "login_pref" agar sama dengan Splash & HomeFragment
        val sharedPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("is_logged_in", false)

        // Kondisi jika isLogin bernilai true (User sudah login sebelumnya)
        if (isLogin) {
            // DIUBAH KE BaseActivity: Agar langsung menampilkan Bottom Nav + HomeFragment
            val intent = Intent(this, BaseActivity::class.java)
            startActivity(intent)
            finish()
        }

        setContentView(R.layout.activity_auth)

        // Penanganan window insets jika layout Anda menggunakan id "main"
        findViewById<android.view.View>(R.id.main)?.let { rootView ->
            ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (username == password && username.isNotEmpty()) {
                // 2. DISERAGAMKAN: Menyimpan session menggunakan key "is_logged_in"
                val editor = sharedPref.edit()
                editor.putBoolean("is_logged_in", true)
                editor.putString("username", username)
                editor.apply()

                // DIUBAH KE BaseActivity: Berpindah ke container utama fragment
                val intent = Intent(this, BaseActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Gagal")
                    .setMessage("Silahkan coba lagi")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
    }
}