package com.akliars.kotlinbitirmeprojesi.ui.viewmodel

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.akliars.kotlinbitirmeprojesi.data.entity.SepetYemekler
import com.akliars.kotlinbitirmeprojesi.data.entity.Yemekler
import com.akliars.kotlinbitirmeprojesi.data.repository.YemeklerRepository
import com.akliars.kotlinbitirmeprojesi.databinding.FragmentSepetBinding
import com.akliars.kotlinbitirmeprojesi.ui.fragment.AnasayfaFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SepetViewModel @Inject constructor (var yrepo: YemeklerRepository): ViewModel()  {

    // Sepetteki Yemek Listesini LiveData Olarak Bize sunar
   var sepetYemeklerListesi = MutableLiveData<List<SepetYemekler>>()



    // + veya - butonuna basıldığında çalışacak kısım
    fun sepeteYemekEkle(yemek_id:Int,yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String){
        CoroutineScope(Dispatchers.Main).launch{
            Log.e("sepeteYemekEkle","sepetViewModel")
            //sepettenYemekSil(yemek_id)
          //  delay(3000)

            yrepo.sepeteYemekEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
           // sepettekiYemekleriGetir()

        }
    }

    // Sepetteki yemeklerin listesini yani apiden çektiğimiz verilerimizi MutableLiveDataya aktarıyoruz
    fun sepettekiYemekleriGetir(binding: FragmentSepetBinding){
        CoroutineScope(Dispatchers.Main).launch{
            try {
                Log.e("sepettekiYemekleriGetir","sepetViewModel")
                    sepetYemeklerListesi.value = yrepo.sepettekiYemekleriGetir("akliars")
                    binding.buttonSiparisiTamamla.setVisibility(View.VISIBLE)
                    binding.textViewSepetBos.setVisibility(View.GONE)
            }catch (e:Exception){
             //   Log.d("Sepet",e.toString()+"burası")
              //  Log.d("Sepet",sepetYemeklerListesi.value.toString())
                sepetYemeklerListesi.value = mutableListOf()
                binding.buttonSiparisiTamamla.setVisibility(View.GONE)
                binding.textViewSepetBos.setVisibility(View.VISIBLE)

            }

        }
    }

    fun sepettekiYemekleriGetir(){
        CoroutineScope(Dispatchers.Main).launch{
            try {
            //    Log.e("sepettekiYemekleriGetir","sepetViewModel")

                sepetYemeklerListesi.value = yrepo.sepettekiYemekleriGetir("akliars")
               // binding.buttonSiparisiTamamla.setVisibility(View.VISIBLE)
            }catch (e:Exception){
             //   Log.d("Sepet",e.toString()+"burası")
             //   Log.d("Sepet",sepetYemeklerListesi.value.toString())
                sepetYemeklerListesi.value = mutableListOf()
              //  binding.buttonSiparisiTamamla.setVisibility(View.GONE)

            }

        }
    }



    fun sepettenYemekSil(yemek_id:Int){
        CoroutineScope(Dispatchers.Main).launch {
            Log.e("sepettenYemekSil","sepetViewModel")
            yrepo.sepettenYemekSil(yemek_id)
            sepettekiYemekleriGetir()

        }
    }

}