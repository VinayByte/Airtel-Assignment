package com.test.airtel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject


/**
 * Created by VINAY on 2019-11-18.
 * vinay6kr@gmail.com
 */
class AddressViewModelFactory @Inject constructor(
    private val addressViewModel: AddressViewModel) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddressViewModel::class.java!!)) {
            return addressViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}