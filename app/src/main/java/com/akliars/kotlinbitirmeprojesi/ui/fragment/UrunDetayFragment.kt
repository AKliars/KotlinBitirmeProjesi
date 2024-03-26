package com.akliars.kotlinbitirmeprojesi.ui.fragment

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.akliars.kotlinbitirmeprojesi.R
import com.akliars.kotlinbitirmeprojesi.data.entity.SepetYemekler
import com.akliars.kotlinbitirmeprojesi.databinding.FragmentAnasayfaBinding
import com.akliars.kotlinbitirmeprojesi.databinding.FragmentUrunDetayBinding
import com.akliars.kotlinbitirmeprojesi.ui.viewmodel.AnasayfaViewModel
import com.akliars.kotlinbitirmeprojesi.ui.viewmodel.SepetViewModel
import com.akliars.kotlinbitirmeprojesi.ui.viewmodel.UrunDetayViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UrunDetayFragment : Fragment() {
    private lateinit var binding: FragmentUrunDetayBinding
    private lateinit var  viewModel: UrunDetayViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUrunDetayBinding.inflate(inflater,container,false)

        var sepetteki_yemek_id =0
        var miktar = 1

        with(binding.animationView){
            playAnimation()
            binding.animationView.setVisibility(View.GONE)

        }

        // Anasayfadan gelen bundle, yani Args.
        val bundle : UrunDetayFragmentArgs by navArgs()
        val yemek =  bundle.yemek
        viewModel.sepettekiYemekleriGetir()

        // Anasayfadan gelen yemek objesindeki verilerimizi widgetlarımıza data olarak verdik
        binding.tvUrunAdi.text = yemek.yemek_adi
        binding.tvUrunDetayUrunAciklamasi.text = yemek.yemek_fiyat.toString() + "₺"
        binding.tvMiktar.text = miktar.toString()
        // Glide Kütüphanesi ile resmimizi görsel nesnelerimize bağladık
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Glide.with(this).load(url).into(binding.urunDetayResim)

        // Artı Butonu çalışma kısmı
        binding.buttonMiktarArttir.setOnClickListener(){
            miktar +=1
            binding.tvMiktar.text = miktar.toString()
            binding.buttonSepeteEkle.isEnabled =true
            binding.textViewUDToplam.text = (yemek.yemek_fiyat * miktar).toString() + " ₺"

        }

        binding.textViewUDToplam.text = (yemek.yemek_fiyat * miktar).toString()

        //Eksi Butonu çalışma kısmı
        binding.buttonMiktarAzalt.setOnClickListener(){

            if(miktar > 1){
                miktar -=1
                binding.tvMiktar.text = miktar.toString()
                binding.buttonSepeteEkle.isEnabled =true
                binding.textViewUDToplam.text = (yemek.yemek_fiyat * miktar).toString() + " ₺"
            }
        }

        binding.buttonGeri.setOnClickListener{
            val gecis = UrunDetayFragmentDirections.urunDetayAnasayfaGecis()
            Navigation.findNavController(it).navigate(gecis)
        }

        // Sepetteki yemek listemizi dinliyoruz ve bazı işlemler yapıyoruz.
        viewModel.sepetYemeklerListesi.observe(viewLifecycleOwner){
            if (it.isNotEmpty()){
                for (i in it){
                    if (i.yemek_adi == yemek.yemek_adi){
                      //  binding.tvMiktar.text = i.yemek_siparis_adet.toString()
                      //  binding.buttonSepeteEkle.text = "Sepeti Güncelle"
                        urunDetayiGuncelle(i)
                        sepetteki_yemek_id = i.sepet_yemek_id
                        miktar = i.yemek_siparis_adet
                        binding.textViewUDToplam.text = (yemek.yemek_fiyat * miktar).toString() + " ₺"
                    }
                }
            }
            else{
              //  binding.buttonSepeteEkle.text = "Sepeti Ekle olarak değişti"
                binding.buttonSepeteEkle.text = "Sepete Ekle"
                binding.tvMiktar.text = "1"
            }
        }

        // Sepete ekleme işlemi gerçekleştirilir, eğer sepette aynı ürün varsa o zaman ürün silinerek tekrar eklenir
        binding.buttonSepeteEkle.setOnClickListener(){
            with(binding.animationView){
                playAnimation()
                binding.animationView.setVisibility(View.VISIBLE)
            }
            // Eğer sepette aynı ürün varsa ürünü siler
            if (sepetteki_yemek_id>0){
                viewModel.sepettenYemekSil(sepetteki_yemek_id)
            }
            // Sepete yemek ekleyip sepetteki yemekleri getir fonksiyonunu çalıştırır
            viewModel.sepeteYemekEkle(yemek.yemek_adi,yemek.yemek_resim_adi,yemek.yemek_fiyat,miktar,"akliars")
            viewModel.sepettekiYemekleriGetir()
            binding.buttonSepeteEkle.isEnabled = false
            binding.buttonSepeteEkle.text="Sepete Eklendi"

            val timer = object :CountDownTimer(1500,1000){
                override fun onTick(millisUntilFinished: Long) {
                }

                override fun onFinish() {
                    binding.animationView.setVisibility(View.GONE)
                    viewModel.sepettekiYemekleriGetir()

                }
            }
            timer.start()

        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: UrunDetayViewModel by viewModels()
        viewModel = tempViewModel
    }



    override fun onResume() {
        super.onResume()
        val bundle : UrunDetayFragmentArgs by navArgs()
        val yemek =  bundle.yemek
        viewModel.sepettekiYemekleriGetir()
        viewModel.sepetYemeklerListesi.observe(viewLifecycleOwner){
            if (it.isNotEmpty()){
                for (i in it){
                    if (i.yemek_adi == yemek.yemek_adi){
                        //  binding.tvMiktar.text = i.yemek_siparis_adet.toString()
                        //  binding.buttonSepeteEkle.text = "Sepeti Güncelle"
                        urunDetayiGuncelle(i)
                    }
                }
            } else{
                //  binding.buttonSepeteEkle.text = "Sepeti Ekle olarak değişti"
                binding.buttonSepeteEkle.text = "Sepete Ekle"
                binding.tvMiktar.text = "1"
            }
        }
        viewModel.sepettekiYemekleriGetir()

    }
    fun urunDetayiGuncelle(i:SepetYemekler){
        binding.tvMiktar.text = i.yemek_siparis_adet.toString()
        binding.buttonSepeteEkle.text = "Sepeti Güncelle"

    }
}