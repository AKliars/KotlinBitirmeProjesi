package com.akliars.kotlinbitirmeprojesi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akliars.kotlinbitirmeprojesi.data.entity.SepetYemekler
import com.akliars.kotlinbitirmeprojesi.data.repository.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UrunDetayViewModel @Inject constructor (var yrepo: YemeklerRepository): ViewModel()  {
    // Sepetteki yemeğin verilerinin canlı olarak tutulduğu liste
    var sepetYemeklerListesi = MutableLiveData<List<SepetYemekler>>(listOf())

    //Sepete Yemek Ekler,Sonra Sepeti Günceller
    fun sepeteYemekEkle(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String){
        CoroutineScope(Dispatchers.Main).launch{
            yrepo.sepeteYemekEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
            //sepettekiYemekleriGetir()
        }
    }

    // UrunDetayViewModel ilk oluşturulduğunda çalışan fonksiyon.
    init {
        try {
            sepettekiYemekleriGetir()
        }catch (exc:Exception){
          //  Log.e("ali",exc.toString())
        }
    }

    // Sepetteki yemekleri getirir
    fun sepettekiYemekleriGetir(){
        CoroutineScope(Dispatchers.Main).launch{
            try {
                val sepetYemekler = yrepo.sepettekiYemekleriGetir("akliars")
                if (sepetYemekler.isNotEmpty()) {
                    sepetYemeklerListesi.value = sepetYemekler
               } else {
                    // Sepet boş mesajı göster veya boş liste işleme kodunu çalıştır.
                }
            }catch (e:Exception){

            }

        }
    }
    // Sepete Ekleme işlemi gerçekleştirildiğinde sepetGüncelle metodumuz olmadığından sepettenYemekSilip Sepete ekleme çalıştırılır.
    fun sepettenYemekSil(yemek_id:Int){
        CoroutineScope(Dispatchers.Main).launch {
            //delay(1000)
            yrepo.sepettenYemekSil(yemek_id)
           // sepettekiYemekleriGetir()
        }
    }


}