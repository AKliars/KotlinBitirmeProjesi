package com.akliars.kotlinbitirmeprojesi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.akliars.kotlinbitirmeprojesi.data.entity.Yemekler
import com.akliars.kotlinbitirmeprojesi.data.repository.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnasayfaViewModel @Inject constructor (var yrepo:YemeklerRepository) : ViewModel() {
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()

    init {
        yemekleriYukle()
    }

    fun yemekleriYukle(){
        CoroutineScope(Dispatchers.Main).launch{
                yemeklerListesi.value =  yrepo.yemekleriYukle()


          //  Log.e("aramaYemekleriYukle",yemeklerListesi.value.toString())
        }
    }



    fun ara(aramaKelimesi:String){

              //  val YemeklerList = mutableListOf<Yemekler>()
                  //  Log.e("arama",yemek.yemek_adi)
                  //  Log.e("aramaYemeklerList",YemeklerList.toString())
                  //  yemeklerListesi.value = YemeklerList
                    Log.e("aramaMutable",yemeklerListesi.value.toString())
                    try {
                       // val filteredList =
                         //   yemeklerListesi.value!!.filter { it.yemek_adi.contains(aramaKelimesi) }
                          val filteredList = yemeklerListesi.value!!.filter { it.yemek_adi.contains(aramaKelimesi) }

                        if (filteredList.isNotEmpty()) {
                            Log.e("arama","filter çalıştı")
                            Log.e("arama",filteredList.toString())

                            yemeklerListesi.value = filteredList
                            Log.e("arama",yemeklerListesi.value.toString())
                        }else{
                            Log.e("arama","ELSE ÇALIŞTI")
                            Log.e("arama",yemeklerListesi.value.toString())
                            yemekleriYukle()
                        }



                    }catch (e:Exception){
                        yemekleriYukle()
                    }
    }

}