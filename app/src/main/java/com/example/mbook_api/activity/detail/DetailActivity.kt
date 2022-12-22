package com.example.mbook_api.activity.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.mbook_api.R
import com.example.mbook_api.activity.edit.BookEdit
import com.example.mbook_api.api.APIClient
import com.example.mbook_api.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        detailBook()
    }
    private fun detailBook() {
        val isiKonten: TextView = findViewById(R.id.isi_konten)
        val judul: TextView = findViewById(R.id.et_judul)
        val btnDelete : Button = findViewById(R.id.btn_delete_det)


        val Judul = intent.getStringExtra("JUDUL")
        val isi_konten = intent.getStringExtra("ISI_KONTEN")
        val id = intent.getStringExtra("ID")

        judul.text = Judul.toString()
        isiKonten.text = isi_konten.toString()

        val btn_update : Button = findViewById(R.id.btn_update_det)

        btn_update.setOnClickListener {
            val intent = Intent(this@DetailActivity,BookEdit::class.java)
            intent.putExtra("JUDUL_UP", Judul)
            intent.putExtra("ISI_KONTEN_UP", isi_konten)
            intent.putExtra("ID", id)
            startActivity(intent)
        }


        btnDelete.setOnClickListener {
            val apiClient = APIClient.create()
            val deleteData = id?.toInt().let { apiClient.deleteBook(it) }
            Log.d("TAG", id.toString())

            deleteData.enqueue(object : Callback<Unit>{
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    Log.d("TAG",response.code().toString())
                    Log.d("TAG",response.message().toString())

                    if (response.isSuccessful){
                        Log.d("GET",response.message())
                        Toast.makeText(applicationContext, response.message(), Toast.LENGTH_LONG).show()
                        finish()
                    }
                }
                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.d("GET",t.message.toString())
                }
            })
        }
    }
}

