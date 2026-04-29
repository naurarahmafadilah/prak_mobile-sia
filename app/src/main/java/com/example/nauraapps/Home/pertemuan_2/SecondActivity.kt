package com.example.nauraapps.Home.pertemuan_2

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nauraapps.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    // Gunakan ViewBinding agar tidak perlu findViewById
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inisialisasi ViewBinding
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Window Insets (Padding untuk System Bars)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- SETUP TOOLBAR ---
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true) // Menampilkan tombol back
            setDisplayShowTitleEnabled(false) // Menghilangkan judul default karena sudah ada Header
        }

        // --- LOGIKA TOMBOL SUBMIT ---
        binding.btnSubmit.setOnClickListener {
            val nama = binding.inputNama.text.toString()

            if (nama.isNotEmpty()) {
                Log.e("Klik btnSubmit", "Tombol ditekan. Nama: $nama")
                Toast.makeText(this, "Welcome, $nama!", Toast.LENGTH_SHORT).show()

                // Jika ingin otomatis kembali setelah submit:
                // finish()
            } else {
                // Memberikan error jika input kosong (Aesthetic error handling)
                binding.inputLayout.error = "Please enter your name"
            }
        }
    }

    // Fungsi agar tombol back di Toolbar berfungsi
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}