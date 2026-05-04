package com.example.nabilapps.Message

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.nabilapps.databinding.ItemMessageBinding
import com.google.android.material.snackbar.Snackbar

class MessageAdapter(
    context: Context,
    private val messages: List<MessageModel>
) : ArrayAdapter<MessageModel>(context, 0, messages) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Inflate layout menggunakan ViewBinding
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(context), parent, false)
        val view = binding.root

        // Ambil data berdasarkan posisi
        val data = messages[position]

        // Set Text
        binding.textSender.text = data.senderName
        binding.textMessage.text = data.messageText

        // Load Gambar dengan Glide
        Glide.with(context)
            .load(data.avatarUrl)
            .into(binding.avatarImg)

        // Langkah 8: Klik Item (Gunakan variabel 'data')
        view.setOnClickListener {
            Snackbar.make(
                parent,
                "Pesan dari ${data.senderName}: ${data.messageText}",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        return view
    }
}