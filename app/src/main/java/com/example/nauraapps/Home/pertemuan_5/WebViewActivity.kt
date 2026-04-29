package com.example.nauraapps.Home.pertemuan_5

import android.R
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.nauraapps.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inisialisasi ViewBinding
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Web Merdeka"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // 3. Konfigurasi WebView
        binding.webView.apply {
            webViewClient = WebViewClient() // Memastikan link terbuka di app, bukan browser
            webChromeClient = WebChromeClient() // Mendukung fitur UI browser

            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                builtInZoomControls = true
                displayZoomControls = false
            }

            loadUrl("https://www.merdeka.com/")
        }

        // 4. Logika Scroll AppBar Dinamis
        binding.webView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY && scrollY > 10) {
                // Scroll ke bawah: Sembunyikan Toolbar
                binding.appBar.setExpanded(false, true)
            } else if (scrollY < oldScrollY) {
                // Scroll ke atas: Munculkan Toolbar
                binding.appBar.setExpanded(true, true)
            }
        }

        // 5. Handle Back Navigation (Modern Way)
        onBackPressedDispatcher.addCallback(this) {
            if (binding.webView.canGoBack()) {
                binding.webView.goBack() // Kembali ke halaman web sebelumnya
            } else {
                finish() // Tutup activity jika tidak ada history web
            }
        }
    }

    // Handle klik tombol Back di Toolbar
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