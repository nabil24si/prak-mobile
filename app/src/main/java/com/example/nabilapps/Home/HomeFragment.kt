package com.example.nabilapps.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.nabilapps.AuthActivity
import com.example.nabilapps.Home.pertemuan_2.SecondActivity
import com.example.nabilapps.Home.pertemuan_3.ThirdActivity
import com.example.nabilapps.Home.pertemuan_4.FourthActivity
import com.example.nabilapps.Home.pertemuan_5.FifthActivity
import com.example.nabilapps.Home.pertemuan_7.SeventhActivity
import com.example.nabilapps.Home.pertemuan_9.NinthActivity
import com.example.nabilapps.R
import com.example.nabilapps.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Home"

            binding.btnToSeventh.setOnClickListener {
                val intent = Intent(requireContext(), SeventhActivity::class.java)
                startActivity(intent)
            }

            binding.btnToFourth.setOnClickListener {
                val intent = Intent(requireContext(), FourthActivity::class.java)
                startActivity(intent)
            }

            binding.btnToSecond.setOnClickListener {
                val intent = Intent(requireContext(), SecondActivity::class.java)
                startActivity(intent)
            }
            binding.btnToThird.setOnClickListener {
                val intent = Intent(requireContext(), ThirdActivity::class.java)
                startActivity(intent)
            }
            binding.btnToFifth.setOnClickListener {
                val intent = Intent(requireContext(), FifthActivity::class.java)
                startActivity(intent)
            }
            binding.btnToSeventh.setOnClickListener {
                val intent = Intent(requireContext(), SeventhActivity::class.java)
                startActivity(intent)
            }
            binding.btnToNinth.setOnClickListener {
                val intent = Intent(requireContext(), NinthActivity::class.java)
                startActivity(intent)
            }
            binding.btnLogout.setOnClickListener {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Konfirmasi")
                    .setMessage("Apakah Anda yakin ingin melanjutkan?")
                    .setPositiveButton("Ya") { dialog, _ ->
                        val editor = sharedPref.edit()
                        editor.clear()
                        editor.apply()

                        dialog.dismiss()
                        val intent = Intent(requireContext(), AuthActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    }
                    .setNegativeButton("Batal") { dialog, _ ->
                        dialog.dismiss()
                        Log.e("Info Dialog", "Anda memilih Tidak!")
                    }
                    .show()
            }
        }
    }
}