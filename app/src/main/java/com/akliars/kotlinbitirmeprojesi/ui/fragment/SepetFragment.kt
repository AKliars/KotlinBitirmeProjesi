package com.akliars.kotlinbitirmeprojesi.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.akliars.kotlinbitirmeprojesi.databinding.FragmentSepetBinding
import com.akliars.kotlinbitirmeprojesi.ui.adapter.SepetYemeklerAdapter
import com.akliars.kotlinbitirmeprojesi.ui.viewmodel.SepetViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SepetFragment : Fragment() {
    private lateinit var binding: FragmentSepetBinding
    private lateinit var  viewModel: SepetViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSepetBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment


        // Sepet Boş Yazısını gizliyoruz.
        binding.textViewSepetBos.setVisibility(View.GONE)

        // SepetYemeklerListesini dinliyoruz.
        // SepetViewModel içerisindeki sepetYemeklerListesini dinleyip
        // SepetYemeklerAdapter ü Çalıştırır.
        // toplam tutarı hesaplar.
        viewModel.sepetYemeklerListesi.observe(viewLifecycleOwner){
                var toplamTutar = 0
                var tempTutar = 0

                val sepetYemeklerAdapter = SepetYemeklerAdapter(requireContext(), it, viewModel,binding)
                binding.sepetRv.adapter = sepetYemeklerAdapter

                // Sepetteki toplam tutarı hesaplıyoruz.
                for (i in it) {
                    tempTutar = (i.yemek_fiyat * i.yemek_siparis_adet)
                    toplamTutar += tempTutar
                }

                // Toplam tutarı sepetteki görünüme aktarıyoruz.
                binding.tvToplamTutarSonuc.text = toplamTutar.toString() + "₺"
        }


        //binding.tvToplamTutarSonuc.text

        // Sepetteki yemekleri getir fonks.nu çalıştırıyoruz ? ?? ? ??
       viewModel.sepettekiYemekleriGetir(binding)
        //binding.sepetRv.layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)

         // RecyclerViewimizin nasıl görüneceğini ayarlıyoruz.
         binding.sepetRv.layoutManager = LinearLayoutManager(requireContext())
        // Siparişi tamamla diyerek sipariş tamamlandı sayfasına gidiyoruz.
        //
        binding.buttonSiparisiTamamla.setOnClickListener {
           // Log.e("ali",".burası çalıştı")
            viewModel.sepetYemeklerListesi.observe(viewLifecycleOwner){
                if (it.isNotEmpty()){
                    val gecis = SepetFragmentDirections.siparisOnayGecis()
                    Navigation.findNavController(binding.buttonSiparisiTamamla).navigate(gecis)

                }else{
                    val snackbar = Snackbar.make(binding.root, "Sepet Ürün Bulunamadı!", Snackbar.LENGTH_SHORT)
                    snackbar.show()
                }
            }
        }


        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: SepetViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
       // viewModel.sepettekiYemekleriGetir()
       // viewModel.sepettekiYemekleriGetir()
    }

}