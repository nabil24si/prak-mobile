package com.example.nabilapps.Home.pertemuan_13

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nabilapps.Home.pertemuan_10.TenthTabsAdapter
import com.example.nabilapps.R
import com.example.nabilapps.databinding.ActivityTenthBinding
import com.example.nabilapps.databinding.ActivityThriteenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class ThriteenthActivity : AppCompatActivity() {
    private lateinit var binding : ActivityThriteenthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThriteenthBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Activity Thirteenth"
            setDisplayHomeAsUpEnabled(true)
        }
        val tabsAdapter = ThirteenthTabsAdapter(this)

        // 2. Set adapter ke ViewPager2
        binding.viewPager.adapter = tabsAdapter

        // 3. Hubungkan TabLayout & ViewPager2 menggunakan Adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            // Atur judul untuk setiap tab
            when (position) {
                0 -> {
                    tab.text = "Tab Capture"
                    //Tambah Icon
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_capture)
                    //Tambah Badge Tanpa nomor (hanya titik)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                }

                1 -> {
                    tab.text = "Tab Qrcode"
                    //Tambah Icon
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_qrcode)
                    //Tambah Badge dengan nomor
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 5
                }
                2 -> {
                    tab.text = "Tab Scan"
                    //Tambah Icon
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_scan)
                    //Tambah Badge dengan nomor
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 10
                }
            }
        }.attach()
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