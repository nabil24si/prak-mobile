package com.example.nabilapps.Home.pertemuan_2

fun main() {
    println("Hai Rekan-rekan...")
    println("Selamat Datang di bahasa pemograman Kotlin ")

    println("===============")

    var angka = 15
    println("Hasil dari 154 + 10 = ${angka + 10}")

    val  nilaiInt = 100000
    val  nilaiDouble = 100.003
    val  nilaiFloat = 1000.0f

    println("Nilai Integer = $nilaiInt")
    println("Nilai Double = $nilaiDouble")
    println("Nilai Float = $nilaiFloat")

    println("====== STRING ======")
    val huruf = 'a'
    println("ini penggunaan karakter '$huruf'")

    val nilaiString = "Mawar"
    println("Helo $nilaiString!\nApa Kabar")

    println("===== Kondisi =====")
    val nilai = 10
    if(nilai<0)
        println("Bilangan negatif")
    else {
        if(nilai%2 == 0)
            println("Bilangan Genap")
        else
            println("Bilangan Ganjil")
    }

    println("===== PERULANGAN =====")
    val kampusKu: Array<String> = arrayOf("kampus","Politeknik","Caltex","Riau")
    for (kampus: String in kampusKu){
        println(kampus)
    }

}