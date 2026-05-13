package com.example.nauraapps

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.nauraapps.Home.HomeFragment
import com.example.nauraapps.Message.MessageFragment
import com.example.nauraapps.More.MoreFragment
import com.example.nauraapps.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right,  0)
            insets
        }

        /** FragmentHome sebagai fragment default saat pertama kali dibuka */
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        // GABUNGKAN SEMUA LOGIKA DI SINI
        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    Toast.makeText(this, "Home Clicked", Toast.LENGTH_SHORT).show()
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.message -> {
                    Toast.makeText(this, "Message Clicked", Toast.LENGTH_SHORT).show()
                    replaceFragment(MessageFragment())
                    true
                }
                R.id.more -> {
                    Toast.makeText(this, "More Clicked", Toast.LENGTH_SHORT).show()
                    replaceFragment(MoreFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}