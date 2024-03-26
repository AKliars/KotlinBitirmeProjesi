package com.akliars.kotlinbitirmeprojesi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.akliars.kotlinbitirmeprojesi.R
import com.akliars.kotlinbitirmeprojesi.databinding.FragmentKullaniciBinding
import com.akliars.kotlinbitirmeprojesi.ui.viewmodel.AnasayfaViewModel
import com.akliars.kotlinbitirmeprojesi.ui.viewmodel.KullaniciViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KullaniciFragment : Fragment() {
    private lateinit var binding: FragmentKullaniciBinding
    private lateinit var  viewModel: KullaniciViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentKullaniciBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: KullaniciViewModel by viewModels()
        viewModel = tempViewModel
    }
}