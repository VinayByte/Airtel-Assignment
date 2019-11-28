package com.test.airtel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.airtel.data.source.AddressRepository
import com.test.airtel.model.AddressListItem
import com.test.airtel.model.Response
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * Created by VINAY on 2019-11-18.
 * vinay6kr@gmail.com
 */
class AddressViewModel @Inject constructor(
    private val addressRepository: AddressRepository) : ViewModel() {

    lateinit var disposableAddressObserver: DisposableObserver<Response>
    var addressResult: MutableLiveData<List<AddressListItem>> = MutableLiveData()
    var addressLoader: MutableLiveData<Boolean> = MutableLiveData()
    var addressError: MutableLiveData<String> = MutableLiveData()

    fun addressResult(): LiveData<List<AddressListItem>> {
        return addressResult
    }

    fun addressError(): LiveData<String> {
        return addressError
    }

    fun addressLoader(): LiveData<Boolean> {
        return addressLoader
    }

    fun loadAddress( ) {

        disposableAddressObserver = object : DisposableObserver<Response>() {
            override fun onComplete() {

            }

            override fun onNext(result: Response) {
                addressResult.postValue(result.data?.addressList as List<AddressListItem>?)
                addressLoader.postValue(false)
            }

            override fun onError(e: Throwable) {
                addressError.postValue(e.message)
                addressLoader.postValue(false)
            }
        }

        addressRepository.getAddress()
            ?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.debounce(400, TimeUnit.MILLISECONDS)
            ?.subscribe(disposableAddressObserver)
    }



    fun disposeElements(){
        if(null != disposableAddressObserver && !disposableAddressObserver.isDisposed) disposableAddressObserver.dispose()

    }

}