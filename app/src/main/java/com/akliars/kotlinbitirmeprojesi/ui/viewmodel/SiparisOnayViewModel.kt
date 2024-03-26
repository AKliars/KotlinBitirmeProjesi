package com.akliars.kotlinbitirmeprojesi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.akliars.kotlinbitirmeprojesi.data.repository.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SiparisOnayViewModel @Inject constructor (var yrepo: YemeklerRepository): ViewModel()  {



    fun sepettenYemekSil(yemek_id:Int){
        CoroutineScope(Dispatchers.Main).launch {
            Log.e("sepettenYemekSil","sepetViewModel")
            yrepo.sepettenYemekSil(yemek_id)
        }
    }
    fun sepetiGuncelle() {
        CoroutineScope(Dispatchers.Main).launch{
            try {
                Log.e("Siparis","SepetiGuncelle Calisti")
                if (yrepo.sepettekiYemekleriGetir("akliars").isNotEmpty()){
                    Log.e("Siparis","döngü çalıştı")
                    val sepetYemekler = yrepo.sepettekiYemekleriGetir("akliars")

                    for (sepet in sepetYemekler){
                        sepettenYemekSil(sepet.sepet_yemek_id)
                    }
                }
            }catch (e:Exception){

            }


        }
    }


}