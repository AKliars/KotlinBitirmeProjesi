package com.akliars.kotlinbitirmeprojesi.data.datasource

import android.util.Log
import com.akliars.kotlinbitirmeprojesi.data.entity.CRUDCevap
import com.akliars.kotlinbitirmeprojesi.data.entity.SepetYemekler
import com.akliars.kotlinbitirmeprojesi.data.entity.Yemekler
import com.akliars.kotlinbitirmeprojesi.retrofit.YemeklerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import retrofit2.http.Field
import java.lang.reflect.Constructor
import javax.inject.Inject

class YemeklerDataSource (var ydao: YemeklerDao){
    suspend fun sepettekiYemekleriGetir(kullanici_adi: String) : List<SepetYemekler> =
        withContext(Dispatchers.IO){
            var ali : List<SepetYemekler>
            ali = mutableListOf()
            if(ydao.sepettekiYemekleriGetir(kullanici_adi).success == 1 && ydao.sepettekiYemekleriGetir(kullanici_adi).sepet_yemekler.isNotEmpty()){
                ali = ydao.sepettekiYemekleriGetir(kullanici_adi).sepet_yemekler
                Log.e("sepettekiYemekleriGetir","YemeklerDataSource")
            }
            Log.e("SepetYDS",ali.toString())
            return@withContext ali
        }



    // Sepete Yemek Ekle
    suspend fun sepeteYemekEkle(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String){
        withContext(Dispatchers.IO){
                ydao.sepeteYemekEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
            Log.e("sepeteYemekEkle","YemeklerDataSource")

        }
    }
    // Sepetten Yemek Sil
    suspend fun sepettenYemekSil( yemek_id : Int){
        withContext(Dispatchers.IO){
            Log.e("sepettenYemekSil","YemeklerDataSource")
            return@withContext ydao.sepettenYemekSil(yemek_id,"akliars")
        }
    }

    // Tüm Yemekleri Listele
    suspend fun yemekleriYukle() : List<Yemekler> =
       withContext(Dispatchers.IO){
           Log.e("yemekleriYukle","YemeklerDataSource")

               return@withContext ydao.yemekleriYukle().yemekler

       }

    // Sepetteki Tüm Yemekleri Listele


}