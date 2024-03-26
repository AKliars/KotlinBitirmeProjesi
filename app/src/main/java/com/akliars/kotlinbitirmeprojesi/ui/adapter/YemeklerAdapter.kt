package com.akliars.kotlinbitirmeprojesi.ui.adapter

import android.content.Context
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.akliars.kotlinbitirmeprojesi.data.entity.Yemekler
import com.akliars.kotlinbitirmeprojesi.databinding.FragmentAnasayfaBinding
import com.akliars.kotlinbitirmeprojesi.databinding.UrunCardTasarimBinding
import com.akliars.kotlinbitirmeprojesi.ui.fragment.AnasayfaFragmentDirections
import com.akliars.kotlinbitirmeprojesi.ui.fragment.SiparisOnayFragmentDirections
import com.bumptech.glide.Glide

class YemeklerAdapter(var mContext: Context, var yemekListesi:List<Yemekler>, var binding: FragmentAnasayfaBinding) : RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(var tasarim:UrunCardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        // Card Tasarımın ViewBinding kısmını gerçekleştiriyoruz.

        val binding = UrunCardTasarimBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return CardTasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val urun = yemekListesi.get(position) // Listemizdeki ilgili ürün
        val t = holder.tasarim
        t.textViewUrunAdi.text = urun.yemek_adi
        t.textViewFiyat.text = urun.yemek_fiyat.toString() +"₺"
        t.imageView2.setOnClickListener(){

            val gecis = AnasayfaFragmentDirections.urunDetayGecis(yemek = urun)
            Navigation.findNavController(it).navigate(gecis)
        }
        t.buttonIncele.setOnClickListener(){
            binding.yemeklerRv.setVisibility(View.GONE)
            binding.animationView4.setVisibility(View.VISIBLE)
            val timer = object : CountDownTimer(2000,1000){
                override fun onTick(millisUntilFinished: Long) {
                }
                override fun onFinish() {
                    val gecis = AnasayfaFragmentDirections.urunDetayGecis(yemek = urun)
                    Navigation.findNavController(it).navigate(gecis)
                }
            }
            timer.start()



        }
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${urun.yemek_resim_adi}"
        Glide.with(mContext).load(url).into(t.imageView2)





    }
    // Listedeki Item Sayısını bize döner
    override fun getItemCount(): Int {
        return yemekListesi.size

    }


}