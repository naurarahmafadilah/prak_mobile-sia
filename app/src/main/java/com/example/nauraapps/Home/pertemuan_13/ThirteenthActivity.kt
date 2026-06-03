package com.example.nauraapps.Home.pertemuan_13 // Perbaikan package sesuai proyek Anda

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nauraapps.databinding.ActivityThirteenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class ThirteenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirteenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirteenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Set Toolbar sebagai ActionBar
        setSupportActionBar(binding.toolbar)

        // 2. Aktifkan tombol Back (Home) di Toolbar
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // Setup Adapter untuk TabLayout & ViewPager2
        val tabsAdapter = ThirteenthTabsAdapter(this)
        binding.viewPager.adapter = tabsAdapter

        // Menghubungkan TabLayout dengan ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Tab Capture"
                1 -> "Tab QR Code"
                2 -> "Tab Scan"
                else -> null
            }
        }.attach()
    }

    // 3. Handle aksi ketika tombol back di Toolbar diklik
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed() // Kembali ke activity/fragment sebelumnya
        return true
    }
}