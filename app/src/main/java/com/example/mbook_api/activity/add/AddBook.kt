package com.example.mbook_api.activity.add

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import com.example.mbook_api.R
import com.example.mbook_api.api.APIClient
import com.example.mbook_api.model.BookModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field

class AddBook : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)
        createBooksData()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun createBooksData() {
        val code: EditText = findViewById(R.id.et_code)
        val pengarang: EditText = findViewById(R.id.et_pengarang)
        val kotaTerbit: EditText = findViewById(R.id.et_kota_terbit)
        val tahunTerbit: EditText = findViewById(R.id.et_tahun_terbit)
        val isiKonten: EditText = findViewById(R.id.isi_konten)
        val judul: EditText = findViewById(R.id.et_judul)
        val btnSubmit: Button = findViewById(R.id.btn_submit)

        val apiClient = APIClient.create()


        btnSubmit.setOnClickListener {

            if (code.text.toString().isEmpty()){
                code.error = "code tidak boleh kosong"
            }
            if (pengarang.text.toString().isEmpty()){
                pengarang.error = "pengarang tidak boleh kosong"
            }
            if (tahunTerbit.text.toString().isEmpty()){
                tahunTerbit.error = "tahunTerbit tidak boleh kosong"
            }
            if (isiKonten.text.toString().isEmpty()){
                isiKonten.error = "isiKonten tidak boleh kosong"
            }
            if (judul.text.toString().isEmpty()){
                judul.error = "judul tidak boleh kosong"
            }
            if (kotaTerbit.text.toString().isEmpty()){
                kotaTerbit.error = "kotaTerbit tidak boleh kosong"
            }
            val sendData = apiClient.createBooks(
                code.text.toString(),
                judul.text.toString(),
                pengarang.text.toString(),
                tahunTerbit.text.toString(),
                kotaTerbit.text.toString(),
                isiKonten.text.toString(),
            )
            sendData.enqueue(object : Callback<BookModel> {
                override fun onResponse(call: Call<BookModel>, response: Response<BookModel>) {
//                        Log.d("TAG", response.code().toString())
                    if (response.isSuccessful) {
                        Toast.makeText(this@AddBook,"Add Book Sucsess",Toast.LENGTH_LONG).show()
                        finish()
                    }
                }
                override fun onFailure(call: Call<BookModel>, t: Throwable) {
                    Log.d("TAG",t.message.toString())
                }
            })
        }
    }


}