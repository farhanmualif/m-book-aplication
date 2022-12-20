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
        val code: TextView = findViewById(R.id.et_code)
        val pengarang: TextView = findViewById(R.id.et_pengarang)
        val kotaTerbit: TextView = findViewById(R.id.et_kota_terbit)
        val tahunTerbit: TextView = findViewById(R.id.et_tahun_terbit)
        val isiKonten: TextView = findViewById(R.id.isi_konten)
        val judul: TextView = findViewById(R.id.et_judul)
        val btnDelete : Button = findViewById(R.id.btn_delete_det)

        val kode = intent.getStringExtra("CODE")
        val Judul = intent.getStringExtra("JUDUL")
        val Pengarang = intent.getStringExtra("PENGARANG")
        val th_terbit = intent.getStringExtra("TAHUN_TERBIT")
        val kt_terbit = intent.getStringExtra("KOTA_TERBIT")
        val isi_konten = intent.getStringExtra("ISI_KONTEN")
        val id = intent.getStringExtra("ID")

        code.text = kode.toString()
        judul.text = Judul.toString()
        pengarang.text = Pengarang.toString()
        tahunTerbit.text = th_terbit.toString()
        kotaTerbit.text = kt_terbit.toString()
        isiKonten.text = isi_konten.toString()

        val btn_update : Button = findViewById(R.id.btn_update_det)

        btn_update.setOnClickListener {
            val intent = Intent(this@DetailActivity,BookEdit::class.java)
            intent.putExtra("KODE_UP", kode)
            intent.putExtra("JUDUL_UP", Judul)
            intent.putExtra("PENGARANG_UP", Pengarang)
            intent.putExtra("TAHUN_TERBIT_UP", th_terbit)
            intent.putExtra("KOTA_TERBIT_UP", kt_terbit)
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

