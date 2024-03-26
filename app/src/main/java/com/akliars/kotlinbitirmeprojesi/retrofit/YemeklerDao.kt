package com.akliars.kotlinbitirmeprojesi.retrofit

import com.akliars.kotlinbitirmeprojesi.data.entity.CRUDCevap
import com.akliars.kotlinbitirmeprojesi.data.entity.SepetYemekler
import com.akliars.kotlinbitirmeprojesi.data.entity.SepetYemeklerCevap
import com.akliars.kotlinbitirmeprojesi.data.entity.YemeklerCevap
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface YemeklerDao {
    //http://kasimadalan.pe.hu/
    //yemekler/tumYemekleriGetir.php

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun yemekleriYukle() : YemeklerCevap

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun sepettekiYemekleriGetir(@Field("kullanici_adi") kullanici_adi: String) : SepetYemeklerCevap



    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun sepettenYemekSil(@Field("sepet_yemek_id") yemek_id : Int,
                                 @Field("kullanici_adi") kullanici_adi: String) : CRUDCevap

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun sepeteYemekEkle(@Field ("yemek_adi") yemek_adi : String,
                                @Field ("yemek_resim_adi") yemek_resim_adi : String,
                                @Field ("yemek_fiyat") yemek_fiyat : Int,
                                @Field ("yemek_siparis_adet") yemek_siparis_adet : Int,
                                @Field ("kullanici_adi") kullanici_adi : String) : CRUDCevap


}