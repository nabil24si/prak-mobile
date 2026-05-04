package com.example.nabilapps.More

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nabilapps.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {
    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    // Data dengan deskripsi menggunakan Map
    private val dataListWithDesc = listOf(
        mapOf("title" to "Kotlin", "desc" to "Bahasa untuk Android modern"),
        mapOf("title" to "Java", "desc" to "Bahasa OOP yang populer"),
        mapOf("title" to "Python", "desc" to "Bahasa yang mudah dipahami"),
        mapOf("title" to "JavaScript", "desc" to "Bahasa utama untuk Web Development"),
        mapOf("title" to "Dart", "desc" to "Bahasa untuk framework Flutter"),
        mapOf("title" to "Swift", "desc" to "Bahasa untuk pengembangan aplikasi iOS"),
        mapOf("title" to "PHP", "desc" to "Bahasa server-side yang legendaris"),
        mapOf("title" to "SQL", "desc" to "Bahasa untuk manajemen Database")
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

        // Setup Toolbar agar muncul teks "More"
        setupToolbar()

        // Setup SimpleAdapter untuk ListView
        setupListView()
    }

    private fun setupToolbar() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "More"
        }
    }

    private fun setupListView() {
        // Menggunakan SimpleAdapter untuk layout 2 baris (simple_list_item_2)
        val adapter = SimpleAdapter(
            requireContext(),
            dataListWithDesc,
            android.R.layout.simple_list_item_2, // Layout bawaan Android dengan 2 TextView
            arrayOf("title", "desc"),            // Key dari Map
            intArrayOf(android.R.id.text1, android.R.id.text2) // ID TextView pada layout
        )

        binding.listViewItems.adapter = adapter

        // Aksi saat item diklik
        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = dataListWithDesc[position]
            val title = selectedItem["title"]
            val desc = selectedItem["desc"]

            Toast.makeText(
                requireContext(),
                "Kamu memilih: $title\n$desc",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}