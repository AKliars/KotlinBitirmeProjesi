package com.akliars.kotlinbitirmeprojesi.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.akliars.kotlinbitirmeprojesi.data.entity.SepetYemekler
import com.akliars.kotlinbitirmeprojesi.data.entity.Yemekler
import com.akliars.kotlinbitirmeprojesi.databinding.FragmentSepetBinding
import com.akliars.kotlinbitirmeprojesi.databinding.SepetCardTasarimBinding
import com.akliars.kotlinbitirmeprojesi.ui.fragment.AnasayfaFragmentDirections
import com.akliars.kotlinbitirmeprojesi.ui.viewmodel.SepetViewModel
import com.bumptech.glide.Glide
import kotlinx.coroutines.delay

class SepetYemeklerAdapter(var mContext: Context, private var yemekListesi:List<SepetYemekler>, var viewModel: SepetViewModel,var binding: FragmentSepetBinding) : RecyclerView.Adapter<SepetYemeklerAdapter.CardTasarimTutucu>() {

        inner class CardTasarimTutucu(var tasarim:SepetCardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
            // Card Tasarımın ViewBinding kısmını gerçekleştiriyoruz.

            val binding = SepetCardTasarimBinding.inflate(LayoutInflater.from(mContext),parent,false)
            return CardTasarimTutucu(binding)
        }


        override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {

                 var sepetMiktar : Int
                 val urun = yemekListesi.get(position) // Listemizdeki ilgili ürün
                 val t = holder.tasarim

               /*  var ali  = MutableLiveData<Int>()
                 ali.value = t.tvSepetMiktar.text.toString().toInt()*/
               // urun.yemek_fiyat = urun.yemek_fiyat*urun.yemek_siparis_adet

                 //Yemek adını görüntümüze veriyoruz.
                 t.tvYemekAdi.text = urun.yemek_adi
                 //Ürünün miktarı ile çarpılıp son fiyatını gösterdiğimiz yer
                 t.tvSepetUrunFiyat.text = (urun.yemek_fiyat * urun.yemek_siparis_adet).toString() +"₺"
                 //Yemeğin miktarını(kaç adet bilgisi) ekranda gösterdiğimiz yer
                 t.tvSepetMiktar.text = urun.yemek_siparis_adet.toString()
                 val url = "http://kasimadalan.pe.hu/yemekler/resimler/${urun.yemek_resim_adi}"
                 Glide.with(mContext).load(url).into(t.sepetUrunResim)

                 sepetMiktar = t.tvSepetMiktar.text.toString().toInt()
                 t.sepetBtnArttR.isEnabled = true
                 t.sepetBtnArttR.setOnClickListener (){
                   //  sepetMiktar = t.tvSepetMiktar.text.toString().toInt()
                     sepetMiktar +=1
                     t.tvSepetMiktar.text = sepetMiktar .toString()
                     urun.yemek_siparis_adet = sepetMiktar
                     t.tvSepetUrunFiyat.text = (urun.yemek_fiyat *  urun.yemek_siparis_adet).toString() + "₺"

                     viewModel.sepeteYemekEkle(urun.sepet_yemek_id,urun.yemek_adi,urun.yemek_resim_adi,urun.yemek_fiyat,sepetMiktar,"akliars")
                     viewModel.sepettenYemekSil(urun.sepet_yemek_id)
                     t.sepetBtnArttR.isEnabled = false
                 }

                 t.sepetBtnAzalt.isEnabled = true
                 t.sepetBtnAzalt.setOnClickListener (){
                     if (sepetMiktar>1){
                         //sepetMiktar = t.tvSepetMiktar.text.toString().toInt()
                         sepetMiktar -=1

                         t.tvSepetMiktar.text = sepetMiktar .toString()
                         t.tvSepetUrunFiyat.text = (urun.yemek_fiyat *  urun.yemek_siparis_adet).toString() + "₺"

                         urun.yemek_siparis_adet = sepetMiktar

                         viewModel.sepeteYemekEkle(urun.sepet_yemek_id,urun.yemek_adi,urun.yemek_resim_adi,urun.yemek_fiyat,sepetMiktar,"akliars")
                         viewModel.sepettenYemekSil(urun.sepet_yemek_id)
                         t.sepetBtnAzalt.isEnabled = false
                     }
                 }

                 // Butona tıklandığında sepetteki ürünü siler
                 t.buttonSil.setOnClickListener () {
                     viewModel.sepettenYemekSil(urun.sepet_yemek_id)
                     viewModel.sepettekiYemekleriGetir(binding)
                   //  viewModel.sepettekiYemekleriGetir()
                 }






        }


    // Listedeki Item Sayısını bize döner
        override fun getItemCount(): Int {
            return yemekListesi.size

        }


    }
