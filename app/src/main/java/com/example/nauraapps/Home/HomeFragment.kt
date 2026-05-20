package com.example.nauraapps.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.example.nauraapps.AuthActivity
import com.example.nauraapps.Home.pertemuan_10.TenthActivity
import com.example.nauraapps.Home.pertemuan_2.SecondActivity
import com.example.nauraapps.Home.pertemuan_3.ThirdActivity
import com.example.nauraapps.Home.pertemuan_4.FourthActivity
import com.example.nauraapps.Home.pertemuan_5.FifthActivity
import com.example.nauraapps.Home.pertemuan_7.SeventhActivity
import com.example.nauraapps.Home.pertemuan_9.NinthActivity // Pastikan import ini benar
import com.example.nauraapps.databinding.FragmentHomeBinding

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

        // Gunakan fungsi pembantu untuk kerapihan (Optional)
        setupNavigation()
        setupLogout()
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

            // Memanggil NinthActivity
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
                    // Hapus Session
                    val sharedPref = requireActivity().getSharedPreferences("login_pref", Context.MODE_PRIVATE)
                    sharedPref.edit().clear().apply()

                    dialog.dismiss()

                    // Redirect ke AuthActivity dan bersihkan backstack
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