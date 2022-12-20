package com.example.mbook_api.`interface`


import com.example.mbook_api.model.BookModel
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @GET("books")
    fun getBooks(): Call<ArrayList<BookModel>>

    @GET("books/{id}")
    fun getBook( @Path("id") id:Int ): Call<ArrayList<BookModel>>

    @FormUrlEncoded
    @POST("books")
    fun createBooks(
        @Field("code") code: String,
        @Field("judul") judul: String,
        @Field("pengarang") pengarang: String,
        @Field("tahun_terbit") tahun_terbit: String,
        @Field("kota_terbit") kota_terbit: String,
        @Field("isi_konten") isi_konten: String,
    ) : Call<BookModel>

    @FormUrlEncoded
    @PUT("books/{id}")
    fun updateBooks(
        @Path("id")id: Int?,
        @Field("kode")kode: String,
        @Field("judul")judul: String,
        @Field("pengarang")pengarang: String,
        @Field("tahun_terbit")tahun_terbit: String,
        @Field("kota_terbit")kota_terbit: String,
        @Field("isi_konten")isi_konten: String,
    ) : Call<BookModel>

    @DELETE("books/{id}")
    fun deleteBook(@Path("id") id: Int?): Call<Unit>
}