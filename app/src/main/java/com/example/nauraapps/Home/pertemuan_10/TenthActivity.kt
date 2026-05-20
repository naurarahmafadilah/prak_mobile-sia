package com.example.nauraapps.Home.pertemuan_10

import android.os.Bundle
import com.example.nauraapps.R
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat // Wajib ditambahkan untuk mengambil drawable/icon
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nauraapps.databinding.ActivityTenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class TenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityTenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Hubungkan Toolbar dan fungsikan tombol Back
        setSupportActionBar(binding.toolbarTenth)
        binding.toolbarTenth.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed() // Kembali ke halaman sebelumnya (FragmentHome)
        }

        // ==========================================
        // LOGIKA TAB LAYOUT & VIEWPAGER2 + CUSTOMIZATION
        // ==========================================

        // 1. Inisialisasi Adapter (Mendukung 3 fragment: TabA, TabB, dan TabC)
        val tabsAdapter = TenthTabsAdapter(this)

        // 2. Set adapter ke ViewPager2 melalui binding
        binding.viewPager.adapter = tabsAdapter

        // 3. Hubungkan TabLayout & ViewPager2 menggunakan TabLayoutMediator dengan Icon dan Badge
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Tab A"
                    // Menggunakan ic_home yang berada di folder drawable
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_home)

                    // Tambah Badge Tanpa nomor (hanya titik indikator merah)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                }
                1 -> {
                    tab.text = "Tab B"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_home)

                    // Tambah Badge dengan nomor 5
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 5
                }
                2 -> {
                    // --- BERHASIL DITAMBAHKAN: Konfigurasi Tampilan Tab C ---
                    tab.text = "Tab C"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_home)

                    // Tambah Badge dengan nomor 9
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 9
                }
            }
        }.attach()
    }
}