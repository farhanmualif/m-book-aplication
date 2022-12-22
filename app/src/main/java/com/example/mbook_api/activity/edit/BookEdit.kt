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
        val isi_konten: EditText = findViewById(R.id.isi_konten_up)
        val judul: EditText = findViewById(R.id.et_judul_up)
        val btnUpdate : Button = findViewById(R.id.btn_update)

        judul.setText(intent.getStringExtra("JUDUL_UP"))
        isi_konten.setText(intent.getStringExtra("ISI_KONTEN_UP"))

        val apiClient = APIClient.create()

        btnUpdate.setOnClickListener {

            if (isi_konten.text.toString().isEmpty()){
                isi_konten.error = "isiKonten tidak boleh kosong"
            }
            if (judul.text.toString().isEmpty()){
                judul.error = "judul tidak boleh kosong"
            }

            val id = intent.getStringExtra("ID")
            val sendData = apiClient.updateBooks(
                id?.toInt(),
                judul.text.toString(),
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