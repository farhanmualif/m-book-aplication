package com.example.mbook_api.activity.edit


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.mbook_api.R

import com.example.mbook_api.api.APIClient
import com.example.mbook_api.model.BookModel

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookEdit : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_edit)
        updateBooks()
    }
    private fun updateBooks() {
        val code: EditText = findViewById(R.id.et_code_up)
        val pengarang: EditText = findViewById(R.id.et_pengarang_up)
        val kota_terbit: EditText = findViewById(R.id.et_kota_terbit_up)
        val tahun_terbit: EditText = findViewById(R.id.et_tahun_terbit_up)
        val isi_konten: EditText = findViewById(R.id.isi_konten_up)
        val judul: EditText = findViewById(R.id.et_judul_up)
        val btnUpdate : Button = findViewById(R.id.btn_update)

        code.setText(intent.getStringExtra("KODE_UP"))
        judul.setText(intent.getStringExtra("JUDUL_UP"))
        tahun_terbit.setText(intent.getStringExtra("TAHUN_TERBIT_UP"))
        kota_terbit.setText(intent.getStringExtra("KOTA_TERBIT_UP"))
        isi_konten.setText(intent.getStringExtra("ISI_KONTEN_UP"))
        pengarang.setText(intent.getStringExtra("PENGARANG_UP"))

        val apiClient = APIClient.create()


        btnUpdate.setOnClickListener {

            if (code.text.toString().isEmpty()){
                code.error = "code tidak boleh kosong"
            }
            if (pengarang.text.toString().isEmpty()){
                pengarang.error = "pengarang tidak boleh kosong"
            }
            if (tahun_terbit.text.toString().isEmpty()){
                tahun_terbit.error = "tahunTerbit tidak boleh kosong"
            }
            if (isi_konten.text.toString().isEmpty()){
                isi_konten.error = "isiKonten tidak boleh kosong"
            }
            if (judul.text.toString().isEmpty()){
                judul.error = "judul tidak boleh kosong"
            }
            if (kota_terbit.text.toString().isEmpty()){
                kota_terbit.error = "kotaTerbit tidak boleh kosong"
            }
            val id = intent.getStringExtra("ID")
            val sendData = apiClient.updateBooks(
                id?.toInt(),
                code.text.toString(),
                judul.text.toString(),
                pengarang.text.toString(),
                tahun_terbit.text.toString(),
                kota_terbit.text.toString(),
                isi_konten.text.toString(),
            )

            sendData.enqueue(object : Callback<BookModel> {
                override fun onResponse(call: Call<BookModel>, response: Response<BookModel>) {
                    Log.d("TAG",response.code().toString())
                    finish()
                }
                override fun onFailure(call: Call<BookModel>, t: Throwable) {
                    Log.d("TAG",t.message.toString())
                    Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG).show()
                    finish()
                }
            })
        }
    }
}