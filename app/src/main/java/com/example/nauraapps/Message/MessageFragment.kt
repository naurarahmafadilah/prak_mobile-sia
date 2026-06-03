package com.example.nauraapps.Message

import android.content.Intent // Pastikan import Intent ini ada
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.nauraapps.Message.tutorial.TutorialMessageActivity // Pastikan import Activity Tutorial ini benar
import com.example.nauraapps.R
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

        // 1. Atur Toolbar sebagai ActionBar
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbarMessage)

        activity.supportActionBar?.apply {
            title = "Message"
        }

        // 2. Aktifkan menu option di Fragment
        setHasOptionsMenu(true)

        // Setup Adapter untuk ListView / RecyclerView
        val adapter = MessageAdapter(requireContext(), messageList)
        binding.listMessageItems.adapter = adapter
    }

    // 3. Inflate layout menu toolbar pesan
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.message_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    // 4. Handle aksi klik pada item menu (membuka halaman tutorial)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_tutorial -> {
                val intent = Intent(requireContext(), TutorialMessageActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}