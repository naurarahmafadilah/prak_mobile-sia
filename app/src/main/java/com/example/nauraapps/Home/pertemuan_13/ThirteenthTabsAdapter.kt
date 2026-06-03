package com.example.nauraapps.Home.pertemuan_13 // Perbaikan package sesuai proyek Anda

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ThirteenthTabsAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabCaptureFragment()
            1 -> TabQrcodeFragment() // Disesuaikan menjadi posisi 1 agar pas dengan Tab QR Code
            2 -> TabScanFragment()   // Disesuaikan menjadi posisi 2 agar pas dengan Tab Scan
            else -> TabCaptureFragment()
        }
    }
}