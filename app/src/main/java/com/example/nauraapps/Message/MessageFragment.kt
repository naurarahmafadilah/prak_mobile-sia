package com.example.nauraapps.Message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nauraapps.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    private val messageList = listOf(
        MessageModel("Alya", "Halo! Apa kabar?", "https://robohash.org/Alya.png?set=set4"),
        MessageModel("Budi", "Sudah makan?", "https://robohash.org/Budi.png?set=set4"),
        MessageModel("Citra", "Jangan lupa tugasnya ya!", "https://robohash.org/Citra.png?set=set4"),
        MessageModel("Dika", "Besok kita rapat jam 9", "https://robohash.org/Dika.png?set=set4"),
        MessageModel("Eka", "Nice job kemarin!", "https://robohash.org/Eka.png?set=set4"),
        MessageModel("Fajar", "Lagi ngapain?", "https://robohash.org/Fajar.png?set=set4"),
        MessageModel("Gita", "Boleh minta tolong?", "https://robohash.org/Gita.png?set=set4"),
        MessageModel("Hana", "Lihat email ya", "https://robohash.org/Hana.png?set=set4"),
        MessageModel("Irfan", "Oke noted", "https://robohash.org/Irfan.png?set=set4"),
        MessageModel("Joko", "Sampai jumpa besok", "https://robohash.org/Joko.png?set=set4")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi Toolbar
        binding.toolbarMessage.title = "Messages"

        // Setup Adapter
        val adapter = MessageAdapter(requireContext(), messageList)
        binding.listMessageItems.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}