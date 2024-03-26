package com.akliars.kotlinbitirmeprojesi.ui.fragment

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.akliars.kotlinbitirmeprojesi.databinding.FragmentSiparisOnayBinding
import com.akliars.kotlinbitirmeprojesi.ui.viewmodel.SiparisOnayViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SiparisOnayFragment : Fragment() {
    private lateinit var binding: FragmentSiparisOnayBinding
    private lateinit var  viewModel: SiparisOnayViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSiparisOnayBinding.inflate(inflater,container,false)

        binding.tvAnasayfaMsg.setVisibility(View.GONE)
        binding.animationView3.setVisibility(android.view.View.GONE)

        viewModel.sepetiGuncelle()

        binding.buttonAnasayfayaDon.setOnClickListener {
            anasayfayaDon(it)
            with(binding.animationView2){
                playAnimation()
                binding.animationView2.setVisibility(View.GONE)


            }
            with(binding.animationView3){
                playAnimation()
                binding.animationView3.setVisibility(android.view.View.VISIBLE)

            }

        }


        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: SiparisOnayViewModel by viewModels()
        viewModel = tempViewModel
    }


    fun anasayfayaDon(view: View){
        binding.buttonAnasayfayaDon.setVisibility(View.GONE)
        binding.tvAnasayfaMsg.setVisibility(View.VISIBLE)
       // viewModel.sepetiGuncelle()
       // binding.tvSiparisMsg.setVisibility(View.GONE)
       val timer = object :CountDownTimer(3000,1000){
            override fun onTick(millisUntilFinished: Long) {
            }
            override fun onFinish() {
                val gecis = SiparisOnayFragmentDirections.siparisOnayAnasayfaGecis()
                Navigation.findNavController(view).navigate(gecis)
            }
        }
        timer.start()
    }

}
