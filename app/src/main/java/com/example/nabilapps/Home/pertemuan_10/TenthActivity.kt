package com.example.nabilapps.Home.pertemuan_10

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nabilapps.R
import com.example.nabilapps.databinding.ActivityBaseBinding
import com.example.nabilapps.databinding.ActivityTenthBinding

class TenthActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTenthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTenthBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Activity Tenth"
            setDisplayHomeAsUpEnabled(true)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            // Improvisasi: Tombol Share mengambil URL aktif dari WebView
            R.id.action_share -> {
                val shareIntent = Intent(Intent.ACTION_SEND).apply {

                }
                startActivity(Intent.createChooser(shareIntent, "Bagikan via"))
                true
            }
            android.R.id.home -> { // Tombol back di toolbar
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}