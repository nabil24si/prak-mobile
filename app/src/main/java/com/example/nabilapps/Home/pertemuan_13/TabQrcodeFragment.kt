package com.example.nabilapps.Home.pertemuan_13

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.nabilapps.databinding.FragmentTabQrcodeBinding
import com.example.nabilapps.utils.NotificationHelper
import com.example.nabilapps.utils.PermissionHelper
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter

class TabQrcodeFragment : Fragment() {

    private var _binding: FragmentTabQrcodeBinding? = null
    private val binding get() = _binding!!

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // PERBAIKAN 1: Penggunaan Toast yang benar di dalam Fragment
                Toast.makeText(requireContext(), "Notifikasi diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                // PERBAIKAN 2: Menggunakan requireContext() bukan this
                Toast.makeText(requireContext(), "Notifikasi ditolak", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(requireContext(), permission)) {
                PermissionHelper.requestPermission(
                    notificationPermissionLauncher,
                    permission
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTabQrcodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGenerate.setOnClickListener {
            val text = binding.edtQrInput.text.toString().trim()
            if (text.isEmpty()) {
                Toast.makeText(requireContext(), "Teks tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 1. Buat dan tampilkan QR Code
            binding.ivQrCode.setImageBitmap(createQR(text))

            // PERBAIKAN 3 & 4: Pindahkan notifikasi ke sini, dan buat variabel intent
            // Intent dibuat mengarah ke Activity yang sedang aktif (bisa kamu ubah tujuannya jika perlu)
            val intent = Intent(requireContext(), requireActivity()::class.java)

            // Pastikan parameter NotificationHelper sesuai dengan yang ada di kelas utils-mu
            NotificationHelper.showNotification(
                requireContext(),
                "QR Code Berhasil Dibuat",
                "QR Code untuk teks: $text sudah siap.",
                intent
            )
        }
    }

    // Fungsi createQR difokuskan murni HANYA untuk membuat gambar Bitmap (Best Practice)
    private fun createQR(text: String): Bitmap {
        val writer = QRCodeWriter()
        val matrix = writer.encode(
            text,
            BarcodeFormat.QR_CODE,
            500,
            500,
            mapOf(EncodeHintType.CHARACTER_SET to "UTF-8")
        )
        return Bitmap.createBitmap(500, 500, Bitmap.Config.RGB_565).apply {
            for (x in 0 until 500) {
                for (y in 0 until 500) {
                    setPixel(x, y, if (matrix.get(x, y)) Color.BLACK else Color.WHITE)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView() // Jangan lupa panggil super saat override lifecycle
        _binding = null
    }
}