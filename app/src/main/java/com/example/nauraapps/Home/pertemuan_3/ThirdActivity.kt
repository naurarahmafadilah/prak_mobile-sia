package com.example.nauraapps.Home.pertemuan_3

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nauraapps.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Third Activity"
            setDisplayHomeAsUpEnabled(true)
        }

        // Klik tombol kirim
        binding.btnKirim.setOnClickListener {
            val inputNoTujuan = binding.inputNoTujuan.text.toString()

            Toast.makeText(
                this,
                "Pesan berhasil dikirim ke $inputNoTujuan",
                Toast.LENGTH_SHORT
            ).show()

            val intent = Intent(this, ThirdResultActivity::class.java)
            startActivity(intent)
        }
    }

    // ✅ Tombol back toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}