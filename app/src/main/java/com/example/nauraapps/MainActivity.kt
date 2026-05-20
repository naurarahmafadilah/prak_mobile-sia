package com.example.nauraapps

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.nauraapps.Home.pertemuan_10.TenthActivity
import com.example.nauraapps.databinding.ActivityMainBinding
import com.example.nauraapps.Home.pertemuan_2.SecondActivity
import com.example.nauraapps.Home.pertemuan_3.ThirdActivity
import com.example.nauraapps.Home.pertemuan_4.FourthActivity
import com.example.nauraapps.Home.pertemuan_5.FifthActivity
import com.example.nauraapps.Home.pertemuan_7.SeventhActivity
import com.example.nauraapps.Home.pertemuan_9.NinthActivity

// Import activity lainnya jika sudah dibuat (ThirdActivity, FifthActivity, SeventhActivity)

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Menggunakan ViewBinding untuk memanggil ID di XML
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)

        // --- Navigasi Pertemuan ---

        // Pertemuan 2
        binding.btnToSecond.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        // Pertemuan 3
        binding.btnToThird.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }

        // Pertemuan 4 (Dengan Data Extra)
        binding.btnToFourth.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        // Pertemuan 5
        binding.btnToFifth.setOnClickListener {
            val intent = Intent(this, FifthActivity::class.java)
            startActivity(intent)
        }

        // Pertemuan 7
        binding.btnToSeventh.setOnClickListener {
            val intent = Intent(this, SeventhActivity::class.java)
            startActivity(intent)
        }

        // --- Logika Logout ---
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    // Hapus data login di Shared Preferences
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()

                    dialog.dismiss()

                    // Arahkan kembali ke halaman Login (AuthActivity)
                    val intent = Intent(this, AuthActivity::class.java)
                    startActivity(intent)
                    finish() // Tutup MainActivity agar tidak bisa kembali dengan tombol back
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}