package com.akliars.kotlinbitirmeprojesi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.akliars.kotlinbitirmeprojesi.R
import com.akliars.kotlinbitirmeprojesi.databinding.FragmentBegenilenUrunBinding
import com.akliars.kotlinbitirmeprojesi.ui.viewmodel.AnasayfaViewModel
import com.akliars.kotlinbitirmeprojesi.ui.viewmodel.BegenilenUrunViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BegenilenUrunFragment : Fragment() {
    private lateinit var binding: FragmentBegenilenUrunBinding
    private lateinit var  viewModel: BegenilenUrunViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBegenilenUrunBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: BegenilenUrunViewModel by viewModels()
        viewModel = tempViewModel
    }
}