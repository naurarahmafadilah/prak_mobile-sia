package com.example.nauraapps.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast // Tambahkan import Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope // Tambahkan import lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager // Tambahkan import LinearLayoutManager
import com.example.nauraapps.AuthActivity
import com.example.nauraapps.Data.api.PhotoApiClient
import com.example.nauraapps.Home.pertemuan_10.TenthActivity
import com.example.nauraapps.Home.pertemuan_2.SecondActivity
import com.example.nauraapps.Home.pertemuan_3.ThirdActivity
import com.example.nauraapps.Home.pertemuan_4.FourthActivity
import com.example.nauraapps.Home.pertemuan_5.FifthActivity
import com.example.nauraapps.Home.pertemuan_7.SeventhActivity
import com.example.nauraapps.Home.pertemuan_9.NinthActivity
import com.example.nauraapps.Home.photo.PhotoAdapter
import com.example.nauraapps.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch // Tambahkan import Coroutine launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigation()
        setupLogout()

        // Memanggil fungsi loadPhoto saat View selesai dibuat
        loadPhoto()
    }

    private fun loadPhoto() {
        lifecycleScope.launch {
            try {
                // Mengambil data foto dari ApiClient
                val photos = PhotoApiClient.apiService.getPhotos()
                val adapter = PhotoAdapter(photos)
                binding.rvGallery.adapter = adapter

                /** List Tampil Vertical */
                binding.rvGallery.layoutManager = LinearLayoutManager(requireContext())

                /** List Tampil Horizontal */
                // binding.rvGallery.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

                /** List Tampil Grid */
                // binding.rvGallery.layoutManager = GridLayoutManager(requireContext(), 2)

            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Gagal memuat gambar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupNavigation() {
        binding.apply {
            btnToSecond.setOnClickListener {
                startActivity(Intent(requireContext(), SecondActivity::class.java))
            }

            btnToThird.setOnClickListener {
                startActivity(Intent(requireContext(), ThirdActivity::class.java))
            }

            btnToFourth.setOnClickListener {
                val intent = Intent(requireContext(), FourthActivity::class.java).apply {
                    putExtra("name", "Politeknik Caltex Riau")
                    putExtra("from", "Rumbai")
                    putExtra("age", 25)
                }
                startActivity(intent)
            }

            btnToFifth.setOnClickListener {
                startActivity(Intent(requireContext(), FifthActivity::class.java))
            }

            btnToSeventh.setOnClickListener {
                startActivity(Intent(requireContext(), SeventhActivity::class.java))
            }

            btnToNinth.setOnClickListener {
                startActivity(Intent(requireContext(), NinthActivity::class.java))
            }

            btnToTenth.setOnClickListener {
                startActivity(Intent(requireContext(), TenthActivity::class.java))
            }
        }
    }

    private fun setupLogout() {
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    val sharedPref = requireActivity().getSharedPreferences("login_pref", Context.MODE_PRIVATE)
                    sharedPref.edit().clear().apply()

                    dialog.dismiss()

                    val intent = Intent(requireContext(), AuthActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    startActivity(intent)
                    requireActivity().finish()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}