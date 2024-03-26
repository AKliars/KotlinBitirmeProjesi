package com.akliars.kotlinbitirmeprojesi.ui.fragment

import android.icu.lang.UCharacter.VerticalOrientation
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.akliars.kotlinbitirmeprojesi.R
import com.akliars.kotlinbitirmeprojesi.data.entity.Yemekler
import com.akliars.kotlinbitirmeprojesi.databinding.FragmentAnasayfaBinding
import com.akliars.kotlinbitirmeprojesi.ui.adapter.YemeklerAdapter
import com.akliars.kotlinbitirmeprojesi.ui.viewmodel.AnasayfaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnasayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnasayfaBinding
    private lateinit var  viewModel: AnasayfaViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var yemekListesi : List<Yemekler>
         yemekListesi = listOf()


        binding = FragmentAnasayfaBinding.inflate(inflater,container,false)

        binding.yemeklerRv.setVisibility(View.VISIBLE)
        binding.animationView4.setVisibility(View.GONE)



        viewModel.yemeklerListesi.observe(viewLifecycleOwner){
            yemekListesi = it
            val yemeklerAdapter = YemeklerAdapter(requireContext(),it,binding)
            binding.yemeklerRv.adapter = yemeklerAdapter
        }

        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextChange(newText: String): Boolean {//Harf girdikçe ve sildikçe çalışır
            //    if (newText == ""){
                   viewModel.ara(newText)
                //    viewModel.yemekleriYukle()
                  //  adapterCalistir(yemekListesi)

              //  }else{
                    //viewModel.ara(newText,yemekListesi)
                  //  adapterCalistir(yemekListesi)
            //    }
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {//Klavyedeki ara butonu ile çalışır
             //   viewModel.ara(query,yemekListesi)
                return false
            }


        })


        binding.yemeklerRv.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        // val gecis = AnasayfaFragmentDirections.urunDetayGecis(mesaj = "Merhaba",sayi= 5) // Args Konusu ile ilgili, Giden fragmenttan bundle olarak alacağız.
        //  Navigation.findNavController(it).navigate(R.id.urunDetayGecis)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: AnasayfaViewModel by viewModels()
        viewModel = tempViewModel
    }
   /* fun adapterCalistir(it:List<Yemekler>){
        val yemeklerAdapter = YemeklerAdapter(requireContext(),it,binding)
        binding.yemeklerRv.adapter = yemeklerAdapter
    }*/

}


