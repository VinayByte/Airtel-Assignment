package com.test.airtel.data.source

import android.util.Log
import com.test.airtel.data.source.local.AddressDao
import com.test.airtel.data.source.remote.ApiInterface
import com.test.airtel.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject


/**
 * Created by VINAY on 2019-11-18.
 * vinay6kr@gmail.com
 */
class AddressRepository @Inject constructor(val apiInterface: ApiInterface,
                                            val addressDao: AddressDao, val utils: Utils) {


    fun getAddress(): Observable<com.test.airtel.model.Response>? {
        val hasConnection = utils.isConnectedToInternet()
        var observableFromApi: Observable<com.test.airtel.model.Response>? = null
        if (hasConnection){
            observableFromApi = getListFromApi()
        }
//        val observableFromDb = getAddressFromDb()

//        return if (hasConnection) Observable.concatArrayEager( observableFromDb, observableFromApi)
        return observableFromApi
    }

    fun getListFromApi() : Observable<com.test.airtel.model.Response> {
      return  apiInterface.getSearchList("airtel","gurgaon")
            .doOnNext {
                Log.e("REPOSITORY API * ", it.data?.addressList?.size.toString())
//
            }
    }

//    fun getAddressFromDb(): Observable<List<AddressListItem>> {
//        return addressDao.queryAddressList()
//            .toObservable()
//            .doOnNext {
//                //Print log it.size :)
//                Log.d("REPOSITORY DB *** ", it.size.toString())
//            }
//    }

}