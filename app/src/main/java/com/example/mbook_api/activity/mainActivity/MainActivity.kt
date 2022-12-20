package com.example.mbook_api.activity.mainActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mbook_api.R
import com.example.mbook_api.activity.add.AddBook
import com.example.mbook_api.adapter.BookAdapter
import com.example.mbook_api.api.APIClient
import com.example.mbook_api.model.BookModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }

    override fun onResume() {
        getBooksData()
        super.onResume()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.add_circle -> {
                val intent = Intent(this, AddBook::class.java)
                startActivity(intent)

                true
            } else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getBooksData(){
        val listData = ArrayList<BookModel>()
        val rvBookData: RecyclerView = findViewById(R.id.rv_books_data)
        rvBookData.setHasFixedSize(true)
        rvBookData.layoutManager = GridLayoutManager(this, 2)
        val tv_code = findViewById<TextView>(R.id.code_status)
        val apiClient = APIClient.create()
        val callData = apiClient.getBooks()
        callData.enqueue(
            object : Callback<ArrayList<BookModel>> {
                override fun onResponse(
                    call: Call<ArrayList<BookModel>>,
                    response: Response<ArrayList<BookModel>>
                ) {
                    val data = response.body()
                    data?.let { listData.addAll(it) }
                    val adapterData = BookAdapter(listData)
                    rvBookData.adapter = adapterData
                    tv_code.text = response.code().toString()
                }
                override fun onFailure(call: Call<ArrayList<BookModel>>, t: Throwable) {
                    tv_code.text = t.message.toString()
                }
            }
        )
    }
}