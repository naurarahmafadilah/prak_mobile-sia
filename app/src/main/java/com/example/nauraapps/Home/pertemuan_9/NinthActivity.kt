package com.example.nauraapps.Home.pertemuan_9

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nauraapps.databinding.ActivityNinthBinding
import com.google.android.material.chip.Chip

class NinthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNinthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup View Binding
        binding = ActivityNinthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbarNinth)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // Back Navigation
        binding.toolbarNinth.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Logika ChipGroup (Menggunakan 'this' karena di dalam Activity)
        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull()

            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                val filterName = chip.text.toString()

                // Karena ini Activity, gunakan 'this'
                Toast.makeText(this, "Filter: $filterName", Toast.LENGTH_SHORT).show()
            }
        }

        // Tombol Login
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            if (email.isNotEmpty()) {
                Toast.makeText(this, "Login Berhasil: $email", Toast.LENGTH_SHORT).show()
            } else {
                // Menampilkan error pada TextInputLayout jika kosong
                binding.textInputLayout.error = "Email wajib diisi"
            }
        }
    }
}