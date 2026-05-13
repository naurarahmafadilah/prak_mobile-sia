package com.example.nauraapps.More

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.nauraapps.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {

    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    // Data dengan deskripsi (menggunakan Map)
    private val dataListWithDesc = listOf(
        mapOf("title" to "Kotlin", "desc" to "Bahasa untuk Android modern"),
        mapOf("title" to "Java", "desc" to "Bahasa OOP yang populer"),
        mapOf("title" to "Python", "desc" to "Bahasa yang mudah dipahami"),
        mapOf("title" to "C++", "desc" to "Bahasa tingkat tinggi untuk performa"),
        mapOf("title" to "JavaScript", "desc" to "Bahasa utama pengembangan web"),
        mapOf("title" to "Dart", "desc" to "Bahasa dibalik framework Flutter"),
        mapOf("title" to "Swift", "desc" to "Bahasa modern untuk ekosistem Apple")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Setup Toolbar
        binding.toolbar.title = "Daftar Bahasa Pemrograman"

        // 2. Inisialisasi SimpleAdapter
        // simple_list_item_2 menyediakan dua TextView (text1 dan text2)
        val adapter = SimpleAdapter(
            requireContext(),
            dataListWithDesc,
            android.R.layout.simple_list_item_2, // Layout dua baris bawaan Android
            arrayOf("title", "desc"), // Key dari Map
            intArrayOf(android.R.id.text1, android.R.id.text2) // ID TextView di layout
        )

        // 3. Hubungkan ListView dengan adapter
        binding.listViewItems.adapter = adapter

        // 4. Tambahkan aksi klik item
        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = dataListWithDesc[position]
            val title = selectedItem["title"]
            val desc = selectedItem["desc"]

            Toast.makeText(
                requireContext(),
                "Kamu memilih: $title\n($desc)",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}