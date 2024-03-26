package com.akliars.kotlinbitirmeprojesi.data.repository

import com.akliars.kotlinbitirmeprojesi.data.datasource.YemeklerDataSource

class YemeklerRepository (var yds: YemeklerDataSource){
    suspend fun sepeteYemekEkle(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String) = yds.sepeteYemekEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
    suspend fun sepettenYemekSil(yemek_id:Int) = yds.sepettenYemekSil(yemek_id)
    suspend fun sepettekiYemekleriGetir(kullanici_adi: String) = yds.sepettekiYemekleriGetir(kullanici_adi)
    suspend fun yemekleriYukle() = yds.yemekleriYukle()
}