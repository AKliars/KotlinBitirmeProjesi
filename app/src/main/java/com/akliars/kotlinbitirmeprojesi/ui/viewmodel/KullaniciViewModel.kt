package com.akliars.kotlinbitirmeprojesi.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.akliars.kotlinbitirmeprojesi.data.repository.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class KullaniciViewModel@Inject constructor (var yrepo: YemeklerRepository) : ViewModel()  {
}