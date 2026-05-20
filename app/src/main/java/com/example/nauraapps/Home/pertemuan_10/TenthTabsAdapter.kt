package com.example.nauraapps.Home.pertemuan_10

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TenthTabsAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    // 1. Ubah jumlah total tab menjadi 3
    override fun getItemCount(): Int = 3

    // 2. Tambahkan TabCFragment ke dalam penanganan posisi
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabAFragment()
            1 -> TabBFragment()
            2 -> TabCFragment() // Tambahan untuk Tab C
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}