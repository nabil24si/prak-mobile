package com.example.nabilapps.Home.pertemuan_3

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nabilapps.databinding.ActivityThirdBinding
import com.example.nabilapps.utils.NotificationHelper
import com.example.nabilapps.utils.PermissionHelper
import com.example.nabilapps.utils.ReminderHelper
import java.util.Calendar

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Notifikasi diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifikasi ditolak", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inisialisasi ViewBinding
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // PERBAIKAN: Gunakan binding.root sebagai ganti findViewById(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Cek dan request permission
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(
                    notificationPermissionLauncher,
                    permission
                )
            }
        }

        // PERBAIKAN: Menggunakan binding langsung untuk tombol, tidak perlu findViewById
        binding.btnKirim.setOnClickListener {
            // PERBAIKAN: Ambil TEKS (String) dari EditText, bukan mengambil objeknya
            val nomorTujuan = binding.inputNoTujuan.text.toString()

            val intent = Intent(this, ThirdResultActivity::class.java)

//            NotificationHelper.showNotification(
//                this,
//                "Pesanan Anda",
//                "Halo $nomorTujuan, Pesanan Anda Sedang Diproses", // Sekarang variabelnya adalah String yang benar
//                intent
//            )
            val calendar = Calendar.getInstance().apply {
                add(Calendar.MINUTE, 1) // Tambah 1 menit dari sekarang
            }

            ReminderHelper.setReminder(
                context = this, //Jika panggil di fragment maka requireContext()
                hour = calendar.get(Calendar.HOUR_OF_DAY),
                minute = calendar.get(Calendar.MINUTE),
                title = "Reminder 1 Menit",
                message = "Halo $nomorTujuan, reminder ini muncul 1 menit setelah tombol ditekan",
                targetActivity = ThirdResultActivity::class.java
            )
            Toast.makeText(this, "Silahkan tunggu 1 Menit untuk menerima Notifikasi...", Toast.LENGTH_SHORT).show()
        }

        // Pastikan tema di themes.xml adalah .NoActionBar dan id toolbar di XML adalah @+id/toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Activity Fifth"
            subtitle = "Ini adalah subtitle"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}