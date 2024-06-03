package com.trackerbreeze.features.myprofile.api.statelist

import com.trackerbreeze.features.myprofile.model.statelist.StateListApiResponse
import io.reactivex.Observable

/**
 * Created by Pratishruti on 19-02-2018.
 */
class StateListRepo(val apiService: StateListApi)  {
    fun getShopList(): Observable<StateListApiResponse> {
        return apiService.getAllState()
    }
}